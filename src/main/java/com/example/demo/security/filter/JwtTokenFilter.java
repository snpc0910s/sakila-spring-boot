package com.example.demo.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.ultility.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.demo.entity.Roles;
import com.example.demo.entity.User;
import com.example.demo.repo.UserRepo;
import com.example.demo.ultility.JwtTokenUtil;

@Component
@Order(1)
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenUtil jwtUtil;

    @Autowired
    private UserRepo userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
            log.info("Filter JWT");
            // 1.not exist bearer token when request 
            if(!hasaAuthorizationBearer(request)) {
                filterChain.doFilter(request, response);
                return;
            }
            // 2.validate accesstoken
            String token = getAccessToken(request);
            if(!jwtUtil.validationAccessToken(token)) {
                filterChain.doFilter(request, response);
                return;
            }
            // 3. set authentication
            setAuthenticationContext(token,request);
            filterChain.doFilter(request, response);
    }
    
    private void setAuthenticationContext(String token, HttpServletRequest request) {
        UserDetails userDetails = getUserDetails(token);
        Optional<User> oUser = userRepo.findByEmail(userDetails.getUsername());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(oUser.isPresent()) {
            for(Roles role : oUser.get().getRoles()) {
                authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
            }
        }
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null, authorities);
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
    private UserDetails getUserDetails(String token) {
        User userDetails = new User();
        String[] jwtSubject = jwtUtil.getSubject(token).split(",");
        userDetails.setUserId(Integer.parseInt(jwtSubject[0]));
        userDetails.setEmail(jwtSubject[1]);
        return userDetails;
    }
    // get token from request
    private boolean hasaAuthorizationBearer(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (ObjectUtils.isEmpty(header) || !header.startsWith("Bearer")) {
            return false;
        }
        return true;
    }
    private String getAccessToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        String token = header.split(" ")[1].trim();
        return token;
    }
}

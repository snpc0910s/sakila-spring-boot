package com.example.demo.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.dto.ActorDTO;
import com.example.demo.entity.Actor;

@Repository
public interface ActorRepo extends JpaRepository<Actor,Integer>{

    @Query(value = "select new com.example.demo.dto.ActorDTO(a.actorId, a.firstName, a.lastName) from Actor a")
    List<ActorDTO> selectSomeField();
    // @Query(nativeQuery = true, value = "select a.actor_id,a.first_name,a.last_name from actor a")
    // List<Object[]> selectSomeField();

    @Query(value = "select a from Actor a") // JPQL
    // @Query(nativeQuery = true, value = "select * from actor", countQuery="select count(*) from actor") // paging native query
    Page<Actor> paging(Pageable pageable);

    @Query(value = "select a from Actor a")
    Page<Actor> findAll(Pageable pageable);
}

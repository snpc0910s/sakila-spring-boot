package com.example.demo.repo.custom;
import java.util.List;
import org.springframework.data.domain.Pageable;
import com.example.demo.entity.Actor;

public interface ActorRepoCustom {
    public List<Actor> dynamicSearch(Actor actor, Pageable page);
}

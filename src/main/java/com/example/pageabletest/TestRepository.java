package com.example.pageabletest;

import org.jeasy.random.EasyRandom;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.stream.Stream;

@Repository
public class TestRepository {
    private EasyRandom easyRandom = new EasyRandom();

    public Flux<Movie> findAll(Pageable pageable) {
        var pageNumber = pageable.getPageNumber();
        var pageSize = pageable.getPageSize();
        return Flux.fromStream(allMovies())
                .buffer(pageSize, pageNumber + 1)
                .elementAt(pageNumber, new ArrayList<>())
                .flatMapMany(Flux::fromIterable);
    }

    private Stream<Movie> allMovies() {
        var result = new ArrayList<Movie>();
        for (var i = 0; i < 20; i++) {
            result.add(easyRandom.nextObject(Movie.class));
        }
        return result.stream();
    }
}

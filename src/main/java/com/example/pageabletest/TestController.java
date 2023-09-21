package com.example.pageabletest;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping()
public class TestController {
    private final TestRepository testRepository;

    public TestController(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    @GetMapping(path="/")
    public Flux<Movie> getAll(@RequestParam int page, @RequestParam int size) {
        var pageable = PageRequest.of(page, size);
        return testRepository.findAll(pageable);
    }

}

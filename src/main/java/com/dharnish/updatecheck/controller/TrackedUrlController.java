package com.dharnish.updatecheck.controller;

import com.dharnish.updatecheck.dto.TrackedUrlRequest;
import com.dharnish.updatecheck.entity.TrackedUrl;
import com.dharnish.updatecheck.service.TrackedUrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/urls")
@RequiredArgsConstructor
public class TrackedUrlController {

    private final TrackedUrlService service;

    @PostMapping
    public TrackedUrl addUrl(@RequestBody TrackedUrlRequest request) {
        return service.save(request);
    }

    @GetMapping
    public List<TrackedUrl> getAllActive() {
        return service.getAllActive();
    }
}
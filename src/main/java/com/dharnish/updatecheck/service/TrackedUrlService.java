package com.dharnish.updatecheck.service;

import com.dharnish.updatecheck.dto.TrackedUrlRequest;
import com.dharnish.updatecheck.entity.TrackedUrl;
import com.dharnish.updatecheck.entity.User;
import com.dharnish.updatecheck.repository.TrackedUrlRepository;
import com.dharnish.updatecheck.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackedUrlService {

    private final TrackedUrlRepository trackedUrlRepository;
    private final UserRepository userRepository;

    public TrackedUrl save(TrackedUrlRequest request) {

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        TrackedUrl trackedUrl = TrackedUrl.builder()
                .url(request.getUrl())
                .user(user)
                .active(true)
                .build();

        return trackedUrlRepository.save(trackedUrl);
    }

    public List<TrackedUrl> getAllActive() {
        return trackedUrlRepository.findByActiveTrue();
    }
}
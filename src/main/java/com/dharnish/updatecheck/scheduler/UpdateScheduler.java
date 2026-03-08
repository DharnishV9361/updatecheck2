package com.dharnish.updatecheck.scheduler;

import com.dharnish.updatecheck.entity.ChangeLog;
import com.dharnish.updatecheck.entity.TrackedUrl;
import com.dharnish.updatecheck.repository.ChangeLogRepository;
import com.dharnish.updatecheck.repository.TrackedUrlRepository;
import com.dharnish.updatecheck.service.EmailService;
import com.dharnish.updatecheck.util.HashUtil;
import com.dharnish.updatecheck.util.WebContentFetcher;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UpdateScheduler {

    private final TrackedUrlRepository trackedUrlRepository;
    private final ChangeLogRepository changeLogRepository;
    private final EmailService emailService;

    @Scheduled(fixedRate = 60000)
    public void checkUpdates() {

        System.out.println("=== Scheduler Running ===");

        List<TrackedUrl> urls = trackedUrlRepository.findByActiveTrue();

        for (TrackedUrl trackedUrl : urls) {

            try {

                String content = WebContentFetcher.fetch(trackedUrl.getUrl());
                String newHash = HashUtil.generateHash(content);

                if (trackedUrl.getLastContentHash() == null) {

                    trackedUrl.setLastContentHash(newHash);
                    trackedUrlRepository.save(trackedUrl);
                    continue;
                }

                if (!trackedUrl.getLastContentHash().equals(newHash)) {

                    System.out.println("CHANGE DETECTED → " + trackedUrl.getUrl());

                    ChangeLog log = ChangeLog.builder()
                            .detectedAt(LocalDateTime.now())
                            .oldHash(trackedUrl.getLastContentHash())
                            .newHash(newHash)
                            .trackedUrl(trackedUrl)
                            .build();

                    changeLogRepository.save(log);

                    // UPDATE HASH
                    trackedUrl.setLastContentHash(newHash);
                    trackedUrlRepository.save(trackedUrl);

                    // SEND EMAIL ALERT
                    emailService.sendChangeAlert(
                            trackedUrl.getUser().getEmail(),
                            trackedUrl.getUrl()
                    );
                }

            } catch (Exception e) {

                System.out.println("Error checking URL: " + trackedUrl.getUrl());
            }
        }
    }
}
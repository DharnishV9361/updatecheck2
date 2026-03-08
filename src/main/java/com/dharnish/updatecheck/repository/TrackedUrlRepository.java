package com.dharnish.updatecheck.repository;

import com.dharnish.updatecheck.entity.TrackedUrl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrackedUrlRepository extends JpaRepository<TrackedUrl, Long> {

    List<TrackedUrl> findByActiveTrue();

}
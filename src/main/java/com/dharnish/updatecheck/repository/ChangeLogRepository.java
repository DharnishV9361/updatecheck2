package com.dharnish.updatecheck.repository;

import com.dharnish.updatecheck.entity.ChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChangeLogRepository extends JpaRepository<ChangeLog, Long> {
}
package org.n11_bootcamp.logger_service.log;

import org.springframework.data.jpa.repository.JpaRepository;


public interface ErrorLogRepository extends JpaRepository<ErrorLog, Long> {
}

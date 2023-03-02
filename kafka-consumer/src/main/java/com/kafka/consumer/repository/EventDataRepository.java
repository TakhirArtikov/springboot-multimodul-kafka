package com.kafka.consumer.repository;

import com.kafka.consumer.entity.Changes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventDataRepository extends JpaRepository<Changes,Long> {
}

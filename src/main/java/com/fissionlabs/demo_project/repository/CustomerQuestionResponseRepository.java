package com.fissionlabs.demo_project.repository;

import com.fissionlabs.demo_project.entity.CustomQuestionResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CustomerQuestionResponseRepository extends JpaRepository<CustomQuestionResponse, UUID> {
}

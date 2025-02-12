package com.fissionlabs.demo_project.repository;

import com.fissionlabs.demo_project.entity.CustomQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CustomQuestionRepository extends JpaRepository<CustomQuestion, UUID> {
}

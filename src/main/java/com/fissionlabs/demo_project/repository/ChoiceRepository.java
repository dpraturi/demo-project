package com.fissionlabs.demo_project.repository;

import com.fissionlabs.demo_project.entity.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, String> {

    @Query(value = "SELECT * FROM Choice WHERE question_id = :questionId", nativeQuery = true)
    List<Choice> findByQuestionId(@Param("questionId") UUID questionId);
}

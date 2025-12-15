package com.example.project.repository;

import com.example.project.entity.Skills;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillsRepository extends CrudRepository<Skills, Long> {
}

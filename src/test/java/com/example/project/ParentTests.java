package com.example.project;

import com.example.project.entity.Education;
import com.example.project.entity.Experience;
import com.example.project.entity.Users;
import com.example.project.repository.ExperienceRepository;
import com.example.project.repository.SkillsRepository;
import com.example.project.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class ParentTests {
    protected final ExperienceRepository experienceRepository;

    protected final UsersRepository usersRepository;

    protected final SkillsRepository skillsRepository;

    @Autowired
    public ParentTests(ExperienceRepository experienceRepository, UsersRepository usersRepository, SkillsRepository skillsRepository) {
        this.experienceRepository = experienceRepository;
        this.usersRepository = usersRepository;
        this.skillsRepository = skillsRepository;
    }

    @BeforeEach
    void setUp() {
        usersRepository.deleteAll();
        experienceRepository.deleteAll();
        skillsRepository.deleteAll();
    }

    protected Users createUser() {
        return new Users(
                "oscar",
                "",
                "ADMIN",
                "Bogota",
                "3123123123",
                "Administrador"
        );
    }

    protected Education createEducation() {
        return new Education(
                "ipi",
                "ipi",
                LocalDate.now(),
                LocalDate.now()
        );
    }

    protected Experience createExperience() {
        return new Experience(
                "CDI",
                "Backen developer",
                LocalDate.now()
        );
    }
}

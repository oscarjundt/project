package com.example.project;

import com.example.project.entity.Education;
import com.example.project.entity.Experience;
import com.example.project.entity.Users;
import com.example.project.repository.ExperienceRepository;
import com.example.project.repository.SkillsRepository;
import com.example.project.repository.UsersRepository;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;

class BaseCase {
    protected final ExperienceRepository experienceRepository;

    protected final UsersRepository usersRepository;

    protected final SkillsRepository skillsRepository;

    public BaseCase(ExperienceRepository experienceRepository, UsersRepository usersRepository, SkillsRepository skillsRepository) {
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
                "Oscar Jundt",
                "jundt@gmail.com",
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
                LocalDate.now(),
                "Bac+5"
        );
    }

    protected Experience createExperience() {
        return new Experience(
                "CDI",
                "Backen developer",
                LocalDate.now(),
                "test"
        );
    }
}

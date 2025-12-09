package com.example.project;

import com.example.project.entity.Education;
import com.example.project.entity.Experience;
import com.example.project.entity.Skills;
import com.example.project.entity.Users;
import com.example.project.repository.ExperienceRepository;
import com.example.project.repository.SkillsRepository;
import com.example.project.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.apache.catalina.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ProjectApplicationTests {

    private final ExperienceRepository experienceRepository;

    private final UsersRepository usersRepository;

    private final SkillsRepository skillsRepository;

    @Autowired
    ProjectApplicationTests(ExperienceRepository experienceRepository, UsersRepository usersRepository, SkillsRepository skillsRepository) {
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

    private Users createUser() {
        Users users = new Users();
        users.setUsername("oscar");
        users.setPassword("");
        users.setRole("ADMIN");
        users.setAddress("Bogota");
        users.setNumberPhone("3123123123");
        users.setDescp("Administrador");
        return users;
    }

    @Test
    void contextLoads() {
        Users users = createUser();
        Experience experience = new Experience();
        experience.setLabel("Estudiante");
        experience.setStatus("ACTIVO");
        experience.setDescp("Estudiante");
        experience.setStartDate(LocalDate.now());
        experience.setUsers(users);
        List<Experience> experiences = new ArrayList<>();
        experiences.add(experience);
        users.setExperience(experiences);
        usersRepository.save(users);
    }

    @Test
    void contextLoads2() {
        Users users = createUser();
        usersRepository.save(users);
        Users user = usersRepository.findById(users.getId()).get();
        Experience experience = new Experience();
        experience.setLabel("Estudiante");
        experience.setStatus("ACTIVO");
        experience.setDescp("Estudiante");
        experience.setStartDate(LocalDate.now());
        experience.setUsers(users);
        List<Experience> experiences = new ArrayList<>();
        experiences.add(experience);
        experienceRepository.save(experience);
    }

    @Test
    @Transactional
    void contextLoads3() {
        Users users = createUser();
        usersRepository.save(users);
        Users user = usersRepository.findById(users.getId()).get();
        Experience experience = new Experience();
        experience.setLabel("Estudiante");
        experience.setStatus("ACTIVO");
        experience.setDescp("Estudiante");
        experience.setStartDate(LocalDate.now());
        experience.setUsers(user);
        List<Experience> experiences = new ArrayList<>();
        experiences.add(experience);
        user.setExperience(experiences);
        usersRepository.save(user);
        Assertions.assertTrue(usersRepository.findById(user.getId()).isPresent());

        List<Experience> experiences1 = usersRepository.findById(user.getId()).get().getExperience();
        Assertions.assertFalse(experiences1.isEmpty());
    }

    @Test
    @Transactional
    void contextLoads4() {
        Users users = createUser();
        usersRepository.save(users);
        Users user = usersRepository.findById(users.getId()).get();
        Experience experience = new Experience();
        experience.setLabel("Estudiante");
        experience.setStatus("ACTIVO");
        experience.setDescp("Estudiante");
        experience.setStartDate(LocalDate.now());
        experience.setUsers(user);
        List<Experience> experiences = new ArrayList<>();
        experiences.add(experience);
        user.setExperience(experiences);
        Skills skill = new Skills();
        skill.setLabel("Java");
        List<Skills> skills = new ArrayList<>();
        skills.add(skill);
        experience.setSkills(skills);
        usersRepository.save(user);
        Assertions.assertTrue(usersRepository.findById(user.getId()).isPresent());

        List<Experience> experiences1 = usersRepository.findById(user.getId()).get().getExperience();
        Assertions.assertFalse(experiences1.isEmpty());
        Assertions.assertFalse(experiences1.get(0).getSkills().isEmpty());
    }

    @Test
    @Transactional
    void contextLoads5() {
        Users users = createUser();
        usersRepository.save(users);
        Users user = usersRepository.findById(users.getId()).get();
        Education education = new Education();
        education.setDegree("ipi");
        education.setSchool("ipi");
        education.setStartDate(LocalDate.now());
        education.setEndDate(LocalDate.now());
        education.setUsers(user);
        List<Education> educations = new ArrayList<>();
        educations.add(education);
        user.setEducation(educations);
        Skills skill = new Skills();
        skill.setLabel("Java");
        List<Skills> skills = new ArrayList<>();
        skills.add(skill);
        education.setSkills(skills);
        usersRepository.save(user);
        Assertions.assertTrue(usersRepository.findById(user.getId()).isPresent());

        List<Education> educations1 = usersRepository.findById(user.getId()).get().getEducation();
        Assertions.assertFalse(educations1.isEmpty());
        Assertions.assertFalse(educations1.get(0).getSkills().isEmpty());
    }

}

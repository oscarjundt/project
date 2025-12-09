package com.example.project;

import com.example.project.entity.Education;
import com.example.project.entity.Experience;
import com.example.project.entity.Skills;
import com.example.project.entity.Users;
import com.example.project.repository.ExperienceRepository;
import com.example.project.repository.SkillsRepository;
import com.example.project.repository.UsersRepository;
import com.example.project.service.WebService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
class WebServiceTests extends ParentTests {

    private final WebService webService;

    @Autowired
    public WebServiceTests(
            ExperienceRepository experienceRepository,
            UsersRepository usersRepository,
            SkillsRepository skillsRepository, WebService webService
    ) {
        super(experienceRepository, usersRepository, skillsRepository);
        this.webService = webService;
    }

    @Test
    void test() {
        Assertions.assertNull(webService.getUser(1L));
        Assertions.assertNull(webService.getEducation(1L));
        Assertions.assertNull(webService.getExperience(1L));
    }


    @Test
    void test2() {
        Assertions.assertNull(webService.getUser('l'));
    }

    @Test
    void test3() {
        Users users = createUser();
        usersRepository.save(users);
        Users user = usersRepository.findById(users.getId()).orElse(null);
        Assertions.assertNotNull(user);
        Experience experience = createExperience();
        experience.setUsers(user);
        List<Experience> experiences = new ArrayList<>();
        experiences.add(experience);
        user.setExperience(experiences);
        usersRepository.save(user);
        Assertions.assertNotNull(webService.getExperience(user.getId()));

        Assertions.assertNull(webService.getSkills(user));

        Assertions.assertNull(webService.getEducation(user.getId()));

    }

    @Test
    void test4() {
        Users users = createUser();
        usersRepository.save(users);
        Users user = usersRepository.findById(users.getId()).orElse(null);
        Assertions.assertNotNull(user);
        Experience experience = createExperience();
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
        Assertions.assertNotNull(webService.getExperience(user.getId()));

        Assertions.assertNotNull(webService.getSkills(user));

        Assertions.assertNull(webService.getEducation(user.getId()));

    }

    @Test
    void test5() {
        Users users = createUser();
        usersRepository.save(users);
        Users user = usersRepository.findById(users.getId()).orElse(null);
        Assertions.assertNotNull(user);
        Education education = createEducation();
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
        Assertions.assertNull(webService.getExperience(user.getId()));

        Assertions.assertNotNull(webService.getSkills(user));

        Assertions.assertNotNull(webService.getEducation(user.getId()));

    }

}

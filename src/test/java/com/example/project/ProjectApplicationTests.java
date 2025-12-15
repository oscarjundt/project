package com.example.project;

import com.example.project.entity.Education;
import com.example.project.entity.Experience;
import com.example.project.entity.Skills;
import com.example.project.entity.Users;
import com.example.project.repository.ExperienceRepository;
import com.example.project.repository.SkillsRepository;
import com.example.project.repository.UsersRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ProjectApplicationTests extends BaseCase {

    @Autowired
    public ProjectApplicationTests(
            ExperienceRepository experienceRepository,
            UsersRepository usersRepository,
            SkillsRepository skillsRepository
    ) {
        super(experienceRepository, usersRepository, skillsRepository);
    }

    @Test
    @Transactional
    void should_save_user_with_experience() {
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
        Assertions.assertTrue(usersRepository.findById(user.getId()).isPresent());

        List<Experience> experiences1 = usersRepository.findById(user.getId()).get().getExperience();
        Assertions.assertFalse(experiences1.isEmpty());
    }

    @Test
    @Transactional
    void should_save_user_with_experience_and_skills() {
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
        Assertions.assertTrue(usersRepository.findById(user.getId()).isPresent());

        List<Experience> experiences1 = usersRepository.findById(user.getId()).get().getExperience();
        Assertions.assertFalse(experiences1.isEmpty());
        Assertions.assertFalse(experiences1.get(0).getSkills().isEmpty());
    }

    @Test
    @Transactional
    void should_save_user_with_education_and_skills() {
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
        Assertions.assertTrue(usersRepository.findById(user.getId()).isPresent());
        List<Education> educations1 = usersRepository.findById(user.getId()).get().getEducation();
        Assertions.assertFalse(educations1.isEmpty());
        Assertions.assertFalse(educations1.get(0).getSkills().isEmpty());
    }

}

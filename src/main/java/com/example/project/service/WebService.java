package com.example.project.service;

import com.example.project.entity.Education;
import com.example.project.entity.Experience;
import com.example.project.entity.Skills;
import com.example.project.entity.Users;
import com.example.project.repository.EducationRepository;
import com.example.project.repository.ExperienceRepository;
import com.example.project.repository.SkillsRepository;
import com.example.project.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WebService {
    private final UsersRepository usersRepository;

    private final ExperienceRepository experienceRepository;

    private final SkillsRepository skillsRepository;

    private final EducationRepository educationRepository;

    public WebService(UsersRepository usersRepository,
                      ExperienceRepository experienceRepository,
                      SkillsRepository skillsRepository,
                      EducationRepository educationRepository) {
        this.usersRepository = usersRepository;
        this.experienceRepository = experienceRepository;
        this.skillsRepository = skillsRepository;
        this.educationRepository = educationRepository;
    }

    public Experience experience(long id) {
        Optional<Experience> experience = experienceRepository.findById(id);
        return experience.orElse(null);
    }

    public Education education(long id) {
        Optional<Education> education = educationRepository.findById(id);
        return education.orElse(null);
    }

    public Skills skills(long id) {
        Optional<Skills> experience = skillsRepository.findById(id);
        return experience.orElse(null);
    }

    public List<Users> getUsers() {
        return (List<Users>) usersRepository.findAll();
    }

    public Users getUser(long id) {
        Optional<Users> user = usersRepository.findById(id);
        return user.orElse(null);
    }

    public Users getUser(String username) {
        Optional<Users> user = usersRepository.findByUsername(username);
        return user.orElse(null);
    }

    public List<Education> getEducation(long id) {
        Optional<Users> user = usersRepository.findById(id);
        return user.map(Users::getEducation).orElse(null);
    }

    public List<Experience> getExperience(long id) {
        Optional<Users> user = usersRepository.findById(id);
        return user.map(Users::getExperience).orElse(null);
    }

    public List<Skills> getSkills(Users user) {
        List<Skills> skills = new ArrayList<>();
        if (user.getEducation() != null) {
            skills = user.getEducation().stream()
                    .filter(e -> e.getSkills() != null)
                    .flatMap(e -> e.getSkills().stream())
                    .toList();
        }
        if (user.getExperience() != null) {
            skills = user.getExperience().stream()
                    .filter(e -> e.getSkills() != null)
                    .flatMap(e -> e.getSkills().stream())
                    .toList();
        }
        return skills.isEmpty() ? null : skills;
    }
}

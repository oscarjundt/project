package com.example.project.security;

import com.example.project.entity.Education;
import com.example.project.entity.Experience;
import com.example.project.entity.Skills;
import com.example.project.entity.Users;
import com.example.project.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WebService {
    private final UsersRepository usersRepository;

    public WebService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
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
        user.getEducation().forEach(e -> skills.addAll(e.getSkills()));
        user.getExperience().forEach(e -> skills.addAll(e.getSkills()));
        return skills;
    }


}

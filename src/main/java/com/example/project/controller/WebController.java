package com.example.project.controller;

import com.example.project.entity.Education;
import com.example.project.entity.Experience;
import com.example.project.entity.Users;
import com.example.project.service.WebService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebController {

    private final WebService webService;

    public WebController(WebService webService) {
        this.webService = webService;
    }

    @GetMapping("/")
    public String index(ModelMap modelMap) {
        modelMap.put("users", webService.getUsers());
        return "index";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "search") String search) {
        Long users = webService.getUser(search).getId();
        return "redirect:/" + users;
    }

    @GetMapping("/{id}")
    public String user(ModelMap modelMap, @PathVariable(value = "id") Long id) {
        Users user = webService.getUser(id);
        if (user == null) {
            return "redirect:/";
        }
        modelMap.put("users", user);
        modelMap.put("educations", user.getEducation());
        modelMap.put("experiences", user.getExperience());
        modelMap.put("skills", webService.getSkills(user));

        return "home";
    }

    @GetMapping("/experience/{id}")
    public String experience(ModelMap modelMap, @PathVariable(value = "id") Long id) {
        Experience experience = webService.experience(id);
        if (experience == null) {
            return "redirect:/";
        }
        modelMap.put("experience", experience);
        return "experience";
    }


    @GetMapping("/education/{id}")
    public String education(ModelMap modelMap, @PathVariable(value = "id") Long id) {
        Education education = webService.education(id);
        if (education == null) {
            return "redirect:/";
        }
        modelMap.put("education", education);
        return "education";
    }
}

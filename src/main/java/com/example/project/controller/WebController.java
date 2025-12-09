package com.example.project.controller;

import com.example.project.entity.Users;
import com.example.project.security.WebService;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.thymeleaf.spring6.expression.SpringStandardExpressions;

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
    public String users(ModelMap modelMap, @PathVariable(value = "id") Long id) {
        Users user = webService.getUser(id);
        if (user == null) {
            return "redirect:/";
        }
        modelMap.put("users", user);
        modelMap.put("education", user.getEducation());
        modelMap.put("experience", user.getExperience());
        modelMap.put("skills", webService.getSkills(user));

        return "home";
    }

}

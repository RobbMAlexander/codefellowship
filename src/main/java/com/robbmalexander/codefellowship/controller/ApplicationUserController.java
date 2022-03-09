package com.robbmalexander.codefellowship.controller;



import com.robbmalexander.codefellowship.model.ApplicationUser;
import com.robbmalexander.codefellowship.repository.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Date;

@Controller
public class ApplicationUserController {
    @Autowired
    ApplicationUserRepository applicationUserRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private HttpServletRequest request;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login.html";
    }

    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup.html";
    }

    @GetMapping("/")
    public String getHomePage(Principal principal, Model model) {
        if (principal != null) {
            String username = principal.getName();
            ApplicationUser applicationUser = applicationUserRepository.findByUsername(username);
            String firstName = applicationUser.getFirstName();
            String lastName = applicationUser.getLastName();
            String bio = applicationUser.getBio();
            Date dateOfBirth = applicationUser.getDateOfBirth();

            model.addAttribute(username);
            model.addAttribute(firstName);
            model.addAttribute(lastName);
            model.addAttribute(bio);
            model.addAttribute(dateOfBirth);
        }

        return "index.html";
    }

    public void authWithHttpServletRequest(String username, String password){
        try {
            request.login(username, password);
        } catch (ServletException servletException){
            System.err.println("Could not successfully log in.");
            servletException.printStackTrace();
        }
    }

    @PostMapping("/signup")
    public RedirectView createUser (String username, String firstName, String lastName, Date dateOfBirth, String bio, String password){
        ApplicationUser applicationUser = new ApplicationUser();
        applicationUser.setUsername(username);
        applicationUser.setFirstName(firstName);
        applicationUser.setLastName(lastName);
        applicationUser.setDateOfBirth(dateOfBirth);
        applicationUser.setBio(bio);
        String hashedPassword = passwordEncoder.encode(password);
        applicationUser.setPassword(hashedPassword);

        applicationUserRepository.save(applicationUser);
        authWithHttpServletRequest(username, password);

        return new RedirectView("/");
    }

}

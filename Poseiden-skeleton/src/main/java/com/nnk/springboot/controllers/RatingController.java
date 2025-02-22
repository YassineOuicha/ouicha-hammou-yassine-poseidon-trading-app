package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("/rating")
public class RatingController {

    @Autowired
    private final RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @RequestMapping("/list")
    public String home(Model model) {
        model.addAttribute("ratings", ratingService.getAllRating());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = ( UserDetails) authentication.getPrincipal();
        model.addAttribute("username", userDetails.getUsername() );
        return "rating/list";
    }

    @GetMapping("/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    @PostMapping("/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        if (result.hasErrors()){
            return "rating/add";
        }
        ratingService.saveRating(rating);
        model.addAttribute("ratings", ratingService.getAllRating());
        return "redirect:/rating/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.getRatingById(id).orElseThrow(()-> new IllegalArgumentException("Invalid Rating Id:" + id));
        model.addAttribute("rating", rating);
        return "rating/update";
    }

    @PostMapping("/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        if (result.hasErrors()){
            return "rating/update";
        }
        rating.setId(id);
        ratingService.saveRating(rating);
        model.addAttribute("ratings", ratingService.getAllRating());
        return "redirect:/rating/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.getRatingById(id).orElseThrow(()-> new IllegalArgumentException("Invalid Rating Id:" + id));
        ratingService.deleteRatingById(id);
        model.addAttribute("ratings", ratingService.getAllRating());
        return "redirect:/rating/list";
    }
}

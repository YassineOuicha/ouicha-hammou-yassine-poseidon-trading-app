package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.RatingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class RatingController {

    @Autowired
    private RatingService ratingService;

    @RequestMapping("/rating/list")
    public String home(Model model) {
        List<Rating> ratingList = ratingService.getAllRating();
        model.addAttribute("ratingList" , ratingList);
        return "rating/list";
    }

    @GetMapping("/rating/add")
    public String addRatingForm(Rating rating) {
        return "rating/add";
    }

    @PostMapping("/rating/validate")
    public String validate(@Valid Rating rating, BindingResult result, Model model) {
        if (result.hasErrors()){
            return "rating/add";
        }
        rating = ratingService.saveRating(rating);
        model.addAttribute("rating", rating);
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.getRatingById(id)
                .orElseThrow(()-> new RuntimeException("Invalid Rating Id : " + id));
        model.addAttribute("rating", rating);
        return "rating/update";
    }

    @PostMapping("/rating/update/{id}")
    public String updateRating(@PathVariable("id") Integer id, @Valid Rating rating,
                             BindingResult result, Model model) {
        if (result.hasErrors()){
            return "rating/update";
        }
        rating.setId(id);
        ratingService.saveRating(rating);
        model.addAttribute("rating", rating);
        return "redirect:/rating/list";
    }

    @GetMapping("/rating/delete/{id}")
    public String deleteRating(@PathVariable("id") Integer id, Model model) {
        Rating rating = ratingService.getRatingById(id)
                .orElseThrow(()-> new RuntimeException("Invalid Rating Id : " + id));
        ratingService.deleteRatingById(id);
        model.addAttribute("rating", rating);
        return "redirect:/rating/list";
    }
}

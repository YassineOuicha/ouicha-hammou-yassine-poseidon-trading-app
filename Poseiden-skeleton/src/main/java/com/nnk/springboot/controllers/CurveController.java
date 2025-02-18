package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
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
public class CurveController {

    @Autowired
    private CurvePointService curvePointService;

    @RequestMapping("/curvePoint/list")
    public String home(Model model) {
        model.addAttribute("curvePoints", curvePointService.getAllCurvePoint());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint bid) {
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        if (result.hasErrors()){
            return "curvePoint/add";
        }
        curvePointService.saveCurvePoint(curvePoint);
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.getCurvePointById(id)
                .orElseThrow(()-> new RuntimeException("Invalid CurvePoint Id : " + id));
        model.addAttribute("curvePoint", curvePoint);
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        if (result.hasErrors()){
            return "curvePoint/update";
        }
        curvePoint.setId(id);
        curvePointService.saveCurvePoint(curvePoint);
        model.addAttribute("curvePoints", curvePointService.getAllCurvePoint());
        return "redirect:/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        CurvePoint curvePoint = curvePointService.getCurvePointById(id)
                .orElseThrow(()-> new RuntimeException("Invalid CurvePoint Id : " + id));
        curvePointService.deleteCurvePointById(id);
        model.addAttribute("curvePoints", curvePointService.getAllCurvePoint());
        return "redirect:/curvePoint/list";
    }
}

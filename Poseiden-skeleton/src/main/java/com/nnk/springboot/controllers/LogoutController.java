package com.nnk.springboot.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LogoutController {

    @GetMapping("/logout-success")
    public ModelAndView logoutSuccess() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("logout");
        return mav;
    }
}

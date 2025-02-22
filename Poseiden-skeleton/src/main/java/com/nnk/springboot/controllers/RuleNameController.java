package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.services.RuleNameService;
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
@RequestMapping("/ruleName")
public class RuleNameController {

    @Autowired
    private final RuleNameService ruleNameService;

    public RuleNameController(RuleNameService ruleNameService) {
        this.ruleNameService = ruleNameService;
    }

    @RequestMapping("/list")
    public String home(Model model) {
        model.addAttribute("ruleNames", ruleNameService.getAllRuleNames());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = ( UserDetails) authentication.getPrincipal();
        model.addAttribute("username", userDetails.getUsername() );
        return "ruleName/list";
    }

    @GetMapping("/add")
    public String addRuleNameForm(RuleName bid) {
        return "ruleName/add";
    }

    @PostMapping("/validate")
    public String validate(@Valid RuleName ruleName, BindingResult result, Model model) {
        if (result.hasErrors()){
            return "ruleName/add";
        }
        ruleNameService.saveRuleName(ruleName);
        model.addAttribute("ruleNames", ruleNameService.getAllRuleNames());
        return "redirect:/ruleName/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameService.getRuleNameById(id).orElseThrow(()-> new IllegalArgumentException("Invalid RuleName Id:" + id));
        model.addAttribute("ruleName", ruleName);
        return "ruleName/update";
    }

    @PostMapping("/update/{id}")
    public String updateRuleName(@PathVariable("id") Integer id, @Valid RuleName ruleName,
                             BindingResult result, Model model) {
        if(result.hasErrors()){
            return "ruleName/update";
        }
        ruleName.setId(id);
        ruleNameService.saveRuleName(ruleName);
        model.addAttribute("ruleNames", ruleNameService.getAllRuleNames());
        return "redirect:/ruleName/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteRuleName(@PathVariable("id") Integer id, Model model) {
        RuleName ruleName = ruleNameService.getRuleNameById(id).orElseThrow(()-> new IllegalArgumentException("Invalid RuleName Id:" + id));
        ruleNameService.deleteRuleNameById(id);
        model.addAttribute("ruleNames", ruleNameService.getAllRuleNames());
        return "redirect:/ruleName/list";
    }
}

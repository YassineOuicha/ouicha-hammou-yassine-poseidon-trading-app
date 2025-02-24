package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
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
@RequestMapping("/trade")
public class TradeController {

    @Autowired
    private final TradeService tradeService;

    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @RequestMapping("/list")
    public String home(Model model) {
        model.addAttribute("trades", tradeService.getAllTrades());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = ( UserDetails) authentication.getPrincipal();
        model.addAttribute("username", userDetails.getUsername() );
        return "trade/list";
    }

    @GetMapping("/add")
    public String addTradeForm(Trade trade) {
        return "trade/add";
    }

    @PostMapping("/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        if (result.hasErrors()){
            return "trade/add";
        }
        tradeService.saveTrade(trade);
        model.addAttribute("trades", tradeService.getAllTrades());
        return "redirect:/trade/list";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeService.getTradeById(id).orElseThrow(()-> new IllegalArgumentException("Invalid Trade Id: " + id));
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    @PostMapping("/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        if (result.hasErrors()){
            return "trade/update";
        }
        trade.setTradeId(id);
        tradeService.saveTrade(trade);
        model.addAttribute("trades", tradeService.getAllTrades());
        return "redirect:/trade/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeService.getTradeById(id).orElseThrow(()-> new IllegalArgumentException("Invalid Trade Id: " + id));
        tradeService.deleteTradeById(id);
        model.addAttribute("trades", tradeService.getAllTrades());
        return "redirect:/trade/list";
    }
}

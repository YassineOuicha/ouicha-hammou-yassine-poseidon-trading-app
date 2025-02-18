package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
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
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @RequestMapping("/trade/list")
    public String home(Model model) {
        model.addAttribute("tradeList", tradeService.getAllTrades());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addUser(Trade bid) {
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        if (result.hasErrors()){
            return "trade/add";
        }
        tradeService.saveTrade(trade);
        model.addAttribute("tradeList", tradeService.getAllTrades());
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeService.getTradeById(id)
                .orElseThrow(()-> new RuntimeException("Invalid Trade Id : " + id));
        model.addAttribute("trade", trade);
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {
        if (result.hasErrors()){
            return "trade/update";
        }
        trade.setTradeId(id);
        tradeService.saveTrade(trade);
        model.addAttribute("tradeList", tradeService.getAllTrades());
        return "redirect:/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        Trade trade = tradeService.getTradeById(id)
                .orElseThrow(()-> new RuntimeException("Invalid Trade Id : " + id));
        tradeService.deleteTradeById(id);
        model.addAttribute("tradeList", tradeService.getAllTrades());
        return "redirect:/trade/list";
    }
}

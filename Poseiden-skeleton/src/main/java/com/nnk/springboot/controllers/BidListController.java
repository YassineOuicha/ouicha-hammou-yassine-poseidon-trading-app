package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
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
public class BidListController {

    @Autowired
    private BidListService bidListService;

    @RequestMapping("/bidList/list")
    public String home(Model model) {
        List<BidList> bidLists = bidListService.getAllBidList();
        model.addAttribute("bidLists", bidLists);
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        if (result.hasErrors()){
            return "bidList/add";
        }
        bidListService.saveBidList(bid);
        model.addAttribute("bidList", bid);
        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        BidList bidList = bidListService.getBidListById(id)
                .orElseThrow(()-> new RuntimeException("Invalid BidList Id : " + id ));
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        if(result.hasErrors()){
            return "bidList/update";
        }
        bidList.setBidListId(id);
        bidListService.saveBidList(bidList);
        return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        bidListService.deleteBidListById(id);
        return "redirect:/bidList/list";
    }
}

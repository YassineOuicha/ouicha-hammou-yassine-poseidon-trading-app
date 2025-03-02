package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;
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

/**
 * Controller for handling bid list operations.
 */
@Controller
@RequestMapping("/bidList")
public class BidListController {

    @Autowired
    private final BidListRepository bidListRepository;

    /**
     * Constructor for BidListController.
     *
     * @param bidListRepository the repository for bid list operations
     */
    public BidListController(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    /**
     * Displays the list of bids.
     *
     * @param model the model to hold bid list data
     * @return the bid list view
     */
    @RequestMapping("/list")
    public String home(Model model) {
        List<BidList> bids = bidListRepository.findAll();
        model.addAttribute("bidLists", bids);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = ( UserDetails) authentication.getPrincipal();
        model.addAttribute("username", userDetails.getUsername() );
        return "bidList/list";
    }

    /**
     * Displays the form to add a new bid.
     *
     * @param bid the bid object to be added
     * @return the add bid form view
     */
    @GetMapping("/add")
    public String addBidForm(BidList bid) {
        return "bidList/add";
    }

    /**
     * Validates and saves a new bid.
     *
     * @param bid    the bid to be validated and saved
     * @param result the result of validation
     * @param model  the model to hold bid list data
     * @return redirect to the bid list if successful, otherwise return to the add form
     */
    @PostMapping("/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "bidList/add";
        }
        bidListRepository.save(bid);
        model.addAttribute("bidLists", bidListRepository.findAll());
        return "redirect:/bidList/list";
    }

    /**
     * Displays the form to update an existing bid.
     *
     * @param id    the id of the bid to update
     * @param model the model to hold bid data
     * @return the update bid form view
     */
    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        BidList bidList = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    /**
     * Updates an existing bid.
     *
     * @param id      the id of the bid to update
     * @param bidList the updated bid data
     * @param result  the result of validation
     * @param model   the model to hold bid list data
     * @return redirect to the bid list if successful, otherwise return to the update form
     */
    @PostMapping("/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                            BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "bidList/update";
        }
        bidList.setBidListId(id);
        bidListRepository.save(bidList);
        model.addAttribute("bidLists", bidListRepository.findAll());
        return "redirect:/bidList/list";
    }

    /**
     * Deletes a bid.
     *
     * @param id    the id of the bid to delete
     * @param model the model to hold bid list data
     * @return redirect to the bid list view
     */
    @GetMapping("/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        BidList bidList = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
        bidListRepository.delete(bidList);
        model.addAttribute("bidLists", bidListRepository.findAll());
        return "redirect:/bidList/list";
    }
}
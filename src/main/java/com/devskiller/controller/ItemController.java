package com.devskiller.controller;

import com.devskiller.dao.ItemRepository;
import com.devskiller.model.Item;
import com.devskiller.model.Review;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


public class ItemController {
    @Autowired
    private final ItemRepository itemRepository;

    public ItemController(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @RequestMapping(value = "/titles",
        produces = { "application/json", "application/stream+json" },
        method = RequestMethod.GET)
    public List<String> getTitles( @RequestParam(value = "rating", required = true) Double rating) {
        Review listReview = new Review();
         return itemRepository.findItemsWithAverageRatingLowerThan(rating)
            .stream()
            .filter(item -> listReview.getRating() > rating).collect(Collectors.toList())
             .stream().map(ist1 -> ist1.getTitle()).collect(Collectors.toList());
    }




}

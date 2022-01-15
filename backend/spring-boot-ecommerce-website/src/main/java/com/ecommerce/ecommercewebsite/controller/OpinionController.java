package com.ecommerce.ecommercewebsite.controller;

import com.ecommerce.ecommercewebsite.entity.Opinion;
import com.ecommerce.ecommercewebsite.service.OpinionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/test")
public class OpinionController {

    private final OpinionService opinionService;

    public OpinionController(OpinionService opinionService) {
        this.opinionService = opinionService;
    }

    @GetMapping("/opinions")
    public List<Opinion> getAllOpinions() {
        return opinionService.getAllOpinions();
    }

    @PostMapping("/add-opinion")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String insertOpinion(@RequestBody Opinion opinion) {
        java.sql.Timestamp date = new java.sql.Timestamp(new java.util.Date().getTime());

        opinionService.insertOpinion(opinion.getDescription(), opinion.getProduct_id(), opinion.getUser_id(), opinion.getRating(), date);

        return "Opinion added successfully";
    }
}

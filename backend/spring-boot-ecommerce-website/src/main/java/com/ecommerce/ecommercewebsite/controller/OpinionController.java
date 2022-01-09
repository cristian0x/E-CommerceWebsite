package com.ecommerce.ecommercewebsite.controller;

import com.ecommerce.ecommercewebsite.entity.Opinion;
import com.ecommerce.ecommercewebsite.service.OpinionService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}

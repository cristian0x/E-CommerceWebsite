package com.ecommerce.ecommercewebsite.service;

import com.ecommerce.ecommercewebsite.dao.OpinionRepository;
import com.ecommerce.ecommercewebsite.entity.Opinion;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpinionServiceImpl implements OpinionService {

    private final OpinionRepository opinionRepository;

    public OpinionServiceImpl(OpinionRepository opinionRepository) {
        this.opinionRepository = opinionRepository;
    }

    @Override
    public List<Opinion> getAllOpinions() {
        return opinionRepository.getAllOpinions();
    }
}

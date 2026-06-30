package com.example.tugasbesarpbo.controller;

import  com.example.tugasbesarpbo.util.InputHandler;
import com.example.tugasbesarpbo.model.KnowledgeRepository;
import java.util.ArrayList;

public class KnowledgeController {
    private final KnowledgeRepository repository;

    public KnowledgeController(KnowledgeRepository repository) {

        this.repository = repository;
    }
}

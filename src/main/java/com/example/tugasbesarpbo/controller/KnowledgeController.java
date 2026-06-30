package com.example.tugasbesarpbo.controller;

import  com.example.tugasbesarpbo.util.InputHandler;
import com.example.tugasbesarpbo.model.KnowledgeRepository;
import java.util.ArrayList;

public class KnowledgeController {
    private final KnowledgeRepository repository;

    public KnowledgeController(KnowledgeRepository repository) {

        this.repository = repository;
    }

    // GET ALL DATA
    public ArrayList<Putusan> getSemuaPutusan() {

        return repository.getDaftarSemua();
    }

    // GET TOTAL DATA
    public int getTotalDataPutusan(){

        return  repository.getTotalData();
    }
}

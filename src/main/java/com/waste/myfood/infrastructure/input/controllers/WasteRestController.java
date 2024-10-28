package com.waste.myfood.infrastructure.input.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waste.myfood.application.input.ManageWasteCUIntPort;

import lombok.AllArgsConstructor;

@CrossOrigin(origins = {"http://localhost:5050"})
@RestController
@RequestMapping("/api/v1/wastes")
@AllArgsConstructor
public class WasteRestController {
    private final ManageWasteCUIntPort domain;
    
}

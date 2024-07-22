package com.elifgenc.service.controller.impl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealtyController {
    @GetMapping(value = "/api/healty")
    public String healty() {
        return "Çalışıyor.";
    }
}

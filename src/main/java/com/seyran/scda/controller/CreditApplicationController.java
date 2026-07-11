package com.seyran.scda.controller;


import com.seyran.scda.service.CreditApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/credit-applications")
@RequiredArgsConstructor
public class CreditApplicationController {
    private final CreditApplicationService service;
}

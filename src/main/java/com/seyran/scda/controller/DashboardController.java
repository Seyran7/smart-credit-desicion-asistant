package com.seyran.scda.controller;

import com.seyran.scda.dto.response.DashboardResponse;
import com.seyran.scda.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/dashboard")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public DashboardResponse dashboard() {

        return dashboardService.getDashboard();

    }

}
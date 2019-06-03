package com.khwilo.employee_manager.controller;

import com.khwilo.employee_manager.payload.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class WelcomeController {

    @GetMapping()
    public ResponseEntity<Object> displayWelcomeMessage() {
        return new ResponseEntity<>(
                new ApiResponse(200, "Welcome to employees' inventory manager"),
                HttpStatus.OK
        );
    }
}

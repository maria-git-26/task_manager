package org.example.task_manager.controller;

import lombok.RequiredArgsConstructor;
import org.example.task_manager.security.JwtService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RequestMapping("/test")
@RequiredArgsConstructor
@RestController
public class TestController {


        private final JwtService jwtService;

        @GetMapping
        public String test() {
            return jwtService.generateToken("masha");
        }

}


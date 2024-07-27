package com.example.gym_app.controller;

import com.example.gym_app.dto.AccountDto;
import com.example.gym_app.dto.BMIRequest;
import com.example.gym_app.model.User;
import com.example.gym_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
@CrossOrigin
public class UserController {

    private final UserService service;

    @GetMapping("/account")
    public ResponseEntity<AccountDto> getAccount(Authentication connectedUser) throws Exception {
        AccountDto accountDto = service.getAccount(connectedUser);
        return ResponseEntity.ok(accountDto);
    }

    @PostMapping("/bmi_calculator")
    public ResponseEntity<String> calculateBMI(@RequestBody BMIRequest bmiRequest,
                                               Authentication connectedUser) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok("" + service.calculateBMI(bmiRequest, connectedUser));
    }
}

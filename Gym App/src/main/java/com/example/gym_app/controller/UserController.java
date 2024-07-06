package com.example.gym_app.controller;

import com.example.gym_app.dto.AccountDto;
import com.example.gym_app.dto.BMIRequest;
import com.example.gym_app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService service;

    @GetMapping("/account/{id}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        AccountDto accountDto = service.getAccount(id);
        return ResponseEntity.ok(accountDto);
    }

    @PostMapping("/bmi_calculator/{id}")
    public ResponseEntity<Double> calculateBMI(@RequestBody BMIRequest bmiRequest,
                                               @PathVariable("id") Long userId) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok(service.calculateBMI(bmiRequest, userId));
    }
}

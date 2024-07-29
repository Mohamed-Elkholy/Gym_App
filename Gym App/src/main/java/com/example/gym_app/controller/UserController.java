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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


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

    @PostMapping(value = "/image", consumes = "multipart/form-data")
    public ResponseEntity<String> addUserImage(Authentication connectedUser, @RequestParam("photo") MultipartFile photo) throws IOException {
        service.addUserImage(connectedUser, photo);
        return ResponseEntity.ok("UserImage has been added successfully :)");
    }

    @PostMapping("/bmi_calculator")
    public ResponseEntity<String> calculateBMI(@RequestBody BMIRequest bmiRequest,
                                               Authentication connectedUser) throws ChangeSetPersister.NotFoundException {
        return ResponseEntity.ok("Your BMI test result = " + service.calculateBMI(bmiRequest, connectedUser));
    }
}

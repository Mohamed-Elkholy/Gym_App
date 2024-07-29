package com.example.gym_app.service;

import com.example.gym_app.config.JwtService;
import com.example.gym_app.dto.AccountDto;
import com.example.gym_app.dto.BMIRequest;
import com.example.gym_app.model.User;
import com.example.gym_app.repository.SleepTrackerRepository;
import com.example.gym_app.repository.UserRepository;
import com.example.gym_app.repository.WaterTrackerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository repository;
    private final WaterTrackerRepository waterTrackerRepository;
    private final SleepTrackerRepository sleepTrackerRepository;
    private final JwtService jwtService;

    public AccountDto getAccount(Authentication connectedUser) throws ChangeSetPersister.NotFoundException {
        User user = (User) connectedUser.getPrincipal();
        AccountDto result = AccountDto.builder()
                .name(user.getUsername())
                .image(user.getImage())
                .age(user.getAge())
                .weight(user.getWeight())
                .height(user.getHeight())
                .waterTrackerList(waterTrackerRepository.findAllByUser(user.getId()))
                .sleepTrackerList(sleepTrackerRepository.findAllByUser(user.getId()))
                .build();
        return result;
    }

    public Double calculateBMI(BMIRequest bmiRequest, Authentication connectedUser) throws ChangeSetPersister.NotFoundException {
        User user = (User) connectedUser.getPrincipal();
        user.setAge(bmiRequest.getAge());
        user.setHeight(bmiRequest.getHeight());
        user.setWeight(bmiRequest.getWeight());
        repository.save(user);
        double result = bmiRequest.getWeight() / Math.pow(bmiRequest.getHeight() / 100.0, 2);
        return result;
    }


    public void addUserImage(Authentication connectedUser, MultipartFile photo) throws IOException {
        User user = (User) connectedUser.getPrincipal();
        user.setImage(photo.getBytes());
        repository.save(user);
    }
}

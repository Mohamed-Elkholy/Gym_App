package com.example.gym_app.service;

import com.example.gym_app.dto.AccountDto;
import com.example.gym_app.dto.BMIRequest;
import com.example.gym_app.exceptions.GlobalExceptionHandler;
import com.example.gym_app.model.User;
import com.example.gym_app.repository.SleepTrackerRepository;
import com.example.gym_app.repository.UserRepository;
import com.example.gym_app.repository.WaterTrackerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final WaterTrackerRepository waterTrackerRepository;
    private final SleepTrackerRepository sleepTrackerRepository;
    public AccountDto getAccount(Long id) throws ChangeSetPersister.NotFoundException {
        User user = repository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        AccountDto result = AccountDto.builder()
                .name(user.getUsername())
                .age(user.getAge())
                .weight(user.getWeight())
                .height(user.getHeight())
                .waterTrackerList(waterTrackerRepository.findAll())
                .sleepTrackerList(sleepTrackerRepository.findAll())
                .build();
        return result;
    }

    public Double calculateBMI(BMIRequest bmiRequest, Long userId) throws ChangeSetPersister.NotFoundException {
        User user = repository.findById(userId)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        user.setAge(bmiRequest.getAge());
        user.setHeight(bmiRequest.getHeight());
        user.setWeight(bmiRequest.getWeight());
        repository.save(user);
        double result = bmiRequest.getWeight() / Math.pow(bmiRequest.getHeight() / 100.0, 2);
        return result;
    }
}

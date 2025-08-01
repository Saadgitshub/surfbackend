package com.example.Surf.Services;

import com.example.Surf.DTO.UserDTO;
import com.example.Surf.Models.User;
import com.example.Surf.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
public UserDTO updateUserLocation(UserDTO userDTO) {
    User user = userRepository.findByDeviceId(userDTO.getDeviceId())
            .orElse(new User());
    user.setDeviceId(userDTO.getDeviceId());
    user.setLatitude(userDTO.getLatitude());
    user.setLongitude(userDTO.getLongitude());
    user.setLastUpdated(LocalDateTime.now());
    userRepository.save(user);
    return new UserDTO(user.getId(), user.getDeviceId(), user.getLatitude(), user.getLongitude());
}

    public Optional<UserDTO> getUserLocation(String deviceId) {
        return userRepository.findByDeviceId(deviceId)
                .map(user -> new UserDTO(user.getId(), user.getDeviceId(), user.getLatitude(), user.getLongitude()));
    }

    public List<UserDTO> getActiveUsers() {
        LocalDateTime fiveMinutesAgo = LocalDateTime.now().minusMinutes(5);
        return userRepository.findActiveUsers(fiveMinutesAgo)
                .stream()
                .map(user -> new UserDTO(user.getId(), user.getDeviceId(), user.getLatitude(), user.getLongitude()))
                .collect(Collectors.toList());
    }
}
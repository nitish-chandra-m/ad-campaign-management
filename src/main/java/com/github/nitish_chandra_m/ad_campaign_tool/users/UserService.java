package com.github.nitish_chandra_m.ad_campaign_tool.users;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDto> listUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(u -> new UserDto(u.getFirstName(), u.getLastName(), u.getEmail(), u.getPassword())).toList();
    }

    public UserDto getUserById(String id) {
        var u = userRepository.findById(UUID.fromString(id));
        return u.map(user -> new UserDto(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword())).orElse(null);
    }

    public UserDto createUser(UserDto userDto) {
        String password = passwordEncoder.encode(userDto.password());
        User u = new User(userDto.firstName(), userDto.lastName(), userDto.email().toLowerCase(), password);
        userRepository.save(u);
        return userDto;
    }

    public void deleteUserById(String id) throws Exception {
        UUID uuid = UUID.fromString(id);
        if (!userRepository.existsById(uuid)) {
            throw new Exception("User not found");
        }
        userRepository.deleteById(uuid);
    }

    public UserDto updateUser(String id, UserDto userDto) throws Exception {
        UUID uuid = UUID.fromString(id);
        var user = userRepository.findById(uuid);
        if (user.isEmpty()) {
            throw new Exception("User not found");
        } else {
            User userToUpdate = user.get();
            userToUpdate.setFirstName(userDto.firstName());
            userToUpdate.setLastName(userDto.lastName());
            userToUpdate.setEmail(userDto.email().toLowerCase());
            userToUpdate.setPassword(userDto.password());
            return userDto;
        }
    }
}

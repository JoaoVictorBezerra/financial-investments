package tech.projects.financialinvestments.service;

import org.springframework.stereotype.Service;
import tech.projects.financialinvestments.dto.CreateUserDTO;
import tech.projects.financialinvestments.dto.UpdateUserDTO;
import tech.projects.financialinvestments.entity.User;
import tech.projects.financialinvestments.repository.UserRepository;

import java.time.Instant;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String createUser(CreateUserDTO createUserDTO) {
        User entity = new User(
                null,
                createUserDTO.username(),
                createUserDTO.email(),
                createUserDTO.password(),
                Instant.now(),
                null
        );
        User savedUser = userRepository.save(entity);
        return savedUser.getId();
    }

    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Erro ao encontrar usuário"));
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public void updateUser(String userId, UpdateUserDTO updateUserDTO) {
        User userToBeUpdated = getUserById(userId);
        if(updateUserDTO.email() != null) {
            userToBeUpdated.setUsername(updateUserDTO.username());
        }
        if(updateUserDTO.email() != null) {
            userToBeUpdated.setEmail(updateUserDTO.email());
        }
        if(updateUserDTO.password() != null) {
            userToBeUpdated.setPassword(updateUserDTO.password());
        }
        userRepository.save(userToBeUpdated);
    }

    public void deleteUserById(String userId) {
        User userToBeDeleted = getUserById(userId);
        userRepository.delete(userToBeDeleted);
    }
}

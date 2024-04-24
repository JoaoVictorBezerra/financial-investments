package tech.projects.financialinvestments.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.projects.financialinvestments.dto.AccountResponseDTO;
import tech.projects.financialinvestments.dto.CreateAccountDTO;
import tech.projects.financialinvestments.dto.CreateUserDTO;
import tech.projects.financialinvestments.dto.UpdateUserDTO;
import tech.projects.financialinvestments.entity.User;
import tech.projects.financialinvestments.service.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Void> createUser(@RequestBody CreateUserDTO createUserDTO) {
        String userId = userService.createUser(createUserDTO);
        return ResponseEntity.created(URI.create("/api/users/" + userId)).build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable("userId") String userId) {
        User foundUser = userService.getUserById(userId);
        return ResponseEntity.ok().body(foundUser);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers();
        return ResponseEntity.ok().body(users);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUser(@PathVariable("userId") String userId, @RequestBody UpdateUserDTO updateUserDTO) {
        userService.updateUser(userId, updateUserDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("userId") String userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/account")
    public ResponseEntity<Void> createUserAccount(@PathVariable("userId") String userId, @RequestBody CreateAccountDTO createAccountDTO) {
       userService.createUserAccount(userId, createAccountDTO);
       return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}/account")
    public ResponseEntity<List<AccountResponseDTO>> getAccountByUserId(@PathVariable("userId") String userId) {
        List<AccountResponseDTO> accountList = userService.listAccountsById(userId);
        return ResponseEntity.ok().body(accountList);
    }

}

package tech.projects.financialinvestments.service;

import org.springframework.stereotype.Service;
import tech.projects.financialinvestments.dto.AccountResponseDTO;
import tech.projects.financialinvestments.dto.CreateAccountDTO;
import tech.projects.financialinvestments.dto.CreateUserDTO;
import tech.projects.financialinvestments.dto.UpdateUserDTO;
import tech.projects.financialinvestments.entity.BillingAddress;
import tech.projects.financialinvestments.entity.User;
import tech.projects.financialinvestments.entity.account.Account;
import tech.projects.financialinvestments.repository.AccountRepository;
import tech.projects.financialinvestments.repository.BillingAddressRepository;
import tech.projects.financialinvestments.repository.UserRepository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BillingAddressService billingAddressService;

    public UserService(UserRepository userRepository, BillingAddressService billingAddressService) {
        this.userRepository = userRepository;
        this.billingAddressService = billingAddressService;
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
        boolean existsById = userRepository.existsById(userId);
        if(!existsById) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(userId);
    }

    public Account createUserAccount(String userId, CreateAccountDTO createAccountDTO) {
        User user = getUserById(userId);

        Account account = new Account(UUID.randomUUID().toString(), createAccountDTO.description(), user, null, new ArrayList<>());
        BillingAddress billingAddress = new BillingAddress(account.getId(), account, createAccountDTO.street(), createAccountDTO.number());
        account.setBillingAddress(billingAddress);
//        accountService.createAccount(account);
        billingAddressService.createBillingAddress(billingAddress);
        return account;
    }

    public List<AccountResponseDTO> listAccountsById(String userId) {
        User user = getUserById(userId);
        return user.getAccounts()
                .stream()
                .map(account -> new AccountResponseDTO(account.getId(), account.getDescription()))
                .toList();
    }
}

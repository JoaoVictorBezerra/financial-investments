package tech.projects.financialinvestments.service;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tech.projects.financialinvestments.dto.CreateUserDTO;
import tech.projects.financialinvestments.dto.UpdateUserDTO;
import tech.projects.financialinvestments.entity.User;
import tech.projects.financialinvestments.repository.UserRepository;

import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Captor
    private ArgumentCaptor<User> userArgumentCaptor;

    @Captor
    private ArgumentCaptor<String> stringArgumentCaptor;

    @Nested
    class createUser {
        @Test
        @DisplayName("Should create an user with success")
        void shouldCreateAnUserWithSuccess() {

            // Arrange
            User user = new User(
                    UUID.randomUUID().toString(),
                    "username",
                    "email@test.com",
                    "123",
                    Instant.now(),
                    null
            );
            doReturn(user).when(userRepository).save(userArgumentCaptor.capture());
            CreateUserDTO createUserDTO = new CreateUserDTO(
                    "username",
                    "email@test.com",
                    "123"
            );
            // Act
            String output = userService.createUser(createUserDTO);
            // Assert
            assertNotNull(output);
            assertEquals(createUserDTO.username(), userArgumentCaptor.getValue().getUsername());
            assertEquals(createUserDTO.email(), userArgumentCaptor.getValue().getEmail());
            assertEquals(createUserDTO.password(), userArgumentCaptor.getValue().getPassword());
        }

        @Test
        @DisplayName("Should throw exception when error occurs")
        void shouldThrowAnExceptionWhenErrorOccurs() {
            // Arrange
            User user = new User(
                    UUID.randomUUID().toString(),
                    "username",
                    "email@test.com",
                    "123",
                    Instant.now(),
                    null
            );
            doThrow(new RuntimeException()).when(userRepository).save(any());
            CreateUserDTO createUserDTO = new CreateUserDTO(
                    "username",
                    "email@test.com",
                    "123"
            );
            // Act & Assert
            assertThrows(RuntimeException.class, () -> userService.createUser(createUserDTO));
        }
    }
    
    @Nested
    class getUserById {
        @Test
        @DisplayName("Should get user by id with success")
        void shouldGetUserByIdWithSuccess() {
            User user = new User(
                    UUID.randomUUID().toString(),
                    "username",
                    "email@test.com",
                    "123",
                    Instant.now(),
                    null
            );
            doReturn(Optional.of(user)).when(userRepository).findById(stringArgumentCaptor.capture());

            User foundUser = userService.getUserById(user.getId());

            assertEquals(user.getId(), stringArgumentCaptor.getValue());
        }

        @Test
        @DisplayName("Should throw not found exception")
        void shouldThrowNotFoundException() {
            String userId = UUID.randomUUID().toString();
            doThrow(new RuntimeException("User not found")).when(userRepository).findById(stringArgumentCaptor.capture());

            assertThrows(RuntimeException.class, () -> userService.getUserById(userId));
        }
    }

    @Nested
    class listUsers {
        @Test
        @DisplayName("Should return all users with success")
        void shouldReturnAllUsersWithSuccess() {
            List<User> list = new ArrayList<>();
            list.add(new User(UUID.randomUUID().toString(), "Test 1", "email1@test.com", "pass", Instant.now(), null));
            list.add(new User(UUID.randomUUID().toString(), "Test 2", "email2@test.com", "pass", Instant.now(), null));
            list.add(new User(UUID.randomUUID().toString(), "Test 3", "email3@test.com", "pass", Instant.now(), null));
            doReturn(list).when(userRepository).findAll();

            List<User> userList = userService.getUsers();
            assertNotNull(userList);
            assertEquals(3, list.size());
        }
    }

    @Nested
    class deleteUserById {
        @Test
        @DisplayName("Should delete user with success")
        void shouldDeleteUserWithSuccess() {
            doReturn(true)
                    .when(userRepository)
                    .existsById(stringArgumentCaptor.capture());

            doNothing().when(userRepository).deleteById(stringArgumentCaptor.capture());

            String userId = UUID.randomUUID().toString();
            userService.deleteUserById(userId);

            List<String> idList = stringArgumentCaptor.getAllValues();

            assertEquals(userId, idList.get(0));
            assertEquals(userId, idList.get(1));

            verify(userRepository, times(1)).existsById(idList.get(0));
            verify(userRepository, times(1)).deleteById(idList.get(1));
        }

        @Test
        @DisplayName("Should throw not exists exception")
        void shouldThrowNotExistsException() {
            doReturn(false)
                    .when(userRepository)
                    .existsById(stringArgumentCaptor.capture());

            String userId = UUID.randomUUID().toString();

            assertThrows(RuntimeException.class, () -> userService.deleteUserById(userId));
            verify(userRepository, times(0)).deleteById(stringArgumentCaptor.getValue());
        }
    }

    @Nested
    class updateUser {
        @Test
        @DisplayName("Should update user when password and username is filled with success")
        void shouldUpdateUsernameAndPasswordWithSuccess() {
            User user = new User(
                    UUID.randomUUID().toString(),
                    "username",
                    "email@test.com",
                    "123",
                    Instant.now(),
                    null
            );
            String newUsername = "";
            String newPassword = "";
            UpdateUserDTO updateUserDTO = new UpdateUserDTO(newUsername, null, newPassword);
            User updatedUser = new User(user.getId(), newUsername, user.getEmail(),newPassword, user.getCreatedAt(), Instant.now());

            doReturn(Optional.of(user)).when(userRepository).findById(stringArgumentCaptor.capture());
            doReturn(user).when(userRepository).save(userArgumentCaptor.capture());

            userService.updateUser(user.getId(), updateUserDTO);

            verify(userRepository, times(1)).findById(stringArgumentCaptor.getValue());
            verify(userRepository, times(1)).save(userArgumentCaptor.getValue());
            assertEquals(user.getId(), stringArgumentCaptor.getValue());
            assertEquals(updatedUser.getUsername(), newUsername);
            assertEquals(updatedUser.getPassword(), newPassword);
        }
    }
}
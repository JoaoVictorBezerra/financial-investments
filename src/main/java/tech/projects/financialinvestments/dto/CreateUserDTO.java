package tech.projects.financialinvestments.dto;

public record CreateUserDTO(
        String username,
        String email,
        String password
) {
}

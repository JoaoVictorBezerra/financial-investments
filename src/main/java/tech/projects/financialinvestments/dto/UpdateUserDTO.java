package tech.projects.financialinvestments.dto;

public record UpdateUserDTO(
        String username,
        String email,
        String password
) {
}

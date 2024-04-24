package tech.projects.financialinvestments.dto;

public record CreateAccountDTO(
        String description,
        String street,
        Integer number
) {
}

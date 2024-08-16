package cl.praxis.SecurityJWT.controller.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthCreateUserRequest(@NotBlank String username,
                                    @NotBlank String password,
                                    AuthCreateRoleRequest roleRequest) {
}

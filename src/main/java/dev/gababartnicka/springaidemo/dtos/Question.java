package dev.gababartnicka.springaidemo.dtos;

import jakarta.validation.constraints.NotNull;

public record Question(@NotNull String question) {
}

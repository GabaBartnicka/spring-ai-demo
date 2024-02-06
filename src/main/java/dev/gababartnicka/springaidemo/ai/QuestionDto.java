package dev.gababartnicka.springaidemo.ai;

import jakarta.validation.constraints.NotNull;

public record QuestionDto(@NotNull String question) {
}

package dev.gababartnicka.springaidemo;

import dev.gababartnicka.springaidemo.ai.AiService;
import dev.gababartnicka.springaidemo.ai.QuestionDto;
import dev.gababartnicka.springaidemo.ai.ReplyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
@RequiredArgsConstructor
public class ApiResource {
    private final AiService aiService;

    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping("/api/question")
    ReplyDto replay(@RequestBody QuestionDto question) {
        log.info("POST: Received question: {}", question);
        return aiService.ask(question);
    }
}

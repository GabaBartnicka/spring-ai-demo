package dev.gababartnicka.springaidemo.controllers;

import dev.gababartnicka.springaidemo.dtos.Answer;
import dev.gababartnicka.springaidemo.dtos.Question;
import lombok.extern.log4j.Log4j2;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@Log4j2
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping
    @ResponseStatus(code = HttpStatus.OK)
    public String answer(@RequestParam(value = "question", defaultValue = "Tell me a joke") String message) {
        return chatClient.prompt()
                .user(message)
                .call()
                .content();
    }

    @ResponseStatus(code = HttpStatus.OK)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    Answer replay(@RequestBody Question question) {
        log.info("POST: Received question: {}", question);
        var response = chatClient.prompt()
                .user(question.question())
                .call()
                .content();
        return new Answer(response);
    }
}

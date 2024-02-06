package dev.gababartnicka.springaidemo.ai;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@RequiredArgsConstructor
public class AiService {

    @Value("${dev.gababartnicka.ask_ai.openai.api-key}")
    private String apiKey;

    private OpenAiChatClient chatClient;


    @PostConstruct
    public void init() {
        log.info("AiService initialization started");
        var openAiApi = new OpenAiApi(apiKey);
        chatClient = new OpenAiChatClient(openAiApi)
                .withDefaultOptions(OpenAiChatOptions.builder()
                        .withModel("gpt-4")
//                        .withTemperature(0.4)
//                        .withMaxTokens(200)
                        .build());

        log.info("AiService initialized successfully");
    }

    public ReplyDto ask(QuestionDto questionDto) {
        final var response = chatClient.call(
                new Prompt(questionDto.question()));
        log.info(response.getResult().toString());
        return new ReplyDto(response.getResult().getOutput().getContent());
    }
}

package com.example.JournAI.Controller;

import com.example.JournAI.Exception.ThymeleafException;
import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GPT {

    @Value("${api.key}")
    private String apiKey;

    // This  is generating a response from the chatgpt api. Prompt is the prompt we are giving it.

    public String usePrompt(String prompt) throws ThymeleafException {
        OpenAiService service = new OpenAiService("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");
        int maxTokens = 500;
        CompletionRequest completionRequest = CompletionRequest.builder()
                .prompt(prompt)
                //.echo(true)
                .model("gpt-3.5-turbo-instruct")
                .maxTokens(maxTokens)
                .temperature(0.0) // Randomness of the answer.
                .build();
        return service.createCompletion(completionRequest).getChoices().get(0).getText();
    }
}



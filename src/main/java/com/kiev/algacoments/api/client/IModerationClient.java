package com.kiev.algacoments.api.client;

import com.kiev.algacoments.api.dto.request.ModerationInput;
import com.kiev.algacoments.api.dto.response.ModerationResponse;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

@HttpExchange("/api/moderate")
public interface IModerationClient {
    @PostExchange
    ModerationResponse moderate(ModerationInput input);
}

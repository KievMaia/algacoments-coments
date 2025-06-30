package com.kiev.algacoments.api.client.impl;

import com.kiev.algacoments.api.client.IModerationClient;
import com.kiev.algacoments.api.client.RestClientFactory;
import com.kiev.algacoments.api.dto.request.ModerationInput;
import com.kiev.algacoments.api.dto.response.ModerationResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ModerationImpl implements IModerationClient {
    private RestClient restClient;

    public ModerationImpl(RestClientFactory restClientFactory) {
        this.restClient = restClientFactory.moderationServiceRestClient();
    }

    @Override
    public ModerationResponse moderate(ModerationInput input) {
        return restClient.put()
                .body(new ModerationInput(input.text(), input.commentId()))
                .retrieve()
                .body(ModerationResponse.class);
    }
}

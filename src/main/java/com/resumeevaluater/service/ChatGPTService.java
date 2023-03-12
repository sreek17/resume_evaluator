package com.resumeevaluater.service;

import org.springframework.stereotype.Component;

import com.resumeevaluater.model.ChatGPTResponse;
import com.resumeevaluater.model.ChatGptRequest;

@Component
public interface ChatGPTService {

	public ChatGPTResponse invokeChatGPTApi(ChatGptRequest chatGptRequest);

}

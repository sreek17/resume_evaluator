package com.resumeevaluater.service;

import com.resumeevaluater.model.ChatGPTResponse;
import com.resumeevaluater.model.ChatGptRequest;

public interface ChatGPTService {

	public ChatGPTResponse invokeChatGPTApi(ChatGptRequest chatGptRequest);

}

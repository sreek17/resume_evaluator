package com.resumeevaluater.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.resumeevaluater.model.ChatGPTResponse;
import com.resumeevaluater.model.ChatGptRequest;
import com.resumeevaluater.model.Messages;

public class ChatGPTServiceImpl implements ChatGPTService {

	@Autowired
	RestTemplate restTemplate;

	@Override
	public ChatGPTResponse invokeChatGPTApi(ChatGptRequest chatGptRequest) {
		String apiKey = "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";
		String endpoint = "https://api.openai.com/v1/chat/completions";

		Messages messages = new Messages();

		ChatGPTResponse chatGPTResponse = new ChatGPTResponse();

		String url = "https://api.openai.com/v1/chat/completions";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<ChatGptRequest> requestEntity = new HttpEntity<>(chatGptRequest, headers);
		ResponseEntity<ChatGPTResponse> response = restTemplate.postForEntity(url, requestEntity,
				ChatGPTResponse.class);
		ChatGPTResponse responseBody = response.getBody();

		System.out.println(response);
		return null;
	}

}

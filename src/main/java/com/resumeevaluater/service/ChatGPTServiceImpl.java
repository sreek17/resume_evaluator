package com.resumeevaluater.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.resumeevaluater.model.ChatGPTResponse;
import com.resumeevaluater.model.ChatGptRequest;
import com.resumeevaluater.model.Messages;

@Component
public class ChatGPTServiceImpl implements ChatGPTService {

	@Autowired
	RestTemplate restTemplate;

	@Override
	public ChatGPTResponse invokeChatGPTApi(ChatGptRequest chatGptRequest) {
		String apiKey = "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";
		String url = "https://api.openai.com/v1/chat/completions";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setBasicAuth(HttpHeaders.AUTHORIZATION, "Basic " + apiKey);
		HttpEntity<ChatGptRequest> requestEntity = new HttpEntity<>(chatGptRequest, headers);
		ResponseEntity<ChatGPTResponse> response = restTemplate.postForEntity(url, requestEntity,
				ChatGPTResponse.class);
		ChatGPTResponse responseBody = response.getBody();
		return responseBody;
	}

}

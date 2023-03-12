package com.resumeevaluater.service;

import java.io.IOException;
import java.util.*;

import com.mongodb.client.MongoCursor;
import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import com.mongodb.client.result.UpdateResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.resumeevaluater.model.ChatGPTResponse;
import com.resumeevaluater.model.ChatGptRequest;
import com.resumeevaluater.model.Choices;
import com.resumeevaluater.model.Messages;
import com.resumeevaluater.model.MongoBooleanResume;
import com.resumeevaluater.model.ResumeResponse;
import com.resumeevaluater.model.Usage;
import com.resumeevaluater.util.EmailIdExtractor;
import com.resumeevaluater.util.YesNoChecker;
import org.apache.commons.io.IOUtils;

@Component
public class ResumeEvaluatorServiceImpl implements ResumeEvaluatorService {

	@Autowired
	MongoTemplate mongoTemplate;

	@Autowired
	ChatGPTService chatGPTService;

	@Autowired
	private GridFsTemplate template;

	@Autowired
	private GridFsOperations operations;

	@Autowired
	EmailIdExtractor emailIdExtractor;

	@Autowired
	YesNoChecker yesNoChecker;

	@Override
	public List<ResumeResponse> getEvaluateResumeData(String jobDescription) throws IOException {

		List<ResumeResponse> resumeResponses = new ArrayList<>();

		List<GridFSFile> filesList = downloadResumesFromMongo();
		for (GridFSFile gridFSFile : filesList) {
			ChatGptRequest chatGptRequest = getChatGPTRequest(gridFSFile);
			ChatGPTResponse chatGPTResponse = chatGPTService.invokeChatGPTApi(chatGptRequest);
			if (yesNoChecker.checkForYesNo(chatGPTResponse.getChoices().get(0).getMessage().getContent())
					.equalsIgnoreCase("Yes")) {
				updateBooleanFlag(gridFSFile);
				resumeResponses.add(getResumeResponse(convertBytesArrayToString(
						IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream()))));
			}
		}

		return resumeResponses;

	}

	private ResumeResponse getResumeResponse(String convertBytesArrayToString) {
		ResumeResponse resumeResponse = new ResumeResponse();
		resumeResponse.setEmail(emailIdExtractor.extractEmailIds(convertBytesArrayToString));
		resumeResponse.setName(emailIdExtractor.extractNames(convertBytesArrayToString));
		resumeResponse.setPhNumber(emailIdExtractor.extractPhoneNumbers(convertBytesArrayToString));
		return resumeResponse;
	}

	private ChatGptRequest getChatGPTRequest(GridFSFile gridFSFile) throws IllegalStateException, IOException {
		ChatGptRequest chatGptRequest = new ChatGptRequest();
		Messages messages = new Messages();

		List<Messages> list = new ArrayList<>();
		messages.setRole("user");
		messages.setContent(
				convertBytesArrayToString(IOUtils.toByteArray(operations.getResource(gridFSFile).getInputStream())));
		list.add(messages);
		chatGptRequest.setMessages(list);
		chatGptRequest.setModel("gpt-3.5-turbo");
		return null;
	}

	private List<ResumeResponse> getMockData() {
		List<ResumeResponse> finalResponse = new ArrayList<>();

		ResumeResponse firstResponse = new ResumeResponse("Sreekar", "Pappu", "abc@d.com", "7654567654");
		ResumeResponse secondResponse = new ResumeResponse("Priyanka", "Paik", "asdas@sda.com", "9898767890");
		ResumeResponse thirdResponse = new ResumeResponse("Pathik", "Patel", "adssad@sad.com", "7673636464");
		ResumeResponse fourthResponse = new ResumeResponse("Akshay", "Jain", "aj@s.com", "73664737373");

		finalResponse.add(firstResponse);
		finalResponse.add(secondResponse);
		finalResponse.add(thirdResponse);
		finalResponse.add(fourthResponse);

		return finalResponse;

	}

	@Override
	public String addResumesToMongo(MultipartFile[] multipartFile) throws IOException {

		int count = 0;
		String emailID = "";
		for (MultipartFile multipartFileSingle : multipartFile) {
			emailID = emailIdExtractor.extractEmailIds(convertBytesArrayToString(multipartFileSingle.getBytes()));
			MongoBooleanResume mongoBooleanResume = new MongoBooleanResume(emailID, false);
			Object fileID = template.store(multipartFileSingle.getInputStream(),
					multipartFileSingle.getOriginalFilename(), multipartFileSingle.getContentType(),
					mongoBooleanResume);

			if (fileID != null) {
				count++;
			}

		}

		return "Succesfully added " + count + " resumes.";
	}

	public List<GridFSFile> downloadResumesFromMongo() throws IOException {
		GridFSFindIterable resumesIterable = template.find(new Query(Criteria.where("flag").is(false)));
		List<GridFSFile> resumesList = new ArrayList<>();
		for (GridFSFile file : resumesIterable.into(new ArrayList<>())) {
			resumesList.add(file);
		}
		return resumesList;
	}

	private String convertBytesArrayToString(byte[] byteArray) throws IOException {
		String convertedString = Base64.getEncoder().encodeToString(byteArray);
		return convertedString;
	}

	private void updateBooleanFlag(GridFSFile gridFSFile) {
		Query query = new Query().addCriteria(Criteria.where("_id").is(gridFSFile.getId()));
		Update updateDefinition = new Update().set("flag", true);
		UpdateResult updateResult = mongoTemplate.updateFirst(query, updateDefinition, GridFSFile.class);

	}

}

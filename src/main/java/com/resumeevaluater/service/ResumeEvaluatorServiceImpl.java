package com.resumeevaluater.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import com.mongodb.client.gridfs.GridFSFindIterable;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.resumeevaluater.model.ResumeRepository;
import com.resumeevaluater.model.ResumeResponse;
import com.resumeevaluater.model.Resumes;

@Component
public class ResumeEvaluatorServiceImpl implements ResumeEvaluatorService {

	@Autowired
	ResumeRepository resumeRepository;

	@Autowired
	private GridFsTemplate template;

	@Autowired
	private GridFsOperations operations;

	@Override
	public List<ResumeResponse> getEvaluateResumeData(String jobDescription) {
		return getMockData();

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
			// emailID = getEmailIDFromPDFResume(multipartFileSingle);
			Object fileID = template.store(multipartFileSingle.getInputStream(),
					multipartFileSingle.getOriginalFilename(), multipartFileSingle.getContentType(), true);

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

}

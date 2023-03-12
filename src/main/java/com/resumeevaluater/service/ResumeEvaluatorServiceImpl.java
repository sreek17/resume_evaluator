package com.resumeevaluater.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.resumeevaluater.model.ResumeRepository;
import com.resumeevaluater.model.ResumeResponse;
import com.resumeevaluater.model.Resumes;

@Component
public class ResumeEvaluatorServiceImpl implements ResumeEvaluatorService {

	@Autowired
	ResumeRepository resumeRepository;

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
	public String addResumesToMongo(MultipartFile[] multipartFile) {
		int count = 0;
		String emailID = "";
		for (MultipartFile multipartFileSingle : multipartFile) {
			emailID = getEmailIDFromPDFResume(multipartFileSingle);
			resumeRepository.save(new Resumes(emailID, multipartFileSingle));
			count++;
		}

		return "Succesfully added " + count + " resumes.";
	}

	private String getEmailIDFromPDFResume(MultipartFile multipartFile) {

		return null;
	}
}

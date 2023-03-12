package com.resumeevaluater.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.resumeevaluater.model.ResumeResponse;

@Component
public interface ResumeEvaluatorService {

	public String addResumesToMongo(MultipartFile[] multipartFile) throws IOException;

	public List<ResumeResponse> getEvaluateResumeData(String jobDescription);

}

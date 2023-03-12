package com.resumeevaluater.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

@Component
public interface ResumeRepository extends MongoRepository<Resumes, String> {

}

package com.equipeor.isepu.service;

import com.equipeor.isepu.model.Subject;
import com.equipeor.isepu.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    public Subject afficherSubjectById(Long id) {
        return subjectRepository.findById(id);
    }

    public List<Subject> afficherSubjects(){
        return subjectRepository.findAll();
    }

    public Subject afficherSubjectByName(String name) {
        return subjectRepository.findByName(name);
    }

    public void deleteSubject(String name) {
        subjectRepository.deleteByName(name);
    }

    public void updateSubject(Subject subject) {
        subjectRepository.save(subject);
    }

    public ResponseEntity<Void> addSubject(Subject subject) {
        Subject subjectAdded = subjectRepository.save(subject);
        if (subjectAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(subjectAdded.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}

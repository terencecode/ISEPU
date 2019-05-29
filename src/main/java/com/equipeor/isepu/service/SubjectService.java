package com.equipeor.isepu.service;

import com.equipeor.isepu.configuration.UserPrincipal;
import com.equipeor.isepu.converter.SubjectToSubjectResponseConverter;
import com.equipeor.isepu.exception.UserNotFoundException;
import com.equipeor.isepu.model.Professor;
import com.equipeor.isepu.model.Student;
import com.equipeor.isepu.model.Subject;
import com.equipeor.isepu.payload.response.SubjectResponse;
import com.equipeor.isepu.repository.ProfessorRepository;
import com.equipeor.isepu.repository.StudentRepository;
import com.equipeor.isepu.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;

@Service
public class SubjectService {
    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ProfessorRepository professorRepository;

    public Collection<SubjectResponse> getAllSubjects(){
        return (new SubjectToSubjectResponseConverter()).createFromEntities(subjectRepository.findAll());
    }

    public SubjectResponse getSubjectByName(String name) {
        return (new SubjectToSubjectResponseConverter()).convertFromEntity(subjectRepository.findByName(name));
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

    public Collection<SubjectResponse> getCurrentUserSubjects(UserPrincipal userPrincipal) {
        Optional<Student> student = studentRepository.findById(userPrincipal.getId());
        Optional<Professor> professor = professorRepository.findById(userPrincipal.getId());

        if (student.isPresent()) {
            Student currentStudent = student.get();
            Collection<Subject> subjects = subjectRepository.findAllByCourses_Students_Email(currentStudent.getEmail());
            SubjectToSubjectResponseConverter converter = new SubjectToSubjectResponseConverter();
            return converter.createFromEntities(subjects);
        } else if (professor.isPresent()) {
            Professor currentProfessor = professor.get();
            Collection<Subject> subjects = subjectRepository.findAllByCourses_Professor_Email(currentProfessor.getEmail());
            SubjectToSubjectResponseConverter converter = new SubjectToSubjectResponseConverter();
            return converter.createFromEntities(subjects);
        } else throw new UserNotFoundException();
    }
}

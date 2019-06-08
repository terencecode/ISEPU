package com.equipeor.isepu.service;

import com.equipeor.isepu.configuration.UserPrincipal;
import com.equipeor.isepu.converter.SubjectRequestToSubjectConverter;
import com.equipeor.isepu.converter.SubjectToSubjectResponseConverter;
import com.equipeor.isepu.exception.BadRequestException;
import com.equipeor.isepu.exception.SubjectNotFoundException;
import com.equipeor.isepu.exception.UserNotFoundException;
import com.equipeor.isepu.model.Professor;
import com.equipeor.isepu.model.Student;
import com.equipeor.isepu.model.Subject;
import com.equipeor.isepu.payload.request.SubjectRequest;
import com.equipeor.isepu.payload.response.SubjectResponse;
import com.equipeor.isepu.repository.ProfessorRepository;
import com.equipeor.isepu.repository.StudentRepository;
import com.equipeor.isepu.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return (new SubjectToSubjectResponseConverter()).convertFromEntity(subjectRepository.findByName(name).get());
    }

    public void deleteSubject(String name) {
        subjectRepository.deleteByName(name);
    }

    public void updateSubject(String name, SubjectRequest subjectRequest) {
        Optional<Subject> subject = subjectRepository.findByName(name);
        if (!subject.isPresent())
            throw new SubjectNotFoundException();
        Subject updatedSubject = subject.get();
        updatedSubject.setName(subjectRequest.getName());
        subjectRepository.save(updatedSubject);
    }

    public URI addSubject(SubjectRequest subjectRequest) {
        Subject subject = new SubjectRequestToSubjectConverter().convertFromEntity(subjectRequest);
        Subject subjectAdded = subjectRepository.save(subject);
        if (subjectAdded == null)
            throw new BadRequestException("The subject cannot be added");

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(subjectAdded.getId())
                .toUri();

        return location;
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

package com.equipeor.isepu.controller;

import com.equipeor.isepu.configuration.CurrentUser;
import com.equipeor.isepu.configuration.UserPrincipal;
import com.equipeor.isepu.payload.request.AddHomeworkRequest;
import com.equipeor.isepu.payload.request.UpdateHomeworkRequest;
import com.equipeor.isepu.payload.response.HomeworkResponse;
import com.equipeor.isepu.service.HomeworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/homework")
public class HomeworkController {

    @Autowired
    private HomeworkService homeworkService;

    @Secured("ROLE_USER")
    @GetMapping("/all")
    public ResponseEntity<Collection<HomeworkResponse>> getCurrentUserHomeWorks(@CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.ok(homeworkService.getCurrentUserHomeWorks(userPrincipal));
    }

    @Secured("ROLE_PROFESSOR")
    @PostMapping
    public ResponseEntity<Void> addSession(@RequestBody AddHomeworkRequest homeworkRequest, @CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.created(homeworkService.addHomework(homeworkRequest, userPrincipal)).build();
    }

    @Secured("ROLE_PROFESSOR")
    @PutMapping
    public ResponseEntity<Void>updateHomeworkState(@RequestBody UpdateHomeworkRequest homeworkRequest, @CurrentUser UserPrincipal userPrincipal) {
        return ResponseEntity.created(homeworkService.updateHomeworkState(homeworkRequest, userPrincipal)).build();
    }
}

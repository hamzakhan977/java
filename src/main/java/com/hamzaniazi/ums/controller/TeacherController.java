package com.hamzaniazi.ums.controller;

import com.hamzaniazi.ums.dto.request.TeacherRequest;
import com.hamzaniazi.ums.dto.response.MessageResponse;
import com.hamzaniazi.ums.dto.response.TeacherResponse;
import com.hamzaniazi.ums.service.TeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/app/teacher")
public class TeacherController {

    private final Logger logger = LoggerFactory.getLogger(TeacherController.class);

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/")
    public ResponseEntity<List<TeacherResponse>> getAll() {
        logger.info("Get all semesters");
        return ResponseEntity.ok(teacherService.getAllResponses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherResponse> getOne(@PathVariable("id") Long id) {
        logger.info("Get a teacher by ID :: {}", id);
        TeacherResponse semesterResponse = teacherService.getOneResponse(id);
        if (semesterResponse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(semesterResponse, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<TeacherResponse>> getOne(
            @Param("page") int page,
            @Param("size") int size
    ) {
        logger.info("Get teachers page :: {} size :: {} ", page, size);
        return ResponseEntity.ok(teacherService.getAllWithPagination(PageRequest.of(page, size)));
    }

    @PostMapping("/")
    public ResponseEntity<MessageResponse> create(@RequestBody TeacherRequest teacherRequest) {
        logger.info("Create new teacher :: {} ", teacherRequest);
        teacherService.add(teacherRequest);
        return new ResponseEntity<>(new MessageResponse("teacher created successfully!"), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<MessageResponse> update(@RequestBody TeacherRequest teacherRequest) {
        logger.info("update teacher :: {} ", teacherRequest);
        teacherService.update(teacherRequest);
        return new ResponseEntity<>(new MessageResponse("teacher Update successfully!"), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete(@PathVariable("id") Long id) {
        logger.info("Delete a teacher ID :: {}", id);
        TeacherResponse semesterResponse = teacherService.getOneResponse(id);
        if (semesterResponse == null) {
            return new ResponseEntity<>(new MessageResponse("Not found!"), HttpStatus.NOT_FOUND);
        }
        teacherService.delete(id);
        return new ResponseEntity<>(new MessageResponse("teacher delete successfully!"), HttpStatus.OK);
    }

    @PostMapping("/{id}")
    public ResponseEntity<MessageResponse> assignSubjects(
            @PathVariable("id") Long teacherId,
            @RequestParam("courseIds") List<Long> courseIds
    ) {
        logger.info("Assign new subject for teacher :: ID {} :: subjects {}", teacherId, courseIds.toArray());
        return new ResponseEntity<>(new MessageResponse(teacherService.assignCourses(teacherId, courseIds)), HttpStatus.CREATED);
    }
}

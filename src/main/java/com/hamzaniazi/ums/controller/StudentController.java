package com.hamzaniazi.ums.controller;

import com.hamzaniazi.ums.dto.request.StudentRequest;
import com.hamzaniazi.ums.dto.response.MessageResponse;
import com.hamzaniazi.ums.dto.response.StudentResponse;
import com.hamzaniazi.ums.service.StudentService;
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
@RequestMapping("/v1/app/student")
public class StudentController {

    private final Logger logger = LoggerFactory.getLogger(StudentController.class);

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/")
    public ResponseEntity<List<StudentResponse>> getAll() {
        logger.info("Get all students");
        return ResponseEntity.ok(studentService.getAllResponses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getOne(@PathVariable("id") Long id) {
        logger.info("Get a student by ID :: {}", id);
        StudentResponse studentResponse = studentService.getOneResponse(id);
        if (studentResponse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(studentResponse, HttpStatus.OK);
    }

    @GetMapping("/get-by-course/{id}")
    public ResponseEntity<List<StudentResponse>> getByCourse(@PathVariable("id") Long id) {
        logger.info("Get a student by course ID :: {}", id);
        return ResponseEntity.ok(studentService.getStudentByCourse(id));
    }

    @GetMapping("/page")
    public ResponseEntity<Page<StudentResponse>> getOne(
            @Param("page") int page,
            @Param("size") int size
    ) {
        logger.info("Get student page :: {} size :: {} ", page, size);
        return ResponseEntity.ok(studentService.getAllWithPagination(PageRequest.of(page, size)));
    }

    @PostMapping("/")
    public ResponseEntity<MessageResponse> create(@RequestBody StudentRequest studentRequest) {
        logger.info("Create new student :: {} ", studentRequest);
        studentService.add(studentRequest);
        return new ResponseEntity<>(new MessageResponse("student created successfully!"), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<MessageResponse> update(@RequestBody StudentRequest studentRequest) {
        logger.info("update student :: {} ", studentRequest);
        studentService.update(studentRequest);
        return new ResponseEntity<>(new MessageResponse("student Update successfully!"), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete(@PathVariable("id") Long id) {
        logger.info("Delete a student ID :: {}", id);
        StudentResponse studentResponse = studentService.getOneResponse(id);
        if (studentResponse == null) {
            return new ResponseEntity<>(new MessageResponse("Not found!"), HttpStatus.NOT_FOUND);
        }
        studentService.delete(id);
        return new ResponseEntity<>(new MessageResponse("student delete successfully!"), HttpStatus.OK);
    }

    @PostMapping("/assign-subject/{studentId}/{semesterId}")
    public ResponseEntity<MessageResponse> assignSubjects(
            @PathVariable("studentId") Long studentId,
            @PathVariable("semesterId") Long semesterId,
            @RequestParam("courseIds") List<Long> courseIds
    ) {
        logger.info("Assign new subject for Student :: ID {} :: Semester ID :: {} subjects {}", studentId, semesterId, courseIds.toArray());
        return new ResponseEntity<>(new MessageResponse(studentService.assignCourses(semesterId, studentId, courseIds)), HttpStatus.CREATED);
    }
}

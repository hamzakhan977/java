package com.hamzaniazi.ums.controller;

import com.hamzaniazi.ums.dto.request.CourseRequest;
import com.hamzaniazi.ums.dto.response.CourseResponse;
import com.hamzaniazi.ums.dto.response.MessageResponse;
import com.hamzaniazi.ums.service.CourseService;
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
@RequestMapping("/v1/app/course")
public class CourseController {

    private final Logger logger = LoggerFactory.getLogger(CourseController.class);

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/")
    public ResponseEntity<List<CourseResponse>> getAll() {
        logger.info("Get all courses");
        return ResponseEntity.ok(courseService.getAllResponses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getOne(@PathVariable("id") Long id) {
        logger.info("Get a courses by ID :: {}", id);
        CourseResponse courseResponse = courseService.getOneResponse(id);
        if (courseResponse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(courseResponse, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<CourseResponse>> getOne(
            @Param("page") int page,
            @Param("size") int size
    ) {
        logger.info("Get course page :: {} size :: {} ", page, size);
        return ResponseEntity.ok(courseService.getAllWithPagination(PageRequest.of(page, size)));
    }

    @GetMapping("/get-by-teacher/{id}")
    public ResponseEntity<List<CourseResponse>> getByTeacher(@PathVariable("id") Long id) {
        logger.info("Get all courses by teacher Id {}", id);
        return ResponseEntity.ok(courseService.getAllResponses());
    }

    @PostMapping("/")
    public ResponseEntity<MessageResponse> create(@RequestBody CourseRequest courseRequest) {
        logger.info("Create new course :: {} ", courseRequest);
        courseService.add(courseRequest);
        return new ResponseEntity<>(new MessageResponse("course created successfully!"), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<MessageResponse> update(@RequestBody CourseRequest courseRequest) {
        logger.info("update course :: {} ", courseRequest);
        courseService.update(courseRequest);
        return new ResponseEntity<>(new MessageResponse("course Update successfully!"), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete(@PathVariable("id") Long id) {
        logger.info("Delete a course ID :: {}", id);
        CourseResponse courseResponse = courseService.getOneResponse(id);
        if (courseResponse == null) {
            return new ResponseEntity<>(new MessageResponse("Not found!"), HttpStatus.NOT_FOUND);
        }
        courseService.delete(id);
        return new ResponseEntity<>(new MessageResponse("course delete successfully!"), HttpStatus.OK);
    }
}

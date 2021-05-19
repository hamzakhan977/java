package com.hamzaniazi.ums.controller;

import com.hamzaniazi.ums.dto.request.SemesterRequest;
import com.hamzaniazi.ums.dto.response.MessageResponse;
import com.hamzaniazi.ums.dto.response.SemesterResponse;
import com.hamzaniazi.ums.service.SemesterService;
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
@RequestMapping("/v1/app/semester")
public class SemesterController {

    private final Logger logger = LoggerFactory.getLogger(SemesterController.class);

    private final SemesterService semesterService;

    @Autowired
    public SemesterController(SemesterService semesterService) {
        this.semesterService = semesterService;
    }

    @GetMapping("/")
    public ResponseEntity<List<SemesterResponse>> getAll() {
        logger.info("Get all semesters");
        return ResponseEntity.ok(semesterService.getAllResponses());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SemesterResponse> getOne(@PathVariable("id") Long id) {
        logger.info("Get a customer by ID :: {}", id);
        SemesterResponse semesterResponse = semesterService.getOneResponse(id);
        if (semesterResponse == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(semesterResponse, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<SemesterResponse>> getOne(
            @Param("page") int page,
            @Param("size") int size
    ) {
        logger.info("Get Semester page :: {} size :: {} ", page, size);
        return ResponseEntity.ok(semesterService.getAllWithPagination(PageRequest.of(page, size)));
    }

    @PostMapping("/")
    public ResponseEntity<MessageResponse> create(@RequestBody SemesterRequest semesterRequest) {
        logger.info("Create new semester :: {} ", semesterRequest);
        semesterService.add(semesterRequest);
        return new ResponseEntity<>(new MessageResponse("semester created successfully!"), HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<MessageResponse> update(@RequestBody SemesterRequest semesterRequest) {
        logger.info("update semester :: {} ", semesterRequest);
        semesterService.update(semesterRequest);
        return new ResponseEntity<>(new MessageResponse("semester Update successfully!"), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> delete(@PathVariable("id") Long id) {
        logger.info("Delete a semester ID :: {}", id);
        SemesterResponse semesterResponse = semesterService.getOneResponse(id);
        if (semesterResponse == null) {
            return new ResponseEntity<>(new MessageResponse("Not found!"), HttpStatus.NOT_FOUND);
        }
        semesterService.delete(id);
        return new ResponseEntity<>(new MessageResponse("Semester delete successfully!"), HttpStatus.OK);
    }
}

package com.hamzaniazi.ums.service.impl;

import com.hamzaniazi.ums.dto.request.SemesterRequest;
import com.hamzaniazi.ums.dto.response.SemesterResponse;
import com.hamzaniazi.ums.exception.ResourceNotFoundException;
import com.hamzaniazi.ums.model.Semester;
import com.hamzaniazi.ums.repository.SemesterRepository;
import com.hamzaniazi.ums.service.SemesterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SemesterServiceImpl implements SemesterService {

    private final Logger logger = LoggerFactory.getLogger(SemesterServiceImpl.class);

    private final SemesterRepository semesterRepository;

    @Autowired
    public SemesterServiceImpl(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    @Override
    public Semester getObject(Long id) {
        Optional<Semester> semester = semesterRepository.findById(id);
        if (semester.isPresent()) {
            return semester.get();
        } else {
            logger.error("Cannot find semester with ID :: {}", id);
            throw new ResourceNotFoundException("Cannot find semester with ID" + id);
        }
    }

    @Override
    public List<Semester> getAll() {
        return semesterRepository.findAll();
    }

    @Override
    public SemesterResponse getOneResponse(Long id) {
        return new SemesterResponse(getObject(id));
    }

    @Override
    public List<SemesterResponse> getAllResponses() {
        return getAll().stream().map(SemesterResponse::new).collect(Collectors.toList());
    }

    @Override
    public Page<SemesterResponse> getAllWithPagination(PageRequest request) {
        return semesterRepository.findAll(request).map(SemesterResponse::new);
    }

    @Override
    public void add(SemesterRequest request) {
        semesterRepository.save(request.getSemester());
    }

    @Override
    public void update(SemesterRequest request) {
        Semester semester = getObject(request.getId());
        semester.setName(request.getName());
        semester.setDescription(request.getDescription());
        semester.setYear(request.getYear());
        semester.setDuration(request.getDuration());
        semesterRepository.save(semester);
    }

    @Override
    public void delete(Long id) {
        semesterRepository.delete(getObject(id));
    }
}

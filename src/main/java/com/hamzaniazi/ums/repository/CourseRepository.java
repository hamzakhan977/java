package com.hamzaniazi.ums.repository;

import com.hamzaniazi.ums.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByTeacherId(Long teacherId);
}

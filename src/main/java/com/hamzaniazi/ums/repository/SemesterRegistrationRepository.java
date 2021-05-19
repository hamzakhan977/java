package com.hamzaniazi.ums.repository;

import com.hamzaniazi.ums.model.Course;
import com.hamzaniazi.ums.model.SemesterRegistration;
import com.hamzaniazi.ums.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SemesterRegistrationRepository extends JpaRepository<SemesterRegistration, Long> {
    Optional<SemesterRegistration> findByStudentIdAndSemesterId(Long studentId, Long semesterId);

    List<SemesterRegistration> findAllByCoursesContaining(Course course);
}

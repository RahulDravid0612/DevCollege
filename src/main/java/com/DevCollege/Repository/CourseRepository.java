package com.DevCollege.Repository;

import com.DevCollege.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseRepository extends JpaRepository<Course, String> {
}

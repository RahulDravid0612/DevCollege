package com.DevCollege.Repository;

import com.DevCollege.Entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


public interface CourseRepository extends JpaRepository<Course, String> {

    @Modifying
    @Transactional
    @Query(
            value = "update course set  where course_id = ?1",
            nativeQuery = true
    )
    int updateCourseByCourseId(String courseId);
}

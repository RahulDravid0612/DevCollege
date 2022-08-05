package com.DevCollege.Repository;

import com.DevCollege.Entity.Enrolment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EnrolmentRepository extends JpaRepository<Enrolment,String> {

    @Query(
            value = "SELECT * FROM enrolment where student_id=?1",
            nativeQuery = true
    )
    Enrolment getStudent(String emailId);

    @Query(
            value = "SELECT * FROM enrolment where course_id=?1",
            nativeQuery = true
    )
    Enrolment checkAvailability(String courseId);



}

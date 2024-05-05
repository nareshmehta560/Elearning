package com.example.elearningplatform.Repository;

import com.example.elearningplatform.Model.InstructorApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorApplicationRepository extends JpaRepository<InstructorApplication, Integer> {
    //Here could be needed methods for SQL-Requests
}

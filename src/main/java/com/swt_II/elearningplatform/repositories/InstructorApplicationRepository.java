package com.swt_II.elearningplatform.repositories;

import com.swt_II.elearningplatform.model.InstructorApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstructorApplicationRepository extends JpaRepository<InstructorApplication, Long> {
    //Here could be needed methods for SQL-Requests
}

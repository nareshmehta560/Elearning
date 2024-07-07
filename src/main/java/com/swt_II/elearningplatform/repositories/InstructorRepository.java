package com.swt_II.elearningplatform.repositories;

import com.swt_II.elearningplatform.model.user.Instructor;
import org.apache.catalina.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InstructorRepository extends CrudRepository<Instructor, Long>  {
     List<Instructor> findAllByIsApprovedFalse();
        boolean existsInstructorByUser_Id(Long id);

}

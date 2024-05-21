package com.swt_II.elearningplatform.model.user;

import com.swt_II.elearningplatform.model.course.Course;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;

@Entity
@Getter
@Setter
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private String qualification;
    private String experience;
    @OneToMany(mappedBy="instructor")
    private List<Course>courses;//Listofcourses
    private boolean isApproved;
    private String paypalEmail;


    public Instructor(){
        this.isApproved=false;
    }

    public Instructor(String qualification,String experience){
        this.qualification=qualification;
        this.experience=experience;
        this.isApproved=false;
    }

}
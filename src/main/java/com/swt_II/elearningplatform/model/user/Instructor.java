package com.swt_II.elearningplatform.model.user;

import com.swt_II.elearningplatform.model.course.Course;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.sql.Blob;
import java.util.List;

@Entity
@Getter
@Setter
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;
    @OneToMany(mappedBy="instructor")
    private List<Course>courses;//Listofcourses

    @Column(name = "Title", nullable = false)
    String title;

    @Lob
    @Column(name = "data", columnDefinition = "BLOB")
    private byte[] data;
    @Lob
    @Basic
    @Column(name = "PDF_Blob", nullable = true, columnDefinition = "BLOB")    //BLOB?
    byte[] pdf;
    @Column(name = "File_Name", nullable = true)
    String fileName;
    @Column(name = "qualificationAndExperience", length = 16777216, nullable = false) // = LONGTEXT
    private String qualificationAndExperience;
    @Column(name = "paypalEmail", nullable = true)
    private String paypalEmail;

    @Column(name = "isApproved", nullable = false)
    private boolean isApproved = false;

    public Instructor(){
        this.isApproved=false;
    }


    /**
     public Instructor(String qualificationAndExperience){
        this.qualificationAndExperience = qualificationAndExperience;
        this.isApproved=false;
     }
    */

    public Instructor(String title, byte[] pdf, String fileName, String qualificationAndExperience, String paypalEmail, User currentUser) {
        this.title = title;
        this.pdf = pdf;
        this.fileName = fileName;
        this.qualificationAndExperience = qualificationAndExperience;
        this.paypalEmail = paypalEmail;
        this.user = currentUser;
    }

    public static Instructor testInstructor(User user) {
        return new Instructor("title", null, null, "MSc, 5 years experience", "test@gmail.com", user);
    }

}
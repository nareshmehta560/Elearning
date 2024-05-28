package com.swt_II.elearningplatform.model;

import com.swt_II.elearningplatform.model.user.User;

import jakarta.persistence.*;

import java.sql.Blob;

@Entity
@lombok.Getter
@lombok.Setter
@Table(name = "`Application`")
public class InstructorApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`Application_ID`", unique = true, nullable = false)
    long id;

    @Column(name = "`Title`", nullable = false)
    String title;

    @Column(name = "`applicationPaypal`", nullable = false)
    String applicationPaypal;

    @Column(name = "`Text`", length = 16777216, nullable = false) // = LONGTEXT
    String text;

    @Lob
    @Basic
    @Column(name = "`PDF_Blob`", nullable = false)    //BLOB?
    Blob pdf;

    @Column(name = "`File_Name`", nullable = false)
    String fileName;

    @ManyToOne
    @JoinColumn(name = "FK_User", nullable = false)
    User applicant;

    public InstructorApplication(){}
    public InstructorApplication(String title, String applicationPaypal,String text, Blob pdf, String fileName, User applicant) {
        this.title = title;
        this.applicationPaypal = applicationPaypal;
        this.text = text;
        this.pdf = pdf;
        this.fileName = fileName;
        this.applicant = applicant;
    }

}

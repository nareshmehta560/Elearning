package com.example.elearningplatform.Model;

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

    @Column(name = "`Text`", length = 16777216, nullable = false) // = LONGTEXT
    String text;

    @Lob
    @Basic
    @Column(name = "`PDF_Blob`", nullable = false)    //BLOB?
    Blob pdf;

    @ManyToOne
    @JoinColumn(name = "FK_User", nullable = false)
    User applicant;

    public InstructorApplication(){}
    public InstructorApplication(String title, String text, Blob pdf, User applicant) {
        this.title = title;
        this.text = text;
        this.pdf = pdf;
        this.applicant = applicant;
    }

}

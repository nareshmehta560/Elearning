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
    @Column(name = "`Application_ID`")
    long id;

    @Column(name = "`Title`")
    String title;

    @Column(name = "`Text`", length = 16777216) // = LONGTEXT
    String text;

    @Lob
    @Basic
    @Column(name = "`PDF_Blob`")    //BLOB?
    Blob pdf;

}

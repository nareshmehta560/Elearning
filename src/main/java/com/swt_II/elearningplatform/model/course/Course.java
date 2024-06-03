package com.swt_II.elearningplatform.model.course;

import com.swt_II.elearningplatform.model.user.Instructor;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Course{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id",nullable=false)
    private Long id;
    
    private String name;

    private String author;

    @Lob
    @Column(columnDefinition = "CLOB")
    private String description;

    private String category;

    private Double price;

    @Lob
    private byte[] field;

    private String fileName;

    @ManyToOne
    @JoinColumn(name="instructor_id")
    private Instructor instructor;

   
}

package com.swt_II.elearningplatform.model.course;


import jakarta.persistence.*;

@Entity
public class file {

    @Id
    private Long Id;

    @Lob
    private byte[] field;

    private String Name;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public byte[] getField() {
        return field;
    }

    public void setField(byte[] field) {
        this.field = field;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}

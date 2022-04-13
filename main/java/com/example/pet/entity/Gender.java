package com.example.pet.entity;

import javax.persistence.*;


@Entity
@Table(name = "gender")
public class Gender {
    @Id
    @Column(name = "gender_id")
    @SequenceGenerator(name = "genderIdSeq", sequenceName = "gender_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genderIdSeq")
    private Integer genderId;

    @Column(name = "name")
    private String name;



    public Gender(){
    }


    public Gender(String name) {
        this.name = name;
    }

    public Integer getGenderId() {
        return genderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}

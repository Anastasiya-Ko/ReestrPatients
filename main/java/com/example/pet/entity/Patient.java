package com.example.pet.entity;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@Table(name = "patient")
public class Patient {
        @Id
        @Column(name = "patient_id")
        @SequenceGenerator(name = "patientIdSeq", sequenceName = "patient_id_seq", allocationSize = 1)
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patientIdSeq")
        private Integer patientId;

        @Column(name = "sur_name")
        private String surName;

        @Column(name = "name")
        private String name;

        @Column(name = "patronymic")
        private String patronymic;

        @Column(name = "birthday")
        private LocalDate birthday;

        @Column(name = "snils")
        private String snils;

        @OneToOne
        @JoinColumn(name = "gender_id")
        private Gender gender;

    public Patient() {
    }

    public Patient(String surName, String name, String patronymic, LocalDate birthday, String snils, Gender gender) {

            this.surName = surName;
            this.name = name;
            this.patronymic = patronymic;
            this.birthday = birthday;
            this.snils = snils;
            this.gender = gender;
        }

        public Integer getPatientId() {
            return patientId;
        }

        public String getSurName() {
            return surName;
        }

        public void setSurName(String surname) {
            this.surName = surname;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPatronymic() {
            return patronymic;
        }

        public void setPatronymic(String patronymic) {
            this.patronymic = patronymic;
        }

        public LocalDate getBirthday() {
            return birthday;
        }

        public void setBirthday(LocalDate birthday) {
            this.birthday = birthday;
        }

        public Gender getGender() {
            return gender;
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

        public String getSnils() {
            return snils;
        }

        public void setSnils(String snils) {

            this.snils = snils;
        }

           }


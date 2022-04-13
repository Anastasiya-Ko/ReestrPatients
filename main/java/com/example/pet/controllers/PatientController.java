package com.example.pet.controllers;


import com.example.pet.entity.Patient;
import com.example.pet.service.PatientService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/patient-entity")
@Hidden
@Tag(name = "Секретный контролер Пациенты", description = "Позволяет работать с Пациентами напрямую через entity")
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping
    @Operation(summary = "Постраничный вывод справочника Пациентов")
    public ResponseEntity<List<Patient>> readAll(@PageableDefault Pageable pageable) {
        Page<Patient> pages = patientService.readAll(pageable);
        return ResponseEntity.ok().body(pages.getContent());
    }

    @GetMapping("{id}")
    @Operation(summary = "Позволяет получить Пациента по ID")
    public Patient readOne(@Valid @PathVariable("id") Integer id) {
        Patient readOnePatient = patientService.readOne(id);
        return readOnePatient;
    }

    @PostMapping
    @Operation(summary = "Регистрация нового Пациента", description = "Позволяет зарегистрировать Пациента")
    public ResponseEntity<Patient> create(@Valid @RequestBody Patient patient) {
        patientService.create(patient);
        return ResponseEntity.ok().body(patient);
    }

    @PutMapping("{id}")
    @Operation(summary = "Позволяет обновить данные о Пациенте по ID")
    public ResponseEntity<Patient> update(@PathVariable("id") Integer id, @RequestBody Patient patient) {
        patientService.update(patient, id);
        return ResponseEntity.ok().body(patient);
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Позволяет удалить Пациента по ID")
    public ResponseEntity<Patient> delete(@PathVariable(name = "id") Integer id) {
        final boolean deleted = patientService.deleteById(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.NOT_MODIFIED)
                : new ResponseEntity<>(HttpStatus.OK);
    }
}







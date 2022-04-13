package com.example.pet.controllers;

import com.example.pet.dto.PatientDto;
import com.example.pet.dto.TestDto;
import com.example.pet.entity.Patient;
import com.example.pet.entity.Test;
import com.example.pet.repository.TestRepository;
import com.example.pet.service.TestService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
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
@RequestMapping("/test")
@Tag(name = "Тест", description = "Взаимодействие с Тестами")
public class TestDTOController {
    @Autowired
    TestService testService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    @Operation(summary = "Постраничный вывод справочника Тестов")
    public ResponseEntity<List<Test>> readAll(@PageableDefault Pageable pageable) {
        Page<Test> pages = testService.readAll(pageable);
        return ResponseEntity.ok().body(pages.getContent());
    }

    @GetMapping("{id}")
    @Operation(summary = "Позволяет получить Тест по ID")
    public ResponseEntity<TestDto> readOneDto(@PathVariable(name = "id") Integer id) {

        Test test = testService.readOne(id);
        // convert entity to DTO
        TestDto testResponse = modelMapper.map(test, TestDto.class);
        return ResponseEntity.ok().body(testResponse);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Позволяет обновить данные о Тесте по ID")
    public ResponseEntity<TestDto> updateDto(@PathVariable("id") Integer id, @Valid @RequestBody TestDto testDto) {
        // convert DTO to Entity
        Test testRequest = modelMapper.map(testDto, Test.class);
        Test test = testService.update(testRequest, id);
        // entity to DTO
        TestDto testResponse = modelMapper.map(test, TestDto.class);
        return ResponseEntity.ok().body(testResponse);
    }

    @PostMapping
    @Operation(summary = "Регистрация нового Тесте", description = "Позволяет зарегистрировать Тест")
    public ResponseEntity<TestDto> createDto(@Valid @RequestBody TestDto testDto) {
        // convert TestDto to Test entity
        Test testRequest = modelMapper.map(testDto, Test.class);
        Test test = testService.create(testRequest);
        // convert Test entity to TestDto class
        TestDto testResponse = modelMapper.map(test, TestDto.class);
        return new ResponseEntity<TestDto>(testResponse, HttpStatus.CREATED);
    }
    @DeleteMapping("{id}")
    @Operation(summary = "Позволяет удалить Тест по ID")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Integer id) {
        final boolean deleted = testService.deleteById(id);
        return deleted
                ? new ResponseEntity<>(HttpStatus.NOT_MODIFIED)
                : new ResponseEntity<>(HttpStatus.OK);
    }
}



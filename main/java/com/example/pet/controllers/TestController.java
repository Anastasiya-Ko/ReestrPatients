package com.example.pet.controllers;

import com.example.pet.entity.Patient;
import com.example.pet.entity.Test;
import com.example.pet.repository.TestRepository;
import com.example.pet.service.TestService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test-entity")
@Hidden
@Tag(name = "Секретный контролер Тест", description = "Позволяет работать с Тестами напрямую через entity")
public class TestController {
    @Autowired
    TestService testService;

    @GetMapping
    @Operation(summary = "Постраничный вывод справочника Тестов")
    public ResponseEntity<List<Test>> readAll(@PageableDefault Pageable pageable) {
        Page<Test> pages = testService.readAll(pageable);
        return ResponseEntity.ok().body(pages.getContent());
    }

    @GetMapping("{id}")
    @Operation(summary = "Позволяет получить Тест по ID")
    public Test readOne(@Validated @PathVariable("id") Integer id) {
        Test testI = testService.readOne(id);
        return testI;
    }

    @PutMapping("{id}")
    @Operation(summary = "Позволяет обновить данные о Тесте по ID")
    public ResponseEntity<Test> update(@PathVariable("id") Integer id, @RequestBody Test test) {
        testService.update(test, id);
        return ResponseEntity.ok().body(test);
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


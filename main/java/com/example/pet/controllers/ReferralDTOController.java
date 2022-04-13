package com.example.pet.controllers;

import com.example.pet.dto.PatientDto;
import com.example.pet.dto.ReferralDto;
import com.example.pet.dto.TestDto;
import com.example.pet.entity.Patient;
import com.example.pet.entity.Referral;
import com.example.pet.entity.Test;
import com.example.pet.service.ReferralService;
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
@RequestMapping("/referral")
@Tag(name = "Направление", description = "Взаимодействие с Направлениями")
public class ReferralDTOController {

    @Autowired
    ReferralService referralService;

    @Autowired
    ModelMapper modelMapper;

    @GetMapping
    @Operation(summary = "Постраничный вывод справочника Направлений")
    public ResponseEntity<List<Referral>>readAll(@PageableDefault Pageable pageable) {
        Page<Referral> pages = referralService.readAll(pageable);
        return ResponseEntity.ok().body(pages.getContent());
    }

    @GetMapping("{id}")
    @Operation(summary = "Позволяет получить Напрвление по ID")
    public ResponseEntity<ReferralDto> readOneDto(@PathVariable("id") Integer id) {
        Referral referral = referralService.readOne(id);
        ReferralDto referralResponse = modelMapper.map(referral, ReferralDto.class);
        return ResponseEntity.ok().body(referralResponse);
    }

    @PostMapping
    @Operation(summary = "Регистрация нового Направления", description = "Позволяет зарегистрировать Направление")
    public ResponseEntity<ReferralDto> create(@Valid @RequestBody ReferralDto referralDto) {
        // convert ReferralDto to Referral entity
        Referral referralRequest = modelMapper.map(referralDto, Referral.class);
        Referral referral = referralService.create(referralRequest);
        // convert Referral entity to ReferralDto class
        ReferralDto referralResponse = modelMapper.map(referral, ReferralDto.class);
        return new ResponseEntity<ReferralDto>(referralResponse, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Позволяет обновить данные о Направлении по ID")
    public ResponseEntity<ReferralDto> updateDto(@PathVariable("id") Integer id, @Valid @RequestBody ReferralDto referralDto) {
        // convert DTO to Entity
        Referral referralRequest = modelMapper.map(referralDto, Referral.class);
        Referral referral = referralService.update(referralRequest, id);
        // entity to DTO
        ReferralDto referralResponse = modelMapper.map(referral, ReferralDto.class);
        return ResponseEntity.ok().body(referralResponse);
    }

}

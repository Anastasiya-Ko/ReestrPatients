package com.example.pet.dto;

import com.example.pet.entity.Referral;
import com.example.pet.entity.Service;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@Schema(description = "Сущность Тест")
public class TestDto {

    @Schema(description = "Идентификатор", accessMode = Schema.AccessMode.READ_ONLY)
    private  Integer testId;

    @NotNull(message = "У Теста должен быть результат")
    @Pattern(regexp = "[12]", message = "Результат Теста должен быть: 1-обнаружено, 2-не обнаружено")
    @Schema(description = "Результат Теста должен быть: 1-обнаружено, 2-не обнаружено")
    private String result;

    @Schema(description = "Услуга - идентификатор из справочника")
    private Service service;

    @Schema(description = "Идентификационный номер направления")
    private Referral referralId;

    public TestDto() { }
}

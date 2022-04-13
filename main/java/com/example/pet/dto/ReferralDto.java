package com.example.pet.dto;

import com.example.pet.entity.MedicalInstitution;
import com.example.pet.entity.Patient;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность Направление")
public class ReferralDto {

    @Schema(description = "Идентификатор", accessMode = Schema.AccessMode.READ_ONLY)
    private Integer referralId;

    @NotNull(message = "У Направления должна быть дата создания")
    @PastOrPresent(message = "Поле должно содержать прошедшую дату или сегодняшнее число")
    @Schema(description = "Дата создания направления")
    private LocalDateTime dateReferral;

    @NotNull(message = "У Направления должна быть дата результата")
    @Future(message = "Поле должно содержать дату в будущем")
    @Schema(description = "Дата результата исследования по направлению")
    private LocalDateTime dateResult;

    @NotNull(message = "У Направления должен быть статус")
    @Pattern(regexp = "[12]", message = "Статус должен быть: 1 - направление создано, 2 – направление выполнено")
    @Schema(description = "Статус должен быть: 1 - направление создано, 2 – направление выполнено")
    private String status;

    @Schema(description = "Идентификационный номер Пациента (из справочника)")
    private Patient patientId;

    @Schema(description = "Идентификационный номер Медицинского учреждения, создавшего направление")
    private MedicalInstitution createdMuId;

    @Schema(description = "Идентификационный номер Медицинского учреждения, выполнившего направление")
    private MedicalInstitution executedMuId;

}

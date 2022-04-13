package PatientServiceTests;

import com.example.pet.entity.Patient;
import com.example.pet.repository.PatientRepository;
import com.example.pet.service.PatientService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreatePatientServiceTest {
    @Mock
    private PatientRepository patientRepository;
    @InjectMocks
    private PatientService patientService;
    @Test
    public void whenSavePatient_shouldReturnPatient() {
        Patient patient = new Patient();
        patient.setName("Test Name");
        when(patientRepository.save(ArgumentMatchers.any(Patient.class))).thenReturn(patient);
        Patient created = patientService.create(patient);
//        assertThat(created.getName()).isSameAs(patient.getName());
        verify(patientRepository).save(patient);
    }
}

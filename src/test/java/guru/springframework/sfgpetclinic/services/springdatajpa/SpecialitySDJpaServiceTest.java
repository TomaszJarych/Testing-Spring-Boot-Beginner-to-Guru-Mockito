package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    private SpecialtyRepository specialtyRepository;

    @InjectMocks
    private SpecialitySDJpaService service;

    @Test
    void deleteById() {
        service.deleteById(1L);
        service.deleteById(1L);

        verify(specialtyRepository, times(2)).deleteById(1L);
    }

    @Test
    void deleteByIdAtLeast() {
        service.deleteById(1L);
        service.deleteById(1L);

        verify(specialtyRepository, atLeastOnce()).deleteById(1L);
    }

    @Test
    void deleteByIdAtMost() {
        service.deleteById(1L);
        service.deleteById(1L);

        verify(specialtyRepository, atMost(5)).deleteById(1L);
    }

    @Test
    void deleteByIdNever() {
        service.deleteById(1L);
        service.deleteById(1L);

        verify(specialtyRepository, atLeastOnce()).deleteById(1L);
        verify(specialtyRepository, never()).deleteById(5L);
    }

    @Test
    void deleteObject() {
        Speciality speciality = new Speciality();
        service.delete(speciality);

        verify(specialtyRepository).delete(speciality);
    }

    @Test
    void findById() {
        Speciality specialityStub = new Speciality();

        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(specialityStub));

        Speciality actualSpecialty = service.findById(1L);

        assertNotNull(actualSpecialty);
        assertEquals(specialityStub, actualSpecialty);
        verify(specialtyRepository).findById(anyLong());

        // AssertJ
        assertThat(actualSpecialty).isNotNull().isEqualTo(specialityStub);
    }

    @Test
    void testDeleteByObject() {
        Speciality specialityStub = new Speciality();

        service.delete(specialityStub);

        verify(specialtyRepository).delete(any(Speciality.class));
    }

    // BDD Mockito


    @Test
    void findByIdBDDMockitoTest() {
        //given

        Speciality speciality = new Speciality();
        // BDDMockito static method
        BDDMockito.given(specialtyRepository.findById(1l)).willReturn(Optional.of(speciality));

        //when
        Speciality actualSpecialty = service.findById(1L);

        //then
        assertNotNull(actualSpecialty);
        BDDMockito.then(specialtyRepository).should(times(1)).findById(anyLong());

        BDDMockito.then(specialtyRepository).shouldHaveNoMoreInteractions();

    }
}

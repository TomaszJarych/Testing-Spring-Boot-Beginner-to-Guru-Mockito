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

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTestBDDStyle {

    @Mock
    private SpecialtyRepository specialtyRepository;

    @InjectMocks
    private SpecialitySDJpaService service;

    @Test
    void deleteById() {
        //given - none

        //when
        service.deleteById(1L);
        service.deleteById(1L);

        //then
        BDDMockito.then(specialtyRepository).should(times(2)).deleteById(1L);
    }

    @Test
    void deleteByIdAtLeast() {
        //given - none

        //when
        service.deleteById(1L);
        service.deleteById(1L);

        //then
        BDDMockito.then(specialtyRepository).should(atLeastOnce()).deleteById(1L);
    }

    @Test
    void deleteByIdAtMost() {
        //given - none, we can omit this section at all, given is optional

        //when
        service.deleteById(1L);
        service.deleteById(1L);

        //then
        BDDMockito.then(specialtyRepository).should(atMost(4)).deleteById(1L);
    }

    @Test
    void deleteByIdNever() {

        //when
        service.deleteById(1L);
        service.deleteById(1L);

        //then
        BDDMockito.then(specialtyRepository).should(atLeastOnce()).deleteById(1L);
        BDDMockito.then(specialtyRepository).should(never()).deleteById(2L);
    }


    @Test
    void testDeleteByObject() {
        //given
        Speciality specialityStub = new Speciality();

        //when
        service.delete(specialityStub);

        //then
        BDDMockito.then(specialtyRepository).should().delete(any(Speciality.class));
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

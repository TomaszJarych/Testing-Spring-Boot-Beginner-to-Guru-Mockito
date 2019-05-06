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
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.timeout;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTimeoutTestBDDStyle {

    @Mock
    private SpecialtyRepository specialtyRepository;

    @InjectMocks
    private SpecialitySDJpaService service;


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
        then(specialtyRepository).should(timeout(100)).findById(anyLong());

        then(specialtyRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void deleteById() {
        //given - none

        //when
        service.deleteById(1L);
        service.deleteById(1L);

        //then
        //chaining verification mode
        BDDMockito.then(specialtyRepository).should(timeout(100).times(2)).deleteById(1L);
    }
}

package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MockitoExceptionThrowing {

    @Mock
    private SpecialtyRepository specialtyRepository;

    private SpecialitySDJpaService service;

    @BeforeEach
    void setUp() {
        service = new SpecialitySDJpaService(specialtyRepository);
    }

    @Test
    void doThrowTestExample() {
        doThrow(new RuntimeException("BOOOM")).when(specialtyRepository).delete(any(Speciality.class));

        assertThrows(RuntimeException.class, () -> service.delete(new Speciality()));

        verify(specialtyRepository, times(1)).delete(any(Speciality.class));
    }

    @Test
    void BDDExceptionThrowingExample() {

        //given
        given(specialtyRepository.findById(1L)).willThrow(new RuntimeException("BOOOM"));

        //when

        assertThrows(RuntimeException.class, () -> service.findById(1L));
        then(specialtyRepository).should().findById(1L);
    }

    @Test
    void BDDExceptionThrowingExample2() {

        //when method returns void return type
        //given
        willThrow(new RuntimeException("BOOO00M")).given(specialtyRepository).delete(any(Speciality.class));

        //when

        assertThrows(RuntimeException.class, () -> service.delete(new Speciality()));
        then(specialtyRepository).should().delete(any());


    }
}

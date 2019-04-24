package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class LambdaArgumentMatchers {


    @Mock()
    private SpecialtyRepository specialtyRepository;

    private SpecialitySDJpaService service;

    @BeforeEach
    void setUp() {
        service = new SpecialitySDJpaService(specialtyRepository);
    }

    @Test
    void testSaveLambda() {
        //given
        final String MATCH_ME = "MATCH_ME";
        Speciality speciality = new Speciality();
        speciality.setDescription(MATCH_ME);

        Speciality savedSpecialty = new Speciality();
        savedSpecialty.setId(1L);

        //need mock to only return on match MATCH_ME string
        given(specialtyRepository.save(argThat(argument -> argument.getDescription().equals(MATCH_ME))))
                .willReturn(savedSpecialty);

        //when
        Speciality returnedSpecialty = service.save(speciality);

        //then
        Assertions.assertThat(returnedSpecialty.getId()).isEqualTo(1L);
    }

    @Test
    void testSaveLambdaNotMatch() {
        //given
        final String MATCH_ME = "MATCH_ME";
        Speciality speciality = new Speciality();
        speciality.setDescription("Not match");

        Speciality savedSpecialty = new Speciality();
        savedSpecialty.setId(1L);

        //need mock to only return on match MATCH_ME string
        given(specialtyRepository.save(argThat(argument -> argument.getDescription().equals(MATCH_ME))))
                .willReturn(savedSpecialty);

        //when
        Speciality returnedSpecialty = service.save(speciality);

        //then
        assertNull(returnedSpecialty);
    }
}

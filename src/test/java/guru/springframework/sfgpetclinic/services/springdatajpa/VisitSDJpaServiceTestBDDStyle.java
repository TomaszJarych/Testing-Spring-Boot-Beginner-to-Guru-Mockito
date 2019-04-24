package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.*;


@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTestBDDStyle {

    @Mock
    private VisitRepository visitRepository;

    @InjectMocks
    private VisitSDJpaService service;

    @Test
    @DisplayName("Test Find All")
    void testFindAll() {
        //given
        Set<Visit> visitList = new HashSet<>();
        given(visitRepository.findAll()).willReturn(visitList);

        //when
        Set<Visit> actual = service.findAll();

        //then
        assertNotNull(actual);
        assertEquals(0, actual.size());
        then(visitRepository).should(times(1)).findAll();
    }

    @Test
    @DisplayName("Test Find By ID")
    void testFindById() {

        //given
        Visit visitStub = new Visit();
        given(visitRepository.findById(anyLong())).willReturn(Optional.of(visitStub));

        //when
        Visit actual = service.findById(1L);

        //then
        then(visitRepository).should(atLeastOnce()).findById(anyLong());
    }

    @Test
    @DisplayName("Test Save Method ")
    void testSave() {
        //given
        Visit visitStub = new Visit();
        given(visitRepository.save(any(Visit.class))).willReturn(visitStub);

        //when
        Visit actual = service.save(new Visit());

        //then
        assertNotNull(actual);
        assertEquals(visitStub, actual);
        then(visitRepository).should(times(1)).save(any(Visit.class));
    }

    @Test
    @DisplayName("Test Delete By Object Method")
    void testDelete() {

        //given
        Visit visitStub = new Visit();

        //when
        service.delete(visitStub);

        //then
        then(visitRepository).should(times(1)).delete(any(Visit.class));

    }

    @Test
    @DisplayName("Test Delete By Id")
    void testDeleteById() {
        //given - none

        //when
        service.deleteById(1L);

        //then
        then(visitRepository).should(atLeastOnce()).deleteById(anyLong());
    }

}

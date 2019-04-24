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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    private VisitRepository visitRepository;

    @InjectMocks
    private VisitSDJpaService service;

    @Test
    @DisplayName("Test Find All")
    void testFindAll() {
        Set<Visit> visitList = new HashSet<>();
        when(service.findAll()).thenReturn(visitList);

        Set<Visit> actual = service.findAll();
        assertNotNull(actual);
        assertEquals(visitList.size(), actual.size());

        verify(visitRepository).findAll();
    }

    @Test
    @DisplayName("Test Find By ID")
    void testFindById() {
        Visit visitStub = new Visit();

        when(visitRepository.findById(anyLong())).thenReturn(Optional.of(visitStub));

        Visit actual = service.findById(1L);

        // AssertJ
        assertThat(actual).isNotNull().isEqualTo(visitStub);
        verify(visitRepository).findById(anyLong());
    }

    @Test
    @DisplayName("Test Save Method ")
    void testSave() {
        Visit visitStub = new Visit();
        when(visitRepository.save(any(Visit.class))).thenReturn(visitStub);

        Visit actual = service.save(new Visit());
        assertNotNull(actual);
        assertEquals(visitStub, actual);
        verify(visitRepository).save(any(Visit.class));
    }

    @Test
    @DisplayName("Test Delete By Object Method")
    void testDelete() {
        Visit visitStub = new Visit();

        service.delete(visitStub);

        verify(visitRepository).delete(any(Visit.class));

    }

    @Test
    @DisplayName("Test Delete By Id")
    void testDeleteById() {
        service.deleteById(1L);

        verify(visitRepository).deleteById(anyLong());
    }

}

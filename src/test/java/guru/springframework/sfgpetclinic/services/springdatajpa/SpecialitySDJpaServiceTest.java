package guru.springframework.sfgpetclinic.services.springdatajpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import guru.springframework.sfgpetclinic.services.SpecialtyService;

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
		verify(specialtyRepository).findById(1L);
		
		// AssertJ
		assertThat(actualSpecialty).isNotNull().isEqualTo(specialityStub);
	}

}

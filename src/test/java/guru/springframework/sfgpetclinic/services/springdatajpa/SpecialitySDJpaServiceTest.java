package guru.springframework.sfgpetclinic.services.springdatajpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

	@Mock
	private SpecialtyRepository specialtyRepository;

	@InjectMocks
	private SpecialitySDJpaService service;

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void deleteById() {
		service.deleteById(1L);
	}

	@Test
	void deleteObject() {
		service.delete(new Speciality());
	}

}

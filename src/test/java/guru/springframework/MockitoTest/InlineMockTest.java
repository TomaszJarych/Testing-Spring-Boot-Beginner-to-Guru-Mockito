package guru.springframework.MockitoTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.util.Map;

import org.junit.jupiter.api.Test;

public class InlineMockTest {
	
	@Test
	void inLineMockTestMethod() {
		Map mockMap = mock(Map.class);
		assertEquals(0, mockMap.size());
		
	}

}

package guru.springframework;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import java.util.Map;

import org.junit.jupiter.api.Test;

public class InlineMockTest {
	
	@Test
	void inLineMockTestmethod() {
		Map mockMap = mock(Map.class);
		assertEquals(0, mockMap.size());
		
	}

}

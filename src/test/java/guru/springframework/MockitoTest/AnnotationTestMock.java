package guru.springframework.MockitoTest;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class AnnotationTestMock {

	@Mock
	Map<String, Object> mapMock;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testName() {
		mapMock.put("keyvalue", "foo");
	}

}

package guru.springframework.MockitoTest;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class JUnitMockitoExtensionTest {

	@Mock
	Map<String, Object> mapMock;

	@Test
	void testName() {
		mapMock.put("keyvalue", "foo");
	}


}

package guru.springframework.MockitoTest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class JUnitMockitoExtensionTest {

    @Mock
    Map<String, Object> mapMock;

    @Test
    void testName() {
        mapMock.put("keyvalue", "foo");
    }

}

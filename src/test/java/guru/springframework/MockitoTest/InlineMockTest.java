package guru.springframework.MockitoTest;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class InlineMockTest {

    @Test
    void inLineMockTestMethod() {
        Map mockMap = mock(Map.class);
        assertEquals(0, mockMap.size());

    }

}

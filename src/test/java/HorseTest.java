import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    //    test{Method}_Should{Do}_When{Condition}
    @Test
    void testConstructor_ShouldThrowIllegalArgumentEx_WhenArgsNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 10));
    }

    @Test
    void testConstructor_ShouldThrowIAExContainsMessage_WhenArgsNameIsNull() {
        String expectedExceptionMessage = "Name cannot be null.";
        var exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 10));
        String actualExceptionMessage = exception.getMessage();
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

}
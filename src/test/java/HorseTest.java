import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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

    @ParameterizedTest
    @ValueSource(strings = {" ", "\n", "\t", "\b", "\r", "\f"})
    void testConstructor_ShouldThrowIAEx_WhenArgsNameIsWhitespaceChar(String argument) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(argument, 7));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\n", "\t", "\b", "\r", "\f"})
    void testConstructor_ShouldThrowIAExMessage_WhenArgsNameWhitespaceChar(String argument) {
        String expectedExceptionMessage = "Name cannot be blank.";
        var exception = assertThrows(IllegalArgumentException.class, () -> new Horse(argument, 7));
        String actualExceptionMessage = exception.getMessage();
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

    @Test
    void testConstructor_ShouldThrowIllegalArgumentEx_WhenArgsSpeedIsNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("blah-blah", -5));
    }




}
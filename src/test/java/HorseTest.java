import lombok.SneakyThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    String name = "Zephyr";
    double speed = 10;
    double distance = 15;

    //    test{Method}_Should{Do}_When{Condition}
    @Test
    void testConstructor_ShouldThrowIllegalArgumentEx_WhenArgsNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, speed));
    }

    @Test
    void testConstructor_ShouldThrowIAExContainsMessage_WhenArgsNameIsNull() {
        String expectedExceptionMessage = "Name cannot be null.";
        var exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, speed));
        String actualExceptionMessage = exception.getMessage();
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\n", "\t", "\r", "\f"})
    void testConstructor_ShouldThrowIAEx_WhenArgsNameIsWhitespaceChar(String argument) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(argument, speed));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "\n", "\t", "\r", "\f"})
    void testConstructor_ShouldThrowIAExMessage_WhenArgsNameWhitespaceChar(String argument) {
        String expectedExceptionMessage = "Name cannot be blank.";
        var exception = assertThrows(IllegalArgumentException.class, () -> new Horse(argument, speed));
        String actualExceptionMessage = exception.getMessage();
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

    @Test
    void testConstructor_ShouldThrowIllegalArgumentEx_WhenArgsSpeedIsNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, -speed));
    }

    @Test
    void testConstructor_ShouldThrowIllegalArgumentExAndMessage_WhenArgsSpeedIsNegativeValue() {
        String expectedExceptionMessage = "Speed cannot be negative.";
        var exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, -speed));
        String actualExceptionMessage = exception.getMessage();
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

    @Test
    void testConstructor_ShouldThrowIllegalArgumentEx_WhenArgsDistanceIsNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, -distance));
    }

    @Test
    void testConstructor_ShouldThrowIllegalArgumentExAndMessage_WhenArgsDistanceIsNegativeValue() {
        String expectedExceptionMessage = "Distance cannot be negative.";
        var exception = assertThrows(IllegalArgumentException.class, () -> new Horse(name, speed, -distance));
        String actualExceptionMessage = exception.getMessage();
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

    @Test
    void testGetName_ShouldReturnName() {
        Horse horse = new Horse(name, speed);
        String actualName = horse.getName();
        assertEquals("Zephyr", actualName);
    }

    @Test
    @SneakyThrows
    void testGetName_ShouldReturnNameByReflection() {
        String expectedName = "Zephyr";

        Horse horse = new Horse(name, speed);
        Field field = Horse.class.getDeclaredField("name");
        field.setAccessible(true);
        String actualName = (String) field.get(horse);

        assertEquals(expectedName, actualName);
    }

    @Test
    void testGetSpeed_ShouldReturnSpeed() {
        Horse horse = new Horse(name, speed);

        assertEquals(speed, horse.getSpeed());
    }

    @Test
    @SneakyThrows
    void testGetSpeed_ShouldReturnSpeedByReflection() {
        double expectedSpeed = 10;

        Horse horse = new Horse(name, speed);
        Field field = Horse.class.getDeclaredField("speed");
        field.setAccessible(true);
        double actualSpeed = (double) field.get(horse);

        assertEquals(expectedSpeed, actualSpeed);
    }

    @Test
    void testGetDistance_ShouldReturnDistance() {
        Horse horse = new Horse(name, speed, distance);

        assertEquals(distance, horse.getDistance());
    }

    @Test
    @SneakyThrows
    void testGetDistance_ShouldReturnDistanceByReflection() {
        double expectedDistance = 15;

        Horse horse = new Horse(name, speed, distance);
        Field field = Horse.class.getDeclaredField("distance");
        field.setAccessible(true);
        double actualDistance = (double) field.get(horse);

        assertEquals(expectedDistance, actualDistance);
    }

    @Test
    @SneakyThrows
    void testGetDistance_ShouldReturnZero() {
        double expectedDistance = 0;

        Horse horse = new Horse(name, speed);
        Field field = Horse.class.getDeclaredField("distance");
        field.setAccessible(true);
        double actualDistance = (double) field.get(horse);

        assertEquals(expectedDistance, actualDistance);
    }

    @Test
    void test() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse(name, speed, distance);
            horse.move();

            mockedStatic.verify(() -> Horse.getRandomDouble(ArgumentMatchers.anyDouble(), ArgumentMatchers.eq(0.9)));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0, 0.2, 0.5, 0.9, 1, 10, 1000})
    void test2(double fakeValue) {
        Horse horse = new Horse(name, speed, distance);
        double expectedDistance = horse.getDistance() + horse.getSpeed() * fakeValue;
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(fakeValue);
            horse.move();
            double actualDistance = horse.getDistance();
            assertEquals(expectedDistance, actualDistance);
        }
    }


}
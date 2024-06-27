import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {
    @Test
    void testConstructor_ShouldThrowIllegalArgumentEx_WhenArgsIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void testConstructor_ShouldThrowIAExContainsMessage_WhenArgsIsNull() {
        String expectedExceptionMessage = "Horses cannot be null.";
        var exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        String actualExceptionMessage = exception.getMessage();
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

    @Test
    void testConstructor_ShouldThrowIAEx_WhenArgsIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(List.of()));
    }

    @Test
    void testConstructor_ShouldThrowIAExMessage_WhenArgsIsEmpty() {
        String expectedExceptionMessage = "Horses cannot be empty.";
        var exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(List.of()));
        String actualExceptionMessage = exception.getMessage();
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

    @Test
    void testGetHorses_ShouldReturnListOfHorses() {
        List<Horse> horses = IntStream.range(0, 30).mapToObj(i -> new Horse("Zephyr " + i, i)).toList();
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    @Test
    void test() {
        List<Horse> horseList = IntStream.range(0, 50).mapToObj(i -> Mockito.mock(Horse.class)).toList();
        Hippodrome hippodrome = new Hippodrome(horseList);
        hippodrome.move();
        for (Horse horse : horseList) {
            Mockito.verify(horse, Mockito.atLeast(1)).move();
        }
    }

    @Test
    void testGetWinner() {
        Horse horse = new Horse("MoonPie", 200, 12);
        Horse horse2 = new Horse("Sunshine", 300, 16);
        Horse horse3 = new Horse("Thunder", 400, 10);
        Hippodrome hippodrome = new Hippodrome(List.of(horse, horse2, horse3));
        assertSame(horse2, hippodrome.getWinner());
    }


}
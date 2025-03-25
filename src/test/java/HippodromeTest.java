import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class HippodromeTest {
    Horse horse = new Horse("Apple",7.0,3.4);

    @Test
    void nullTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () ->
                        new Hippodrome(null)
        );
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void emptyListTest() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () ->
                        new Hippodrome(new ArrayList<>())
        );
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorsesTest() {
        List<Horse> horses = new ArrayList<>();

        for (int i = 0; i <= 30; i++) {
            horses.add(new Horse("Apple" + i, 15.0, 3.0));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        //получаем список лошадей из Hippodrome
        List<Horse> returnedHorses = hippodrome.getHorses();
        //теперь проверяем, что в списках те же объекты и в той же последовательности
        assertEquals(horses, returnedHorses);

        //проверяем что список неизеняемый
        assertThrows(UnsupportedOperationException.class, () ->
                returnedHorses.add(new Horse("new horse", 20.0, 5.0)));
    }

    @Test
    void moveTest() {

        List<Horse> horses = new ArrayList<>();
        for (int i = 1; i < 50; i++) {
            Horse horse = Mockito.mock(Horse.class);
            horses.add(horse);

        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for(Horse horse : horses) {
            verify(horse).move();
        }
    }
    @Test
    void winnerTest() {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Flower", 23.0,7.0));
        horses.add(new Horse("Pineapple", 19.0,6.5));
        horses.add(new Horse("Orange", 21.2,7.5));

        Hippodrome hippodrome = new Hippodrome(horses);
        Horse winner = hippodrome.getWinner();
        assertEquals(horses.get(2),winner);

    }
}
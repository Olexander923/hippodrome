import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

public class HorseTest {
    public Horse horse = new Horse("Apple",15.0,3.0);

    @BeforeAll
    static void initAll() {
        System.out.println("init before all tests");
    }

    @Test
    void nullTest() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                ()->
                     new Horse(null,15.0,3.0)
                );
        assertEquals("name cannot be null", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "   ", "\t","\n"})
    void testWithCsvSource(String name) {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () ->
                    new Horse(name,15.0,3.0)
        );
        assertEquals("Name cannot be blank",exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(doubles = {-1.0})
    void negativeSpeedTest(double speed) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () ->
                        new Horse("Apple",-1.0,3.0)
                );
        assertEquals("speed cant be negative",exception.getMessage());
    }
    @ParameterizedTest
    @ValueSource(doubles = {-5.0})
    void negativeDistanceTest(double distance) {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () ->
                        new Horse("Apple",10.0,-5.0)
                );
        assertEquals("distance cant be negative",exception.getMessage());
    }

    @Test
    void getNameTest() {
        String name = horse.getName();
        assertEquals("Apple", name);
    }

    @Test
    void testSpeed() {
        double speed = horse.getSpeed();
        assertEquals(15.0,speed);
    }

    @Test
    void testDistance() {
        double distance = horse.getDistance();
        assertEquals(3.0,distance);
        Horse horseTest = new Horse("Apple",15.0);
        double testDistance = horseTest.getDistance();
        assertEquals(0,testDistance);
    }

}

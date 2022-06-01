import Collection.Map.MapRealization;
import Container.LinkedList;
import Collection.Map.IMap;
import com.google.common.base.Stopwatch;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ThreadLocalRandom;

class MapRealizationTest {
    private static final int REQUIRED_NUMBER_OF_ELEMENTS = 500;

    @Test
    void timeComplexityTest() {
        testMethod(new MapRealization<>(new LinkedList<>()), 1_000);
        testMethod(new MapRealization<>(new LinkedList<>()), 10_000);
        testMethod(new MapRealization<>(new LinkedList<>()), 100_000);
        testMethod(new MapRealization<>(new LinkedList<>()), 200_000);
        testMethod(new MapRealization<>(new LinkedList<>()), 1_000_000);
    }

    private Stopwatch searchingTime(IMap<Integer, Integer> newMap) {
        Stopwatch stopwatch = Stopwatch.createStarted();

        for (int i = 0; i < REQUIRED_NUMBER_OF_ELEMENTS; i++) {
            int value = ThreadLocalRandom.current().nextInt();
            newMap.contains(value);
        }

        return stopwatch;
    }

    private void testMethod(IMap<Integer, Integer> newMap, int size) {
        Stopwatch timer = Stopwatch.createStarted();

        for (int i = 0; i < size; i++) {
            int val = ThreadLocalRandom.current().nextInt();
            newMap.put(val, val);
        }

        timer.stop();

        System.out.printf("Adding %d elements in map: %s. %d elements search time: %s\n",
                size, timer, REQUIRED_NUMBER_OF_ELEMENTS, searchingTime(newMap));
    }

}
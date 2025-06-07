package week4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class 기차가어둠을헤치고은하수를S215787Test {

    기차가어둠을헤치고은하수를_S2_15787 기차가어둠을헤치고은하수를S215787 = new 기차가어둠을헤치고은하수를_S2_15787();
    int[] arr;

    @BeforeEach
    void setUp() {
        arr = new int[5];
    }

    @Test
    void board() {
        assertEquals(1, 기차가어둠을헤치고은하수를S215787.board(arr, 0, 0));
    }

    @Test
    void getOff() {
        arr[0] = 1;
        assertEquals(1, 기차가어둠을헤치고은하수를S215787.getOff(arr, 0, 1));
        assertEquals(0, 기차가어둠을헤치고은하수를S215787.getOff(arr, 0, 0));
    }

    @Test
    void pushBack() {
        arr[0] = 1 << 18;
        assertEquals(1 << 19, 기차가어둠을헤치고은하수를S215787.pushBack(arr, 0));
        assertEquals(0, 기차가어둠을헤치고은하수를S215787.pushBack(arr, 0));
        assertEquals(0, 기차가어둠을헤치고은하수를S215787.pushBack(arr, 0));
    }

    @Test
    void pushFront() {
        arr[0] = 1 << 19;
        assertEquals((1 << 18), 기차가어둠을헤치고은하수를S215787.pushFront(arr, 0));
        arr[0] = 1;
        assertEquals(0, 기차가어둠을헤치고은하수를S215787.pushFront(arr, 0));
    }
}
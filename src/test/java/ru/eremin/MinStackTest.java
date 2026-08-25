package ru.eremin;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@DisplayName("Тесты для класса MinStack")
public class MinStackTest {
    private MinStack minStack;

    @BeforeEach
    void setup() {
        minStack = new MinStack();
    }


    @Test
    void shouldReturnMinElement() {
        minStack.push(5);
        minStack.push(3);
        minStack.push(4);
        minStack.push(1);
        minStack.push(6);

        int expected = 1;

        int actual = minStack.getMin();

        assertEquals(expected, actual);
    }

    @Test
    void shouldReturnMinElementIfSomeRemoves() {
        minStack.push(5);
        minStack.push(3);
        minStack.push(4);
        minStack.push(1);
        minStack.push(6);
        minStack.pop();
        minStack.pop();

        int expected = 3;

        int actual = minStack.getMin();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Базовый сценарий: push, top, getMin, pop")
    void testBasicOperations() {
        minStack.push(5);
        assertEquals(5, minStack.top(), "Верхний элемент должен быть 5");
        assertEquals(5, minStack.getMin(), "Минимальный элемент должен быть 5");

        minStack.push(2);
        assertEquals(2, minStack.top(), "Верхний элемент должен быть 2");
        assertEquals(2, minStack.getMin(), "Минимальный элемент должен обновиться до 2");

        minStack.pop();
        assertEquals(5, minStack.top(), "После pop верхний элемент снова 5");
        assertEquals(5, minStack.getMin(), "Минимальный элемент должен вернуться к 5");
    }

    @Test
    @DisplayName("Дубликаты минимального значения")
    void testDuplicatesOfMinValue() {
        minStack.push(3);
        minStack.push(3);
        minStack.push(3);

        assertEquals(3, minStack.getMin());

        minStack.pop();

        assertEquals(3, minStack.getMin());
        assertEquals(3, minStack.top());
    }

    @Test
    @DisplayName("Отрицательные числа и ноль")
    void testNegativeNumbersAndZero() {
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);

        assertEquals(-3, minStack.getMin(), "Минимум должен быть -3");
        assertEquals(-3, minStack.top());

        minStack.pop();
        assertEquals(-2, minStack.getMin(), "После удаления -3 минимум должен стать -2");
        assertEquals(0, minStack.top());
    }

    @Test
    @DisplayName("Убывающая последовательность: минимум должен обновляться")
    void testDecreasingSequence() {
        minStack.push(10);
        minStack.push(5);
        minStack.push(1);

        assertEquals(1, minStack.getMin());

        minStack.pop();
        assertEquals(5, minStack.getMin());

        minStack.pop();
        assertEquals(10, minStack.getMin());
    }

    @Test
    @DisplayName("Возрастающая последовательность: минимум не должен меняться")
    void testIncreasingSequence() {
        minStack.push(1);
        minStack.push(5);
        minStack.push(10);
        minStack.push(100);

        assertEquals(1, minStack.getMin(), "Минимум должен оставаться 1");

        minStack.pop();
        assertEquals(1, minStack.getMin(), "Минимум должен оставаться 1");
    }


    @Test
    @DisplayName("Исключение EmptyStackException при операциях с пустым стеком")
    void testEmptyStackExceptions() {
        assertThrows(EmptyStackException.class, () -> minStack.pop(),
                "pop() на пустом стеке должен выбрасывать EmptyStackException");

        assertThrows(EmptyStackException.class, () -> minStack.top(),
                "top() на пустом стеке должен выбрасывать EmptyStackException");

        assertThrows(EmptyStackException.class, () -> minStack.getMin(),
                "getMin() на пустом стеке должен выбрасывать EmptyStackException");
    }

    @Test
    @DisplayName("Опустошение стека")
    void testEmptyingStackCompletely() {
        minStack.push(42);
        minStack.push(43);
        minStack.pop();
        minStack.pop();

        assertThrows(EmptyStackException.class, () -> minStack.getMin());
        assertThrows(EmptyStackException.class, () -> minStack.top());
    }
}

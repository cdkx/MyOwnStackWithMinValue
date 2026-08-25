package ru.eremin;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.EmptyStackException;


public class MinStack {
    private final Deque<Integer> stack;
    private final Deque<Integer> minValues;


    public MinStack() {
        this.stack = new ArrayDeque<>();
        this.minValues = new ArrayDeque<>();
    }

    /**
     * Кладет на вершину стека за О(1)
     */

    public void push(int val) {
        stack.push(val);

        if (minValues.peek() == null || minValues.peek() > val) {
            minValues.push(val);
        } else {
            minValues.push(minValues.peek());
        }
    }

    /**
     * Удаляет верхний элемент за О(1)
     * @throws EmptyStackException если стек пуст
     */
    public void pop() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        stack.pop();
        minValues.pop();
    }

    /**
     * @return верхний элемент без удаления за О(1)
     * @throws EmptyStackException если стек пуст
     */
    public int top() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.peek();
    }


    /**
     * @return минимальный элемент в стеке на текущий момент за О(1)
     * @throws EmptyStackException если стек пуст
     */
    public int getMin() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        return minValues.peek();
    }
}

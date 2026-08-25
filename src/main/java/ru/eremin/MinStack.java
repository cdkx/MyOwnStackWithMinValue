package ru.eremin;

import java.util.EmptyStackException;


public class MinStack {
    private Node topNode;

    public MinStack() {
        topNode = null;
    }

    /**
     * Кладет на вершину стека за О(1)
     */

    public void push(int val) {
        if (topNode == null) {
            topNode = new Node(val, val, null);
        } else {
            int newMin = Math.min(val, topNode.currentMin());
            topNode = new Node(val, newMin, topNode);
        }
    }

    /**
     * Удаляет верхний элемент за О(1)
     *
     * @throws EmptyStackException если стек пуст
     */
    public void pop() {
        if (topNode == null) {
            throw new EmptyStackException();
        }
        topNode = topNode.nextNode();
    }

    /**
     * @return верхний элемент без удаления за О(1)
     * @throws EmptyStackException если стек пуст
     */
    public int top() {
        if (topNode == null) {
            throw new EmptyStackException();
        }
        return topNode.val();
    }


    /**
     * @return минимальный элемент в стеке на текущий момент за О(1)
     * @throws EmptyStackException если стек пуст
     */
    public int getMin() {
        if (topNode == null) {
            throw new EmptyStackException();
        }
        return topNode.currentMin();
    }

    private record Node(
            int val,
            int currentMin,
            Node nextNode) {
    }
}

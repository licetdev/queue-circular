/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.queuecircularfx.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author Admin
 */
public class QueueCircular {
    private Node rear;
    private int size;
    private static final int MAX = 6;

    public QueueCircular() {
        rear = null;
        size = 0;
    }

    public boolean isEmpty() {
        return rear == null;
    }

    public boolean isFull() {
        return size == MAX;
    }

    public void enqueue(int value) {
        if (isFull()) {
            throw new IllegalStateException("Cola llena");
        }

        Node nuevo = new Node(value);

        if (isEmpty()) {
            rear = nuevo;
            rear.next = rear; // circular
        } else {
            nuevo.next = rear.next; // apunta al frente
            rear.next = nuevo;
            rear = nuevo;
        }
        size++;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cola vacía");
        }

        Node front = rear.next;
        int value = front.value;

        if (rear == front) {
            rear = null;
        } else {
            rear.next = front.next;
        }
        size--;
        return value;
    }

    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Cola vacía");
        }
        return rear.next.value;
    }

    public List<Integer> toList() {
        List<Integer> list = new ArrayList<>();

        if (isEmpty()) return list;

        Node actual = rear.next;
        for (int i = 0; i < size; i++) {
            list.add(actual.value);
            actual = actual.next;
        }
        return list;
    }

    public int size() {
        return size;
    }
}

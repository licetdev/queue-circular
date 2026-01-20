/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espoch.queuecircularfx.controller;

import ec.edu.espoch.queuecircularfx.modelo.QueueCircular;
import ec.edu.espoch.queuecircularfx.vista.QueueCanvas;
import javafx.scene.control.Label;

import java.util.NoSuchElementException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Admin
 */
public class QueuecController {

    @FXML
    private TextField txtValue;

    @FXML
    private Label lblEstado;

    @FXML
    private StackPane canvasHolder;

    private QueueCanvas canvas;
    private QueueCircular queue;

    // ========================
    // INITIALIZE
    // ========================
    @FXML
    private void initialize() {
        queue = new QueueCircular(); // sin parámetro
        canvas = new QueueCanvas();

        canvasHolder.getChildren().add(canvas);
        refreshView();
    }

    // ========================
    // ENQUEUE
    // ========================
    @FXML
    private void enqueue() {
        Integer value = readInt();

        if (value == null) {
            lblEstado.setText("Valor inválido");
            return;
        }

        if (queue.isFull()) {
            lblEstado.setText("Cola llena");
            return;
        }

        queue.enqueue(value);
        lblEstado.setText("Encolado: " + value);
        refreshView();
    }

    // ========================
    // DEQUEUE
    // ========================
    @FXML
    private void dequeue() {
        try {
            int removed = queue.dequeue();
            lblEstado.setText("Eliminado: " + removed);
            refreshView();
        } catch (NoSuchElementException e) {
            lblEstado.setText("Cola vacía");
        }
    }

    // ========================
    // PEEK
    // ========================
    @FXML
    private void peek() {
        try {
            int front = queue.peek();
            lblEstado.setText("Frente: " + front);

            canvas.highlightFront();
            canvas.render();
        } catch (NoSuchElementException e) {
            lblEstado.setText("Cola vacía");
        }
    }

    // ========================
    // IS EMPTY
    // ========================
    @FXML
    private void isEmpty() {
        lblEstado.setText(queue.isEmpty()
                ? "La cola está vacía"
                : "La cola NO está vacía");
    }

    // ========================
    // IS FULL
    // ========================
    @FXML
    private void isFull() {
        lblEstado.setText(queue.isFull()
                ? "La cola está llena"
                : "Aún hay espacio");
    }

    // ========================
    // AUX
    // ========================
    private Integer readInt() {
        try {
            return Integer.valueOf(txtValue.getText().trim());
        } catch (Exception e) {
            return null;
        }
    }

    private void refreshView() {
        canvas.setValues(queue.toList());
        canvas.render();
    }
}

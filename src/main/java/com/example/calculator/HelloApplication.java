package com.example.calculator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Quadratic Equation Calculator
 * @author Alan Chen
 */
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        Label title = createTitle();
        TextField aValue = createTextInput("Enter value of A: ");
        TextField bValue = createTextInput("Enter value of B: ");
        TextField cValue = createTextInput("Enter value of C: ");
        TextArea answerField = createTextArea();

        Button submitButton = createButton("Calculate Roots");
        Button clearButton = createButton("Clear");

        EventHandler<ActionEvent> clear = actionEvent -> {
            aValue.clear();
            bValue.clear();
            cValue.clear();
            answerField.clear();
        };

        EventHandler<ActionEvent> calculate = actionEvent -> {
            String inputA = aValue.getText();
            String inputB = bValue.getText();
            String inputC = cValue.getText();

            if(inputA.equals("") || inputB.equals("") || inputC.equals("")) {
                answerField.setText("Please enter values for A, B, and C");
            }
            else {
                double a = Double.parseDouble(inputA);
                if(Double.compare(a, 0) == 0) {
                    answerField.setText("A cannot be 0");
                }
                else {
                    double b = Double.parseDouble(inputB);
                    double c = Double.parseDouble(inputC);
                    if(negativeDiscriminant(a, b, c)) {
                        answerField.setText("No real solutions");
                    }
                    else {
                        double sqrt = Math.sqrt((b * b) - (4 * a * c));
                        double x1 = (-b + sqrt) / (2 * a);
                        double x2 = (-b - sqrt) / (2 * a);
                        answerField.setText(String.format("X = %.3f and X = %.3f", x1, x2));
                    }
                }
            }
        };

        submitButton.setOnAction(calculate);
        clearButton.setOnAction(clear);

        HBox textFields = new HBox(aValue, bValue, cValue);
        textFields.setSpacing(10);

        HBox buttons = new HBox(submitButton, clearButton);
        buttons.setAlignment(Pos.BOTTOM_CENTER);
        buttons.setSpacing(30);

        VBox root = new VBox(title, textFields, buttons, answerField);
        root.setSpacing(50);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.TOP_CENTER);
        root.setBackground(Background.fill(Color.PINK));

        Scene scene = new Scene(root, 600, 400);
        stage.setResizable(false); // didn't add property bindings so just to prevent the styling from looking wacky
        stage.setTitle("Quadratic Equation Calculator");
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Creates a custom text input field
     * @param name the prompt text of the input field
     * @return the stylized text field
     */
    private static TextField createTextInput(String name) {
        TextField tempText = new TextField();
        tempText.setPromptText(name);
        tempText.setPrefSize(200, 30);
        tempText.setBorder(Border.stroke(Color.BLACK));
        return tempText;
    }

    /**
     * Creates a custom button
     * @param name the name of the button
     * @return the stylized button
     */
    private static Button createButton(String name) {
        Button tempButton = new Button(name);
        tempButton.setFont(new Font("Galaxy BT", 20));
        tempButton.setBorder(Border.stroke(Color.BLACK));
        tempButton.setPrefSize(200, 30);
        return tempButton;
    }

    /**
     * Creates a custom label for title
     * @return the stylized title label
     */
    private static Label createTitle() {
        Label tempLabel = new Label("Quadratic Equation Calculator");
        tempLabel.setPadding(new Insets(5));
        tempLabel.setBorder(Border.stroke(Color.WHITE));
        tempLabel.setAlignment(Pos.CENTER);
        tempLabel.setFont(new Font("Galaxy BT", 40));
        tempLabel.setStyle("-fx-text-fill: white");
        return tempLabel;
    }

    /**
     * Creates a custom text area
     * @return the stylized text area
     */
    private static TextArea createTextArea() {
        TextArea tempText = new TextArea();
        tempText.setPromptText("Your answer will appear here: ");
        tempText.setEditable(false);
        tempText.setBorder(Border.stroke(Color.BLACK));
        tempText.setFont(new Font("Galaxy BT", 20));
        return tempText;
    }

    /**
     * Checks if given values result in negative discriminant
     * @param a a value
     * @param b b value
     * @param c c value
     * @return true if negative, false if not
     */
    private static boolean negativeDiscriminant(double a, double b, double c) {
        return ((b * b) < (4 * a * c));
    }

    public static void main(String[] args) {
        launch();
    }
}
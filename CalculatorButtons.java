/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import javafx.scene.control.Button;

/**
 * @author rober
 */
public class CalculatorButtons {
    public static Button[] numberButtons = {
        new Button(" 0 "),
        new Button(" 1 "),
        new Button(" 2 "),
        new Button(" 3 "),
        new Button(" 4 "),
        new Button(" 5 "),
        new Button(" 6 "),
        new Button(" 7 "),
        new Button(" 8 "),
        new Button(" 9 "),
        new Button(" + "),
        new Button(" - "),
        new Button(" * "),
        new Button(" / "),
        new Button(" . "),
        new Button(" Enter "),
        new Button(" C ")
    };
    
    public static void setInitialStyle(Button currButton, String buttonSize) {
        currButton.setStyle(
                    "-fx-background-color: white;" + 
                    "-fx-font-size: 2em;" + 
                    String.format("-fx-background-radius: %spx;", buttonSize)
            );
    }
    
    public static void setButtonColor(Button currButton, String buttonSize, String color) {
        currButton.setStyle(
                    String.format("-fx-background-color: %s;", color) + 
                    "-fx-font-size: 2em;" + 
                    String.format("-fx-background-radius: %spx;", buttonSize)
            );
    }
}

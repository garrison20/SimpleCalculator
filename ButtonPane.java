/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculator;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author rober
 */
public class ButtonPane {
    public static GridPane gridPane = new GridPane();
    
    public static void setGridPane(Text textInput) {
        gridPane.add(textInput, 0, 0);
        gridPane.add(CalculatorButtons.numberButtons[7], 0, 1);
        gridPane.add(CalculatorButtons.numberButtons[8], 1, 1);
        gridPane.add(CalculatorButtons.numberButtons[9], 2, 1);
        gridPane.add(CalculatorButtons.numberButtons[10], 3, 1); // +
        gridPane.add(CalculatorButtons.numberButtons[4], 0, 2);
        gridPane.add(CalculatorButtons.numberButtons[5], 1, 2);
        gridPane.add(CalculatorButtons.numberButtons[6], 2, 2);
        gridPane.add(CalculatorButtons.numberButtons[11], 3, 2); // -
        gridPane.add(CalculatorButtons.numberButtons[1], 0, 3);
        gridPane.add(CalculatorButtons.numberButtons[2], 1, 3);
        gridPane.add(CalculatorButtons.numberButtons[3], 2, 3);
        gridPane.add(CalculatorButtons.numberButtons[12], 3, 3); // *
        gridPane.add(CalculatorButtons.numberButtons[0], 0, 4, 2, 1);
        gridPane.add(CalculatorButtons.numberButtons[14], 2, 4); // .
        gridPane.add(CalculatorButtons.numberButtons[13], 3, 4); // /
        gridPane.add(CalculatorButtons.numberButtons[15], 0, 5, 3, 1); // Enter
        gridPane.add(CalculatorButtons.numberButtons[16], 3, 5); // C
        
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        gridPane.setAlignment(Pos.BOTTOM_CENTER);
        gridPane.setPadding(new Insets(10));
    }
        
}

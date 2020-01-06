/*
    This class createst the GridPane which organizes all the buttons into a 
    neat display. It also sets the style of the layout by adjusting spacing. 
    Finally, the class is also used to hold the textual values of the user's 
    inputs.
 */
package calculator;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

/**
 *
 * @author Robert Denhardt
 */
public class ButtonPane {
    public static Label outputText = new Label("0");
    public static Label inputOneText = new Label("");
    public static Label mathTypeText = new Label("");
    public static Label inputTwoText = new Label("");
    
    public static GridPane gridPane = new GridPane();
    
    public static void setGridPane() {
        /* 
            Set the font style of the label which displays the number being 
            inputed and the final calucaltion
        */
        outputText.setFont(
                Font.font("verdana", null, FontPosture.REGULAR, 20)
        );
        
        // Build the button layout row by row
        
        // All text which displays the values of previous inputs
        gridPane.add(inputOneText, 0, 0);
        gridPane.add(mathTypeText, 1, 0);
        gridPane.add(inputTwoText, 2, 0);
        // Displays the current value being inputted and the final calculation
        gridPane.add(outputText, 0, 1);
        /*
            Displays all the buttons used for manipulating and performing 
            calcualtions.
        */
        gridPane.add(CalculatorButtons.numberButtons[7], 0, 2);
        gridPane.add(CalculatorButtons.numberButtons[8], 1, 2);
        gridPane.add(CalculatorButtons.numberButtons[9], 2, 2);
        gridPane.add(CalculatorButtons.numberButtons[10], 3, 2); // +
        gridPane.add(CalculatorButtons.numberButtons[4], 0, 3);
        gridPane.add(CalculatorButtons.numberButtons[5], 1, 3);
        gridPane.add(CalculatorButtons.numberButtons[6], 2, 3);
        gridPane.add(CalculatorButtons.numberButtons[11], 3, 3); // -
        gridPane.add(CalculatorButtons.numberButtons[1], 0, 4);
        gridPane.add(CalculatorButtons.numberButtons[2], 1, 4);
        gridPane.add(CalculatorButtons.numberButtons[3], 2, 4);
        gridPane.add(CalculatorButtons.numberButtons[12], 3, 4); // *
        gridPane.add(CalculatorButtons.numberButtons[0], 0, 5, 2, 1);
        gridPane.add(CalculatorButtons.numberButtons[14], 2, 5); // .
        gridPane.add(CalculatorButtons.numberButtons[13], 3, 5); // /
        gridPane.add(CalculatorButtons.numberButtons[15], 0, 6, 3, 1); // Enter
        gridPane.add(CalculatorButtons.numberButtons[16], 3, 6); // C
        
        // Put a 10px spacing around all the contents of the GridPane
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        
        /* 
            Align the GridPane to the bottom to allow manipulation of the 
            calculator's top portion. This is done so the display at the top
            doesn't feel cluttered.
        */
        gridPane.setAlignment(Pos.BOTTOM_CENTER);
        // Places a 10px spacing around the border of the GridPane
        gridPane.setPadding(new Insets(10));
    }
        
}

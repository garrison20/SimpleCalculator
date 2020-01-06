/* 
    Program created by Robert Denhardt.
 
    This is a simple calculator which takes in two numbers and performs either
    addition, subtraction, multiplication, or division. This is performed by 
    using a GUI, which represents a standard calculator. As a user enters their
    desired numbers for each input, the input's value is displayed and updates 
    in real time. When the user presses enter, the desired computation is 
    performed and the calculation is shown in place of this text. Above the 
    computation's display, the previous inputs are displayed to show the user
    the full expression they inputted, and these numbers' values update in 
    real-time respective to which value (first or second) the user is on.
 */
package calculator;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

/**
 * @author Robert Denhardt
 */
public class Calculator extends Application {
    
    final int HEIGHT = 400, WIDTH = 270;
    final String BUTTONSIZE = "30";
    String inputOne = "", inputTwo = "", mathType = "", inputString = "";
    
    enum InputVal {INPUTONE, INPUTTWO;}
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Set the scene using the gridPane from custom ButtonPane class
        Scene scene = new Scene(ButtonPane.gridPane, HEIGHT, WIDTH);
        
        // Build the button layout in custom ButtonPane class
        ButtonPane.setGridPane();
        
        /*
           Give every button a white background that changes to gray when 
           hovered.
        */
        for (Button currButton: CalculatorButtons.numberButtons) {
            // Make all buttons the same size
            currButton.setMinWidth(50);
            // Need to unconstrain button so it can extend past width of column
            currButton.setMaxWidth(HEIGHT);
            
            // Make all buttons start with the same style
            CalculatorButtons.setInitialStyle(currButton, BUTTONSIZE);
            
            /*
                Make color of button change to gray when hovering and change
                the cursor to a hand.
            */
            currButton.addEventHandler(MouseEvent.MOUSE_ENTERED, 
                    new EventHandler<MouseEvent>() {
                @Override 
                public void handle(MouseEvent m) {
                    CalculatorButtons.setButtonColor(currButton, 
                            BUTTONSIZE, "gray");
                    scene.setCursor(Cursor.HAND);
                    
                }
            });
        
            /*
                Make color of button change back to white after moving cursor 
                off button and change the cursor back to a pointer.
            */
            currButton.addEventHandler(MouseEvent.MOUSE_EXITED,
                    new EventHandler<MouseEvent>() {
                @Override 
                public void handle(MouseEvent m) {
                    CalculatorButtons.setButtonColor(currButton, 
                            BUTTONSIZE, "white");
                    scene.setCursor(Cursor.DEFAULT);
                }
            });
            
            // Read button press as text
            currButton.addEventHandler(MouseEvent.MOUSE_PRESSED, 
                    new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent m) {
                    String inputString = "";
                    for (int i = 0; i < 10; i++) {
                        inputString = currButton.getText().trim();
                        if (inputString.equals(Integer.toString(i))) {
                            if (mathType.isEmpty()) {
                                addInteger(inputString, InputVal.INPUTONE, ButtonPane.outputText);
                            } else {
                                addInteger(inputString, InputVal.INPUTTWO, ButtonPane.outputText);
                            }
                        }
                    }
                    
                    switch(inputString) {
                        case "C":
                            clearSingleInput(ButtonPane.outputText);
                            break;
                        case "Enter":
                            computeExpression(ButtonPane.outputText);
                            break;
                        case "+":
                        case "-":
                        case "*":
                        case "/":
                            setMathType(inputString, ButtonPane.outputText);
                            break;
                        case ".":
                            System.out.println("Not implemented yet.");
                    }
                    
                    // Update any values which are being displayed
                    setStoredInputs();
                }
            });
        }
        
        // Construct the innards of the window and show it
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculator");
        primaryStage.getIcons().add(
                new Image(
                    Calculator.class.getResourceAsStream("calculator-logo.png")
                )
        );
        // Keep the window at its set size
        primaryStage.setMaxHeight(HEIGHT);
        primaryStage.setMinHeight(HEIGHT);
        primaryStage.setMaxWidth(WIDTH);
        primaryStage.setMinWidth(WIDTH);
        
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    // Changes the displayed text based on which one is currently being updated 
    void addInteger(String inputString, InputVal inputVal, Label labelInput) {
        if (inputVal == InputVal.INPUTONE) {
            inputOne = inputOne + inputString;
            labelInput.setText(inputOne);
        } else {
            inputTwo += inputString;
            labelInput.setText(inputTwo);
        }
    }
    
    // Clears the previous inputs one input at a time
    void clearSingleInput(Label labelInput) {
        /* 
            Both values aren't equal, then they must've been wiped and now we're 
            performing a new calculation.
        */
        if (!labelInput.getText().equals(inputOne) && 
                !labelInput.getText().equals(inputTwo)) {
            clearBothInputs();
            labelInput.setText("0");
        /*
            Here we start performing one deletion at a time. If the second value 
            is being worked on, then only delete this value.
        */
        } else if (!inputTwo.isEmpty()) {
            inputTwo = "";
            labelInput.setText("0");
        // Only delete the first input
        } else if (!inputOne.isEmpty()) {
            inputOne = "";
            labelInput.setText("0");
        }
        
        // Delete the sign since there is no computation being worked on
        if (inputOne.isEmpty() && inputTwo.isEmpty()) {
            mathType = "";
        }
    }
    
    /*
        Clears the stored values of all inputs. Their previously stored values
        will remain on the screen until a button is pressed and changes the 
        first value.
    */
    void clearBothInputs() {
        inputOne = "";
        inputTwo = "";
        mathType = "";
    }
    
    // Perform the calculation based on the sign that was given
    void computeExpression(Label labelInput) {
        if (!inputOne.isEmpty() && !inputTwo.isEmpty()) {
            if (!mathType.isEmpty()) {
                int inputOneVal = Integer.parseInt(inputOne);
                int inputTwoVal = Integer.parseInt(inputTwo);
                
                switch(mathType) {
                    case "+":
                        labelInput.setText(Integer.toString(
                            inputOneVal + inputTwoVal)
                        );
                        break;
                    case "-":
                        labelInput.setText(Integer.toString(
                            inputOneVal - inputTwoVal)
                        );
                        break;
                    case "*":
                        labelInput.setText(Integer.toString(
                            inputOneVal * inputTwoVal)
                        );
                        break;
                    case "/":
                        // Prevent a "division by zero" error
                        if (!inputTwo.equals("0")) {
                            labelInput.setText(Integer.toString(
                                    inputOneVal / inputTwoVal)
                            );
                        }
                }
                
                // Clear the inputs to set up storage for the next calculation
                clearBothInputs();
            }
        }
    }
    
    // Sets the sign used for computation
    void setMathType(String inputString, Label labelInput) {
        // Prevent the sign from changing if it's already been set once 
        if (!inputOne.isEmpty() && inputTwo.isEmpty()) {
            mathType = inputString;
            // Reset display for new inputs for the second value
            labelInput.setText("0");
        }
    }
    
    // Updates the displayed values of all inputs in real-time
    void setStoredInputs() {
        if (!ButtonPane.inputOneText.getText().equals(inputOne))
            ButtonPane.inputOneText.setText(inputOne);
        if (!ButtonPane.mathTypeText.getText().equals(mathType))
            ButtonPane.mathTypeText.setText(mathType);
        if (!ButtonPane.inputTwoText.getText().equals(inputTwo))
            ButtonPane.inputTwoText.setText(inputTwo);
    }
    
}

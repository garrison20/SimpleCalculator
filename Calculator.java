/*
 * Robert Denhardt
 */
package calculator;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

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
        
        Text textInput = new Text("0");
        textInput.setFont(Font.font("verdana", null, FontPosture.REGULAR, 20));
        
        // Set the scene using the gridPane from custom ButtonPane class
        Scene scene = new Scene(ButtonPane.gridPane, HEIGHT, WIDTH);
        
        // Build the button layout in custom ButtonPane class
        ButtonPane.setGridPane(textInput);
        
        /*
           Give every button a white background that changes to gray when 
           hovered.
        */
        for (Button currButton: CalculatorButtons.numberButtons) {
            // Make all buttons the same size
            currButton.setMinWidth(50);
            // Need to unconstrain button so it can extend past width of column.
            currButton.setMaxWidth(HEIGHT);
            
            /*if (currButton.getId().equals(" Clear ")) {
                currButton.setMaxWidth(1000);
            }*/
            
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
            
            // Read button press as text.
            currButton.addEventHandler(MouseEvent.MOUSE_PRESSED, 
                    new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent m) {
                    String inputString = "";
                    for (int i = 0; i < 10; i++) {
                        inputString = currButton.getText().trim();
                        if (inputString.equals(Integer.toString(i))) {
                            if (mathType.isEmpty()) {
                                addInteger(inputString, InputVal.INPUTONE, textInput);
                            } else {
                                addInteger(inputString, InputVal.INPUTTWO, textInput);
                            }
                            
                        }
                    }
                    
                    if (inputString.equals("C")) {
                        clearSingleInput(textInput);
                    } else if (inputString.equals("Enter")) {
                        computeExpression(textInput);
                    } else if (inputString.equals("+") ||
                            inputString.equals("-") ||
                            inputString.equals("*") ||
                            inputString.equals("/")) {
                        setMathType(inputString, textInput);
                    }
                }
            });
        }
        
        /****************** Set the stage and show it *************************/
        
        // Construct the innards of the window and show it
        primaryStage.setScene(scene);
        primaryStage.setTitle("Calculator");
        primaryStage.getIcons().add(
                new Image(Calculator.class.getResourceAsStream("umdlogo.png"))
        );
        primaryStage.setMaxHeight(HEIGHT);
        primaryStage.setMinHeight(HEIGHT);
        primaryStage.setMaxWidth(WIDTH);
        primaryStage.setMinWidth(WIDTH);
        primaryStage.show();
        
        
    }
    
    public static void main(String[] args) {
        Application.launch(args);
    }
    
    void addInteger(String inputString, InputVal inputVal, Text textInput) {
        if (inputVal == InputVal.INPUTONE) {
            inputOne = inputOne + inputString;
            textInput.setText(inputOne);
        } else {
            inputTwo += inputString;
            textInput.setText(inputTwo);

        }
    }
    
    void clearSingleInput(Text textInput) {
        if (!textInput.getText().equals(inputOne) && 
                !textInput.getText().equals(inputTwo)) {
            clearBothInputs();
            textInput.setText("0");
        } else if (!inputTwo.isEmpty()) {
            inputTwo = "";
            textInput.setText("0");
        } else if (!inputOne.isEmpty()) {
            inputOne = "";
            textInput.setText("0");
        }
        
        if (inputOne.isEmpty() && inputTwo.isEmpty()) {
            mathType = "";
        }
    }
    
    void clearBothInputs() {
        inputOne = "";
        inputTwo = "";
        mathType = "";
    }
    
    void computeExpression(Text textInput) {
        if (!inputOne.isEmpty() && !inputTwo.isEmpty()) {
            if (!mathType.isEmpty()) {
                if (mathType.equals("+")) {
                    textInput.setText(Integer.toString(
                            Integer.parseInt(inputOne) 
                            + Integer.parseInt(inputTwo))
                    );
                } else if (mathType.equals("-")) {
                    textInput.setText(Integer.toString(
                            Integer.parseInt(inputOne) 
                            - Integer.parseInt(inputTwo))
                    );
                } else if (mathType.equals("*")) {
                    textInput.setText(Integer.toString(
                            Integer.parseInt(inputOne) 
                            * Integer.parseInt(inputTwo))
                    );
                } else {
                    textInput.setText(Integer.toString(
                            Integer.parseInt(inputOne) 
                            / Integer.parseInt(inputTwo))
                    );
                }
                clearBothInputs();
            }
        }
    }
    
    void setMathType(String inputString, Text textInput) {
        if (!inputOne.isEmpty() && inputTwo.isEmpty()) {
            mathType = inputString;
            textInput.setText("0");
        }
    }
    
}

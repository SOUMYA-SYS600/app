import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * A simple GUI calculator application built with Java Swing.
 * It performs basic arithmetic operations: addition, subtraction,
 * multiplication, and division.
 */
public class Calculator implements ActionListener {

    // Swing components for the calculator's GUI
    JFrame frame;
    JTextField textField;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[8];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, equButton, delButton, clrButton;
    JPanel panel;

    // Font for the display and buttons
    Font myFont = new Font("Inter", Font.BOLD, 30);

    // Variables to hold the operands and the operator for calculation
    double num1 = 0, num2 = 0, result = 0;
    char operator;

    /**
     * Constructor to set up the calculator's GUI and initialize components.
     */
    public Calculator() {

        // --- Frame Setup ---
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 550);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null); // Center the frame on the screen

        // --- Text Field (Display) ---
        textField = new JTextField();
        textField.setBounds(50, 25, 300, 50);
        textField.setFont(myFont);
        textField.setEditable(false); // User cannot type directly into the display
        textField.setHorizontalAlignment(JTextField.RIGHT);

        // --- Function Buttons ---
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        equButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("C");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = equButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;

        // --- Adding Action Listeners and Setting Font for Function Buttons ---
        for (int i = 0; i < 8; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false); // Removes the focus border around the button text
        }

        // --- Number Buttons ---
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }
        
        // --- Setting Bounds for Delete and Clear Buttons ---
        delButton.setBounds(50, 430, 145, 50);
        clrButton.setBounds(205, 430, 145, 50);

        // --- Panel for number and operator buttons ---
        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10)); // 4x4 grid with 10px spacing

        // --- Adding Buttons to the Panel (Row by Row) ---
        // Row 1
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        // Row 2
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        // Row 3
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        // Row 4
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(equButton);
        panel.add(divButton);

        // --- Adding Components to the Frame ---
        frame.add(panel);
        frame.add(delButton);
        frame.add(clrButton);
        frame.add(textField);
        frame.setVisible(true);
    }

    /**
     * The main entry point for the application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Creates an instance of the Calculator, which sets up and shows the GUI.
        Calculator calc = new Calculator();
    }

    /**
     * Handles action events from all the buttons.
     * @param e The ActionEvent object containing details about the event.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // --- Number Buttons ---
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textField.setText(textField.getText().concat(String.valueOf(i)));
            }
        }

        // --- Decimal Button ---
        if (e.getSource() == decButton) {
            // Prevent adding more than one decimal point
            if (!textField.getText().contains(".")) {
                textField.setText(textField.getText().concat("."));
            }
        }

        // --- Add Button ---
        if (e.getSource() == addButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '+';
            textField.setText("");
        }

        // --- Subtract Button ---
        if (e.getSource() == subButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '-';
            textField.setText("");
        }

        // --- Multiply Button ---
        if (e.getSource() == mulButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '*';
            textField.setText("");
        }

        // --- Divide Button ---
        if (e.getSource() == divButton) {
            num1 = Double.parseDouble(textField.getText());
            operator = '/';
            textField.setText("");
        }

        // --- Equals Button ---
        if (e.getSource() == equButton) {
            num2 = Double.parseDouble(textField.getText());

            // Perform calculation based on the stored operator
            switch (operator) {
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    // Handle division by zero
                    if (num2 == 0) {
                        textField.setText("Error");
                        return; // Exit the method to prevent further execution
                    }
                    result = num1 / num2;
                    break;
            }
            textField.setText(String.valueOf(result));
            num1 = result; // Allows for chained calculations
        }

        // --- Clear Button ---
        if (e.getSource() == clrButton) {
            textField.setText("");
            num1 = 0;
            num2 = 0;
            result = 0;
        }

        // --- Delete Button ---
        if (e.getSource() == delButton) {
            String string = textField.getText();
            textField.setText("");
            for (int i = 0; i < string.length() - 1; i++) {
                textField.setText(textField.getText() + string.charAt(i));
            }
        }
    }
}
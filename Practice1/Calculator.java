import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Calculator extends JFrame {

    private JTextField inputSpace;
    private String num = "";
    private String prev_opertaion = "";
    private ArrayList<String> equation = new ArrayList<String>();

    public Calculator() {

        setLayout(null);

        inputSpace = new JTextField();
        inputSpace.setEditable(false);
        inputSpace.setBackground(Color.white);
        inputSpace.setHorizontalAlignment(JTextField.RIGHT);
        inputSpace.setFont(new Font("Arial", Font.BOLD, 50));
        inputSpace.setBounds(8, 10, 270, 70);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 10, 10));
        buttonPanel.setBounds(8, 90, 270, 235);

        String button_names[] = {"C", "/", "x", "=", "7", "8", "9", "+", "4", "5", "6", "-", "1", "2", "3", "0"};
        JButton buttons[] = new JButton[button_names.length];

        for (int i = 0; i < button_names.length; i++) {
            buttons[i] = new JButton(button_names[i]);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 20));
            if (button_names[i] == "C") buttons[i].setBackground(Color.RED);
            else if ((i >= 4 && i <= 6) || (i >= 8 && i <= 10) || (i >= 12 && i <= 14))
                buttons[i].setBackground(Color.black);
            else buttons[i].setBackground(Color.gray);
            buttons[i].setForeground(Color.black);
            buttons[i].setBorderPainted(false);
            buttons[i].addActionListener(new PadActionListener());
            buttonPanel.add(buttons[i]);
        }

        add(inputSpace);
        add(buttonPanel);

        setTitle("계산기");
        setVisible(true);
        setSize(300, 370);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    class PadActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String operation = e.getActionCommand();

            if (operation.equals("C")) {
                inputSpace.setText("");
            } else if (operation.equals("=")) {
                String result = Double.toString(calculate(inputSpace.getText()));
                inputSpace.setText("" + result);
                num = "";
            } else if (operation.equals("+") || operation.equals("-") || operation.equals("x") || operation.equals("/")) {
                if (inputSpace.getText().equals("") && operation.equals("-")) {
                    inputSpace.setText(inputSpace.getText() + e.getActionCommand());
                } else if (!inputSpace.getText().equals("") && !prev_opertaion.equals("+") && !prev_opertaion.equals("-") && !prev_opertaion.equals("x") && !prev_opertaion.equals("/")) {
                    inputSpace.setText(inputSpace.getText() + e.getActionCommand());
                }
            } else inputSpace.setText(inputSpace.getText() + e.getActionCommand());
            prev_opertaion = e.getActionCommand();
        }
    }

    public double calculate(String inputText) {
        fullTextParsing(inputText);

        double prev = 0;
        double current = 0;

        String mode = "";

        for (int i = 0; i < equation.size(); i++) {
            String s = equation.get(i);

            if (s.equals("+")) {
                mode = "add";
            } else if (s.equals("-")) {
                mode = "sub";
            } else if (s.equals("x")) {
                mode = "mul";
            } else if (s.equals("/")) {
                mode = "div";
            } else {
                if ((mode.equals("mul") || mode.equals("div")) && !s.equals("+") && !s.equals("-") && !s.equals("x") && !s.equals("/")) {
                    Double one = Double.parseDouble(equation.get(i - 2));
                    Double two = Double.parseDouble(equation.get(i));
                    Double result = 0.0;

                    if (mode.equals("mul")) result = one * two;
                    else if (mode.equals("div")) result = one / two;

                    equation.add(i + 1, Double.toString(result));

                    for (int j = 0; j < 3; j++) equation.remove(i - 2);
                    i -= 2;
                }
            }
        }

        for (String s : equation) {
            if (s.equals("+")) mode = "add";
            else if (s.equals("-")) mode = "sub";
            else if (s.equals("x")) mode = "mul";
            else if (s.equals("/")) mode = "div";
            else {
                current = Double.parseDouble(s);

                if (mode.equals("add")) prev += current;
                else if (mode.equals("sub")) prev -= current;
                else if (mode.equals("mul")) prev *= current;
                else if (mode.equals("div")) prev /= current;
                else prev = current;
            }
            prev = Math.round(prev * 100000) / 100000.0;
        }
        return prev;
    }

    private void fullTextParsing(String inputText) {
        equation.clear();

        for (int i = 0; i < inputText.length(); i++) {
            char ch = inputText.charAt(i);

            if (ch == '-' || ch == '+' || ch == 'x' || ch == '/') {
                equation.add(num);
                num = "";
                equation.add(ch + "");
            } else num = num + ch;
        }
        equation.add(num);
    }

    public static void main(String[] args) {
        new Calculator();
    }
}

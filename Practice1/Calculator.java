import javax.swing.*;
import java.awt.*;

public class Calculator extends JFrame{

    private JTextField inputSpace;
    public Calculator() {

        setLayout(null);

        inputSpace = new JTextField();
        inputSpace.setEditable(false);
        inputSpace.setBackground(Color.white);
        inputSpace.setHorizontalAlignment(JTextField.RIGHT);
        inputSpace.setFont(new Font("Arial",Font.BOLD,50));
        inputSpace.setBounds(8,10,270,70);

        add(inputSpace);
        
        setTitle("계산기");
        setVisible(true);
        setSize(300,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new Calculator();
    }
}

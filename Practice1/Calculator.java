import javax.swing.JFrame;
public class Calculator extends JFrame{
    public Calculator() {
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

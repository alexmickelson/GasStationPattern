import javax.swing.*;

public class SwingTanks extends JPanel {

    public SwingTankPanel regular;
    public SwingTankPanel premium;

    public SwingTanks(){
        super();
        regular = new SwingTankPanel("Regular");
        premium = new SwingTankPanel("Premium");

        add(regular);
        add(premium);
    }
}

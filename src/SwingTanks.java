import javax.swing.*;

public class SwingTanks extends JPanel {

    public SwingTanks(){
        super();

        add(new SwingTankPanel("Regular"));
        add(new SwingTankPanel("Premium"));
    }
}

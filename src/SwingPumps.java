import javax.swing.*;
import java.awt.*;

public class SwingPumps extends JPanel {
    public SwingPumpPanel pump1;
    public SwingPumpPanel pump2;
    public SwingPumpPanel pump3;
    public SwingPumpPanel pump4;
    public SwingPumpPanel pump5;
    public SwingPumpPanel pump6;

    public SwingPumps(){
        super();

        pump1 = new SwingPumpPanel("Pump 1");
        pump2 = new SwingPumpPanel("Pump 2");
        pump3 = new SwingPumpPanel("Pump 3");
        pump4 = new SwingPumpPanel("Pump 4");
        pump5 = new SwingPumpPanel("Pump 5");
        pump6 = new SwingPumpPanel("Pump 6");

        setLayout(new GridBagLayout());
        var c = new GridBagConstraints();
        c.insets = new Insets(2, 2, 2, 2);

        c.gridx=0;
        c.gridy=0;
        add(pump1,c);

        c.gridx=1;
        add(pump2,c);

        c.gridx=2;
        add(pump3,c);

        c.gridx=0;
        c.gridy=1;
        add(pump4,c);

        c.gridx=1;
        add(pump5,c);

        c.gridx=2;
        add(pump6,c);



    }
}

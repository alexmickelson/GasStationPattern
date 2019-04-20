import javax.swing.*;
import java.awt.*;

public class SwingPumps extends JPanel {

    public SwingPumps(){
        super();

        var pump1 = new SwingPumpPanel("Pump 1");
        var pump2 = new SwingPumpPanel("Pump 2");
        var pump3 = new SwingPumpPanel("Pump 3");
        var pump4 = new SwingPumpPanel("Pump 4");
        var pump5 = new SwingPumpPanel("Pump 5");
        var pump6 = new SwingPumpPanel("Pump 6");

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

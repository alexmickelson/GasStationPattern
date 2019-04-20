import javax.swing.*;
import java.awt.*;

public class SwingStationFrame extends JFrame {

    public SwingStationFrame(){

        var pump1 = new SwingPumpPanel("Pump 1");
        var pump2 = new SwingPumpPanel("Pump 2");
        var pump3 = new SwingPumpPanel("Pump 3");
        var pump4 = new SwingPumpPanel("Pump 4");
        var pump5 = new SwingPumpPanel("Pump 5");
        var pump6 = new SwingPumpPanel("Pump 6");

        setLayout(new GridBagLayout());
        var c = new GridBagConstraints();
        
        c.gridx=0;
        c.gridy=0;

        add(pump1);
        add(pump2);
        add(pump3);
        add(pump4);
        add(pump5);
        add(pump6);


        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/3, dim.height/3);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

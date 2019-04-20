import javax.swing.*;
import java.awt.*;

public class SwingStationFrame extends JFrame {

    public SwingStationFrame(){

        setLayout(new GridBagLayout());
        var c = new GridBagConstraints();
        c.gridx=0;
        c.gridy=0;
        add(new SwingPumps(), c);
        c.gridx=1;
        add(new SwingTanks(), c);
        c.gridy=1;
        add(new SwingStatsGeneral(), c);




        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/4, dim.height/4);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

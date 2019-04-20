import javax.swing.*;
import java.awt.*;

public class SwingStationFrame extends JFrame {

    public SwingStationFrame(){

        add(new SwingPumps());




        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/3, dim.height/3);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

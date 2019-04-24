import javax.swing.*;
import java.awt.*;

public class SwingStationFrame extends JFrame {
    private SwingPumps pumps;
    private SwingTanks tanks;
    private SwingStatsGeneral stats;
    private SwingButtons buttons;

    public SwingStationFrame(){
        pumps = new SwingPumps();
        tanks = new SwingTanks();
        stats = new SwingStatsGeneral();
        buttons = new SwingButtons();

        setLayout(new GridBagLayout());
        var c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.gridx=0;
        c.gridy=0;
        c.gridheight=3;
        add(pumps, c);
        c.gridheight=1;
        c.gridx=1;
        add(tanks, c);
        c.gridy=1;
        add(stats, c);
        c.gridy=2;

        add(buttons, c);




        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/4, dim.height/4);
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

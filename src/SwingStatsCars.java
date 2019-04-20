import javax.swing.*;
import java.awt.*;

public class SwingStatsCars extends JPanel {
    private JTextArea arrived;
    private JTextArea Served;
    private JTextArea noP;
    private JTextArea noM;
    private JTextArea noR;
    private JTextArea totalLost;

    public SwingStatsCars(){
        arrived = new JTextArea("none");
        arrived.setEditable(false);
        Served = new JTextArea("none");
        Served.setEditable(false);
        noP = new JTextArea("none");
        noP.setEditable(false);
        noM = new JTextArea("none");
        noM.setEditable(false);
        noR = new JTextArea("none");
        noR.setEditable(false);
        totalLost = new JTextArea("none");
        totalLost.setEditable(false);

        setLayout(new GridBagLayout());
        var c = new GridBagConstraints();

        var l = new JLabel("Arrived");
        l.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        c.fill=GridBagConstraints.HORIZONTAL;
        c.anchor=GridBagConstraints.LINE_START;
        c.gridx = 0;
        c.gridy=0;
        add(l,c);

        l = new JLabel("Served");
        l.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        c.gridy=1;
        add(l,c);

        l = new JLabel("Lost - No Fuel Premium");
        l.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        c.gridy=2;
        add(l,c);

        l = new JLabel("Lost - No Fuel Midgrade");
        l.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        c.gridy=3;
        add(l,c);

        l = new JLabel("Lost - No Fuel Regular");
        l.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        c.gridy=4;
        add(l,c);

        l = new JLabel("Total Lost");
        l.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        c.gridy=5;
        add(l,c);

        c.gridx=1;
        c.gridy=0;
        add(arrived, c);
        c.gridy=1;
        add(Served, c);
        c.gridy=2;
        add(noP, c);
        c.gridy=3;
        add(noM, c);
        c.gridy=4;
        add(noR, c);
        c.gridy=5;
        add(totalLost, c);
    }
}

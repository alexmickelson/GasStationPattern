import javax.swing.*;
import java.awt.*;

public class SwingStatsSales extends JPanel {
    private JTextArea premiumGas;
    private JTextArea midgrade;
    private JTextArea regular;


    public SwingStatsSales(){

        premiumGas = new JTextArea("none");
        premiumGas.setEditable(false);
        midgrade = new JTextArea("none");
        midgrade.setEditable(false);
        regular = new JTextArea("none");
        regular.setEditable(false);

        setLayout(new GridBagLayout());
        var c = new GridBagConstraints();

        var l = new JLabel("Premium Gallons Sold");
        l.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        c.fill=GridBagConstraints.HORIZONTAL;
        c.anchor=GridBagConstraints.LINE_START;
        c.gridx=0;
        c.gridy=0;

        add(l,c);

        l = new JLabel("Midgrade Gallons Sold");
        l.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        c.gridx = 0;
        c.gridy=1;
        add(l,c);

        l = new JLabel("Regular Gallons Sold");
        l.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        c.gridx = 0;
        c.gridy=2;
        add(l,c);

        c.gridx=1;
        c.gridy=0;
        add(premiumGas, c);

        c.gridy=1;
        add(midgrade, c);

        c.gridy=2;
        add(regular, c);




    }
}
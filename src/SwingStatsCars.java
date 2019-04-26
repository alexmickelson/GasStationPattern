import javax.swing.*;
import java.awt.*;

public class SwingStatsCars extends JPanel {
    private JTextArea arrived;
    private JTextArea served;
    private JTextArea noP;
    private JTextArea noM;
    private JTextArea noR;
    private JTextArea lostPumpsfull;
    private JTextArea totalLost;

    public void setArrived(int amt){
        arrived.setText(amt + "");
        setTotalLost();
    }
    public void setServed(int amt){
        served.setText(amt+"");
        setTotalLost();
    }
    public void setLostNoPremium(int amt){
        noP.setText(amt+"");
        setTotalLost();
    }
    public void setLostNoMedium(int amt){
        noM.setText(amt+"");
        setTotalLost();
    }
    public void setLostNoRegular(int amt){
        noR.setText(amt+"");
        setTotalLost();
    }
    public void setlostPumpsfull(int amt){
        lostPumpsfull.setText(amt+"");
        setTotalLost();
    }
    public void setTotalLost(){
        int total = 0;
        try {
            total += Integer.parseInt(noP.getText());
            total += Integer.parseInt(noM.getText());
            total += Integer.parseInt(noR.getText());
            total += Integer.parseInt(lostPumpsfull.getText());
            totalLost.setText(total + "");
        }catch (NumberFormatException e){ // if any arent numbers set total lost to none
            totalLost.setText("none");
        }
    }

    public SwingStatsCars(){
        int col = 4;
        arrived = new JTextArea("none", 1, col);
        arrived.setEditable(false);
        served = new JTextArea("none", 1, col);
        served.setEditable(false);
        noP = new JTextArea("none", 1, col);
        noP.setEditable(false);
        noM = new JTextArea("none", 1, col);
        noM.setEditable(false);
        noR = new JTextArea("none", 1, col);
        noR.setEditable(false);
        lostPumpsfull = new JTextArea("none", 1, col);
        lostPumpsfull.setEditable(false);
        totalLost = new JTextArea("none", 1, col);
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

        l = new JLabel("Lost - Pumps Full");
        l.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        c.gridy=5;
        add(l,c);

        l = new JLabel("Total Lost");
        l.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        c.gridy=6;
        add(l,c);

        c.gridx=1;
        c.gridy=0;
        add(arrived, c);
        c.gridy=1;
        add(served, c);
        c.gridy=2;
        add(noP, c);
        c.gridy=3;
        add(noM, c);
        c.gridy=4;
        add(noR, c);
        c.gridy=5;
        add(lostPumpsfull, c);
        c.gridy=6;
        add(totalLost, c);
    }
}

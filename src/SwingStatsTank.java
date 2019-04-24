import javax.swing.*;
import java.awt.*;

public class SwingStatsTank extends JPanel {
    private JTextArea pTotalGallonsOrdered;
    private JTextArea pNumberOfOrders;
    private JTextArea pTotalGallonsDelivered;
    private JTextArea pTotalGallonsOrderedOverage;
    private JTextArea rTotalGallonsOrdered;
    private JTextArea rNumberOfOrders;
    private JTextArea rTotalGallonsDelivered;
    private JTextArea rTotalGallonsOrderedOverage;


    public void setPremiumTotalGallonsOrdered(double amt){
        pTotalGallonsOrdered.setText(amt+"");
    }
    public void setPremiumNumberOfOrders(int num){
        pNumberOfOrders.setText(num+"");
    }
    public void setPremiumTotalGallonsDelivered(double amt){
        pTotalGallonsDelivered.setText(amt+"");
    }
    public void setPremiumTotalGallonsOrderedOverage(double amt){
        pTotalGallonsOrderedOverage.setText(amt+"");
    }


    public void setRegularTotalGallonsOrdered(double amt){
        rTotalGallonsOrdered.setText(amt+"");
    }
    public void setRegularNumberOfOrders(int num){
        rNumberOfOrders.setText(num+"");
    }
    public void setRegularTotalGallonsDelivered(double amt){
        rTotalGallonsDelivered.setText(amt+"");
    }
    public void setRegularTotalGallonsOrderedOverage(double amt){
        rTotalGallonsOrderedOverage.setText(amt+"");
    }




    public SwingStatsTank(){
        super();
        pTotalGallonsOrdered = new JTextArea("none");
        pTotalGallonsOrdered.setEditable(false);
        pNumberOfOrders = new JTextArea("none");
        pNumberOfOrders.setEditable(false);
        pTotalGallonsDelivered = new JTextArea("none");
        pTotalGallonsDelivered.setEditable(false);
        pTotalGallonsOrderedOverage = new JTextArea("none");
        pTotalGallonsOrderedOverage.setEditable(false);

        rTotalGallonsOrdered = new JTextArea("none");
        rTotalGallonsOrdered.setEditable(false);
        rNumberOfOrders = new JTextArea("none");
        rNumberOfOrders.setEditable(false);
        rTotalGallonsDelivered = new JTextArea("none");
        rTotalGallonsDelivered.setEditable(false);
        rTotalGallonsOrderedOverage = new JTextArea("none");
        rTotalGallonsOrderedOverage.setEditable(false);

        //Premium Panel
        var premium = new JPanel();
        premium.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        premium.setLayout(new GridBagLayout());
        var c = new GridBagConstraints();

        //word premium
        var pLabel = new JLabel("Premium");
        c.gridx=0;
        c.gridy=0;
        c.weighty=4;
        c.anchor=GridBagConstraints.LAST_LINE_START;
        premium.add(pLabel, c);

        //middle stats labels
        var l = new JLabel("Total Gallons Ordered");
        l.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        c.anchor=GridBagConstraints.LINE_START;
        c.weighty=0;
        c.gridx=1;
        c.gridy=0;
        premium.add(l, c);

        l = new JLabel("Number of Orders");
        l.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        c.gridy=1;
        premium.add(l, c);

        l = new JLabel("Total Gallons Delivered");
        l.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        c.gridy=2;
        premium.add(l, c);

        l = new JLabel("Total Gallons Ordered Overage");
        l.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        c.gridy=3;
        premium.add(l, c);

        //values
        c.gridx=2;
        c.gridy=0;
        premium.add(pTotalGallonsOrdered, c);
        c.gridy=1;
        premium.add(pNumberOfOrders,c);
        c.gridy=2;
        premium.add(pTotalGallonsDelivered,c);
        c.gridy=3;
        premium.add(pTotalGallonsOrderedOverage,c);



        //Regular Panel
        var reg = new JPanel();
        reg.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        reg.setLayout(new GridBagLayout());
        c = new GridBagConstraints();

        //word regular
        l = new JLabel("Regular  ");
        c.gridx=0;
        c.gridy=0;
        c.weighty=4;
        c.anchor=GridBagConstraints.LAST_LINE_START;
        reg.add(l, c);

        //middle stats labels
        l = new JLabel("Total Gallons Ordered");
        l.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        c.anchor=GridBagConstraints.LINE_START;
        c.weighty=0;
        c.gridx=1;
        c.gridy=0;
        reg.add(l, c);

        l = new JLabel("Number of Orders");
        l.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        c.gridy=1;
        reg.add(l, c);

        l = new JLabel("Total Gallons Delivered");
        l.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        c.gridy=2;
        reg.add(l, c);

        l = new JLabel("Total Gallons Ordered Overage");
        l.setBorder(BorderFactory.createLineBorder(Color.lightGray, 1));
        c.gridy=3;
        reg.add(l, c);

        //values
        c.gridx=2;
        c.gridy=0;
        reg.add(rTotalGallonsOrdered, c);
        c.gridy=1;
        reg.add(rNumberOfOrders,c);
        c.gridy=2;
        reg.add(rTotalGallonsDelivered,c);
        c.gridy=3;
        reg.add(rTotalGallonsOrderedOverage,c);



        //Master panel
        setLayout(new GridBagLayout());
        c = new GridBagConstraints();



        c.gridy=1;
        add(premium, c);

        c.gridy=2;
        add(reg, c);


    }
}

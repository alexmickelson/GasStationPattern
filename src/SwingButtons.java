import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SwingButtons extends JPanel {

    public SwingButton orderSpeed;
    public SwingButton carArrivalSpeed;
    public SwingButton pumpingSpeed;
    public SwingButton avgGasReq;
    public JButton playPause;

    public void setPlayPause(String s){
        playPause.setText(s);
    }

    public SwingButtons(ActionListener actionListener){
        super();
        playPause = new JButton("Pause");
        playPause.addActionListener(actionListener);

        setLayout(new GridBagLayout());
        var c = new GridBagConstraints();
        setBorder(BorderFactory.createRaisedBevelBorder());


        orderSpeed = new SwingButton("Speed of Order Arriving   ", actionListener);
        carArrivalSpeed = new SwingButton("Car Arrival Frequency   ", actionListener);
        pumpingSpeed = new SwingButton("Speed of Pumping    ", actionListener);
        avgGasReq=new SwingButton("Average Gas Request (gal)    ", actionListener);

        c.ipadx=5;
        c.gridx=0;
        c.gridy=0;
        c.anchor=GridBagConstraints.LINE_END;
        c.ipady=10;
        add(orderSpeed, c);

        c.gridy=1;
        add(carArrivalSpeed, c);

        c.gridy=2;
        add(pumpingSpeed, c);

        c.gridy=3;
        add(avgGasReq, c);

        c.gridx=1;
        c.gridy=0;
        c.insets=new Insets(2, 10, 2, 10);
        add(playPause, c);
    }
}

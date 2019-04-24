import javax.swing.*;
import java.awt.*;

public class SwingButtons extends JPanel {

    private SwingButton orderSpeed;
    private SwingButton carArrivalSpeed;
    private SwingButton pumpingSpeed;
    private SwingButton avgGasReq;

    public SwingButtons(){
        super();
        setLayout(new GridBagLayout());
        var c = new GridBagConstraints();
        setBorder(BorderFactory.createRaisedBevelBorder());


        orderSpeed = new SwingButton("Speed of Order Arriving   ");
        carArrivalSpeed = new SwingButton("Speed of Cars Arriving   ");
        pumpingSpeed = new SwingButton("Speed of Pumping    ");
        avgGasReq=new SwingButton("Average Gas Request (gal)    ");

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
    }
}

import javax.swing.*;
import java.awt.*;

public class SwingStatsGeneral extends JPanel {

    public SwingStatsTank tank;
    public SwingStatsSales sales;
    public SwingStatsCars cars;

    public SwingStatsGeneral(){
        super();
        tank = new SwingStatsTank();
        sales = new SwingStatsSales();
        cars = new SwingStatsCars();

        setLayout(new GridBagLayout());
        var c = new GridBagConstraints();

        //titles
        var name = new JLabel("Tanks");
        name.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        c.fill=GridBagConstraints.HORIZONTAL;
        c.gridx=0;
        c.gridy=0;
        add(name, c);

        name = new JLabel("Sales");
        name.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        c.gridx=1;
        add(name, c);

        name = new JLabel("Cars");
        name.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        c.gridx=2;
        add(name, c);


        //info
        tank.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        c.gridx=0;
        c.gridy=1;
        add(tank, c);

        c.gridx=1;
        c.fill=GridBagConstraints.BOTH;
        sales.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        add(sales, c);

        c.gridx=2;
        c.fill=GridBagConstraints.BOTH;
        cars.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        add(cars, c);
    }
}

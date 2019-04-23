import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class SwingPumpPanel extends JPanel {
    private JTextArea title;
    private JTextArea carDisplay;

    private JTextArea fuelType;
    private JTextArea gallonsRequested;
    private JTextArea gallonsPumped;

    private JTextArea grade85Total;
    private JTextArea grade87Total;
    private JTextArea grade89Total;

    private Border border = BorderFactory.createEmptyBorder(3, 3, 3, 3);
    private int textWidth = 20;

    public void setCarDisplay(String s){
        carDisplay.setText(s);
    }
    public void setRequestedFuel(GradeEnum type){
        switch (type){
            case GRADE_85:
                fuelType.setText("85 Grade");
                break;
            case GRADE_87:
                fuelType.setText("87 Grade");
                break;
            case GRADE_89:
                fuelType.setText("89 Grade");
                break;
        }
    }
    public void setGallonsRequested(double amount){
        gallonsRequested.setText(amount+"");
    }
    public void setGallonsPumped(double amount){
        gallonsPumped.setText(amount+"");
    }

    public void setGrade85Total(double amt){
        grade85Total.setText(amt+" gal");
    }
    public void setGrade87Total(double amt){
        grade87Total.setText(amt+" gal");
    }
    public void setGrade89Total(double amt){
        grade89Total.setText(amt+" gal");
    }


    public SwingPumpPanel(String name){
        super();
        title = new JTextArea(name, 1, textWidth);
        title.setEditable(false);
        title.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));
        carDisplay = new JTextArea("no cars here", 1, textWidth);
        carDisplay.setEditable(false);
        carDisplay.setBorder(BorderFactory.createEmptyBorder(0, 3, 0, 3));

        fuelType = new JTextArea("Requested Fuel: none", 1, textWidth);
        fuelType.setEditable(false);
        gallonsRequested = new JTextArea("Gallons Req: none", 1, textWidth);
        gallonsRequested.setEditable(false);
        gallonsPumped = new JTextArea("Gallons Pumped: none", 1, textWidth);
        gallonsPumped.setEditable(false);

        grade85Total = new JTextArea("Regular Gas Pumped: none", 1, textWidth);
        grade85Total.setEditable(false);
        grade87Total = new JTextArea("Midgrade Gas Pumped: none", 1, textWidth);
        grade87Total.setEditable(false);
        grade89Total = new JTextArea("Premium Gas Pumped: none", 1, textWidth);
        grade89Total.setEditable(false);


        //TRANSACTION STATS PANEL
        var transactionStats = new JPanel();
        transactionStats.setLayout(new GridBagLayout());
        transactionStats.setBorder(border);
        GridBagConstraints c = new GridBagConstraints();

        c.insets=new Insets(0,0,0,0);
        //c.fill=GridBagConstraints.HORIZONTAL;

        c.anchor=GridBagConstraints.CENTER;
        c.gridx=0;
        c.gridy=0;
        var tStats = new JTextArea("Transaction Stats:", 1, textWidth);
        tStats.setEditable(false);
        transactionStats.add(tStats, c);
        c.anchor = GridBagConstraints.LINE_START;
        c.gridy=1;
        transactionStats.add(fuelType, c);
        c.gridy=2;
        transactionStats.add(gallonsRequested, c);
        c.gridy=3;
        transactionStats.add(gallonsPumped, c);


        //PUMP STATS PANEL
        var pumpStats = new JPanel();
        pumpStats.setLayout(new GridBagLayout());
        pumpStats.setBorder(border);
        c = new GridBagConstraints();

        c.insets=new Insets(0,0,0,0);
        //c.fill=GridBagConstraints.HORIZONTAL;

        c.anchor=GridBagConstraints.CENTER;
        c.gridx=0;
        c.gridy=0;
        var pStats = new JTextArea("Transaction Stats:", 1, textWidth);
        pStats.setEditable(false);
        pumpStats.add(pStats, c);

        c.anchor=GridBagConstraints.LINE_START;
        c.gridy=1;
        pumpStats.add(grade85Total, c);
        c.gridy=2;
        pumpStats.add(grade87Total, c);
        c.gridy=3;
        pumpStats.add(grade89Total, c);



        //MASTER PANEL
        setLayout(new GridBagLayout());
        setBorder(border);
        c = new GridBagConstraints();

        c.anchor=GridBagConstraints.LINE_START;
        c.insets=new Insets(2,2,2,2);
        //c.fill=GridBagConstraints.HORIZONTAL;

        c.gridx=0;
        c.gridy=0;
        add(title, c);

        c.gridx=0;
        c.gridy=1;
        add(carDisplay, c);

        c.gridx=0;
        c.gridy=2;
        add(transactionStats, c);

        c.gridx=0;
        c.gridy=3;
        add(pumpStats, c);



    }

}

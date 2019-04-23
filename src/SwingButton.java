import javax.swing.*;
import java.awt.*;

public class SwingButton extends JPanel {
    private JButton up;
    private JButton down;
    private JTextArea stat;

    public void setStat(String stat){
        this.stat.setText(stat);
    }



    public SwingButton (String name){
        super();
        up = new JButton("⇧");
        down = new JButton("⇩");
        stat = new JTextArea("100%");
        stat.setEditable(false);
        var title = new JLabel(name);

        setLayout(new GridBagLayout());
        var c = new GridBagConstraints();

        //title.setBorder(BorderFactory.createLineBorder(Color.black, 1));
        c.gridx=0;
        c.gridy=0;
        c.fill=GridBagConstraints.BOTH;
        c.gridheight=2;
        add(title, c);

        c.gridx=1;
        c.fill=GridBagConstraints.NONE;
        add(stat, c);

        c.gridheight=1;
        c.gridx=2;
        add(up, c);

        c.gridy=1;
        add(down, c);
    }

}

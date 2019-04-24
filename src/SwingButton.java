import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class SwingButton extends JPanel {
    private JButton up;
    private JButton down;
    private JTextArea stat;

    public JButton getUp(){
        return up;
    }
    public JButton getDown(){
        return down;
    }

    public void setStat(String stat){
        this.stat.setText(stat);
    }



    public SwingButton (String name, ActionListener actionListener){
        super();
        up = new JButton("⇧");
        up.addActionListener(actionListener);
        down = new JButton("⇩");
        down.addActionListener(actionListener);
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

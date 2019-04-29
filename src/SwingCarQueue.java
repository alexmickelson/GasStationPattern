import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Vector;

public class SwingCarQueue extends JPanel {
    private Vector<JTextArea> spots;

    public void displayQueue(int size, int maxsize ){
        if (size > 19){
            return;
        }
        for (int i=0; i < maxsize; i++){
            spots.get(i).setBorder(BorderFactory.createRaisedBevelBorder());
        }
        for (int i = maxsize; i < spots.size(); i++){
            spots.get(i).setBorder(BorderFactory.createEmptyBorder());
        }
        for (int i=0; i < size; i++){
            spots.get(i).setText(   "  ______\n" +
                    " /|_||_\\`.__\n" +
                    "(   _    _ _\\\n" +
                    "=`-(_)--(_)-' ");
        }
        for (int i = size; i < spots.size(); i++){
            spots.get(i).setText("");
        }
    }

    public SwingCarQueue(){
        super();
        spots = new Vector<JTextArea>();


        int rows = 10;
        int col = 20;
        int fontsize = 5;
        setLayout(new GridBagLayout());
        var c = new GridBagConstraints();
        var x = 0;
        var y = 0;

        for(int i = 0; i<20; i++){
            var spot = new JTextArea("car space", rows, col);
            spot.setFont(new Font("monospaced", Font.PLAIN, fontsize));
            spot.setBorder(BorderFactory.createRaisedBevelBorder());
            spots.add(spot);

            c.gridx = x++%10;
            c.gridy=y++/10;
            add(spot, c);
        }


    }
}

/*
 *Programmer : Amal Assem Dora 
*experience :1 Year in Java programming language
*Education : third year in computer science departement in faculty of electronic engineering
*Minesweaper Compitition 2019 for IEEEMSB
*/
package ieee;

import static java.awt.Frame.MAXIMIZED_HORIZ;
import static java.awt.Frame.MAXIMIZED_VERT;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;

public class Minesweaper {

    public static void main(String[] args) throws InterruptedException {
       // int delay =30;  //in minutes
       // TimeUnit.MINUTES.sleep(delay);
        JFrame frame = new JFrame(" Minsweeper IEEE Team ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Paint paint = new Paint();
        frame.add(paint);
        frame.setSize(MAXIMIZED_HORIZ, MAXIMIZED_VERT);
        frame.setVisible(true);

    }

}

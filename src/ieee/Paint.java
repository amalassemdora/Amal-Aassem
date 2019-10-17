package ieee;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javax.swing.JPanel;

public class Paint extends JPanel {

    public void paintComponent(Graphics g) {
        int wdRec = 20, StartMap = 20, EndMap = 400, u = 0;    //width of rectang
        String x[] = new String[1000];
        String y[] = new String[1000];
        String z[] = new String[1000];
        ArrayList<String> total = new ArrayList<>();
        super.paintComponent(g);
        for (int j = StartMap; j < EndMap; j += wdRec) {
            for (int i = StartMap; i < EndMap; i += wdRec) {
                g.drawRect(j, i, wdRec, wdRec);
            }
        }
        //**************************drow axis*************************
        Integer j = 19;
        int asciinum = 65;
        for (int k = 1; k < wdRec; k++) {
            g.drawString(j.toString(), 0, (k * wdRec) + 14);
            g.drawString(Character.toString((char) asciinum), (k * wdRec) + 8, (20 * wdRec) + 15);  //convert number into letter
            asciinum++;
            j--;
        }
        //tables
        int surfacetableStart = 480, surfacetableEnd = 620, BuriedTableStart = 700, BuriedTableEnd = 620, WdTable = 142, Wdcell = 70, SurStart = 40, BurStart = 40;

        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(surfacetableStart, wdRec, WdTable, wdRec);
        g.setColor(Color.BLACK);
        g.drawString("Surface mines found at:", surfacetableStart, 35);
        g.setColor(Color.BLACK);
        g.fillRect(BuriedTableStart, wdRec, WdTable, wdRec);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString("Buried mines found at:", BuriedTableStart, 35);

        //***************************avoid duplicate in data************************
        //*******************read file********************
        String csvFile = "Data.csv";
        String line;
        String cvsSplitBy = ",";
        String number = "";
        String XDir, YDir;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            double XReal, YReal;
            int Xbefore = 0, Ybefore = 0, X = 0, Y = 0;
            br.readLine();
            while ((line = br.readLine()) != null) {
                // use comma as separator
                String[] country = line.split(cvsSplitBy);
                try {
                    XReal = Double.parseDouble(country[1]);
                    YReal = Double.parseDouble(country[2]);
                    Xbefore = (int) XReal;
                    Ybefore = (int) YReal;
                    XDir = Integer.toString(Xbefore);
                    YDir = Integer.toString(Ybefore);
                    X = Xbefore * 20;
                    Y = Ybefore * 20;
                    Xbefore += 65;     //convert to char by asciicode
                    number = Integer.toString(Ybefore + 1);
                    String ch = Character.toString((char) Xbefore);
                    x[u] = XDir;
                    y[u] = YDir;
                    z[u] = country[4];
                    total.add(x[u] + "," + y[u] + "," + z[u]);
                    u++;
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
            List<String> newList = total.stream().distinct().collect(Collectors.toList());
            FileWriter writer = new FileWriter("output1111.txt");
            for (String str : newList) {
                writer.write(str + System.lineSeparator());
            }
            System.out.println("the file convert successfully .");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //surface mine
        System.out.println("the start of design");
        String csvFile1 = "output1111.txt";
        String line1;
        String cvsSplitBy1 = ",";
        String Num;
        int count1 = 0, count2 = 0;
        double XReal, YReal;
        int Xbefore = 0, Ybefore = 0, X = 0, Y = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile1))) {

            while ((line1 = br.readLine()) != null) {
                String[] country2 = line1.split(cvsSplitBy1);
                XReal = Double.parseDouble(country2[0]);
                YReal = Double.parseDouble(country2[1]);
                Xbefore = (int) XReal;
                Ybefore = (int) YReal;
                X = Xbefore * 20;
                Y = Ybefore * 20;
                Xbefore += 65;     //convert to char by asciicode
                number = Integer.toString(Ybefore + 1);
                String ch = Character.toString((char) Xbefore);

                if (country2[2].equals("0")) {

                    //****************fill the map****************
                    g.setColor(Color.lightGray);
                    g.fillRect(X+ wdRec, 380 - Y, wdRec, wdRec);
                    
                    //****************fill the table****************
                    g.setColor(Color.BLACK);
                    g.drawRect(surfacetableStart, SurStart, Wdcell, wdRec);
                    g.drawString(Character.toString((char) Xbefore), 510, 55 + (count1 * 20));
                    g.drawRect(surfacetableStart + Wdcell, SurStart, Wdcell, wdRec);
                    g.drawString(number, 580, 55 + (count1 * 20));

                    //*********************update*******************
                    SurStart += wdRec;
                    count1++;
                } else {
                    //****************fill the map****************
                    g.setColor(Color.black);
                    g.fillRect(X + wdRec, 380 - Y, wdRec, wdRec);

                    //****************fill the table*****************
                    g.drawRect(BuriedTableStart, BurStart, Wdcell, wdRec);
                    g.drawString(Character.toString((char) Xbefore), 725, 55 + (count2 * 20));
                    g.drawRect(BuriedTableStart + Wdcell, BurStart, Wdcell, wdRec);
                    g.drawString(number, 795, 55 + (count2 * 20));

                    //*********************update*******************
                    BurStart += wdRec;
                    count2++;
                }

            }
        } catch (IOException ex) {
            Logger.getLogger(Paint.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

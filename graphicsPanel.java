package bitirme_tezi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class graphicsPanel  extends JPanel {
    String liquid_name;

    public graphicsPanel() {

    }
    public JPanel metot1(String name){
        String my_liquid_name=name; // JComboBox'tan çekilen isim
        JPanel panel = new JPanel();
        panel.setBackground(Color.cyan);
        xy_grafik grafik1 = new xy_grafik("deneme", "deneme");  // Burası bir panel elementidir.
        liquid_values values = new liquid_values();
        double cp_c[]= values.getcp(my_liquid_name);
        double Tmin= cp_c[4];
        double Tmax= cp_c[5];
        ArrayList<double[]> x = new ArrayList<double[]>();
        ArrayList<double[]> y = new ArrayList<double[]>();

        liquids liquid = new liquids();


        double x_values []= new double[20];
        double y_values []= new double[20];
        double T=Tmin;
        for(int i =0;i<20;i++)  {
            T = Tmin+ (Tmax-Tmin)/20*i;
            x_values[i] = T;
            y_values[i] = liquid.cp(my_liquid_name);
        }
        x.add(x_values);
        y.add(y_values);

        grafik1.setValues(x,y);
        grafik1.repaint();


         return panel;

    }

    public static void main( String args[]) {
        JFrame frame = new JFrame();
        graphicsPanel panel = new graphicsPanel();
        liquid_names liquid_names = new liquid_names();
        String isimler[] = liquid_names.get_names();
        System.out.println(isimler[0]);
        JComboBox<String> isim_listesi = new JComboBox<String>(isimler);
        JPanel panel2 = panel.metot1("CO");
        panel2.setBounds(300,300,400,400);

        isim_listesi.setBounds(250,25,150,25);

        frame.add(isim_listesi);
        frame.add(panel2);
        frame.setSize(700,700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}

package bitirme_tezi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class grafikPanel_last extends JPanel   {
    String liquid_name ;



    public grafikPanel_last(){
        setLayout(null);
        setSize(1000,1000);
        setBackground(Color.white);
        liquid_names liquid_names = new liquid_names();
        String isimler[] = liquid_names.get_names();
        liquid_name = isimler[0];
        JComboBox<String> isim_listesi = new JComboBox<String>(isimler);
        isim_listesi.setBounds(300,50,150,20);
        this.add(isim_listesi);
        xy_grafik g_vis = new xy_grafik("Temperature(K)","Viskozite(Pa.s)");
        g_vis.setBounds(400,100,300,300);
        this.add(g_vis);
        xy_grafik g_st= new xy_grafik("Temp(K)","Yüzey gerilimi(N/m)");
        g_st.setBounds(50,100,300,300);
        this.add(g_st);
        xy_grafik g_k= new xy_grafik("Temp(K)","Isıl iletk. kats. (W/(mK))");
        g_k.setBounds(750,100,300,300);
        this.add(g_k);
        xy_grafik g_ro= new xy_grafik("Temp (K)","Yogunluk(kg/m^3)");
        g_ro.setBounds(50,450,300,300);
        this.add(g_ro);
        xy_grafik g_cp= new xy_grafik("Temp (K)","Özgül ısı (kJ/kmol/K");
        g_cp.setBounds(400,450,300,300);
        this.add(g_cp);

        ActionListener ac_lis = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                liquid_name=(String) isim_listesi.getSelectedItem();
                g_vis.createGraphic(liquid_name,"viscosity");
                g_st.createGraphic(liquid_name,"surface tension");
                g_k.createGraphic(liquid_name,"ısıl iletkenlik");
                g_ro.createGraphic(liquid_name,"yoğunluk");
                g_cp.createGraphic(liquid_name,"özgül ısı");


            }
        };
        isim_listesi.addActionListener(ac_lis);





    }

    public static void main(String[] args) {

        grafikPanel_last g = new grafikPanel_last(); // Panel
        JFrame frame = new JFrame(" Grafik Paneli");
        frame.setSize(1000,1000);
        frame.add(g);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }

}

package bitirme_tezi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class grafikPanel_last extends JPanel   {
    String liquid_name ;
    xy_grafik g_vis,g_st,g_k,g_ro,g_cp;
    double P=100;
    public grafikPanel_last(){
        setLayout(null);
        setSize(1000,1000);
        setBackground(Color.white);
        liquid_names liquid_names = new liquid_names();
        String isimler[] = liquid_names.get_names();
        liquid_name = isimler[0];
        JComboBox<String> isim_listesi = new JComboBox<String>(isimler);
        //isim_listesi.setEditable(true);
        isim_listesi.setBounds(300,50,150,20);
        this.add(isim_listesi);
        JLabel label_Pressure = new JLabel("P(kPa):");
        label_Pressure.setBounds(500,50,50,20);
        this.add(label_Pressure);
        JTextField field_P = new JTextField();
        field_P.setText(""+P);
        field_P.setBounds(570,50,50,20);
        this.add(field_P);

        g_vis = new xy_grafik("Temperature(K)","Viskozite(Pa.s)");
        g_vis.setBounds(50,100,400,300);
        this.add(g_vis);
        g_st= new xy_grafik("Temperature(K)","Yüzey gerilimi(N/m)");
        g_st.setBounds(500,100,400,300);
        this.add(g_st);
        g_k= new xy_grafik("Temperature(K)","Isıl iletk.kats.(W/(mK))");
        g_k.setBounds(950,100,400,300);
        this.add(g_k);
        g_ro= new xy_grafik("Temperature(K)","Yogunluk(kg/m^3)");
        g_ro.setBounds(50,450,400,300);
        this.add(g_ro);
        g_cp= new xy_grafik("Temperature(K)","Özgül ısı (kJ/kmol/K");
        g_cp.setBounds(500,450,400,300);
        this.add(g_cp);

        ActionListener ac_lis = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                liquid_name=(String) isim_listesi.getSelectedItem();
                P=Double.parseDouble(field_P.getText());
                g_vis.createGraphic(liquid_name,"viscosity",P);
                g_st.createGraphic(liquid_name,"surface tension",P);
                g_k.createGraphic(liquid_name,"ısıl iletkenlik",P);
                g_ro.createGraphic(liquid_name,"yoğunluk",P);
                g_cp.createGraphic(liquid_name,"özgül ısı",P);
            }
        };
        isim_listesi.addActionListener(ac_lis);
        field_P.addActionListener(ac_lis);
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

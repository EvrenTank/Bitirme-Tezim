package bitirme_tezi;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;

public class liquids_Table extends JPanel{
    String liquid_name="CO";
    JLabel label;
    JTable table;
    Object row[][]={{1,2,3,4},{4,5,6,7}};
    JScrollPane sp;
    JTextField field_T,field_P;
    String T="300.0";
    String P="100.0";//kPa
    public liquids_Table()
    {

        JLabel label_T=new JLabel("T:");
        JLabel label_P=new JLabel("P:");


        field_T=new JTextField(""+T,10);
        field_T.setBackground(this.getBackground());

        field_P=new JTextField(""+P,10);
        field_P.setBackground(this.getBackground());
        //field.setSelectedTextColor(Color.yellow);
        //field.setForeground(Color.cyan);








        String [] isimler= {"H2O","CBrClF2","CBrCl3","CBrF3","CBr2F2","CClF3","CClN","CCl2F2","CCl2O","CCl3F","CCl4","CF2O","CF4","CHBr3",
                "CHClF2","CO","CO2","CH4","C2H6","NH3","C3H8O3","CH3OH","Bi","Hg","Pb","Ag","AgCl","AgI","Al","AlBr3","Al2O3","AsF3",
                "AsCl3","Au","B","Ba","Be","BeF2","BiBr3","BiCl3","Na","CHCl2F","CHCl3","CHF3","CHI3","CHN","CHNS","CH2BrCI","СН2Вr2",
                "CH2ClF","CH2Cl2","CH2F2","CH2I2","СН2O","CH2O2","CH3Br","CH3Cl","CH3Cl3Si","CH3F",
                "СН3I","CH3NO","CH3NO2NITROMETHANE","CH3NO2METHYLNITRITE","CH3NO3","CH4","CH4Cl2Si",
                "CH4O","CH4O3S","CH4S","CH5ClSi","CH5N","CH6Si","CN4O8","CO","COS","CO2","CS2","C2BrF3","C2Br2F4","C2ClF3",
                "C2ClF5","C2Cl2F4","C2Cl3F3","C2Cl4","C2Cl4F2","C2Cl4O","C2Cl6","C2F4",
                "C2F6","C2HBrClF3","C2HClF2","C2HCl3","C2HCl3O_DICHLOROACETYLCHLORIDE",	"C2HCl3O_TRICHLOROACETALDEHYDE","C2HCl5","C2HF3",
                "C2HF3O2","C2HF5","C2H2","C2H2Br4","C2H2Cl2_11","C2H2Cl2_cis12","C2H2Cl2_trans12","C2H2Cl2O_CHLOROACETYLCHLORIDE",
                "C2H2Cl2O_DICHLOROACETALDEHYDE","C2H2Cl2O2","C2H2Cl3F","C2H2Cl4_1112","C2H2Cl4_1122","C2H2F2_11","C2H2F2_cis12",
                "C2H2F2_trans12","C2H2F4","C2H2O","C2H2O4","C2H3Br","C2H3Cl","C2H3ClF2","C2H3ClO_ACETYLCHLORIDE","C2H3ClO_CHLOROACETALDEHYDE",
                "C2H3ClO2_CHLOROACETICACID","C2H3ClO2_METHYLCHLOROFORMATE","C2H3Cl3_111","C2H3Cl3_112",
                "C2H3F","C2H3F3","C2H3N","C2H3NO","C2H4","С2Н4Вr2_11","С2Н4Вr2_12"};
        JComboBox <String> isim_listesi=new JComboBox<String>(isimler);

        label=new JLabel(" Label ");
        //label.setSize(100,100); 7
        this.add(label);



        isim_listesi.setBounds(200, 100, 100, 50);
        this.add(isim_listesi);
        this.add(label_T);
        this.add(field_T);
        this.add(label_P);
        this.add(field_P);


        String column[]= {"Özellik"," Değer"," Birim"," Uygun Sıcaklık Aralığı"};

        table=new JTable(row, column);
        //table.setBackground(Color.red);
        //this.add(table);

        sp=new JScrollPane(table);

        this.add(sp);
        liquids liquid=new liquids();

        //System.out.print(this.getClass().getName());


        ActionListener ac_lis=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                liquids_Table.this.remove(sp);
                T=(field_T.getText());
                P=(field_P.getText());
                liquid_name=(String) isim_listesi.getSelectedItem();
                //label.setText(this.getLayout().getClass().getName());
                label.setText(liquid_name);
                row=liquid.calculate_values_for_pure(liquid_name,Double.parseDouble(T),Double.valueOf(P));
                table=new JTable(row, column);
                sp=new JScrollPane(table);
                sp.setSize(100, 400);
                liquids_Table.this.add(sp);
                liquids_Table.this.revalidate();
//		        /*Object comp[]=this.getComponents();
//		        for(int i=0;i<comp.length;i++) {
//		        	System.out.println(comp[i]);
//		        }*/

            }
        };

        field_P.addActionListener(ac_lis);
        field_T.addActionListener(ac_lis);
        isim_listesi.addActionListener(ac_lis);






    }










    public static void main(String args[]) {

        JFrame frame=new JFrame(" Sıvıların Gazların Özellikleri:  ");
        JTabbedPane tabbedPane=new JTabbedPane(JTabbedPane.TOP);
        liquids_Table panel=new liquids_Table();

        liquids_Table_mix panel2=new liquids_Table_mix();


        tabbedPane.addTab("Saf Sıvılar",panel);
        tabbedPane.addTab("Karışım",panel2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(670, 600);
        frame.add(tabbedPane);
        frame.setVisible(true);

    }

}

package bitirme_tezi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.*;

public class liquids_Table_mix extends JPanel{
    String liquid_name1="CO";
    String liquid_name2="CO";
    JLabel label_l1,label_l2,label_T,label_P,label_m1,label_m2;// label_l1,label_l2 mean liquid1 and liquid2
    JTable table;
    Object row[][]={{1,2,3,4},{4,5,6,7}};
    JScrollPane sp;
    JTextField field_T,field_P,field_m1,field_m2;
    String T="300.0";
    String P="100.0";//kPa
    double m1,m2;

    public liquids_Table_mix()
    {
//         this.setMinimumSize(new Dimension(700,500));
         label_T=new JLabel("T:");
         label_P=new JLabel("P:");
         label_m1=new JLabel("m1");
         label_m2=new JLabel("m2");
         //label_w1=new JLabel("m1");
         //label_w2=new JLabel("m2");


        field_m1=new JTextField("1",5);
        field_m2=new JTextField("1",5);
       // field_w1=new JTextField(5);
//        field_w2=new JTextField(5);


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
        JComboBox <String> isim_listesi2=new JComboBox<String>(isimler);

        label_l1=new JLabel(" Label L1 ");
        //label.setSize(100,100); 7
        this.add(label_l1);

        label_l2=new JLabel("Label L2");



        isim_listesi.setBounds(200, 100, 120, 150);
        isim_listesi2.setBounds(200, 100, 120, 150);
        this.add(isim_listesi);
        this.add(label_l2);
        this.add(isim_listesi2);
        this.add(label_T);
        this.add(field_T);
        this.add(label_P);
        this.add(field_P);
        this.add(label_m1);
        this.add(field_m1);
        this.add(label_m2);
        this.add(field_m2);
//        this.add(label_w1);
//        this.add(field_w1);
//        this.add(label_w2);
//        this.add(field_w2);

        String column[]= {"Özellik"," Değer"," Birim"};

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
                liquids_Table_mix.this.remove(sp);
                T=(field_T.getText());
                P=(field_P.getText());
                m1=Double.parseDouble(field_m1.getText());
                m2=Double.parseDouble(field_m2.getText());
                if(liquid_name2==null){
                    liquid_name2="CO";
                }
                if(liquid_name1==null){
                    liquid_name1="CO";
                }
                liquid_name1=(String) isim_listesi.getSelectedItem();
                liquid_name2=(String) isim_listesi2.getSelectedItem();
                //label.setText(this.getLayout().getClass().getName());
                label_l1.setText(liquid_name1);
                label_l2.setText(liquid_name2);
                row=liquid.calculate_values_for_mixtures(liquid_name1,liquid_name2,Double.parseDouble(T),m1,m2);
                table=new JTable(row, column);
                sp=new JScrollPane(table);
                sp.setSize(100, 400);
                liquids_Table_mix.this.add(sp);
                liquids_Table_mix.this.revalidate();
//		        /*Object comp[]=this.getComponents();
//		        for(int i=0;i<comp.length;i++) {
//		        	System.out.println(comp[i]);
//		        }*/

            }
        };

        field_P.addActionListener(ac_lis);
        field_T.addActionListener(ac_lis);
        isim_listesi.addActionListener(ac_lis);
        isim_listesi2.addActionListener(ac_lis);
        field_m1.addActionListener(ac_lis);
        field_m2.addActionListener(ac_lis);





    }












}

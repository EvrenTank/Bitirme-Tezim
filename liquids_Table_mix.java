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

     liquid_names l_names= new liquid_names();
     String isimler [] = l_names.get_names();








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

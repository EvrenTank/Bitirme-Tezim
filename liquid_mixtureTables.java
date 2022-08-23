package bitirme_tezi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

public class liquid_mixtureTables extends JPanel {
    liquids sample=new liquids();
    String liquid_name1="CO";
    String liquid_name2="CO";
    JLabel label_l1,label_l2,label_T,label_P,label_m1,label_m2;// label_l1,label_l2 mean liquid1 and liquid2
    JTable table;
    JLabel l,l2; JComboBox<String> c; JTextField t;
    Object row[][]={{1,2,3,4},{4,5,6,7}};
    JScrollPane sp;
    JTextField field_T,field_P,field_m1,field_m2;
    String T="300.0";
    String P="100.0";//kPa
    double m1,m2;
    ArrayList<JLabel> labels=new ArrayList<JLabel>();
    ArrayList<JLabel> labels2=new ArrayList<JLabel>();
    ArrayList<JComboBox> comboboxes=new ArrayList<JComboBox>();
    ArrayList<JTextField> textfields=new ArrayList<JTextField>();
    public liquid_mixtureTables()
    {
        GridBagLayout layout=new GridBagLayout();
        GridBagConstraints a=new GridBagConstraints();
        label_T=new JLabel("T:");
        field_T=new JTextField(""+T,10);
        field_T.setBackground(this.getBackground());

        liquid_names l_names= new liquid_names();
        String isimler [] = l_names.get_names();
        this.setLayout(layout);
        JPanel panel_compounds=new JPanel();// Bu paneli malzeme sayısına göre oluşturulacak olan alanlar için kullanacağım. GridLayout yaparak
        // direkt olarak elemanları buna ekleyip, bu paneli de ana panele ekleyeceğim.


        JLabel label=new JLabel("Karışımdaki bileşen sayısını giriniz:");
        Border blackline=BorderFactory.createLineBorder(Color.BLACK);
        JRadioButton rb1=new JRadioButton("Molar orana göre hesapla");
        JRadioButton rb2=new JRadioButton("Kütlesel orana göre hesapla");
        ButtonGroup buttonGroup=new ButtonGroup();
        buttonGroup.add(rb1);
        buttonGroup.add(rb2);
        rb1.setSelected(true);

        label.setBorder(blackline);
        JTextField field=new JTextField(10);
        JButton hesapla_butonu=new JButton("Hesapla");
        hesapla_butonu.setSize(400,20);
        a.gridheight=1;
        a.gridx=4;a.gridy=0;a.gridwidth=1;
        this.add(hesapla_butonu,a);
        a.ipady=0;
        a.gridx=0;
        a.gridy=0;
        a.gridwidth=1;
        a.insets=new Insets(15,5,1,0);
        this.add(label,a);
        a.gridx=1;
        a.gridy=0;
        a.gridwidth=1;
        a.insets=new Insets(15,5,1,0);
        System.out.println(a.fill);
        this.add(field,a);
        a.gridx=3;
        a.gridy=0;
        a.gridwidth=1;
        this.add(label_T,a);
        a.gridx=2;
        a.gridy=0;
        a.gridwidth=1;
        this.add(field_T,a);
        ActionListener radiolistener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(rb1.isSelected()){
                    for(int i=0;i<labels2.size();i++){
                        labels2.get(i).setText((i+1)+".malzeme mol sayısı:");
                    }
                }
                else if(rb2.isSelected()){
                    for(int i=0;i<labels2.size();i++){
                        labels2.get(i).setText((i+1)+".malzeme kütlesi:");
                    }
                }
            }
        };
        rb1.addActionListener(radiolistener);
        rb2.addActionListener(radiolistener);
        a.gridx=1;
        a.gridy=1;
        a.gridwidth=1;
        System.out.println(a.fill);
        a.insets=new Insets(15,5,10,0);
        this.add(rb1,a);

        a.gridx=2;
        a.gridy=1;
        a.gridwidth=1;
        System.out.println(a.insets);
        this.add(rb2,a);

        ActionListener get_values=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String liquid_names[] = new String[comboboxes.size()];
                double mass[] = new double[comboboxes.size()];
                for(int i=0;i<comboboxes.size();i++){
                    liquid_names[i] = labels.get(i).getText();
                    mass[i] = Double.parseDouble(textfields.get(i).getText());



                 }
                sample.calculate_values_for_mixtures2(liquid_names,mass,Double.parseDouble(T));

            }
        };

        hesapla_butonu.addActionListener(get_values);










        field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int compound_number=Integer.parseInt(field.getText());
                Component[] components=liquid_mixtureTables.this.getComponents();
                //field.setBackground(Color.red);  Bu çalışıyor.

                for(int j=4;j<components.length;j++){
                    liquid_mixtureTables.this.remove(components[j]);
                }

                labels.clear();
                comboboxes.clear();
                textfields.clear();
                labels.clear();
                System.out.println("Deneme");
                for(int i=0;i<compound_number;i++){
                    a.gridy=i+5;
                    a.gridx =1;
                    a.gridwidth=2;
                    c=new JComboBox<String>(isimler);
                    c.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String liquid_name=(String)((JComboBox)e.getSource()).getSelectedItem();
                            for(int i=0; i<comboboxes.size();i++ ){
                                if( e.getSource() == comboboxes.get(i)){
                                    labels.get(i).setText(liquid_name);
                                }
                            }
                        }
                    });
                    comboboxes.add(c);
                    liquid_mixtureTables.this.add(c,a);
                    a.gridx =0;
                    a.gridwidth=1;
                    l=new JLabel((String)c.getSelectedItem());
                    labels.add(l);
                    liquid_mixtureTables.this.add(l,a);
                    a.gridx =3;
                    a.gridwidth=1;
                    l2=new JLabel("x"+(i+1));
                    labels2.add(l2);
                    liquid_mixtureTables.this.add(l2,a);
                    a.gridx =4;
                    a.gridwidth=1;
                    t=new JTextField(5);
                    t.setText("1");
                    textfields.add(t);
                    liquid_mixtureTables.this.add(t,a);

                }
                for(int a=0;a< labels.size();a++){
                    System.out.println(labels.get(a));
                    System.out.println(comboboxes.get(a));
                    System.out.println(textfields.get(a));
                }
                liquid_mixtureTables.this.repaint();

                liquid_mixtureTables.this.revalidate();
            }
        });
//         this.setMinimumSize(new Dimension(700,500));
        label_P=new JLabel("P:");
        label_m1=new JLabel("m1");
        label_m2=new JLabel("m2");
        //label_w1=new JLabel("m1");
        //label_w2=new JLabel("m2");
        field_m1=new JTextField("1",5);
        field_m2=new JTextField("1",5);

        field_P=new JTextField(""+P,10);
        field_P.setBackground(this.getBackground());
        //field.setSelectedTextColor(Color.yellow);
        //field.setForeground(Color.cyan);
        JComboBox <String> isim_listesi=new JComboBox<String>(isimler);
        JComboBox <String> isim_listesi2=new JComboBox<String>(isimler);
        label_l1=new JLabel(" Label L1 ");
        //label.setSize(100,100); 7
//        this.add(label_l1);
        label_l2=new JLabel("Label L2");
        isim_listesi.setBounds(200, 100, 120, 150);
        isim_listesi2.setBounds(200, 100, 120, 150);
//        this.add(isim_listesi);
//       this.add(label_l2);
//        this.add(isim_listesi2);
//        this.add(label_P);
//        this.add(field_P);
//        this.add(label_m1);
//        this.add(field_m1);
//        this.add(label_m2);
//        this.add(field_m2);
//        this.add(label_w1);
//        this.add(field_w1);
//        this.add(label_w2);
//        this.add(field_w2);
        String column[]= {"Özellik"," Değer"," Birim"};
        table=new JTable(row , column);
        //table.setBackground(Color.red);
        //this.add(table);
        sp=new JScrollPane(table);
//        this.add(sp);
        liquids liquid=new liquids();
        //System.out.print(this.getClass().getName());
        ActionListener ac_lis=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                liquid_mixtureTables.this.remove(sp);
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
                liquid_mixtureTables.this.add(sp);
                liquid_mixtureTables.this.revalidate();
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


    public static void main(String[] args) {

        liquid_mixtureTables t1=new liquid_mixtureTables();
        JFrame frame=new JFrame("Mixture için olan table");
        frame.setSize(800,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(t1);
        frame.setVisible(true);

    }



}

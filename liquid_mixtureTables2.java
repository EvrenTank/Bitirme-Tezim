package bitirme_tezi;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;

public class liquid_mixtureTables2 extends JPanel {
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
    public liquid_mixtureTables2()
    {

        label_T=new JLabel("T(K):");
        label_T.setBounds(20,100,30,30);
        this.add(label_T);
        field_T=new JTextField(""+T,10);
        field_T.setBounds(60,100,50,30);
        Border blackline=BorderFactory.createLineBorder(Color.BLACK);
        field_T.setBorder(blackline);
        this.add(field_T);

        liquid_names l_names= new liquid_names();
        String isimler [] = l_names.get_names();
        this.setLayout(null);
        JPanel panel_compounds=new JPanel();// Bu paneli malzeme sayısına göre oluşturulacak olan alanlar için kullanacağım. GridLayout yaparak
        // direkt olarak elemanları buna ekleyip, bu paneli de ana panele ekleyeceğim.

        String column[]= {"Şey"," Value"," Unit","Available temp. range"};
        table=new JTable(row , column);


        JLabel label=new JLabel("Enter the comp. numbers in the mixture:");
/*        JRadioButton rb1=new JRadioButton("Molar orana göre hesapla");
        JRadioButton rb2=new JRadioButton("Kütlesel orana göre hesapla");
        ButtonGroup buttonGroup=new ButtonGroup();
        //rb1.setBounds(50,180,75,30);

        buttonGroup.add(rb1);
        buttonGroup.add(rb2)
        rb1.setSelected(true);*/
        JLabel label_P=new JLabel("P(kPa):");
        field_P = new JTextField(""+P,10);
        label_P.setBounds(120,100,40,30);
        field_P.setBounds(160,100,50,30);
        this.add(label_P);
        this.add(field_P);
        label.setBorder(blackline);
        label.setBounds(220,100,200,30 );
        this.add(label);
        JTextField field=new JTextField(10);
        field.setBounds(440,100,30,30);
        this.add(field);
        sp=new JScrollPane(table);
        sp.setBounds(600,20,500,700);
        liquid_mixtureTables2.this.add(sp);


        JButton hesapla_butonu=new JButton("Hesapla butonu");
        hesapla_butonu.setBounds(250,150,100,20);
        this.add(hesapla_butonu);


/*        ActionListener radiolistener=new ActionListener() {
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
*/




        ActionListener get_values=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                T=field_T.getText();
                P=field_P.getText();
                System.out.println(P);
                String liquid_names[] = new String[comboboxes.size()];
                double mole[] = new double[comboboxes.size()];
                for(int i=0;i<comboboxes.size();i++){
                    liquid_names[i] = labels.get(i).getText();
                    mole[i] = Double.parseDouble(textfields.get(i).getText());
                }
                row=sample.calculate_values_for_mixtures2(liquid_names,mole,Double.parseDouble(T),Double.parseDouble(P));
                liquid_mixtureTables2.this.remove(sp);
                table=new JTable(row , column);
                sp=new JScrollPane(table);
                sp.setBounds(600,20,500,700);
                liquid_mixtureTables2.this.add(sp);
                //liquid_mixtureTables2.this.repaint();
                liquid_mixtureTables2.this.revalidate();


                //System.out.println("T="+T);

            }
        };

        hesapla_butonu.addActionListener(get_values);











        field.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int compound_number=Integer.parseInt(field.getText());
                Component[] components=liquid_mixtureTables2.this.getComponents();
                //field.setBackground(Color.red);  Bu çalışıyor.

                for(int j=8;j<components.length;j++){
                    liquid_mixtureTables2.this.remove(components[j]);
                }

                labels.clear();
                comboboxes.clear();
                textfields.clear();
                labels.clear();
                //System.out.println("Deneme");
                int x=20;int y=180;
                for(int i=0;i<compound_number;i++){

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
                    c.setBounds(x+105,y+30*i,300,20);
                    comboboxes.add(c);
                    liquid_mixtureTables2.this.add(c);

                    l=new JLabel((String)c.getSelectedItem());
                    l.setBounds(x,y+30*i,100,20);
                    labels.add(l);
                    liquid_mixtureTables2.this.add(l);

                    l2=new JLabel("Mol sayısı:");
                    l2.setBounds(x+410,y+30*i,70,20);
                    labels2.add(l2);
                    liquid_mixtureTables2.this.add(l2);

                    t=new JTextField();
                    t.setText("1");
                    t.setBounds(x+470,y+30*i,45,20);
                    textfields.add(t);
                    liquid_mixtureTables2.this.add(t);

                }
               /* for(int a=0;a< labels.size();a++){
                    System.out.println(labels.get(a));
                    System.out.println(comboboxes.get(a));
                    System.out.println(textfields.get(a));
                }
*/
//                sp.setBounds(20,150+compound_number*30+30,500,700);


                liquid_mixtureTables2.this.repaint();

                liquid_mixtureTables2.this.revalidate();
            }
        });
//         this.setMinimumSize(new Dimension(700,500));
        label_P=new JLabel("P:");
        label_m1=new JLabel("m1");
        label_m2=new JLabel("m2");
        //label_w1=new JLabel("m1");
        //label_w2=new JLabel("m2");
        field_m1=new JTextField("1",5); // mol sayilarini girmek icin olan alan
        field_m2=new JTextField("1",5); // mol sayilarini girmek için olan alan


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

        //table.setBackground(Color.red);
        //this.add(table);
//        this.add(sp);
        liquids liquid=new liquids();
        //System.out.print(this.getClass().getName());
/*        field_P.addActionListener(ac_lis);
        field_T.addActionListener(ac_lis);
        isim_listesi.addActionListener(ac_lis);
        isim_listesi2.addActionListener(ac_lis);
        field_m1.addActionListener(ac_lis);
        field_m2.addActionListener(ac_lis);*/
    }


    public static void main(String[] args) {

        liquid_mixtureTables2 t1=new liquid_mixtureTables2();
        JFrame frame=new JFrame("Mixture için olan table");
        frame.setSize(1200,800);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(t1);
        frame.setVisible(true);

    }



}

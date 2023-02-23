package bitirme_tezi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;

//  0. T(Celsius)
//  1.T(Kelvin)                      //  2.Pressure(MPa)                 //  3.Density liquid (kg/m^3)      //  4.Specific volume vapor(m^3/kg)      //  5.Liquid enthalpy( kJ/kg)
//  6.Vapor enthalpy(kJ/kg)          //  7.Liquid entropy(kJ/(kgK))      //  8.Vapor entropy(kJ/(kgK))      //  9.Liquid specific heat (kJ/(kgK))    //  10.Vapor specific heat (kJ/(kgK))
// 11.cp/cv   (Birimi yok.)          //  12.Liquid vel. of sound (m/s)	 //  13.Vapor vel. of sound (m/s)   //  14.Liquid  viscosity ( mikroPa.s)    //  15.Vapor viscosity ( mikroPa.s)
// 16.Liquid therm. cond. ( mW/mK))  //17.Vapor therm. cond. ( mW/mK))	 //18.Surface  Tension ( mN/m)      //  19. T( C )

// Yukaridaki birimler tablolarda olan birimleri gosteriyor. Ama metotlard acogunluk olarak kullanilan birimler neyse bu birimler de onlara donusturulecek ve
// hata analizi icin olusturulan tablolarda bu birimler kullanilacak.

public class createTable_for_comparision extends JPanel {

    /* Bu sınıfta özelliklerin hesaplanması için kullanılan farklı yöntemlerden elde edilen
    değerler tablo halinde verilecek ve kıyaslamaları yapılacaktır.
     */
    JTable table;
    JLabel label = new JLabel("");
    /*String liquids[] = { "CH4_methane","C3H8_propane","C3H6_propylene","CF4_carbontetrafluoride","CCl4_carbontetrachloride","C3H6O_acetone","CH4O_methanol","C4H10_butane","C4H10_isobutane","C7H16_heptane","C3H8O3_glycerol",
                         "CHCl3_chloroform","CH3Cl_methylchloride","C6H6_benzene","C2H6_ethane","C2H6O_ethylalcohol","CO2_carbondioxide","C7H8_toluene","C8H18_octane","C9H20_nonane","C10H22_decane","Ar_argon","Br2_bromine",
                         "N2_nitrogen","NH3_ammonia","O2_oxygen","He_helium4",
                         "Hg_mercury","H2O2_hydrogenperoxide","Bi_bismuth","Pb_lead","Na_sodium","K_potassium" };*/
    String properties[] = {"Density","Surface tension","Thermal conductivity","Viscosity","Specific heat"};
    String liquids[] = {"Ar_argon","CH4_methane","C2H2F4_1112tetrafluoroethane","C2H3F3_111trifluoroethane","C2H4F2_11difluoroethane",
            "C2HF5_pentafluoroethane","C2HClF4_2chloro1112tetrafluoroethane","C2HCl2F3_22dichloro111trifluoroethane","C2H2F4_1112tetrafluoroethane",
            "C3H8_propane","C3H6_propylene","C4H10_butane","C4H10_isobutane","CCl2F2_dichlorodifluoromethane","CH2F2_difluoromethane",
            "CHClF2_chlorodifluoromethane","CHF3_fluoroform","C2H6_ethane","CO2_carbondioxide","N2_nitrogen","NH3_ammonia",
            "O2_oxygen","He_helium4"};
    JComboBox <String> liquid_list=new JComboBox<String>(liquids);
    JComboBox <String> property_list=new JComboBox<String>(properties);
    String liquid = "CH4_methane";
    String property = "Density";
    String column[] = {"T(Kelvin)","Tablo","Katsayılar(%Hata)","Rackett(%Hata)","Yamada and Gunn(%Hata)","HBT(%Hata)"};
    Object row[][];
    JScrollPane scrollPane;
    public createTable_for_comparision(){
        this.setLayout(null);
        liquid_list.setBounds(20,50,300,50);
        property_list.setBounds(350,50,300,50);
        this.add(liquid_list);
        this.add(property_list);

         double x_values[],y_values[];

        row = density_values_for_Table(liquid);
        label.setBounds(235,130,300,50);
        this.add(label);
        table=new JTable(row,column);
        table.setPreferredScrollableViewportSize(new Dimension(700,600));
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20,200,650,600);
        this.add(scrollPane);
        ActionListener actionListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTable_for_comparision.this.remove(label);
                createTable_for_comparision.this.remove(scrollPane);
                liquid = (String) liquid_list.getSelectedItem();
                property = (String) property_list.getSelectedItem();
                label.setText("Liquid: "+liquid + " Property:"+property);
                label.setBounds(100,30,200,50);
                createTable_for_comparision.this.add(label);
                row = calculate(liquid,property);
                table = new JTable(row,column);
                table.setPreferredScrollableViewportSize(new Dimension(700,600));
                scrollPane = new JScrollPane(table);
                scrollPane.setBounds(20,200,650,600);
                createTable_for_comparision.this.add(scrollPane);
                createTable_for_comparision.this.revalidate();
            }
        };
        liquid_list.addActionListener(actionListener);
        property_list.addActionListener(actionListener);
    }
    public Object[][] calculate(String liquid,String property){
        Object row[][] ;
        if(property == "Density"){
            row = density_values_for_Table(liquid);
        }
        else if(property == "Surface tension"){
            row = surten_values_for_Table(liquid);
        }
        else if(property == "Thermal conductivity"){
            row = thermalconductivity_values_for_Table(liquid);
        }
        else if(property == "Viscosity"){
            row = viscosity_values_for_Table(liquid);
        }
        else if(property == "Specific heat"){
            row = cp_values_for_Table(liquid);
        }
        else{
            row = density_values_for_Table(liquid);
        }
        //System.out.println("Sütunlar"+column[2]+" "+ column[3]);

        return  row;
    }



    public Object[][] surten_values_for_Table (String name){
        liquid_values values = new liquid_values();
        double table_Values[][] = values.getTableValues(name);
        liquids liquids = new liquids();
        double surten_c[]=values.getsurtension(name);
        String metot_names[]= {"T(Kelvin)","Tablo","Katsayılar (% Hata)","Brock ve Bird(Yüzde Hata)","Pitzer(% Hata)","Zuo ve Stendby( % Hata)","Sastri ve Rao(% Hata)"};
        column = metot_names;

        Object row[][]=new Object[table_Values.length][metot_names.length]; // Tablolara eklenecek olan satırlar
        double sigma; // Yüzey gerilimi Birimi: N/m
        double sigma_referans;
        double percent_error;
        double T;
        DecimalFormatSymbols symbol= new DecimalFormatSymbols();
        symbol.setDecimalSeparator('.');
        NumberFormat formatter = new DecimalFormat("#0.0000000",symbol);
        NumberFormat formatter2 = new DecimalFormat("#0.00",symbol);
        for(int i=0;i<table_Values.length;i++){
            //row[i][1]=String.format("%,.5f", sigma); Boyle de formatlanabilir. Bircok yontem var.
            T = table_Values[i][1];
            row[i][0]=T;
            sigma = table_Values[i][18]; // Birimi mN/m. Bunu N/m yapmak icin 1000'e bolecegim.
            sigma = sigma/1000; // Birimini degistirdim.
            sigma_referans = sigma;
            row[i][1]=formatter.format(sigma);
            sigma = liquids.sur_tension(name,T);
            percent_error = (sigma - sigma_referans) / sigma_referans*100;
            row[i][2]=formatter.format(sigma) + "(%" + formatter2.format(percent_error) + ")";
            sigma = liquids.surten_BrockandBird(name,T);
            percent_error = (sigma-sigma_referans)/sigma_referans*100;
            row[i][3]=formatter.format(sigma) + "(%" + formatter2.format(percent_error) + ")";
            sigma = liquids.surten_Pitzer(name,T);
            percent_error = (sigma-sigma_referans)/sigma_referans*100;
            row[i][4]=formatter.format(sigma) + "(%" + formatter2.format(percent_error) + ")";
            sigma = liquids.surten_ZuoandStendby(name,T);
            percent_error = (sigma-sigma_referans)/sigma_referans*100;
            row[i][5]=formatter.format(sigma) + "(%" + formatter2.format(percent_error) + ")";
            sigma = liquids.surten_SastriandRao(name,T);
            percent_error = (sigma-sigma_referans)/sigma_referans*100;
            row[i][6]=formatter.format(sigma) + "(%" + formatter2.format(percent_error) + ")";
           /* try{
                Pdoyma = Double.parseDouble(liquids.Pvapor(name,T));//Bundan dolayi try icine aldim.
                sigma = liquids.surten_MacleodandSugden(name,T,Pdoyma,"double");
                percent_error = (sigma-sigma_referans)/sigma_referans*100;
                row[i][6]=formatter.format(sigma) + "(%" + formatter2.format(percent_error) + ")";
            }
            catch (NumberFormatException e ){
                e.printStackTrace();
            }
           */
        }
        return row;
    }
    public Object[][] viscosity_values_for_Table(String name) {

        liquid_values values = new liquid_values();
        double table_Values[][] = values.getTableValues(name);
        liquids liquids = new liquids();
        double vis_c[]=values.getvis(name);
        String metot_names[] = {"T(Kelvin)","Tablo","Katsayılar(% Hata)","Przezdziecki and Sridhar(% Hata)"};
        column = metot_names;
        Object row[][]=new Object[table_Values.length][metot_names.length]; // Tablolara eklenecek olan satırlar
        DecimalFormatSymbols symbol= new DecimalFormatSymbols();
        symbol.setDecimalSeparator('.');
        NumberFormat formatter = new DecimalFormat("#0.0000000",symbol);
        NumberFormat formatter2 = new DecimalFormat("#0.00",symbol);
        double T;
        double vis; // Pa.s
        double vis_referans;
        double percent_error;
        for(int i=0;i<table_Values.length;i++){
            T = table_Values[i][1];
            row[i][0] = T;
            vis = table_Values[i][14];//Birimi mikroPa.s . Bunu Pa.s yapmak icin 1000000'e bolecegim.
            vis = vis / 1000000; // Birimini cevirdim.
            vis_referans = vis;
            row[i][1]=formatter.format(vis);
            vis = liquids.vis(name,T);
            percent_error = (vis-vis_referans)/vis_referans*100;
            row[i][2]=formatter.format(vis) + "(%" + formatter2.format(percent_error) + ")";
            vis = liquids.vis_Przezdziecki_and_Sridhar(name,T,"double");
            percent_error = (vis-vis_referans)/vis_referans*100;
            row[i][3]=formatter.format(vis) + "(%" + formatter2.format(percent_error) + ")";
//            try{
//                Pdoy = Double.parseDouble(liquids.Pvapor(name,T));
//                vis = liquids.vis_Lucas(name,T,Pdoy,"double");
//                percent_error = (vis-vis_referans)/vis_referans*100;
//                row[i][3]=formatter.format(vis) + "(%" + formatter2.format(percent_error) + ")";
//            }
//            catch(NumberFormatException e){
//                e.printStackTrace();
//                row[i][3]="Hesaplanamadı";
//            }
        }
        return row;
    }
    public Object[][] cp_values_for_Table(String name) {

        liquid_values values = new liquid_values();
        double table_Values[][] = values.getTableValues(name);
        liquids liquids = new liquids();
        double cp_c[]=values.getcp(name);
        double critical_values[] = values.get_critical(name);
        double M = critical_values[0];
        String metot_names[] = {"T(Kelvin)","Tablo","Katsayılar( % Hata)","CSP(% Hata)"};
        column = metot_names;
        Object row[][]=new Object[table_Values.length][metot_names.length]; // Tablolara eklenecek olan satırlar
        DecimalFormatSymbols symbol= new DecimalFormatSymbols();
        symbol.setDecimalSeparator('.');
        NumberFormat formatter = new DecimalFormat("#0.0000000",symbol);
        NumberFormat formatter2 = new DecimalFormat("#0.00",symbol);
        double T;
        double cp; // Pa.s
        double cp_referans;
        double percent_error;
        for(int i=0;i<table_Values.length;i++){
            T = table_Values[i][1];
            row[i][0] = T;
            cp = table_Values[i][9]; // Birimini degistirecegim. Su an kJ/(kgK) ben bunu kJ/(kmolK) yapacagim.
            cp = cp * M ;
            cp_referans = cp;
            row[i][1]=formatter.format(cp);
            cp = liquids.cp(name,T);
            percent_error = (cp-cp_referans)/cp_referans*100;
            row[i][2]=formatter.format(cp) + "(%" + formatter2.format(percent_error) + ")";
            cp = liquids.cp_CSP(name,T,"double");
            percent_error = (cp-cp_referans)/cp_referans*100;
            row[i][3]=formatter.format(cp) + "(%" + formatter2.format(percent_error) + ")";
        }
        return row;
    }
    public Object[][] density_values_for_Table(String name) {

        liquid_values values = new liquid_values();
        double table_Values[][] = values.getTableValues(name);
        liquids liquids = new liquids();
        double cp_c[]=values.getro(name);
        String metot_names[] = {"T(Kelvin)","Tablo","Katsayılar(% Hata)","Rackett(% Hata)","Yamada and Gunn(% Hata)","HBT(% Hata)"}; // HBT: Hankinson and Thomson
        column = metot_names;
        Object row[][]=new Object[table_Values.length][metot_names.length]; // Tablolara eklenecek olan satırlar
        DecimalFormatSymbols symbol= new DecimalFormatSymbols();
        symbol.setDecimalSeparator('.');
        NumberFormat formatter = new DecimalFormat("#0.0000000",symbol);
        NumberFormat formatter2 = new DecimalFormat("#0.00",symbol);
        double T;
        double ro; // Pa.s
        double ro_referans;
        double percent_error;
        for(int i=0;i<table_Values.length;i++){
            T = table_Values[i][1];
            row[i][0] = T;
            ro = table_Values[i][3];
            ro_referans = ro;
            row[i][1]=formatter.format(ro);
            ro = liquids.ro(name,T);
            percent_error = (ro-ro_referans)/ro_referans*100;
            // Simdilik katsayi ile hesaplanann degerler referans olarak kullanilacak.
            row[i][2]=formatter.format(ro) + "(%" + formatter2.format(percent_error) + ")";
            ro = liquids.ro_Rackett(name,T);
            percent_error = (ro-ro_referans)/ro_referans*100;
            row[i][3]=formatter.format(ro) + "(%" + formatter2.format(percent_error) + ")";
            ro = liquids.ro_Yamada_Gunn(name,T);
            percent_error = (ro-ro_referans)/ro_referans*100;
            row[i][4]=formatter.format(ro) + "(%" + formatter2.format(percent_error) + ")";
            ro = liquids.ro_HBT(name,T);
            percent_error = (ro-ro_referans)/ro_referans*100;
            row[i][5]=formatter.format(ro) + "(%" + formatter2.format(percent_error) + ")";
        }
        return row;
    }
    public Object[][] thermalconductivity_values_for_Table(String name) {

        liquid_values values = new liquid_values();
        double table_Values[][] = values.getTableValues(name);
        liquids liquids = new liquids();
        double cp_c[]=values.getk(name);
        String metot_names[] = {"T(Kelvin)","Tablo","Katsayılar( % Hata)","Latini( % Hata)","Sastri( % Hata)"};
        column = metot_names;
        Object row[][]=new Object[table_Values.length][metot_names.length]; // Tablolara eklenecek olan satırlar
        DecimalFormatSymbols symbol= new DecimalFormatSymbols();
        symbol.setDecimalSeparator('.');
        NumberFormat formatter = new DecimalFormat("#0.0000000",symbol);
        NumberFormat formatter2 = new DecimalFormat("#0.00",symbol);
        double T;
        double k; // Pa.s
        double k_referans;
        double percent_error;
        for(int i=0;i<table_Values.length;i++){
            T = table_Values[i][1];
            row[i][0] = T;
            k = table_Values[i][16]; // Birimi mW/(mK). Bunu W/(mK) yapmak icin 1000'e bolecegim.
            k = k/1000;
            k_referans = k;
            row[i][1]=formatter.format(k);
            k = liquids.k(name,T);
            percent_error = (k-k_referans)/k_referans*100;
            row[i][2]=formatter.format(k) + "(%" + formatter2.format(percent_error) + ")";
            k = liquids.k_Latini(name,T);
            percent_error = (k-k_referans)/k_referans*100;
            row[i][3]=formatter.format(k) + "(%" + formatter2.format(percent_error) + ")";
            k = liquids.k_Sastri(name,T);
            percent_error = (k-k_referans)/k_referans*100;
            row[i][4]=formatter.format(k) + "(%" + formatter2.format(percent_error) + ")";
        }
        return row;
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Farklı Metotlar ile Elde Edilen Değerlerin Tablo Değerleri ile Karşılaştırılması");
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createTable_for_comparision panel = new createTable_for_comparision();
        frame.add(panel);
        frame.setVisible(true);
    }
}

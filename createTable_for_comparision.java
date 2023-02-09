package bitirme_tezi;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;


public class createTable_for_comparision extends JPanel {

    /* Bu sınıfta özelliklerin hesaplanması için kullanılan farklı yöntemlerden elde edilen
    değerler tablo halinde verilecek ve kıyaslamaları yapılacaktır.
     */
    JTable table;


    public createTable_for_comparision(){
        this.setLayout(null);
        //String column[]= {"T(Kelvin)","Katsayılar","Brock ve Bird","Pitzer","Zuo ve Stendby","Sastri ve Rao"};
        //String column[]= {"T(Kelvin)","Katsayılar","Przezdziecki and Sridhar"};
        //String column[]= {"T(Kelvin)","Katsayılar","CSP"}; // cp
        //String column[]= {"T(Kelvin)","Katsayılar","Rackett","Yamada and Gunn","HBT"}; // density
        String column[]= {"T(Kelvin)","Katsayılar","Latini","Sastri"}; // thermal conductivity
         double x_values[],y_values[];
        //double T[] = {90.0,100.0,110.0,120.0,130.0,140.0,150.0,160.0,170.0,180.0,190.0};
        //double T[] = {113,123,133,143,153,163,173,183}; // CH4 methane icin olan sicaklik degerleri
        double T[] = {293,303,313,323,333,343}; // CH4O methanol icin olan sicaklik degerleri
        //double T[] = {173,193,223,248,273,298,323,348,373}; // C4H10 isobutane icin olan sicaklik degerleri
        //double T[] = {273,278,283,288,293,298,303,308,313}; // C3H8O3 Glycerin icin olan sicaklik degerleri
        //double T[] = {623,673,773,873,973}; // Bi Bismuth  icin olan sicaklik degerleri
        //double T[] = {623,673,723,773,823,873,923,973}; // Pb lead icin olan sicaklik degerleri
        //double T[] = {373,473,573,673,773,873}; // Na icin olan sicaklik degerleri
        //double T[] = {473,573,673,773,873}; // K icin olan sicaklik degerleri
        //Object row[][] = surten_values_for_Table("CH4_methane",T);
        Object row[][] = thermalconductivity_values_for_Table("CH4O_methanol",T);
        table=new JTable(row,column);
        table.setPreferredScrollableViewportSize(new Dimension(700,600));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100,100,700,600);
        this.add(scrollPane);
    }

    public Object[][] surten_values_for_Table (String name, double Ti []){
        liquid_values values = new liquid_values();
        liquids liquids = new liquids();
        double surten_c[]=values.getsurtension(name);
        String metot_names[]= {"Katsayılar","Brock ve Bird değeri (Yüzde Hata)","Pitzer","Zuo ve Stendby","Sastri ve Rao"};
        Object row[][]=new Object[Ti.length][metot_names.length+1]; // Tablolara eklenecek olan satırlar
        // metot_names.length+1 ekledim. Çünkü daha T değerleri de eklenecek.
        // Tablo degerlerini de eklemek lazim aslinda.
        double sigma; // Yüzey gerilimi Birimi: N/m
        double sigma_referans;
        double percent_error;
        double T;
        double Pdoyma;
        DecimalFormatSymbols symbol= new DecimalFormatSymbols();
        symbol.setDecimalSeparator('.');
        NumberFormat formatter = new DecimalFormat("#0.00000",symbol);
        NumberFormat formatter2 = new DecimalFormat("#0.00",symbol);
        for(int i=0;i<Ti.length;i++){
            //row[i][1]=String.format("%,.5f", sigma); Boyle de formatlanabilir. Bircok yontem var.
            T = Ti[i];
            row[i][0]=T;
            sigma = liquids.sur_tension(name,T);
            sigma_referans=sigma; // % hata hesaplarken kullanılacak. Daha sonra bu deger yerine tablodan okunan degerler eklenecektir.
            // Simdilik katsayi ile hesaplanann degerler referans olarak kullanilacak.
            row[i][1]=formatter.format(sigma);
            sigma = liquids.surten_BrockandBird(name,T);
            percent_error = (sigma-sigma_referans)/sigma_referans*100;
            row[i][2]=formatter.format(sigma) + "(%" + formatter2.format(percent_error) + ")";
            sigma = liquids.surten_Pitzer(name,T);
            percent_error = (sigma-sigma_referans)/sigma_referans*100;
            row[i][3]=formatter.format(sigma) + "(%" + formatter2.format(percent_error) + ")";
            sigma = liquids.surten_ZuoandStendby(name,T);
            percent_error = (sigma-sigma_referans)/sigma_referans*100;
            row[i][4]=formatter.format(sigma) + "(%" + formatter2.format(percent_error) + ")";
            sigma = liquids.surten_SastriandRao(name,T);
            percent_error = (sigma-sigma_referans)/sigma_referans*100;
            row[i][5]=formatter.format(sigma) + "(%" + formatter2.format(percent_error) + ")";
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
    public Object[][] viscosity_values_for_Table(String name,double Ti[]) {
        liquid_values values = new liquid_values();
        liquids liquids = new liquids();
        double vis_c[]=values.getvis(name);
        String metot_names[] = {"Katsayılar","Przezdziecki and Sridhar","Lucas"};
        Object row[][]=new Object[Ti.length][metot_names.length+1]; // Tablolara eklenecek olan satırlar
        DecimalFormatSymbols symbol= new DecimalFormatSymbols();
        symbol.setDecimalSeparator('.');
        NumberFormat formatter = new DecimalFormat("#0.00000",symbol);
        NumberFormat formatter2 = new DecimalFormat("#0.00",symbol);
        double T;
        double vis; // Pa.s
        double vis_referans;
        double percent_error;
        double Pdoy;
        for(int i=0;i<Ti.length;i++){
            T = Ti[i];
            row[i][0] = T;
            vis = liquids.vis(name,T);
            vis_referans=vis; // % hata hesaplarken kullanılacak. Daha sonra bu deger yerine tablodan okunan degerler eklenecektir.
            // Simdilik katsayi ile hesaplanann degerler referans olarak kullanilacak.
            row[i][1]=formatter.format(vis);
            vis = liquids.vis_Przezdziecki_and_Sridhar(name,T,"double");
            percent_error = (vis-vis_referans)/vis_referans*100;
            row[i][2]=formatter.format(vis) + "(%" + formatter2.format(percent_error) + ")";
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
        // x.add(x_ekseni2);
        //y.add(y_ekseni2);
        return row;
    }

    public Object[][] cp_values_for_Table(String name,double Ti[]) {
        liquid_values values = new liquid_values();
        liquids liquids = new liquids();
        double cp_c[]=values.getcp(name);
        String metot_names[] = {"Katsayılar","CSP"};
        Object row[][]=new Object[Ti.length][metot_names.length+1]; // Tablolara eklenecek olan satırlar
        DecimalFormatSymbols symbol= new DecimalFormatSymbols();
        symbol.setDecimalSeparator('.');
        NumberFormat formatter = new DecimalFormat("#0.00000",symbol);
        NumberFormat formatter2 = new DecimalFormat("#0.00",symbol);
        double T;
        double cp; // Pa.s
        double cp_referans;
        double percent_error;
        for(int i=0;i<Ti.length;i++){
            T = Ti[i];
            row[i][0] = T;
            cp = liquids.cp(name,T);
            cp_referans=cp; // % hata hesaplarken kullanılacak. Daha sonra bu deger yerine tablodan okunan degerler eklenecektir.
            // Simdilik katsayi ile hesaplanann degerler referans olarak kullanilacak.
            row[i][1]=formatter.format(cp);
            cp = liquids.cp_CSP(name,T,"double");
            percent_error = (cp-cp_referans)/cp_referans*100;
            row[i][2]=formatter.format(cp) + "(%" + formatter2.format(percent_error) + ")";

        }
        return row;
    }
    public Object[][] density_values_for_Table(String name,double Ti[]) {
        liquid_values values = new liquid_values();
        liquids liquids = new liquids();
        double cp_c[]=values.getro(name);
        String metot_names[] = {"Katsayılar","Rackett","Yamada and Gunn","HBT"}; // HBT: Hankinson and Thomson
        Object row[][]=new Object[Ti.length][metot_names.length+1]; // Tablolara eklenecek olan satırlar
        DecimalFormatSymbols symbol= new DecimalFormatSymbols();
        symbol.setDecimalSeparator('.');
        NumberFormat formatter = new DecimalFormat("#0.00000",symbol);
        NumberFormat formatter2 = new DecimalFormat("#0.00",symbol);
        double T;
        double ro; // Pa.s
        double ro_referans;
        double percent_error;
        for(int i=0;i<Ti.length;i++){
            T = Ti[i];
            row[i][0] = T;
            ro = liquids.ro(name,T);
            ro_referans=ro; // % hata hesaplarken kullanılacak. Daha sonra bu deger yerine tablodan okunan degerler eklenecektir.
            // Simdilik katsayi ile hesaplanann degerler referans olarak kullanilacak.
            row[i][1]=formatter.format(ro);
            ro = liquids.ro_Rackett(name,T);
            percent_error = (ro-ro_referans)/ro_referans*100;
            row[i][2]=formatter.format(ro) + "(%" + formatter2.format(percent_error) + ")";
            ro = liquids.ro_Yamada_Gunn(name,T);
            percent_error = (ro-ro_referans)/ro_referans*100;
            row[i][3]=formatter.format(ro) + "(%" + formatter2.format(percent_error) + ")";
            ro = liquids.ro_HBT(name,T);
            percent_error = (ro-ro_referans)/ro_referans*100;
            row[i][4]=formatter.format(ro) + "(%" + formatter2.format(percent_error) + ")";
        }
        return row;
    }
    public Object[][] thermalconductivity_values_for_Table(String name,double Ti[]) {
        liquid_values values = new liquid_values();
        liquids liquids = new liquids();
        double cp_c[]=values.getk(name);
        String metot_names[] = {"Katsayılar","Latini","Sastri"};
        Object row[][]=new Object[Ti.length][metot_names.length+1]; // Tablolara eklenecek olan satırlar
        DecimalFormatSymbols symbol= new DecimalFormatSymbols();
        symbol.setDecimalSeparator('.');
        NumberFormat formatter = new DecimalFormat("#0.00000",symbol);
        NumberFormat formatter2 = new DecimalFormat("#0.00",symbol);
        double T;
        double k; // Pa.s
        double k_referans;
        double percent_error;
        for(int i=0;i<Ti.length;i++){
            T = Ti[i];
            row[i][0] = T;
            k = liquids.k(name,T);
            k_referans=k; // % hata hesaplarken kullanılacak. Daha sonra bu deger yerine tablodan okunan degerler eklenecektir.
            // Simdilik katsayi ile hesaplanann degerler referans olarak kullanilacak.
            row[i][1]=formatter.format(k);
            k = liquids.k_Latini(name,T);
            percent_error = (k-k_referans)/k_referans*100;
            row[i][2]=formatter.format(k) + "(%" + formatter2.format(percent_error) + ")";
            k = liquids.k_Sastri(name,T);
            percent_error = (k-k_referans)/k_referans*100;
            row[i][3]=formatter.format(k) + "(%" + formatter2.format(percent_error) + ")";

        }
        return row;
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Cp");
        frame.setSize(800,800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createTable_for_comparision panel = new createTable_for_comparision();
        frame.add(panel);
        frame.setVisible(true);
    }
}

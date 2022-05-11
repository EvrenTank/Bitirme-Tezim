package bitirme_tezi;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

public class liquid_values {



    // ısıl iletkenlik=> A,B,C,Tmin,Tmax  birimi: W/(mK)  Kontrol et



    // Cp organic liq =A+B*T+C*(T^2)+D*(T^3)	( J/(molK))
    // A,B,C,D,Tmin,Tmax

    // Cp inorganic liq =A+B*T+C*(T^2)+D*(T^3)	( J/(molK))
    // A,B,C,D,Tmin,Tmax

    // Density organic and inorganic  g/ml
    //A*B^( - (1 - T/C)^n)
    // A, B, C, n, Tmin, Tmax, T, density@T

    //Hvap organic and inorganic kJ/mol
    // A*((1 - T/Tc)^n)
    // A, Tc, n, Tmin, Tmax, T, Hvap@T

    // Critical values organic and inorganic
    // NO, formula,Name, MW, Tb, Tc, Pc, Vc, RHOC, ZC, omega

    // viskozite organic - centipoise
    //log10(μ liq) = A + B/T + C*T + D*T^2
    // A,B,C,D,Tmin,Tmax,T,vis@T




    ArrayList <String[]> cp_katsayılar= new ArrayList<String[]>(); // İlk olarak dosyalarda okunulan satırları kaydetmek için ArrayList oluşturuyorum.
    ArrayList <String[]> critical_katsayılar= new ArrayList<String[]>();// İlk olarak dosyalarda okunulan satırları kaydetmek için ArrayList oluşturuyorum.
    ArrayList <String[]> density_katsayılar= new ArrayList<String[]>(); // İlk olarak dosyalarda okunulan satırları kaydetmek için ArrayList oluşturuyorum.
    ArrayList <String[]> hvap_katsayılar= new ArrayList<String[]>();// İlk olarak dosyalarda okunulan satırları kaydetmek için ArrayList oluşturuyorum.
    ArrayList <String[]> viscosity_katsayılar= new ArrayList<String[]>();// İlk olarak dosyalarda okunulan satırları kaydetmek için ArrayList oluşturuyorum.






    public ArrayList<String[]> read_file(File file){
    String[] a=null;
    ArrayList <String []> katsayılar = new ArrayList< String[] >();
    Scanner myReader;
    try {
        myReader = new Scanner(file);
        while(myReader.hasNextLine()){
            String data = myReader.nextLine();
            String veri[]= data.split(" ");
            katsayılar.add(veri);

        }
    } catch (FileNotFoundException e) {

        e.printStackTrace();
    }



    return  katsayılar;
}
    public void  read_all_Files(){
 File cpvalues_File= new File("D:\\Kullanıcılar-Lenovo-silme\\eclipse-workspace\\Bitirme Tezi\\src\\bitirme_tezi\\katsayılar\\cp.txt");
 File criticalvalues_File= new File("D:\\Kullanıcılar-Lenovo-silme\\eclipse-workspace\\Bitirme Tezi\\src\\bitirme_tezi\\katsayılar\\critical values.txt");
 File densityvalues_File= new File("D:\\Kullanıcılar-Lenovo-silme\\eclipse-workspace\\Bitirme Tezi\\src\\bitirme_tezi\\katsayılar\\density.txt");
 File hvapvalues_File= new File("D:\\Kullanıcılar-Lenovo-silme\\eclipse-workspace\\Bitirme Tezi\\src\\bitirme_tezi\\katsayılar\\hvap.txt");
 File viscosityvalues_File= new File("D:\\Kullanıcılar-Lenovo-silme\\eclipse-workspace\\Bitirme Tezi\\src\\bitirme_tezi\\katsayılar\\viscosity.txt");

   cp_katsayılar= read_file(cpvalues_File);
   critical_katsayılar =read_file(criticalvalues_File);
   density_katsayılar= read_file(densityvalues_File);
   hvap_katsayılar= read_file(hvapvalues_File);
   viscosity_katsayılar= read_file(viscosityvalues_File);




        String temp_array[];
        String cp_values [] =new String[cp_katsayılar.size()] ; // ArrayListleri, arraylere dönüşürdüm.
        String density_values [] =new String[density_katsayılar.size()] ;// ArrayListleri, arraylere dönüşürdüm.
        String critical_values [] =new String[critical_katsayılar.size()] ;// ArrayListleri, arraylere dönüşürdüm.
        String hvap_values [] =new String[hvap_katsayılar.size()] ;// ArrayListleri, arraylere dönüşürdüm.
        String viscosity_values [] =new String[viscosity_katsayılar.size()] ;// ArrayListleri, arraylere dönüşürdüm.
//        for (int i=0;i<cp_katsayılar.size();i++){
//            cp_values[i] = String.valueOf(cp_katsayılar.get(i).split(" "));
//
//
//        }
//        for (int i=0;i<density_katsayılar.size();i++){
//            density_values[i] = density_katsayılar.get(i);
//            temp_array= density_values[i].split(" ");
//            for(int k=0;k<temp_array.length;k++){
//                System.out.println(temp_array[k]);
//            }
//           }
//        for (int i=0;i<critical_katsayılar.size();i++){
//            critical_values[i] = critical_katsayılar.get(i);}
//        for (int i=0;i<hvap_katsayılar.size();i++){
//            hvap_values[i] = hvap_katsayılar.get(i);}
//        for (int i=0;i<viscosity_katsayılar.size();i++){
//            viscosity_values[i] = viscosity_katsayılar.get(i);}

//        for(int i=0; i<1000;i++){
//           System.out.println(cp_values[i]);
//           System.out.println(density_values[i]);
//           System.out.println(critical_values[i]);
//           System.out.println(hvap_values[i]);
//           System.out.println(viscosity_values[i]);
//        }
}
    public double[] getcp(String name){
        double cp []=null;
        for(String [] i:cp_katsayılar){
            if( i[0] == name){
                cp = new double[i.length-1];
                cp [0] = Double.parseDouble(i[1]);
                cp [1] = Double.parseDouble(i[2]);
                cp [2] = Double.parseDouble(i[3]);
                cp [3] = Double.parseDouble(i[4]);
                cp [4] = Double.parseDouble(i[5]);
                cp [5] = Double.parseDouble(i[6]);

            }
        }
        return cp;
    }
    public double[] getro(String name){
        double ro []=null;
        for(String [] i:density_katsayılar){
            if( i[0] == name){
                ro = new double[i.length-1];
                ro [0] = Double.parseDouble(i[1]);
                ro [1] = Double.parseDouble(i[2]);
                ro [2] = Double.parseDouble(i[3]);
                ro [3] = Double.parseDouble(i[4]);
                ro [4] = Double.parseDouble(i[5]);
                ro [5] = Double.parseDouble(i[6]);
                ro [6] = Double.parseDouble(i[7]);
                ro [7] = Double.parseDouble(i[8]);

            }
        }
        return ro;
    }

    public double[] getvis(String name){
        double vis []=null;
        for(String [] i:viscosity_katsayılar){
            if( i[0] == name){
                vis = new double[i.length-1];
                vis [0] = Double.parseDouble(i[1]);
                vis [1] = Double.parseDouble(i[2]);
                vis [2] = Double.parseDouble(i[3]);
                vis [3] = Double.parseDouble(i[4]);
                vis [4] = Double.parseDouble(i[5]);
                vis [5] = Double.parseDouble(i[6]);
                vis [6] = Double.parseDouble(i[7]);

            }
        }
        return vis;}

    public double[] gethvap(String name){
        double hvap []=null;
        for(String [] i:hvap_katsayılar){
            if( i[0] == name){
                hvap = new double[i.length-1];
                hvap [0] = Double.parseDouble(i[1]);
                hvap [1] = Double.parseDouble(i[2]);
                hvap [2] = Double.parseDouble(i[3]);
                hvap [3] = Double.parseDouble(i[4]);
                hvap [4] = Double.parseDouble(i[5]);
                hvap [5] = Double.parseDouble(i[6]);
                hvap [6] = Double.parseDouble(i[7]);

            }
        }
        return hvap;}

    public double[] get_critical(String name){

        double critic []=null;
        for(String [] i:critical_katsayılar){
            if( i[0] == name){
                critic = new double[i.length-1];
                critic [0] = Double.parseDouble(i[1]);
                critic [1] = Double.parseDouble(i[2]);
                critic [2] = Double.parseDouble(i[3]);
                critic [3] = Double.parseDouble(i[4]);
                critic [4] = Double.parseDouble(i[5]);
                critic [5] = Double.parseDouble(i[6]);
                critic [6] = Double.parseDouble(i[7]);
                critic [7] = Double.parseDouble(i[8]);

            }
        }
        return critic;
    }



    public static void main(String[] args) {
        liquid_values v1=new liquid_values();
        //v1.getcp("CO2");
        v1.read_all_Files();
    }
}

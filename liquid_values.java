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





    public String[] read_all_Files(){
 File myFile1= new File("D:\\Kullanıcılar-Lenovo-silme\\eclipse-workspace\\Bitirme Tezi\\src\\bitirme_tezi\\katsayılar\\cp.txt");
 File myFile2= new File("D:\\Kullanıcılar-Lenovo-silme\\eclipse-workspace\\Bitirme Tezi\\src\\bitirme_tezi\\katsayılar\\critical values.txt");
 File myFile3= new File("D:\\Kullanıcılar-Lenovo-silme\\eclipse-workspace\\Bitirme Tezi\\src\\bitirme_tezi\\katsayılar\\density.txt");
 File myFile4= new File("D:\\Kullanıcılar-Lenovo-silme\\eclipse-workspace\\Bitirme Tezi\\src\\bitirme_tezi\\katsayılar\\hvap.txt");
 File myFile5= new File("D:\\Kullanıcılar-Lenovo-silme\\eclipse-workspace\\Bitirme Tezi\\src\\bitirme_tezi\\katsayılar\\viscosity.txt");
 ArrayList <String> cp_katsayılar= new ArrayList<String>();
 ArrayList <String> critical_katsayılar= new ArrayList<String>();
 ArrayList <String> density_katsayılar= new ArrayList<String>();
 ArrayList <String> hvap_katsayılar= new ArrayList<String>();
 ArrayList <String> viscosity_katsayılar= new ArrayList<String>();
        Scanner myReader = null;
        try {
            myReader = new Scanner(myFile1);
            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                cp_katsayılar.add(data);

            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        try {
            myReader = new Scanner(myFile2);
            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                critical_katsayılar.add(data);

            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        try {
            myReader = new Scanner(myFile3);
            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                density_katsayılar.add(data);

            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        try {
            myReader = new Scanner(myFile4);
            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                hvap_katsayılar.add(data);

            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        try {
            myReader = new Scanner(myFile5);
            while(myReader.hasNextLine()){
                String data = myReader.nextLine();
                viscosity_katsayılar.add(data);

            }
        } catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        String temp_array[];
        String cp_values [] =new String[cp_katsayılar.size()] ;
        String density_values [] =new String[density_katsayılar.size()] ;
        String critical_values [] =new String[critical_katsayılar.size()] ;
        String hvap_values [] =new String[hvap_katsayılar.size()] ;
        String viscosity_values [] =new String[viscosity_katsayılar.size()] ;
        for (int i=0;i<cp_katsayılar.size();i++){
            cp_values[i] = cp_katsayılar.get(i);


        }
        for (int i=0;i<density_katsayılar.size();i++){
            density_values[i] = density_katsayılar.get(i);}
        for (int i=0;i<critical_katsayılar.size();i++){
            critical_values[i] = critical_katsayılar.get(i);}
        for (int i=0;i<hvap_katsayılar.size();i++){
            hvap_values[i] = hvap_katsayılar.get(i);}
        for (int i=0;i<viscosity_katsayılar.size();i++){
            viscosity_values[i] = viscosity_katsayılar.get(i);}

        for(int i=0; i<1000;i++){
            System.out.println(cp_values[i]);
            System.out.println(density_values[i]);
            System.out.println(critical_values[i]);
            System.out.println(hvap_values[i]);
            System.out.println(viscosity_values[i]);
        }




return cp_values;
}
    public double[] getcp(String name){
        Field[] fields=this.getClass().getDeclaredFields();
        double ro[]=null;
        String nameofliquid="ro_"+name;
        for(int i=0;i<fields.length;i++) {
            if(nameofliquid.equals(fields[i].getName())){
                fields[i].setAccessible(true);
                try {
                    ro= (double[]) fields[i].get(this);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return ro;
    }
    public double[] getro(String name){
        Field[] fields=this.getClass().getDeclaredFields();
        double ro[]=null;
        String nameofliquid="ro_"+name;
        for(int i=0;i<fields.length;i++) {
            if(nameofliquid.equals(fields[i].getName())){
                fields[i].setAccessible(true);
                try {
                    ro= (double[]) fields[i].get(this);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return ro;
    }

    public double[] getvis(String name){
        Field[] fields=this.getClass().getDeclaredFields();
        double vis[]=null;
        String nameofliquid="vis_"+name;
        for(int i=0;i<fields.length;i++) {
            if(nameofliquid.equals(fields[i].getName())){
                fields[i].setAccessible(true);
                try {
                    vis= (double[]) fields[i].get(this);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return vis;
    }

    public double[] getk(String name){
        Field[] fields=this.getClass().getDeclaredFields();
        double k[]=null;
        String nameofliquid="k_"+name;
        for(int i=0;i<fields.length;i++) {
            if(nameofliquid.equals(fields[i].getName())){
                fields[i].setAccessible(true);
                try {
                    k= (double[]) fields[i].get(this);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return k;
    }

    public double[] getref(String name){
        Field[] fields=this.getClass().getDeclaredFields();
        double ref[]=null;
        String nameofliquid="ref_"+name;
        for(int i=0;i<fields.length;i++) {
            if(nameofliquid.equals(fields[i].getName())){
                fields[i].setAccessible(true);
                try {
                    ref= (double[]) fields[i].get(this);

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return ref;
    }



    public static void main(String[] args) {
        liquid_values v1=new liquid_values();
        //v1.getcp("CO2");
        v1.read_all_Files();
    }
}

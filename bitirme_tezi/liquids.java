package bitirme_tezi;

import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.JOptionPane;

// Sıvılar
//Evren TANIK Ege Üniversitesi Makina Mühendisliği

public class liquids {
    // Sınıf değişkenlerinin tanımlanması

    //==============================================================

    liquid_values values=new liquid_values();


    // href tüm malzemeler için 0 Kelvin'de 0 kJ/kg olarak alınmıştır.

    double M,Tb,Tc,Pc,Zc,wp,v0;


    double vis_c[],k_c[],ro_c[],cp_c[], cpgas_c[],Pvapor_c[],critical[],hvap_c[],a_values[],Tf,organiccompounds_classification[],surtension_c[];// viskozite coefficients

    String vis,k,ro,cp,v,cp_cal,h,u,s,h_kg,Pr,alfa,
    Pvapor_mix,
    ro_mix_Aalto,ro_mix_Spencer_and_Danner,
    cp_kg,hvap,cp_csp,cp_Teja,cp_mix_JamiesonandCartwright,cp_mix_Teja,
            vis_GCM,
            k_latini,k_Sastri,k_Missenard,k_Latini_and_Baroncini, k_mix_Filippov , k_mix_Baroncini, k_mix_Li,k_mix_PowerLaw,
            ro2,
            surten,surten_MacleodandSugden,surten_BrockandBird,surten_Pitzer,surten_ZuoandStendby,surten_SastriandRao,
            surten_mix_WeinaugKatz,surten_mix_Hadden,surten_mix_ZuoandStendby_RiceTeja, surten_mix_ZuoandStendby_Kays,surten_mix_WeinaugKatz2,
            Pvapor,ro_Tait,ro_Chand_and_Zhao,ro_HBT; //h_kg birimi kJ/kg olduğu için şimdilik böyle yazdım.
    String vis_mix,vis_mix_Teja_and_Rice;
    String vis_Lucas,vis_Przezdziecki_and_Sridhar,vis_Letsou_and_Stiel;
    String malzemenin_turu="";
    double T;
    double ro_Rackett,ro_Yamada_Gunn;
    double cp_gas;
    double denklem;
    double h0,u0,s0;//kJ/kmol
    double Cv,g,viscosity;// g: gibbs free energy k: ısıl iletkenlik
    String name;
    double Ru,R;
    double P;
    double a,b;
    //===========================================================================================
    public liquids() {

    }

    public String Pvapor(double T) { //  ilk halinde mmhg olarak hesaplıyor. Ama ben onu kPa' çevireceğim.
        // Pvapor organic and inorganic => A, B, C, D, E, Tmin, Tmax (Birimi: mmhg)
        // Formülü: log10(P)=A+B/T+C*log10(T)+D*T+E*T^2
        // 1 mmhg = 0.133322368 kPa 1 kPa = 0.01 bar
        double A,B,C,D,E,Tmin,Tmax;
        double Pvapor=0;

        if(Pvapor_c[5]<=T && T<=Pvapor_c[6])
        {
            A=Pvapor_c[0];
            B=Pvapor_c[1];
            C=Pvapor_c[2];
            D=Pvapor_c[3];
            E=Pvapor_c[4];
            Pvapor=Math.pow(10,A+B/T+C*Math.log10(T)+D*T+E*T*T);
            Pvapor *= 0.133322368; // Birimini kPa yaptım.
            return (""+Pvapor);
        }
        else {
            return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
        }
    }
    public String Pvapor() { //  ilk halinde mmhg olarak hesaplıyor. Ama ben onu kPa' çevireceğim.
        // Pvapor organic and inorganic => A, B, C, D, E, Tmin, Tmax (Birimi: mmhg)
        // Formülü: log10(P)=A+B/T+C*log10(T)+D*T+E*T^2
        // 1 mmhg =0.133322368 kPa 1 kPa = 0.01 bar
        double A,B,C,D,E,Tmin,Tmax;
        double Pvapor=0;

        if(Pvapor_c[5]<=T && T<=Pvapor_c[6])
        {
            A=Pvapor_c[0];
            B=Pvapor_c[1];
            C=Pvapor_c[2];
            D=Pvapor_c[3];
            E=Pvapor_c[4];
            Pvapor=Math.pow(10,A+B/T+C*Math.log10(T)+D*T+E*T*T);
            Pvapor *= 0.133322368; // Birimini kPa yaptım.
            return (""+Pvapor);
        }
        else {
            return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
        }
    }
    public String Pvapor(String name, double T) { //  ilk halinde mmhg olarak hesaplıyor. Ama ben onu kPa' çevireceğim.
        // Pvapor organic and inorganic => A, B, C, D, E, Tmin, Tmax (Birimi: mmhg)
        // Formülü: log10(P)=A+B/T+C*log10(T)+D*T+E*T^2
        // 1 mmhg =0.133322368 kPa 1 kPa = 0.01 bar
        double A,B,C,D,E,Tmin,Tmax;
        double Pvapor=0;
        Pvapor_c = values.getPvapor(name);

        if(Pvapor_c[5]<=T && T<=Pvapor_c[6])
        {
            A=Pvapor_c[0];
            B=Pvapor_c[1];
            C=Pvapor_c[2];
            D=Pvapor_c[3];
            E=Pvapor_c[4];
            Pvapor=Math.pow(10,A+B/T+C*Math.log10(T)+D*T+E*T*T);
            Pvapor *= 0.133322368; // Birimini kPa yaptım.
            return (""+Pvapor);
        }
        else {
            return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
        }
    }
    public double Pvapor(String name, double T,String type) {  // type parametresi String return eden metot ile karismamasi icin eklendi. Bir islevi yok yani.
        //  ilk halinde mmhg olarak hesaplıyor. Ama ben onu kPa' çevireceğim.
        // Pvapor organic and inorganic => A, B, C, D, E, Tmin, Tmax (Birimi: mmhg)
        // Formülü: log10(P)=A+B/T+C*log10(T)+D*T+E*T^2
        // 1 mmhg =0.133322368 kPa 1 kPa = 0.01 bar
        double A,B,C,D,E,Tmin,Tmax;
        double Pvapor=0;
        Pvapor_c = values.getPvapor(name);

        if(Pvapor_c[5]<=T && T<=Pvapor_c[6])
        {
            A=Pvapor_c[0];
            B=Pvapor_c[1];
            C=Pvapor_c[2];
            D=Pvapor_c[3];
            E=Pvapor_c[4];
            Pvapor=Math.pow(10,A+B/T+C*Math.log10(T)+D*T+E*T*T);
            Pvapor *= 0.133322368; // Birimini kPa yaptım.
            return (Pvapor);
        }
        else {
            return 0;
        }
    }

    public String cp() { // (kJ/(kmolK))
        double A,B,C,D;

        double cp=0;

        if(cp_c[4]<=T && T<=cp_c[5])
        {
            A=cp_c[0];
            B=cp_c[1];
            C=cp_c[2];
            D=cp_c[3];
            cp=A+B*T+C*T*T+D*T*T*T;
            return (""+cp);
        }
        else {
            return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
        }
    }
    public String cp(double T) { // (kJ/(kmolK))
        double A,B,C,D;

        double cp=0;

        if(cp_c[4]<=T && T<=cp_c[5])
        {
            A=cp_c[0];
            B=cp_c[1];
            C=cp_c[2];
            D=cp_c[3];
            cp=A+B*T+C*T*T+D*T*T*T;
            return (""+cp);
        }
        else {
            return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
        }
    }
    public double cp(String name,double T) { // (kJ/(kmolK))
        double A,B,C,D;
        double cp=0;
        cp_c=values.getcp(name);
            A=cp_c[0];
            B=cp_c[1];
            C=cp_c[2];
            D=cp_c[3];
            cp=A+B*T+C*T*T+D*T*T*T;
            return cp;


    }
    public double cp_gas(String name,double T) { // (kJ/(kmolK))
        double A,B,C,D,E,Tmin,Tmax;
        double cp=0;
        cpgas_c=values.getcpgas(name);
        A=cpgas_c[0];
        B=cpgas_c[1];
        C=cpgas_c[2];
        D=cpgas_c[3];
        E=cpgas_c[4];
        Tmin=cpgas_c[5];
        Tmax=cpgas_c[6];
        if(T >= Tmin && T<=Tmax){
            cp=A+B*T+C*T*T+D*T*T*T+E*T*T*T*T;
        }
        return cp;
    }
    public String cp_gas (double T,String name) { // (kJ/(kmolK))
        double A,B,C,D,E,Tmin,Tmax;
        double cp=0;
        cpgas_c=values.getcpgas(name);
        A=cpgas_c[0];
        B=cpgas_c[1];
        C=cpgas_c[2];
        D=cpgas_c[3];
        E=cpgas_c[4];
        Tmin=cpgas_c[5];
        Tmax=cpgas_c[6];
        if(T >= Tmin && T<=Tmax){
            cp=A+B*T+C*T*T+D*T*T*T+E*T*T*T*T;
            return ""+cp;
        }

        return " Bu sıcaklık aralığı için hesap yapılamıyor";
    }

    public String cp_CSP(String name, double T) { // (kJ/(kmolK))
        // CSP: Corresponding States Method

        // Tmin ve Tmax değerleri verilmeyen malzemeler vardı. Onları her sıcaklıkta olur diye kabul ettiğim için
        // 0 ile 10000 arası diye kafama göre yazdım.
        double w,Tr,Tc,a0,a1,a2,a3,a4,Tmin,Tmax;
        cp_c= values.getcp(name);
        critical=values.get_critical(name);
        Tmin = cp_c[4];
        Tmax = cp_c[5];

        double cp_saturated=0.0;
        double cp=0;
        w=critical[7];
        Tc=critical[2];
        double M = critical[0] ;
        double Ru= 8.3145; // kJ/(kmolK)
        Tr=T/Tc;
        double R = Ru/M;   // kJ/(kgK)
        double cp_gas = cp_gas(name,T);
        cp_gas = cp_gas/M; // Birimini kJ/(kgK) yaptım burada.

        if( (T <= Tmax && T >= Tmin) )
        {

            if(cp_gas != 0){
                cp = R* (1.586 + 0.49/(1-Tr)+w*(4.2775 + 6.3/Tr*Math.pow(1.0-Tr,0.3333)+0.4355/(1-Tr)))+cp_gas ;
                cp_saturated =  cp - R* Math.pow(Math.E,20.1*Tr-17.9);
                cp = cp*M; // Birimi kJ/ (kmolK) yaptım.
                cp_saturated = cp_saturated*M;
//                System.out.println("cpL="+cp);
//                System.out.println("cp_saturated="+cp_saturated);
//                System.out.println("%Hata= "+(cp-cp_saturated)/cp_saturated*100);
                if ( Tr < 0.99){
                    return ""+ cp_saturated;
                }
                else { return ""+cp; }
            }

            else{
                return "cp ideal gaz hesaplanamadığı için hesap yapılamıyor.";
            }

        }
        else {
            return " Bu sıcaklık değeri için  hesaplama yapılamıyor";
        }
    }
    public double cp_CSP(String name, double T,String tip) { // (kJ/(kmolK))
        // CSP: Corresponding States Method

        // Tmin ve Tmax değerleri verilmeyen malzemeler vardı. Onları her sıcaklıkta olur diye kabul ettiğim için
        // 0 ile 10000 arası diye kafama göre yazdım.
        double w,Tr,Tc,a0,a1,a2,a3,a4,Tmin,Tmax;
        cp_c= values.getcp(name);
        critical=values.get_critical(name);
        Tmin = cp_c[4];
        Tmax = cp_c[5];

        double cp_saturated=0.0;
        double cp=0;
        w=critical[7];
        Tc=critical[2];
        double M = critical[0] ;
        double Ru= 8.3145; // kJ/(kmolK)
        Tr=T/Tc;
        double R = Ru/M;   // kJ/(kgK)
        double cp_gas = cp_gas(name,T);
        cp_gas = cp_gas/M; // Birimini kJ/(kgK) yaptım burada.

        if( (T <= Tmax && T >= Tmin) )
        {

            if(cp_gas != 0){
                cp = R* (1.586 + 0.49/(1-Tr)+w*(4.2775 + 6.3/Tr*Math.pow(1.0-Tr,0.3333)+0.4355/(1-Tr)))+cp_gas ;
                cp_saturated =  cp - R* Math.pow(Math.E,20.1*Tr-17.9);
                cp = cp*M; // Birimi kJ/ (kmolK) yaptım.
                cp_saturated = cp_saturated*M;
//                System.out.println("cpL="+cp);
//                System.out.println("cp_saturated="+cp_saturated);
//                System.out.println("%Hata= "+(cp-cp_saturated)/cp_saturated*100);
                if ( Tr < 0.99){
                    return cp_saturated;
                }
                else { return cp; }
            }

            else{
                return 0.0;
            }

        }
        else {
            return 0.0;
        }
    }

    public String cp_Teja(String name,double T) {
        // Bu metodun hassasiyeti pek iyi değil. O yüzden çıktılara eklemeyeceğim.
        // Bu metodun hassasiyeti pek iyi değil. O yüzden çıktılara eklemeyeceğim.

        double Tcm=0;
        double Mm= 0;
        double wm=0;
        double Tc1,Tc2,w1,w2,T1,T2,cp1,cp2,cpgas1,cpgas2,cpgas;
        critical = values.get_critical(name);
        double Tc = critical[2];
        critical = values.get_critical("C5H12_pentane");
        cp_c = values.getcp("C5H12_pentane");
        Tc1 = critical[2];
        w1 = critical[7];
        T1 = T/Tc*Tc1;
        try{
            cp1 = Double.parseDouble(cp(T1));
            cpgas1 = Double.parseDouble(cp_gas(T1,"C5H12_pentane"));
        }
        catch(NumberFormatException e){
            e.printStackTrace();
            return "C5H12_pentane"+" malzemesinin sıvı veya gaz hali için özgül ısı değeri hesaplanamıyor";
        }
        critical = values.get_critical("C8H18_octane");
        cp_c = values.getcp("C8H18_octane");
        Tc2 = critical[2];
        w2 = critical[7];
        T2 = T/Tc*Tc2;
        try{
            cp2 = Double.parseDouble(cp(T2));
            cpgas2 = Double.parseDouble(cp_gas(T2,"C8H18_octane"));
        }
        catch(NumberFormatException e){
            e.printStackTrace();
            return "C8H18_octane"+" malzemesinin sıvı veya gaz hali için özgül ısı değeri hesaplanamıyor";
        }
        try{
            cpgas = Double.parseDouble(cp_gas(T,name));
        }
        catch(NumberFormatException e){
            e.printStackTrace();
            return name+" malzemesinin gaz hali için özgül ısı değeri hesaplanamıyor";
        }
        double cp=cpgas+(cp1-cpgas1)+(wm-w1)/(w2-w1)*((cp2-cpgas2)-(cp1-cpgas1));
        return ""+cp;
    }

    public String cp2() {  // (kJ/(kgK))
        double A,B,C,D;

        double cp=0;
        double M=critical[0];

        if(cp_c[4]<=T && T<=cp_c[5])
        {
            A=cp_c[0];
            B=cp_c[1];
            C=cp_c[2];
            D=cp_c[3];
            cp=(A+B*T+C*T*T+D*T*T*T)/M;
            return (""+cp);
        }
        else {
            return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
        }
    }
    public String cp_mix(double cp1,double cp2,double x1,double x2) {
        double cp_mix=x1*cp1+x2*cp2; // cp değerleri mol biriminde olduğu için x1 ve x2 kullanıldı.

        return ""+cp_mix;
    }
    public String cp_mix_JamiesonandCartwright(String name[],double x[],double T){ // (kJ/kmolK)
        // Sadece ikili karışımlar için kullanılacak bir yöntemdir. O yüzden bir if komutu ile bunu kontrol edeceğim.
        if(name.length>2){
            return "Bu yöntem ikili sıvı karışımarı için kullanılabilir";
        }
        if(name[0].equals("H2O_water") || name[1].equals("H2O_water") )
        {
            return " Bu yöntem su içeren karışımlar için kullanılamaz";
        }
        double Tb1,Tb2,cp1,cp2,x1,x2,M1,M2,w1,w2,hvap1,hvap2,alfa,beta;
        cp_c = values.getcp(name[0]);
        critical = values.get_critical(name[0]);
        hvap_c = values.gethvap(name[0]);
        Tb1 = critical[1];
        M1 = critical[0];
        x1 = x[0];
        try {
            hvap1 = Double.parseDouble(hvap(Tb1));
            cp1 = Double.parseDouble(cp(T));
        }
        catch (NumberFormatException e){
            e.printStackTrace();
            return name[0]+" malzemesinin cp veya hbuharlaşma değeri hesaplanamıyor";
        }
        cp_c = values.getcp(name[1]);
        critical = values.get_critical(name[1]);
        hvap_c = values.gethvap(name[1]);
        Tb2= critical[1];
        M2 = critical[0];
        x2 = x[1];

        try {
            hvap2 = Double.parseDouble(hvap(Tb2));
            cp2 = Double.parseDouble(cp(T));
        }
        catch (NumberFormatException e){
            e.printStackTrace();
            return name[1]+" malzemesinin cp veya hbuharlaşma değeri hesaplanamıyor";
        }
        w1 = M1*x1/(M1*x1+M2*x2);
        w2 = M2*x2/(M1*x1+M2*x2);
        alfa = (0.00141*Math.pow(Math.abs(hvap1-hvap2),0.88)-0.08)*w1*w2;
        beta = (5E-05)*Math.abs(hvap1-hvap2)*Math.sin(360*w2);

        double cpm = (w1*cp1+w2*cp2)*(1+alfa+beta);
        return ""+cpm;
    }


    public String cp_mix_Teja(String name[],double x[], double T) {
        double Tcm=0;
        double Mm= 0;
        double wm=0;
        double Tc1,Tc2,w1,w2,T1,T2,cp1,cp2,cpgas1,cpgas2,cpgasmix;
        for(int i=0;i<name.length;i++){
            critical = values.get_critical(name[i]);
            Tcm += x[i]*critical[2];
            Mm += x[i]*critical[0];
            wm += x[i]*critical[7]; // accentric
        }
        critical = values.get_critical(name[0]);
        cp_c = values.getcp(name[0]);
        Tc1 = critical[2];
        w1 = critical[7];
        T1 = T/Tcm*Tc1;
       try{
           cp1 = Double.parseDouble(cp(T1));
           cpgas1 = Double.parseDouble(cp_gas(T1,name[0]));
       }
       catch(NumberFormatException e){
           e.printStackTrace();
           return name[0]+"  malzemesinin sıvı veya gaz hali için özgül ısı değeri hesaplanamıyor";
       }
        critical = values.get_critical(name[1]);
        cp_c = values.getcp(name[1]);
        Tc2 = critical[2];
        w2 = critical[7];
        T2 = T/Tcm*Tc2;
        try{
            cp2 = Double.parseDouble(cp(T2));
            cpgas2 = Double.parseDouble(cp_gas(T2,name[1]));
        }
        catch(NumberFormatException e){
            e.printStackTrace();
            return name[1]+" malzemesinin sıvı veya gaz hali için özgül ısı değeri hesaplanamıyor";
        }
         cpgasmix = x[0]*cpgas1+x[1]*cpgas2;

        double cpm=cpgasmix+(cp1-cpgas1)+(wm-w1)/(w2-w1)*((cp2-cpgas2)-(cp1-cpgas1));

        return ""+cpm;
    }
    public String cp_mix2(String cp[],double x[]) {
        double cp_mix=0; // cp değerleri mol biriminde olduğu için x1 ve x2 kullanıldı.
        for(int i=0;i<cp.length;i++){
            try {
                cp_mix += Double.parseDouble(cp[i])*x[i];
            }
            catch (NumberFormatException e) {
                e.printStackTrace();
                return "Bu sıcaklıkta hesaplama yapılamıyor";
            }
        }
        return ""+cp_mix;
    }
    public String cp_cal() {
        double A,B,C,D;
        double cp=0;
        if(cp_c[4]<=T && T<=cp_c[5])
        {
            A=cp_c[0];
            B=cp_c[1];
            C=cp_c[2];
            D=cp_c[3];
            cp=A+B*T+C*T*T+D*T*T*T;
            cp/=4.184; // calori biriminden değer verir.
            return (""+cp);
        }
        else {
            return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
        }
    }
    public String h() {  //  kJ/kmol
        double A,B,C,D;
        double h=0;
        double Tref=0;
        double href=0;
        double sref=0;
        double M=critical[0];
            if((cp_c[4]-5 )<=T && T<=(cp_c[5]+5))
            {
                A=cp_c[0];
                B=cp_c[1];
                C=cp_c[2];
                D=cp_c[3];
                h=(A*T+B*T*T/2+C*T*T*T/3+D*T*T*T*T/4)-(A*Tref+B*Tref*Tref/2+C*Tref*Tref*Tref/3+D*Tref*Tref*Tref*Tref/4)+(href*M); // kJ/kmol
                return (""+h);
            }
            else {
                return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
            }

        }
    public double h(String name, double T) {  //  kJ/kmol
        double A,B,C,D;
        double h=0;
        double Tref=0;
        double href=0;
        double sref=0;
        critical = values.get_critical(name);
        cp_c = values.getcp(name);
        double M=critical[0];
        if((cp_c[4]-5 )<=T && T<=(cp_c[5]+5))
        {
            A=cp_c[0];
            B=cp_c[1];
            C=cp_c[2];
            D=cp_c[3];
            h=(A*T+B*T*T/2+C*T*T*T/3+D*T*T*T*T/4)-(A*Tref+B*Tref*Tref/2+C*Tref*Tref*Tref/3+D*Tref*Tref*Tref*Tref/4)+(href*M); // kJ/kmol
            return  h;
        }
        else {
            return 0;
        }

    }
    public String h2() {  // kJ/kg
        double A,B,C,D;
        double h=0;
        double Tref=0;
        double href=0;
        double sref=0;
        double M= critical[0];
        if((cp_c[4]-5 )<=T && T<=(cp_c[5]+5))
        {
            A=cp_c[0];
            B=cp_c[1];
            C=cp_c[2];
            D=cp_c[3];
            h=((A*T+B*T*T/2+C*T*T*T/3+D*T*T*T*T/4)-(A*Tref+B*Tref*Tref/2+C*Tref*Tref*Tref/3+D*Tref*Tref*Tref*Tref/4)+(href*M))/M; // kJ/kg
            return (""+h);
        }
        else {
            return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
        }
    }
    public String hvap(double T) {  //  (kJ / kmol)
        // kJ/mol: Bu katsayilar kullanilarak hesapl yapilinca elde edilen degerin birimi.
        // A*((1 - T/Tc)^n)
        // A, Tc, n, Tmin, Tmax, T, Hvap@T
        double A,Tc,n;
        double hvap=0;
        double M=critical[0];
        if(hvap_c[3]<=T && T<=hvap_c[4])
        {
            A=hvap_c[0];
            Tc=hvap_c[1];
            n=hvap_c[2];
            hvap = A* Math.pow(1-T/Tc,n)*1000;
            return (""+hvap);
        }
        else {
            return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
        }
    }
    public double hvap(String name,double T) {  //  (kJ / kmol)
        // kJ/mol
        // A*((1 - T/Tc)^n)
        // A, Tc, n, Tmin, Tmax, T, Hvap@T
        hvap_c = values.gethvap(name);
        critical = values.get_critical(name);
        double A,Tc,n;
        double hvap=0;
        double M=critical[0];
        if(hvap_c[3]<=T && T<=hvap_c[4])
        {
            A=hvap_c[0];
            Tc=hvap_c[1];
            n=hvap_c[2];
            hvap = A* Math.pow(1-T/Tc,n)*1000;
            return (hvap);
        }
        else {
            return 0;
        }
    }
    public String hvap_2() {  //  (kJ / kg)
        // kJ/mol
        // A*((1 - T/Tc)^n)
        // A, Tc, n, Tmin, Tmax, T, Hvap@T
        double A,Tc,n;
        double hvap=0;
        double M=critical[0];
        if(hvap_c[3]<=T && T<=hvap_c[4])
        {
            A=hvap_c[0];
            Tc=hvap_c[1];
            n=hvap_c[2];
            hvap = A* Math.pow(1-T/Tc,n)*1000/M;
            return (""+hvap);
        }
        else {
            return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
        }
    }


    public String s() {// kJ/(kgK)
        double A,B,C,D;
        double s=0;
        double Tref=1,href=0,sref=0,M=critical[0], vref,v;
        double Ru=8.3145; // kJ/(kmolK)
            vref=v(Tref); // m^3/kg
            v=v(T);
            //System.out.println("vref="+vref+" v="+v);
            if((cp_c[4]-5)<=T && T<=(cp_c[5]+5))
            {
                A=cp_c[0];
                B=cp_c[1];
                C=cp_c[2];
                D=cp_c[3];
                //s=(((A-Ru)*Math.log(T)+B*T+C*T*T/2+D*T*T*T/3)-((A-Ru)*Math.log(Tref)+B*Tref+C*Tref*Tref/2+D*Tref*Tref*Tref/3)+ Ru*Math.log(v/vref))/M+sref; // kJ/kg
                s = ((A*Math.log(T)+B*T+C*T*T/2+D*T*T*T/3)-(A*Math.log(Tref)+B*Tref+C*Tref*Tref/2+D*Tref*Tref*Tref/3)+(sref*M))/M;
                return (""+s);
            }
            else {
                return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
            }
    }
    public double s(String name, double T) {// kJ/(kgK)
        double A,B,C,D;
        critical = values.get_critical(name);
        cp_c = values.getcp(name);
        ro_c = values.getro(name);
        double s=0;
        double Tref=1,href=0,sref=0,M=critical[0], vref,v;
        double Ru=8.3145; // kJ/(kmolK)
        vref=v(Tref); // m^3/kg
        v=v(T);
        //System.out.println("vref="+vref+" v="+v);
        if((cp_c[4]-5)<=T && T<=(cp_c[5]+5))
        {
            A=cp_c[0];
            B=cp_c[1];
            C=cp_c[2];
            D=cp_c[3];
            //s=(((A-Ru)*Math.log(T)+B*T+C*T*T/2+D*T*T*T/3)-((A-Ru)*Math.log(Tref)+B*Tref+C*Tref*Tref/2+D*Tref*Tref*Tref/3)+ Ru*Math.log(v/vref))/M+sref; // kJ/kg
            s = ((A*Math.log(T)+B*T+C*T*T/2+D*T*T*T/3)-(A*Math.log(Tref)+B*Tref+C*Tref*Tref/2+D*Tref*Tref*Tref/3)+(sref*M))/M;
            return (s);
        }
        else {
            return 0;
        }
    }
    public double k(String name,double T) {
        double A,B,C;
        double k=0;
        k_c = values.getk(name);
            A=k_c[0];
            B=k_c[1];
            C=k_c[2];
            k=A+B*T+C*T*T;
            return k;
    }

    public String k() {
        double A,B,C;
        double k=0;
        if(k_c[3]<=T && T<=k_c[4])
        {
            A=k_c[0];
            B=k_c[1];
            C=k_c[2];
                k=A+B*T+C*T*T;
            return (""+k);
        }
        else {
            return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
        }
    }
    public double k_Latini(String name,double T){
        // Latini et. al method
        double A=0,Tb,Tc,M,Tr,Asharp,alfa,beta,gamma;
        double k=0.0;
        critical=values.get_critical(name);
        organiccompounds_classification=values.get_orgmat_classification(name);
        M=critical[0];
        Tb=critical[1];
        Tc=critical[2];
        Tr=T/Tc;
        Asharp=organiccompounds_classification[0];
        alfa=organiccompounds_classification[1];
        beta=organiccompounds_classification[2];
        gamma=organiccompounds_classification[3];

        if( M != 0 && Tb != 0 && Tc != 0 && Asharp != 0  && beta != 0 && gamma != 0){
            A=Asharp*Math.pow(Tb,alfa)/Math.pow(M,beta)/Math.pow(Tc,gamma);
            k=A*Math.pow(1-Tr,0.38)/Math.pow(Tr,0.166666);
        }
        //intln("k_Latini metodundaki A değeri:"+A);
            return k;
    }

    public void A_parameter() {
        liquid_names names = new liquid_names();
        String name;
        String isimler[] = names.get_names();
        for ( int i=0;i<isimler.length;i++){
            name = isimler[i];
            double A = values.getAparameter_for_kLatini(name);
            if( A != 0.0){
                //intln("malzeme:"+name+" A value: "+A);
                double Tb,Tc,M,Tr,Asharp,alfa,beta,gamma;
                double k=0;
                critical = values.get_critical(name);
                M=critical[0];
                Tb=critical[1];
                Tc=critical[2];
                Tr=T/Tc;
                organiccompounds_classification = values.get_orgmat_classification(name);
                Asharp=organiccompounds_classification[0];
                alfa=organiccompounds_classification[1];
                beta=organiccompounds_classification[2];
                gamma=organiccompounds_classification[3];
                A=Asharp*Math.pow(Tb,alfa)/Math.pow(M,beta)/Math.pow(Tc,gamma);
                //System.out.println("malzeme:"+name+" A value: "+A);
            }
        }
    }

    public String k_Latini(String name){
        // Latini et. al method

        double A = values.getAparameter_for_kLatini(name);
        double Tb,Tc,M,Tr,Asharp,alfa,beta,gamma;
        double k=0;
        critical = values.get_critical(name);
        organiccompounds_classification = values.get_orgmat_classification(name);
        M=critical[0];
        Tb=critical[1];
        Tc=critical[2];
        Tr=T/Tc;
        Asharp=organiccompounds_classification[0];
        alfa=organiccompounds_classification[1];
        beta=organiccompounds_classification[2];
        gamma=organiccompounds_classification[3];
        if( M == 0 || Tb == 0 || Tc == 0 ){
            return "M, Tb, Tc değerlerinden en az biri bilinmiyor";
        }
        if( Asharp == 0  || beta == 0 || gamma == 0 ){
            return " Malzeme organik değil veya ailesi bilinmiyor";
        }

        if(k_c[3]<=T && T<=k_c[4])
        {
            if ( A == 0.0){
                A=Asharp*Math.pow(Tb,alfa)/Math.pow(M,beta)/Math.pow(Tc,gamma);
            }
            k=A*Math.pow(1-Tr,0.38)/Math.pow(Tr,0.166666);
            //System.out.println("k_Latini metodundaki A değeri:"+A);
            return (""+k);
        }
        else {
            return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
        }
    }
    public String k_Sastri(String name){
        // Sastri 1998: Normalde bu denklemde kullanılan, normal kaynama sıcaklığındaki ısıl iletkenlik değeri
        // group contribution yöntemi ile hesaplanıyormuş. Ama ben Yaws kitabındaki katsayıları kullanacağım.
        values.get_orgmat_classification(name);
        String malzeme_turu=values.malzemenin_turu;
        double Tb = critical[1];
        double Tc = critical[2];
        double a=0.16;
        double n=0.2;
        if(name.endsWith("phenol") || malzeme_turu.equals("alcohol") ){
            a=0.856;
            n=1.23;
        }
        else if(malzeme_turu.equals("")){
            return " Bu malzeme için hesap yapılamıyor";
        }
        if(Tb == 0){
            return " Kaynama sıcaklığı bilinmediği için hesaplama yapılamıyor";
        }
        double k_Tb;
         try {
              k_Tb = k(name,Tb);
         }
         catch (NumberFormatException e){
             e.printStackTrace();
             return " Kaynama sıcaklığı için ısıl iletkenlik değeri hesaplanamadı";
         }

         double m = 1- Math.pow((1-T/Tc)/(1-Tb/Tc),n);
         double k = k_Tb*Math.pow(a,m);

        return ""+k;
    }

    public double k_Sastri(String name,double T){
        // Sastri 1998: Normalde bu denklemde kullanılan, normal kaynama sıcaklığındaki ısıl iletkenlik değeri
        // group contribution yöntemi ile hesaplanıyormuş. Ama ben Yaws kitabındaki katsayıları kullanacağım.
        values.get_orgmat_classification(name);
        String malzeme_turu=values.malzemenin_turu;
        double Tb = critical[1];
        double Tc = critical[2];
        double a=0.16;
        double n=0.2;
        if(name.endsWith("phenol") || malzeme_turu.equals("alcohol") ){
            a=0.856;
            n=1.23;
        }
        else if(malzeme_turu.equals("")){
            return 0.0;
        }
        if(Tb == 0){
            return 0.0;
        }
        double k_Tb;
        try {
            k_Tb = k(name,Tb);
        }
        catch (NumberFormatException e){
            e.printStackTrace();
            return 0.0;
        }
        double m = 1- Math.pow((1-T/Tc)/(1-Tb/Tc),n);
        double k = k_Tb*Math.pow(a,m);
        return k;
    }
    public double k_Missenard(String name,double T,double P,String tip){
        double Pc = critical[3]; // bar
        double k_saturated; // Düşük basınç da diyebiliriz.
        double Tc = critical[2];
        // Benim girdiğim P ise kPa biriminden o yüzden Pc de kPa olmalı
        Pc =Pc*100; // kPa yaptım.
        double Pr = P/Pc;
        double Tr = T/Tc;
        if( Pc == 0) {
            return 0.0;
        }
        if ( Tr>1 || Tr< 0.4) {
            return 0.0;
        }
        try{
            k_saturated =k(name,T);
        }
        catch (NumberFormatException e){
            e.printStackTrace();
            return 0.0;
        }

        double Q; // bilmem gereken parametre. Tr ve Pr ile bulunur.
        double Q_array[][] = {
                {0.012,0.0165,0.017,0.019,0.02,0.02},
                {0.015,0.02,0.022,0.024,0.025,0.025},
                {0.018,0.025,0.027,0.031,0.032,0.032},
                {0.036,0.038,0.038,0.038,0.038,0.038}
        };
        double Tr_array[] ={0.5,0.6,0.7,0.8};
        double Pr_array[] ={1.0,5.0,10.0,50.0,100.0,200.0};
        int y1=0,y2=0,x1=0,x2=0;

        for(int i=0;i<Tr_array.length-1;i++){
            if ( Tr < Tr_array[i+1] && Tr >Tr_array[i]){
                y1 = i;
                y2 = i+1;
            }
            else if ( Tr < Tr_array[0] ){
                y1 = 0;
                y2 = 1;
            }

            else if(Tr > Tr_array[Tr_array.length-1]){
                y1 = 2;
                y2 = 3;
            }
        }
        for(int j=0;j<Pr_array.length-1;j++){
            if ( Pr < Pr_array[j+1] && Pr >Pr_array[j]){
                x1 = j;
                x2 = j+1;
            }
            else if ( Pr < Pr_array[0]){
                x1 = 0;
                x2 = 1;
            }
            else if (Pr > Pr_array[Pr_array.length-1]) {
                x1 = 4;
                x2 = 5;
            }
        }
        double Tr1 = Tr_array[y1];
        double Tr2 = Tr_array[y2];
        double Pr1 = Pr_array[x1];
        double Pr2 = Pr_array[x2];

        double Q_ref1=Q_array[y1][x1];
        double Q_ref2=Q_array[y1][x2];
        double Q_ref3=Q_array[y2][x1];
        double Q_ref4=Q_array[y2][x2];

        double Qref13 = Q_ref1+(Q_ref3-Q_ref1) / ((Tr2-Tr1)/(Tr-Tr1));
        double Qref24 = Q_ref2+(Q_ref4-Q_ref2) / ((Tr2-Tr1)/(Tr-Tr1));
        double Qfinal = (Qref24-Qref13)*(Pr-Pr1)/(Pr2-Pr1)+Qref13;

        double k_high_pressure = (1+ Qfinal*Math.pow(Pr,0.7))*k_saturated;

        double Pvapor=0; // Birimi kPa
        Pvapor_c = values.getPvapor(name);
        try {
            Pvapor = Double.parseDouble(Pvapor(T));
        }
        catch (NumberFormatException e){
            e.printStackTrace();
            return 0; //Pbuhar bilinmeli ki sivi compressed mi diye bakabileyim.
        }
        if(Pvapor !=0 && Pvapor >= P){
            k_high_pressure = k_saturated; // Eger girilen basinc Pbuhardan dusuk ise zaten compressed olmayacagi icin
            // direkt doymus deger alinacak.
        }

        return k_high_pressure;
    }
    public String k_Missenard(String name,double T,double P){
     double Pc = critical[3]; // bar
        double k_saturated; // Düşük basınç da diyebiliriz.
        double Tc = critical[2];
        // Benim girdiğim P ise kPa biriminden o yüzden Pc de kPa olmalı
        Pc =Pc*100; // kPa yaptım.
     double Pr = P/Pc;
     double Tr = T/Tc;
     if( Pc == 0) {
         return " Pc değeri bilinmediği için hesap yapılamıyor";
     }
     if ( Tr>1 || Tr< 0.4) {
         return " Tr 0.4 ile 1 arasında olmalıdır.";
     }
     try{
          k_saturated = Double.parseDouble(k());
     }
     catch (NumberFormatException e){
         e.printStackTrace();
     return " Düşük basınç için olan k değeri hesaplanamadı. Sıcaklık aralığı geçerli değil";
     }

     double Q; // bilmem gereken parametre. Tr ve Pr ile bulunur.
     double Q_array[][] = {
             {0.012,0.0165,0.017,0.019,0.02,0.02},
             {0.015,0.02,0.022,0.024,0.025,0.025},
             {0.018,0.025,0.027,0.031,0.032,0.032},
             {0.036,0.038,0.038,0.038,0.038,0.038}
     };
     double Tr_array[] ={0.5,0.6,0.7,0.8};
     double Pr_array[] ={1.0,5.0,10.0,50.0,100.0,200.0};
     int y1=0,y2=0,x1=0,x2=0;

     for(int i=0;i<Tr_array.length-1;i++){
         if ( Tr < Tr_array[i+1] && Tr >Tr_array[i]){
             y1 = i;
             y2 = i+1;
         }
         else if ( Tr < Tr_array[0] ){
             y1 = 0;
             y2 = 1;
         }

         else if(Tr > Tr_array[Tr_array.length-1]){
             y1 = 2;
             y2 = 3;
         }
     }
        for(int j=0;j<Pr_array.length-1;j++){
            if ( Pr < Pr_array[j+1] && Pr >Pr_array[j]){
                x1 = j;
                x2 = j+1;
            }
            else if ( Pr < Pr_array[0]){
                x1 = 0;
                x2 = 1;
            }
            else if (Pr > Pr_array[Pr_array.length-1]) {
                x1 = 4;
                x2 = 5;
            }
        }
        double Tr1 = Tr_array[y1];
        double Tr2 = Tr_array[y2];
        double Pr1 = Pr_array[x1];
        double Pr2 = Pr_array[x2];

        double Q_ref1=Q_array[y1][x1];
        double Q_ref2=Q_array[y1][x2];
        double Q_ref3=Q_array[y2][x1];
        double Q_ref4=Q_array[y2][x2];

        double Qref13 = Q_ref1+(Q_ref3-Q_ref1) / ((Tr2-Tr1)/(Tr-Tr1));
        double Qref24 = Q_ref2+(Q_ref4-Q_ref2) / ((Tr2-Tr1)/(Tr-Tr1));
        double Qfinal = (Qref24-Qref13)*(Pr-Pr1)/(Pr2-Pr1)+Qref13;

        double k_high_pressure = (1+ Qfinal*Math.pow(Pr,0.7))*k_saturated;

        double Pvapor=0; // Birimi kPa
        Pvapor_c = values.getPvapor(name);
        try {
            Pvapor = Double.parseDouble(Pvapor(T));
        }
        catch (NumberFormatException e){
            e.printStackTrace();
            return "Pbuhar bilinmediği için hesap yapılamıyor."; //Pbuhar bilinmeli ki sivi compressed mi diye bakabileyim.
        }
        if(Pvapor !=0 && Pvapor >= P){
            k_high_pressure = k_saturated; // Eger girilen basinc Pbuhardan dusuk ise zaten compressed olmayacagi icin
            // direkt doymus deger alinacak.
        }

return ""+k_high_pressure;
    }

    public String k_Latini_and_Baroncini(String name,double T,double P){
        // Latini and Baroncini 1983 method
        // Bu metot yalnızca doymuş hidrokarbonlar ve aromatikler için geçerlidir.
        double A,A0,A1=0,Tb,Tc,Pc,M,Tr,Pr,Asharp,alfa,beta,gamma; // A1 değerini initialize etmem gerektiği için değer verdim.
        double k=0;
        double k_saturated;
        try{
            k_saturated = Double.parseDouble(k());
        }
        catch (NumberFormatException e){
            e.printStackTrace();
            return " Doyma basıncı için olan k değeri hesaplanamadı.";
        }

        double Pvapor=0; // Birimi kPa
        Pvapor_c = values.getPvapor(name);
        try {
            Pvapor = Double.parseDouble(Pvapor(T));
        }
        catch (NumberFormatException e){
            e.printStackTrace();
            return "Pbuhar bilinmediği için hesap yapılamıyor."; //Pbuhar bilinmeli ki sivi compressed mi diye bakabileyim.
        }
        if(Pvapor !=0 && Pvapor >= P){
            k = k_saturated; // Eger girilen basinc Pbuhardan dusuk ise zaten compressed olmayacagi icin
            // direkt doymus deger alinacak.
            return ""+k;
        }


        M=critical[0];
        Tb=critical[1];
        Tc=critical[2];
        Pc = critical[3]*100; // kPa yaptım birimini
        Tr=T/Tc;
        Pr=P/Pc;
        organiccompounds_classification=values.get_orgmat_classification(name);
        String malzeme_turu=values.malzemenin_turu;
        Asharp=organiccompounds_classification[0];
        alfa=organiccompounds_classification[1];
        beta=organiccompounds_classification[2];
        gamma=organiccompounds_classification[3];
        if(malzeme_turu.equals("saturated_hydrocarbon")==false && malzeme_turu.equals("aromatic")==false ){
            return " Bu metot sadece doymuş hidrokarbonlar ve aromatik malzemeler için kullanılabilir.";
        }
        if( M == 0 || Tb == 0 || Tc == 0 ){
            return "M, Tb, Tc değerlerinden en az biri bilinmiyor";
        }
        if(malzeme_turu.equals("saturated_hydrocarbon")){
          A1 = 0.0673/Math.pow(M,0.84);
        }
        else if(malzeme_turu.equals("aromatic")){
            A1 = 102.50/Math.pow(M,2.4);
        }
        if(Pr > 51) {
            return " Bu metot Pr değeri 50'den küçük olduğu durumlarda kullanılabilir Pr="+Pr;
        }
        if(k_c[3]<=T && T<=k_c[4])
        {
            A0=Asharp*Math.pow(Tb,alfa)/Math.pow(M,beta)/Math.pow(Tc,gamma);
            A = A0+A1*Pr;
            k=A*Math.pow(1-Tr,0.38)/Math.pow(Tr,0.166666);
            return (""+k);
        }
        else {
            return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
        }
    }

    //Flippov Equation
    public String k_mix_Flippov(double k1,double k2,double w1,double w2) {
        double k_mix=w1*k1+w2*k2-0.72*w1*w2*Math.abs((k2-k1));
        return ""+k_mix;
    }

    public void weight_fraction_to_mole_fraction(String name[], double w[]){
        double Ntotal=0;
        double M;
        double x[] = new double[name.length];
        for ( int i=0;i<name.length;i++){
            critical = values.get_critical(name[i]);
            M = critical[0];
            Ntotal += w[i]/M;
        }
        for ( int j=0;j<name.length;j++){
            critical = values.get_critical(name[j]);
            M = critical[0];
            x[j] = w[j]/M/Ntotal ;
            System.out.println("Sıvı: "+name[j]+ "  weight fraction: "+ w[j]+ " mole fraction: "+x[j]);
        }

    }
    public void M_mix(String name[], double w[]){
        double Ntotal=0;
        double M;
        double x[] = new double[name.length];
        for ( int i=0;i<name.length;i++){
            critical = values.get_critical(name[i]);
            M = critical[0];
            Ntotal += w[i]/M;
        }
        for ( int j=0;j<name.length;j++){
            critical = values.get_critical(name[j]);
            M = critical[0];
            x[j] = w[j]/M/Ntotal ;
            System.out.println("Sıvı: "+name[j]+ "  weight fraction: "+ w[j]+ " mole fraction: "+x[j]);
        }

    }

    //Flippov Equation
    public String k_mix_Filippov_x(double T,String name[], double x[]) { // molar fraction üzerinden hesap yapıyorum.
        // Orijinal halinde w üzerinden yapılıyor.
        if( name.length > 2) {
            return " Bu metot ikili karışımlar için uygundur";
        }
        double k1, k2,M1,M2,N1,N2,Mmix,kmix,x1,x2;
        k_c = values.getk(name[0]);
        critical = values.get_critical(name[0]);
        try{
            k1 = Double.parseDouble(k());
            //System.out.println("k1="+k1);
        }
        catch (NumberFormatException e){
            e.printStackTrace();
            return " Karışımdaki sıvılardan birinin ısıl iletkenliği hesaplamadığı için hesap yapılamıyor";
        }
        x1 = x[0];
        N1 = x[0];
        M1 = critical[0];
        k_c = values.getk(name[1]);
        critical = values.get_critical(name[1]);
        try{
            k2 = Double.parseDouble(k());
            //System.out.println("k2="+k2);
        }
        catch (NumberFormatException e){
            e.printStackTrace();
            return " Karışımdaki sıvılardan birinin ısıl iletkenliği hesaplamadığı için hesap yapılamıyor";
        }
        x2 = x[1];
        M2 = critical[0];
        N2 = x[1];
        x1 = N1/(N1+N2);
        x2 = N2/(N1+N2);
        Mmix = x1*M1+ x2*M2;
        kmix = (M1*x1)/(M1*x1+M2*x2)*k1+(M2*x2)/(M1*x1+M2*x2)*k2-0.72*(M1*x1)/(M1*x1+M2*x2)*(M2*x2)/(M1*x1+M2*x2)*Math.abs(k2-k1);
        for(int i=2;i<name.length;i++){
            k_c = values.getk(name[i]);
            critical = values.get_critical(name[i]);
            N2 = N1+N2;
            N1 = x[i];
            x1 = (N1)/(N1+N2);
            x2 = 1-x1;
            M2 = Mmix;
            M1 = critical[0];
            Mmix = x1*M1+ x2*M2;
            try{
                k1 = Double.parseDouble(k());
            }
            catch (NumberFormatException e){
                e.printStackTrace();
                return " Karışımdaki sıvılardan birinin ısıl iletkenliği hesaplamadığı için hesap yapılamıyor";
            }
            k2 = kmix;
            kmix = (M1*x1)/(M1*x1+M2*x2)*k1+(M2*x2)/(M1*x1+M2*x2)-0.72*(M1*x1)/(M1*x1+M2*x2)*(M2*x2)/(M1*x1+M2*x2)*Math.abs(k2-k1);
        }
     return ""+kmix;
    }

    public String k_mix_Baroncini(String name[], double x[]){
        // Baroncini 1981a,1983,1984

        if( name.length > 2) {
            return " Bu metot ikili karışımlar için uygundur";
        }
        double A,Tb,Tc,Pc,M,Trm,Pr,Asharp,alfa,beta,gamma; // A1 değerini initialize etmem gerektiği için değer verdim.
        double kmix=0;
        double Tc1, Tc2;
        double Tcm=0;
        double x1=x[0];
        double x2=x[1];
        double k1, k2;
        double A1,A2;
        critical = values.get_critical(name[0]);
        M=critical[0];
        Tb=critical[1];
        Tc=critical[2];
        Tc1 = Tc;
        Pc = critical[3]*100; // kPa yaptım birimini
        Pr=P/Pc;
        organiccompounds_classification=values.get_orgmat_classification(name[0]);
        Asharp=organiccompounds_classification[0];
        alfa=organiccompounds_classification[1];
        beta=organiccompounds_classification[2];
        gamma=organiccompounds_classification[3];
        k_c = values.getk(name[0]);

        if( M == 0 || Tb == 0 || Tc == 0 ){
            return "M, Tb, Tc değerlerinden en az biri bilinmiyor";}
        if(Asharp == 0  || beta == 0 || gamma == 0){
            return "Sıvılardan biri organik değil veya ailesi bilinmiyor";}
        A1 = Asharp* Math.pow(Tb,alfa)/Math.pow(M,beta)/Math.pow(Tc,gamma);

        critical = values.get_critical(name[1]);
        M=critical[0];
        Tb=critical[1];
        Tc=critical[2];
        Tc2 = Tc;
        Pc = critical[3]*100; // kPa yaptım birimini
        Pr=P/Pc;
        organiccompounds_classification=values.get_orgmat_classification(name[1]);
        Asharp=organiccompounds_classification[0];
        alfa=organiccompounds_classification[1];
        beta=organiccompounds_classification[2];
        gamma=organiccompounds_classification[3];
        k_c = values.getk(name[1]);


        if( M == 0 || Tb == 0 || Tc == 0 ){
            return "M, Tb, Tc değerlerinden en az biri bilinmiyor";}
        if(Asharp == 0  || beta == 0 || gamma == 0){
            return "Sıvılardan biri organik değil veya ailesi bilinmiyor";}
        A2= Asharp* Math.pow(Tb,alfa)/Math.pow(M,beta)/Math.pow(Tc,gamma);

        Tcm = x1*Tc1+x2*Tc2;
        Trm = T/Tcm;

        if ( A1 <= A2 ){
            kmix = (x1*x1*A1+x2*x2*A2+2.2*x1*x2*Math.pow(A1*A1*A1/A2,0.5))*Math.pow(1-Trm,0.38)/Math.pow(Trm,0.16667);
        }
        if ( A2 <= A1 ){
            kmix = (x1*x1*A1+x2*x2*A2+2.2*x1*x2*Math.pow(A2*A2*A2/A1,0.5))*Math.pow(1-Trm,0.38)/Math.pow(Trm,0.16667);
        }
        return ""+kmix;
    }


    // Li 1976
    public String k_mix_Li(String names[],double x[],double T){
        double ro = 0;
        double fi_number;
        double toplam_x_times_v = 0;
        double M;
        double Vc;
        for(int k = 0; k< names.length;k++){

            ro_c = values.getro(names[k]);
            critical = values.get_critical(names[k]);
            M = critical[0];
            Vc = critical[4];
            if( M == 0 ){
                return names[k]+" malzemesinin M değeri bilinmiyor";
            }
            try {
                ro = Double.parseDouble(ro(T));
                toplam_x_times_v += x[k]*(1/ro)*M;
                //toplam_x_times_v += x[k]*Vc;
            }
            catch(NumberFormatException e ){
                e.printStackTrace();
                //System.out.println(names[k]+" malzemesinin özgül hacmi hesaplanamıyor.");
                return names[k]+ " malzemesinin özgül hacmi hesaplatılamıyor";
            }
        }
        double fi_number_i;
        double fi_number_j;
        double ki;
        double kj;
        double kji;
        double roi;
        double roj;
        double xi;
        double xj;
        double Mi;
        double Mj;
        double Vci,Vcj;
        double k = 0;
        for ( int i=0;i< names.length;i++){
            k_c = values.getk(names[i]);
            ro_c = values.getro(names[i]);
            critical = values.get_critical(names[i]);
            xi = x[i];
            Mi = critical[0];
            Vci = critical[4];
            if( Mi == 0){
                return names[i]+" malzemesinin M değeri bilinmiyor";
            }
            try{
                roi = Double.parseDouble(ro());
            }
            catch (NumberFormatException e){
                e.printStackTrace();
               // System.out.println(names[i]+" malzemesinin özgül hacmi hesaplanamıyor.");
                return names[i]+" malzemesinin özgül hacim değeri hesaplanamıyor";
            }
            try{
                ki = Double.parseDouble(k());
                //System.out.println("ki="+ki);
            }
            catch (NumberFormatException e){
                e.printStackTrace();
                //System.out.println(names[i]+" malzemesinin ısıl iletkenlik değeri hesaplanamıyor.");
                return names[i]+" malzemesinin ısıl iletkenlik değeri hesaplanamıyor";
            }
            for(int j=0;j< names.length;j++){
                k_c = values.getk(names[j]);
                ro_c = values.getro(names[j]);
                critical = values.get_critical(names[j]);
                Mj = critical[0];
                Vcj = critical[4];
                if( Mj == 0){
                    return names[j]+" malzemesinin M değeri bilinmiyor";
                }
                try{
                    roj = Double.parseDouble(ro());
                }
                catch (NumberFormatException e){
                    e.printStackTrace();
                    //System.out.println(names[j]+" malzemesinin özgül hacmi hesaplanamıyor.");
                    return names[j]+" malzemesinin özgül hacim değeri hesaplanamıyor";
                }
                try{
                    kj = Double.parseDouble(k());
                   // System.out.println("kj="+kj);
                }
                catch (NumberFormatException e){
                    e.printStackTrace();
                  //  System.out.println(names[j]+" malzemesinin ısıl iletkenlik değeri hesaplanamıyor.");
                    return names[j]+" malzemesinin ısıl iletkenlik değeri hesaplanamıyor";
                }
                xj = x[j];
                fi_number_i = xi/roi*Mi/toplam_x_times_v;
                //fi_number_i = xi*Vci/toplam_x_times_v;
                fi_number_j = xj/roj*Mj/toplam_x_times_v;
                //fi_number_j = xj*Vcj/toplam_x_times_v;
                kji = 2/(1/ki+1/kj);
                k += fi_number_i*fi_number_j*kji;
            }
        }
        return ""+k;
    }

    public String k_mix_PowerLaw(String names[],double x[],double T){
        // Vredeveld 1973
        double total_x_times_M=0;
        double ki;
        double Mi;
        double kmix=0;
        for ( int k=0;k<names.length;k++){
            critical = values.get_critical(names[k]);
            total_x_times_M += x[k]*critical[0];
        }
        for ( int i=0;i<names.length;i++){
        k_c = values.getk(names[i]);
        critical = values.get_critical(names[i]);
        Mi = critical[0];
        if( Mi == 0.0){
            return names[i]+" malzemesinin M değeri bilinmiyor";
        }
        try{
            ki = Double.parseDouble(k());
        }
        catch (NumberFormatException e){
            e.printStackTrace();
            return names[i]+" malzemesinin ısıl iletkenlik değeri hesaplanamıyor";
        }
        kmix += x[i]*Mi/total_x_times_M/ki/ki;

    }
        kmix = Math.pow(kmix,-0.5);
        return ""+kmix;
    }

    public double vis(String name,double T) {
        double A,B,C,D;
        double vis=0;
        vis_c = values.getvis(name);
        if(vis_c[4]<=(T+10) && T<=vis_c[5]+10)
        {
            A=vis_c[0];
            B=vis_c[1];
            C=vis_c[2];
            D=vis_c[3];
            vis=Math.pow(10.0, A+B/T+C*T+D*T*T); // kitaptan çekilen katsayılar ile elde edilen değerler centipoise birimindedir
            vis=vis/1000; // Pa.s birimine çevirdim
        }
    return vis;
    }
    public String vis1(String name,double T) { // Bunu  vis_Lucas ile kullanarak basıncın etkisini görebilmek için yapıyorum sadece.
        // Bu metodu GUI'ye  eklemeyeceğim.
        double A,B,C,D;
        double vis=0;
        vis_c=values.getvis(name);
        critical=values.get_critical(name); // Sonra düzeltmek gerek
        if(vis_c[4]<=(T+10) && T<=vis_c[5]+10)
        {
            A=vis_c[0];
            B=vis_c[1];
            C=vis_c[2];
            D=vis_c[3];
            vis=Math.pow(10.0, A+B/T+C*T+D*T*T); // kitaptan çekilen katsayılar ile elde edilen değerler centipoise birimindedir
            vis=vis/1000; // Pa.s birimine çevirdim
            return (""+vis);
        }
        else {
            return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
        }
    }
    public double vis_Lucas(String name,double T ,double P,String tip) {
        // Bu hesaplamada basınç değeri de hesaba katılacak.
        // Lucas (1981) tarafından oluşturulan bir eşitlik. Sıkıştırılmış sıvı için olan viskoziteyi hesaplar.
        double A,B,C,D;
        double vis_sat;
        double Pc,w,Tc;
        double vis=0;
        double Pvapor=0; // Birimi kPa
        Pvapor_c = values.getPvapor(name);
        try {
            Pvapor = Double.parseDouble(Pvapor(T));
        }
        catch (NumberFormatException e){
            e.printStackTrace();
            return 0.0 ;
        }
        vis_c = values.getvis(name);
        critical = values.get_critical(name); // Sonra düzeltmek gerek
        Pc = critical[3]*100; // Birimi kPa yapmak için böyle yaptım.
        w = critical[7];
        Tc = critical[2];
        if(vis_c[4] <= T && T <= vis_c[5]){
            if(Pc !=0 && Tc !=0 && w !=0 && Pvapor != 0){
                double Tr = T/Tc;
                double Pr = P/Pc;
                double deltaPr = (P-Pvapor)/Pc;// bar cinsinde verilecek.
                A = 0.9991- (4.674E-4 / (1.0523* Math.pow(Tr,-0.03877)-1.0513));
                C = -0.07921+2.1616*Tr-13.404*Tr*Tr+44.1706*Tr*Tr*Tr-84.8291*Tr*Tr*Tr*Tr+ 96.1209 * Tr* Tr*Tr*Tr*Tr - 59.8127*Tr*Tr*Tr*Tr*Tr*Tr+
                        15.6719*Tr*Tr*Tr*Tr*Tr*Tr*Tr;
                D= (0.3257 /Math.pow (1.0039- Math.pow(Tr,2.573),0.2906))-0.2086;
                try {
                    vis_sat = Double.parseDouble(vis1(name,T));
                    vis = vis_sat * ( 1+ D*Math.pow((deltaPr/2.118),A))/(1+C*w*deltaPr);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return 0.0;  }
                if( P > Pvapor){return vis;}
                else{ return vis_sat;  }
            }
            else {  return 0.0;  }
        }
        else{  return 0.0; }
    }

    public String vis_Lucas(String name,double T ,double P) { // Bu hesaplamada basınç değeri de hesaba katılacak.
        // Lucas (1981) tarafından oluşturulan bir eşitlik. Sıkıştırılmış sıvı için olan viskoziteyi hesaplar.
        double A,B,C,D;
        double vis_sat;
        double Pc,w,Tc;
        double vis=0;
        double Pvapor=0; // Birimi kPa
        Pvapor_c = values.getPvapor(name);
        try {
             Pvapor = Double.parseDouble(Pvapor());
        }
        catch (NumberFormatException e){
            e.printStackTrace();
            return " Buhar basıncı hesaplanamadığı için işlem yapılamıyor";
        }
        vis_c=values.getvis(name);
        critical=values.get_critical(name); // Sonra düzeltmek gerek
        Pc = critical[3]*100; // Birimi kPa yapmak için böyle yaptım.
        w= critical[7];
        Tc = critical[2];
        if(vis_c[4]<=T && T<=vis_c[5]){
            if(Pc !=0 && Tc !=0 && w !=0 && Pvapor != 0){
                double Tr = T/Tc;
                double Pr = P/Pc;
                double deltaPr = (P-Pvapor)/Pc;// bar cinsinde verilecek.
                A = 0.9991- (4.674E-4 / (1.0523* Math.pow(Tr,-0.03877)-1.0513));
                C = -0.07921+2.1616*Tr-13.404*Tr*Tr+44.1706*Tr*Tr*Tr-84.8291*Tr*Tr*Tr*Tr+ 96.1209 * Tr* Tr*Tr*Tr*Tr - 59.8127*Tr*Tr*Tr*Tr*Tr*Tr+
                        15.6719*Tr*Tr*Tr*Tr*Tr*Tr*Tr;
                D= (0.3257 /Math.pow (1.0039- Math.pow(Tr,2.573),0.2906))-0.2086;
                try {
                    vis_sat = Double.parseDouble(vis1(name,T));
                    vis = vis_sat * ( 1+ D*Math.pow((deltaPr/2.118),A))/(1+C*w*deltaPr);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    return " Bu sıcaklık aralığında hesap yapılamıyor.";  }

                if( P > Pvapor){return ""+vis;}
                else{ return ""+vis_sat;  }
                            }
            else {  return "Bilinmeyen bir kritik değer olduğu için hesaplama yapılamıyor";  }
        }
        else{  return "Bu sıcaklık aralığında hesaplama yapılamıyor"; }
    }
    public String vis() {
        double A,B,C,D;
        double vis=0;
        if(vis_c[4]<=T && T<=vis_c[5])
        {
            A=vis_c[0];
            B=vis_c[1];
            C=vis_c[2];
            D=vis_c[3];
            vis=Math.pow(10.0, A+B/T+C*T+D*T*T); // kitaptan çekilen katsayılar ile elde edilen değerler centipoise birimindedir
            vis=vis/1000; // Pa.s birimine çevirdim
            return (""+vis);
        }
        else {
            return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
        }
    }
    public String vis_GCM() { // Burada V değerlerini ro metodunu kullanarak hesapladım.

        // Przezdziecki and Sridhar Yöntemi ( Group Contribution Yöntemlerinden biri)

        double Tr, w, H1, H2, Tc, Vc, Pc, V;
        double Vm, Vo, E;
        Pc = critical[3];
        w = critical[7];
        Tc = critical[2]; // Kelvin
        Vc = critical[4]; // (ml/mol), ( cm^3/mol) ikisi aynı şey
        double M = critical[0];
        double vis = 0;
        Tr= T/Tc;

        try {
            Vm = M / Double.parseDouble(ro(Tf)) * 1000;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "Tkritik için yoğunluk bilinmediği için hesaplama yapılamıyor";
        }
        try {
         V = M / Double.parseDouble(ro()) * 1000;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "Yoğunluk bilinmediği için hesaplama yapılamıyor";
        }
        Vo = 0.0085 * w * Tc - 2.02 + Vm / (0.342 * (Tf / Tc) + 0.894);
        E = -1.12 + Vc / (12.94 + 0.10 * M - 0.23 * Pc + 0.0424 * Tf - 11.58 * (Tf / Tc));
        vis = Vo / E / (V - Vo);

        if( Tf == 0   ){
            return "Tf bilinmediği için hesaplama yapılamıyor.";
        }
        else if( Tr< 0.50){
            return "Tr, 0.50'den küçük olduğu için hesaplama yapılamıyor.";
        }
        else if( w==0){
            return "Accentric factor bilinmediği için hesaplama yapılamıyor.";
        }
        else {
            vis = vis / 1000; // Pa.s birimine çevirdim
            return ("" + vis);        }
    }
    public double vis_Przezdziecki_and_Sridhar (String name,double T,String tip){ // 1985 yılında geliştirilmiş.
        // vis = Vo/(E(V-Vo));
        // String tipi bu metot ile String döndüren metot karışmasın diye ekledim. Başka bir amacı yok.
        critical = values.get_critical(name);
        ro_c = values.getro(name);
        Tf = values.getTf(name);
        double Vm=0, Vo, E;
        double Pc = critical[3]; //bar
        double w = critical[7];
        double Tc = critical[2]; // Kelvin
        double Vc = critical[4]; // (ml/mol), ( cm^3/mol) ikisi aynı şey
        double M = critical[0];  // g/mol
        double Tfreezing = Tf;
        double V;
        double vis=0;
        if (w == 0){
            return 0;
        }
        if(Tfreezing == 0){
            return 0;
        }
        if( Pc!=0.0 && Tc != 0.0 && w != 0.0 && Vc != 0.0 && Tfreezing != 0){
            double Tr = T/Tc;
            try
            {
                Vm  = 1/Double.parseDouble(ro(Tfreezing))*1000*M;
                V  = 1/Double.parseDouble(ro(T))*1000*M;
            }
            catch (NumberFormatException e ){
                e.printStackTrace();
                return 0;
            }



            Vo = 0.0085 * w * Tc - 2.02 + Vm / (0.342 * (Tfreezing / Tc) + 0.894);
            E = -1.12 + Vc / (12.94 + 0.10 * M - 0.23 * Pc + 0.0424 * Tfreezing - 11.58 * (Tfreezing / Tc));
            vis= Vo/E/(V-Vo);
            vis = vis/1000; // Birimini çevirdim.
        }
        return vis;
    }


    public String vis_Przezdziecki_and_Sridhar (String name,double T){ // 1985 yılında geliştirilmiş.
        // vis = Vo/(E(V-Vo));
        critical = values.get_critical(name);
        ro_c = values.getro(name);
        Tf = values.getTf(name);
        double Vm=0, Vo, E;
        double Pc = critical[3]; //bar
        double w = critical[7];
        double Tc = critical[2]; // Kelvin
        double Vc = critical[4]; // (ml/mol), ( cm^3/mol) ikisi aynı şey
        double M = critical[0];  // g/mol
        double Tfreezing = Tf;
        double V;
        double vis=0;
        if (w == 0){
            return "w değeri bilinmediği için hesap yapılamıyor";
        }
        if(Tfreezing == 0){
            return "Tf değeri bilinmediği için hesap yapılamıyor";
        }
        if( Pc!=0.0 && Tc != 0.0 && w != 0.0 && Vc != 0.0 && Tfreezing != 0){
            double Tr = T/Tc;
            try
            {
                Vm  = 1/Double.parseDouble(ro(Tfreezing))*1000*M;
                V  = 1/Double.parseDouble(ro(T))*1000*M;
            }
            catch (NumberFormatException e ){
                e.printStackTrace();
                return "Vm hesaplanamadığı için işlem yapılamıyor";
            }

            Vo = 0.0085 * w * Tc - 2.02 + Vm / (0.342 * (Tfreezing / Tc) + 0.894);
            E = -1.12 + Vc / (12.94 + 0.10 * M - 0.23 * Pc + 0.0424 * Tfreezing - 11.58 * (Tfreezing / Tc));
            vis= Vo/E/(V-Vo);
            vis = vis/1000; // Birimini çevirdim.
        }
          return ""+vis;
    }
    public double vis_Letsou_and_Stiel(String name, double T){
        double Pc = critical[3]; //bar
        double w = critical[7];
        double Tc = critical[2]; // Kelvin
        double Vc = critical[4]; // (ml/mol), ( cm^3/mol) ikisi aynı şey
        double M = critical[0];  // g/mol
        double Tr = T/Tc;
        if( Tr <0.98 && Tr > 0.76 ) {
            double epsilon = 0.176 * Math.pow(Tc / (M * M * M) / (Pc * Pc * Pc * Pc), 0.16666667);
            double epsilon0= (2.648-3.725*Tr+1.309*Tr*Tr)/1000;
            double epsilon1= (7.425-13.39*Tr+5.933*Tr*Tr)/1000;
            double vis= (epsilon0+w*epsilon1)/epsilon; // Birimi centipoise(cP)
            return vis/1000; // Birimini Pa.s yaptım.
        }
        else{
            return 0;
        }
    }

    public String vis_Letsou_and_Stiel(){
        double Pc = critical[3]; //bar
        double w = critical[7];
        double Tc = critical[2]; // Kelvin
        double Vc = critical[4]; // (ml/mol), ( cm^3/mol) ikisi aynı şey
        double M = critical[0];  // g/mol
        double Tr = T/Tc;
        if( Tr <0.98 && Tr > 0.76 ) {
            double epsilon = 0.176 * Math.pow(Tc / (M * M * M) / (Pc * Pc * Pc * Pc), 0.16666667);
            double epsilon0= (2.648-3.725*Tr+1.309*Tr*Tr)/1000;
            double epsilon1= (7.425-13.39*Tr+5.933*Tr*Tr)/1000;
            double vis= (epsilon0+w*epsilon1)/epsilon; // Birimi centipoise(cP)
            return ""+vis/1000; // Birimini Pa.s yaptım.
        }
        else{
            return "Tr değeri 0.76 ile 0.98 arasında olmalıdır";
        }
    }

    public String vis_Teja_and_Rice(String name[], double x[],double P){
        double Pc; //bar
        double w;
        double Tc; // Kelvin
        double Vc; // (ml/mol), ( cm^3/mol) ikisi aynı şey
        double M;  // g/mol
        double epsilon1,epsilon2,epsilonm;
        double vis1,vis2;
        double T1,T2;
        double vis_mix=0;
        double Tc1,Tc2,Tcm;
        double Vcij,Vc1,Vc2,Vcm;
        double w1,w2,wm; //accentric factor
        double x1,x2,M1,M2,Mm;
        double interaction_coefficient=1.00; // susuz karışımlar için
        double N1,N2; // mol sayısı
        DecimalFormatSymbols symbol= new DecimalFormatSymbols();
        symbol.setDecimalSeparator('.');
        NumberFormat formatter = new DecimalFormat("#0.0000000",symbol);

        if (name[0].equals("H2O_water") || name[1].equals("H2O_water")) {
            interaction_coefficient = 1.37;
        }

        critical=values.get_critical(name[0]);
        Pc = critical[3];
        w = critical[7];
        Tc = critical[2];
        Vc = critical[4];
        M1 = critical[0];
        x1= x[0];
        N1=x1;
        Tc1 = Tc;
        Vc1 = Vc;
        w1 = w;

        critical=values.get_critical(name[1]);
        Pc = critical[3];
        w = critical[7];
        Tc = critical[2];
        Vc = critical[4];
        M2 = critical[0];
        x2=x[1];
        N2=x2;
        Tc2=Tc;
        Vc2=Vc;
        w2 = w;

        x1 = (x1)/(x1+x2);
        x2 = 1-x1;

        if( Tc1 != 0.0 && w1 != 0.0 && Vc1 != 0.0 && M1 != 0 && Tc2 != 0.0 && w2 != 0.0 && Vc2 != 0.0 && M2 != 0){
            epsilon1 = Math.pow(Vc1,0.6666)/Math.pow(Tc1*M1,0.5);
            epsilon2 = Math.pow(Vc2,0.6666)/Math.pow(Tc2*M2,0.5);
            Vcij = (Math.pow(Vc1,0.3333)+Math.pow(Vc2,0.3333))/8;
            Vcm = x1*x1*Vc1+2*x1*x2*Math.pow((Math.pow(Vc1,0.3333)+Math.pow(Vc2,0.3333)),3)/8+x2*x2*Vc2;
            Mm= x1*M1+x2*M2;
            wm= x1*w1+x2*w2;
            Tcm = 1/Vcm*(x1*x1*Math.pow(Tc1*Tc1*Vc1*Vc1,0.5)+2*x1*x2*interaction_coefficient*Math.pow(Tc1*Tc2*Vc1*Vc2,0.5)+x2*x2*Math.pow(Tc2*Tc2*Vc2*Vc2,0.5));
            epsilonm=Math.pow(Vcm,0.6666)/Math.pow(Tcm*Mm,0.5);

                try {
                    vis_c = values.getvis(name[0]);
                    vis1 = Double.parseDouble(vis1(name[0],T*Tc1/Tcm ));
                    vis_c = values.getvis(name[1]);
                    vis2 = Double.parseDouble(vis1(name[1],T*Tc2/Tcm));
                    vis_mix = Math.pow(Math.E,Math.log(vis1*epsilon1) + (Math.log(vis2*epsilon2)- Math.log(vis1*epsilon1))*((wm-w1)/(w2-w1)))/epsilonm;
                   // System.out.println("Katsayılar ile hesaplandı.");

                }
                catch (NumberFormatException e1){
                    e1.printStackTrace();

                    try{
                        vis_c = values.getvis(name[0]);
                        ro_c = values.getvis(name[0]);
                        vis1 = Double.parseDouble(vis_Przezdziecki_and_Sridhar(name[0],T*Tc1/Tcm ));
                        vis_c = values.getvis(name[1]);
                        ro_c = values.getvis(name[1]);
                        vis2 = Double.parseDouble(vis_Przezdziecki_and_Sridhar(name[1],T*Tc2/Tcm));
                        vis_mix = Math.pow(Math.E,Math.log(vis1*epsilon1) + (Math.log(vis2*epsilon2)- Math.log(vis1*epsilon1))*((wm-w1)/(w2-w1)))/epsilonm;
                       // System.out.println("Przezdziecki ve Sridhar yöntemi ile hesaplandı.");

                    }
                    catch (NumberFormatException e2){
                        e2.printStackTrace();
                        return "İki farklı yöntem ile denenmesine rağmen referans viskoziteleri hesaplanamdı";

                    }


            }
        }
        else{
            return "Kritik değerlerden birisi veya molar kütle bilinmiyor";
        }

        // Burada x[i]'ler hesaplanırken bir hata oluşmuş olabilir. Kontrol et.

        for(int i=2;i<name.length;i+=1){
            if(name[i].equals("H2O_water"))
            {
                interaction_coefficient= 1.37;
            }
            //System.out.println("interaction coefficient="+interaction_coefficient);
            critical=values.get_critical(name[i]);
            Pc = critical[3];
            w = critical[7];
            Tc = critical[2];
            Vc = critical[4];
            M1 = critical[0];
            N2=N1+N2;
            N1=x[i];
            x1= x[i]/(x[i]+N1+N2);
            Tc1 = Tc;
            Vc1 = Vc;
            w1 = w;



            M2 =Mm;
            x2=1-x1;
            Tc2=Tcm;
            Vc2=Vcm;
            w2 = wm;

            if( Tc1 != 0.0 && w1 != 0.0 && Vc1 != 0.0 && M1 != 0 && Tc2 != 0.0 && w2 != 0.0 && Vc2 != 0.0 && M2 != 0){
                epsilon1 = Math.pow(Vc1,0.6666)/Math.pow(Tc1*M1,0.5);
                epsilon2 = Math.pow(Vc2,0.6666)/Math.pow(Tc2*M2,0.5);
                Vcij = (Math.pow(Vc1,0.3333)+Math.pow(Vc2,0.3333))/8;
                Vcm = x1*x1*Vc1+2*x1*x2*Math.pow((Math.pow(Vc1,0.3333)+Math.pow(Vc2,0.3333)),3)/8+x2*x2*Vc2;
                Mm= x1*M1+x2*M2;
                wm= x1*w1+x2*w2;
                Tcm = 1/Vcm*(x1*x1*Math.pow(Tc1*Tc1*Vc1*Vc1,0.5)+2*interaction_coefficient*x1*x2*Math.pow(Tc1*Tc2*Vc1*Vc2,0.5)+x2*x2*Math.pow(Tc2*Tc2*Vc2*Vc2,0.5));
                epsilonm=Math.pow(Vcm,0.6666)/Math.pow(Tcm*Mm,0.5);
                try{

                   // System.out.println("vis1="+vis1(name[0],T*Tc1/Tcm ));
                   // System.out.println("vis2="+vis1(name[1],T*Tc2/Tcm ));
                    vis_c = values.getvis(name[i]);
                    vis1 = Double.parseDouble(vis_Lucas(name[i],T*Tc1/Tcm,P ));
                    vis2 = vis_mix;
                    vis_mix = Math.pow(Math.E,Math.log(vis1*epsilon1) + (Math.log(vis2*epsilon2)- Math.log(vis1*epsilon1))*((wm-w1)/(w2-w1)))/epsilonm;
                    //System.out.println("Lucas metodu ile hesaplandı.");
                }
                catch(NumberFormatException e ){
                    e.printStackTrace();

                    try {
                        vis_c = values.getvis(name[i]);
                        vis1 = Double.parseDouble(vis1(name[i],T*Tc1/Tcm ));
                        vis2 = vis_mix;
                        vis_mix = Math.pow(Math.E,Math.log(vis1*epsilon1) + (Math.log(vis2*epsilon2)- Math.log(vis1*epsilon1))*((wm-w1)/(w2-w1)))/epsilonm;
                        //System.out.println("Katsayılar ile hesaplandı.");

                    }
                    catch (NumberFormatException e1){
                        e1.printStackTrace();
                        try{
                            vis_c = values.getvis(name[i]);
                            ro_c = values.getvis(name[i]);
                            vis1 = Double.parseDouble(vis_Przezdziecki_and_Sridhar(name[i],T*Tc1/Tcm ));
                            vis2 =vis_mix;
                            vis_mix = Math.pow(Math.E,Math.log(vis1*epsilon1) + (Math.log(vis2*epsilon2)- Math.log(vis1*epsilon1))*((wm-w1)/(w2-w1)))/epsilonm;
                           // System.out.println("Przezdziecki ve Sridhar yöntemi ile hesaplandı.");
                        }
                        catch (NumberFormatException e2){
                            e2.printStackTrace();
                            return "Üç farklı yöntem ile denenmesine rağmen referans viskoziteleri hesaplanamdı";
                        }
                    }
                    //return "Referans viskozite değerleri hesaplanamadığı için hesapl yapılamıyor";
                }
                return ""+formatter.format(vis_mix);
            }
            else{
                return "Kritik değerlerden birisi veya molar kütle bilinmiyor";
            }
        }
        return ""+formatter.format(vis_mix);
    }

    public double vis_CSP(String name, double T) { // Burada V değerlerini ro metodunu kullanarak hesapladım.
// Bu metot çoğu sıvı için hesaplama yapamıyor.
        // Przezdziecki and Sridhar Yöntemi ( CSP)
        critical = values.get_critical(name);
        double Tr, w, H1, H2, Tc, Vc, Pc, V;
        double Vm, Vo, E;
        Pc = critical[3];
        w = critical[7];
        Tc = critical[2]; // Kelvin
        Vc = critical[4]; // (ml/mol), ( cm^3/mol) ikisi aynı şey
        double M = critical[0];
        double vis = 0.0;
        Tr= T/Tc;
        try {
            Vm = M / Double.parseDouble(ro(Tf)) * 1000;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0;
        }
        try {
            V = M / Double.parseDouble(ro()) * 1000;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0;
        }
         if( Tc != 0 && Vc != 0 && Pc !=0 && w != 0 && M != 0 && Tr>0.50){
            Vo = 0.0085 * w * Tc - 2.02 + Vm / (0.342 * (Tf / Tc) + 0.894);
            E = -1.12 + Vc / (12.94 + 0.10 * M - 0.23 * Pc + 0.0424 * Tf - 11.58 * (Tf / Tc));
            vis = Vo / E / (V - Vo);
            vis = vis / 1000; // Pa.s birimine çevirdim
        }

         return vis;

    }
    public String vis_GCM2() { // Viskozite hesabını yaparken V değerlerini fT üzerinden hesaplamaya çalışacağım.

        // Przezdziecki and Sridhar Yöntemi ( Group Contribution Yöntemlerinden biri)

        double Tr, w, H1, H2, Tc, Vc, Pc, V;
        double fTreferans;// f(Treferans);
        double Vreferans, Treferans;
        double fT; // f(T) demek istedim.
        double Vm, Vo, E;
        Pc = critical[3];
        w = critical[7];
        Tc = critical[2]; // Kelvin
        Vc = critical[4]; // (ml/mol), ( cm^3/mol) ikisi aynı şey
        Vreferans = Vc;
        Treferans = Tc;
        double M = critical[0];
        double vis = 0;
        Tr = Tf / Tc;
        H1 = 0.33593 - 0.33953 * Tr + 1.51941 * Tr * Tr - 2.02512 * Tr * Tr * Tr + 1.11422 * Tr * Tr * Tr * Tr;
        H2 = 0.29607 - 0.09045 * Tr - 0.04842 * Tr * Tr;
        fT = H1 * (1 - w * H2); // Donma sıcaklığı için yapılan hesaplama Bunun yardımı ile Vm bulunacak.
        /*System.out.println("Yazma İşlemi Başlıyor.");
        System.out.println("Tf:"+Tf);
        System.out.println("H1:"+H1);
        System.out.println("H2:"+H2);
        System.out.println("fT:"+fT);
        System.out.println("Treferans(Tfp):"+Treferans);
        System.out.println("w"+w);*/


        double x = (Treferans / Tc);
        H1 = 0.33593 - 0.33953 * x + 1.51941 * x * x - 2.02512 * x * x * x + 1.11422 * x * x * x * x;
        H2 = 0.29607 - 0.09045 * x - 0.04842 * x * x;
        fTreferans = H1 * (1 - w * H2); // referans noktası olması için kullanılan fT.
        // Bu fT hem Vm hesabında hem de girdiğimiz sıcaklık için yapılan V hesabında kullanılacak.
        // Referans noktası olarak kritik sıcaklığı seçtim. Çünkü orası için V değerleri tablolarda mevcut.
        /*System.out.println("H1:"+H1);
        System.out.println("H2:"+H2);
        System.out.println("fTreferans:"+fTreferans);*/

//        Vm = fT * Vreferans / fTreferans;
//        Vm=95.2;
        try {
            Vm = M / Double.parseDouble(ro(Tf)) * 1000;
//          Vreferans=106.87;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "Bu sıcaklıkta hesaplama yapılamıyor";
        }


        //System.out.println("Vm:"+Vm);


        Tr = T / Tc;


        H1 = 0.33593 - 0.33953 * Tr + 1.51941 * Tr * Tr - 2.02512 * Tr * Tr * Tr + 1.11422 * Tr * Tr * Tr * Tr;
        H2 = 0.29607 - 0.09045 * Tr - 0.04842 * Tr * Tr;
        fT = H1 * (1 - w * H2);
        H1 = 0.33593 - 0.33953 * x + 1.51941 * x * x - 2.02512 * x * x * x + 1.11422 * x * x * x * x;
        H2 = 0.29607 - 0.09045 * x - 0.04842 * x * x;


//        fTreferans = H1 * (1 - w * H2);
        fTreferans=0.340;

        /*System.out.println("H1:"+H1);
        System.out.println("H2:"+H2);
        System.out.println("fT:"+fT);*/

        try {
            Vreferans = M / Double.parseDouble(ro()) * 1000;
            V = M / Double.parseDouble(ro()) * 1000;
//       Vreferans=106.87;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "Bu sıcaklıkta hesaplama yapılamıyor";
        }
//        V = fT * Vreferans / fTreferans;




        Vo = 0.0085 * w * Tc - 2.02 + Vm / (0.342 * (Tf / Tc) + 0.894);
        E = -1.12 + Vc / (12.94 + 0.10 * M - 0.23 * Pc + 0.0424 * Tf - 11.58 * (Tf / Tc));
        vis = Vo / E / (V - Vo);
      /*  System.out.println("V:"+V);
        System.out.println("Vreferans:"+Vreferans);
        System.out.println("Vo:"+Vo);
        System.out.println("E:"+E);
        System.out.println("vis:"+vis);*/

//        for (int i = 0; i < vis_c.length; i++) {
//            System.out.println("vis_c[" + i + "]=" + vis_c[i]);
//        }

       /* if(Tr > 0.55)
        {
            vis=vis/1000; // Pa.s birimine çevirdim
            return (""+vis);
        }
        else {
            return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
        }
    }*/
        if( Tf != 0 && (Tr > 0.50)){ // Aslında kitapta 0.55 yazıyordu ama sınırdaki değerlerde sıkıntı olmasın diye böyle yaptım.
            vis = vis / 1000; // Pa.s birimine çevirdim
            return ("" + vis);
        }
        else if( w == 0.0 ){
            return "Accentric factor bilinmiyor.";
        }
        else {
            return "Tf değeri bilinmediği için hesap yapılamıyor.";
        }


    }

    public String vis_centipoise() {
        double A,B,C,D;

        double vis=0;

        if(vis_c[4]<=T && T<=vis_c[5])
        {
            A=vis_c[0];
            B=vis_c[1];
            C=vis_c[2];
            D=vis_c[3];
            vis=Math.pow(10.0, vis_c[0]+vis_c[1]/T+vis_c[2]*T+vis_c[3]*T*T); // kitaptan çekilen katsayılar ile elde edilen değerler centipoise birimindedir
            vis=vis/1000; // Pa.s birimine çevirdim
            return (""+vis);
        }
        else {
            return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
        }
    }

    public String ro() {
        double A,B,C,n;
        double ro=0;
        //System.out.println("A="+ro_c[0]+" B="+ro_c[1]+" C="+ro_c[2]+" n="+ro_c[3]+" T="+T);

        if(ro_c[4]<=(T+5.0) && (T-5.0)<=ro_c[5])
        {
            A=ro_c[0];
            B=ro_c[1];
            C=ro_c[2];
            n=ro_c[3];
            ro=A*Math.pow(B, -Math.pow((1-T/C), n)); // g/ml birimindedir.
            ro*=1000; // Birimi kg/m^3 yaptım.
            //System.out.println("ro metodu içinde:density="+ro);
            //System.out.println("A="+A+" B="+B+" C="+C+" n="+n);
            return (""+ro);
        }
        else {
            return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
        }
    }

    public String ro(double T) {
        double A,B,C,n;

        double ro=0;

        if(ro_c[4]<=(T+5.0) && (T-5.0)<=ro_c[5])
        {
            A=ro_c[0];
            B=ro_c[1];
            C=ro_c[2];
            n=ro_c[3];
            ro=A*Math.pow(B, -Math.pow((1-T/C), n)); // g/ml birimindedir.
            ro*=1000; // Birimi kg/m^3 yaptım.
            //System.out.println("density="+ro);
            return (""+ro);

        }
        else {
            return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
        }
    }
    public double ro(String name,double T) {
        double A,B,C,n;
         ro_c = values.getro(name);
         Pvapor_c = values.getPvapor(name); // Grafik çizdirirken bazı metotlarda Pvapor
        // kullanılması gerekiyor. Grafik kısmında ro için kullanılan ilk metot bu olduğu
        // için bunda Pvapor katsayılarını okutuverdim.
        double ro=0;
            A=ro_c[0];
            B=ro_c[1];
            C=ro_c[2];
            n=ro_c[3];
            ro=A*Math.pow(B, -Math.pow((1-T/C), n)); // g/ml birimindedir.
            ro*=1000; // Birimi kg/m^3 yaptım.
            //System.out.println("ro metodu içinde:density="+ro);
            //System.out.println("A="+A+" B="+B+" C="+C+" n="+n);
            return ro;
    }


    public double ro_Rackett(String name,double T){ // Rackett Equation ile yapılmıştır.
        double ro=0;
        double Vc,Zc,Tc,M;
        critical = values.get_critical(name);
        double Vs; // saturated liquid volume
        Vc = critical[4]; // ml/mol
        Zc = critical[6];
        Tc = critical[2]; // K
        M = critical[0];  // g/mol
        if(Vc != 0 && Zc != 0 && Tc !=0){

            Vs = Vc*Math.pow(Zc,Math.pow(1-T/Tc,0.2857)); // ml/mol
        Vs = Vs/1000000; // m^3/mol
        Vs = Vs/M; // m^3/g
        Vs = Vs *1000; // (m^3/kg)
        ro = 1/Vs; // kg/(m^3)
             }
        return ro;
    }
    public double ro_Yamada_Gunn(String name,double T){ // Yamada and Gunn(1973) Equation ile yapılmıştır.
        double ro=0;
        double Vc,Zc,Tc,M,w;
        double Vs; // saturated liquid volume
        critical = values.get_critical(name);
        Vc = critical[4]; // ml/mol
        Zc = critical[6];
        Tc = critical[2]; // K
        M = critical[0];  // g/mol
        w = critical[7];
        if(Vc != 0 && Zc != 0 && Tc !=0 && w != 0){
            Vs = Vc*Math.pow(0.29056-0.08775*w,Math.pow(1-T/Tc,0.2857)); // ml/mol
            Vs = Vs/1000000; // m^3/mol
            Vs = Vs/M; // m^3/g
            Vs = Vs *1000; // (m^3/kg)
            ro = 1/Vs; // kg/(m^3)
        }

        return ro;
    }
    public String ro_HBT(double T){ // Hankinson and Thomson
        // Doymuş sıvı için hesaplama yapar. İstenilirse karışımlar için de kullanılabilir.
        double w = critical[7];
        double Tc = critical[2];
        double Vc = critical[4]; // ml/mol
        double Pc = critical[3]; // bar
        double M = critical[0];  // g/mol
        double ro=0;
        double Pvapor=0,ro_Saturated=0;
        double Pvpr=0;
        if(Vc != 0 && Pc != 0 && Tc !=0 && w != 0){
        Pc = Pc*100; // kPa
        double a = -1.52816,b=1.43907,c=-0.81446,d=0.190454,e=-0.296123,f=0.386914,g=-0.0427258,h=-0.0480645;
        double Tr = T/Tc;
        double V0=1-1.52816*Math.pow(1-Tr,0.3333)+1.43907*Math.pow(1-Tr,0.6666)-0.81446*Math.pow(1-Tr,1.0)+0.190454*Math.pow(1-Tr,1.3333);
        double Vsigma = (-0.296123+0.386914*Tr-0.0427258*Tr*Tr-0.0480645*Tr*Tr*Tr)/(Tr-1.00001);
        double V = Vc*V0*(1-w*Vsigma); // ml/mol
        V=V/M/1000; // m3/kg
         ro = 1/V;
        double Pvpr0=6.13144-6.30662/Tr-1.55663*Math.log(Tr)+0.17518*Math.pow(Tr,6.0); // Pvpr hesaplanırken kullanılacak.
        double Pvpr1=2.99938-3.08508/Tr+1.26573*Math.log(Tr)+0.08560*Math.pow(Tr,6.0); // Pvpr hesaplanırken kullanılacak.
         Pvpr = Math.pow(Math.E,Pvpr0+w*Pvpr1); // Pvapor reduced
             }
        try {
            Pvapor = Double.parseDouble(Pvapor()); // kPa
           // System.out.println("Katsayılar ile hesaplatılan Pvapor="+Pvapor);
            //System.out.println("Denklem ile hesaplatılan Pvapor="+Pvpr*Pc);
        }
        catch (NumberFormatException e1){
            e1.printStackTrace();
        }
        return ""+ro;
    }
    public double ro_HBT(String name,double T){ // Hankinson and Thomson
        // Doymuş sıvı için hesaplama yapar. İstenilirse karışımlar için de kullanılabilir.
        critical = values.get_critical(name);
        double w = critical[7];
        double Tc = critical[2];
        double Vc = critical[4]; // ml/mol
        double Pc = critical[3]; // bar
        double M = critical[0];  // g/mol
        double ro=0;
        double Pvapor=0,ro_Saturated=0;
        double Pvpr=0;
        if(Vc != 0 && Pc != 0 && Tc !=0 && w != 0){
            Pc = Pc*100; // kPa
            double a = -1.52816,b=1.43907,c=-0.81446,d=0.190454,e=-0.296123,f=0.386914,g=-0.0427258,h=-0.0480645;
            double Tr = T/Tc;
            double V0=1-1.52816*Math.pow(1-Tr,0.3333)+1.43907*Math.pow(1-Tr,0.6666)-0.81446*Math.pow(1-Tr,1.0)+0.190454*Math.pow(1-Tr,1.3333);
            double Vsigma = (-0.296123+0.386914*Tr-0.0427258*Tr*Tr-0.0480645*Tr*Tr*Tr)/(Tr-1.00001);
            double V = Vc*V0*(1-w*Vsigma); // ml/mol
            V=V/M/1000; // m3/kg
            ro = 1/V;
            double Pvpr0=6.13144-6.30662/Tr-1.55663*Math.log(Tr)+0.17518*Math.pow(Tr,6.0); // Pvpr hesaplanırken kullanılacak.
            double Pvpr1=2.99938-3.08508/Tr+1.26573*Math.log(Tr)+0.08560*Math.pow(Tr,6.0); // Pvpr hesaplanırken kullanılacak.
            Pvpr = Math.pow(Math.E,Pvpr0+w*Pvpr1); // Pvapor reduced
        }
       /* try {
            Pvapor = Double.parseDouble(Pvapor()); // kPa
            // System.out.println("Katsayılar ile hesaplatılan Pvapor="+Pvapor);
            //System.out.println("Denklem ile hesaplatılan Pvapor="+Pvpr*Pc);
        }
        catch (NumberFormatException e1){
            e1.printStackTrace();
        }*/
         return ro;
    }

    public String ro_Tait(double T,double P){
         /*Bu yöntem kullanılırken bazı değerler Nan çıkıyor. Çünkü B değeri eksi çıktığı için bazı basınç değerlerinde
         negatif logaritma veriyor. log( -değer) veriyor. Bu da Nan olmasına neden oluyor.
         Yöntemde hata yok yani.
         */
        double w = critical[7];
        double Tc = critical[2];
        double Pc = critical[3]; // bar
        Pc = Pc*100; // kPa
        double Pvapor=0,ro_Saturated=0;
        double a = -9.070217;
        double b = 62.45326;
        double d = -135.1102;
        double f = 4.79594;
        double g = 0.250047;
        double h = 1.14188;
        double j = 0.0861488;
        double k = 0.0344483;
        double Tr = T/Tc;
        double e = Math.pow(Math.E,f+g*w+h*w*w);
        double C = j+k*w;
        double B = Pc*(-1+a*Math.pow(1-Tr,0.3333)+b*Math.pow(1-Tr,0.6666)+d*(1-Tr)+e*Math.pow(1-Tr,1.3333));
        try {
             Pvapor = Double.parseDouble(Pvapor()); // kPa
        }
        catch (NumberFormatException e1){
            e1.printStackTrace();
            return "Pvapor hesaplanmadığı için hesaplama yapılamıyor.";
        }
        try{
             ro_Saturated = Double.parseDouble(ro());
        }
        catch (NumberFormatException e2){
            e2.printStackTrace();
            return "Doymuş sıvı yoğunluğu hesaplanamadığı için hesap yapılamıyor.";
        }

        double ro = ro_Saturated/(1-C*(Math.log((B+P)/(B+Pvapor))));
        if(Pvapor>P){
            ro = ro_Saturated;
        }

        return ""+ro;
    }
    public double ro_Tait(String name,double T,double P){

        // Bu yöntem kullanılırken bazı değerler Nan çıkıyor. Çünkü B değeri eksi çıktığı için bazı basınç değerlerinde
        // negatif logaritma veriyor. log( -değer) veriyor. Bu da Nan olmasına neden oluyor.
        // Yöntemde hata yok yani.

        Pvapor_c = values.getPvapor(name);
        critical = values.get_critical(name);
        ro_c = values.getro(name);
        double w = critical[7];
        double Tc = critical[2];
        double Pc = critical[3]; // bar
        Pc = Pc*100; // kPa
        double Pvapor=0,ro_Saturated=0;
        double a = -9.070217;
        double b = 62.45326;
        double d = -135.1102;
        double f = 4.79594;
        double g = 0.250047;
        double h = 1.14188;
        double j = 0.0861488;
        double k = 0.0344483;
        double Tr = T/Tc;
        double e = Math.pow(Math.E,f+g*w+h*w*w);
        double C = j+k*w;
        double B = Pc*(-1+a*Math.pow(1-Tr,0.3333)+b*Math.pow(1-Tr,0.6666)+d*(1-Tr)+e*Math.pow(1-Tr,1.3333));

        try {
            Pvapor = Double.parseDouble(Pvapor(T)); // kPa

        }
        catch (NumberFormatException e1){
            e1.printStackTrace();
            return 0.0;
        }
        try{
            ro_Saturated = Double.parseDouble(ro(T));

        }
        catch (NumberFormatException e2){
            e2.printStackTrace();
            return 0.0;
        }

        double ro = ro_Saturated/(1-C*(Math.log((B+P)/(B+Pvapor))));
        if(Pvapor>P){
            ro = ro_Saturated;
        }

        return ro;
    }

    public double ro_Chang_and_Zhao(String name,double T,double P){
        Pvapor_c = values.getPvapor(name);
        critical = values.get_critical(name);
        ro_c = values.getro(name);
        double w = critical[7];
        double Tc = critical[2];
        double Pc = critical[3]; // bar
        Pc = Pc*100; // kPa
        double Pvapor=0,ro_Saturated=0;
        double a0=-170.335,a1 = -28.578, a2=124.809,a3=-55.5393,a4=130.01,b0=0.164813,b1=-0.0914427,C=Math.E,D=1.00588;
        double Tr = T/Tc;
        double A = a0+a1*Tr+a2*Tr*Tr*Tr+a3*Tr*Tr*Tr*Tr*Tr*Tr*Tr+a4/Tr;
        double B = b0+w*b1;
        try {
            Pvapor = Double.parseDouble(Pvapor(T)); // kPa
        }
        catch (NumberFormatException e1){
            e1.printStackTrace();
            return 0.0;
        }
        try{
            ro_Saturated = Double.parseDouble(ro(T));
        }
        catch (NumberFormatException e2){
            e2.printStackTrace();
            return 0.0;
        }
        double ro = ro_Saturated/((A*Pc+Math.pow(C,Math.pow(D-Tr,B))*(P-Pvapor))/(A*Pc+C*(P-Pvapor)));
        if(Pvapor>P){
            ro = ro_Saturated;
        }
        return ro;
    }
    public String ro_Chang_and_Zhao(double P){
        double w = critical[7];
        double Tc = critical[2];
        double Pc = critical[3]; // bar
        Pc = Pc*100; // kPa
        double Pvapor=0,ro_Saturated=0;
        double a0=-170.335,a1 = -28.578, a2=124.809,a3=-55.5393,a4=130.01,b0=0.164813,b1=-0.0914427,C=Math.E,D=1.00588;
        double Tr = T/Tc;
        double A = a0+a1*Tr+a2*Tr*Tr*Tr+a3*Tr*Tr*Tr*Tr*Tr*Tr*Tr+a4/Tr;
        double B = b0+w*b1;
        try {
            Pvapor = Double.parseDouble(Pvapor()); // kPa
        }
        catch (NumberFormatException e1){
            e1.printStackTrace();
            return "Pvapor hesaplanmadığı için hesaplama yapılamıyor.";
        }
        try{
            ro_Saturated = Double.parseDouble(ro());
        }
        catch (NumberFormatException e2){
            e2.printStackTrace();
            return "Doymuş sıvı yoğunluğu hesaplanamadığı için hesap yapılamıyor.";
        }
        double ro = ro_Saturated/((A*Pc+Math.pow(C,Math.pow(D-Tr,B))*(P-Pvapor))/(A*Pc+C*(P-Pvapor)));
        if(Pvapor>P){
            ro = ro_Saturated;
        }
        return ""+ro;
    }
    public String ro_mix_Spencer_and_Danner(double T,String name[],double x[]){

        double w, Tc,Pc,Vc,M; // Pc:bar,Tc:Kelvin,Vc: ml/mol
        double Vcm=0.0; // Vc mixture. Hesaplatılması için 3 sayı lazım.
        double Tcm=0.0;
        double wsrkm=0; // wsrk mixture: Soawe Redlich Kwong accentric factor: direkt w değerini kullanmak da önemli hatalara neden olmaz.
        // O yüzden ben onu kullanacağım.
        double K1=0.0,K2=0.0,K3=0.0;
        double Ru = 8.314; // kJ/(kgK)
        double Z_RAm=0.0;
        double Z_RAi;
        double Vm; // Bubble point için hesaplanacak olan özgül hacim. Vsaturated yani
        double L1 = 0;
        double total_mole;
        double Molar_mass_mixture=0;
        for(int i=0;i<name.length;i++){
            critical = values.get_critical(name[i]);
            w = critical[7]; // accentric factor
            Tc = critical[2]; // Kelvin
            Pc = critical[3]; // bar
            Vc = critical[4]; // ml/mol
            M = critical[0]; // g/mol

            if(w == 0 || Tc == 0 || Pc == 0 || Vc == 0 || M ==0){
                return "w,Tc,Pc,Vc,M değerlerinden en az biri bilinmediği için hesaplama yapılamıyor";
            }

            Molar_mass_mixture += x[i]*M;
            K1 += x[i]*Vc;
            K2 += x[i]*Math.pow(Vc,0.6666);
            K3 += x[i]*Math.pow(Vc,0.3333);
            wsrkm += x[i]*Math.pow(w,0.5);
            Tcm += x[i]*Math.pow(Vc*Tc,0.5);
            Z_RAi = 0.29056-0.08775*w;
            Z_RAm += x[i]*Z_RAi;
            L1 += x[i]*Tc/Pc;


        }
        Vcm = 0.25*(K1+3*K2*K3); // Hankinson and Thomson (1979), özgül hacim
        wsrkm = wsrkm*wsrkm;
        Tcm= Tcm*Tcm/Vcm;        // Hankinson and Thomson (1979)
        double Pcm = (0.291-0.08*wsrkm)*Ru*Tcm/Vcm*1000; // kPa
        double Trm=T/Tcm;  // Treduced mixture
        // Spencer and Danner ( Kabarcıklanma noktasındaki özgül hacim hesaplanıyor. Yani doymuş karisim icin olan.
        Vm = Ru*L1*Math.pow(Z_RAm,1+Math.pow(1-Trm,0.2857))*10; // Burada diğer hesap yöntemlerine göre bir farklı çıkıyor. 10 ile çarpmak
        // değeri düzeltir gibi oluyor. R yerine 8.314 yerine 83.14 kullanmışlar. Ben mi birimlerde hata yaptım onlarda mı bir sorun var bilemedim.
        // Ama başka yöntemler ile hesaplanınca da onların değerine yakın çıkıyor. O yüzden 10 ile çarpıverdim.
        //  Vm = 116.43;
        return ""+1/(Vm/Molar_mass_mixture /1000);
    }

    public String ro_mix_Hankinson_and_Thomson(double T,double P,String name[],double x[]){
        //Bu metot  algoritmalara  ve tez metnine eklenmeyecek.

        double w, Tc,Pc,Vc,M; // Pc:bar,Tc:Kelvin,Vc: ml/mol
        double Vcm=0.0; // Vc mixture. Hesaplatılması için 3 sayı lazım.
        double Tcm=0.0;
        double wsrkm=0; // wsrk mixture: Soawe Redlich Kwong accentric factor: direkt w değerini kullanmak da önemli hatalara neden olmaz.
        // O yüzden ben onu kullanacağım.
        double K1=0.0,K2=0.0,K3=0.0;
        double Ru = 8.314; // kJ/(kgK)
        double Z_RAm=0.0;
        double Z_RAi;
        double Vm; // Bubble point için hesaplanacak olan özgül hacim. Vsaturated yani
        double L1 = 0;
        double total_mole;
        double Molar_mass_mixture=0;
        for(int i=0;i<name.length;i++){
            critical = values.get_critical(name[i]);
            w = critical[7]; // accentric factor
            Tc = critical[2];
            Pc = critical[3]; // bar
            Vc = critical[4]; // ml/mol
            M = critical[0]; // ml/mol

            if(w == 0 || Tc == 0 || Pc == 0 || Vc == 0 || M ==0){
                return "w,Tc,Pc,Vc,M değerlerinden en az biri bilinmediği için hesaplama yapılamıyor";
            }

            Molar_mass_mixture += x[i]*M;
            K1 += x[i]*Vc;
            K2 += x[i]*Math.pow(Vc,0.6666);
            K3 += x[i]*Math.pow(Vc,0.3333);
            wsrkm += x[i]*Math.pow(w,0.5);
            Tcm += x[i]*Math.pow(Vc*Tc,0.5);
            Z_RAi = 0.29056-0.08775*w;
            Z_RAm += x[i]*Z_RAi;
            L1 += x[i]*Tc/Pc;


        }
        Vcm = 0.25*(K1+3*K2*K3); // Hankinson and Thomson (1979), özgül hacim
        wsrkm = wsrkm*wsrkm;
        Tcm= Tcm*Tcm/Vcm;        // Hankinson and Thomson (1979)
        double Pcm = (0.291-0.08*wsrkm)*Ru*Tcm/Vcm*1000; // kPa
        double Trm=T/Tcm;  // Treduced mixture
        // Spencer and Danner
        Vm = Ru*L1*Math.pow(Z_RAm,1+Math.pow(1-Trm,0.2857))*10; // Burada diğer hesap yöntemlerine göre bir farklı çıkıyor. 10 ile çarpmak
        // değeri düzeltir gibi oluyor. R yerine 8.314 yerine 83.14 kullanmışlar. Ben mi birimlerde hata yaptım onlarda mı bir sorun var bilemedim.
        // Ama başka yöntemler ile hesaplanınca da onların değerine yakın çıkıyor. O yüzden 10 ile çarpıverdim.
        //  Vm = 116.43;
        double V0=1-1.52816*Math.pow(1-Trm,0.3333)+1.43907*Math.pow(1-Trm,0.6666)-0.81446*Math.pow(1-Trm,1.0)+0.190454*Math.pow(1-Trm,1.3333);
        double Vsigma = (-0.296123+0.386914*Trm-0.0427258*Trm*Trm-0.0480645*Trm*Trm*Trm)/(Trm-1.00001);
        double Vm2 = Vcm*V0*(1-wsrkm*Vsigma);// HBT eşitliğinin karışımlar için düzemlenmiş hali. Direkt olarak saf sıvılar için de kullanılabilen bir yöntem.

        double Pvpr0=6.13144-6.30662/Trm-1.55663*Math.log(Trm)+0.17518*Math.pow(Trm,6.0); // Pvpr hesaplanırken kullanılacak.
        double Pvpr1=2.99938-3.08508/Trm+1.26573*Math.log(Trm)+0.08560*Math.pow(Trm,6.0); // Pvpr hesaplanırken kullanılacak.
        double Pvpr = Math.pow(Math.E,Pvpr0+wsrkm*Pvpr1); // Pvapor reduced
        double a0=-170.335,a1 = -28.578, a2=124.809,a3=-55.5393,a4=130.01,b0=0.164813,b1=-0.0914427,C=Math.E,D=1.00588;
        double A = a0+a1*Trm+a2*Trm*Trm*Trm+a3*Trm*Trm*Trm*Trm*Trm*Trm+a4/Trm;
        double B = b0+wsrkm*b1;
        //double Vs = 116.43; // Vref gibi kullanılacak.
        double Pvp=Pvpr*Pcm; // Pref gibi kullanılacak. Pbubble basıncı da denilebilir.

        if ( P < Pvp ){
            P = Pvp;// V hesaplanırken P-Pvp terimi negatif çıkarsa sorun olabilir diye düşündüğüm için böyle yaptım. Sonra gerekirse düzelt.
            // Hem de doymuş halde olduğunu kabul etmiş oluyorum böylece.
        }

        double V = Vm2*(A*Pcm+Math.pow(C,Math.pow(D-Trm,B))*(P-Pvp))/(A*Pcm+C*(P-Pvp));
        double V_ikinciyol ;

        return ""+1/(V/Molar_mass_mixture /1000);
    }

    public String ro_mix_Aalto(double T,double P,String name[],double x[]){

        double w, Tc,Pc,Vc,M; // Pc:bar,Tc:Kelvin,Vc: ml/mol
        double Vcm=0.0; // Vc mixture. Hesaplatılması için 3 sayı lazım.
        double Tcm=0.0;
        double wsrkm=0; // wsrk mixture: Soawe Redlich Kwong accentric factor: direkt w değerini kullanmak da önemli hatalara neden olmaz.
        // O yüzden ben onu kullanacağım.
        double K1=0.0,K2=0.0,K3=0.0;
        double Ru = 8.314;
        double Z_RAm=0.0;
        double Z_RAi;
        double Vm; // Bubble point için hesaplanacak olan özgül hacim. Vsaturated yani
        double L1 = 0;
        double total_mole;
        double Molar_mass_mixture=0;
        for(int i=0;i<name.length;i++){
            critical = values.get_critical(name[i]);
            w = critical[7]; // accentric factor
            Tc = critical[2];
            Pc = critical[3]; // bar
            Vc = critical[4]; // ml/mol
            M = critical[0]; // ml/mol

            if(w == 0 || Tc == 0 || Pc == 0 || Vc == 0 || M ==0){
                return "w,Tc,Pc,Vc,M değerlerinden en az biri bilinmediği için hesaplama yapılamıyor";
            }
            Molar_mass_mixture += x[i]*M;
            K1 += x[i]*Vc;
            K2 += x[i]*Math.pow(Vc,0.6666);
            K3 += x[i]*Math.pow(Vc,0.3333);
            wsrkm += x[i]*Math.pow(w,0.5);
            Tcm += x[i]*Math.pow(Vc*Tc,0.5);
            Z_RAi = 0.29056-0.08775*w;
            Z_RAm += x[i]*Z_RAi;
            L1 += x[i]*Tc/Pc;
        }
        Vcm = 0.25*(K1+3*K2*K3); // Hankinson and Thomson (1979), özgül hacim
        wsrkm = wsrkm*wsrkm;
        Tcm= Tcm*Tcm/Vcm;        // Hankinson and Thomson (1979)
        double Pcm = (0.291-0.08*wsrkm)*Ru*Tcm/Vcm*1000; // kPa
        double Trm=T/Tcm;  // Treduced mixture
        // Spencer and Danner
        Vm = Ru*L1*Math.pow(Z_RAm,1+Math.pow(1-Trm,0.2857))*10; // Burada diğer hesap yöntemlerine göre bir farklı çıkıyor. 10 ile çarpmak
        // değeri düzeltir gibi oluyor. R yerine 8.314 yerine 83.14 kullanmışlar. Ben mi birimlerde hata yaptım onlarda mı bir sorun var bilemedim.
        // Ama başka yöntemler ile hesaplanınca da onların değerine yakın çıkıyor. O yüzden 10 ile çarpıverdim.
        //  Vm = 116.43;
        double Pvpr0=6.13144-6.30662/Trm-1.55663*Math.log(Trm)+0.17518*Math.pow(Trm,6.0); // Pvpr hesaplanırken kullanılacak.
        double Pvpr1=2.99938-3.08508/Trm+1.26573*Math.log(Trm)+0.08560*Math.pow(Trm,6.0); // Pvpr hesaplanırken kullanılacak.
        double Pvpr = Math.pow(Math.E,Pvpr0+wsrkm*Pvpr1); // Pvapor reduced
        double a0=-170.335,a1 = -28.578, a2=124.809,a3=-55.5393,a4=130.01,b0=0.164813,b1=-0.0914427,C=Math.E,D=1.00588;
        double A = a0+a1*Trm+a2*Trm*Trm*Trm+a3*Trm*Trm*Trm*Trm*Trm*Trm+a4/Trm;
        double B = b0+wsrkm*b1;
        double Pvp=Pvpr*Pcm; // Pref gibi kullanılacak. Pbubble basıncı da denilebilir.
        double V = Vm*(A*Pcm+Math.pow(C,Math.pow(D-Trm,B))*(P-Pvp))/(A*Pcm+C*(P-Pvp));
        if ( P < Pvp ){
            V = Vm;
        }
        return ""+1/(V/Molar_mass_mixture /1000);
    }
    public String Pvapor_mix_Aalto(double T,String name[],double x[]){

        // Yontemde sadece Pvp degerini dondurmek istiyorum. Gereksiz hesaplamalar olabilir. Ama yanlis bir sey slmemek icin dokunmuyorum.

        double w, Tc,Pc,Vc,M; // Pc:bar,Tc:Kelvin,Vc: ml/mol
        double Vcm=0.0; // Vc mixture. Hesaplatılması için 3 sayı lazım.
        double Tcm=0.0;
        double wsrkm=0; // wsrk mixture: Soawe Redlich Kwong accentric factor: direkt w değerini kullanmak da önemli hatalara neden olmaz.
        // O yüzden ben onu kullanacağım.
        double K1=0.0,K2=0.0,K3=0.0;
        double Ru = 8.314; // kJ/(kgK)
        double Z_RAm=0.0;
        double Z_RAi;
        double Vm; // Bubble point için hesaplanacak olan özgül hacim. Vsaturated yani
        double L1 = 0;
        double total_mole;
        double Molar_mass_mixture=0;
        for(int i=0;i<name.length;i++){
            critical = values.get_critical(name[i]);
            w = critical[7]; // accentric factor
            Tc = critical[2];
            Pc = critical[3]; // bar
            Vc = critical[4]; // ml/mol
            M = critical[0]; // ml/mol

            if(w == 0 || Tc == 0 || Pc == 0 || Vc == 0 || M ==0){
                return "w,Tc,Pc,Vc,M değerlerinden en az biri bilinmediği için hesaplama yapılamıyor";
            }
            Molar_mass_mixture += x[i]*M;
            K1 += x[i]*Vc;
            K2 += x[i]*Math.pow(Vc,0.6666);
            K3 += x[i]*Math.pow(Vc,0.3333);
            wsrkm += x[i]*Math.pow(w,0.5);
            Tcm += x[i]*Math.pow(Vc*Tc,0.5);
            Z_RAi = 0.29056-0.08775*w;
            Z_RAm += x[i]*Z_RAi;
            L1 += x[i]*Tc/Pc;
        }
        Vcm = 0.25*(K1+3*K2*K3); // Hankinson and Thomson (1979), özgül hacim
        wsrkm = wsrkm*wsrkm;
        Tcm= Tcm*Tcm/Vcm;        // Hankinson and Thomson (1979)
        double Pcm = (0.291-0.08*wsrkm)*Ru*Tcm/Vcm*1000; // kPa
        double Trm=T/Tcm;  // Treduced mixture
        double Pvpr0=6.13144-6.30662/Trm-1.55663*Math.log(Trm)+0.17518*Math.pow(Trm,6.0); // Pvpr hesaplanırken kullanılacak.
        double Pvpr1=2.99938-3.08508/Trm+1.26573*Math.log(Trm)+0.08560*Math.pow(Trm,6.0); // Pvpr hesaplanırken kullanılacak.
        double Pvpr = Math.pow(Math.E,Pvpr0+wsrkm*Pvpr1); // Pvapor reduced
        double a0=-170.335,a1 = -28.578, a2=124.809,a3=-55.5393,a4=130.01,b0=0.164813,b1=-0.0914427,C=Math.E,D=1.00588;

        double Pvp=Pvpr*Pcm; // Pref gibi kullanılacak. Pbubble basıncı da denilebilir.


        return ""+Pvp;
    }

    public String ro_mix_molar(String ro[],double x[],double M[]) {
        double ro_mix=0;
        double pay=0;
        double payda=0;
        for(int i=0;i<ro.length;i++){
            //System.out.println(" ro:"+ ro[i]);

            pay += M[i]*x[i];
            try{
                payda += M[i]*x[i]/Double.parseDouble(ro[i]);
            }
            catch (NumberFormatException e){
                e.printStackTrace();
                return "try catch kısmında sıkıntı oluştu.";
            }
        }
        ro_mix=pay/payda;
        return ""+ro_mix;
    }
    public String ro_mix_weight(double ro1,double ro2,double w1, double w2) {
        double ro_mix=(w1+w2)/(w1/ro1+w2/ro2);
        return ""+ro_mix;
    }
    public String ro_mix_weight2(double ro[],double w[]) {
         double ro_mix=0;
         double pay=0;
         double payda=0;
        for(int i=0;i<ro.length;i++){
            pay += w[i];
            payda += w[i]/ro[i];
        }
        ro_mix=pay/payda;

        return ""+ro_mix;
    }
    public double ro2(String name, double T) { // Viskozite hesabını yaparken V(molar hacim) hesabı için bir formül buldum. Onu kullanacağım.

        double Tr, w, H1, H2, Tc, Vc, V;
        double ro=0.0;//  ro (kg/m^3)
        double fTreferans;// f(Treferans);
        double Vreferans, Treferans;
        double fT; // f(T) demek istedim.
        critical=values.get_critical(name);
        ro_c=values.getro(name);
        Pc = critical[3];
        w = critical[7];
        Tc = critical[2]; // Kelvin
        Vc = critical[4]; // (ml/mol), (cm^3/mol) ikisi aynı şey
        Treferans = ro_c[4];
        double M = critical[0];

        try {
            Vreferans = M/Double.parseDouble(ro(Treferans))*1000;
        }
        catch (NumberFormatException e){
            e.printStackTrace();
            return 0.0;
        }

        if(Pc !=0 && Tc !=0 && Vc !=0 && w != 0 )
        {
            Tr = Treferans / Tc;
            H1 = 0.33593 - 0.33953 * Tr + 1.51941 * Tr * Tr - 2.02512 * Tr * Tr * Tr + 1.11422 * Tr * Tr * Tr * Tr;
            H2 = 0.29607 - 0.09045 * Tr - 0.04842 * Tr * Tr;
            fTreferans = H1 * (1 - w * H2);
            Tr = T / Tc;
            H1 = 0.33593 - 0.33953 * Tr + 1.51941 * Tr * Tr - 2.02512 * Tr * Tr * Tr + 1.11422 * Tr * Tr * Tr * Tr;
            H2 = 0.29607 - 0.09045 * Tr - 0.04842 * Tr * Tr;
            fT = H1 * (1 - w * H2);
            V=fT*Vreferans/fTreferans;
            ro=M*1000/V;
        }
        return ro;
    }
    public String ro2() { // Viskozite hesabını yaparken V(molar hacim) hesabı için bir formül buldum. Onu kullanacağım.
        double Tr, w, H1, H2, Tc, Vc, V,ro; //  ro (kg/m^3)
        double fTreferans;// f(Treferans);
        double Vreferans, Treferans;
        double fT; // f(T) demek istedim.
        Pc = critical[3];
        w = critical[7];
        Tc = critical[2]; // Kelvin
        Vc = critical[4]; // (ml/mol), (cm^3/mol) ikisi aynı şey
        Treferans = ro_c[4];
        double M = critical[0];
        try {
            Vreferans = M/Double.parseDouble(ro(Treferans))*1000;
        }
        catch (NumberFormatException e){
            e.printStackTrace();
            return "Bu sıcaklıkta hesaplama yapılamıyor";
        }
        Tr = Treferans / Tc;
        H1 = 0.33593 - 0.33953 * Tr + 1.51941 * Tr * Tr - 2.02512 * Tr * Tr * Tr + 1.11422 * Tr * Tr * Tr * Tr;
        H2 = 0.29607 - 0.09045 * Tr - 0.04842 * Tr * Tr;
        fTreferans = H1 * (1 - w * H2);
//        System.out.println("Yazma İşlemi Başlıyor.");
//        System.out.println("Tf:"+Tf);
//        System.out.println("H1:"+H1);
//        System.out.println("H2:"+H2);
//        System.out.println("fT:"+fT);
//        System.out.println("Treferans(Tfp):"+Treferans);
//        System.out.println("w"+w);

        Tr = T / Tc;
        H1 = 0.33593 - 0.33953 * Tr + 1.51941 * Tr * Tr - 2.02512 * Tr * Tr * Tr + 1.11422 * Tr * Tr * Tr * Tr;
        H2 = 0.29607 - 0.09045 * Tr - 0.04842 * Tr * Tr;
        fT = H1 * (1 - w * H2);

      V=fT*Vreferans/fTreferans;
      ro=M*1000/V;

        if( w == 0.0 ){
            return "Accentric factor bilinmiyor.";
        }
        else{
            return ""+ro;
        }
    }
    public String v() {
        double A,B,C,n;

        double ro=0;
        double v=0;

        if(ro_c[4]<=T && T<=ro_c[5])
        {
            A=ro_c[0];
            B=ro_c[1];
            C=ro_c[2];
            n=ro_c[3];

            ro=A*Math.pow(B, -Math.pow((1-T/C), n)); // g/ml birimindedir.
            ro*=1000; // Birimi kg/m^3 yaptım.
            v=1/ro;
            return (""+v);
        }
        else {
            return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
        }
    }

    public double  v(double T) {
        double A,B,C,n;
        double ro=0;
        double v=0;
        A=ro_c[0];
        B=ro_c[1];
        n=ro_c[3];
        C=ro_c[2];
        ro=A*Math.pow(B, -Math.pow((1-T/C), n)); // g/ml birimindedir.
        //System.out.println(ro);
        ro*=1000; // Birimi kg/m^3 yaptım.
        v=1/ro;
        //System.out.println(v);
        return (v);
    }


    public String Pr(String cp, String k, String vis){
        // Hesaplanırken cp birimi kmol ile değil kg ile olmalıydı. Onu düzelt!!!

        try {
            double cp_value=Double.parseDouble(cp);
            double k_value=Double.parseDouble(k);
            double vis_value=Double.parseDouble(vis);
            return String.valueOf(1000*cp_value*vis_value/k_value);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "Bu sıcaklıkta hesaplama yapılamıyor";
        }
    }
    public String termal_diffuzivite(String cp, String k, String ro){ //( iletilen ısı / depolanan ısı) denklemi ile bulunur.
        // Hesaplanırken cp birimi kmol ile değil kg ile olmalıydı. Onu düzelt!!!
        try {
            double cp_value=Double.parseDouble(cp);
            double k_value=Double.parseDouble(k);
            double ro_value=Double.parseDouble(ro);
            return String.valueOf(k_value/(cp_value*ro_value));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "Bu sıcaklıkta hesaplama yapılamıyor";
        }
    }
    public double sur_tension(String name,double T ){ //surface tension: Orijinal halinde birimi dynes/cm ama ben N/m' ye çevireceğim.
        double sigma;
        surtension_c=values.getsurtension(name);
        double A = surtension_c[0];
        double B = surtension_c[1];
        double n = surtension_c[2];

        double Tmin = surtension_c[3];
        double Tmax = surtension_c[4];
            sigma = A* Math.pow(1-T/B,n);
            sigma = sigma/1000; // Birimini N/m' ye çevirmek için yaptım.
        return  sigma;
    }
    public String sur_tension(double T){ //surface tension: Orijinal halinde birimi dynes/cm ama ben N/m' ye çevireceğim.
        double sigma;
        double A = surtension_c[0];
        double B = surtension_c[1];
        double n = surtension_c[2];
        double Tmin = surtension_c[3];
        double Tmax = surtension_c[4];
        if(Tmin<=T && T<=Tmax)
        {
          sigma = A*Math.pow(1-T/B,n);
          sigma = sigma/1000; // Birimini N/m' ye çevirmek için yaptım.
            return (""+sigma);
        }
        else {
            return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
        }
    }
    public String surten_MacleodandSugden(String name,double T){
        critical = values.get_critical(name);
        ro_c = values.getro(name);
        double ro;
        double Tc = critical[2];
        double Pc = critical[3];
        double w = critical[7];
        double M = critical[0];
        double  Parachor = 40.1684*(0.151-0.0464*w)*Math.pow(Tc,1.08333)/Math.pow(Pc,0.833333);
        //System.out.println("Parachor="+Parachor);
        try{
            ro = Double.parseDouble(ro(T)); // Birimi şu an kg/m^3. Bunu mol/cm^3 yapmam lazım.
            ro = ro/1000/M; // Birimini mol/cm^3 yaptım.
        }
        catch (NumberFormatException e){
            e.printStackTrace();
            return "Yoğunluk hesaplanamadığı için hesap yapılamıyor";
        }
        double sigma = Math.pow(Parachor*ro,4.0); // dyn/cm
        return ""+sigma/1000; // Birimini N/m yaptım.
    }
    public double surten_MacleodandSugden(String name,double T,String type){
        // String olan metot ile çakışmasın diye String type ekledim. Hiçbir işlevi yok. Herhangi bir şey yazsam da olur.
        critical = values.get_critical(name);
        ro_c = values.getro(name);
        double sigma = 0;
        double ro;
        double Tc = critical[2];
        double Pc = critical[3];
        double w = critical[7];
        double M = critical[0];
        double  Parachor = 40.1684*(0.151-0.0464*w)*Math.pow(Tc,1.08333)/Math.pow(Pc,0.833333);
        //System.out.println("Parachor="+Parachor);
        try{

            ro = Double.parseDouble(ro(T)); // Birimi şu an kg/m^3. Bunu mol/cm^3 yapmam lazım.
            ro = ro/1000/M; // Birimini mol/cm^3 yaptım.
            sigma = Math.pow(Parachor*ro,4.0); // dyn/cm
        }
        catch (NumberFormatException e){
            sigma = 0.0;
        }
        return sigma/1000; // Birimini N/m yaptım.
    }
    public String surten_BrockandBird(){ //surface tension: Orijinal halinde birimi dynes/cm ama ben N/m' ye çevireceğim.
        // BROCK and BIRD
        double sigma;
        double Tb = critical[1];
        double Tc = critical[2];
        double Pc = critical[3]; // bar
        // P değerleri bar biriminden olmalı
        if((Tb !=0) && (Tc != 0) && (Pc != 0) ){
            double Tbr=Tb/Tc;
            double Tr=T/Tc;
            double Q=0.1196*(1+Tbr/(1-Tbr)*Math.log(Pc/1.01325))-0.279;
            sigma=Math.pow(Pc,0.66666)*Math.pow(Tc,0.33333)*Q*Math.pow(1-Tr,1.22222);
            //System.out.println("Tb="+Tb+" Tc="+Tc+" Pc="+Pc+" Tbr="+Tbr+" Tr="+Tr+" Q="+Q);
            return ""+sigma/1000;
        }
        else {
            return " Kritik değerlerden en az biri bilinmediği için hesap yapılamıyor.";
        }
    }
    public double surten_BrockandBird(String name,double T){ //surface tension: Orijinal halinde birimi dynes/cm ama ben N/m' ye çevireceğim.
        // BROCK and BIRD
        double sigma=0;
        critical = values.get_critical(name);
        double Tb = critical[1];
        double Tc = critical[2];
        double Pc = critical[3];
        if( Tb != 0 && Tc != 0 && Pc != 0) {
            double Tbr=Tb/Tc;
            double Tr=T/Tc;
            double Q=0.1196*(1+Tbr/(1-Tbr)*Math.log(Pc/1.01325))-0.279;
            sigma=Math.pow(Pc,0.66666)*Math.pow(Tc,0.33333)*Q*Math.pow(1-Tr,1.22222);
        }
                return sigma/1000;
    }
    public String surten_Pitzer(){ //surface tension: Orijinal halinde birimi dynes/cm ama ben N/m' ye çevireceğim.
        // PITZER
        double sigma=0;
        double w = critical[7];
        double Tc = critical[2];
        double Pc = critical[3];
        double Tr=T/Tc;
        if(w != 0 && Tc !=0 && Pc !=0){
            sigma = Math.pow(Pc,0.66666)*Math.pow(Tc,0.33333)*(1.86+1.18*w)/19.05*Math.pow((3.75+0.91*w)/(0.291-0.08*w),0.66666)*Math.pow(1-Tr,1.2222);
            return  ""+sigma/1000; // Birimini çevirdim.
        }
        else{
            return "Kritik değerlerden en az biri bilinmediği için hesaplama yapılamıyor.";
        }
    }

    public double surten_Pitzer(String name,double T){ //surface tension: Orijinal halinde birimi dynes/cm ama ben N/m' ye çevireceğim.
        // PITZER
        double sigma=0;
        critical = values.get_critical(name);
        double w = critical[7];
        double Tc = critical[2];
        double Pc = critical[3];
        double Tr=T/Tc;
        if(w != 0 && Tc != 0 && Pc != 0){
            sigma = Math.pow(Pc,0.66666)*Math.pow(Tc,0.33333)*(1.86+1.18*w)/19.05*Math.pow((3.75+0.91*w)/(0.291-0.08*w),0.66666)*Math.pow(1-Tr,1.2222);
        }
        return sigma/1000;
    }

    public String surten_ZuoandStendby(){ //surface tension: Orijinal halinde birimi dynes/cm ama ben N/m' ye çevireceğim.
        // ZUO-STENBY
        double sigma;
        double w = critical[7];
        double Tc = critical[2];
        double Pc = critical[3];
        double Tr=T/Tc;
        double Tc1=190.56;
        double Tc2=568.7;
        double Pc1=45.99;
        double Pc2=24.9;
        double w1=0.011;
        double w2=0.399;
        double sigma1=40.520*Math.pow(1-Tr,1.287);//methane
        double sigma2=52.095*Math.pow(1-Tr,1.21548);//n-octane
        double sigmar1=Math.log(1+sigma1/(Math.pow(Pc1,0.66666)*Math.pow(Tc1,0.33333)));
        double sigmar2=Math.log(1+sigma2/(Math.pow(Pc2,0.66666)*Math.pow(Tc2,0.33333)));
        double sigmar=sigmar1+(w-w1)/(w2-w1)*(sigmar2-sigmar1);

        if(w != 0 && Tc !=0 && Pc !=0){
            sigma=(Math.pow(Math.E,sigmar)-1)*(Math.pow(Pc,0.66666)*Math.pow(Tc,0.33333));
            return ""+sigma/1000; // Birimini çevirdim.
        }
        else{
            return "Kritik değerlerden en az biri bilinmediği için hesaplama yapılamıyor.";
        }
    }
    public double surten_ZuoandStendby(String name, double T){ //surface tension: Orijinal halinde birimi dynes/cm ama ben N/m' ye çevireceğim.
        // ZUO-STENBY
        double sigma=0;
        critical = values.get_critical(name);
        double w = critical[7];
        double Tc = critical[2];
        double Pc = critical[3];
        double Tr=T/Tc;
        double Tc1=190.56;
        double Tc2=568.7;
        double Pc1=45.99;
        double Pc2=24.9;
        double w1=0.011;
        double w2=0.399;
        if( Tc != 0 && Pc != 0) {
            double sigma1=40.520*Math.pow(1-Tr,1.287);
            double sigma2=52.095*Math.pow(1-Tr,1.21548);
            double sigmar1=Math.log(1+sigma1/(Math.pow(Pc1,0.66666)*Math.pow(Tc1,0.33333)));
            double sigmar2=Math.log(1+sigma2/(Math.pow(Pc2,0.66666)*Math.pow(Tc2,0.33333)));
            double sigmar=sigmar1+(w-w1)/(w2-w1)*(sigmar2-sigmar1);
            sigma=(Math.pow(Math.E,sigmar)-1)*(Math.pow(Pc,0.66666)*Math.pow(Tc,0.33333));
        }
        /*System.out.println("sigma1:"+sigma1);
        System.out.println("sigma2:"+sigma2);
        System.out.println("sigmar1:"+sigmar1);
        System.out.println("sigmar2:"+sigmar2);
        System.out.println("sigmar:"+sigmar);*/
        return sigma/1000;

    }
    public double surten_SastriandRao(String name, double T){ //surface tension: Orijinal halinde birimi dynes/cm ama ben N/m' ye çevireceğim.
        // SASTRI-RAO
        double sigma=0;
        critical = values.get_critical(name);
        double Tb=critical[1];
        double Tc=critical[2];
        double Pc=critical[3];
        if (Tb != 0 && Tc != 0 && Pc != 0){
            double Tr=T/Tc;
            double Tbr=Tb/Tc;
            double K=0.158;
            double x=0.50;
            double y=-1.5;
            double z=1.85;
            double m=1.22222;
            if (malzemenin_turu.equals("alcohol")){
                K=2.28;
                x=0.25;
                y=0.175;
                z=0;
                m=0.8;
            }

            else if(malzemenin_turu.equals("acid")){
                K=0.125;
                x=0.50;
                y=0.-1.5;
                z=1.85;
                m=1.22222;
            }
            sigma= K*Math.pow(Pc,x)*Math.pow(Tb,y)*Math.pow(Tc,z)*Math.pow((1-Tr)/(1-Tbr),m);
        }


        return sigma/1000;

    }
    public String surten_SastriandRao(){ //surface tension: Orijinal halinde birimi dynes/cm ama ben N/m' ye çevireceğim.
        // SASTRI-RAO
        double sigma;
        double Tb=critical[1];
        double Tc=critical[2];
        double Pc=critical[3];

        if (Tb != 0 && Tc != 0 && Pc != 0){
            double Tr=T/Tc;
            double Tbr=Tb/Tc;
            double K=0.158;
            double x=0.50;
            double y=-1.5;
            double z=1.85;
            double m=1.22222;
            if (malzemenin_turu.equals("alcohol")){
                K=2.28;
                x=0.25;
                y=0.175;
                z=0;
                m=0.8;
            }
            else if(malzemenin_turu.equals("acid")){
                K=0.125;
                x=0.50;
                y=0.-1.5;
                z=1.85;
                m=1.22222;
            }
            sigma= K*Math.pow(Pc,x)*Math.pow(Tb,y)*Math.pow(Tc,z)*Math.pow((1-Tr)/(1-Tbr),m);
            return ""+sigma/1000; // Birimini çevirdim.
        }
        else {
            return  "Kritik değerlerden en az biri bilinmediği için hesaplama yapılamıyor.";
        }
    }



    public String surten_mix_ZuoandStendby_Kays(String name[],double x[],double T){
        // pseudocritical özellikler Kay's Rule ile hesaplanmıştır.
        double w, Tc,Pc,Vc,M; // Pc:bar,Tc:Kelvin,Vc: ml/mol
        double Pcm=0.0;
        double Tcm=0.0;
        double wm=0; // wsrk mixture: Soawe Redlich Kwong accentric factor: direkt w değerini kullanmak da önemli hatalara neden olmaz.
        // O yüzden ben onu kullanacağım.
        double Molar_mass_mixture=0;
        for(int i=0;i<name.length;i++){
            critical = values.get_critical(name[i]);
            w = critical[7]; // accentric factor
            Tc = critical[2];
            Pc = critical[3]; // bar
            Vc = critical[4]; // ml/mol
            M = critical[0]; // g/mol
            if(w == 0 || Tc == 0 || Pc == 0 || Vc == 0 || M ==0){
                return "w,Tc,Pc,Vc,M değerlerinden en az biri bilinmediği için hesaplama yapılamıyor";
            }
            Molar_mass_mixture += x[i]*M;
            wm += x[i]*w;
            Tcm += x[i]*Tc; // Kelvin
            Pcm += x[i]*Pc;//bar
        }
        double Trm = T/ Tcm;
        double Tc1=190.56;
        double Tc2=568.7;
        double Pc1=45.99;
        double Pc2=24.9;
        double w1=0.011;
        double w2=0.399;
        double sigma1=40.520*Math.pow(1-Trm,1.287);
        double sigma2=52.095*Math.pow(1-Trm,1.21548);
        double sigmar1=Math.log(1+sigma1/(Math.pow(Pc1,0.66666)*Math.pow(Tc1,0.33333)));
        double sigmar2=Math.log(1+sigma2/(Math.pow(Pc2,0.66666)*Math.pow(Tc2,0.33333)));
        double sigmar=sigmar1+(wm-w1)/(w2-w1)*(sigmar2-sigmar1);
        double sigma_mix=(Math.pow(Math.E,sigmar)-1)*(Math.pow(Pcm,0.66666)*Math.pow(Tcm,0.33333));
        sigma_mix = sigma_mix/1000; // Birimi dyn/cm'den N/m'ye çevirdim.

        System.out.println("x1:"+x[0]);
        System.out.println("x2:"+x[1]);
        System.out.println("Kays Rule :");
        System.out.println("Tcm="+Tcm);
        System.out.println("Pcm="+Pcm);
        return ""+sigma_mix;
    }

    public String surten_mix_ZuoandStendby_RiceTeja(String name[],double x[],double T){
        double xi,wi, Tci,Pci,Vci,Mi; // Pc:bar,Tc:Kelvin,Vc: ml/mol
        double xj,wj, Tcj,Pcj,Vcj,Mj; // Pc:bar,Tc:Kelvin,Vc: ml/mol
        double Vcm=0.0; // Vc mixture. Hesaplatılması için 3 sayı lazım.
        double Tcm=0.0;
        double wm=0; // wsrk mixture: Soawe Redlich Kwong accentric factor: direkt w değerini kullanmak da önemli hatalara neden olmaz.
        // O yüzden ben onu kullanacağım.
        double Ru = 83.14; // J/(molK) Aslında 8.314 olması lazım ama böyle olunca doğru hesap yapıyor.
        // Hatanın ne olduğunu öğrendim. 83.14 olduğu zaman basıncın birimini bar biriminden alıyor. O yüzden bu şekilde.
        double Mm=0;//karışım molar kütlesi
        double Vcij;
        double TcijVcij;//Tcij * Vcij terimi
        double interaction_parameter=1.0;
        for(int i=0;i<name.length;i++){
            critical = values.get_critical(name[i]);
            wi = critical[7]; // accentric factor
            Tci = critical[2];
            Pci = critical[3]; // bar
            Vci = critical[4]; // ml/mol
            Mi = critical[0]; // g/mol
            xi = x[i];
            Mm += xi*Mi;
            wm += xi*wi;
            for(int j=0;j<name.length;j++) {
                critical = values.get_critical(name[j]);
                wj = critical[7]; // accentric factor
                Tcj = critical[2];
                Pcj = critical[3]; // bar
                Vcj = critical[4]; // ml/mol
                Mj = critical[0]; // g/mol
                xj = x[j];
                Vcij = Math.pow(Math.pow(Vci,0.3333)+Math.pow(Vcj,0.3333),3.0)/8.0;
                Vcm += xi*xj*Vcij;

                if( wj==0 || Tcj==0 || Pcj==0 || Vcj==0 || Mj ==0 ){
                    return name[j]+" malzemesinin kritik özelliklerinden en az biri bilinmediği için hesaplama yapılamıyor";
                }
                if(name[i].equals("H2O_water") || name[j].equals("H2O_water") ){
                    interaction_parameter =1.37;
                }
                TcijVcij = interaction_parameter*Math.pow(Tci*Tcj*Vci*Vcij,0.5);
                Tcm += xi*xj*TcijVcij;
            }
        }
        Tcm = Tcm/Vcm;
        double Pcm = (0.291-0.08*wm)*Ru*Tcm/Vcm; // bar
        System.out.println("Kays Rule değil:");
        System.out.println("Tcm="+Tcm);
        System.out.println("Pcm="+Pcm);
        double Trm = T/ Tcm;
        double Tc1=190.56;
        double Tc2=568.7;
        double Pc1=45.99;
        double Pc2=24.9;
        double w1=0.011;
        double w2=0.399;
        double sigma1=40.520*Math.pow(1-Trm,1.287);
        double sigma2=52.095*Math.pow(1-Trm,1.21548);
        double sigmar1=Math.log(1+sigma1/(Math.pow(Pc1,0.66666)*Math.pow(Tc1,0.33333)));
        double sigmar2=Math.log(1+sigma2/(Math.pow(Pc2,0.66666)*Math.pow(Tc2,0.33333)));
        double sigmar=sigmar1+(wm-w1)/(w2-w1)*(sigmar2-sigmar1);
        double sigma_mix=(Math.pow(Math.E,sigmar)-1)*(Math.pow(Pcm,0.66666)*Math.pow(Tcm,0.33333));
        sigma_mix = sigma_mix/1000; // Birimi dyn/cm'den N/m'ye çevirdim.
        return ""+sigma_mix;
    }

    public String surten_mix_WeinaugKatz_Macleod(String name[],double x[],double T){
        // Macleod and Sugden 1923 eşitliğini karışımlara uyarlayarak hesaplanır.
        // Buhar hali için olan hesaplar ihmal edilecek. Zaten basıncın düşük olduğu durumlarda
        // etkisi azdır.
        // sigma_mix = [[P_lm]ro_lm-[P_vm]ro_vm]^n
        // Bunda parachor değerleri  Parachori = Math.pow( surteni/(roi*roi*roi*roi),0.25)
        // eşitliği ile bulunmuştur. İkinci yöntemde ise bu değer Hugill ve Welsenes eşitliği ile bulunmuştur.
        double surteni,surtenj;
        double roi,roj;
        double xi,xj; // molar fraction
        double Parachori,Parachorj;
        double Parachorij,Parachorlm=0;// lm : liquid mixture
        double rolm=0; // liquid mixture ddensity
        double Mmix= 0; // molar mass of mixture
        for(int i=0;i<name.length;i++){
            critical = values.get_critical(name[i]);
            Mmix += x[i]*critical[0];
            for(int j=0;j<name.length;j++){
                surtension_c = values.getsurtension(name[i]);
                ro_c = values.getro(name[i]);
                critical = values.get_critical(name[i]);
                try{
                    surteni = Double.parseDouble(sur_tension(T))*1000;  // birimlerini tekrar dyn/cm yapıyorum.
                    roi = Double.parseDouble(ro(T))/1000.0/critical[0]; // birimini kg/m^3 yerine mol/cm^3 yapmış bulunmaktayım.
                    surtension_c = values.getsurtension(name[j]);
                    ro_c = values.getro(name[j]);
                    critical = values.get_critical(name[j]);
                    surtenj = Double.parseDouble(sur_tension(T))*1000;  // birimlerini tekrar dyn/cm yapıyorum.
                    roj = Double.parseDouble(ro(T))/1000.0/critical[0]; // birimini kg/m^3 yerine mol/cm^3 yapmış bulunmaktayım.
                }
                catch (NumberFormatException e){
                    e.printStackTrace();
                    return " Saf sıvılar için olan yoğunluk veya yüzey gerilimi değerlerinden en az biri hesaplanamıyor";
                }
                try{
                    rolm = Double.parseDouble(ro_mix_Spencer_and_Danner(T,name,x))/1000.0; // birimini kg/m^3 yerine g/cm^3 yapmış bulunmaktayım.
                }
                catch (NumberFormatException e1) {
                    e1.printStackTrace();
                    return " Karışımın yoğunluk değeri hesaplanamadığı için hesap yapılamıyor";
                }
                Parachori = Math.pow( surteni/(roi*roi*roi*roi),0.25) ;
                Parachorj = Math.pow(surtenj/(roj*roj*roj*roj),0.25);
                System.out.println("Macleod-Sugden Parachori:"+Parachori);
                System.out.println("Macleod-Sugden Parachorj:"+Parachorj);
                xi = x[i];
                xj = x[j];
                Parachorij = (Parachori+Parachorj)/2.0;
                Parachorlm += xi*xj*Parachorij;

            }
        }
        double sigma_mix = Math.pow(Parachorlm*rolm/Mmix,4.0);
        sigma_mix = sigma_mix/1000; // Birimi dyn/cm'den N/m'ye çevirdim.
        return ""+sigma_mix;
    }

    public String surten_mix_WeinaugKatz_HugillandWelsenes(String name[],double x[],double T){
        double Tci,Tcj; // Kelvin
        double Pci,Pcj; // bar
        double Parachori,Parachorj,Parachorij,Parachor_mix=0;
        double wi,wj;
        double xi,xj;
        double ro_mix;
        double Mmix = 0;
        for(int i=0;i<name.length;i++){
            critical = values.get_critical(name[i]);
            Mmix += x[i]*critical[0];
            wi  = critical[7];
            Tci = critical[2];
            Pci = critical[3];
            for(int j=0;j<name.length;j++){
                critical = values.get_critical(name[j]);
                wj  = critical[7];
                Tcj = critical[2];
                Pcj = critical[3];
                if ( Tci == 0.0 && Tcj == 0.0 && Pci == 0.0 && Pcj == 0.0 && wi == 0.0 && wj == 0.0  ){
                    return "Kritik değerleri bilinmeyen en az bir malzeme  olduğu için hesap yapılamıyor";
                }
                /*
                Parachori = (8.21307+1.97473*wi)*Math.pow(Tci,1.03406)*Math.pow(Pci,-0.82636);
                Parachorj = (8.21307+1.97473*wj)*Math.pow(Tcj,1.03406)*Math.pow(Pcj,-0.82636);
                Zuo and Stendby 1997 kaynağından alınan eşitlik. Aşağıdaki ise Hugill ve Welsenes 1986
                kaynağından alınan eşitlik. Aşağıdaki eşitlik Quayle 1953 kaynağında bulunan Parachor
                değerlerine daha yakın değerler veriyor.
                */
                Parachori = 40.1684*(0.151-0.0464*wi)*Math.pow(Tci,1.08333)/Math.pow(Pci,0.833333);
                Parachorj = 40.1684*(0.151-0.0464*wj)*Math.pow(Tcj,1.08333)/Math.pow(Pcj,0.833333);
                System.out.println("WeinaugKatz2 Parachori:"+Parachori);
                System.out.println("WeinaugKatz2 Parachorj:"+Parachorj);
                xi = x[i];
                xj = x[j];
                Parachorij = (Parachori+Parachorj)/2.0;
                Parachor_mix += xi*xj*Parachorij;
            }
        }
        try{
            ro_mix = Double.parseDouble(ro_mix_Spencer_and_Danner(T,name,x))/Mmix/1000.0; // birimini kg/m^3 yerine mol/cm^3 yapmış bulunmaktayım.
        }
        catch (NumberFormatException e1) {
            e1.printStackTrace();
            return " Karışımın yoğunluk değeri hesaplanamadığı için hesap yapılamıyor";
        }
        double sigma_mix = Math.pow(Parachor_mix*ro_mix,3.6);
        sigma_mix = sigma_mix/1000; // Birimi dyn/cm'den N/m'ye çevirdim.
        return ""+sigma_mix;
    }

    public String Parachor(String name){
        critical = values.get_critical(name);
        double Tc = critical[2]; // Kelvin
        double Pc = critical[3]; // bar
        double Parachor;
        double w = critical[7];
        String s  = "";
        if ( Tc != 0 && Pc != 0 && w !=0){
            Parachor = (8.21307+1.97473*w)*Math.pow(Tc,1.03406)*Math.pow(Pc,-0.82636);
            s += "Parachor ilk yöntem="+Parachor+"\n";
            Parachor = 40.1684*(0.151-0.0464*w)*Math.pow(Tc,1.08333)/Math.pow(Pc,0.833333);
            s += "Parachor ikinci yöntem="+ Parachor+"\n";
            return ""+s;
        }
        return " Pc, Tc, w değerlerinden en az biri bilinmiyor";
    }

    public String surten_mix_Hadden(String name[],double x[],double T,double r){
        // r is power coefficient
        double sigma_i;
        double x_i;
        double sigma_mix=0;
        for(int i=0;i<name.length;i++){
            surtension_c = values.getsurtension(name[i]);
            x_i = x[i];
            try{
                sigma_i = Double.parseDouble(sur_tension(T))*1000; // Birimini tekrar dyn/cm yaptım.
            }
            catch (NumberFormatException e){
                e.printStackTrace();
                return " Saf sıvılar için olan yüzey gerilimi değerlerinden en az biri hesaplanamıyor";
            }
            sigma_mix += x_i*Math.pow(sigma_i,1);
    }
        sigma_mix = Math.pow(sigma_mix,1);
        sigma_mix = sigma_mix/1000; // Birimi dyn/cm'den N/m'ye çevirdim.
        return ""+sigma_mix;
    }

        public String sur_tension_mixtures(String sur_tension[],double mole_ratio[],double M[], String ro[]){
        boolean calculatable=true;
        double surface_tension_mix=0.0;
        double density=0.0;
        double x=0.0;
        for(int i=0;i<sur_tension.length;i++){
            //System.out.println("sur tension:"+ sur_tension[i]);
            try {
                Double.parseDouble(sur_tension[i]);
                Double.parseDouble(ro[i]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                calculatable=false;
                break;
            }
        }
        double n=3.6;
        double V=0.0,ro_mix=0.0;
        double Pi=0.0,Pj=0.0,Vi=0.0,Vj=0.0,sigmai=0.0,sigmaj=0.0,P_mix=0.0;
        if(calculatable == true){
            for(int i=0;i<sur_tension.length;i++){
                for(int j=0;j<sur_tension.length;j++){
                    Vi=M[i]/Double.parseDouble(ro[i]);
                    Vj=M[j]/Double.parseDouble(ro[j]);
                    sigmai = Double.parseDouble(sur_tension[i]);
                    sigmaj = Double.parseDouble(sur_tension[j]);
                    Pi=Vi*Math.pow(sigmai,0.25);
                    Pj=Vj*Math.pow(sigmaj,0.25);

                    P_mix += mole_ratio[i]*mole_ratio[j]*(Pi+Pj)/2;
                }

            }
            ro_mix =Double.parseDouble(ro_mix_molar(ro,mole_ratio,M));

            surface_tension_mix = Math.pow(P_mix*ro_mix,n);

        }


        return ""+x;
    }



    public Object[][] calculate_values_for_pure(String name,double Ti,double Pi) {
        this.name=name;
        T=Ti;
        P=Pi;
        Ru=8.3145; // kJ/kmol/K
        // h0 ve u0 değerleri kJ/kmol
        // cpler de kmol cinsinden



        // ===============================================================
        // Seçilen sıvıya göre gerekli sınıf değişkenlerinin değerlerinin atanması. Burası belki array ile yapılabilir. Daha fazla değer ekleyince karışıklık olabilir çünkü.


       ro_c=values.getro(name);
       vis_c=values.getvis(name);
       cp_c=values.getcp(name);
       k_c=values.getk(name); // Sonradan düzeltmek gerek
       critical=values.get_critical(name); // Sonra düzeltmek gerek
       hvap_c=values.gethvap(name);
       a_values=values.get_a_values(name);
       Tf=values.getTf(name);
       organiccompounds_classification=values.get_orgmat_classification(name);
       surtension_c=values.getsurtension(name);
       malzemenin_turu= values.malzemenin_turu;
       Pvapor_c = values.getPvapor(name);


        vis=vis();

        k=k();

        cp=cp();

        cp_kg=cp2();

        cp_csp=cp_CSP(name,T);

        //cp_Teja = cp_Teja(name,T);

        cp_gas=cp_gas(name,T);

        ro=ro();

        v=v();

        cp_cal=cp_cal();

        h=h();

        h_kg=h2();

        s=s();

        alfa=termal_diffuzivite(cp,k,ro);

        hvap=hvap_2();

        Pr=Pr(cp,k,vis);

        vis_GCM=vis_GCM();

        vis_Lucas = vis_Lucas(name,T,P);

        vis_Przezdziecki_and_Sridhar = vis_Przezdziecki_and_Sridhar(name,T);

        vis_Letsou_and_Stiel=vis_Letsou_and_Stiel();

        k_latini=k_Latini(name);

        k_Sastri=k_Sastri(name);

        k_Missenard=k_Missenard(name,T,P);

        k_Latini_and_Baroncini=k_Latini_and_Baroncini(name,T,P);

        ro2=ro2();

        ro_Rackett = ro_Rackett(name,T);

        ro_Yamada_Gunn = ro_Yamada_Gunn(name,T);

        ro_Tait = ro_Tait(T,P);

        ro_Chand_and_Zhao = ro_Chang_and_Zhao(P);

        ro_HBT = ro_HBT(T);
        double v_Tait=0;
        try {
            v_Tait = 1/Double.parseDouble(ro_Tait);

        }
        catch (NumberFormatException e){
            e.printStackTrace();
            //System.out.println("ro_Tait değeri hesaplatılamamış");
        }

        surten = sur_tension(T);
        surten_MacleodandSugden = surten_MacleodandSugden(name,T);
        surten_BrockandBird = surten_BrockandBird();
        surten_Pitzer = surten_Pitzer();
        surten_ZuoandStendby = surten_ZuoandStendby();
        surten_SastriandRao = surten_SastriandRao();
        Pvapor= Pvapor();

        ro_c=values.getro(name);
        vis_c=values.getvis(name);
        cp_c=values.getcp(name);
        k_c=values.getk(name); // Sonradan düzeltmek gerek
        critical=values.get_critical(name); // Sonra düzeltmek gerek
        hvap_c=values.gethvap(name);
        a_values=values.get_a_values(name);
        Tf=values.getTf(name);
        organiccompounds_classification=values.get_orgmat_classification(name);
        surtension_c=values.getsurtension(name);
        malzemenin_turu= values.malzemenin_turu;
        Pvapor_c = values.getPvapor(name);



        double Tmin_for_cp_gas = (cpgas_c[5] <= cp_c[4]) ? cp_c[4] : cpgas_c[5];
        double Tmax_for_cp_gas = (cpgas_c[6] <= cp_c[5]) ?  cpgas_c[6] : cp_c[5];


        Object result[][]= {{"T,sıcaklık:",T,"K"," "},{"P,basınç:",P,"kPa"," "},{"Tc,kritik sıcaklık",critical[2],"K",""},
                {"Tb, kaynama sıcaklığı",critical[1],"K",""},{"Tf,donma sıcaklığı",Tf,"K",""},{"Pc,kritik basınç",critical[3]*100,"kPa",""},
                {"M,molar kütle",critical[0],"kg/kmol",""},{"Vc,kritik özgül hacim",critical[4],"ml/mol veya cm^3/mol",""},
                {"w,accentric factor",critical[7],"Birimsiz",""},{"Pvapor,buhar basıncı",Pvapor,"kPa",Pvapor_c[5]+"-"+Pvapor_c[6]},
                {"cp,sabit basınçta özgül ısı,katsayılar ile:",cp,"kJ/(kmolK)",cp_c[4]+"-"+cp_c[5]},
                {"cp_csp, sabit basınçta özgül ısı:",cp_csp,"kJ/(kmolK)", Tmin_for_cp_gas+"-"+Tmax_for_cp_gas},
                {"cp,sabit basınçta özgül ısı:",cp_cal,"kcal/(kmolK)",cp_c[4]+"-"+cp_c[5]},{"cp, sabit basınçta özgül ısı:",cp_kg,"(kJ/kgK)"," "},
                {"cv, sabit hacimde özgül ısı:",Cv,"(kJ/kmolK)"," "},{"cp ideal gas,ideal gaz sabit hacimde özgül ısı:",cp_gas,"(kJ/kmolK)",cpgas_c[5]+"-"+cpgas_c[6]},
                {"h,entalpi:",h,"kJ/kmol",cp_c[4]+"-"+cp_c[5]},{"h,entalpi:",h_kg,"kJ/kg",cp_c[4]+"-"+cp_c[5]},
                {"hvap,buharlaşma entalpisi,katsayılar ile:",hvap,"kJ/kg",hvap_c[3]+"-"+hvap_c[4]},
                {"u, iç enerji:",h,"kJ/kmol",cp_c[4]+"-"+cp_c[5]},{"s, entropi:",s,"kJ/(kgK)",cp_c[4]+"-"+cp_c[5]},
                {"v, özgül hacim:",v,"m^3/kg",ro_c[4]+"-"+ro_c[5]},
                {"ro,yoğunluk,katsayılar ile:",ro,"kg/m^3",ro_c[4]+"-"+ro_c[5]},{"ro,yoğunluk(diğer bir yöntem):",ro2,"kg/m^3",ro_c[4]+"-"+ro_c[5]},
                {"ro,Rackett:",ro_Rackett,"kg/m^3",ro_c[4]+"-"+ro_c[5]},{"ro,Yamada and Gunn:",ro_Yamada_Gunn,"kg/m^3",ro_c[4]+"-"+ro_c[5]},
                {"ro,HBT:",ro_HBT,"kg/m^3",ro_c[4]+"-"+ro_c[5]}, {"ro,Tait:",ro_Tait,"kg/m^3",ro_c[4]+"-"+ro_c[5]},{"ro,Chang and Zhao:",ro_Chand_and_Zhao,"kg/m^3",ro_c[4]+"-"+ro_c[5]},
                {"v,Tait:",v_Tait,"m^3/kg",ro_c[4]+"-"+ro_c[5]},
                {"viskozite,katsayılar ile:",vis," Ns/m^2",vis_c[4]+"-"+vis_c[5]},{"vis, Przezdziecki and Sridhar:",vis_Przezdziecki_and_Sridhar," Ns/m^2",vis_c[4]+"-"+vis_c[5]},
                {"vis, Lucas:",vis_Lucas," Ns/m^2",vis_c[4]+"-"+vis_c[5]},{"vis, Letsou and Sitel:",vis_Letsou_and_Stiel," Ns/m^2",vis_c[4]+"-"+vis_c[5]},
                {"viskozite,GCM:",vis_GCM," Ns/m^2",vis_c[4]+"-"+vis_c[5]},
                {"k, ısıl iletkenlik,katsayılar ile:",k," W/(mK)",k_c[3]+"-"+k_c[4]},{"k, ısıl iletkenlik(latini met.):",k_latini," W/(mK)",k_c[3]+"-"+k_c[4]},
                {"k, ısıl iletkenlik(Sastri met.):",k_Sastri," W/(mK)",k_c[3]+"-"+k_c[4]},
                {"k,Missenard metodu sıkıştırılmış sıvı:",k_Missenard," W/(mK)",k_c[3]+"-"+k_c[4]},
                {"k,Latini ve Baroncini metodu sıkıştırılmış sıvı:",k_Latini_and_Baroncini," W/(mK)",k_c[3]+"-"+k_c[4]},
                {"sigma, yüzey gerilimi, katsayılar ile:",surten," N/m",surtension_c[3]+"-"+surtension_c[4]},
                {"sigma(Macleod-Sugden), yüzey gerilimi:",surten_MacleodandSugden," N/m",surtension_c[3]+"-"+surtension_c[4]},
                {"sigma( Brock and Bird), yüzey gerilimi:",surten_BrockandBird," N/m",surtension_c[3]+"-"+surtension_c[4]},
                {"sigma(Pitzer), yüzey gerilimi:",surten_Pitzer," N/m",surtension_c[3]+"-"+surtension_c[4]},
                {"sigma(Zuo-Stenby), yüzey gerilimi:",surten_ZuoandStendby," N/m",surtension_c[3]+"-"+surtension_c[4]},
                {"sigma(Sastri-Rao), yüzey gerilimi:",surten_SastriandRao," N/m",surtension_c[3]+"-"+surtension_c[4]},{"Prandtl Number:",Pr," Birimsiz"," "},
                {"termal difüzivite:",alfa,"m^2/s",""}
        };



        return result;

    }

    // Sıcaklıkları içeren arraylerdeki min ve max sıcaklıkları bulmak için kullanacağım.
    public double findMin(double array[]){
        double min = array[0];
        for (int i=0;i<array.length;i++){
            if(array[i] < min){
                min =  array[i];
            }
        }
        return min;
    }
    public double findMax(double array[]){
        double max = array[0];
        for (int i=0;i<array.length;i++){
            if(array[i] > max){
                max =  array[i];
            }
        }
        return max;
    }


    public Object[][] calculate_values_for_mixtures2(String liquid_names[],double mole[],double Ti,double P) {

        String vis_mix,k_mix,surten_mix,ro_mix,cp_mix;

        String h[]=new String[liquid_names.length];
        String cp[]=new String[liquid_names.length];
        String sur_tension[]=new String[liquid_names.length];
        String vis[]=new String[liquid_names.length];
        String k[]=new String[liquid_names.length];
        String ro[]=new String[liquid_names.length];

        // Uygun sıcaklık aralığını bulabilmek için her bir sıvının her bir özellik için Tmin ve Tmax
        // değerlerini bulmam gereki. Daha sonra Tmin dizisindeki en büyük değeri
        // Tmax'taki en küçük değeri okuyacağım ve uyfun sıcaklık aralığı bu olacak.
        double ro_Tmin[]=new double[liquid_names.length];
        double ro_Tmax[]=new double[liquid_names.length];
        double cp_Tmin[]=new double[liquid_names.length];
        double cp_Tmax[]=new double[liquid_names.length];
        double surten_Tmin[]=new double[liquid_names.length];
        double surten_Tmax[]=new double[liquid_names.length];
        double vis_Tmin[]=new double[liquid_names.length];
        double vis_Tmax[]=new double[liquid_names.length];
        double k_Tmin[]=new double[liquid_names.length];
        double k_Tmax[]=new double[liquid_names.length];

        T=Ti; // ro_c vis_c, k_c gibi dizileri çekerken bu sıcaklık (sınıf değişkeni olan T) kullanılıyor.
        // O yüzden değerinin atanması mecburi.


      /*  for (int i = 0; i < liquid_names.length; i++) {

            System.out.println("Sıvının adı: "+liquid_names[i]+" Kütlesi:"+mole[i]);

        }*/

        Ru=8.3145; // kJ/kmol/K
        // h0 ve u0 değerleri kJ/kmol
        // cpler de kmol cinsinden

        String ro1 = null,ro2,vis1 = null,vis2,cp1 = null,cp2=null,k1 = null,k2=null,v1,v2,h1,h2,s1,s2;
        double h_mix,v_mix,s_mix;

        double x1=0,x2=0,w1 = 0,w2=0,M1,M2,N1,N2;// x1 x2 karışımdaki bileşenlerin  mol oranları-toplamı 1 olmalı
        // w1 ,w2 bileşenlerin kütlesel oranları, M1,M2 molar kütle N1,N2 mol sayıları

        //===============================================================
        // Seçilen sıvıya göre gerekli sınıf değişkenlerinin değerlerinin atanması. Burası belki array ile yapılabilir. Daha fazla değer ekleyince karışıklık olabilir çünkü.
        double total_mass=0.0;
        double total_mole=0.0;
        double M=0.0;
        double w=0;
        double N=0;
        double x[]=new double[liquid_names.length]; // Mole ratio
        double molar_mass[]=new double[liquid_names.length];
        for(int i=0;i<liquid_names.length;i++){
            critical=values.get_critical(liquid_names[i]); // Daha sonra düzelt
            //total_mass += mass[i];
            M=critical[0];
            total_mole += mole[i];
        }
        for(int i=0;i<liquid_names.length;i++){
            String name=liquid_names[i];
            //double m=mass[i];
            x[i]=mole[i]/total_mole;
            ro_c=values.getro(name);
            vis_c=values.getvis(name);
            cp_c=values.getcp(name);
            k_c=values.gethvap(name); // Sonra düzelt
            surtension_c=values.getsurtension(name);
            critical=values.get_critical(name); // Daha sonra düzelt
            M=critical[0];
            molar_mass[i]=M;
            //x[i]=mass[i]/M/total_mole;

            k_Tmin[i] = k_c[3];
            k_Tmax[i] = k_c[4];
            surten_Tmin[i] = surtension_c[3];
            surten_Tmax[i] = surtension_c[4];
            cp_Tmin[i] = cp_c[4];
            cp_Tmax[i] = cp_c[5];
            vis_Tmin[i] = vis_c[4];
            vis_Tmax[i] = vis_c[5];
            ro_Tmin[i] = ro_c[4];
            ro_Tmax[i] = ro_c[5];

            ro[i]=ro();
            vis[i]=vis();
            k[i]=k();
            cp[i]=cp();
            h[i]=h();
            sur_tension[i]=sur_tension(T);
            //System.out.println("T:"+T);
            String s = "name="+name+" ro="+ ro[i]+" vis="+vis[i]+" k="+k[i]+" cp="+cp[i]+" h="+h[i]+" st="+sur_tension[i];
           // System.out.println(s);

        }

        surten_mix = sur_tension_mixtures(sur_tension,x,molar_mass,ro);

        Pvapor_mix = Pvapor_mix_Aalto(T,liquid_names,x);

        ro_mix = ro_mix_molar(ro,x,molar_mass);
        ro_mix_Aalto = ro_mix_Aalto(T,P,liquid_names,x);
        ro_mix_Spencer_and_Danner = ro_mix_Spencer_and_Danner(T,liquid_names,x);

        vis_mix_Teja_and_Rice = vis_Teja_and_Rice(liquid_names,x,P);
        k_mix_Filippov = k_mix_Filippov_x(T,liquid_names,x);
        k_mix_Baroncini = k_mix_Baroncini(liquid_names,x);
        k_mix_Li = k_mix_Li(liquid_names,x,T);
        k_mix_PowerLaw = k_mix_PowerLaw(liquid_names,x,T);

        cp_mix=cp_mix2(cp,x);
        cp_mix_JamiesonandCartwright = cp_mix_JamiesonandCartwright(liquid_names,x,T);
        cp_mix_Teja = cp_mix_Teja(liquid_names,x,T);
        surten_mix_WeinaugKatz=surten_mix_WeinaugKatz_Macleod(liquid_names,x,T);
        surten_mix_Hadden = surten_mix_Hadden(liquid_names,x,T,-1.0);
        surten_mix_ZuoandStendby_Kays = surten_mix_ZuoandStendby_Kays(liquid_names,x,T);
        surten_mix_ZuoandStendby_RiceTeja = surten_mix_ZuoandStendby_RiceTeja(liquid_names,x,T);
        surten_mix_WeinaugKatz2 = surten_mix_WeinaugKatz_HugillandWelsenes(liquid_names,x,T);
        double kinematic_vis = 0;
        try {
            kinematic_vis = Double.parseDouble(vis_mix_Teja_and_Rice)/ Double.parseDouble(ro_mix_Aalto);
        }
        catch (NumberFormatException e ) {
            e.printStackTrace();
        }

        double stTmin = findMax(surten_Tmin); // yüzey gerilimi hesaplaması yapılabilecek aralık için min değeri
        double stTmax = findMin(surten_Tmax);
        double cpTmin = findMax(cp_Tmin); // cp hesaplaması yapılabilecek aralık için min değeri
        double cpTmax = findMin(cp_Tmax);
        double kTmin = findMax(k_Tmin); // ısıl iletkenlik hesaplaması yapılabilecek aralık için min değeri
        double kTmax = findMin(k_Tmax);
        double visTmin = findMax(vis_Tmin);// viskozite hesaplaması yapılabilecek aralık için min değeri
        double visTmax = findMin(vis_Tmax);
        double roTmin = findMax(ro_Tmin);// yoğunluk hesaplaması yapılabilecek aralık için min değeri
        double roTmax = findMin(ro_Tmax);



       // System.out.println("x1:"+x1+" x2:"+x2+" w1:"+w1+" w2:"+w2);





//        Object result[][]= {{"T,sıcaklık:",T,"K"," "},{"P,basınç:",P,"kPa"," "},
//                {"cp,sabit basınçta özgül ısı:",cp,"kJ/kmolK",cp_c[4]+"-"+cp_c[5]},{"cp,sabit basınçta özgül ısı:",cp_cal,"kcal/kmolK",cp_c[4]+"-"+cp_c[5]}
//                ,{"cv, sabit hacimde özgül ısı:",Cv,"kJ/kmolK"," "},
//                {"h,entalpi:",h,"kJ/kmol",cp_c[4]+"-"+cp_c[5]},{"h,entalpi:",h_kg,"kJ/kg",cp_c[4]+"-"+cp_c[5]},
//                {"u, iç enerji:",u,"kJ/kmol",cp_c[4]+"-"+cp_c[5]},
//                {"s, entropi:",s,"kJ/(kgK)",cp_c[4]+"-"+cp_c[5]},{"v, özgül hacim:",v,"m^3/kg",ro_c[4]+"-"+ro_c[5]},
//                {"ro,yoğunluk:",ro,"kg/m^3",ro_c[4]+"-"+ro_c[5]},{"g, gibbs serbest enerjisi:",g,"kJ/kmol",""},
//                {"viskozite:",vis," Ns/m^2",vis_c[4]+"-"+vis_c[5]},{"k, ısıl iletkenlik:",k," W/(mK)",k_c[3]+"-"+k_c[4]}
//
//        };

        Object result[][]= {{"T,sıcaklık:",T,"K",""},{"P,basınç:",P,"kPa",""},
                {"Pvapor_mix,kabarcıklanma basıncı:",Pvapor_mix,"kPa",""},
                {"cp_mix,sabit basınçta özgül ısı:",cp_mix,"kJ/kmolK",cpTmin+"-"+cpTmax},
                {"cp_mix,sabit basınçta özgül ısı, Jamieson ve Cartwright:",cp_mix_JamiesonandCartwright,"kJ/kmolK",cpTmin+"-"+cpTmax},
                {"cp_mix,sabit basınçta özgül ısı, Teja:",cp_mix_Teja,"kJ/kmolK",cpTmin+"-"+cpTmax},
                {"ro_mix,toplam kütle ve toplam hacim ile hesap:",ro_mix,"kg/m^3",roTmin+"-"+roTmax},
                {"ro_mix_Aalto,hesaplamalara basınç dahil:",ro_mix_Aalto," kg/m^3",roTmin+"-"+roTmax},
                {"ro_mix_Spencer_and_Danner, kabarcıklanma basıncı:",ro_mix_Spencer_and_Danner," kg/m^3",roTmin+"-"+roTmax},
                {"surten_mix_Hadden,yüzey gerilimi:",surten_mix_Hadden,"N/m",stTmin+"-"+stTmax},
                {"surten_mix_WeinaugKatz_Macleod:",surten_mix_WeinaugKatz,"N/m",stTmin+"-"+stTmax},
                {"surten_mix_WeinaugKatz_HugillandWelsenes,",surten_mix_WeinaugKatz2,"N/m",stTmin+"-"+stTmax},
                {"surten_mix_ZuoandStendby_Kays,",surten_mix_ZuoandStendby_Kays,"N/m",stTmin+"-"+stTmax},
                {"surten_mix_ZuoandStendby_RiceTeja:",surten_mix_ZuoandStendby_RiceTeja,"N/m",stTmin+"-"+stTmax},
                {"vis_mix Teja and Rice,viskozite:",vis_mix_Teja_and_Rice," Ns/m^2",visTmin+"-"+visTmax},
                {"kinematic_viskozite :",kinematic_vis," m^2/s",visTmin+"-"+visTmax},
                {"k_mix_Filippov, ısıl iletkenlik:",k_mix_Filippov,"W/(mK)",kTmin+"-"+kTmax},
                {"k_mix_Baroncini, ısıl iletkenlik:",k_mix_Baroncini,"W/(mK)",kTmin+"-"+kTmax},
                {"k_mix_Li, ısıl iletkenlik:",k_mix_Li,"W/(mK)",kTmin+"-"+kTmax},
                {"k_mix_PowerLaw, ısıl iletkenlik:",k_mix_PowerLaw,"W/(mK)",kTmin+"-"+kTmax}

        };


        return result;

    }













    public static void main(String[] args) {

        //gas3 gas=new gas3();

/*        for(double a=100.0;a<6000;a+=100) {
        	gas=new gas("CO",a);
        	gas.all_methods();
        }
       */
        /*
         * Object v[][]=gas.calculate_values("CO", 300,100); create_gui model=new
         * create_gui();
         *
         * // model.panel(v);
         *
         * String gas_name=model.gas_name;
         */
        /*
         * ArrayList<Double>xseries=new ArrayList<Double>();
         * ArrayList<Double>yseries=new ArrayList<Double>();
         *
         *
         * for(double x=100.0;x<=3000.0;x+=10.0) { xseries.add(x); yseries.add((Double)
         * (gas.calculate_values("CO", x, 100.0)[5][1])); } int boyut=xseries.size();
         * double xdeger[]=new double[boyut]; double ydeger[]=new double[boyut];
         *
         * for(int i=0;i<boyut;i++) { xdeger[i]=xseries.get(i);
         * ydeger[i]=yseries.get(i); }
         *
         * xygrafik grafik=new xygrafik(); grafik.degergir(xdeger, ydeger);
         */

        String isimler[]= {"CHCl2F","CHCl3","CHF3","CHI3","CHN","CHNS","CH2BrCI","СН2Вr2",
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


        liquids liquid=new liquids();
        for(int i=0;i<isimler.length;i++) {

            liquid.calculate_values_for_pure(isimler[i], 298, 1);

        }



    }



}

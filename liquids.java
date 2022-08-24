package bitirme_tezi;

import java.io.File;
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


    double vis_c[],k_c[],ro_c[],cp_c[],critical[],hvap_c[],a_values[],Tf,organiccompounds_classification[],surtension_c[];// viskozite coefficients

    String vis,k,ro,cp,v,cp_cal,h,u,s,h_kg,Pr,alfa,cp_kg,hvap,cp_csp,vis_GCM,k_latini,ro2,sigma,sigma2,sigma3,sigma4,sigma5; //h_kg birimi kJ/kg olduğu için şimdilik böyle yazdım.
    String vis_mix;
    String malzemenin_turu;
    double T;

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
    public double cp(String name) { // (kJ/(kmolK))
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



    public String cp_CSP() { // (kJ/(kmolK))
        // CSP: Corresponding States Method

        // Tmin ve Tmax değerleri verilmeyen malzemeler vardı. Onları her sıcaklıkta olur diye kabul ettiğim için
        // 0 ile 10000 arası diye kafama göre yazdım.
        double w,Tr,Tc,a0,a1,a2,a3,a4,Tmin,Tmax;
        a0 = a_values[0];
        a1 = a_values[1];
        a2= a_values[2];
        a3 = a_values[3];
        a4 = a_values[4];
        Tmin = a_values[5];
        Tmax = a_values[6];
        double cp_saturated;
        double cp=0;
        w=critical[7];
        Tc=critical[2];
        double M = critical[0] ;
        double Ru= 8.3145; // kJ/(kmolK)
        Tr=T/Tc;
        double R = Ru/M;   // kJ/(kgK)


        if( Tr < 0.99 && (T>=Tmin && T<= Tmax) )
        {
            cp = R* (1.586 + 0.49/(1-Tr)+w*(4.2775 + 6.3/Tr*Math.pow(1.0-Tr,0.3333)+0.4355/(1-Tr)) +a0+a1*T+a2*T*T+a3*T*T*T+a4*T*T*T*T) ;
            cp_saturated =  cp - R* Math.pow(Math.E,20.1*Tr-17.9);
            return (""+cp+"    "+cp_saturated);
        }
        else {
            return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
        }
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
    public String cp_mix2(String cp[],double x[]) {
        double cp_mix=0; // cp değerleri mol biriminde olduğu için x1 ve x2 kullanıldı.
        for(int i=0;i<cp.length;i++){
            try {
                cp_mix += Double.parseDouble(cp[i]);

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


            if(cp_c[4]<=T && T<=cp_c[5])
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
    public String hvap() {  //  (kJ / kg)
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






    public String h2() {  // kJ/kg
        double A,B,C,D;

        double h=0;
        double Tref=0;
        double href=0;
        double sref=0;
        double M= critical[0];


            if(cp_c[4]<=T && T<=cp_c[5])
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





    public String s() {// kJ/kg
        double A,B,C,D;
        double s=0;
        double Tref=1,href=0,sref=0,M=critical[0], vref,v;
        double Ru=8.3145; // kJ/(kmolK)

            vref=v(Tref); // m^3/kg
            v=v(T);
            //System.out.println("vref="+vref+" v="+v);
            if(cp_c[4]<=T && T<=cp_c[5])
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
    public double k(String name) {
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
    public String k_Latini(){
        // Latini et. al method

        double A,Tb,Tc,M,Tr,Asharp,alfa,beta,gamma;
        double k=0;
        M=critical[0];
        Tb=critical[1];
        Tc=critical[2];
        Tr=T/Tc;
        Asharp=organiccompounds_classification[0];
        alfa=organiccompounds_classification[1];
        beta=organiccompounds_classification[2];
        gamma=organiccompounds_classification[3];

        if(k_c[3]<=T && T<=k_c[4])
        {
            A=Asharp*Math.pow(Tb,alfa)/Math.pow(M,beta)/Math.pow(Tc,gamma);
            k=A*Math.pow(1-Tr,0.38)/Math.pow(Tr,0.166666);

            return (""+k);
        }
        else {
            return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
        }

    }

    //Flippov Equation
    public String k_mix_Flippov(double k1,double k2,double w1,double w2) {
        double k_mix=w1*k1+w2*k2-0.72*w1*w2*(k2-k1);

        return ""+k_mix;

    }

    // Baroncini et al Correlation

    public String k_mix_Baron(double k1,double k2,double x1,double x2, double A1, double A2, double T, double Tc1, double Tc2) {
        double Tcm=x1*Tc1+x2*Tc2;
        double Trm= T/Tcm;

        double k_mix= (x1*x1*A1+x2*x2*A2+2.2*x1*x2*Math.pow((Math.pow(A1, 3.0)/A2),0.5))*(Math.pow(1-Trm, 0.38)/Math.pow(Trm, 1/6)) ;
        return " "+k_mix;
    }
    public double vis(String name,double T) {
        double A,B,C,D;

        double vis=0;
        vis_c = values.getvis(name);
            A=vis_c[0];
            B=vis_c[1];
            C=vis_c[2];
            D=vis_c[3];
            System.out.println("A="+A);
            System.out.println("B="+B);
            System.out.println("C="+C);
            System.out.println("D="+D);
            vis=Math.pow(10.0, A+B/T+C*T+D*T*T); // kitaptan çekilen katsayılar ile elde edilen değerler centipoise birimindedir
            System.out.println("double vis="+vis);
            vis=vis/1000; // Pa.s birimine çevirdim
            System.out.println("vis="+vis);

            return vis;


    }

    public String vis() {
        double A,B,C,D;

        double vis=0;
//        for(int i=0;i<vis_c.length;i++){
//            System.out.println("vis_c["+i+"]="+vis_c[i]);
//        }

        if(vis_c[4]<=T && T<=vis_c[5])
        {
            A=vis_c[0];
            B=vis_c[1];
            C=vis_c[2];
            D=vis_c[3];
            System.out.println("A="+A);
            System.out.println("B="+B);
            System.out.println("C="+C);
            System.out.println("D="+D);
            vis=Math.pow(10.0, A+B/T+C*T+D*T*T); // kitaptan çekilen katsayılar ile elde edilen değerler centipoise birimindedir
            System.out.println("String vis1="+vis);
            vis=vis/1000; // Pa.s birimine çevirdim
            System.out.println("vis2="+vis);
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



    // Grunberg and Nissan Method
        public String vis_mix_GN(double vis1, double vis2,double x1,double x2) {// sıvı karışımının viskozite değeri
        double power=x1*Math.log(vis1)+x2*Math.log(vis2);// vis1 ve vis2 değerlerini centipoise biriiminden hesaplamam gerekiyor.
        double viskozite=Math.pow(Math.E, power);
        /*System.out.println(power);
        System.out.println(x1+" "+x2);*/
        return ""+viskozite;
    }
    public String vis_mix_GN2(String vis[],double x[]) {// sıvı karışımının viskozite değeri
       double power=0;
       for(int i=0;i<vis.length;i++){
           System.out.println("viskozite:"+ vis[i]);

           try{
               power += x[i]*(Math.log(Double.parseDouble(vis[i])));
           }
           catch (NumberFormatException e){
               e.printStackTrace();
               return "Bu sıcaklık değeri için hesaplama yapılamıyor.";
           }

       }
       double viskozite= Math.pow(Math.E, power);


        return ""+viskozite;
    }
    //Teja and Rice Method



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
    public double ro(String name) {
        double A,B,C,n;
         ro_c = values.getro(name);
        double ro=0;



            A=ro_c[0];
            B=ro_c[1];
            C=ro_c[2];
            n=ro_c[3];

            ro=A*Math.pow(B, -Math.pow((1-T/C), n)); // g/ml birimindedir.
            ro*=1000; // Birimi kg/m^3 yaptım.
            System.out.println("ro metodu içinde:density="+ro);
            System.out.println("A="+A+" B="+B+" C="+C+" n="+n);
            return ro;



    }
    public String ro() {
        double A,B,C,n;

        double ro=0;
        System.out.println("A="+ro_c[0]+" B="+ro_c[1]+" C="+ro_c[2]+" n="+ro_c[3]+" T="+T);


        if(ro_c[4]<=(T+5.0) && (T-5.0)<=ro_c[5])
        {
            A=ro_c[0];
            B=ro_c[1];
            C=ro_c[2];
            n=ro_c[3];

            ro=A*Math.pow(B, -Math.pow((1-T/C), n)); // g/ml birimindedir.
            ro*=1000; // Birimi kg/m^3 yaptım.
            System.out.println("ro metodu içinde:density="+ro);
            System.out.println("A="+A+" B="+B+" C="+C+" n="+n);
            return (""+ro);

        }
        else {

            return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
        }
    }

    public String ro_mix_molar(String ro[],double x[],double M[]) {
        double ro_mix=0;
        double pay=0;
        double payda=0;
        for(int i=0;i<ro.length;i++){
            System.out.println(" ro:"+ ro[i]);

            pay += M[i]*x[i];
            try{
                payda += M[i]*x[i]*Double.parseDouble(ro[i]);
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
//



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


            sigma = A*Math.pow(1-T/B,n);
            sigma = sigma/1000; // Birimini N/m' ye çevirmek için yaptım.


        return  sigma;

    }

    public String sur_tension(){ //surface tension: Orijinal halinde birimi dynes/cm ama ben N/m' ye çevireceğim.

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
    public String sur_tension2(){ //surface tension: Orijinal halinde birimi dynes/cm ama ben N/m' ye çevireceğim.
        // BROCK and BIRD
        double sigma;
        double Tb = critical[1];
        double Tc = critical[2];
        double Pc = critical[3];
        double Tbr=Tb/Tc;
        double Tr=T/Tc;
        double Q=0.1196*(1+Tbr/(1-Tbr)*Math.log(Pc/1.01325))-0.279;
        sigma=Math.pow(Pc,0.66666)*Math.pow(Tc,0.33333)*Q*Math.pow(1-Tr,1.22222);


        return ""+sigma/1000;

    }
    public String sur_tension3(){ //surface tension: Orijinal halinde birimi dynes/cm ama ben N/m' ye çevireceğim.
        // PITZER
        double sigma=0;
        double w = critical[7];
        double Tc = critical[2];
        double Pc = critical[3];
        double Tr=T/Tc;

        if(w != 0){
            sigma = Math.pow(Pc,0.66666)*Math.pow(Tc,0.33333)*(1.86+1.18*w)/19.05*Math.pow((3.75+0.91*w)/(0.291-0.08*w),0.66666)*Math.pow(1-Tr,1.2222);
        }



        return ""+sigma/1000;

    }
    public String sur_tension4(){ //surface tension: Orijinal halinde birimi dynes/cm ama ben N/m' ye çevireceğim.
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
        double sigma1=40.520*Math.pow(1-Tr,1.287);
        double sigma2=52.095*Math.pow(1-Tr,1.21548);
        double sigmar1=Math.log(1+sigma1/(Math.pow(Pc1,0.66666)*Math.pow(Tc1,0.33333)));
        double sigmar2=Math.log(1+sigma2/(Math.pow(Pc2,0.66666)*Math.pow(Tc2,0.33333)));

        double sigmar=sigmar1+(w-w1)/(w2-w1)*(sigmar2-sigmar1);

        /*System.out.println("sigma1:"+sigma1);
        System.out.println("sigma2:"+sigma2);
        System.out.println("sigmar1:"+sigmar1);
        System.out.println("sigmar2:"+sigmar2);
        System.out.println("sigmar:"+sigmar);*/

        sigma=(Math.pow(Math.E,sigmar)-1)*(Math.pow(Pc,0.66666)*Math.pow(Tc,0.33333));

        return ""+sigma/1000;

    }
    public String sur_tension5(){ //surface tension: Orijinal halinde birimi dynes/cm ama ben N/m' ye çevireceğim.
        // SASTRI-RAO
        double sigma;
        double Tb=critical[1];
        double Tc=critical[2];
        double Pc=critical[3];
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


        return ""+sigma/1000;

    }

    public String sur_tension_mixtures(String sur_tension[],double mole_ratio[],double M[], String ro[]){
        boolean calculatable=true;
        double surface_tension_mix=0.0;
        double density=0.0;
        double x=0.0;
        for(int i=0;i<sur_tension.length;i++){
            System.out.println("sur tension:"+ sur_tension[i]);
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
       malzemenin_turu= values.malzemenin_turu;;

        vis=vis();

        k=k();

        cp=cp();

        cp_kg=cp2();

        cp_csp=cp_CSP();

        ro=ro();

        v=v();

        cp_cal=cp_cal();

        h=h();

        h_kg=h2();

        s=s();

        alfa=termal_diffuzivite(cp,k,ro);

        hvap=hvap();

        Pr=Pr(cp,k,vis);

        vis_GCM=vis_GCM();

        k_latini=k_Latini();

        ro2=ro2();

        sigma = sur_tension();
        sigma2 = sur_tension2();
        sigma3 = sur_tension3();
        sigma4 = sur_tension4();
        sigma5 = sur_tension5();

        Object result[][]= {{"T,sıcaklık:",T,"K"," "},{"P,basınç:",P,"kPa"," "},{"Tc,kritik sıcaklık",critical[2],"K",""},
                {"Tb, kaynama sıcaklığı",critical[1],"K",""},
                {"Tf,donma sıcaklığı",Tf,"K",""},{"Pc,kritik basınç",critical[3],"bar",""},
                {"Vc,kritik hacim",critical[4],"ml/mol veya cm^3/mol",""},{"w,accentr,c factor",critical[7],"Birimsiz",""},
                {"cp,sabit basınçta özgül ısı:",cp,"kJ/kmolK",cp_c[4]+"-"+cp_c[5]},{"cp,sabit basınçta özgül ısı:",cp_cal,"kcal/kmolK",cp_c[4]+"-"+cp_c[5]}
                ,{"cp, sabit basınçta özgül ısı:",cp_kg,"kJ/kgK"," "},{"cv, sabit hacimde özgül ısı:",Cv,"kJ/kmolK"," "},
                {"cp_csp, sabit basınçta özgül ısı:",cp_csp,"kJ/kgK", a_values[5]+"-"+a_values[6]},
                {"h,entalpi:",h,"kJ/kmol",cp_c[4]+"-"+cp_c[5]},{"h,entalpi:",h_kg,"kJ/kg",cp_c[4]+"-"+cp_c[5]},{"hvap,buharlaşma entalpisi:",hvap,"kJ/kg",hvap_c[3]+"-"+hvap_c[4]},
                {"u, iç enerji:",u,"kJ/kmol",cp_c[4]+"-"+cp_c[5]},
                {"s, entropi:",s,"kJ/(kgK)",cp_c[4]+"-"+cp_c[5]},{"v, özgül hacim:",v,"m^3/kg",ro_c[4]+"-"+ro_c[5]},
                {"ro,yoğunluk:",ro,"kg/m^3",ro_c[4]+"-"+ro_c[5]},{"ro,yoğunluk(diğer bir yöntem):",ro2,"kg/m^3",ro_c[4]+"-"+ro_c[5]},{"g, gibbs serbest enerjisi:",g,"kJ/kmol",""},
                {"viskozite:",vis," Ns/m^2",vis_c[4]+"-"+vis_c[5]},{"viskozite,GCM:",vis_GCM," Ns/m^2",vis_c[4]+"-"+vis_c[5]},
                {"k, ısıl iletkenlik:",k," W/(mK)",k_c[3]+"-"+k_c[4]},
                {"k, ısıl iletkenlik(latini met.):",k_latini," W/(mK)",k_c[3]+"-"+k_c[4]},{"sigma, yüzey gerilimi:",sigma," N/m",surtension_c[3]+"-"+surtension_c[4]},
                {"sigma( Brock and Bird), yüzey gerilimi:",sigma2," N/m",surtension_c[3]+"-"+surtension_c[4]},{"sigma(Pitzer), yüzey gerilimi:",sigma3," N/m",surtension_c[3]+"-"+surtension_c[4]},
                {"sigma(Zuo-Stenby), yüzey gerilimi:",sigma4," N/m",surtension_c[3]+"-"+surtension_c[4]},{"sigma(Sastri-Rao), yüzey gerilimi:",sigma5," N/m",surtension_c[3]+"-"+surtension_c[4]},
                {"Prandtl Number:",Pr," Birimsiz"," "},{"termal difüzivite:",alfa,"m^2/s",""}
        };



        return result;

    }

    public Object[][] calculate_values_for_mixtures(String name1i,String name2i,double Ti,double m1i,double m2i) {
        this.name=name;
        T=Ti;
        Ru=8.3145; // kJ/kmol/K
        // h0 ve u0 değerleri kJ/kmol
        // cpler de kmol cinsinden

        String ro1,ro2,vis1,vis2,cp1,cp2,k1,k2,v1,v2,h1,h2,s1,s2;
        double ro_mix=0.0,vis_mix=0.0,cp_mix=0.0,k_mix=0.0,h_mix,v_mix,s_mix;
        String name1=name1i;
        String name2=name2i;
        double m1=m1i;
        double m2=m2i;
        double x1,x2,w1,w2,M1,M2,N1,N2;// x1 x2 karışımdaki bileşenlerin  mol oranları-toplamı 1 olmalı
        // w1 ,w2 bileşenlerin kütlesel oranları, M1,M2 molar kütle N1,N2 mol sayıları

        //===============================================================
        // Seçilen sıvıya göre gerekli sınıf değişkenlerinin değerlerinin atanması. Burası belki array ile yapılabilir. Daha fazla değer ekleyince karışıklık olabilir çünkü.

        ro_c=values.getro(name1);
        vis_c=values.getvis(name1);
        cp_c=values.getcp(name1);
        k_c=values.gethvap(name1); // Sonra düzelt
        critical=values.get_critical(name1); // Daha sonra düzelt

        w1=m1/(m1+m2);
        w2=m2/(m1+m2);

        M1=critical[0];

        N1=m1/M1;
        ro1=(ro());
        vis1=(vis());
        v1=(v());
        k1=k();
        cp1=(cp());
        h1=(h());
        s1=(s());

        ro_c=values.getro(name2);
        vis_c=values.getvis(name2);
        cp_c=values.getcp(name2);
        k_c=values.gethvap(name2); // Daha sonra düzelt
        critical=values.get_critical(name2); // Daha sonra düzelt

        M2= critical[0];


        N2=m2/M2;

        x1=N1/(N1+N2);
        x2=N2/(N1+N2);

        ro2=(ro());
        vis2=(vis());
        k2=(k());
        v2=(v());
        cp2=(cp());
        h2=(h());
        s2=(s());

        if(k1.equals(" Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor") ==false &&
           k2.equals(" Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor") == false){
            k_mix= Double.parseDouble(k_mix_Flippov(Double.parseDouble(k1),Double.parseDouble(k2),w1,w2));
        }
        else if (k1.equals(" Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor")  ||
                k2.equals(" Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor") ){
            k_mix= 0;
        }
        if(ro1.equals(" Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor") ==false &&
                ro2.equals(" Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor") == false){
            ro_mix= Double.parseDouble(ro_mix_weight(Double.parseDouble(ro1),Double.parseDouble(ro2),w1,w2));
        }
        else if (ro1.equals(" Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor")  ||
                ro2.equals(" Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor") ){
            ro_mix= 0;
        }
        if(cp1.equals(" Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor") ==false &&
                cp2.equals(" Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor") == false){
            cp_mix= Double.parseDouble(cp_mix(Double.parseDouble(cp1),Double.parseDouble(cp2),x1,x2));
        }
        else if (cp1.equals(" Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor")  ||
                cp2.equals(" Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor") ){
            cp_mix= 0;
        }
        if(vis1.equals(" Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor") ==false &&
                vis2.equals(" Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor") == false){
            vis_mix= Double.parseDouble(vis_mix_GN(Double.parseDouble(vis1),Double.parseDouble(vis2),x1,x2));
        }
        else if (vis1.equals(" Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor")  ||
                vis2.equals(" Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor") ){
            vis_mix= 0;
        }

        //System.out.println("x1:"+x1+" x2:"+x2+" w1:"+w1+" w2:"+w2);





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

        Object result[][]= {{"T,sıcaklık:",T,"K"},{"P,basınç:",P,"kPa"},{"m1,1.malzeme kütle:",m1,"kg"},{"m2,2.malzeme kütle:",m2,"kg"},
                {"cp_mix,sabit basınçta özgül ısı:",cp_mix,"kJ/kmolK"},
                {"ro_mix,yoğunluk:",ro_mix,"kg/m^3"},
                {"vis_mix,viskozite:",vis_mix," Ns/m^2"},{"k_mix, ısıl iletkenlik:",k_mix,"W/(mK)"}

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


    public Object[][] calculate_values_for_mixtures2(String liquid_names[],double mole[],double Ti) {

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
            x[i]=M/total_mole;
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
            sur_tension[i]=sur_tension();
            System.out.println("T:"+T);
            String s = "name="+name+" ro="+ ro[i]+" vis="+vis[i]+" k="+k[i]+" cp="+cp[i]+" h="+h[i]+" st="+sur_tension[i];
            System.out.println(s);

        }

        surten_mix = sur_tension_mixtures(sur_tension,x,molar_mass,ro);
        ro_mix = ro_mix_molar(ro,x,molar_mass);
        vis_mix = vis_mix_GN2(vis,x);
        cp_mix=cp_mix2(cp,x);
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
                {"cp_mix,sabit basınçta özgül ısı:",cp_mix,"kJ/kmolK",cpTmin+"-"+cpTmax},
                {"ro_mix,yoğunluk:",ro_mix,"kg/m^3",roTmin+"-"+roTmax},{"surten_mix,yüzey gerilimi:",surten_mix,"N/m",stTmin+"-"+stTmax},
                {"vis_mix,viskozite:",vis_mix," Ns/m^2",visTmin+"-"+visTmax},{"k_mix, ısıl iletkenlik:",0,"W/(mK)",kTmin+"-"+kTmax}

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

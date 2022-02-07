package bitirme_tezi;

import java.util.ArrayList;

import javax.swing.JOptionPane;


// Sıvılar
//Evren TANIK Ege Üniversitesi Makina Mühendisliği

public class liquids {

    // Sınıf değişkenlerinin tanımlanması
    //==============================================================

    liquid_values values=new liquid_values();

    //coefficients coefficient=new coefficients();


    double M,Tb,Tc,Pc,Zc,wp,v0;


    double vis_c[],k_c[],ro_c[],cp_c[],ref[];// viskozite coefficients

    String vis,k,ro,cp,v,cp_cal,h,u,s,h_kg,Pr,alfa; //h_kg birimi kJ/kg olduğu için şimdilik böyle yazdım.
    String vis_mix;
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



    public String cp() {
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


    public String cp_mix(double cp1,double cp2,double x1,double x2) {
        double cp_mix=x1*cp1+x2*cp2; // cp değerleri mol biriminde olduğu için x1 ve x2 kullanıldı.

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
    public String h() {
        double A,B,C,D;
        double h=0;
        double Tref,href,sref,M;

        if(ref.length==4) {
            Tref=ref[0];  // K
            href=ref[1]; // kJ/kg
            sref=ref[2]; // kJ/(kgK)
            M=ref[3];    // g/mol
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

        else {
            return "href değ. bilinm. için hesap yapılamıyor.";
        }

    }



    public String h2() {// kJ/kg
        double A,B,C,D;
        double h=0;
        double Tref,href,sref,M;

        if(ref.length==4) {
            Tref=ref[0];  // K
            href=ref[1]; // kJ/kg
            sref=ref[2]; // kJ/(kgK)
            M=ref[3];    // g/mol
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

        else {
            return "href değ. bilinm. için hesap yapılamıyor.";
        }

    }
    public String s() {// kJ/kg
        double A,B,C,D;
        double s=0;
        double Tref,href,sref,M,vref,v;
        double Ru=8.3145; // kJ/(kmolK)
        if(ref.length==4) {
            Tref=ref[0];  // K
            href=ref[1];  // kJ/kg
            sref=ref[2];  // kJ/(kgK)
            M=ref[3];     // g/mol
            vref=v(Tref); // m^3/kg
            v=v(T);
            System.out.println("vref="+vref+" v="+v);
            if(cp_c[4]<=T && T<=cp_c[5])
            {
                A=cp_c[0];
                B=cp_c[1];
                C=cp_c[2];
                D=cp_c[3];
                s=(((A-Ru)*Math.log(T)+B*T+C*T*T/2+D*T*T*T/3)-((A-Ru)*Math.log(Tref)+B*Tref+C*Tref*Tref/2+D*Tref*Tref*Tref/3)+ Ru*Math.log(v/vref))/M+sref; // kJ/kg
                return (""+s);
            }

            else {
                return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
            }

        }

        else {
            return "sref değ. bilinm. için hesap yapılamıyor.";
        }

    }


    public String k() {
        double A,B,C;
        double k=0;



        if(k_c[3]<=T && T<=k_c[4])
        {
            A=k_c[0];
            B=k_c[1];
            C=k_c[2];
            if(k_c[5]==1.0) {
                k=Math.pow(10.0,(A+B* Math.pow((1-T/C), 2.0/7.0)));}
            else if(k_c[5]==2.0) {
                k=A+B*T+C*T*T;
            }
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


    public String vis() {
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
        System.out.println(power);
        System.out.println(x1+" "+x2);
        return ""+viskozite;
    }

    //Teja and Rice Method




    public String ro() {
        double A,B,n,Tc;

        double ro=0;

        if(ro_c[4]<=T && T<=ro_c[5])
        {
            A=ro_c[0];
            B=ro_c[1];
            n=ro_c[2];
            Tc=ro_c[3];

            ro=A*Math.pow(B, -Math.pow((1-T/Tc), n)); // g/ml birimindedir.
            ro*=1000; // Birimi kg/m^3 yaptım.
            return (""+ro);
        }
        else {
            return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
        }
    }

    public String ro_mix_molar(double ro1,double ro2,double x1, double x2,double M1,double M2) {

        double ro_mix=(M1*x1+M2*x2)/(ro1*M1*x1+ro2*M2*x2);
        return ""+ro_mix;

    }

    public String ro_mix_weight(double ro1,double ro2,double w1, double w2) {

        double ro_mix=(w1+w2)/(w1/ro1+w2/ro2);
        return ""+ro_mix;

    }

    public String v() {
        double A,B,n,Tc;

        double ro=0;
        double v=0;

        if(ro_c[4]<=T && T<=ro_c[5])
        {
            A=ro_c[0];
            B=ro_c[1];
            n=ro_c[2];
            Tc=ro_c[3];

            ro=A*Math.pow(B, -Math.pow((1-T/Tc), n)); // g/ml birimindedir.
            ro*=1000; // Birimi kg/m^3 yaptım.
            v=1/ro;
            return (""+v);
        }
        else {
            return " Bu sıcaklık değeri için "+"\n"+" hesaplama yapılamıyor";
        }
    }

    public double  v(double T) {
        double A,B,n,Tc;

        double ro=0;
        double v=0;


        A=ro_c[0];
        B=ro_c[1];
        n=ro_c[2];
        Tc=ro_c[3];

        ro=A*Math.pow(B, -Math.pow((1-T/Tc), n)); // g/ml birimindedir.
        ro*=1000; // Birimi kg/m^3 yaptım.
        v=1/ro;
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
       k_c=values.getk(name);
       ref=values.getref(name);

        vis=vis();

        k=k();

        cp=cp();

        ro=ro();

        v=v();

        cp_cal=cp_cal();

        h=h();

        h_kg=h2();

        s=s();

        alfa=termal_diffuzivite(cp,k,ro);

        Pr=Pr(cp,k,vis);

        Object result[][]= {{"T,sıcaklık:",T,"K"," "},{"P,basınç:",P,"kPa"," "},
                {"cp,sabit basınçta özgül ısı:",cp,"kJ/kmolK",cp_c[4]+"-"+cp_c[5]},{"cp,sabit basınçta özgül ısı:",cp_cal,"kcal/kmolK",cp_c[4]+"-"+cp_c[5]}
                ,{"cv, sabit hacimde özgül ısı:",Cv,"kJ/kmolK"," "},
                {"h,entalpi:",h,"kJ/kmol",cp_c[4]+"-"+cp_c[5]},{"h,entalpi:",h_kg,"kJ/kg",cp_c[4]+"-"+cp_c[5]},
                {"u, iç enerji:",u,"kJ/kmol",cp_c[4]+"-"+cp_c[5]},
                {"s, entropi:",s,"kJ/(kgK)",cp_c[4]+"-"+cp_c[5]},{"v, özgül hacim:",v,"m^3/kg",ro_c[4]+"-"+ro_c[5]},
                {"ro,yoğunluk:",ro,"kg/m^3",ro_c[4]+"-"+ro_c[5]},{"g, gibbs serbest enerjisi:",g,"kJ/kmol",""},
                {"viskozite:",vis," Ns/m^2",vis_c[4]+"-"+vis_c[5]},{"k, ısıl iletkenlik:",k," W/(mK)",k_c[3]+"-"+k_c[4]},
                {"Prandtl Number:",vis," Birimsiz"," "},{"termal difüzivite:",k,"m^2/s",""}
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
        k_c=values.getk(name1);
        ref=values.getref(name1);

        w1=m1/(m1+m2);
        w2=m2/(m1+m2);


        if(ref.length==4){
           M1=ref[3];//kg/kmol
            //M1=5;
        }
        else{
            M1=10; // Birçok sıvı için henüz M değeri girilmedi. Bunu kod hata vermesin diye ekledim ama bu şekilde sonuçlar zaten hatalı
            //olur.
        }

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
        k_c=values.getk(name2);
        ref=values.getref(name2);

        if(ref.length==4){
            M2=ref[3];//kg/kmol
           // M2=5;
        }
        else{
            M2=10;
        }

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

        System.out.println("x1:"+x1+" x2:"+x2+" w1:"+w1+" w2:"+w2);





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

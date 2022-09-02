package bitirme_tezi;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

    public class xy_grafik extends JPanel implements ActionListener {

        public ArrayList<double[]> xlist=new ArrayList<double[]>();
        public ArrayList<double[]> ylist=new ArrayList<double[]>();
        private boolean tagsVisible=false;
        private JButton buton;
        private String xlabel="deneme";
        private String ylabel="deneme";

        double[] xseries,xseries2;
        double[] yseries,yseries2;
        //private NumberFormat numberformat=NumberFormat.getInstance(); // Virgülden sonra kaç rakam
        // gösterileceğini ayarlamam için gerekli
        public xy_grafik(String x_label,String y_label) {
            setLayout(null);
            xlabel = x_label;
            ylabel = y_label;
            this.setBounds(0,0,300,300);
            //this.setSize(300,300);
            buton=new JButton("Veri etiketlerini gizle");
            buton.setBounds(700, 300, 170, 30);
            buton.addActionListener(this);
            buton.setFocusPainted(false);
            add(buton);
            setBackground(Color.cyan);
/*		JFrame frame=new JFrame("XY Grafiği");
		frame.setLayout(null);
		frame.setBounds(0, 0, 900, 700);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(this);
		frame.setVisible(true);*/

        }


        public void setValues(double x[],double y[]) {

        xlist.clear();
        ylist.clear();
        xlist.add(x);
        ylist.add(y);
        }

        public void setValues(ArrayList<double[]> xlist,ArrayList<double[]> ylist) {
            this.xlist.clear();
            this.ylist.clear();
            this.xlist= xlist;
            this.ylist= ylist;
        }

        public void createGraphic(String name,String graphic_name){
            // name : liquid name'i ifade eder.
            Object o[];
            if(graphic_name.equalsIgnoreCase("viscosity")){
                o = viscosity_values(name);
            }
            else if(graphic_name.equalsIgnoreCase("surface tension")){
                o = surten_values(name);
            }
            else if(graphic_name.equalsIgnoreCase("ısıl iletkenlik")){
                o= k_values(name);
            }
            else if(graphic_name.equalsIgnoreCase("yoğunluk")){
                o= ro_values(name);
            }
            else {
                o = cp_values(name);
            }


            ArrayList<double[]> x ;
            ArrayList<double[]> y ;
            x = (ArrayList<double[]>) o[0];
            y = (ArrayList<double[]>) o[1];
            setValues(x,y);
            repaint();

     /*       for(int i=0;i<x.size();i++){
                for(int k=0;k<x.get(i).length;k++){
                    System.out.println("x değeri:"+x.get(i)[k]);
                    System.out.println("y değeri:"+y.get(i)[k]);
                }
            }*/

        }
        public Object[] cp_values(String name) {
            liquid_values values = new liquid_values();
            liquids liquids = new liquids();
            double cp_c[]=values.getcp(name);
            ArrayList<double[]> x = new ArrayList<double[]>();
            ArrayList<double[]> y = new ArrayList<double[]>();
            double x_ekseni[]  = new double[20];
            double y_ekseni[]  = new double[20];
            double Tmin= cp_c[4];
            double Tmax= cp_c[5];
            double T;
            double cp; // Özgül ısı, Birimi kJ/(kmolK)

            for(int i=0;i<20;i++){
                T = Tmin+(Tmax-Tmin)/19*i;
                x_ekseni[i] = T;
                cp = liquids.cp(name,T);
                //System.out.println("cp="+cp);
                y_ekseni[i] = cp;
            }
            x.add(x_ekseni);
            y.add(y_ekseni);


            Object [] object =new Object[2];
            object[0] = x;
            object[1] = y;

            return object;
        }

        public Object[] ro_values(String name) {
            liquid_values values = new liquid_values();
            liquids liquids = new liquids();
            double ro_c[]=values.getro(name);
            ArrayList<double[]> x = new ArrayList<double[]>();
            ArrayList<double[]> y = new ArrayList<double[]>();
            double x_ekseni[]  = new double[20];
            double y_ekseni[]  = new double[20];
            double Tmin= ro_c[4];
            double Tmax= ro_c[5];
            double T;
            double ro; // Yoğunluk, Birimi kg/m^3

            for(int i=0;i<20;i++){
                T = Tmin+(Tmax-Tmin)/19*i;
                x_ekseni[i] = T;
                ro = liquids.ro(name,T);
                //System.out.println("ro="+ro);
                y_ekseni[i] = ro;
            }
            x.add(x_ekseni);
            y.add(y_ekseni);


            Object [] object =new Object[2];
            object[0] = x;
            object[1] = y;

            return object;
        }

        public Object[] k_values(String name) {
            liquid_values values = new liquid_values();
            liquids liquids = new liquids();
            double k_c[]=values.getk(name);
            ArrayList<double[]> x = new ArrayList<double[]>();
            ArrayList<double[]> y = new ArrayList<double[]>();
            ArrayList<double[]> x_Latini = new ArrayList<double[]>();
            ArrayList<double[]> y_Latini = new ArrayList<double[]>();// Latini yöntemi ile hesaplanan değerler.
            double x_ekseni[]  = new double[20];
            double y_ekseni[]  = new double[20];
            double Tmin= k_c[3];
            double Tmax= k_c[4];
            double T;
            double k; // Isıl iletkenlik katsayısı: N/m

            for(int i=0;i<20;i++){
                T = Tmin+(Tmax-Tmin)/19*i;
                x_ekseni[i] = T;
                k = liquids.k(name,T);
               //System.out.println("k="+k);
                y_ekseni[i] = k;
            }
            x.add(x_ekseni);
            y.add(y_ekseni);
/*
            for(int i=0;i<20;i++){
                T = Tmin+(Tmax-Tmin)/19*i;
                x_ekseni[i] = T;
                k = liquids.k_Latini(name,T);
                System.out.println("k="+k);
                y_ekseni[i] = k;
            }
            x.add(x_ekseni);
            y.add(y_ekseni);
*/

            Object [] object =new Object[2];
            object[0] = x;
            object[1] = y;

            return object;
        }
        public Object[] surten_values(String name) {
            liquid_values values = new liquid_values();
            liquids liquids = new liquids();
            double surten_c[]=values.getsurtension(name);
            ArrayList<double[]> x = new ArrayList<double[]>();
            ArrayList<double[]> y = new ArrayList<double[]>();
            double x_ekseni[]  = new double[20];
            double x_ekseni2[]  = new double[20];
            double y_ekseni[]  = new double[20];
            double y_ekseni2[]  = new double[20];
            double Tmin= surten_c[3];
            double Tmax= surten_c[4];
            double T;
            double sigma; // Yüzey gerilimi Birimi: N/m

            for(int i=0;i<20;i++){
                T = Tmin+(Tmax-Tmin)/19*i;
                x_ekseni[i] = T;
                x_ekseni2[i] = T;
                sigma = liquids.sur_tension(name,T);
                //System.out.println("sigma="+sigma);
                y_ekseni[i] = sigma;
                sigma = liquids.sur_tension2(name,T);
                y_ekseni2[i] = sigma;
            }
            x.add(x_ekseni);
            y.add(y_ekseni);

            boolean isNan = false;
            /* Bazı sıvılar için sur_tension2 metodunda kullanılan kritik değerler bilinmediği için bu değerler 0 olarak kabul ediliyor.
            Bu da sigma değerin NaN sonucu vermesine neden oluyor. Bu durumda grafikte sorun çıkacağı için eğer değerler arasında NaN
            varsa onun grafiğini çizdirmeyecek şekilde ayarladım.
            */
            for(int j=0;j<y_ekseni2.length;j++){
                System.out.println(y_ekseni2[j]);
                System.out.println(Double.isNaN(y_ekseni[j]));
                if(Double.isNaN(y_ekseni2[j]) ){
                    isNan = true;
                    break;
                }
            }
            if(isNan == false){
                x.add(x_ekseni2);
                y.add(y_ekseni2);
            }


            Object [] object =new Object[2];
            object[0] = x;
            object[1] = y;

            return object;
        }

        public Object[] viscosity_values(String name) {
            liquid_values values = new liquid_values();
            liquids liquids = new liquids();
            double vis_c[]=values.getvis(name);
            ArrayList<double[]> x = new ArrayList<double[]>();
            ArrayList<double[]> y = new ArrayList<double[]>();
            double x_ekseni[]  = new double[20];
            double y_ekseni[]  = new double[20];
            double Tmin= vis_c[4];
            double Tmax= vis_c[5];
            double T;
            double vis; // Pa.s

            for(int i=0;i<20;i++){
                T = Tmin+(Tmax-Tmin)/19*i;
                x_ekseni[i] = T;
                vis = liquids.vis(name,T);
                //System.out.println("vis="+vis);
                y_ekseni[i] = vis;
            }
            x.add(x_ekseni);
            y.add(y_ekseni);


Object [] object =new Object[2];
object[0] = x;
object[1] = y;

return object;
        }


        public void myPaint(Graphics g){

            //boolean tagsVisible=true;
            JButton buton;

            int alanx=100;
            int alany=20;
            int alanwidth=200;
            int alanheight=200;
            double[] xseries,xseries2;
            double[] yseries,yseries2;
            // y ekseni için kullanıyorum
            NumberFormat numberformat1=NumberFormat.getInstance(); // Virgülden sonra kaç rakam
            numberformat1.setMaximumFractionDigits(5);
            numberformat1.setMinimumFractionDigits(5);

            // x ekseni için kullanıyorum.
            NumberFormat numberformat2=NumberFormat.getInstance(); // Virgülden sonra kaç rakam
            numberformat2.setMaximumFractionDigits(2);
            numberformat2.setMinimumFractionDigits(2);

            Graphics2D g2=(Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.white); // Grafik arka planı rengi
            g2.fillRect(alanx, alany, alanwidth, alanheight);

            double x_min = 0.0;
            double x_max = 0.0;
            double y_min = 0.0;
            double y_max = 0.0;
            double x[];
            double y[];
            Color colors[] = {Color.black,Color.red,Color.orange.darker(),Color.green.darker()};
            for ( int j1=0;j1<xlist.size();j1++){
                double xd[] = xlist.get(j1);
                double yd[] = ylist.get(j1);
                for(int k=0;k<xd.length;k++){
                    if(xd[k] < x_min){ x_min = xd[k];}
                    if(xd[k] > x_max){ x_max = xd[k];}
                    if(yd[k] < y_min){ y_min = yd[k];}
                    if(yd[k] > y_max){ y_max = yd[k];}
                }
                /*System.out.println("x_min="+x_min);
                System.out.println("x_max="+x_max);
                System.out.println("y_min="+y_min);
                System.out.println("y_max="+y_max);*/
            }
            g2.drawString("8",-(alany+alanheight)/2-140,alanx-50);


            for ( int j=0;j<xlist.size();j++)
            {
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setStroke(new BasicStroke(1.0f));
                 x = xlist.get(j);
                 y = ylist.get(j);

                //System.out.println("final y_max="+y_max);
                //System.out.println("y_max="+x_max);

                int boyut=Math.min(x.length,y.length);
                xseries=new double[boyut];
                yseries=new double[boyut];
                xseries2=new double[boyut];
                yseries2=new double[boyut];
                for(int i=0;i<boyut;i++) {
                    xseries[i]=x[i];
                    yseries[i]=y[i];
                    xseries2[i]=x[i];
                    yseries2[i]=y[i];
                }








                g2.setColor(Color.black); // Grafik eğrisinin üzerindeki veri noktalarının rengi
                Arrays.sort(xseries2); // Minimum değeri bulmak içinn sıraladım
                Arrays.sort(yseries2);


                double xrange=x_max-x_min; // En büyük değer - En küçük değer
                double yrange=y_max-y_min; // En büyük değer - En küçük değer
//                System.out.println("final y_max="+y_max);
//                System.out.println("yrange="+yrange);

                double katsayıx;
                double katsayıy;
                if(xrange == 0.0){
                    katsayıx = 0;
                    katsayıy = 0;
                }
                else {
                    katsayıx=alanwidth/xrange;
                    katsayıy=alanheight/yrange;
                }

                for(int i=0;i<xseries.length;i++) {
                    g2.fillOval((int)((xseries[i]-x_min)*katsayıx+alanx-1),(int)(alany+alanheight-(yseries[i]-y_min)*katsayıy-1), 2,2);


                }

                g2.setColor(Color.red);
                g2.setFont(new Font("arial", Font.PLAIN, 8));
                if(tagsVisible) {

                    for(int i=0;i<xseries.length;i++) {
                        g2.drawString("("+numberformat2.format(xseries[i])+";"+numberformat2.format((yseries[i]))+")"
                                ,(int)((xseries[i]-x_min)*katsayıx+alanx+2),(int)(alany+alanheight-(yseries[i]-y_min)*katsayıy));

                    }

                }

                g2.setColor(colors[j]);

                // Veri noktalarını bağlayan çizgiler ( Eğrinin kendisi yani )
                for(int i=0; i<xseries.length-1;i++) {

                    g2.drawLine((int)((xseries[i]-x_min)*katsayıx+alanx),(int)(alany+alanheight-(yseries[i]-y_min)*katsayıy),
                            (int)((xseries[i+1]-x_min)*katsayıx+alanx),(int)(alany+alanheight-(yseries[i+1]-y_min)*katsayıy));
                }
                g2.setColor(Color.black);
                g2.setStroke(new BasicStroke(1.5f));
                // x çizgisini belirginleştirmek için kullanıldı.
                g2.drawLine(alanx, (int)(alany+alanheight-(-y_min)*katsayıy),alanx+alanwidth, (int)(alany+alanheight-(-y_min)*katsayıy));
                //System.out.println("katsayıx="+katsayıx+" katsayıy="+katsayıy+" ymin="+y_min+" xmin="+x_min);
                // y çizgisini belirginleştirmek için kullanıldı.
                g2.drawLine((int)(-x_min*katsayıx+alanx),alany,(int)(-x_min*katsayıx+alanx), (alany+alanheight));

                for(int i=0;i<=5;i++) {
                    double xdegeri=(double)(xrange/5*i+x_min);
                    g2.drawString(""+(numberformat2.format(xdegeri)), alanx+(i*(alanwidth/5))-10, alany+alanheight+15);
                    g2.drawRect(alanx+(i*(alanwidth/5)), alany+alanheight-1, 1, 2);
                    g2.drawString(""+numberformat1.format((double)(yrange/5*i+y_min)), alanx-40, alany+alanheight-(i*(alanheight/5)));
                    g2.drawRect(alanx-1,alany+alanheight-(i*(alanheight/5)), 2, 1); }
            }


            g2.setFont(new Font("arial", Font.BOLD, 10));
            g2.drawString(xlabel, (alanx+alanwidth)/2+10, alany+alanheight+30);

            AffineTransform at=new AffineTransform();
            at.rotate(-Math.PI/2);
            g2.setTransform(at);
            g2.setFont(new Font("arial", Font.BOLD, 15));
            g2.drawString(ylabel,-(alany+alanheight)/2-140,alanx-50);
            at.rotate(Math.PI/2);
            g2.setTransform(at);
        }

        @Override
        public void paint(Graphics g) {
            // TODO Auto-generated method stub
            super.paint(g);
            myPaint(g);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

            if(e.getSource()==buton) {
                tagsVisible = !tagsVisible;
                //System.out.println(tagsVisible);
                if(buton.getText()=="Veri etiketlerini gizle") {
                    buton.setText("Veri etiketlerini göster");

                }

                else {
                    buton.setText("Veri etiketlerini gizle");
                }

                repaint();
            }


        }


        public static void main(String[] args) {
            ArrayList<double[]>x=new ArrayList<double[]>();
            ArrayList<double[]>y=new ArrayList<double[]>();

            JFrame frame = new JFrame();

            xy_grafik grafik=new xy_grafik("x label","Viskozite");
            xy_grafik grafik2=new xy_grafik("x label","Yoğunluk");
            grafik.setBackground(Color.green);
            grafik.setBounds(20,20,400,300);
            grafik2.setBackground(Color.pink);
            grafik2.setBounds(450,20,400,300);
            //grafik.createGraphic("CO");
            double x1[]=new double[11];
            double y1[]=new double[11];
            double x2[]=new double[11];
            double y2[]=new double[11];

            for(int i=0;i<11;i++){
                int a = i-5;
                x1[i] = a;
                x2[i] = a;
                y1[i] = a*a*a;
                y2[i] = a*a;
            }
            x.add(x1);
            x.add(x2);
            y.add(y1);
            y.add(y2);
            grafik2.repaint();
            grafik.repaint();

            grafik.setValues(x,y);
            grafik2.setValues(x,y);
            frame.add(grafik);
            frame.add(grafik2);
            frame.setSize(700,700);
            frame.setLayout(null);
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }




    }






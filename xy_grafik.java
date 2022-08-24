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
        private String xlabel="";
        private String ylabel="";
        private int alanx=125;
        private int alany=100;
        private int alanwidth=200;
        private int alanheight=200;
        double[] xseries,xseries2;
        double[] yseries,yseries2;
        //private NumberFormat numberformat=NumberFormat.getInstance(); // Virgülden sonra kaç rakam
        // gösterileceğini ayarlamam için gerekli
        public xy_grafik(String xlabel,String ylabel) {
            setLayout(null);
            this.xlabel = xlabel;
            this.ylabel = ylabel;
            this.setBounds(0,0,300,300);
            //this.setSize(300,300);
            buton=new JButton("Veri etiketlerini gizle");
            buton.setBounds(700, 300, 170, 30);
            buton.addActionListener(this);
            buton.setFocusPainted(false);
            add(buton);
            setBackground(Color.red);
/*		JFrame frame=new JFrame("XY Grafiği");
		frame.setLayout(null);
		frame.setBounds(0, 0, 900, 700);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.add(this);
		frame.setVisible(true);*/

        }

        public void setXlabel(String s) {
            xlabel=s;
        }

        public void setYlabel(String s1) {
            ylabel=s1;
        }
        public void writeText(){
            System.out.println("Deneme yapıyotum dikat!!!");
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
            Object o[];
            if(graphic_name.equalsIgnoreCase("viscosity")){
                o = viscosity_values(name);
            }
            else if(graphic_name.equalsIgnoreCase("surface tension")){
                o = surten_values(name);
            }
            else {
                o = viscosity_values(name);
            }


            ArrayList<double[]> x = new ArrayList<double[]>();
            ArrayList<double[]> y = new ArrayList<double[]>();
            x = (ArrayList<double[]>) o[0];
            y = (ArrayList<double[]>) o[1];
            System.out.println();
            setValues(x,y);
            repaint();

            for(int i=0;i<x.size();i++){
                for(int k=0;k<x.get(i).length;k++){
                    System.out.println("x değeri:"+x.get(i)[k]);
                    System.out.println("y değeri:"+y.get(i)[k]);
                }
            }

        }

        public Object[] surten_values(String name) {
            liquid_values values = new liquid_values();
            liquids liquids = new liquids();
            double surten_c[]=values.getsurtension(name);
            ArrayList<double[]> x = new ArrayList<double[]>();
            ArrayList<double[]> y = new ArrayList<double[]>();
            double x_ekseni[]  = new double[20];
            double y_ekseni[]  = new double[20];
            double Tmin= surten_c[3];
            double Tmax= surten_c[4];
            double T;
            double sigma; // Yüzey gerilimi Birimi: N/m

            for(int i=0;i<20;i++){
                T = Tmin+(Tmax-Tmin)/19*i;
                x_ekseni[i] = T;
                sigma = liquids.sur_tension(name,T);
                System.out.println("sigma="+sigma);
                y_ekseni[i] = sigma;
            }
            x.add(x_ekseni);
            y.add(y_ekseni);


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

            int alanx=70;
            int alany=10;
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

            double x_min = 0;
            double x_max = 0;
            double y_min = 0;
            double y_max = 0;

            for ( int j=0;j<xlist.size();j++)
            {
                double x[] = xlist.get(j);
                double y[] = ylist.get(j);
                for(int k=0;k<x.length;k++){
                    if(x[k] < x_min){ x_min = x[k];}
                    if(x[k] > x_max){ x_max = x[k];}
                    if(y[k] < y_min){ y_min = y[k];}
                    if(y[k] > y_max){ y_max = y[k];}
                }

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
                double katsayıx=alanwidth/xrange;
                double katsayıy=alanheight/yrange;

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

                g2.setColor(Color.black);

                // Veri noktalarını bağlayan çizgiler ( Eğrinin kendisi yani )
                for(int i=0; i<xseries.length-1;i++) {
                    g2.drawLine((int)((xseries[i]-x_min)*katsayıx+alanx),(int)(alany+alanheight-(yseries[i]-y_min)*katsayıy),
                            (int)((xseries[i+1]-x_min)*katsayıx+alanx),(int)(alany+alanheight-(yseries[i+1]-y_min)*katsayıy));
                }
                g2.setStroke(new BasicStroke(2.0f));
                // x çizgisini belirginleştirmek için kullanıldı.
                g2.drawLine(alanx, (int)(alany+alanheight-(-y_min)*katsayıy),alanx+alanwidth, (int)(alany+alanheight-(-y_min)*katsayıy));
                // y çizgisini belirginleştirmek için kullanıldı.
                g2.drawLine((int)(-x_min*katsayıx+alanx),alany,(int)(-x_min*katsayıx+alanx), (alany+alanheight));

                for(int i=0;i<=5;i++) {
                    double xdegeri=(double)(xrange/5*i+x_min);

                    g2.drawString(""+(numberformat2.format(xdegeri)), alanx+(i*(alanwidth/5))-10, alany+alanheight+15);
                    g2.drawRect(alanx+(i*(alanwidth/5)), alany+alanheight-1, 1, 2);
                    g2.drawString(""+numberformat1.format((double)(yrange/5*i+y_min)), alanx-40, alany+alanheight-(i*(alanheight/5)));
                    g2.drawRect(alanx-1,alany+alanheight-(i*(alanheight/5)), 2, 1);
                }
            }

            g2.setFont(new Font("arial", Font.BOLD, 10));
            g2.drawString(xlabel, (alanx+alanwidth)/2+45, alany+alanheight+30);

            AffineTransform at=new AffineTransform();
            at.rotate(-Math.PI/2);
            g2.setTransform(at);
            g2.setFont(new Font("arial", Font.BOLD, 15));
            g2.drawString(ylabel,-(alany+alanheight)/2-150,alanx-40);


        }

        @Override
        public void paint(Graphics g) {
            // TODO Auto-generated method stub
            super.paint(g);
            ArrayList<Double>x=new ArrayList<Double>();
            ArrayList<Double>y=new ArrayList<Double>();
            ArrayList<Double>y2=new ArrayList<Double>();




            for(double i=0;i<5;i+=0.2) {
                x.add(i);
                y.add(Math.pow(Math.E,i));
                y2.add(10*i+25);
            }
            double xseries[]=new double[x.size()];
            double yseries[]=new double[x.size()];
            double yseries2[]=new double[x.size()];
            for(int i=0;i<x.size();i++) {
                xseries[i]=x.get(i);
                yseries[i]=y.get(i);
                yseries2[i]=y2.get(i);
            }

            myPaint(g);

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

            if(e.getSource()==buton) {
                tagsVisible = !tagsVisible;
                System.out.println(tagsVisible);
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
            ArrayList<Double>x=new ArrayList<Double>();
            ArrayList<Double>y=new ArrayList<Double>();

            JFrame frame = new JFrame();

            xy_grafik grafik=new xy_grafik("x label","y label");
            grafik.setBackground(Color.green);
            grafik.setBounds(20,20,300,300);
            //grafik.createGraphic("CO");

            frame.add(grafik);
            grafik.repaint();
            frame.setSize(700,700);
            frame.setLayout(null);
            frame.setVisible(true);
            frame.add(grafik);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }




    }






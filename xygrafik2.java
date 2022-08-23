package grafikcizimkutuphanesi;

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


// xygrafik üzerinde deðiþiklikler yapmaya çalýþýrken bozarým falan diye xygrafik2.java dosyasýna kopyaladým.
public class xygrafik2 extends JPanel implements ActionListener {

    private double xmin,ymin;
    private int hamle=0;
    private JButton buton;
    private String xlabel="";
    private String ylabel="";
    private int alanx=100;
    private int alany=200;
    private int alanwidth=550;
    private int alanheight=450;
    double[] xseries,xseries2;
    double[] yseries,yseries2;
    private NumberFormat numberformat=NumberFormat.getInstance();
    public xygrafik2() {
        setLayout(null);
        buton=new JButton("Veri etiketlerini gizle");
        buton.setBounds(700, 300, 170, 30);
        buton.addActionListener(this);
        buton.setFocusPainted(false);
        add(buton);
        numberformat.setMaximumFractionDigits(2);
        setBackground(Color.gray.brighter());
        JFrame frame=new JFrame("XY Grafiði");
        frame.setBounds(0, 0, 900, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(this);
        frame.setVisible(true);

    }

    public void setXlabel(String s) {
        xlabel=s;
    }

    public void setYlabel(String s1) {
        ylabel=s1;
    }

    public void degergir(double x[],double y[]) {

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


    }


    public void degergir(Double x[],Double y[]) {

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
//       Eðer degergir(Double x[],Double y[]) metodunu kullanmak istiyorsam  x[] ve y[] dizisine aþaðýdaki gibi
//		bir iþlem uygulayabilirim.
//		ArrayList<Double> xserisi=new ArrayList<>();
//		ArrayList<Double> yserisi=new ArrayList<>();
//		for(int i=0;i<361;i=i+10) {
//			xserisi.add((double) i);
//			yserisi.add(Math.sin(i*Math.PI/180));
//
//		}
//
//		Double[] x=xserisi.toArray(new Double[0]);
//		Double[] y=yserisi.toArray(new Double[0]);
//
    }

    @Override
    public void paint(Graphics g) {
        // TODO Auto-generated method stub
        super.paint(g);
        Graphics2D g2=(Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.white);
        g2.fillRect(alanx, alany, alanwidth, alanheight);
        g2.setColor(Color.black);
        Arrays.sort(xseries2);
        Arrays.sort(yseries2);

        if(xseries2[0]<0) {
            xmin=xseries2[0];
        }
        else{
            xmin=0;
        }

        if(yseries2[0]<0) {
            ymin=yseries2[0];
        }
        else{
            ymin=0;
        }
        double xrange=xseries2[xseries.length-1]-xmin;
        double yrange=yseries2[yseries.length-1]-ymin;
        double katsayýx=alanwidth/xrange;
        double katsayýy=alanheight/yrange;

        for(int i=0;i<xseries.length;i++) {
            g2.fillOval((int)((xseries[i]-xmin)*katsayýx+alanx-2),(int)(alany+alanheight-(yseries[i]-ymin)*katsayýy-2), 4,4);


        }

        g2.setColor(Color.red);
        if(hamle%2==0) {

            for(int i=0;i<xseries.length;i++) {
                g2.drawString("("+numberformat.format(xseries[i])+";"+numberformat.format((yseries[i]))+")"
                        ,(int)((xseries[i]-xmin)*katsayýx+alanx+2),(int)(alany+alanheight-(yseries[i]-ymin)*katsayýy-2));

            }

        }

        g2.setColor(Color.black);

        for(int i=0; i<xseries.length-1;i++) {
            g2.drawLine((int)((xseries[i]-xmin)*katsayýx+alanx),(int)(alany+alanheight-(yseries[i]-ymin)*katsayýy),
                    (int)((xseries[i+1]-xmin)*katsayýx+alanx),(int)(alany+alanheight-(yseries[i+1]-ymin)*katsayýy));
        }
        g2.setStroke(new BasicStroke(2.0f));
        g2.drawLine(alanx, (int)(alany+alanheight-(-ymin)*katsayýy),alanx+alanwidth, (int)(alany+alanheight-(-ymin)*katsayýy));
        g2.drawLine((int)(-xmin*katsayýx+alanx),alany,(int)(-xmin*katsayýx+alanx), (alany+alanheight));

        for(int i=0;i<=10;i++) {
            double xdegeri=(double)(xrange/10*i+xmin);

            g2.drawString(""+(numberformat.format(xdegeri)), alanx+(i*(alanwidth/10)), alany+alanheight+15);
            g2.drawRect(alanx+(i*(alanwidth/10)), alany+alanheight-1, 1, 2);
            g2.drawString(""+numberformat.format((double)(yrange/10*i+ymin)), alanx-40, alany+alanheight-(i*(alanheight/10)));
            g2.drawRect(alanx-1,alany+alanheight-(i*(alanheight/10)), 2, 1);
        }
        g2.setFont(new Font("arial", Font.BOLD, 30));
        g2.drawString(xlabel, (alanx+alanwidth-30)/2, alany+alanheight+55);

        AffineTransform at=new AffineTransform();
        at.rotate(-Math.PI/2);
        g2.setTransform(at);
        g2.setFont(new Font("arial", Font.BOLD, 30));
        g2.drawString(ylabel,-(alanx+alanwidth-140),alany-20);





    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub

        if(e.getSource()==buton) {
            hamle++;
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

        for(double i=0;i<5;i+=0.05) {
            x.add(i);
            y.add(Math.pow(Math.E,i));
        }
        double xseries[]=new double[x.size()];
        double yseries[]=new double[x.size()];
        for(int i=0;i<x.size();i++) {
            xseries[i]=x.get(i);
            yseries[i]=y.get(i);
        }

        xygrafik grafik=new xygrafik();
        grafik.degergir(xseries, yseries);
        grafik.setXlabel("x");
        grafik.setYlabel("e^x");
    }




}


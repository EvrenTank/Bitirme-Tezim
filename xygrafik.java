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

public class xygrafik extends JPanel implements ActionListener {

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
	private NumberFormat numberformat=NumberFormat.getInstance(); // Virgülden sonra kaç rakam
	// gösterileceðini ayarlamam için gerekli
	public xygrafik() {
		setLayout(null);
		this.setBounds(0,0,300,300);
		this.setSize(300,300);
		buton=new JButton("Veri etiketlerini gizle");
		buton.setBounds(700, 300, 170, 30);
		buton.addActionListener(this);
		buton.setFocusPainted(false);
		add(buton);
		numberformat.setMaximumFractionDigits(2);
		setBackground(Color.red);
/*		JFrame frame=new JFrame("XY Grafiði");
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
		System.out.println("Deneme yapýyotum dikat!!!");
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

	public void degergir(ArrayList<double[]> xlist,ArrayList<double[]> ylist) {

this.xlist= xlist;
this.ylist= ylist;


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

	}
	public void myPaint(Graphics g,ArrayList <double[]> xi, ArrayList <double[]> yi){

		 //boolean tagsVisible=true;
		 JButton buton;

		 int alanx=70;
		 int alany=10;
		 int alanwidth=200;
		 int alanheight=200;
		double[] xseries,xseries2;
		double[] yseries,yseries2;
		 NumberFormat numberformat=NumberFormat.getInstance(); // Virgülden sonra kaç rakam


		Graphics2D g2=(Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.white); // Grafik arka planý rengi
		g2.fillRect(alanx, alany, alanwidth, alanheight);

double x_min = 0;
double x_max = 0;
double y_min = 0;
double y_max = 0;
for ( int j=0;j<xi.size();j++)
{
	double x[] = xi.get(j);
	double y[] = yi.get(j);
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








	g2.setColor(Color.black); // Grafik eðrisinin üzerindeki veri noktalarýnýn rengi
	Arrays.sort(xseries2); // Minimum deðeri bulmak içinn sýraladým
	Arrays.sort(yseries2);


	double xrange=x_max-x_min; // En büyük deðer - En küçük deðer
	double yrange=y_max-y_min; // En büyük deðer - En küçük deðer
	double katsayýx=alanwidth/xrange;
	double katsayýy=alanheight/yrange;

	for(int i=0;i<xseries.length;i++) {
		g2.fillOval((int)((xseries[i]-x_min)*katsayýx+alanx-1),(int)(alany+alanheight-(yseries[i]-y_min)*katsayýy-1), 2,2);


	}

	g2.setColor(Color.red);
	g2.setFont(new Font("arial", Font.PLAIN, 8));
	if(tagsVisible) {

		for(int i=0;i<xseries.length;i++) {
			g2.drawString("("+numberformat.format(xseries[i])+";"+numberformat.format((yseries[i]))+")"
					,(int)((xseries[i]-x_min)*katsayýx+alanx+2),(int)(alany+alanheight-(yseries[i]-y_min)*katsayýy));

		}

	}

	g2.setColor(Color.black);

	// Veri noktalarýný baðlayan çizgiler ( Eðrinin kendisi yani )
	for(int i=0; i<xseries.length-1;i++) {
		g2.drawLine((int)((xseries[i]-x_min)*katsayýx+alanx),(int)(alany+alanheight-(yseries[i]-y_min)*katsayýy),
				(int)((xseries[i+1]-x_min)*katsayýx+alanx),(int)(alany+alanheight-(yseries[i+1]-y_min)*katsayýy));
	}
	g2.setStroke(new BasicStroke(2.0f));
	// x çizgisini belirginleþtirmek için kullanýldý.
	g2.drawLine(alanx, (int)(alany+alanheight-(-y_min)*katsayýy),alanx+alanwidth, (int)(alany+alanheight-(-y_min)*katsayýy));
	// y çizgisini belirginleþtirmek için kullanýldý.
	g2.drawLine((int)(-x_min*katsayýx+alanx),alany,(int)(-x_min*katsayýx+alanx), (alany+alanheight));

	for(int i=0;i<=10;i++) {
		double xdegeri=(double)(xrange/10*i+x_min);

		g2.drawString(""+(numberformat.format(xdegeri)), alanx+(i*(alanwidth/10)), alany+alanheight+15);
		g2.drawRect(alanx+(i*(alanwidth/10)), alany+alanheight-1, 1, 2);
		g2.drawString(""+numberformat.format((double)(yrange/10*i+y_min)), alanx-40, alany+alanheight-(i*(alanheight/10)));
		g2.drawRect(alanx-1,alany+alanheight-(i*(alanheight/10)), 2, 1);
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
		xlist.add(xseries);
		xlist.add(xseries);
		ylist.add(yseries);
		ylist.add(yseries2);
		myPaint(g,xlist,ylist);

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

	for(double i=0;i<5;i+=0.2) {
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
	grafik.setXlabel("Sýcaklýk( K)");
	grafik.setYlabel("Viskozite(Pa.s)");
	}
	
	
	

}


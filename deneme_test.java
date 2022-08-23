package bitirme_tezi;

import javax.swing.*;

public class  deneme_test extends deneme{
    int a;

    public deneme_test(int i, int j, int k,int m) {
        super(i, j, k);
        a=m;
    }

    @Override
    public String func1() {
        return super.func1();
    }

    @Override
    public String func2() {
        return super.func2();
    }

    @Override
    public String func3() {
        return super.func3();
    }

    @Override
    public String func4() {
        return super.func4();
    }

    public static void main(String[] args) {
        deneme e1=new deneme(2,3,4);
        deneme_test e2=new deneme_test(1,5,8,10);
//        String s="e1 için hesaplanan değerler\n"+"f1="+e1.func1()+" f2="+e1.func2()+" f3="+e1.func3()+" f4="+e1.func4();
//        s+="\ne2 için hesaplanan değerler\n"+"f1="+e2.func1()+" f2="+e2.func2()+" f3="+e2.func3()+" f4="+e2.func4();
//        JOptionPane.showMessageDialog(null,s);

        deneme k[]=new deneme[2];
        k[0]=e1;
        k[1]=e2;
        for(int i=0;i<k.length;i++){
           String s= k[i].func1()+" "+k[i].func2()+" "+k[i].func3()+" "+k[i].func4();
            System.out.println(s);

        }
    }
}

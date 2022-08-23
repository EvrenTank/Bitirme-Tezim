package bitirme_tezi;

class abstract_class1 extends abstract_deneme{
    @Override
    public double func1(double x) {
        return x*x-3*x+2;
    }
}
class abstract_class2 extends abstract_deneme{
    @Override
    public double func1(double x) {
        return x*x*x-2;
    }
}

public class abstract_test {



    public static void main(String[] args) {
        abstract_class1 e1= new abstract_class1();
        abstract_class2 e2= new abstract_class2();

//        System.out.println("e1 için:"+e1.func1(5.0));
//        System.out.println("e2 için:"+e2.func1(5.0));
        abstract_deneme elemanlar[]=new abstract_deneme[2];
        elemanlar[0]=e1;
        elemanlar[1]=e2;

        for (int i=0;i<elemanlar.length;i++){
            System.out.println(elemanlar[i].func1(5.0));
        }

          }
}

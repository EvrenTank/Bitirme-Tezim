package bitirme_tezi;

public  class interface_test implements interface1,interface2{
    @Override
    public double func1(double x) {
        return 2*x-5;
    }

    @Override
    public double func1(int x) {
        return x*x-5*x+6;
    }

    public static void main(String[] args) {
        interface_test e1=new interface_test();
        System.out.println("func1(double x)(3.0)="+e1.func1(3.0));
        System.out.println("func1(int x)(3)="+e1.func1(3));
    }
}

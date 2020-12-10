import java.util.Scanner;

public class checkNum {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入变量：");
        int x = scanner.nextInt();
        //定义计时器
        double a= System.currentTimeMillis();
        System.out.println("原方法耗时：（计算过程消耗时间较长，可注释直接测线程代码）");
        for (long i = 1; i < 1000000000; i++) {
            if (contain(i, x)) ans += i;
        }

        System.out.println((System.currentTimeMillis()-a)+"ms");
        System.out.println("原方法结果："+ans);

        //重置变量
        ans=0;
        //重置计时器
        a=System.currentTimeMillis();
        System.out.println("4线程方法耗时：");
        //4线程
        MyThread myThread=new MyThread(0,250000000,x,0);
        myThread.start();
        MyThread myThread1=new MyThread(250000001,500000000,x,1);
        myThread1.start();
        MyThread myThread2=new MyThread(500000001,750000000,x,2);
        myThread2.start();
        MyThread myThread3=new MyThread(750000001,1000000000,x,3);
        myThread3.start();

        //阻塞代码，防止直接打印方法结果，无法直观对比改进前后代码效率
        boolean flag=false;
        boolean flag1=false;
        boolean flag2=false;
        boolean flag3=false;
        System.out.println("不同java版本可能造成该循环阻塞，java14SE没问题，但是9有问题");
        while(!(flag&&flag1&&flag2&&flag3)){
            flag=myThread.isInterrupted();
            flag1=myThread1.isInterrupted();
            flag2=myThread2.isInterrupted();
            flag3=myThread3.isInterrupted();
        }


        System.out.println((System.currentTimeMillis()-a)+"ms");
        //4个结果相加即为最终结果
        System.out.println("线程方法结果："+(ans+ans2+ans3+ans4));

    }

    private static boolean contain(long num, int x) {
        return String.valueOf(num).contains(String.valueOf(x));
    }

    //4个线程对应的4个结果
    static long ans;
    static long ans2;
    static long ans3;
    static long ans4;

    static class MyThread extends Thread{
        private int start;
        private int end;
        private int x;
        private int check;//check值控制线程运算结果赋值个哪个变量
        @Override
        public void run() {
            long res=0;
            for (int i = start; i <=end; i++) {
                if (contain(i, x)) {res += i;}
            }

            //结果赋值
            if(check==0)ans=res;
            else if(check==1)ans2=res;
            else if(check==2)ans3=res;
            else ans4=res;
            this.interrupt();
        }

        //构造方法
        public MyThread(int start, int end, int x,int check) {
            this.start = start;
            this.end = end;
            this.x = x;
            this.check=check;
        }

    }

}

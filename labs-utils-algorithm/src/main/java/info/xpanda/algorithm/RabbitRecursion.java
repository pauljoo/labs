package info.xpanda.algorithm;

public class RabbitRecursion {

    /**
     * 1对兔子，3个月后开始每个月都可以生1对兔子，N个月有多少对兔子
     * 1,1,1,2,3
     */
    public static int simple(int n){
        if(n == 1 || n == 2 || n == 3){
            return 1;
        }

        // 总数 = （n - 1）的兔子总数 + (n - 3) 可以生的兔子总数
        return simple(n - 1) + simple(n - 3);
    }

    /**
     * 1对兔子，3个月后开始每隔1个月都可以生1对兔子，N个月有多少对兔子
     * 1,1,1,2,2,3,4
     */
    public static int simple2(int n){
        if(n == 1 || n == 2 || n == 3){
            return 1;
        }

        // 总数 = （n - 1）的兔子总数 + [n-3出生的兔子 + n-5出生的兔子 + n-7出生的兔子]
        int sum = 0;
        for(int i = 0; i < n - 3; i += 2){
            sum += simple2Born(n - 3 - i);
        }

        int monthSum = simple2(n - 1) + sum;
        System.out.println(n + "=" + monthSum);
        return monthSum;
    }

    // 第n月出生的兔子 = n-3出生的兔子 + n-5出生的兔子 + n-7出生的兔子
    // n == 1 有新出生的兔子
    // n == 2 or n ==3 没有新出生的兔子
    public static int simple2Born(int n){
        if(n == 1){
            return 1;
        }
        if(n == 2 || n == 3){
            return 0;
        }
        int sum = 0;
        for(int i = 0; i < n - 3; i += 2){
            sum += simple2Born(n - 3 - i);
        }
        return sum;
    }
}

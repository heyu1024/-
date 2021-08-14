package com.cxw.datesource.recursion;

/**
 * @author shkstart
 * @date 2020-12-17 17:27
 */
public class Queue8 {
    //定义一个max表示共有多少个皇后
    int max=8;
    //定义数组array，保存皇后放置位置的结果，比如arr={0，4，7，5，2，6，1，3}
    /**
     * 第1行第1列
     * 第2行第5列
     * 第3行第8列。。。
     */
    int[] array=new int[max];
    static int count=0;

    public static void main(String[] args) {
        //测试一把，8皇后是否正确
        Queue8 queue8 = new Queue8();
        queue8.check(0);
        System.out.println("一共有多少种解法："+count);

    }


    //编写一个方法，放置第number个皇后
    //特别之一：check是每一次递归时，进入到check中都有for (int i = 0; i < max; i++),因此会有回溯
    private void check(int number){
        if(number==max){
            //number=8,其实8个皇后已经放好了
            print();
            return;
        }

        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个第number个皇后，放到该行的第1列
            array[number]=i;
            //判断当放置第number个皇后到i列时，是否冲突
            if(judge(number)){//不冲突
                //接着放number+1个皇后，即开始递归
                check(number+1);
            }


            //如果冲突，就继续执行array[number]=i;即将第number个皇后，放置在本行的后移的一个位置
        }
    }

    //查看当我们放置第number个皇后时，就去检测该皇后是否和前面已经摆放的皇后冲突
    //number为0时，表示第1个
    private boolean judge(int number){

        for (int i = 0; i < number; i++) {
            //1.array[i]==array[number] 表示判断 第number个皇后是否和前面的number-1皇后在同一列
            //2.Math.abs(number-i) == Math.abs(array[number]-array[i]) 表示判断 第number个皇后是否和前面的number-1皇后在同一斜线
            //3.没有必要判断咋同一行，number每次都在递增
            if(array[i]==array[number] || Math.abs(number-i) == Math.abs(array[number]-array[i])){
                return false;
            }
        }
        return true;
    }

    //写一个方法，可以将皇后摆放的位置打印输出
    private void print(){
        count++;

        for (int i = 0; i <array.length ; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }


}

package com.cxw.datesource.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author shkstart
 * @date 2020-12-24 14:27
 */
public class ShellSort {
    public static void main(String[] args) {
//        int[] arr={8,9,1,7,2,3,5,4,6,0};
////        int[] arr={5,6,8,4};
//
//
//        shellSort2(arr);


        //创建一个8w个随机数据的数组
        int[] arr=new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            arr[i]=(int)(Math.random()*80000000);//生成一个【0，800000）数
        }

        Date date=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStart = simpleDateFormat.format(date);
        System.out.println("排序前的时间："+dateStart);

//        System.out.println("排序之前");
//        System.out.println(Arrays.toString(arr));

        shellSort2(arr);

        Date date2=new Date();
        String dateEnd = simpleDateFormat.format(date2);
        System.out.println("排序后的时间："+dateEnd);
    }

    //使用逐步推导的方式
    public static void shellSort(int[] arr){
        int temp=0;
        int num=0;

        for(int gap=arr.length/2;gap>0;gap/=2){

            //希尔排序的交换法
            //第一轮
            //因为第一轮排序，是将10个数据分成了5组
            for (int i = gap; i <arr.length ; i++) {
                //遍历各组中所有的元素（共gap组，每组有个元素），步长是gap
                for (int  j = i-gap;  j >=0 ;  j-=gap) {
                    //如果当前元素大于加上步长后的那个元素，说明交换
                    if(arr[j]>arr[j+gap]){
                        temp=arr[j];
                        arr[j]=arr[j+gap];
                        arr[j+gap]=temp;
                    }
                }
            }
            num++;
            System.out.println("shell排序第"+num+"轮后");
            System.out.println(Arrays.toString(arr));

        }

    }


    //对交换式的希尔排序进行优化 - > 移位法
    public static void shellSort2(int[] arr){
//        int num=0;
        for(int gap=arr.length/2;gap>0;gap/=2){
            //从第gap个元素开始，逐个对其所在的组进行直接插入排序
            for (int i = gap; i <arr.length ; i++) {
                int j=i;
                int temp=arr[j];
                if(arr[j]<arr[j-gap]){
                    while(j-gap>=0 && temp<arr[j-gap]){
                        //移动
                        arr[j]=arr[j-gap];
                        j=j-gap;
                    }
                    //当退出while循环后，就给temp找到插入的位置
                    arr[j]=temp;
                }
            }
//            num++;
//            System.out.println("shell排序第"+num+"轮后");
//            System.out.println(Arrays.toString(arr));
        }
        /**
         * shell排序第1轮后
         * [3, 5, 1, 6, 0, 8, 9, 4, 7, 2]
         * shell排序第2轮后
         * [0, 2, 1, 4, 3, 5, 7, 6, 9, 8]
         * shell排序第3轮后
         * [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
         *
         *
         * shell排序第1轮后
         * [3, 5, 1, 6, 0, 8, 9, 4, 7, 2]
         * shell排序第2轮后
         * [0, 2, 1, 4, 3, 5, 7, 6, 9, 8]
         * shell排序第3轮后
         * [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
         *
         *
         *
                以数组int[] arr = {8,9,1,7,2,3,5,4,6,0};为例，
         shell排序第1轮后
         [3, 5, 1, 6, 0, 8, 9, 4, 7, 2]
         *    两种方法的第一次分组操作基本相同，移位法在执行第二次分组操作时，此时间隔为5/2=2，
         *    第一轮，元素下标从2也就是第三个元素数字1开始，比较前面间隔为2的元素数字3，发现3比1大，因此发生直接移位；
         *    第二轮5<6所以不发生位移；第三轮，3>0,3和0移位，即此时数组的数据是[1, 5, 0, 6, 3, 8, 9, 4, 7, 2]，
         *    根据循环再次和0前面的1比较1>0,所以1和0移位，此时[0, 5, 1, 6, 3, 8, 9, 4, 7, 2]，
         *    由此可见，中间借助temp移动了两次，这个过程在数据量大的情况下非常消耗时间。
         *    对于交换法来说，同样是在这个位置，它的处理方法就比移位法灵活很多，
         *    首先它定义int temp = arr[j](此时j的值为4);即将此时结果存到temp中，
         *    然后直接与前面进行比较：3>0满足条件，将原本0的位置上的数字改成3，
         *    即此时数据是[1, 5, 3, 6, 3, 8, 9, 4, 7, 2],**继续循环，发现1和0比(注意此时循环判断条件不是判断3和1，
         *    而是判断temp和1，此时temp的值依然是0)，1>0满足条件，
         *    因此将原本是3的位置上元素改成1，即[1, 5, 1, 6, 3, 8, 9, 4, 7, 2]，
         *    回到循环，由于循环条件不满足，直接执行下一步，
         *    把temp的值赋给arr[j](此时j已经为0)**，此时[0, 5, 1, 6, 3, 8, 9, 4, 7, 2]。
         *    这个过程的元素temp只移动了一次。后面的过程与前面类似我不在赘述。
         *    总结：算法的魅力在于能把复杂的问题变得简单，而在程序中，虽然基本思想都是一样的，但是如果在算法中不合理去运用技巧，也可能“弄巧成拙”，这个例子就能说明了这一点。
         *    如果有什么错误的地方，还请各位批评指正，笔者在此谢谢大家的阅读。
         */

    }
}

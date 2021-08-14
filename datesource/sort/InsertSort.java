package com.cxw.datesource.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author shkstart
 * @date 2020-12-22 17:38
 */
public class InsertSort {
    public static void main(String[] args) {
//        int[] arr={101,34,119,1};
//        insertSort(arr);

        //创建一个8w个随机数据的数组
        int[] arr=new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i]=(int)(Math.random()*800000);//生成一个【0，800000）数
        }

        Date date=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStart = simpleDateFormat.format(date);
        System.out.println("排序前的时间："+dateStart);

//        System.out.println("排序之前");
//        System.out.println(Arrays.toString(arr));

        insertSort(arr);

        Date date2=new Date();
        String dateEnd = simpleDateFormat.format(date2);
        System.out.println("排序后的时间："+dateEnd);
    }

    //插入排序
    public static void insertSort(int[] arr){
        int insertVal=0;
        int insertIndex=0;

        //使用for循环代码简化
        for (int i = 1; i < arr.length; i++) {
            //第一轮
            //101,34,119,1 -> 34，101，119，1

            //定义待插入的数
            insertVal=arr[i];
            insertIndex=i-1;//即arr【1】的前面这个数的下标
            //给insertVal找到插入的位置
            while(insertIndex>=0 && insertVal<arr[insertIndex]){
                //insertIndex>=0保证在给insertVal找插入位置时，不越界
                //insertVal<arr[insertIndex]说明待插入的数，还没有找到插入位置
                //就需要将arr[insertIndex]后移
                arr[insertIndex+1]=arr[insertIndex];
                insertIndex--;
            }
            //当退出while循环时，说明插入的位置找到，insertIndex+1
            //这里我们判断是否需要赋值
            if((insertIndex+1)!=i){
                arr[insertIndex+1]=insertVal;
            }
//
//            System.out.println("第"+i+"轮插入后");
//            System.out.println(Arrays.toString(arr));
        }


    }
}

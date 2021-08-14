package com.cxw.datesource.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author shkstart
 * @date 2020-12-22 17:04
 */
public class SelectSort {
    public static void main(String[] args) {
//        int[] arr={101,34,119,1};
//        selectSort(arr);
//
//        System.out.println("排序后");
//        System.out.println(Arrays.toString(arr));


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

        selectSort(arr);

        Date date2=new Date();
        String dateEnd = simpleDateFormat.format(date2);
        System.out.println("排序后的时间："+dateEnd);
    }

    //选择排序
    //时间复杂度仍然是O(n^2)
    public static void selectSort(int[] arr){

        int minIndex=0;
        int min=0;
        for (int i = 0; i < arr.length-1; i++) {
            //第一轮排序
            //101，34，119，1 -> 1，34，119，101
            minIndex=i;
            min=arr[i];

            for(int j=i+1;j<arr.length;j++){
                if(min>arr[j]){
                    //说明假定的最小值，并不是最小
                    min=arr[j];//重置min
                    minIndex=j;//重置minIndex
                }
            }

            //将最小值，放在arr[0],即交换
            if(minIndex!=i){
                arr[minIndex]=arr[i];
                arr[i]=min;
            }

//            System.out.println("第"+(i+1)+"轮后");
//            System.out.println(Arrays.toString(arr));

        }


    }
}

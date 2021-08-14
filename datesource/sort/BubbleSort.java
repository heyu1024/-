package com.cxw.datesource.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author shkstart
 * @date 2020-12-21 17:41
 */
public class BubbleSort {
    public static void main(String[] args) {
//        int arr[]={3,9,-1,10,20};

        //测试一下冒泡排序速度 O（n^2）,给8w个数据模拟一下
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

        bubbleSort(arr);

        Date date2=new Date();
        String dateEnd = simpleDateFormat.format(date2);
        System.out.println("排序后的时间："+dateEnd);



//        System.out.println("排序之后");
//        System.out.println(Arrays.toString(arr));


    }

    public static void bubbleSort(int[] arr){
        int temp=0;//临时变量
        boolean flag=false;//标识变量，表示是否进行过交换

        for (int i = 0; i < arr.length-1; i++) {

            for (int j = 0; j < arr.length-1-i; j++) {
                //如果前面的数比后面的数大，则交换
                if(arr[j]>arr[j+1]){
                    flag=true;
                    temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }

//            System.out.println("第"+(i+1)+"趟排序");
//            System.out.println(Arrays.toString(arr));

            if(!flag){
                //在一趟排序中，一次交换都没有发生过
                break;
            }else {
                flag=false;//重置flag，进行下次判断
            }

        }
    }
}

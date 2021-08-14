package com.cxw.datesource.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author shkstart
 * @date 2020-12-24 22:14
 */
public class QuickSort {
    public static void main(String[] args) {
//        int[] arr={-9,78,0,23,-567,70};

//        int[] arr={1,2,5,7,10,23,31,4,4,3,3,3,3,6,5,7,8,1};
        int[] arr={29, 5, 78, 72, 39, 10, 83, 86, 93, 45};

//        int[] arr={2,2,2,2,2,2,2,2,1,1,1,1,1,1,1,1,1,1,1};

        quickSort(arr,0,arr.length-1);

        System.out.println("快排:"+ Arrays.toString(arr));



//        //创建一个8w个随机数据的数组
//        int[] arr=new int[10];
//        for (int i = 0; i < 10; i++) {
//            arr[i]=(int)(Math.random()*100);//生成一个【0，800000）数
//        }
//
//        System.out.println("arr:"+Arrays.toString(arr));
//
//        Date date=new Date();
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateStart = simpleDateFormat.format(date);
//        System.out.println("排序前的时间："+dateStart);
//
////        System.out.println("排序之前");
////        System.out.println(Arrays.toString(arr));
//
//        quickSort(arr,0,arr.length-1);
//
//        Date date2=new Date();
//        String dateEnd = simpleDateFormat.format(date2);
//        System.out.println("排序后的时间："+dateEnd);
    }

    public static void quickSort(int[] arr,int left,int right){
        int l=left;//左下标
        int r=right;//右下标
        int pivot=arr[(left+right)/2];//中轴

        int temp=0;//交换时使用

        //while循环的目的是让比pivot值小的放到左边
        //比pivot大的值放到右边
        while (l<r){
            //在pivot的左边一直找，找到一个大于等于pivot的值，才退出
            while(arr[l]<pivot){
                l+=1;
            }
            //在pivot的右边一直找，找到一个小于等于pivot的值，才退出
            while (arr[r]>pivot){
                r-=1;
            }
            //如果l>=r说明，pivot的左右两边的值，已经按照左边全部是小于等于pivot值，右边全部是大于等于pivot值
            if(l>=r){
                break;
            }

            //交换
            temp=arr[l];
            arr[l]=arr[r];
            arr[r]=temp;

            //如果交换完后，发现这个arr[l]==pivot值相等， 需要r--
            if(arr[l]==pivot){
                r-=1;
            }
            //如果交换完后，发现这个arr[r]==pivot值相等， 需要l++
            if(arr[r]==pivot){
                l+=1;
            }
        }

        //如果 l==r ，必须l++，r--，否则出现栈溢出
        if(l==r){
            l +=1;
            r -=1;
        }
        //向左递归
        if(left <r){
            quickSort(arr,left,r);
        }
        //向右递归
        if(right>l){
            quickSort(arr,l,right);
        }
    }
}

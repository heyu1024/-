package com.cxw.datesource.search;

import java.util.Arrays;

/**
 * @author shkstart
 * @date 2021-01-04 18:07
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr=new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=i+1;
        }

        System.out.println("arr:"+ Arrays.toString(arr));

        int index = insertValueSearch(arr, 0, arr.length - 1, 1);
        System.out.println("index:"+index);
    }


    /**
     *     //编写插值查找算法
     *     说明插值查找算法也要求数组是有序的
     * @param arr 数组
     * @param left 左边索引
     * @param right 右边索引
     * @param findVal 查找值
     * @return
     */
    public static int insertValueSearch(int[] arr,int left,int right,int findVal){
        System.out.println("hello");
        if(left>right || findVal<arr[0] || findVal>arr[arr.length-1]){
            return -1;
        }

        //求出mid
        int mid=left+(right-left)*(findVal-arr[left])/(arr[right]-arr[left]);
        int midVal=arr[mid];

        if(findVal>midVal){
            //向右递归
            return insertValueSearch(arr,mid+1,right,findVal);
        }else if(findVal<midVal){
            return insertValueSearch(arr,left,mid-1,findVal);
        }else {
            return mid;
        }
    }
}

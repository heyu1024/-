package com.cxw.datesource.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author shkstart
 * @date 2020-12-29 16:59
 */
public class MergetSort {
    public static void main(String[] args) {
//        int[] arr={8,4,5,7,1,3,6,2};
//        int[] temp=new int[arr.length];//说明归并排序需要一个额外的空间
//
//        mergeSort(arr,0,arr.length-1,temp);
//
//        System.out.println("归并排序后："+ Arrays.toString(arr));


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

        int[] temp=new int[arr.length];

        mergeSort(arr,0,arr.length-1,temp);

        Date date2=new Date();
        String dateEnd = simpleDateFormat.format(date2);
        System.out.println("排序后的时间："+dateEnd);

    }

    //分+合方法
    public static void mergeSort(int[] arr,int left,int right,int[] temp){
        if(left < right){
            int mid=(left+right)/2;//中间索引
            //向左递归进行分解
            mergeSort(arr,left,mid,temp);
            //向右递归进行分解
            mergeSort(arr,mid+1,right,temp);
            //到合并
            merge(arr,left,mid,right,temp);
        }
    }



    /**
     * 合并的方法
     * @param arr 排序的原始数组
     * @param left 左边有序序列的初始索引
     * @param mid 中间索引
     * @param right 右边有序序列的初始索引
     * @param temp 做中转的数组
     */
    public static void merge(int[] arr,int left,int mid,int right,int[] temp){
        int i=left;//初始化i 左边有序序列的初始索引
        int j=mid+1;//初始化j 右边有序序列的初始索引
        int t=0;//指向temp数组的当前索引

        //（一）
        //先把左右两边（有序）的数据，按照规则填充到temp数组
        //直到左右两边的有序序列，有一边处理完毕为止
        while(i<=mid && j<=right){//继续
            if(arr[i]<=arr[j]){
                //如果左边的有序序列的当前元素，小于或等于右边有序序列的当前元素
                //即将左边的当前元素拷贝到temp数组
                //然后t和i要后移 ++
                temp[t]=arr[i];
                t +=1;
                i +=1;
            }else {
                //反之将右边的当前元素拷贝到temp数组
                temp[t]=arr[j];
                t +=1;
                j +=1;
            }

        }

        //（二）
        //把有剩余数据的一边的数据依次全部填充到temp
        while(i<=mid){//说明左边的有序序列还有剩余元素，就全部填充到temp
            temp[t]=arr[i];
            t +=1;
            i +=1;
        }

        while(j<=right){//说明右边的有序序列还有剩余元素，就全部填充到temp
            temp[t]=arr[j];
            t +=1;
            j +=1;
        }

        //（三）
        //将temp数组的元素拷贝到arr
        //注意，并不是每次都拷贝所有
        t=0;
        int tempLeft=left;

        //第一次合并 tempLeft=0，right=1//tempLeft=2，right=3//tempLeft=0，right=3
        //最后一次 tempLeft=0，right=7
//        System.out.println("tempLeft="+tempLeft+" right="+right);
        while (tempLeft<=right){
            arr[tempLeft]=temp[t];
            t +=1;
            tempLeft +=1;
        }

    }
}

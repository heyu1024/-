package com.cxw.datesource.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author shkstart
 * @date 2020-12-30 16:00
 */
public class RadixSort {
    public static void main(String[] args) {
//        int[] arr={53,3,542,748,14,214};
//
//        radixSort(arr);

        //创建一个8w个随机数据的数组
        int[] arr=new int[80000000];
        for (int i = 0; i < 80000000; i++) {
            arr[i]=(int)(Math.random()*800000000);//生成一个【0，800000）数
        }

        Date date=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStart = simpleDateFormat.format(date);
        System.out.println("排序前的时间："+dateStart);

//        System.out.println("排序之前");
//        System.out.println(Arrays.toString(arr));

        int[] temp=new int[arr.length];

        radixSort(arr);

        Date date2=new Date();
        String dateEnd = simpleDateFormat.format(date2);
        System.out.println("排序后的时间："+dateEnd);

    }

    //基数排序方法
    public static void radixSort(int[] arr){

        //1.得到数组中最大数的位数
        int max=arr[0];//假设第一个数是最大数
        for (int i = 0; i < arr.length; i++) {
            if(arr[i]>max){
                max=arr[i];
            }
        }
        //得到最大数是几位数
        int maxLength=(max +"").length();

        //定义一个二维数组表示十个桶，每个桶就是一个一维数组
        //1.二维数组包含10个一维数组
        //2.为了防止在放入数的时候，数据溢出，则每个一维数组（桶），大小定为arr.length
        //3.很明确，基数排序是使用空间换时间的经典算法
        int[][] bucket=new int[10][arr.length];

        //为了记录每个桶中，实际存放了多少个数据，我们定义一个一维数组来记录各个桶的每次放入的数据个数
        //bucketElementCounts[0]，记录的就是bucket[0]桶的放入数据的个数
        int[] bucketElementCounts=new int[10];

        //使用循环处理代码
        for (int i = 0,n=1; i < maxLength; i++,n*=10) {
            //第一轮（针对每个元素的各位进行排序处理）
            for (int j = 0; j < arr.length ; j++) {
                //取出每个元素的个位
                int digitOfElement=arr[j]/n%10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }

            //按照这个桶的顺序（一维数组的下标依次取出数据，放入原来数组）
            int index=0;
            //遍历每一个桶，并将桶中的数据，放入到原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据，我们才放入到原数组
                if(bucketElementCounts[k]!=0){
                    //循环该桶，即第k桶（即第k一维数组），将数据放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入到arr中
                        arr[index]=bucket[k][l];
                        index++;
                    }
                }
                //第一轮处理后，需要将每个bucketElementCounts[k]置0 !!!
                bucketElementCounts[k]=0;
            }

//            System.out.println("第"+(i+1)+"轮，对"+(i+1)+"位的排序处理"+ Arrays.toString(arr));

        }


    }
}

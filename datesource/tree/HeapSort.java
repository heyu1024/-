package com.cxw.datesource.tree;

import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * @author shkstart
 * @date 2021-02-03 17:42
 */
public class HeapSort {
    public static void main(String[] args) {
        //要求对数组进行升序排列
//        int[] arr={4,6,8,5,9};
//        int[] arr={4,6,8,3,10,2,5,7,15};

        int[] arr=new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i]=(int)(Math.random()*800000);//生成一个【0，800000）数
        }

        Date date=new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStart = simpleDateFormat.format(date);
        System.out.println("排序前的时间："+dateStart);


        heapSort(arr);


        Date date2=new Date();
        String dateEnd = simpleDateFormat.format(date2);
        System.out.println("排序后的时间："+dateEnd);

    }

    //编写一个堆排序的方法
    public static void heapSort(int arr[]){
        int temp=0;
//        System.out.println("堆排序");

        //分步完成
//        adjustHeap(arr,1,arr.length);
//        System.out.println("第一次调整"+ Arrays.toString(arr));
//
//        adjustHeap(arr,0,arr.length);
//        System.out.println("第二次调整"+Arrays.toString(arr));

        //将无序序列构建成一个推，根据升序降序需求选择大顶堆或小顶堆
        for (int i = arr.length/2 - 1; i >=0 ; i--) {
            adjustHeap(arr,i,arr.length);
        }

//        System.out.println("调整"+Arrays.toString(arr));
//
//        //将堆顶元素和末尾元素交换，将最大元素“沉”到数组末尾
        for (int j = arr.length-1; j >0 ; j--) {
            //交换
            temp=arr[j];
            arr[j]=arr[0];
            arr[0]=temp;
            adjustHeap(arr,0,j);
        }
//
//        System.out.println("交换"+Arrays.toString(arr));

    }

    //将一个数组（对应的是二叉树），调整成一个大顶堆

    /**
     * 功能：将以 i 对应的非叶子节点的树调整成大顶堆
     * 举例：int[] arr={4,6,8,5,9} -> i=1 -> {4,9,8,5,6}
     * 如果我们再次调用adjustHeap 传入的是i=0 ->{9，6，8，5，4}
     * @param arr 待调整的数组
     * @param i 表示非叶子节点在数组中的索引
     * @param length 表示对多少个元素进行调整，length是在逐渐减少
     */
    public static void adjustHeap(int arr[],int i,int length){
        int temp=arr[i];//先取出当前元素的值，保存在一个临时变量
        //开始调整
        //说明
        //k+1<length提高效率
        //1.k=i*2+1 k是i结点的左子结点
        //2.
        for(int k=i*2+1;k<length;k=k*2+1){
            if(k+1<length && arr[k]<arr[k+1]){//说明左子结点的值小于右子结点的值
                k++;//k指向右子结点
            }
            if(arr[k]>temp){//如果子结点大于父节点
                arr[i]=arr[k];//把较大的值赋给当前结点
                i=k;//i 指向 k，继续循环比较
            }else {
                break;
            }
        }

        //当for循环结束后，已经将以i为父节点的树的最大值，放在了最顶（局部）
        arr[i]=temp;//将temp放到调整后的位置
    }
}

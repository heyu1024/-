package com.cxw.datesource.search;

/**
 * @author shkstart
 * @date 2020-12-30 18:10
 */
public class SeqSearch {
    public static void main(String[] args) {
        int[] arr={1,9,11,-1,34,89};//没有顺序的数组
        int index = swqSearch(arr, 3);
        if(index==-1){
            System.out.println("没有查找到");
        }else {
            System.out.println("找到了，下标为:"+index);
        }
    }

    public static int swqSearch(int[] arr,int value){
        //线性查找是逐一比对，发现有相同的值时就返回下标
        for (int i = 0; i <arr.length ; i++) {
            if(arr[i]==value){
                return i;
            }
        }
        return -1;
    }
}

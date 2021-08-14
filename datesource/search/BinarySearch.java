package com.cxw.datesource.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shkstart
 * @date 2020-12-30 18:41
 *
 *  //二分法查找法
 *     //前提时数组必须时有序的!!!!!!!!!!!!!!
 */
public class BinarySearch {
    public static void main(String[] args) {
//        int[] arr={10,89,1000,1000,1000,1000,1234};
//        int resIndex = binarySearch(arr, 0, arr.length - 1, 1000);
//        System.out.println("索引"+resIndex);

//        ArrayList<Integer> resultList = binarySearch2(arr, 0, arr.length - 1, 1000);
//        System.out.println("resultList:"+resultList);

        int[] arr=new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=i+1;
        }
        binarySearch(arr, 0, arr.length - 1, 1);

    }


    /**
     *
     * @param arr 数组
     * @param left 左边的索引
     * @param right 右边的索引
     * @param findVal 要查找的值
     * @return
     */
    public static int binarySearch(int[] arr,int left,int right,int findVal){
        System.out.println("二分查找");

        //当left>right时，说明递归整个数组，但是没有找到
        if(left>right){
            return -1;
        }
        int mid=(left+right)/2;
        int midVal=arr[mid];

        if(findVal>midVal){
            //向右递归
            return binarySearch(arr,mid+1,right,findVal);
        }else if(findVal<midVal){
            return binarySearch(arr,left,mid-1,findVal);
        }else {
            return mid;
        }
    }

    /**
     * 思路：在找到mid值时，不要马上返回，
     * 向mid索引值的左边扫描，将所有满足1000的元素下标，加入到集合ArrayList
     * 向mid索引值的右边扫描，将所有满足1000的元素下标，加入到集合ArrayList
     */

    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findVal){
        //当left>right时，说明递归整个数组，但是没有找到
        if(left>right){
            return new ArrayList<Integer>();
        }
        int mid=(left+right)/2;
        int midVal=arr[mid];

        if(findVal>midVal){
            //向右递归
            return binarySearch2(arr,mid+1,right,findVal);
        }else if(findVal<midVal){
            return binarySearch2(arr,left,mid-1,findVal);
        }else {
            ArrayList<Integer> resIndexList=new ArrayList<Integer>();
            int temp=mid -1;
            //向mid索引值的左边扫描，将所有满足1000的元素下标，加入到集合ArrayList
            while (true){
                if(temp<0 || arr[temp] != findVal){//退出
                    break;
                }
                //否则，就将temp放入到resIndexList
                resIndexList.add(temp);
                temp -=1;//temp左移
            }
            resIndexList.add(mid);

            //向mid索引值的右边扫描，将所有满足1000的元素下标，加入到集合ArrayList
            temp=mid+1;
            while (true){
                if(temp>arr.length-1 || arr[temp] != findVal){//退出
                    break;
                }
                //否则，就将temp放入到resIndexList
                resIndexList.add(temp);
                temp +=1;//temp左移
            }
            return resIndexList;
        }
    }

}

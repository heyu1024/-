package com.cxw.datesource.hashTable;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author shkstart
 * @date 2021-01-18 17:14
 */
public class HashTableDemo {
    public static void main(String[] args) {
        //创建一个哈希表
        HashTab hashTab=new HashTab(7);

        //写一个简单菜单
        String key="";
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("add:添加雇员");
            System.out.println("list：显示雇员");
            System.out.println("find：查找雇员");
            System.out.println("exit：退出");
            key=scanner.next();

            switch (key){
                case "add":
                    System.out.println("输入id");
                    int id=scanner.nextInt();
                    System.out.println("输入名字");
                    String name=scanner.next();
                    //创建雇员
                    Emp emp=new Emp(id,name);
                    hashTab.add(emp);
                    break;
                case "list":
                    hashTab.list();
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                case "find":
                    System.out.println("输入id");
                    int no=scanner.nextInt();
                    hashTab.findEmp(no);
                    break;
            }

        }
    }
}

//创建哈希表，管理多条链表
class HashTab{
    private EmpLinkedList[] empLinkedListArray;
    private int size;


    //构造器
    public HashTab(int size){
        this.size=size;
        //初始化empLinkedListArray
        empLinkedListArray=new EmpLinkedList[size];
        //不要忘了，分别初始化每一条链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i]=new EmpLinkedList();
        }
    }

    //添加雇员
    public void add(Emp emp){
        //根据员工的id，得到该员工应该加入到哪条链表
        int empLinkedListNo=hashFun(emp.id);
        //将emp添加到对应的链表中
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    //编写散列函数，使用一个简单取模法
    public int hashFun(int id){
        return id % size;
    }

    //遍历所有的链表，遍历hashTable
    public void list(){
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].list(i);
        }
    }

    //根据输入的id，找到雇员
    public void findEmp(int id){
        //使用散列函数，确定到哪条链表中查找
        int empLinkedListNo=hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].findEmpById(id);
        if(emp==null){
            System.out.println("没有找到该雇员");
        }else {
            System.out.println("在第"+empLinkedListNo+"条链表中找到该雇员");
            System.out.println(emp);
        }
    }

}

//表示一个雇员
class Emp{
    public int id;
    public String name;
    public Emp next;//next默认为null

    public Emp(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

//创建一个EmpLinkedList，表示链表
class EmpLinkedList{

    //头指针，指向第一个Emp，因此我们这个链表的head是直接指向第一个Emp
    private Emp head;//默认为null
    //添加雇员到链表
    //说明
    //1.假定，当添加雇员时，id是自增长的，即id的分配总是从小到大
    //  因此我们将该雇员直接加入到本链表的最后即可
    public void add(Emp emp){
        //如果是添加第一个雇员
        if(head==null){
            head=emp;
            return;
        }
        //如果不是第一个雇员，则使用一个辅助的指针，帮助定位到最后
        Emp curEmp=head;
        while(true){
            if(curEmp.next==null){
                //说明到链表最后
                break;
            }
            curEmp=curEmp.next;//后移
        }
        //退出时，直接将Emp加入链表
        curEmp.next=emp;
    }

    //遍历链表的雇员信息
    public void list(int no){
        if(head==null){
            //说明链表为空
            System.out.println("第"+no+"链表为空");
            return;
        }
        System.out.print("第"+no+"链表的信息为");
        Emp curEmp=head;//辅助指针
        while(true){
            System.out.println("id:"+curEmp.id+"name:"+curEmp.name);
            if(curEmp.next==null){
                //说明curEmp已经是最后节点
                break;
            }
            curEmp=curEmp.next;//后移，遍历
        }
    }

    //根据id查找雇员
    //如果查找到就返回Emp，如果没有返回null
    public Emp findEmpById(int id){
        if(head==null){
            System.out.println("链表空");
            return null;
        }

        Emp curEmp=head;
        while (true){
            if(curEmp.id==id){
                //这时curEmp就指向该雇员
                break;
            }
            if(curEmp.next==null){
                //如果一直没有找到
                curEmp=null;
                break;
            }
            curEmp=curEmp.next;
        }
        return curEmp;
    }
}

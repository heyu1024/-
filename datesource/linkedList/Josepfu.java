package com.cxw.datesource.linkedList;

/**
 * @author shkstart
 * @date 2020/7/29 - 17:50
 */
public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.showBoy();


        //测试一把小孩出圈是否正确
        circleSingleLinkedList.countBoy(2,2,5);
    }
}

//创建一个环形的单向链表
class CircleSingleLinkedList{
    //创建一个first节点，当前没有编号
    private Boy first=null;
    //添加小孩节点，构建一个环形的链表
    public void addBoy(int nums){
        //nums 做一个数据校验
        if(nums<1){
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy=null;//辅助指针，帮助构建环形链表
        //使用for来创建我们的环形链表
        for (int i = 1; i <=nums ; i++) {
            //根据编号创建小孩节点
            Boy boy=new Boy(i);
            //如果是第一个小孩
            if(i==1){
                first=boy;
                first.setNext(first);//构成环
                curBoy=first;//让curBoy指向第一个小孩
            }else {
                curBoy.setNext(boy);
                boy.setNext(first);
                curBoy=boy;
            }
        }
    }

    //遍历当前环形链表
    public void showBoy(){
        //判断链表是否为空
        if(first==null){
            System.out.println("没有小孩");
            return;
        }
        //因为first不能动，因此我们仍然使用辅助指针，完成遍历
        Boy curBoy=first;
        while (true){
            System.out.println("小孩的编号"+curBoy.getNo());
            if(curBoy.getNext()==first){
                System.out.println("遍历完毕");
                break;
            }
            curBoy=curBoy.getNext();//curBoy后移
        }
    }

    /**
     * 根据用户的输入，计算出小孩出圈的顺序
     * @param startNo 表示从第几个小孩开始数数
     * @param countNum 表示数几下
     * @param nums 表示最初有多少个小孩
     */
    public void countBoy(int startNo,int countNum,int nums){
        //先对数据进行校验
        if(first==null || startNo<1 || startNo>nums ){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建一个辅助指针，帮助完成小孩出圈
        Boy hepler=first;
        while (true){
            if(hepler.getNext()==first){//说明hepler指向了最后的节点
                break;
            }
            hepler=hepler.getNext();
        }
        //小孩报道前，先让first和hepler移动k-1次
        for (int i = 0; i <startNo-1 ; i++) {
            first=first.getNext();
            hepler=hepler.getNext();
        }
        //当小孩报数时，让first和hepler指针同时的移动m-1次，然后出圈
        //这里是一个循环操作，直到圈中只有一个节点
        while (true){
            if(hepler==first){//说明圈中只有一个人
                break;
            }
            //让first和hepler指针同时移动countNum-1
            for (int j = 0; j <countNum-1 ; j++) {
                first=first.getNext();
                hepler=hepler.getNext();
            }
            //这时first指向的节点就是要出圈的节点
            System.out.println(first.getNo()+"小孩出圈");
            first=first.getNext();
            hepler.setNext(first);
        }
        System.out.println("最后留在圈中的小孩编号"+first.getNo());
    }
}

//创建一个Boy类，表示一个节点
class Boy{
    private int no;//编号
    private Boy next;//指向下一个节点，默认为null
    public Boy(int no) {
        this.no=no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}
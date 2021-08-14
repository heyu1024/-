package com.cxw.datesource.linkedList;

import java.util.Stack;

/**
 * 单链表
 * @author
 * @date 2020/7/7 - 18:56
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "吴用", "智多星");
        HeroNode hero3 = new HeroNode(3, "林冲", "豹子头");
        HeroNode hero4 = new HeroNode(4, "狗头", "猫");


        //创建要给链表
        SingleLinkedList singleLinkedList=new SingleLinkedList();
//        //加入
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);

        System.out.println("原来链表");
        singleLinkedList.list();

        System.out.println("测试逆序打印单链表");
        reversePrint(singleLinkedList.getHead());
/*


        System.out.println("反转之后");
        reverseList(singleLinkedList.getHead());
        singleLinkedList.list();


        //加入按照编号的顺序
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero3);

        //显示
        singleLinkedList.list();

        //测试修改节点的代码
        HeroNode newHeroNode = new HeroNode(2, "小路", "路麒麟");
        singleLinkedList.update(newHeroNode);

        System.out.println("修改后的链表情况~");
        //显示
        singleLinkedList.list();

        //删除一个节点
        singleLinkedList.del(1);
        singleLinkedList.del(4);
//        singleLinkedList.del(2);
//        singleLinkedList.del(3);

        System.out.println("删除之后~");
        singleLinkedList.list();


        //测试一下，求单链表中有效节点的个数
        System.out.println(getLength(singleLinkedList.getHead()));

        //测试一下，是否得到倒数第k个节点
        HeroNode lastIndexNode = findLastIndexNode(singleLinkedList.getHead(), 3);
        System.out.println(lastIndexNode);
*/
    }

    //使用方式2
    public static void reversePrint(HeroNode head){
        if(head.next==null){
            return;//空链表，不能打印
        }
        //创建一个栈，将各个节点压入栈
        Stack<HeroNode> stack=new Stack<>();
        HeroNode cur=head.next;
        //将链表的所有节点压入栈中
        while(cur!=null){
            stack.push(cur);
            cur=cur.next;//cur后移
        }
        //将栈中的节点进行打印
        while(stack.size()>0){
            System.out.println(stack.pop());
        }

    }

    //将单链表进行反转
    public static void reverseList(HeroNode head){
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if(head.next==null || head.next.next==null){
            return;
        }
        //定义一个辅助指针（变量），帮组我们遍历原来的链表
        HeroNode cur=head.next;
        //指向但钱节点（cur）的下一个节点
        HeroNode next=null;
        HeroNode reverseHead=new HeroNode(0,"","");

        //遍历原来的链表
        //每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
        while (cur!=null){
            next=cur.next;//先暂时保存当前节点的下一个节点，因为后面需要使用
            cur.next=reverseHead.next;//将cur的下一个节点，指向新的链表的最前端
            reverseHead.next=cur;
            cur=next;//让cur后移
        }
        head.next=reverseHead.next;
    }

    /**
     * 查找单链表中的倒数第k个节点
     * 1.编写一个方法，接受head节点，同时接收一个index（表示倒数第index个节点）
     * 2.先把链表从头到尾开始遍历，得到链表总的长度getLength
     * 3.得到size后，我们从链表的第一个开始遍历（size-index）个
     * 4.如果找到，返回该节点；否则返回null
     */
    public static HeroNode findLastIndexNode(HeroNode head,int index){
        //判断如果链表为空，返回null
        if(head.next==null){
            return null;
        }
        //第一次遍历得到链表的长度（节点个数）
        int size = getLength(head);
        //第二次遍历 size-index 位置，就是我们的倒数第K个节点
        //先做一个index的校验
        if(index<=0 || index>size){
            return null;
        }
        //定义一个辅组变量,for循环定位到倒数的index
        HeroNode cur=head.next;
        for (int i = 0; i <size-index ; i++) {
            cur=cur.next;
        }
        return cur;
    }


    /**
     *方法：获取单链表的节点的个数（如果是带头节点的链表，需求不统计头节点）
     * @param head 链表的头节点
     * @return 有效节点的个数
     */
    public static int getLength(HeroNode head){
        if(head.next==null){//空链表
            return 0;
        }
        int length=0;
        //定义一个辅组的变量
        HeroNode cur=head.next;
        while (cur!=null){
            length++;
            cur=cur.next;//遍历
        }
        return length;
    }
}

//定义一个SingleLinkedList 管理我们的英雄
class SingleLinkedList{
    //先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode head=new HeroNode(0,"","");

    public HeroNode getHead() {
        return head;
    }

    //添加节点到单向链表
    //思路，当不考虑编号的顺序时
    //1.找到当前链表的最后节点
    public void add(HeroNode headNode){
        //因为head节点不能动，因此我们需要一个辅助变量temp
        HeroNode temp=head;
        //遍历链表，找到最后
        while (true){
            //找到链表最后
            if(temp.next==null){
                break;
            }
            //如果没有找到最后，就将temp后移
            temp=temp.next;
        }
        //当退出while循环时，temp就指向链表的最后
        //2.将最后节点的next指向新的节点
        temp.next=headNode;
    }

    //第二种添加英雄，根据排名将英雄插入到指定位置
    //（如果有这个排名，则添加失败，并给出提示）
    public void addByOrder(HeroNode heroNode){
        //因为头节点不能动，因此我们仍然通过一个辅组指针(变量)来帮助找到添加得位置，
        //因为单链表，因此我们找的temp是位于添加位置得前一个节点，否则插入不了
        HeroNode temp=head;
        boolean flag=false;//flag标识添加的编号是否存在，默认为false
        while (true){
            if(temp.next==null){//说明temp已经再链表的最后
                break;
            }
            if(temp.next.no>heroNode.no){//位置找到，就再temp的后面插入
                break;
            }else if(temp.next.no == heroNode.no){
                //说明希望添加的heroNode的编号已然存在
                flag=true;
                break;
            }
            temp=temp.next;//后移，遍历当前链表
        }

        //判断flag的值
        if(flag){//不能添加，说明编号存在
            System.out.println("准备插入的"+heroNode.no+"英雄的编号已经存在，不能加入");
        }else {
            //插入到链表中
            heroNode.next=temp.next;
            temp.next=heroNode;
        }
    }

    //修改节点的信息，根据no编号来修改，即no编号不能改
    //说明
    //1.根据newHeroNode的no来修改即可
    public void update(HeroNode newHeroNode){
        //判断是否为空
        if(head.next==null){
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode temp=head.next;
        boolean flag=false;//表示是否找到该节点
        while(true){
            if(temp==null){
                break;//已经遍历完链表
            }
            if(temp.no == newHeroNode.no){
                //找到
                flag=true;
                break;
            }
            temp=temp.next;
        }

        //根据flag判断是否找到要修改的节点
        if(flag){
            temp.name=newHeroNode.name;
            temp.nickName=newHeroNode.nickName;
        }else {//没有找到
            System.out.printf("没有找到 编号 %d 的节点，不能修改 \n",newHeroNode.no);
        }

    }

    //删除节点
    //思路
    //1.head不能动，因此我们需要一个temp辅组节点找到待删除节点的前一个节点
    //2.说明我们在比较时，是temp.next.no 和需要删除的节点的no比较
    public void del(int no){
        HeroNode temp=head;
        boolean flag=false;//标志是否找到待删除的节点
        while (true){
            if(temp.next==null){//已经到链表的最后
                break;
            }
            if(temp.next.no==no){
                //找到了待删除节点的前一个节点temp
                flag=true;
                break;
            }
            temp=temp.next;//temp后移
        }
        //判断flage
        if(flag){//找到
            //可以删除
            temp.next=temp.next.next;
        }else {
            System.out.println("要删除的节点"+no+"不存在");
        }
    }


    //显示链表【遍历】
    public void list(){
        //判断链表是否为空
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，因此我们需要一个辅助变量来遍历
        HeroNode temp=head.next;
        while (true){
            //判断是否到链表最后了
            if(temp==null){
                break;
            }
            //输出节点信息
            System.out.println(temp);
            //将next后移
            temp=temp.next;
        }
    }


}

//定义HeroNode，每个HeroNode 对象就是一个节点
class HeroNode{
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;//指向下一个节点

    //构造器
    public HeroNode(int no, String name, String nickName){
        this.no=no;
        this.name=name;
        this.nickName=nickName;
    }

    //为了显示方便，重写toString
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName +
                '}';
    }


}

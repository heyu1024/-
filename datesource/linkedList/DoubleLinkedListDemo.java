package com.cxw.datesource.linkedList;

/**
 * @author shkstart
 * @date 2020/7/29 - 16:14
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //进行测试
        //先创建节点
        HeroNode2 hero1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 hero2 = new HeroNode2(2, "吴用", "智多星");
        HeroNode2 hero3 = new HeroNode2(3, "林冲", "豹子头");
        HeroNode2 hero4 = new HeroNode2(4, "狗头", "猫");

        //创建一个双向链表对象
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.add(hero1);
//        doubleLinkedList.add(hero2);
//        doubleLinkedList.add(hero3);
//        doubleLinkedList.add(hero4);

        System.out.println("--------------------");
        doubleLinkedList.addByOrder(hero4);
        doubleLinkedList.addByOrder(hero1);
        doubleLinkedList.addByOrder(hero3);
        doubleLinkedList.addByOrder(hero2);
        doubleLinkedList.addByOrder(hero3);




        doubleLinkedList.list();
        //修改
        HeroNode2 hero5 = new HeroNode2(4, "猫女", "露易丝");
        doubleLinkedList.update(hero5);
        System.out.println("修改后的双向链表情况");
        doubleLinkedList.list();

        //删除
        doubleLinkedList.del(2);
        System.out.println("删除后的双向链表情况");
        doubleLinkedList.list();

    }
}


//创建一个双向链表的类
class DoubleLinkedList{
    //先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode2 head=new HeroNode2(0,"","");

    public HeroNode2 getHead() {
        return head;
    }

    //遍历双向链表的方法
    //显示链表【遍历】
    public void list(){
        //判断链表是否为空
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，因此我们需要一个辅助变量来遍历
        HeroNode2 temp=head.next;
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

    //添加节点到双向链表
    //思路，当不考虑编号的顺序时
    //1.找到当前链表的最后节点
    public void add(HeroNode2 headNode){
        //因为head节点不能动，因此我们需要一个辅助变量temp
        HeroNode2 temp=head;
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
        //形成一个双向链表
        temp.next=headNode;
        headNode.pre=temp;
    }

    //第二种添加英雄，根据排名将英雄插入到指定位置
    //（如果有这个排名，则添加失败，并给出提示）
    public void addByOrder(HeroNode2 heroNode){
        //因为头节点不能动，因此我们仍然通过一个辅组指针(变量)来帮助找到添加得位置，
        //因为单链表，因此我们找的temp是位于添加位置得前一个节点，否则插入不了
        HeroNode2 temp=head;
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
//            if(temp.next==null){
//                heroNode.pre=temp;
//                temp.next=heroNode;
//            }else {
                //插入到链表中
                if(temp.next!=null){
                    heroNode.next=temp.next;
                    temp.next.pre=heroNode;
                }

                temp.next=heroNode;
                heroNode.pre=temp;
//            }
        }
    }

    //修改一个节点
    //修改节点的信息，根据no编号来修改，即no编号不能改
    //说明
    //1.根据newHeroNode的no来修改即可
    public void update(HeroNode2 newHeroNode){
        //判断是否为空
        if(head.next==null){
            System.out.println("链表为空~");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义一个辅助变量
        HeroNode2 temp=head.next;
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
    //说明，对于双向链表，我们可以找到要删除的节点，找到后，自我删除即可
    public void del(int no){
        if(head.next==null){//空链表
            System.out.println("链表为空，不能删除~");
            return;
        }
        HeroNode2 temp=head.next;
        boolean flag=false;//标志是否找到待删除的节点
        while (true){
            if(temp==null){//已经到链表的最后节点的next
                break;
            }
            if(temp.no==no){
                //找到了待删除节点的前一个节点temp
                flag=true;
                break;
            }
            temp=temp.next;//temp后移
        }
        //判断flage
        if(flag){//找到
            //可以删除
            temp.pre.next=temp.next;
            //如果是最后一个节点，就不需要执行下面这句话，否则容易出现空指针异常
            if(temp.next!=null){
                temp.next.pre=temp.pre;
            }
        }else {
            System.out.println("要删除的节点"+no+"不存在");
        }
    }
}

//定义HeroNode，每个HeroNode 对象就是一个节点
class HeroNode2{
    public int no;
    public String name;
    public String nickName;
    public HeroNode2 next;//指向下一个节点
    public HeroNode2 pre;//指向前一个节点

    //构造器
    public HeroNode2(int no, String name, String nickName){
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
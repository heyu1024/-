package com.cxw.datesource.tree;

/**
 * @author shkstart
 * @date 2021-01-22 15:23
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        //先需要创建一颗二叉树
        BinaryTree binaryTree=new BinaryTree();
        //创建需要的节点
        HeroNode root=new HeroNode(1,"aaa");
        HeroNode node2=new HeroNode(2,"bbb");
        HeroNode node3=new HeroNode(3,"ccc");
        HeroNode node4=new HeroNode(4,"ddd");
        HeroNode node5=new HeroNode(5,"eee");


        //说明，我们先手动创建二叉树，后面我们学习以递归方式创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);

        binaryTree.setRoot(root);

        //测试
//        System.out.println("前序遍历");//1 2 3 5 4
//        binaryTree.preOrder();

//        System.out.println("中序遍历");//2 1 5 3 4
//        binaryTree.infixOrder();
//
//        System.out.println("后序遍历");//2 5 4 3 1
//        binaryTree.postOrder();
//
//        HeroNode preOrderSearch = binaryTree.preOrderSearch(5);
//        System.out.println("前序遍历查找");
//        System.out.println(preOrderSearch);
//
//        HeroNode infixOrderSearch = binaryTree.infixOrderSearch(5);
//        System.out.println("中序遍历查找");
//        System.out.println(infixOrderSearch);
//
//        HeroNode postOrderSearch = binaryTree.postOrderSearch(5);
//        System.out.println("后序遍历查找");
//        System.out.println(postOrderSearch);

        System.out.println("删除前");
        System.out.println("前序遍历");//1 2 3 5 4
        binaryTree.preOrder();

        System.out.println("删除后");
        binaryTree.delNode(5);
        binaryTree.preOrder();//1 2 3 4

    }
}

//定义BinaryTree 二叉树
class BinaryTree{
    private HeroNode root;//根节点

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    //前序遍历
    public void preOrder(){
        if(this.root!=null){
            this.root.preOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //中序遍历
    public void infixOrder(){
        if(this.root!=null){
            this.root.infixOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //后序遍历
    public void postOrder(){
        if(this.root!=null){
            this.root.postOrder();
        }else {
            System.out.println("二叉树为空，无法遍历");
        }
    }

    //前序遍历查找
    public HeroNode preOrderSearch(int no){
        if(this.root!=null){
            return this.root.preOrderSearch(no);
        }else {
            System.out.println("二叉树为空，无法遍历查找");
            return null;
        }
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no){
        if(this.root!=null){
            return this.root.infixOrderSearch(no);
        }else {
            System.out.println("二叉树为空，无法遍历查找");
            return null;
        }
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no){
        if(this.root!=null){
            return this.root.postOrderSearch(no);
        }else {
            System.out.println("二叉树为空，无法遍历查找");
            return null;
        }
    }

    //删除节点
    public void delNode(int no){
        if(root!=null){
            //如果只有一个Root节点，这里立即判断root是不是就要删除的节点
            if(root.getNo()==no){
                root=null;
            }else {
                //递归删除
                root.delNode(no);
            }
        }else {
            System.out.println("空树无法删除");
        }
    }

}


//先创建HeroNode节点
class HeroNode{
    private int no;
    private String name;
    private HeroNode left;//默认为null
    private HeroNode right;//默认为null

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    //编写前序遍历的方法
    public void preOrder(){
        System.out.println(this);//先输出父节点
        //递归向左子树前序遍历
        if(this.left!=null){
            this.left.preOrder();
        }
        //递归向右子树前序遍历
        if(this.right!=null){
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder(){
        //递归向左子树中序遍历
        if(this.left!=null){
            this.left.infixOrder();
        }
        //输出父节点
        System.out.println(this);
        //递归向右子树中序遍历
        if(this.right!=null){
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder(){
        //递归向左子树后序遍历
        if(this.left!=null){
            this.left.postOrder();
        }
        //递归向右子树后序遍历
        if(this.right!=null){
            this.right.postOrder();
        }
        //输出父节点
        System.out.println(this);
    }

    //前序遍历查找
    /**
     *
     * @param no 查找的编号
     * @return 如果找到返回该Node，如果没有找到返回null
     */
    public HeroNode preOrderSearch(int no){
        System.out.println("进入前序遍历查找");
        //比较当前结点是不是
        if(this.no==no){
            return this;
        }
        //
        HeroNode resNode=null;
        if(this.left!=null){
            resNode=this.left.preOrderSearch(no);
        }
        if(resNode!=null){//说明我们左子树找到了
            return resNode;
        }
        if(this.right!=null){
            resNode=this.right.preOrderSearch(no);
        }
        return resNode;
    }

    //中序遍历查找
    public HeroNode infixOrderSearch(int no){

        HeroNode resNode=null;
        if(this.left!=null){
            resNode=this.left.infixOrderSearch(no);
        }
        if(resNode!=null){//说明我们左子树找到了
            return resNode;
        }

        System.out.println("进入中序遍历查找");

        //比较当前结点是不是
        if(this.no==no){
            return this;
        }

        if(this.right!=null){
            resNode=this.right.infixOrderSearch(no);
        }
        return resNode;
    }

    //后序遍历查找
    public HeroNode postOrderSearch(int no){
        HeroNode resNode=null;
        if(this.left!=null){
            resNode=this.left.postOrderSearch(no);
        }
        if(resNode!=null){//说明我们左子树找到了
            return resNode;
        }

        if(this.right!=null){
            resNode=this.right.postOrderSearch(no);
        }
        if(resNode!=null){
            return resNode;
        }

        System.out.println("进入后序遍历查找");

        //比较当前结点是不是
        if(this.no==no){
            return this;
        }

        return resNode;
    }

    //递归删除节点
    //1.如果删除的节点是叶子节点，则删除该节点
    //2.如果删除的节点是非叶子节点，则删除该子树
    public void delNode(int no){
        if(this.left!=null && this.left.no==no){
            this.left=null;
            return;
        }

        if(this.right!=null && this.right.no==no){
            this.right=null;
            return;
        }

        if(this.left!=null){
            this.left.delNode(no);
        }

        if(this.right!=null){
            this.right.delNode(no);
        }
    }
}
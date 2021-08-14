package com.cxw.datesource.stack;

import java.util.Stack;

/**
 * @author shkstart
 * @date 2020-12-14 18:02
 */
public class Calculator {
    public static void main(String[] args) {
        //表达式
        String expression="5-2*2-1";
        //创建两个栈，数栈和符号栈
        ArrayStack02 numStack = new ArrayStack02(10);

        ArrayStack02 operStack = new ArrayStack02(10);

        //定义需要的相关变量
        int index=0;//用于扫描
        int num1=0;
        int num2=0;//弹数用
        int oper=0;
        int result=0;
        char ch=' ';//将每次扫描得到char保存到ch

        String keepNum="";//用于拼接多位数

        //开始用while循环的扫描expression
        while (true){
            //依次得到expression的每一个字符
            ch=expression.substring(index,index+1).charAt(0);
            //判断ch是什么，然后做相应的处理、
            if(operStack.isOper(ch)){
                //如果是运算符
                //判断当前的符号是否为空
                if(!operStack.isEmpty()){
                    //处理
                    if(operStack.priority(ch) <= operStack.priority(operStack.peek())){
                        num1=numStack.pop();
                        num2=numStack.pop();
                        oper=operStack.pop();

                        result=numStack.cal(num1,num2,oper);

                        //把运算的结果入数栈
                        numStack.push(result);
                        //把当前的操作符入符号栈
                        operStack.push(ch);
                    }else {
                        operStack.push(ch);
                    }
                }else {
                    //如果为空直接入栈
                    operStack.push(ch);
                }
            }else {
                //如果是数，则直接入数栈
                //numStack.push(ch-48);//'1'转换为ASCLL值为49
                //当处理多位数时，不能发现是一个数就立刻入栈，因为他可能是多位数
                //在处理数是，需要向expression的表达式的index后再看一位，如果是数就继续扫描，不能马上入栈，如果是符号才入栈
                //因此我们需要定义一个变量（字符串）用于拼接

                //处理多位数
                keepNum += ch;

                //如果ch已经是expression的最后一位，就直接入栈，不需要判断
                if(index == expression.length()-1){
                    numStack.push(Integer.parseInt(keepNum));
                }else {
                    //判断下一个字符是不是数字，如果是数字，则进行继续扫描，如果是运算符，则入栈
                    //注意是看后一位，不是index++
                    if(operStack.isOper(expression.substring(index+1,index+2).charAt(0))){
                        //如果后一位是运算符，则入栈 keepNum="1" 或者“123”
                        numStack.push(Integer.parseInt(keepNum));
                        //重要，清空keepNumber
                        keepNum="";
                    }
                }


            }

            //让index+1,并判断是否扫描到expression最后
            index++;
            if(index>=expression.length()){
                break;
            }


        }

        //当表达式扫描完毕
        while (true){
            //如果符号栈为空，则计算到最后的结果
            if(operStack.isEmpty()){
                break;
            }
            num1=numStack.pop();
            num2=numStack.pop();
            oper=operStack.pop();

            result=numStack.cal(num1,num2,oper);

            numStack.push(result);//入栈
        }

        System.out.println("表达式:"+expression);
        System.out.println("结果:"+numStack.pop());
    }
}

//先创建一个栈
//定义一个ArrayStack表示栈,需要扩展功能
class ArrayStack02{
    private int maxSize;//栈的大小
    private int[] stack;//数组，数组模拟栈，数据就该放在数组中
    private int top=-1;//top表示栈底，初始化为-1



    //构造器
    public ArrayStack02(int maxSize){
        this.maxSize=maxSize;
        stack=new int[this.maxSize];
    }

    //栈满
    public boolean isFull(){
        return top==maxSize-1;
    }

    //栈空
    public boolean isEmpty(){
        return top == -1;
    }

    //入栈 push
    public void push(int value){
        //先判断是否满
        if(isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        stack[top]=value;
    }

    //出栈 pop--将栈顶的数据返回
    public int pop(){
        //先判断是否空
        if (isEmpty()){
            throw new RuntimeException("栈空");
        }
        int value = stack[top];
        top--;
        return value;
    }

    //遍历栈 遍历时需要从栈顶开始显示数据
    public void list(){
        if(isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >=0 ; i--) {
            System.out.println("stack["+i+"]"+stack[i]);
        }
    }

    //返回运算符的优先级，优先级是程序员确定的，优先级使用数字表示
    //数字越大，则优先级就越高
    public int priority(int oper){
        if(oper == '*' || oper == '/'){
            return 1;
        }else if(oper == '+'||oper == '-'){
            return 0;
        }else {
            return -1;//假定目前的表达式只有+，-，*，/
        }
    }

    //判断是不是一个运算符
    public boolean isOper(char val){
        return val == '+' || val == '-' || val == '*' || val =='/';
    }

    //计算方法
    public int cal(int num1,int num2,int oper){
        int res=0;//用于存放计算的结果
        switch (oper){
            case '+':
                res=num1+num2;
                break;
            case '-':
                res= num2-num1;//注意顺序
                break;
            case '*':
                res= num2*num1;//注意顺序
                break;
            case '/':
                res= num2/num1;//注意顺序
                break;
            default:
                break;
        }
        return res;
    }

    //查看栈顶的值，但是不出栈
    public int peek(){
        return stack[top];
    }

}

package com.cxw.datesource.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author shkstart
 * @date 2020-12-15 15:03
 */
public class PolandNotation {
    public static void main(String[] args) {

        //完成将一个中缀表达式转后缀表达式
        // 1+（（2+3）*4）-5  ——> 1 2 3 + 4 * + 5 -
        //因为直接对一个字符串进行操作不方便，因此先将“1+（（2+3）*4）-5 ”中缀的表达式对应的list
        String infixExpression="1-((2+3)*4)-5";

        List<String> infixExpressionList = toInfixExpressionList(infixExpression);
        System.out.println("中缀表达式："+infixExpressionList);

        //[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5] ->[1,2,3,+,4,*,+,5,-]
        List<String> suffixExpreesionList = parseSuffixExpreesionList(infixExpressionList);

        System.out.println("后缀表达式："+suffixExpreesionList);

        int result = calculate(suffixExpreesionList);

        System.out.println(result);

        //先定义一个逆波兰表达式
        //(3+4)*5-6 => 3 4 + 5 * 6 -
        //4*5-8+60+8/2 => 4 5 * 8 - 60 + 8 2 / +
        //为了方便，逆波兰表达式的数字和符号使用空格隔开
        String suffixExpression="4 5 * 8 - 60 + 8 2 / +";
        //思路
        //1.先将"3 4 + 5 * 6 -" =>放到ArrayList中
        //2.将ArrayList传递给一个方法，配合栈完成计算

//        List<String> rpnList = getListString(suffixExpression);
//        System.out.println(rpnList);
//
//        int result = calculate(rpnList);
//        System.out.println("suffixExpression:"+suffixExpression);
//        System.out.println("result:"+result);
    }

    //中缀转后缀
    public static List<String> parseSuffixExpreesionList(List<String> infixExpressionList){
        //定义两个栈
        Stack<String> s1=new Stack<>();//符号栈
        //说明：因为s2这个栈在整个转化过程中，没有pop的操作，后面需要逆序输出
        //因此比较麻烦，这里我们不用Stack<String> 直接使用List<String> s2
        List<String> s2=new ArrayList<>();//存储中间结果的List

        //遍历infixExpressionList
        for (String item : infixExpressionList) {
            //如果是一个数，加入s2
            if(item.matches("\\d+")){
                s2.add(item);
            }else if(item.equals("(")){
                s1.push(item);
            }else if(item.equals(")")){
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();//消掉一对括号
            }else {
                //当item的优先级小于等于s1栈顶的运算符的优先级
                //问题：我们缺少比较优先级高低的方法
                while(s1.size()!=0 && Operation.getValue(item) <= Operation.getValue(s1.peek())){
                    s2.add(s1.pop());
                }
                //还需要将item压入栈
                s1.push(item);
            }
        }

        //将s1中剩余的运算符依次弹出并加入s2
        while (s1.size()!=0){
            s2.add(s1.pop());
        }

        return s2;//因为是放到List中，因此按照顺序输出就是对应的逆波兰表达式
    }


    //将中缀表达式转成对应的list
    public static List<String> toInfixExpressionList(String infixExpression){
        //定义一个List，存放重罪表达式对应的内容
        List<String> ls=new ArrayList<>();
        int i=0;//指针，用于遍历中缀表达式
        String str;//做多位数的一个拼接
        char c;//每遍历到一个字符，就放到c

        do{
            //如果c是一个非数字，需要加入到ls
            if((c=infixExpression.charAt(i))<48 || (c=infixExpression.charAt(i))>57){
                ls.add(""+c);
                i++;//i需要后移
            }else {
                //如果是一个数，需要考虑多位数的问题
                str="";//先将str置空
                while (i<infixExpression.length() && ((c=infixExpression.charAt(i))>=48 && (c=infixExpression.charAt(i))<=57)){
//                    c=infixExpression.charAt(i);
                    str +=c;//拼接
                    i++;
                }
                ls.add(str);
            }
        }while (i<infixExpression.length());

        return ls;
    }

    //将一个逆波兰表达式，依次将数据和运算符放入到ArrayList中
    public static List<String> getListString(String suffixExpression){
        //将suffixExpression分割
        String[] split = suffixExpression.split(" ");
        List<String> list=new ArrayList<>();
        for (String s : split) {
            list.add(s);
        }
        return list;
    }

    //完成对逆波兰表达式的运算
    public static int calculate(List<String> ls){
        //创建一个栈，只需要一个栈即可
        Stack<String> stack = new Stack<String>();
        //遍历ls
        for (String item : ls) {
            //这里使用正则表达式来取出数
            if(item.matches("\\d+")){
                //匹配的是多位数
                //入栈
                stack.push(item);
            }else {
                //pop出两个数，并运算，再入栈
                int num2=Integer.parseInt(stack.pop());
                int num1=Integer.parseInt(stack.pop());

                int result=0;
                if(item.equals("+")){
                    result=num1+num2;
                }else if(item.equals("-")){
                    result=num1-num2;
                }else if(item.equals("*")){
                    result=num1*num2;
                }else if(item.equals("/")){
                    result=num1/num2;
                }else {
                    throw new RuntimeException("运算符有误");
                }
                //把result入栈
                stack.push(""+result);
            }

        }

        //最后留在stack中的数据就是运算结果
        return Integer.parseInt(stack.pop());
    }
}


//编写一个类Operation,可以返回一个运算符对应的优先级
class Operation{
    private static int ADD=1;
    private static int SUB=1;
    private static int MUL=2;
    private static int DIV=2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation){
        int result=0;
        switch(operation){
            case "+":
                result =ADD;
                break;
            case "-":
                result =SUB;
                break;
            case "*":
                result =MUL;
                break;
            case "/":
                result =DIV;
                break;
            default:
                result=0;
                break;

        }
        return result;
    }

}
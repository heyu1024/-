package com.cxw.datesource.linkedList;

import java.util.Stack;

/**
 * 演示栈的基本使用
 * @author shkstart
 * @date 2020/7/29 - 15:35
 */
public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack=new Stack<>();
        //入栈
        stack.add("jack");
        stack.add("tom");
        stack.add("smith");
        //出栈
        while (stack.size()>0){
            System.out.println(stack.pop());//pop就是将栈的顶部数据取出
        }
    }
}

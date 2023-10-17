//package com.learn.java.indepth.ds.responsibilitychain.servlet;
//
//import java.util.ArrayList;
//
///**
// * 责任链模式 :
// * 相对于对某类对象, 进行一系列相同操作的时候, 将这些操作, 并且这些操作可能进行扩展的时候 ,可以使用此设计模式
// * 声明一个抽象接口, 所有类都实现你需要实现的抽象操作,
// * 然后定义一个Chain类, 这个Chain类也需要实现这个接口, 因为他是一个调用链, 间接代理了内部List中所有对象执行相同操作
// * 同时也添加自身类型加入到List中, 然后遍历调用多层实现
// */
//public class ServletMain {
//    public static void main(String[] args) {
//        Request request = new Request();
//        request.str = "resquest";
//        Response response = new Response();
//        response.str = "response";
//
//    }
//}
//
//class Msg {
//    String name;
//    String msg;
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    @Override
//    public String toString() {
//        return "Msg{" +
//                "name='" + name + '\'' +
//                ", msg='" + msg + '\'' +
//                '}';
//    }
//}
//
//interface Filter {
//    boolean doFilter(Request request, Response response);
//}
//
//class HttpMessageFilter implements Filter {
//    @Override
//    public boolean doFilter(Request request, Response response) {
//        return false;
//    }
//}
//
//
//
//class SensitiveMessageFilter implements Filter {
//
//    @Override
//    public boolean doFilter(Request request, Response response) {
//        return false;
//    }
//}
//
//class FilterChain implements Filter{
//    ArrayList<Filter> filters = new ArrayList<>();
//
//    public FilterChain addFilter(Filter filter) {
//        filters.add(filter);
//        return this;
//    }
//
//    // 只要责任链中, 有一个失败, 就停止, 代表全部失败
//
//    @Override
//    public boolean doFilter(Request request, Response response) {
//        return false;
//    }
//}
//
//
//class Request{
//    String str;
//}
//class Response{
//    String str;
//}
//
//
//

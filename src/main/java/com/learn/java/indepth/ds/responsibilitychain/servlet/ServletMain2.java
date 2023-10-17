package com.learn.java.indepth.ds.responsibilitychain.servlet;

import lombok.Getter;

import java.util.ArrayList;

/**
 * 实现servlet 的过滤器链 ：
 * 定义chain的时候， 需要有一个point代表当前正在执行的filter
 * 而doFilter方法也需要含有chain的引用， 来控制时候继续向下运行
 * 因为这种方式是递归调用的， 如果放在方法的最后， 就是顺序执行，
 * 如果是放在最前，就是倒叙执行
 * 如果放在中间， 就类似与中序遍历
 */
public class ServletMain2 {
    public static void main(String[] args) {
        Request request = new Request();
        request.str = "resquest";
        Response response = new Response();
        response.str = "response";


        HttpMessageFilter httpMessageFilter = new HttpMessageFilter();
        SensitiveMessageFilter sensitiveMessageFilter = new SensitiveMessageFilter();

        FilterChain filterChain = new FilterChain();
        filterChain.addFilter(httpMessageFilter).addFilter(sensitiveMessageFilter);
        filterChain.doFilter(request, response, filterChain);


    }
}

class Msg {
    String name;
    @Getter
    String msg;

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "name='" + name + '\'' +
                ", msg='" + msg + '\'' +
                '}';
    }
}

interface Filter {
    boolean doFilter(Request request, Response response,FilterChain filterChain);
}

class HttpMessageFilter implements Filter {

    @Override
    public boolean doFilter(Request request, Response response, FilterChain filterChain) {
        Filter next;

            request.str = request.str + " http ";
            System.out.println("request : " + request.str);
            filterChain.doFilter(request,response,filterChain);
            response.str += "hello http";
            System.out.println("response : " + response.str);

        return true;
    }
}



class SensitiveMessageFilter implements Filter {

    @Override
    public boolean doFilter(Request request, Response response, FilterChain filterChain) {
        request.str += "is sensitive";
        System.out.println("request : " + request.str);
        filterChain.doFilter(request, response, filterChain);
        response.str += "very sensitive";
        System.out.println("response : " + response.str);
        return true;
    }
}

class FilterChain implements Filter{
    int pointer = 0;
    ArrayList<Filter> filters = new ArrayList<>();

    public FilterChain addFilter(Filter filter) {
        filters.add(filter);
        return this;
    }

    public Filter findNext(Filter filter) {
        return filters.get(pointer++);

    }

    public void reset() {
        pointer = 0;
    }

    // 只要责任链中, 有一个失败, 就停止, 代表全部失败

    @Override
    public boolean doFilter(Request request, Response response, FilterChain filterChain) {

        if(pointer >= filters.size()) {
            return false;
        }
        Filter filter = filters.get(pointer++);
        return filter.doFilter(request, response, filterChain);

    }
}


class Request{
    String str;
}
class Response{
    String str;
}




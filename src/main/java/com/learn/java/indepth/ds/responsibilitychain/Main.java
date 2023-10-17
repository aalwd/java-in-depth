package com.learn.java.indepth.ds.responsibilitychain;

import java.util.ArrayList;

/**
 * 责任链模式 :
 * 相对于对某类对象, 进行一系列相同操作的时候, 将这些操作, 并且这些操作可能进行扩展的时候 ,可以使用此设计模式
 * 声明一个抽象接口, 所有类都实现你需要实现的抽象操作,
 * 然后定义一个Chain类, 这个Chain类也需要实现这个接口, 因为他是一个调用链, 间接代理了内部List中所有对象执行相同操作
 * 同时也添加自身类型加入到List中, 然后遍历调用多层实现
 */
public class Main {
    public static void main(String[] args) {
        Msg msg = new Msg();
        msg.setMsg("大家好:)，<script>，欢迎访问 java-in-depth.com ，大家都是996");

        FilterChain fc = new FilterChain();
        fc
                .addFilter(new HttpMessageFilter())
                .addFilter(new SensitiveMessageFilter());
        FilterChain fc2 = new FilterChain();
        fc2
                .addFilter(new FaceFilter())
                .addFilter(new URLFilter());

        fc.addFilter(fc2);
        fc.doFilter(msg);
        System.out.println(msg);


    }
}

class Msg {
    String name;
    String msg;

    public String getMsg() {
        return msg;
    }

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
    boolean doFilter(Msg m);
}

class HttpMessageFilter implements Filter {
    @Override
    public boolean doFilter(Msg m) {
        String r = m.getMsg();
        r = r.replace("<", "[");
        r =r.replace(">", "]");
        m.setMsg(r);
        return true;
    }
}
class FaceFilter implements Filter {
    @Override
    public boolean doFilter(Msg m) {
        String r = m.getMsg();
        r = r.replace(":)", "^C^");
        m.setMsg(r);
        return false;
    }
}

class URLFilter implements Filter {
    @Override
    public boolean doFilter(Msg m) {
        String r = m.getMsg();
        r = r.replace("java-in-depth.com", "https://java-in-depth.com");
        m.setMsg(r);
        return true;
    }
}


class SensitiveMessageFilter implements Filter {
    @Override
    public boolean doFilter(Msg m) {
        String r = m.getMsg();
        r = r.replaceAll("996", "955");
        m.setMsg(r);
        return true;
    }
}

class FilterChain implements Filter{
    ArrayList<Filter> filters = new ArrayList<>();

    public FilterChain addFilter(Filter filter) {
        filters.add(filter);
        return this;
    }

    // 只要责任链中, 有一个失败, 就停止, 代表全部失败
    public boolean doFilter(Msg m) {
        for (Filter filter : filters) {
            if (!filter.doFilter(m)) {
                return false;
            }
        }
        return true;
    }
}



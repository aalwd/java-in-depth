package com.learn.java.indepth.ds.singleton;

import java.io.*;

public class InnerClassSingletonTest {
    /**
     * 验证反序列化破坏单例
     * 但是通过实现Serializable, 并添加特殊的读取方式readResolve
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String fileName = "text";
        ObjectOutputStream text = new ObjectOutputStream(new FileOutputStream(fileName));
        InnerClassSingleton currentObj = InnerClassSingleton.getInstance();
        text.writeObject(currentObj);
        text.close();
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName));
        Object object = objectInputStream.readObject();
        System.out.println(object == currentObj);


    }
}

class InnerClassSingleton implements Serializable {
    private static final long serialVersionUID = 42L;

    public static String name = "wang";

    /**
     * 防止反射实例化
     */
    private InnerClassSingleton() {
        if(InnerClassSingleton.getInstance() != null) {
            throw new RuntimeException("实例已经存在");
        }
    }



    public static InnerClassSingleton getInstance() {
        return InnerClassSingletonHolder.INSTANCE;
    }

    static class InnerClassSingletonHolder {
        public static final InnerClassSingleton INSTANCE = new InnerClassSingleton();
    }

    /**
     * 防止读取序列化的时候, 读取数据产生新的实例
     * 即防止破坏数据
     * @return
     * @throws ObjectStreamException
     */
    private Object readResolve() throws ObjectStreamException {
        return InnerClassSingleton.getInstance();
    }
}


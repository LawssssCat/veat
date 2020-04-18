package test;

import com.sun.javafx.util.WeakReferenceQueue;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/**
 * @author alan smith
 * @version 1.0
 * @date 2020/4/13 8:46
 */
public class Referrence {
    public static void main(String[] args) {
        Object key = new Object();
        // WeakHashMap 的弱引用对象是key，而value是强引用存储在WeakHashMap内部
        WeakHashMap<Object, Object> map = new WeakHashMap<>();
        map.put(key, "haha");
        System.out.println("obj：" + map.get(key));
        System.out.println("size：" + map.size());
        System.gc();
        System.out.println("obj：" + map.get(key) + "（gc后）");
        System.out.println("size：" + map.size());
        // 清除强引用
        key = null;
        // 调用gc清除弱引用
        System.gc();
        System.out.println("obj：" + map.get(key) + "（gc后，obj=null后）");
        System.out.println("size：" + map.size());
    }
}

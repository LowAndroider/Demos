package com.exam.basecomponent.util;

/**
 * @author LowAndroider
 * @date 2018/9/26
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 遍历的包装对象
 * @param <T> list中的item类型
 */
public class Foreach<T> {

    /**
     * 转换操作接口
     * @param <T> 转换前的item类型
     * @param <R> 转换后的item类型
     */
    public interface Action<T, R> {
        /**
         * 转换的具体操作
         * @param t 转换前的item
         * @return  转换后的item集合
         */
        List<R> action(T t);
    }

    /**
     * 对当前的list进行遍历
     * @param <T> 当前item的类型
     */
    public interface Action2<T> {
        /**
         * 遍历操作
         * @param t 当前的列表中的item
         */
        void action(T t);
    }

    /**
     * 过滤接口
     * @param <T>
     */
    public interface Filter<T> {
        /**
         * 过滤的具体操作
         * @param t 列表中的单个item对象
         * @return false即被过滤
         */
        boolean filt(T t);
    }

    private List<T> data;

    public void setData(List<T> data) {
        this.data = data;
    }

    /**
     * 生成一个包装iterable的包装对象
     * @param iterator 传进来的数据
     * @param <T>   iterator的遍历子类型
     * @return  返回一个包装对象
     */
    public static <T> Foreach<T> create(Iterable<T> iterator) {
        List<T> data = new ArrayList<>();
        for (T t : iterator) {
            data.add(t);
        }

        Foreach<T> ts = new Foreach<>();
        ts.setData(data);

        return ts;
    }

    /**
     * 转换操作
     * @param action 转换的操作接口
     * @param <R>   转换后的item类型
     * @return 返回一个转换后的包装对象
     */
    public <R> Foreach<R> map(Action<T, R> action) {
        List<R> dr = new ArrayList<>();

        for (T t : data) {
            if (action != null) {
                List<R> ls = action.action(t);
                dr.addAll(ls);
            }
        }

        return Foreach.create(dr);
    }

    /**
     * 遍历操作
     * @param action 遍历的操作接口
     */
    public void map(Action2<T> action) {
        if (action != null) {
            for (T t : data) {
                action.action(t);
            }
        }
    }

    /**
     * 过滤
     * @param filter 过滤的具体操作
     * @return 返回过滤后的包装对象
     */
    public Foreach<T> filter(Filter<T> filter) {
        if (filter != null) {
            Iterator<T> it = data.iterator();
            while (it.hasNext()) {
                T t = it.next();
                if (!filter.filt(t)) {
                    it.remove();
                }
            }
        }
        return this;
    }
}
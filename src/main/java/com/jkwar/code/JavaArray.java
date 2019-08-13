package com.jkwar.code;

import java.util.stream.IntStream;

/**
 * @author zph
 * @date 2019年8月12日14:58:43
 */
public class JavaArray<E> {
    private E[] data;
    private int size;

    /**
     * 指定数组的初始容量
     *
     * @param capacity 容量
     */
    public JavaArray(int capacity) {
        this.data = (E[]) new Object[capacity];
        this.size = 0;
    }

    /**
     * 指定数组初始容量为10
     */
    public JavaArray() {
        this(10);
    }

    /**
     * @return 获取数组容量
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * @return 判断数组是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 添加数据
     *
     * @param index 下标
     * @param e     值
     */
    public void add(int index, E e) {
        //边界判断
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add failed, index > 0 and index < size");
        }
        //将 index 到 size 这部分的元素全部向后挪一位
        //时间复杂度分析：
        //1.最好的情况是在数组尾部插入数据，就不需要挪动数据，时间复杂度为 O(1)
        //2.最坏的情况是在数组头部插入数据，就需要将所有时间想后挪动一位，时间复杂度为 O(n)
        //3.平均的情况是在每个位置插入数据的概率一样，时间复杂度为 (1+2+3+4+...+n)/n = n
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    /**
     * 在头部添加数据
     *
     * @param e 值
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 在尾部添加数据
     *
     * @param e 值
     */
    public void addLast(E e) {
        add(size, e);
    }


    /**
     * 删除元素
     *
     * @param index 下标
     */
    public E remove(int index) {
        //边界判断
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("remove failed, index > 0 and index < size");
        }
        E ret = data[index];
        //将 index 到 size 这部分的元素全部向前挪一位，相当于删除了 index 坐标的元素
        for (int i = index + 1; i < size; i++) {
            //index + 1 防止数组越界
            data[i - 1] = data[i];
        }
        size--;
        //将空出来的下标置为null
        data[size] = null;
        return ret;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }


    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0; i < size; i++) {
            if (null != data[i]) {
                res.append(data[i]);
                if (i != size - 1) {
                    res.append("->");
                }
            }
        }
        res.append("]");
        return res.toString();
    }

    public static void main(String[] args) {
        JavaArray<Integer> javaArray = new JavaArray<>(20);
        IntStream.range(0, 10).forEach(javaArray::addFirst);
        System.out.println(javaArray);
        javaArray.addFirst(10);
        System.out.println(javaArray);
        javaArray.addLast(20);
        System.out.println(javaArray);
        javaArray.removeFirst();
        System.out.println(javaArray);
        javaArray.removeLast();
        System.out.println(javaArray);
    }

}

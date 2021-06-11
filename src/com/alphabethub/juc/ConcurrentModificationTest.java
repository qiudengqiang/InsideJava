package com.alphabethub.juc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 并发修改异常演示
 */
public class ConcurrentModificationTest {
    public static void main(String[] args) {

        List<Integer> list = new CopyOnWriteArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);


        for (Integer integer : list) {
            if (3 == integer) {
                list.remove(2);
            }
        }
        System.out.println(Arrays.toString(list.stream().toArray()));
    }
}

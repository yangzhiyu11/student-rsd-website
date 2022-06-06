package com.rsd.servlet;

import java.util.ArrayList;
import java.util.List;

public class Test1 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        list.add(7);
        list.add(8);
        list.add(9);

        String[] ss = null;

        ss = new String[list.size()];
        for (int i = 0; i < list.size();i++){
            ss[i]=list.get(i).toString();
        }

        for (int i =0;i< ss.length;i++) {
            System.out.println(ss[i]);
        }
    }
}

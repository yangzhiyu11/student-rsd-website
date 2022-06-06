package com.rsd.day3;

import com.rsd.bean.SysFiles;

import java.util.List;

public class Test2 {
    public static void main(String[] args) {
        Bus bus = new Bus();
        bus.setName("五菱");
        bus.setPrice(100000);

        IService service = new ServiceImpl();
//        service.insert(bus);
//        List<Bus> list = service.queryList();
//        System.out.println(list);
        service.delete(1);
    }
}

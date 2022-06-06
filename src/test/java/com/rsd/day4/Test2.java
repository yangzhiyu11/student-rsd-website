package com.rsd.day4;

import com.rsd.service.IEducationService;
import com.rsd.service.impl.EducationServiceImpl;

public class Test2 {
    public static void main(String[] args) {
        IEducationService iEducationService = new EducationServiceImpl();

        iEducationService.queryList();
        iEducationService.queryList();
        iEducationService.queryList();
        iEducationService.queryList();
        iEducationService.queryList();
        iEducationService.queryList();
    }
}

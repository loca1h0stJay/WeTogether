package com.we.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceTest {
    @Autowired
    Student student;
    public void heihei(){
        student.setAge("123");
        System.out.println(student.getAge());
    }

}

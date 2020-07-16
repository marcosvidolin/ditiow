package com.vidolima.ditiow.entity;

import com.vidolima.ditiow.annotation.ResponseResource;

public class Person {
	 
    @ResponseResource(value = String.class)
    public void init() {
        System.out.println("something");
    }
}

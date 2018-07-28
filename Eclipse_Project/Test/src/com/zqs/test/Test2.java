package com.zqs.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test2 {

	public static void main(String[] args) {

        /**
         *  ArrayList    public <T> T[] toArray(T[] a) {  
         *		1. 采用的是数据拷贝方式
         *      2. 返回值也是a数组
         *  public Object[] toArray() {
         *  	1. Arrays.copyOf(elementData, size);
         */
		List<Object> list = new ArrayList<>();
		list.add("name");
		list.add(22);
		Object[] objects = list.toArray();
		for(Object item: objects) {
			System.out.println(item);
		}
		
		List<String> list2 = new ArrayList<>();
		list2.add("name");
		list2.add("22");
		String[] strings = new String[list2.size()];
		list2.toArray(strings);
		for(String item: strings) {
			System.out.println(item);
		}
	}
}

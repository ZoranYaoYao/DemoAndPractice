package com.zqs.test;

public class Test {
	public static void main(String[] args) {
//		Child child = new Child();
//		child.name= "BB";
//		child.age = 18;
//		child.printName();
		
		String[] arr1 = {"1"};
		Object[] objects = arr1; //向上转型
		for(Object item: objects) {
			System.out.println(item);
		}
		
		Object[] object = {"1"};
		String[] arr2 = (String[]) object;  //ClassCastException [Ljava.lang.Object; cannot be cast to [Ljava.lang.String;
		for(Object item: object) {			//本来就是Object对象, 就不能向下转型
			System.out.println(item);
		}
		
	}
	
}

class Parent {
	public String name;
	public int age;
	
	public void printName() {
		System.out.println(name);
		printAge();
	}
	
	public void printAge() {
		System.out.println(age);
	}
}

class Child extends Parent {
	public String name;
	public int age;
	
	public void printAge() {
		System.out.println(age);
	}
	
	
}


import java.util.Scanner;

import two.Animal;

/**
 * 	https://www.cnblogs.com/szlbm/p/5504649.html
 * 
 * */
public class Test extends Animal{
	

	public static void main(String[] args) throws CloneNotSupportedException {
		//protected 访问修饰符修辞方法,变量时 定义:
		//  1.在同一包中的类都可以访问类的方法
		//  2.在不同包中,有继承关系的子类可以访问父类的方法
		Animal animal = new Animal();
		animal.name = "cc";
		animal.protectedM1();
		
		//如果是{实例,对象}调用方法,该实例必须是访问类的子类实例,并且方法是protected或者 public
		/**
		 * 1.因为test调用自己的protectedM1();
		 * 2.由于Test继承Animal方法
		 * */
		Test test = new Test();
		test.clone();
		test.name = "bb";
		test.protectedM1();
		
	}
	
//	protected void protectedM1() {
//		super.protectedM1();
//		System.out.println("Test protectedM1");
//	}
//	
}






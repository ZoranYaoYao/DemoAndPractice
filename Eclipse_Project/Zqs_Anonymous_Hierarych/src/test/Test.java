package test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import test.HttpClient.ResponseCallback;

/*
 *rebase: http://blog.csdn.net/jiaotuwoaini/article/details/51542059 
 *
 *反编译文件可以看出 AnonymousClassTest$1  implement Runnable  
 **/
public  class Test {

	public static void main(String[] args) {
			new HttpClient.ResponseCallback<Student>() {

				@Override
				public void onResponse(Student response) {
					// TODO Auto-generated method stub
					
				}
			};
			
			ResponseCallback<Student> responseCallback = new ResponseCallback<Student>() {
				
				@Override
				public void onResponse(Student response) {
					// TODO Auto-generated method stub
					
				}
			};
			
			Type type = responseCallback.type;
			
			Person person = new Person<Student>() {

				@Override
				public void method1() {
					// TODO Auto-generated method stub
					
				}
			};
			//person对象 test.Test$3@48cf768c 是Test类里面的匿名内部类
			//由于匿名内部类实现父类接口或abstract方法！ 继承关系： test.Test$3 -> test.Person -> Object
			//所以输出是type2 = test.Person<test.Student>
			Type type2 = person.getClass().getGenericSuperclass();
			System.out.println("type2 = " + type2);
			
			//student对象 test.Student@5ccd43c2 是Student类的一个实体变量
			//类对象的父类通过继承关系来看： test.Student -> Object
			//type3 = class java.lang.Object
			Student student = new Student();
			Type type3 = student.getClass().getGenericSuperclass();
			System.out.println("type3 = " + type3);
	}


}


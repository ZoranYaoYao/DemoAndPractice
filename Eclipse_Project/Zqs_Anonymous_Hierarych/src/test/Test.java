package test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import test.HttpClient.ResponseCallback;

/*
 *rebase: http://blog.csdn.net/jiaotuwoaini/article/details/51542059 
 *
 *�������ļ����Կ��� AnonymousClassTest$1  implement Runnable  
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
			//person���� test.Test$3@48cf768c ��Test������������ڲ���
			//���������ڲ���ʵ�ָ���ӿڻ�abstract������ �̳й�ϵ�� test.Test$3 -> test.Person -> Object
			//���������type2 = test.Person<test.Student>
			Type type2 = person.getClass().getGenericSuperclass();
			System.out.println("type2 = " + type2);
			
			//student���� test.Student@5ccd43c2 ��Student���һ��ʵ�����
			//�����ĸ���ͨ���̳й�ϵ������ test.Student -> Object
			//type3 = class java.lang.Object
			Student student = new Student();
			Type type3 = student.getClass().getGenericSuperclass();
			System.out.println("type3 = " + type3);
	}


}


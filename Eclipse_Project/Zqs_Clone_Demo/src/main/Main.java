package main;

/**
 * http://www.importnew.com/22035.html 
 * */
public class Main {
	public static void main(String[] args) throws CloneNotSupportedException {
		Person person = new Person(23, "zsy",new Body(),new Money());
		Person person2 = (Person) person.clone();
		
		//clone Body浅拷贝  Money深拷贝
		System.out.println(person.toString());
		System.out.println(person2.toString());
	}
}

/**
 *1.对象无论实现浅拷贝,还是深拷贝 , 类都要实现 cloneable接口
 *     不然报错 :The method clone() from the type Object is not visible
 * */
class Person implements Cloneable{
	private int age;
	private String name;
	private Body body;
	private Money money;
	
	public Person(int age, String name,Body body,Money money) {
		super();
		this.age = age;
		this.name = name;
		this.body = body;
		this.money = money;
	}
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		Person person =  (Person) super.clone();
		person.money = (Money) money.clone();
		return person;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Person [name=" + name + ", age=" + age + ",body=" + body + ",money=" + money +  "]";
	}
}

class Body{
	
}

class Money implements Cloneable{
	float count = 200f;
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
}



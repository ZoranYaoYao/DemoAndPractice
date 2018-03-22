package two;




public class  Animal implements Cloneable {
	protected String name;
//	@Override
//	protected void eat() throws Exception {
//		// TODO Auto-generated method stub
//		super.clone();
//	}
	
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
	protected void protectedM1() {
		System.out.println("Animal protectedM1");
	}
}

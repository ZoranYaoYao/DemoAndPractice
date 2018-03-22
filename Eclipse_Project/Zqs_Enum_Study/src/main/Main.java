package main;

/**
 * https://www.cnblogs.com/hyl8218/p/5088287.html
 * 
 * 1. 测试 enum 默认的 name, ordinal(序列值)
 * */
public class Main {
	public static void main(String[] args) {
		//1. name 默认为枚举值   ordinal默认从0开始
		for(Week week : Week.values()) {
			System.out.println("week name = " + week.name());
			System.out.println("week ordinal = " + week.ordinal());
			//2. 扩展Enum的值 并进行初始化
			System.out.println("week isWork = " + week.isWork());
		}
	}
}

enum Week {
	MON(true),TUE(true),WED(true),THU(true),FRI(true),SAT(false),SUN(false);
	
	boolean isWork = false;
	
	Week(Boolean isWork) {
		this.isWork = isWork;
	}
	
	public boolean isWork() {
		return isWork;
	}
}
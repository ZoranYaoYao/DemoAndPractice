package com.dida.zqs;
/**
 * https://baike.baidu.com/item/%E5%86%92%E6%B3%A1%E6%8E%92%E5%BA%8F/4602306?fr=aladdin
 * 
 * */
public class PopoAlgorthm {

	// 8, 66, 103, 22, 4, 19
	//output : 8,  22,  4,  19,  66,  103, 
	// Root Case: 前面的数字如果从i++,出发的话，双重循环将不会对前面的数进行排序！
	public static void sort(int[] numbers) {
		for(int i =0; i < numbers.length; i++) {
			for (int j = i; j+1 < numbers.length; j++) {
				int number1 = numbers[j];
				int number2 = numbers[j+1];
				if(number1 > number2) {
					int tmp = numbers[j+1];  
					numbers[j+1] = numbers[j];
					numbers[j] = tmp;              
				}
			}
		}

		for(int i =0; i < numbers.length; i++) {
			System.out.print(numbers[i]+",  ");
		}
	}
	
	// 	   8, 66, 103, 22, 4, 19
	//                           <- i--   i=5
	//j=0 ->j++
	// (1) 8, 66, 22, 4 , 19, 103   i= 5,j=0
	// (2) 8, 22, 4 , 19, 66, 103   i= 4,j=0
	// (3) 8,  4, 19, 22, 66, 103   i= 3,j=0
	// (4) 4,  8, 19, 22, 66, 103   i= 2,j=0
	// (5) 4,  8, 19, 22, 66, 103   i= 1,j=0
	/**
	 * 冒泡排序： 如果有N个元素进行排序
	 * 		1. 就是鱼吐N次水泡              										i=n , i < length -1(because j+1< i)
	 * 		2. 每次水泡都是从开始位置进行冒泡，相邻元素进行对比				j=0 ,j,j+1
	 * 		3. 每冒一次泡，每行的对比次数 -1. 								i--, j< i (控制j的次数!)
	 * 
	 * 时间复杂度： O(n2)   所以是双重循环
	 */
	public static void bubbleSort(int[] numbers){
		for(int i =numbers.length -1; i>0;i--) {
			for(int j =0; j<i; j++) {
				if(numbers[j] > numbers[j+1]) {
					int tmp = numbers[j];
					numbers[j] = numbers[j+1];
					numbers[j+1] = tmp;
				}
			}
		}
		
		for(int i =0; i < numbers.length; i++) {
			System.out.print(numbers[i]+",  ");
		}
	}
}

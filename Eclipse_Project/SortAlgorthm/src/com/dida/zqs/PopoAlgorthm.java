package com.dida.zqs;
/**
 * https://baike.baidu.com/item/%E5%86%92%E6%B3%A1%E6%8E%92%E5%BA%8F/4602306?fr=aladdin
 * 
 * */
public class PopoAlgorthm {

	// 8, 66, 103, 22, 4, 19
	//output : 8,  22,  4,  19,  66,  103, 
	// Root Case: ǰ������������i++,�����Ļ���˫��ѭ���������ǰ�������������
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
	 * ð������ �����N��Ԫ�ؽ�������
	 * 		1. ��������N��ˮ��              										i=n , i < length -1(because j+1< i)
	 * 		2. ÿ��ˮ�ݶ��Ǵӿ�ʼλ�ý���ð�ݣ�����Ԫ�ؽ��жԱ�				j=0 ,j,j+1
	 * 		3. ÿðһ���ݣ�ÿ�еĶԱȴ��� -1. 								i--, j< i (����j�Ĵ���!)
	 * 
	 * ʱ�临�Ӷȣ� O(n2)   ������˫��ѭ��
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

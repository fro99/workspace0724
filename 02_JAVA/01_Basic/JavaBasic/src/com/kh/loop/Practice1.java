package com.kh.loop;

public class Practice1 {

	public static void main(String[] args) {
		for(int i=1; i<=100; i++) {
			
			//7의 배수가 아닐 때 건너뜀
			if(i%7 != 0){
				continue;
			}
			
			if(i%2 ==0) {
				continue;
			}
			
			System.out.print(i+" ");
		}

	}

}

package com.kh.loop;

import java.util.Scanner;

public class Practice2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int sum =0;
		
		while(true) {
			System.out.print("숫자입력 : ");
			int num = sc.nextInt();
			
			if(num <0) {
				break;
		
				
			}
			
			sum += num;
		}
		
		
		System.out.println("총합 : "+sum);
		

	}

}

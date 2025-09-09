package com.kh.pracetice.array;

import java.util.Scanner;

public class Practice2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] iArr = new int[n];
		
		for(int i=0; i<n; i++) {
			iArr[i] = sc.nextInt();
		}
		//최솟값과 최댓값 확인
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		
		for(int num : iArr) {
			if(num<min) min = num;
		}
		
		System.out.print(min+" "+ max);

	}

}

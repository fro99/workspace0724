package com.kh.method;

public class MethodLocalVariable {
	/*
	 * 지역변수
	 * - 블록({}) 내부에서 선언된 변수
	 * - 해당 블록을 벗어나면 메모리에서 제거됨.
	 * - 선언시 반드시 초기화 후 사용해야함.
	 *
	 * 매개변수
	 * - 메소드 호출 외부에서 전달받는 값을 저장하는 지역변수
	 * - 메소드 선언부에 정의됨
	 * - 메소드 실행시 생성되고, 메소드 종료시 사라짐.
	 */


	public static void main(String[] args) {
		// main의 지역변수로
	    // main이 종료되면 사라짐.
	    String name = "철수";
	    int age = 20;
	    
	    say(name,age);
	    if(name.equals("철수")) {
//	    	String name = "지원"; 포함되는 관계는 같은 지역이므로 동일한 이름을 사용할 수 없음
	    }
	}
	
	public static void say(String name, int age) {
		name = "지원"
	};

}

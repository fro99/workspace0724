package com.kh.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Scanner;

public class TCPServer {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		BufferedReader br = null;
		
		//1)포트전호 지정
		int port = 3000;
		
		try {
			//2)serverSocket객체 생성(port와 결합(bind))
			ServerSocket server = new ServerSocket(port);
			
			System.out.println("클라이언트 요청을 기다립니다.");
			Socket socket = server.accept();
			InetAddress clientIp = socket.getIneAddress();
			System.out.println(clientIp.getHostAddress()+"가 연결을 요청함...");
			
			//입력용 스트림(클라이언트로부터 전달된 값을 한줄단뒤로 읽기위한 스트림)
			br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			
			
		}catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Socket socket = null;
        BufferedReader br = null;
        PrintWriter pw = null;

        try {
            // 서버의 IP 주소와 포트 번호
            String serverIP = "192.168.10.147";  // 서버 IP (실행 중인 PC의 IPv4)
            int port = 3000;

            // 서버에 연결 시도
            socket = new Socket(serverIP, port);
            System.out.println("서버(" + serverIP + ")에 연결되었습니다.");

            // 입출력 스트림
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            pw = new PrintWriter(socket.getOutputStream(), true);

            // 메시지 주고받기
            while (true) {
                System.out.print("클라이언트에서 보낼 내용 : ");
                String msg = sc.nextLine();

                // 서버로 전송
                pw.println(msg);

                if (msg.equalsIgnoreCase("exit")) {
                    System.out.println("클라이언트 종료.");
                    break;
                }

                // 서버에서 받은 메시지 출력
                String serverMsg = br.readLine();
                System.out.println("서버로부터 전달받은 메시지 : " + serverMsg);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
                if (pw != null) pw.close();
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

package com.kh.example.gearrent;

import java.time.LocalDate;
import java.util.*;

public class GearRentMenu {
    private GearRentController gc = new GearRentController();
    private Scanner sc = new Scanner(System.in);

    public void mainMenu() {
        while (true) {
            System.out.println("\n=== KH 장비 대여 관리 프로그램 ===");
            System.out.printf("1) 장비등록  ");
            System.out.printf("2) 회원등록  ");
            System.out.printf("3) 대여  ");
            System.out.printf("4) 반납  ");
            System.out.println("5) 태그검색");
            System.out.printf("6) 키워드검색  ");
            System.out.printf("7) 전체장비  ");
            System.out.printf("8) 대여중목록  ");
            System.out.println("9) 종료");
            System.out.print("메뉴: ");
            int choice = Integer.parseInt(sc.nextLine());

            switch (choice) {
                case 1:
                	addDevice(); 
                	break;
                case 2: 
                	addMember(); 
                	break;
                case 3: 
                	borrow(); 
                	break;
                case 4: 
                	returnItem(); 
                	break;
                case 5: 
                	findByTag(); 
                	break;
                case 6: 
                	findByKeyword(); 
                	break;
                case 7: 
                	printAllDevices(); 
                	break;
                case 8: 
                	printActiveLoans(); 
                	break;
                case 9: 
                	System.out.println("종료합니다."); 
                	return;
                default: 
                	System.out.println("잘못 입력하였습니다. 다시 입력해주세요.");
            }
        }
    }

    private void addDevice() {
        System.out.print("유형(1:Camera, 2:Laptop): ");
        int type = Integer.parseInt(sc.nextLine());
        System.out.print("id: ");
        String id = sc.nextLine();
        System.out.print("name: ");
        String name = sc.nextLine();
        System.out.printf("category: ");
        String category = sc.nextLine();
        System.out.print("tage(쉼표로 구분): ");
        String tagStr = sc.nextLine();
        Set<String> tags = new HashSet<>();
        if (!tagStr.trim().isEmpty()) {
            for (String t : tagStr.split(",")) {
                tags.add(t.trim());
            }
        }
        Device device = null;
        if (type == 1) device = new Camera(id, name, tags);
        else if (type == 2) device = new Laptop(id, name, tags);
        else {
            System.out.println("잘못 입력하였습니다. 다시 입력해주세요");
            return;
        }
        boolean success = gc.addDevice(device);
        if (success) System.out.println("등록 완료");
        else System.out.println("중복된 ID입니다. 다시 입력해주세요.");
    }

    private void addMember() {
        System.out.print("member id: ");
        String id = sc.nextLine();
        System.out.print("name: ");
        String name = sc.nextLine();
        boolean success = gc.addMember(new Member(id, name));
        if (success) System.out.println("가입 완료");
        else System.out.println("중복된 ID입니다. 다시 입력해주세요.");
    }

    private void borrow() {
        System.out.print("memberId: ");
        String memberId = sc.nextLine();
        System.out.print("itemId: ");
        String itemId = sc.nextLine();
        System.out.print("대여일(YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(sc.nextLine());
        Loan loan = gc.borrow(memberId, itemId, date);
        if (loan != null) {
            System.out.println("대여 완료: " + loan);
            System.out.println("반납 예정일: " + loan.getDueDate());
        }
    }
   
  
    private void returnItem() {
        System.out.print("장비 ID: ");
        String itemId = sc.nextLine();
        System.out.print("반납일(YYYY-MM-DD): ");
        LocalDate date = LocalDate.parse(sc.nextLine());
        int fee = gc.returnItem(itemId, date);
        if (fee >= 0) {
            System.out.println("반납 완료.연체료: " + fee + "원");
        }
    }
    
	private void findByTag() {
        System.out.print("검색 태그: ");
        String tag = sc.nextLine();
        List<Device> list = gc.findByTag(tag);
        if (list.isEmpty()) System.out.println("결과 없음");
        else list.forEach(System.out::println);
    }
	 

    private void findByKeyword() {
        System.out.print("검색할 키워드: ");
        String keyword = sc.nextLine();
        List<Device> list = gc.findByKeyword(keyword);
        if (list.isEmpty()) System.out.println("조회 결과 없음");
        else list.forEach(System.out::println);
    }

    private void printAllDevices() {
        for (Device d : gc.getAllDevices()) {
            System.out.println(d);
        }
    }

    private void printActiveLoans() {
        for (Loan l : gc.getActiveLoans()) {
            System.out.println(" - "+l);
        }
    }
}
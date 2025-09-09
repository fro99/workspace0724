package com.kh.example.inherit2;

import java.util.Scanner;

public class PersonMenu {
    private Scanner sc = new Scanner(System.in);
    private PersonController pc = new PersonController();

    public void mainMenu() {
        while (true) {
            int[] count = pc.personCount();
            System.out.println("학생은 최대 3명까지 저장할 수 있습니다.");
            System.out.println("현재 저장된 학생은 " + count[0] + "명입니다.");
            System.out.println("사원은 최대 10명까지 저장할 수 있습니다.");
            System.out.println("현재 저장된 사원은 " + count[1] + "명입니다.");
            System.out.println();
            System.out.println("1. 학생 메뉴");
            System.out.println("2. 사원 메뉴");
            System.out.println("9. 끝내기");
            System.out.print("메뉴 번호 : ");

            int menu = sc.nextInt();
            sc.nextLine();

            switch (menu) {
                case 1:
                    studentMenu();
                    break;
                case 2:
                    employeeMenu();
                    break;
                case 9:
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못 입력하셨습니다.");
            }
        }
    }

    public void studentMenu() {
        while (true) {
            System.out.println("1. 학생 추가");
            System.out.println("2. 학생 보기");
            System.out.println("9. 메인으로");
            System.out.print("메뉴 번호 : ");
            int menu = sc.nextInt();
            sc.nextLine();

            switch (menu) {
                case 1:
                    insertStudent();
                    break;
                case 2:
                    printStudent();
                    break;
                case 9:
                    return;
                default:
                    System.out.println("잘못 입력하셨습니다.");
            }
        }
    }

    public void employeeMenu() {
        while (true) {
            System.out.println("1. 사원 추가");
            System.out.println("2. 사원 보기");
            System.out.println("9. 메인으로");
            System.out.print("메뉴 번호 : ");
            int menu = sc.nextInt();
            sc.nextLine();

            switch (menu) {
                case 1:
                    insertEmployee();
                    break;
                case 2:
                    printEmployee();
                    break;
                case 9:
                    return;
                default:
                    System.out.println("잘못 입력하셨습니다.");
            }
        }
    }

    public void insertStudent() {
        int[] count = pc.personCount();
        if (count[0] >= 3) {
            System.out.println("학생을 담을 수 있는 공간이 꽉 찼습니다.");
            return;
        }

        while (true) {
            System.out.print("학생 이름 : ");
            String name = sc.nextLine();
            System.out.print("학생 나이 : ");
            int age = sc.nextInt();
            System.out.print("학생 키 : ");
            double height = sc.nextDouble();
            System.out.print("학생 몸무게 : ");
            double weight = sc.nextDouble();
            System.out.print("학생 학년 : ");
            int grade = sc.nextInt();
            sc.nextLine();
            System.out.print("학생 전공 : ");
            String major = sc.nextLine();

            pc.insertStudent(name, age, height, weight, grade, major);

            count = pc.personCount();
            if (count[0] >= 3) {
                System.out.println("학생을 담을 수 있는 공간이 꽉 찼기 때문에 추가를 종료합니다.");
                return;
            }

            System.out.print("그만하시려면 N(또는 n), 이어하시려면 아무 키나 누르세요 : ");
            String cont = sc.nextLine();
            if (cont.equalsIgnoreCase("n")) break;
        }
    }

    public void printStudent() {
        Student[] s = pc.printStudent();
        for (Student st : s) {
            if (st != null) {
                System.out.println(st);
            }
        }
    }

    public void insertEmployee() {
        int[] count = pc.personCount();
        if (count[1] >= 10) {
            System.out.println("사원을 담을 수 있는 공간이 꽉 찼습니다.");
            return;
        }

        while (true) {
            System.out.print("사원 이름 : ");
            String name = sc.nextLine();
            System.out.print("사원 나이 : ");
            int age = sc.nextInt();
            System.out.print("사원 키 : ");
            double height = sc.nextDouble();
            System.out.print("사원 몸무게 : ");
            double weight = sc.nextDouble();
            System.out.print("사원 급여 : ");
            int salary = sc.nextInt();
            sc.nextLine();
            System.out.print("사원 부서 : ");
            String dept = sc.nextLine();

            pc.insertEmployee(name, age, height, weight, salary, dept);

            count = pc.personCount();
            if (count[1] >= 10) {
                System.out.println("사원을 담을 수 있는 공간이 꽉 찼기 때문에 추가를 종료합니다.");
                return;
            }

            System.out.print("그만하시려면 N(또는 n), 이어하시려면 아무 키나 누르세요 : ");
            String cont = sc.nextLine();
            if (cont.equalsIgnoreCase("n")) break;
        }
    }

    public void printEmployee() {
        Employee[] e = pc.printEmployee();
        for (Employee em : e) {
            if (em != null) {
                System.out.println(em);
            }
        }
    }
}

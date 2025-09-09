// MusicView
public class MusicView {
    // Scanner, MusicController 생성

    public void mainMenu() {
    	  int menu = 0;

          do {
              System.out.println("\n***** 메인 메뉴 *****");
              System.out.println("1. 마지막 위치에 곡 추가");
              System.out.println("2. 첫 위치에 곡 추가");
              System.out.println("3. 전체 곡 목록 출력");
              System.out.println("4. 특정 곡 검색");
              System.out.println("5. 특정 곡 삭제");
              System.out.println("6. 특정 곡 정보 수정");
              System.out.println("7. 곡명 오름차순 정렬");
              System.out.println("8. 가수명 내림차순 정렬");
              System.out.println("9. 종료");
              System.out.print("메뉴 선택 : ");
              menu = Integer.parseInt(sc.nextLine());

              switch (menu) {
                  case 1: addList(); break;
                  case 2: addAtZero(); break;
                  case 3: printAll(); break;
                  case 4: searchMusic(); break;
                  case 5: removeMusic(); break;
                  case 6: setMusic(); break;
                  case 7: ascTitle(); break;
                  case 8: descSinger(); break;
                  case 9: System.out.println("프로그램을 종료합니다."); break;
                  default: System.out.println("잘못 입력하셨습니다.");
              }
          } while (menu != 9);
    }

    public void addList() {
    	  System.out.print("곡명 입력: ");
          String title = sc.nextLine();
          System.out.print("가수명 입력: ");
          String singer = sc.nextLine();

          int result = mc.addList(new Music(title, singer));
          System.out.println(result > 0 ? "추가 성공" : "추가 실패");
    }

    public void addAtZero() {
    	 System.out.print("곡명 입력: ");
         String title = sc.nextLine();
         System.out.print("가수명 입력: ");
         String singer = sc.nextLine();

         int result = mc.addAtZero(new Music(title, singer));
         System.out.println(result > 0 ? "추가 성공" : "추가 실패");
    }

    public void printAll() {
        // mc.printAll() 결과 목록 출력
    }

    public void searchMusic() {
        // 입력: 곡명
        // mc.searchMusic(title)
        // null이면 "검색한 곡이 없습니다."
        // 아니면 "검색한 곡은 가수 - 곡명 입니다." 출력(또는 VO toString)
    }

    public void removeMusic() {
        // 입력: 곡명
        // mc.removeMusic(title)
        // null이면 "삭제할 곡이 없습니다."
        // 아니면 "가수 - 곡명 을(를) 삭제했습니다." 출력
    }

    public void setMusic() {
        // 입력: (검색) 기존 곡명, (수정) 새 곡명, 새 가수명
        // mc.setMusic(oldTitle, new Music(newTitle, newSinger))
        // null이면 "수정할 곡이 없습니다."
        // 아니면 "가수 - 기존곡명 의 값이 변경되었습니다." 출력(또는 새 값 출력)
    }

    public void ascTitle() {
        // mc.ascTitle() 결과에 따라 "정렬 성공 / 정렬 실패"
    }

    public void descSinger() {
        // mc.descSinger() 결과에 따라 "정렬 성공 / 정렬 실패"
    }
}


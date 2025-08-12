package com.ohgiraffers.run;

import com.ohgiraffers.myTeam.dao.TeamDAO;
import com.ohgiraffers.myTeam.dto.TeamDTO;
import com.ohgiraffers.players.dao.PlayerDAO;

import java.sql.Connection;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.*;

public class GameMenu {

    private Scanner sc = new Scanner(System.in);
    private Connection con;
    private TeamDAO teamDAO = new TeamDAO();
    private TeamDTO myTeam = new TeamDTO();
    private PlayerDAO playerDAO = new PlayerDAO();


    public GameMenu(Connection con) {
        this.con = con;
    }

    public void start() {
        createTeam();   // 1. 팀 생성

        // 2. 메뉴 루프
        while (true) {
            printMenu();
            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1": buyPlayer(); break;
                case "2": swapPlayer(); break;
                case "3": removePlayer(); break;
                case "4": showMyInfo(); break;
                case "5": startGame(); break;
                case "0":
                    System.out.println("게임 종료!");
                    close(con);
                    return;
                default: System.out.println("잘못된 입력!");
            }
        }
    }

    /* 팀 생성 */
    private void createTeam() {
        System.out.println("팀 이름을 입력하세요:");
        String teamName = sc.nextLine();

        System.out.println("팀 예산을 입력하세요:");
        int budget = sc.nextInt();
        sc.nextLine();
        if (budget <= 0) {
            System.out.println("당신은 돈이 없습니다!!");
        }

        String tactics = "";
        while (true) {
            System.out.println("전술을 선택하세요 (442 / 433 / 4222):");
            tactics = sc.nextLine().trim();
            if (tactics.equals("442") || tactics.equals("433") || tactics.equals("4222")) break;
            System.out.println("유효한 전술만 입력하세요!");
        }

        myTeam = new TeamDTO(teamName, budget, tactics);
        int result = teamDAO.createTeam(myTeam, con);

        if (result > 0) {
            System.out.println("팀 생성 성공!");
        } else {
            System.out.println("팀 생성 실패!");
        }
    }

    /* 메뉴 출력 */
    private void printMenu() {
        System.out.println("\n==== 메뉴 ====");
        System.out.println("1. 선수 구매");
        System.out.println("2. 선수 교체");
        System.out.println("3. 선수 방출");
        System.out.println("4. 내 정보 조회");
        System.out.println("5. 게임 시작");
        System.out.println("0. 종료");
        System.out.print("번호 선택: ");
    }

    // ===== 메뉴 기능 메서드 =====
    private void buyPlayer() {
        List<Map<String, Object>> list = playerDAO.market(con,myTeam.getTEAM_ID());     //*선수를 구매할때 내가 보유한 선수는 제외해야 함으로 내 팀 아이디가 필요함.;
        if (list.isEmpty()) {
            System.out.println("마켓에 선수가 없습니다.")
            ;}

        System.out.println("+----+------------+---------+----------+--------------+--------+");
        System.out.println("| ID | 이름       | 포지션  |   가치   | 팀           | 기여도 |");
        System.out.println("+----+------------+---------+----------+--------------+--------+");
        for (Map<String,Object> r : list) {
            System.out.printf("| %-2d | %-10s | %-7s | %,8d | %-12s | %.2f |\n",
                    //길이2의 크기를 가진 번호 표시,문자열 왼쪽 정렬 길이 10칸,문자열 7칸,오른쪽 문자 정렬 8칸,길이 12의 숫자,소수점 2까지의 길이
                    r.get("PLAYER_ID"), r.get("PLAYER_NAME"),
                    r.get("PLAYER_POSITION"), r.get("PLAYER_VALUE"),
                    r.get("OPPONENT_NAME"), r.get("PLAYER_CONTRIBUTION"));
        }
        System.out.println("+----+------------+---------+----------+--------------+--------+");

        System.out.print("\n영입할 선수 ID: ");
        int pid = sc.nextInt();
        sc.nextLine();

        printNumberedBoard(myTeam.getTACTICS());
        System.out.print("어디에 선수를 배정할 것인지 선택해주세요.(1~11 숫자): ");
         int slot = sc.nextInt();
        sc.nextLine();

        if (slot<1 || slot>11){
            System.out.println("1부터11까지의 숫자만 선택 해 주세요!");
        }

        int rs = playerDAO.insertIntoMyTeam(con,myTeam.getTEAM_ID(),pid,slot);{
            if (rs == 1 ){
                System.out.println("선수 영입 성공!!" + slot+"에 배정되었습니다.");
            } else{
                System.out.println("등록 실패(이미 보유/위치 중복)");
            }
        }

    }

    private void swapPlayer() {
        System.out.println("[선수 교체 기능 - 구현 예정]");
    }

    private void removePlayer() {
        System.out.println("[선수 방출 기능 - 구현 예정]");
    }

    private void showMyInfo() {
        System.out.println("[내 정보 조회]");
        System.out.println("팀명: " + myTeam.getTEAM_NAME());
        System.out.println("예산: " + myTeam.getBUDGET());
        System.out.println("전술: " + myTeam.getTACTICS());
    }

    private void startGame() {
        System.out.println("[게임 시작 기능 - 구현 예정]");
    }

    private void printNumberedBoard(String tactics) {
        System.out.println();
        if ("433".equals(tactics)) {
            System.out.println("전술: 4-3-3");
            System.out.println("|============================================|");
            System.out.println("|           [ 1 ]    [ 2 ]   [ 3 ]           |       ");
            System.out.println("|                                            | ");
            System.out.println("|           [ 4 ]    [ 5 ]   [ 6 ]           | ");
        } else if ("442".equals(tactics)) {
            System.out.println("전술: 4-4-2");
            System.out.println("|=============================================|");
            System.out.println("|                [ 1 ]   [ 2 ]                |");
            System.out.println("|                                             |");
            System.out.println("|        [ 3 ]   [ 4 ]   [ 5 ]   [ 6 ]        | ");
        } else {
            System.out.println("전술: 4-2-2-2");
            System.out.println("|=============================================|");
            System.out.println("|                [ 1 ]   [ 2 ]                | ");
            System.out.println("|                                             |");
            System.out.println("|                [ 3 ]   [ 4 ]                |" );
            System.out.println("|                                             |");
            System.out.println("|                [ 5 ]   [ 6 ]                |");
        }   System.out.println("|                                             |");
            System.out.println("|       [ 7 ]    [ 8 ]   [ 9 ]   [ 10 ]       |");
        System.out.println(    "|                                             |");
            System.out.println("|                    [ 11 ]                   |");
        System.out.println(    "|=============================================|");
        System.out.println();
    }

}

package com.ohgiraffers.run;

import com.ohgiraffers.myTeam.dao.TeamDAO;

import java.sql.Connection;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application {
    public static void main(String[] args) {
        Connection con = getConnection();
        TeamDAO teamDAO = new TeamDAO();
        Scanner sc = new Scanner(System.in);

        System.out.println("팀 이름을 입력하세요:");
        String teamName = sc.nextLine();

        System.out.println("팀 예산을 입력하세요:");
        int budget = sc.nextInt();
        sc.nextLine();

        String tactics ="";
        while (true) {
            System.out.println("전술을 선택하세요 (442 / 433 / 4222):");
            tactics = sc.nextLine().trim();
            if (tactics.equals("442") || tactics.equals("433") || tactics.equals("4222")) break;
            System.out.println("유효한 전술만 입력하세요!");
        }
        int result = teamDAO.createTeam(con, teamName, budget, tactics);

        if (result > 0) {
            System.out.println("팀 생성 성공!");
        } else {
            System.out.println("팀 생성 실패!");
        }
        close(con);
    }
}

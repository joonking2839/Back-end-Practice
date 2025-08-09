package com.ohgiraffers.section.run;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("팀의 이름을 적어주세요.");
        String teamName = sc.nextLine();
        System.out.println("팀의 예산을 적어주세요.");
        int teamBudget = sc.nextInt();
        sc.nextLine();  // ← 중요!
        System.out.println("팀의 전술을 선택해주세요(4-1-4-1 / 4-1-2-3)");
        String teamTactics = sc.nextLine();

        Connection con = getConnection();
        PreparedStatement pstmt = null;
        int result = 0;
        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));
            String query = prop.getProperty("addTeam");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1, teamName);
            pstmt.setInt(2, teamBudget);
            pstmt.setString(3, teamTactics);

            result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println(" 팀 생성 성공!");
            } else {
                System.out.println("팀 생성 실패!");
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            close(con);
            close(pstmt);
        }

    }
}

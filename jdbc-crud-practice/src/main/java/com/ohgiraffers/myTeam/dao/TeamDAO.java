package com.ohgiraffers.myTeam.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.close;

public class TeamDAO {
    private final Properties prop = new Properties();

    public TeamDAO() {
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/menu-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int createTeam(Connection con, String TEAM_NAME, int BUDGET, String TACTICS) {
        PreparedStatement pstmt = null;
        int result = 0;

        String query = prop.getProperty("createTeam");

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setString(1, TEAM_NAME);
            pstmt.setInt(2, BUDGET);
            pstmt.setString(3, TACTICS);

            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(pstmt);
        }
        return result;
    }
}


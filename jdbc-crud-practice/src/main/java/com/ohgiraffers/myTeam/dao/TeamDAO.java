package com.ohgiraffers.myTeam.dao;

import com.ohgiraffers.myTeam.dto.TeamDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import static com.ohgiraffers.common.JDBCTemplate.close;

public class TeamDAO {

    private Properties prop = new Properties();

    public TeamDAO() {
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/team-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int createTeam(TeamDTO newTeam, Connection con) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String insert = prop.getProperty("createTeam");
            ps = con.prepareStatement(insert);
            ps.setString(1, newTeam.getTEAM_NAME());
            ps.setInt(2, newTeam.getBUDGET());
            ps.setString(3, newTeam.getTACTICS());
            int r = ps.executeUpdate();
            close(ps);

            if (r != 1) return 0;

            ps = con.prepareStatement(prop.getProperty("team.lastInsertId"));
            rs = ps.executeQuery();
            if (rs.next()) newTeam.setTEAM_ID(rs.getInt("TEAM_ID"));
            else return 0;

            return 1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rs);
            close(ps);
        }
    }

    public Map<String,Object> myInfo(Connection con, int TEAM_ID) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String query = prop.getProperty("team.info");

        Map<String,Object> row = new LinkedHashMap<>();
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, TEAM_ID);
            rset = pstmt.executeQuery();
            if (rset.next()) {
                row.put("TEAM_NAME", rset.getString("TEAM_NAME"));
                row.put("BUDGET", rset.getInt("BUDGET"));
                row.put("TACTICS", rset.getString("TACTICS"));
            }
            return row;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }
    }

    public void startGame(){
        PreparedStatement ps = null;
        ResultSet rs = null;
        double sum = 0.0;
    }


}

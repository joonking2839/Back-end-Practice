package com.ohgiraffers.myTeam.dao;

import com.ohgiraffers.myTeam.dto.TeamDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

            ps = con.prepareStatement(prop.getProperty("team.lastInsertId")); // SELECT LAST_INSERT_ID() AS TEAM_ID
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

}

package com.ohgiraffers.players.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static com.ohgiraffers.common.JDBCTemplate.close;

public class PlayerDAO {

    private Properties prop = new Properties();

    public PlayerDAO(){
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/team-query.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Map<String,Object>> market(Connection con, int TEAM_ID){
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String query =prop.getProperty("market");

        List<Map<String,Object>> list = new ArrayList<>();

        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1,TEAM_ID);
            rset = pstmt.executeQuery();
            while (rset.next()){
                Map<String,Object> row = new LinkedHashMap<>();
                row.put("PLAYER_ID", rset.getInt("PLAYER_ID"));
                row.put("PLAYER_NAME", rset.getString("PLAYER_NAME"));
                row.put("PLAYER_VALUE", rset.getInt("PLAYER_VALUE"));
                row.put("PLAYER_POSITION", rset.getString("PLAYER_POSITION"));
                row.put("OPPONENT_NAME", rset.getString("OPPONENT_NAME"));
                row.put("PLAYER_CONTRIBUTION", rset.getDouble("PLAYER_CONTRIBUTION"));
                list.add(row);
            }return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }
    }
    //선수 추가
    public int insertIntoMyTeam(Connection con, int TEAM_ID, int PLAYER_ID, int POSITION_SLOT ) {
        PreparedStatement ps =null;

        int result =0;

        String query = prop.getProperty("my.insert");
        try  {
            ps = con.prepareStatement(query);
            ps.setInt(1, TEAM_ID);
            ps.setInt(2, PLAYER_ID);
            ps.setInt(3, POSITION_SLOT);

            result = ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(ps);
        } return result;
    }

    // 내 선수 라인업
    public Map<Integer, String> lineup(Connection con, int TEAM_ID) {
        PreparedStatement pstmt = null;
        ResultSet rset = null;
        String query = prop.getProperty("my.lineupByTeam");

        Map<Integer, String> map = new LinkedHashMap<>();
        //linkhashmap은 순서가 잘 지켜진다.
        try {
            pstmt = con.prepareStatement(query);
            pstmt.setInt(1, TEAM_ID);
            rset = pstmt.executeQuery();
            while (rset.next()) {
                map.put(rset.getInt("SLOT"), rset.getString("PLAYER_NAME"));
            }
            return map;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(rset);
            close(pstmt);
        }
    }
    //선수 교체
    public int updatePlayer(Connection con, int TEAM_ID, int NEW_PLAYER_ID, int POSITION_SLOT) {
        PreparedStatement ps = null;

        int result = 0;

        String query = prop.getProperty("my.updateBySlot");
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, NEW_PLAYER_ID);
            ps.setInt(2, TEAM_ID);
            ps.setString(3, String.valueOf(POSITION_SLOT));

            result = ps.executeUpdate();

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(ps);
        }
    }

    public int deleteBySlot(Connection con, int TEAM_ID, int POSITION_SLOT) {
        PreparedStatement ps = null;

        int result = 0;

        String query = prop.getProperty("my.deleteBySlot");
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, TEAM_ID);
            ps.setString(2, String.valueOf(POSITION_SLOT));

            result = ps.executeUpdate();

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(ps);
        }
    }
}

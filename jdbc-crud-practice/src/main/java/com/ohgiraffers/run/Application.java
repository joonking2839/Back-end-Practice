package com.ohgiraffers.run;

import java.sql.Connection;

import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application {
    public static void main(String[] args) {
        Connection con = getConnection();
        GameMenu menu = new GameMenu(con);
        menu.start();
    }
}

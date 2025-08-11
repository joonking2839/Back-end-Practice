package com.ohgiraffers.myTeamPlayer.dto;

public class MyTeamPlayersDTO {
    private int TEAM_ID;
    private int PLAYER_ID;
    private String POSITION_SLOT;

    public MyTeamPlayersDTO(){}

    public MyTeamPlayersDTO(int TEAM_ID, int PLAYER_ID, String POSITION_SLOT) {
        this.TEAM_ID = TEAM_ID;
        this.PLAYER_ID = PLAYER_ID;
        this.POSITION_SLOT = POSITION_SLOT;
    }

    public int getTEAM_ID() {
        return TEAM_ID;
    }

    public void setTEAM_ID(int TEAM_ID) {
        this.TEAM_ID = TEAM_ID;
    }

    public int getPLAYER_ID() {
        return PLAYER_ID;
    }

    public void setPLAYER_ID(int PLAYER_ID) {
        this.PLAYER_ID = PLAYER_ID;
    }

    public String getPOSITION_SLOT() {
        return POSITION_SLOT;
    }

    public void setPOSITION_SLOT(String POSITION_SLOT) {
        this.POSITION_SLOT = POSITION_SLOT;
    }

    @Override
    public String toString() {
        return "MyTeamPlayersDTO{" +
                "TEAM_ID=" + TEAM_ID +
                ", PLAYER_ID=" + PLAYER_ID +
                ", POSITION_SLOT='" + POSITION_SLOT + '\'' +
                '}';
    }
}

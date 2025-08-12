package com.ohgiraffers.players.dto;

public class MyPlayerDTO {
    private int POSITION_SLOT;
    private int TEAM_ID;
    private int PLAYER_ID;

    public MyPlayerDTO(){}

    public MyPlayerDTO(int POSITION_SLOT, int TEAM_ID, int PLAYER_ID) {
        this.POSITION_SLOT = POSITION_SLOT;
        this.TEAM_ID = TEAM_ID;
        this.PLAYER_ID = PLAYER_ID;
    }

    public int getPOSITION_SLOT() {
        return POSITION_SLOT;
    }

    public void setPOSITION_SLOT(int POSITION_SLOT) {
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

    @Override
    public String toString() {
        return "MyPlayerDTO{" +
                "POSITION_SLOT=" + POSITION_SLOT +
                ", TEAM_ID=" + TEAM_ID +
                ", PLAYER_ID=" + PLAYER_ID +
                '}';
    }
}

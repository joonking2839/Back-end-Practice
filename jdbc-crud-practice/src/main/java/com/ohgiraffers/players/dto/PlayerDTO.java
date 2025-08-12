package com.ohgiraffers.players.dto;

public class PlayerDTO {
    private int PLAYER_ID;
    private String PLAYER_NAME;
    private int PLAYER_VALUE;
    private String PLAYER_POSITION;
    private int OPPONENT_ID;
    private float PLAYER_CONTRIBUTION;

    PlayerDTO(){}

    public PlayerDTO(int PLAYER_ID, String PLAYER_NAME, int PLAYER_VALUE, String PLAYER_POSITION, int OPPONENT_ID, float PLAYER_CONTRIBUTION) {
        this.PLAYER_ID = PLAYER_ID;
        this.PLAYER_NAME = PLAYER_NAME;
        this.PLAYER_VALUE = PLAYER_VALUE;
        this.PLAYER_POSITION = PLAYER_POSITION;
        this.OPPONENT_ID = OPPONENT_ID;
        this.PLAYER_CONTRIBUTION = PLAYER_CONTRIBUTION;
    }

    public PlayerDTO(int PLAYER_ID, String PLAYER_NAME, int PLAYER_VALUE, String PLAYER_POSITION) {
        this.PLAYER_ID = PLAYER_ID;
        this.PLAYER_NAME = PLAYER_NAME;
        this.PLAYER_VALUE = PLAYER_VALUE;
        this.PLAYER_POSITION = PLAYER_POSITION;
    }

    public int getPLAYER_ID() {
        return PLAYER_ID;
    }

    public void setPLAYER_ID(int PLAYER_ID) {
        this.PLAYER_ID = PLAYER_ID;
    }

    public String getPLAYER_NAME() {
        return PLAYER_NAME;
    }

    public void setPLAYER_NAME(String PLAYER_NAME) {
        this.PLAYER_NAME = PLAYER_NAME;
    }

    public int getPLAYER_VALUE() {
        return PLAYER_VALUE;
    }

    public void setPLAYER_VALUE(int PLAYER_VALUE) {
        this.PLAYER_VALUE = PLAYER_VALUE;
    }

    public String getPLAYER_POSITION() {
        return PLAYER_POSITION;
    }

    public void setPLAYER_POSITION(String PLAYER_POSITION) {
        this.PLAYER_POSITION = PLAYER_POSITION;
    }

    public int getOPPONENT_ID() {
        return OPPONENT_ID;
    }

    public void setOPPONENT_ID(int OPPONENT_ID) {
        this.OPPONENT_ID = OPPONENT_ID;
    }

    public float getPLAYER_CONTRIBUTION() {
        return PLAYER_CONTRIBUTION;
    }

    public void setPLAYER_CONTRIBUTION(float PLAYER_CONTRIBUTION) {
        this.PLAYER_CONTRIBUTION = PLAYER_CONTRIBUTION;
    }

    @Override
    public String toString() {
        return "PlayerDTO{" +
                "PLAYER_ID=" + PLAYER_ID +
                ", PLAYER_NAME='" + PLAYER_NAME + '\'' +
                ", PLAYER_VALUE=" + PLAYER_VALUE +
                ", PLAYER_POSITION='" + PLAYER_POSITION + '\'' +
                ", OPPONENT_ID=" + OPPONENT_ID +
                ", PLAYER_CONTRIBUTION=" + PLAYER_CONTRIBUTION +
                '}';
    }
}

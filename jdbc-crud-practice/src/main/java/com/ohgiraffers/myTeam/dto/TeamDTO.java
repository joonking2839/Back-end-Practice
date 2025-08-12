package com.ohgiraffers.myTeam.dto;

public class TeamDTO {
    private int TEAM_ID;
    private String TEAM_NAME;
    private int BUDGET;
    private String TACTICS;

    public TeamDTO(){}

    public TeamDTO(int TEAM_ID, String TEAM_NAME, int BUDGET, String TACTICS) {
        this.TEAM_ID = TEAM_ID;
        this.TEAM_NAME = TEAM_NAME;
        this.BUDGET = BUDGET;
        this.TACTICS = TACTICS;
    }

    public TeamDTO(String TEAM_NAME, int BUDGET, String TACTICS) {
        this.TEAM_NAME = TEAM_NAME;
        this.BUDGET = BUDGET;
        this.TACTICS = TACTICS;
    }

    public int getTEAM_ID() {
        return TEAM_ID;
    }

    public void setTEAM_ID(int TEAM_ID) {
        this.TEAM_ID = TEAM_ID;
    }

    public String getTEAM_NAME() {
        return TEAM_NAME;
    }

    public void setTEAM_NAME(String TEAM_NAME) {
        this.TEAM_NAME = TEAM_NAME;
    }

    public int getBUDGET() {
        return BUDGET;
    }

    public void setBUDGET(int BUDGET) {
        this.BUDGET = BUDGET;
    }

    public String getTACTICS() {
        return TACTICS;
    }

    public void setTACTICS(String TACTICS) {
        this.TACTICS = TACTICS;
    }

    @Override
    public String toString() {
        return "TeamDTO{" +
                "TEAM_ID=" + TEAM_ID +
                ", TEAM_NAME='" + TEAM_NAME + '\'' +
                ", BUDGET=" + BUDGET +
                ", TACTICS='" + TACTICS + '\'' +
                '}';
    }
}

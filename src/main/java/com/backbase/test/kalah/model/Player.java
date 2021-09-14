package com.backbase.test.kalah.model;

import com.backbase.test.kalah.util.Constants;

public class Player {

    private String name;

    private Pit[] pits;

    private boolean playerTurn;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Pit[] getPits() {
        if(pits == null){
            pits = new Pit[Constants.NUMBER_OF_PITS_PER_PLAYER];
        }
        return pits;
    }

    public void setPits(Pit[] pits) {
        this.pits = pits;
    }

    public boolean isPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(boolean playerTurn) {
        this.playerTurn = playerTurn;
    }
}

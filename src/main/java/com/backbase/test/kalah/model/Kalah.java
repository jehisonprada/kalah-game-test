package com.backbase.test.kalah.model;

public class Kalah {

    private Player player1;

    private Player player2;

    private static Kalah kalah;

    private Kalah(){

    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    //Singleton to guarantee only one kalah instance through the game execution
    public static Kalah getInstance(){
        if(kalah == null){
            kalah = new Kalah();
        }
        return kalah;
    }

}

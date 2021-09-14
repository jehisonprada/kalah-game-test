package com.backbase.test.kalah.service;

import com.backbase.test.kalah.model.Kalah;
import com.backbase.test.kalah.model.Player;
import com.backbase.test.kalah.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultKalahService implements KalahService {

    @Autowired
    private PlayerService playerService;

    @Override
    public void initKalah() {
        Kalah kalah = Kalah.getInstance();
        Player player1 = new Player();
        player1.setPlayerTurn(true);
        kalah.setPlayer1(player1);
        kalah.setPlayer2(new Player());
        playerService.initPlayer(kalah.getPlayer1());
        playerService.initPlayer(kalah.getPlayer2());
    }

    @Override
    public void performPlayerTurn(int playerNumber, int pitNumber) {
        Player playerWithTurn;
        Player otherPlayer;
        if(playerNumber == Constants.PLAYER_NUMBER_1){
            playerWithTurn = Kalah.getInstance().getPlayer1();
            otherPlayer = Kalah.getInstance().getPlayer2();
        } else{
            playerWithTurn = Kalah.getInstance().getPlayer2();
            otherPlayer = Kalah.getInstance().getPlayer1();
        }
        playerService.distributeStones(playerWithTurn, otherPlayer, pitNumber);
    }
}

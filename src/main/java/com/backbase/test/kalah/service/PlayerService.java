package com.backbase.test.kalah.service;

import com.backbase.test.kalah.model.Player;

/**
 * Class in charge of player related operations
 */
public interface PlayerService {

    void initPlayer(Player player);

    void distributeStones(Player playerWithTurn, Player otherPlayer, int selectedPit);

}

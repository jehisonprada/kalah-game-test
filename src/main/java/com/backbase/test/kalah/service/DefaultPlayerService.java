package com.backbase.test.kalah.service;

import com.backbase.test.kalah.model.Pit;
import com.backbase.test.kalah.model.PitType;
import com.backbase.test.kalah.model.Player;
import com.backbase.test.kalah.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultPlayerService implements PlayerService {

    @Autowired
    PitService pitService;

    @Override
    public void initPlayer(Player player) {
        pitService.initPits(player.getPits());
    }

    @Override
    public void distributeStones(Player playerWithTurn, Player otherPlayer, int selectedPit) {
        Pit[] playerWithTurnPits = playerWithTurn.getPits();
        int stonesToMove = playerWithTurnPits[selectedPit].getStones();

        pitService.clearPit(playerWithTurnPits[selectedPit]);

        distributeStonesForPlayer(playerWithTurn, otherPlayer, stonesToMove, stonesToMove, 0, selectedPit, true);

    }

    /**
     * Distributes the stones for the given player
     * @param
     * @return number of left stones to move
     */
    private void distributeStonesForPlayer(Player playerToDistributePits, Player otherPlayer, int stonesToMove, int totalStones, int totalMovedStones, int initialPosition, boolean firstCall){

        if(firstCall){
            initialPosition = initialPosition + Constants.INCREMENT_TO_NOT_INCLUDE_SELECTED_PIT;
            //stonesToMove = stonesToMove + Constants.INCREMENT_TO_MOVE_RIGHT_NUMBER_OF_STONES_WHEN_INCREMENTING_SELECTED_PIT;
        }

        Pit[] playerPits = playerToDistributePits.getPits();
        for(int i=0; i < stonesToMove; i++){
            int position = initialPosition + i;
            totalMovedStones += applyAddingStoneRules(position, playerPits, playerToDistributePits);
            applyEmptyLastPitRule(playerPits[position], playerToDistributePits, otherPlayer, totalStones-totalMovedStones);
            applyTurnRules(playerPits[position], playerToDistributePits, otherPlayer, totalStones-totalMovedStones);

            if(playerPits[position].getPitType() == PitType.STORE && totalStones - totalMovedStones > 0){
                distributeStonesForPlayer(otherPlayer,
                        playerToDistributePits,
                        totalStones-totalMovedStones,
                        totalStones,
                        totalMovedStones,
                        0,
                        false);
                break;
            }

        }
    }

    private int applyAddingStoneRules(int position,Pit[] pits, Player player){
        Pit pit = pits[position];
        if(pit.getPitType() == PitType.HOUSE || (pit.getPitType() == PitType.STORE && player.isPlayerTurn())){
            pitService.addStones(pit,1);
            return 1;
        }
        return 0;
    }

    private void applyTurnRules(Pit pit, Player player, Player secondPlayer, int leftStonesToMove){
        if(leftStonesToMove == 0){
            if(player.isPlayerTurn() && pit.getPitType() != PitType.STORE){
                player.setPlayerTurn(false);
                secondPlayer.setPlayerTurn(true);
            } else if(!player.isPlayerTurn()){
                player.setPlayerTurn(true);
                secondPlayer.setPlayerTurn(false);
            }
        }
    }

    private void applyEmptyLastPitRule(Pit pit, Player player, Player secondPlayer, int leftStonesToMove){
        if(leftStonesToMove == 0 && player.isPlayerTurn() && pit.getPitType() == PitType.HOUSE && pit.getStones() == 1){
            //+1 as there is one stone in the current pit
            Pit store = player.getPits()[Constants.NUMBER_OF_PITS_PER_PLAYER-1];
            pitService.addStones(store, secondPlayer.getPits()[pitService.getInFrontPitId(pit.getId())].getStones() + 1);
            pitService.clearPit(pit);
        }
    }

}

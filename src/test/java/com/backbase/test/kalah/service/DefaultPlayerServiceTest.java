package com.backbase.test.kalah.service;

import com.backbase.test.kalah.model.Pit;
import com.backbase.test.kalah.model.PitType;
import com.backbase.test.kalah.model.Player;
import com.backbase.test.kalah.util.Constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.stream.IntStream;

import static org.mockito.Mockito.*;

public class DefaultPlayerServiceTest {

    @InjectMocks
    DefaultPlayerService defaultPlayerService;

    PitService pitService = mock(DefaultPitService.class);;

    @Mock
    Player player;

    @Mock
    Player secondPlayer;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        doCallRealMethod().when(player).setPits(any());
        doCallRealMethod().when(player).getPits();
        when(player.isPlayerTurn()).thenReturn(true);
        doCallRealMethod().when(secondPlayer).setPits(any());
        doCallRealMethod().when(secondPlayer).getPits();
        doCallRealMethod().when(pitService).initPits(any());
        doCallRealMethod().when(pitService).addStones(any(), anyInt());
    }

    @Test
    public void initPlayer(){
        defaultPlayerService.initPlayer(player);
        verify(pitService, times(1)).initPits(player.getPits());
    }

    @Test
    public void distributeStones(){
        defaultPlayerService.initPlayer(player);
        defaultPlayerService.initPlayer(secondPlayer);
        int selectedPit = 0;
        defaultPlayerService.distributeStones(player, secondPlayer, selectedPit);

        verify(pitService, times(1)).clearPit(any(Pit.class));

        IntStream.range(selectedPit + 1, Constants.NUMBER_OF_PITS_PER_PLAYER)
        .forEach(index -> {
            if (player.getPits()[index].getPitType() == PitType.HOUSE){
                Assert.assertEquals(Constants.NUMBER_OF_STONES_PER_PIT + 1, player.getPits()[index].getStones());
            } else{
                Assert.assertEquals(1, player.getPits()[index].getStones());
            }
        });
    }


}

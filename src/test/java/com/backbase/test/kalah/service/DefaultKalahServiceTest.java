package com.backbase.test.kalah.service;

import com.backbase.test.kalah.model.Kalah;
import com.backbase.test.kalah.model.Player;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class DefaultKalahServiceTest {

    @InjectMocks
    DefaultKalahService defaultKalahService;

    @Mock
    PlayerService playerService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        doNothing().when(playerService).initPlayer(any(Player.class));
    }

    @Test
    public void init(){
        defaultKalahService.initKalah();
        assertNotNull(Kalah.getInstance().getPlayer1());
        assertNotNull(Kalah.getInstance().getPlayer2());
    }

    @Test
    public void performPlayerTurn(){
        int pitNumber = 1;
        defaultKalahService.performPlayerTurn(1, pitNumber);
        verify(playerService, times(1))
                .distributeStones(Kalah.getInstance().getPlayer1(), Kalah.getInstance().getPlayer2(), pitNumber);
    }

}

package com.backbase.test.kalah.service;

import com.backbase.test.kalah.model.Player;

/**
 * Integrates view with operations related to the Kalah object
 */
public interface KalahService {

    void initKalah();

    void performPlayerTurn(int playerNumber, int pitNumber);

}

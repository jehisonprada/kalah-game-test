package com.backbase.test.kalah.service;

import com.backbase.test.kalah.model.Pit;

public interface PitService {

    void initPits(Pit[] pits);

    void clearPit(Pit pit);

    void addStones(Pit pit, int stonesToAdd);

    int getInFrontPitId(int pitId);

}

package com.backbase.test.kalah.service;

import com.backbase.test.kalah.model.Pit;
import com.backbase.test.kalah.model.PitType;
import com.backbase.test.kalah.util.Constants;
import org.springframework.stereotype.Service;

import java.util.stream.IntStream;

@Service
public class DefaultPitService implements PitService {

    @Override
    public void initPits(Pit[] pits) {
        IntStream.range(0, pits.length)
                .forEach(index -> {
                    Pit p = new Pit();
                    p.setId(index);
                    if(index == pits.length-1){
                        p.setPitType(PitType.STORE);
                    } else{
                        p.setPitType(PitType.HOUSE);
                        p.setStones(Constants.NUMBER_OF_STONES_PER_PIT);
                    }
                    pits[index] = p;
                });
    }

    @Override
    public void clearPit(Pit pit) {
        if(pit != null){
            pit.setStones(0);
        }
    }

    @Override
    public void addStones(Pit pit, int stonesToAdd) {
        pit.setStones(pit.getStones() + stonesToAdd);
    }

    @Override
    public int getInFrontPitId(int pitId) {
        // the number 2 corresponds: 1 because of the store pit and other because length starts counting from 1 and positions in the array start from 0
        int realNumberOfPits = Constants.NUMBER_OF_PITS_PER_PLAYER - 2;
        return realNumberOfPits - pitId;
    }

}

package com.backbase.test.kalah.controller;

import com.backbase.test.kalah.model.Kalah;
import com.backbase.test.kalah.service.KalahService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class KalahController {

    @Autowired
    private KalahService kalahService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String renderInitialPage(ModelMap dataModel){
        kalahService.initKalah();
        dataModel.put("kalah", Kalah.getInstance());
        return "kalah";
    }

    @RequestMapping(value = "/pitSelected", method = RequestMethod.GET)
    public String renderGamePageAfterClickingPit(@RequestParam int playerNumber, @RequestParam int pitNumber, ModelMap dataModel){
        kalahService.performPlayerTurn(playerNumber, pitNumber);
        dataModel.put("kalah", Kalah.getInstance());
        return "kalah";
    }

}

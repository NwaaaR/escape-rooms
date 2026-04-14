package org.sebsy.demo.escaperooms.controller;

import org.sebsy.demo.escaperooms.bll.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EscapeRoom1Controller {

    private RoomService roomService;

    @Autowired
    public EscapeRoom1Controller(RoomService roomService){
        this.roomService = roomService;
    }

    public void entreeSalle1() {
        System.out.println("Bravo, vous avez trouvé la première salle !");
    }

}

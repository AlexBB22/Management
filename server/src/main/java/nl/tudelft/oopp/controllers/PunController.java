package nl.tudelft.oopp.controllers;


import nl.tudelft.oopp.entities.Pun;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Random;

@Controller
public class PunController {

    @GetMapping("pun")
    @ResponseBody
    public Pun getRandomPun() {
        Pun p1=new Pun(
                "How do you make holy water? You boil the hell out of it"
        );
        Pun p2=new Pun(
                "I lost my job at the bank on my very first day. A woman asked me to check her balance, so I pushed her over"
        );
        Pun p3=new Pun(
                "What do you call a bee that can’t make up its mind? A maybe"
        );

        ArrayList<Pun> puns = new ArrayList<>();
        puns.add(p1);
        puns.add(p2);
        puns.add(p3);
        return puns.get(new Random().nextInt(puns.size()));
    };




}

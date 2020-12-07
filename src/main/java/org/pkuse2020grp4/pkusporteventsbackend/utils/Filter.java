package org.pkuse2020grp4.pkusporteventsbackend.utils;

import lombok.*;

@Data
public class Filter {

    public enum Sequence{
        Positive, Reverse
    }

    /*
     if not null, user subscribed
     */
    private  String username;
    /*
        if Positive, earlier first
     */
    private Sequence dateSequence;
    private Sequence nameSequence;
    /*
        if -1, mean no day distance
     */
    private Integer dayDistance;

    public Filter(String username, Sequence dateSequence, Sequence nameSequence, Integer dayDistance){
        this.username = username;
        this.dateSequence = dateSequence;
        this.nameSequence = nameSequence;
        this.dayDistance = dayDistance;
    }

    static public Sequence int2Sequence(Integer input){
        if(input == 0)
            return Sequence.Positive;
        else
            return Sequence.Reverse;
    }

}

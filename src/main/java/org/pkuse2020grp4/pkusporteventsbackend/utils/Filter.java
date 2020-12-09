package org.pkuse2020grp4.pkusporteventsbackend.utils;

import lombok.*;

@Deprecated
@Data
@AllArgsConstructor
public class Filter {

    public enum Sequence{
        Positive, Reverse
    }

    /*
        if Positive, earlier first
     */
    private Sequence dateSequence;
    private Sequence nameSequence;
    /*
        if -1, mean no day distance
     */
    private Integer dayDistance;

    static public Sequence int2Sequence(Integer input){
        if(input == 0)
            return Sequence.Positive;
        else
            return Sequence.Reverse;
    }

}

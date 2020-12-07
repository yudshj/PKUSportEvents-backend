package org.pkuse2020grp4.pkusporteventsbackend.utils;

import lombok.*;
import org.pkuse2020grp4.pkusporteventsbackend.entity.Tag;

import java.util.List;

@Data
public class Filter {

    public enum Sequence{
        Positive, Reverse
    }

    /*
     if not null, user interest
     */
    private List<Tag> tags;
    /*
        if Positive, earlier first
     */
    private Sequence dateSequence;
    private Sequence nameSequence;
    /*
        if -1, mean no day distance
     */
    private Integer dayDistance;

    public Filter(List<Tag> tags, Sequence dateSequence, Sequence nameSequence, Integer dayDistance){
        this.tags = tags;
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

package org.pkuse2020grp4.pkusporteventsbackend.utils;

public class UID {
    private static final Integer MAX_UID = 1000000;
    private static Integer currentUid = 0;
    public static Integer genNewUID(){
        if(currentUid >= MAX_UID)
            return -1; // Will be modified....
        return currentUid ++;
    }
    public static  void resetUID(){
        currentUid = 0;
    }
}
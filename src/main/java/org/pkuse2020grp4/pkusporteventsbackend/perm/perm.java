package org.pkuse2020grp4.pkusporteventsbackend.perm;

import java.lang.reflect.Array;
import java.util.*;

public class perm {
    static public final int ROOT = 0;
    static public final int ADMIN = 1;
    static public final int PUBLISHER = 4;
    static public final int READER = 10;

    static public final int DEFAULT = READER;

    static public final Map<Integer, List<String>> permAPI = new HashMap<>(){{
        put(ROOT, Arrays.asList("/api"));
        put(ADMIN, Arrays.asList("/api"));
        put(PUBLISHER, Arrays.asList("/api/login", "/api/register", "/api/article/get", "/api/article/add", "/api/article/edit", "/api/game/get", "/api/userinfo", "/api/perm/apply"));
        put(READER, Arrays.asList("/api/login", "/api/register", "/api/article/get", "/api/game/get", "/api/userinfo", "/api/perm/apply"));
    }};

    static public String permToString(Integer perm){
        switch (perm){
            case 0:
                return "ROOT";
            case 1:
                return "ADMIN";
            case 4:
                return "PUBLISHER";
            default:
                return "READER";
        }
    }
}
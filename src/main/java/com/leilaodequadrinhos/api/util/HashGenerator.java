package com.leilaodequadrinhos.api.util;

import java.util.UUID;

public class HashGenerator {

    public static String hashGenerator() {
        UUID uuid = UUID.randomUUID();
        String myRandom = uuid.toString();
        System.out.println(myRandom.substring(0, 20));
        return myRandom.substring(0, 20);
    }
}

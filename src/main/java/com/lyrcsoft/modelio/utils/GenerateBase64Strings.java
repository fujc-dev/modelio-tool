package com.lyrcsoft.modelio.utils;

/**
 * @author fujc2dev@126.com
 * @date 2024-05-20 16:26
 */

import java.security.SecureRandom;
import java.util.Base64;

public class GenerateBase64Strings {

    public static String getRandomBase64String() {
        SecureRandom random = new SecureRandom();
        byte[] randomBytes = new byte[14];
        random.nextBytes(randomBytes);
        return "_" + Base64.getEncoder().encodeToString(randomBytes) + "-fWesxfPKa5g";
    }

    public static void main(String[] args) {

        System.out.println(getRandomBase64String());

    }
}


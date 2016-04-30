/*
 * Copyright 2016 Egor Andreevici
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.egorand.mysecrets.data.db;

import android.util.Base64;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * @author Egor
 */
public class SecretsDatabaseKeyGenerator {

    public static final int DEFAULT_KEY_LENGTH = 256;

    public static String generateKey() {
        try {
            SecretKey secretKey = internalGenerateKey();
            return Base64.encodeToString(secretKey.getEncoded(), Base64.NO_WRAP);
        } catch (NoSuchAlgorithmException ignored) {
        }
        return null;
    }

    private static SecretKey internalGenerateKey() throws NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(DEFAULT_KEY_LENGTH, random);
        return keyGenerator.generateKey();
    }
}

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

package me.egorand.mysecrets;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MySecretsDaoGenerator {

    private static final String PACKAGE_NAME = "me.egorand.mysecrets.data.gen";

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, PACKAGE_NAME);

        addSecret(schema);

        new DaoGenerator().generateAll(schema, "../app/src/main/java");
    }

    private static void addSecret(Schema schema) {
        Entity secret = schema.addEntity("Secret");
        secret.addIdProperty();
        secret.addStringProperty("text").notNull();
        secret.addDateProperty("addedDate").notNull();
    }
}

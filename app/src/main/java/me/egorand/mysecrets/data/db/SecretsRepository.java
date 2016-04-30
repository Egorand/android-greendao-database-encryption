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

import java.util.Date;
import java.util.List;

import javax.inject.Singleton;

import de.greenrobot.dao.database.Database;
import me.egorand.mysecrets.data.gen.DaoMaster;
import me.egorand.mysecrets.data.gen.Secret;
import me.egorand.mysecrets.data.gen.SecretDao;

/**
 * @author Egor
 */
@Singleton
public class SecretsRepository {

    private final SecretDao secretDao;

    public SecretsRepository(SecretsOpenHelper helper, SecretsDatabaseKeyHolder keyHolder) {
        Database database = helper.getWritableDatabase(keyHolder.getKey());
        DaoMaster daoMaster = new DaoMaster(database);
        this.secretDao = daoMaster.newSession().getSecretDao();
    }

    public List<Secret> loadAllSecrets() {
        return secretDao.loadAll();
    }

    public void storeSecret(String secretText) {
        Secret secret = new Secret();
        secret.setText(secretText);
        secret.setAddedDate(new Date());
        secretDao.insert(secret);
    }
}

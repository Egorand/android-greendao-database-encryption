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

package me.egorand.mysecrets.di.modules;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.egorand.mysecrets.data.db.SecretsDatabaseKeyHolder;
import me.egorand.mysecrets.data.db.SecretsOpenHelper;
import me.egorand.mysecrets.data.db.SecretsRepository;

/**
 * @author Egor
 */
@Module
public class AppModule {

    private final Context context;

    public AppModule(@NonNull Context context) {
        this.context = context.getApplicationContext();
    }

    @Provides public Context provideContext() {
        return context;
    }

    @Provides @Singleton public SecretsOpenHelper provideSecretsOpenHelper(Context context) {
        return new SecretsOpenHelper(context);
    }

    @Provides @Singleton public SecretsDatabaseKeyHolder provideSecretsDatabaseKeyHolder(Context context) {
        return new SecretsDatabaseKeyHolder(context);
    }

    @Provides @Singleton public SecretsRepository provideSecretsRepository(
            SecretsOpenHelper secretsOpenHelper, SecretsDatabaseKeyHolder secretsDatabaseKeyHolder) {
        return new SecretsRepository(secretsOpenHelper, secretsDatabaseKeyHolder);
    }
}

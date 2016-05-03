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

package me.egorand.mysecrets.di.components;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import me.egorand.mysecrets.data.db.SecretsDatabaseKeyHolder;
import me.egorand.mysecrets.data.db.SecretsRepository;
import me.egorand.mysecrets.data.loaders.SecretsLoader;
import me.egorand.mysecrets.di.modules.AppModule;
import me.egorand.mysecrets.ui.activities.SecretsActivity;

/**
 * @author Egor
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    void inject(SecretsActivity activity);

    Context appContext();

    @Singleton SecretsDatabaseKeyHolder secretsDatabaseKeyHolder();

    @Singleton SecretsRepository secretsRepository();

    SecretsLoader secretsLoader();
}

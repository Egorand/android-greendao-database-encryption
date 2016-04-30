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

package me.egorand.mysecrets.data.loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import java.util.List;

import javax.inject.Inject;

import me.egorand.mysecrets.data.db.SecretsRepository;
import me.egorand.mysecrets.data.gen.Secret;

/**
 * @author Egor
 */
public class SecretsLoader extends AsyncTaskLoader<List<Secret>> {

    private final SecretsRepository secretsRepository;

    private List<Secret> cachedSecrets;

    @Inject public SecretsLoader(Context context, SecretsRepository secretsRepository) {
        super(context);
        this.secretsRepository = secretsRepository;
    }

    @Override protected void onStartLoading() {
        if (cachedSecrets != null) {
            deliverResult(cachedSecrets);
        } else {
            forceLoad();
        }
    }

    @Override public List<Secret> loadInBackground() {
        return secretsRepository.loadAllSecrets();
    }

    @Override public void deliverResult(List<Secret> data) {
        cachedSecrets = data;
        super.deliverResult(data);
    }
}

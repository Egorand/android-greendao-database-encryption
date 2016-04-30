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

package me.egorand.mysecrets.ui.activities;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import me.egorand.mysecrets.MySecretsApp;
import me.egorand.mysecrets.R;
import me.egorand.mysecrets.data.db.SecretsRepository;
import me.egorand.mysecrets.data.gen.Secret;
import me.egorand.mysecrets.databinding.ActivitySecretsBinding;
import me.egorand.mysecrets.ui.adapters.SecretsAdapter;
import me.egorand.mysecrets.ui.dialogs.NewSecretDialogFragment;

public class SecretsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Secret>>,
        NewSecretDialogFragment.OnKeepNewSecretListener {

    private static final String LOG_TAG = "SecretsActivity";

    private static final String NEW_SECRET_DIALOG_TAG = "new_secret_dialog";

    private static final int LOADER_SECRETS = 0x01;

    @Inject SecretsRepository secretsRepository;

    private SecretsAdapter secretsAdapter;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MySecretsApp) getApplication()).appComponent().inject(this);

        ActivitySecretsBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_secrets);
        initToolbar(binding);
        initFab(binding);
        initSecretsList(binding);

        getSupportLoaderManager().initLoader(LOADER_SECRETS, null, this);
    }

    private void initToolbar(ActivitySecretsBinding binding) {
        setSupportActionBar(binding.toolbar);
    }

    private void initFab(ActivitySecretsBinding binding) {
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NewSecretDialogFragment fragment = new NewSecretDialogFragment();
                fragment.show(getSupportFragmentManager(), NEW_SECRET_DIALOG_TAG);
            }
        });
    }

    private void initSecretsList(ActivitySecretsBinding binding) {
        RecyclerView secretsList = (RecyclerView) binding.content.findViewById(R.id.secrets_list);
        this.secretsAdapter = new SecretsAdapter();
        secretsList.setAdapter(secretsAdapter);
    }

    @Override public void onKeepNewSecret(String secretText) {
        secretsRepository.storeSecret(secretText);
        getSupportLoaderManager().restartLoader(LOADER_SECRETS, null, this);
    }

    @Override public Loader<List<Secret>> onCreateLoader(int id, Bundle args) {
        return ((MySecretsApp) getApplication()).appComponent().secretsLoader();
    }

    @Override public void onLoadFinished(Loader<List<Secret>> loader, List<Secret> data) {
        Log.d(LOG_TAG, "Loaded " + data.size() + " secrets from DB");
        secretsAdapter.setSecrets(data);
    }

    @Override public void onLoaderReset(Loader<List<Secret>> loader) {
        secretsAdapter.setSecrets(Collections.<Secret>emptyList());
    }
}

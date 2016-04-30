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

package me.egorand.mysecrets.ui.adapters;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import me.egorand.mysecrets.BR;
import me.egorand.mysecrets.R;
import me.egorand.mysecrets.data.gen.Secret;

/**
 * @author Egor
 */
public class SecretsAdapter extends RecyclerView.Adapter<SecretsAdapter.ViewHolder> {

    private final List<Secret> secrets;

    public SecretsAdapter() {
        this.secrets = new ArrayList<>();
    }

    public void setSecrets(List<Secret> secrets) {
        int oldSize = this.secrets.size();
        this.secrets.clear();
        notifyItemRangeRemoved(0, oldSize);
        this.secrets.addAll(secrets);
        notifyItemRangeInserted(0, secrets.size());
    }

    @Override public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(layoutInflater.inflate(R.layout.row_secret, parent, false));
    }

    @Override public void onBindViewHolder(ViewHolder holder, int position) {
        Secret secret = secrets.get(position);
        holder.binding.setVariable(BR.secret, secret);
        holder.binding.executePendingBindings();
    }

    @Override public int getItemCount() {
        return secrets.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        final ViewDataBinding binding;

        public ViewHolder(View itemView) {
            super(itemView);
            binding = DataBindingUtil.bind(itemView);
        }
    }
}

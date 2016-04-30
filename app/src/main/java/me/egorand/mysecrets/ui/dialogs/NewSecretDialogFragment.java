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

package me.egorand.mysecrets.ui.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import me.egorand.mysecrets.R;

/**
 * @author Egor
 */
public class NewSecretDialogFragment extends DialogFragment {

    private static final String LOG_TAG = "NewSecretDialogFragment";

    private OnKeepNewSecretListener listener;

    @Override public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            listener = (OnKeepNewSecretListener) getActivity();
        } catch (ClassCastException e) {
            Log.e(LOG_TAG, activity + "must implement OnKeepNewSecretListener!");
        }
    }

    @NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.new_secret_dialog_title);
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.view_new_secret_dialog, null);
        final EditText input = (EditText) view.findViewById(R.id.input);
        builder.setView(view);
        builder.setPositiveButton(R.string.new_secret_dialog_button_ok, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                listener.onKeepNewSecret(input.getText().toString());
            }
        });
        builder.setNegativeButton(android.R.string.cancel, null);
        return builder.create();
    }

    public interface OnKeepNewSecretListener {

        void onKeepNewSecret(String secretText);
    }
}

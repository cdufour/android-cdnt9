package com.cdnt9.signinoutapp.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.cdnt9.signinoutapp.R;
import com.cdnt9.signinoutapp.ServerResponse;
import com.cdnt9.signinoutapp.User;
import com.cdnt9.signinoutapp.UserApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private UserApi userApi;
    private EditText email;
    private EditText pass;
    private Button valid;
    private TextView tvResult;

    // android:usesCleartextTraffic="true" => allows http (not https) requests
    private String ipServer = "http://10.0.2.2:3000/";

    private PageViewModel pageViewModel;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final TextView textView = root.findViewById(R.id.section_label);
        pageViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        email    = root.findViewById(R.id.inpEmail);
        pass     = root.findViewById(R.id.inpPass);
        valid    = root.findViewById(R.id.btnValid);
        tvResult = root.findViewById(R.id.tvResult);

        valid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });

        return root;
    }

    private void signup() {
        Retrofit retrofit = new Retrofit.Builder()
                // https://futurestud.io/tutorials/how-to-run-an-android-app-against-a-localhost-api
                .baseUrl(ipServer)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userApi = retrofit.create(UserApi.class);
        // ToDo: validation d'inputs
        User user = new User(email.getText().toString(), pass.getText().toString());

        Call<User> call = userApi.signUp(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i("Signup", "ok");
                Log.i("Code", response.code() + "");

                if (response.code() == 409) {
                    tvResult.setText("Email already found");
                }
                if (response.code() == 200) {
                    tvResult.setText("Success");

                    // TODO Change Activity
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("Signup", t.getMessage());
            }
        });
    }
}
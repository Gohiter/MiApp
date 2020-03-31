package com.co.MiApp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private TextView textView;
    private Button BnLogout;

    OnLogoutListener logoutListener;

    public interface OnLogoutListener{
        public void logoutPerform();
    }

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        textView = view.findViewById(R.id.nombre);
        textView.setText("Bienvenido "+MainActivity.prefConfig.readName());

        BnLogout = view.findViewById(R.id.btn_Logout);
        BnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                logoutListener.logoutPerform();

            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        logoutListener = (OnLogoutListener) activity;
    }

}

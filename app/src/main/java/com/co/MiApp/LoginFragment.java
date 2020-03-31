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
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private TextView RegText;
    private EditText UserName,Password;
    private Button BnLogin;

    OnLoginFormActivityListener loginFormActivityListener;



    public  interface OnLoginFormActivityListener{
        public void performRegister();
        public void performLogin(String name);
    }

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        UserName = view.findViewById(R.id.User_name);
        Password = view.findViewById(R.id.Password);
        BnLogin = view.findViewById(R.id.btnLogin);
        BnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performLogin();
            }
        });


        RegText = view.findViewById(R.id.register_text);
        RegText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validar();
                loginFormActivityListener.performRegister();
            }
        });
        return view;
    }


    public boolean validar(){

        boolean retorno = true;

        String username = UserName.getText().toString();
        String password = Password.getText().toString();

        if (username.isEmpty()){
            UserName.setError("Este campo no puede quedar vacio");
            retorno = false;
        }if (password.isEmpty()){
            Password.setError("Este campo no puede quedar vacio");
            retorno = false;

        }

        return retorno;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Activity activity = (Activity) context;
        loginFormActivityListener = (OnLoginFormActivityListener) activity;
    }

    private void performLogin(){
        String username = UserName.getText().toString();
        String password = Password.getText().toString();

        Call<User> call = MainActivity.apiInterface.performUserLogin(username,password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.body().getResponse().equals("ok")){

                    //se activa el estado de logeo
                    MainActivity.prefConfig.writeLoginStatus(true);
                    loginFormActivityListener.performLogin(response.body().getName());

                }else if (response.body().getResponse().equals("failed")){

                    MainActivity.prefConfig.displayToast("Login fallido...");

                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        UserName.setText("");
        Password.setText("");
    }
}

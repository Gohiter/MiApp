package com.co.MiApp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistroFragment extends Fragment {

    private EditText Name,UserName,Password;
    private Button BnRegister;


    public RegistroFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registro, container, false);
        Name = view.findViewById(R.id.txtNombre);
        UserName = view.findViewById(R.id.txtUser_name);
        Password = view.findViewById(R.id.txtPassword);
        BnRegister = view.findViewById(R.id.btnRegistro);
        BnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                validar();
                performRegistration();

            }
        });
        return view;
    }



    public boolean validar(){

        boolean retorno = true;

        String name = Name.getText().toString();
        String username = UserName.getText().toString();
        String password = Password.getText().toString();

        if (name.isEmpty()){
            Name.setError("Este campo no puede quedar vacio");
            retorno = false;
        }
        if (username.isEmpty()){
            UserName.setError("Este campo no puede quedar vacio");
            retorno = false;
        }if (password.isEmpty()){
            Password.setError("Este campo no puede quedar vacio");
            retorno = false;

        }

        return retorno;
    }




    public void performRegistration(){
        String name = Name.getText().toString();
        String username = UserName.getText().toString();
        String password = Password.getText().toString();

        Call<User> call = MainActivity.apiInterface.performRegistration(name,username,password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.body().getResponse().equals("ok")){

                    MainActivity.prefConfig.displayToast("Registro satisfactorio...");

                }else if(response.body().getResponse().equals("exist")){

                    MainActivity.prefConfig.displayToast("Usuario existente...");

                }else if (response.body().getResponse().equals("error")){

                    MainActivity.prefConfig.displayToast("Error en el egistro...");

                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        Name.setText("");
        UserName.setText("");
        Password.setText("");
    }

}

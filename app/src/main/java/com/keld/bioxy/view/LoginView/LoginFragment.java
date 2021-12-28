package com.keld.bioxy.view.LoginView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.keld.bioxy.R;
import com.keld.bioxy.helper.SharedPreferenceHelper;
import com.keld.bioxy.view.MainView.MainActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    TextInputLayout username_input_login, password_input_login;
    Button btn_login;

    private LoginViewModel loginViewModel;
    private SharedPreferenceHelper helper;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        username_input_login = view.findViewById(R.id.username_input_login);
        password_input_login = view.findViewById(R.id.password_input_login);
        btn_login = view.findViewById(R.id.btn_login);

        loginViewModel = new ViewModelProvider(getActivity()).get(LoginViewModel.class);
        helper = SharedPreferenceHelper.getInstance(requireActivity());
        btn_login.setOnClickListener(view1 -> {
            if (!username_input_login.getEditText().getText().toString().isEmpty()
                    && !password_input_login.getEditText().getText().toString().isEmpty()) {
                String email = username_input_login.getEditText().getText().toString().trim();
                String pass = password_input_login.getEditText().getText().toString().trim();
                loginViewModel.login(email, pass).observe(LoginFragment.this.requireActivity(), tokenResponse -> {
                    if (tokenResponse != null) {
                        helper.saveAccessToken(tokenResponse.getAuthorization());
                        NavDirections actions = LoginFragmentDirections.actionLoginFragmentToDifficultyFragment();
                        Navigation.findNavController(view1).navigate(actions);
                        Toast.makeText(LoginFragment.this.requireActivity(), "Login Success", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(LoginFragment.this.requireActivity(), "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }else{
                Toast.makeText(requireActivity(), "Insert username and password", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        ((MainActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((MainActivity)getActivity()).getSupportActionBar().show();
    }
}
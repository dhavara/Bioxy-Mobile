package com.keld.bioxy.view.MainView;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.keld.bioxy.R;
import com.keld.bioxy.helper.SharedPreferenceHelper;

public class SplashFragment extends Fragment {
    private static int splashtime = 2500 ;
    private static final String TAG = "SplashFragment";

    public SplashFragment() {
        // Required empty public constructor

    }

    // TODO: Rename and change types and number of parameters
    public static SplashFragment newInstance(String param1, String param2) {
        SplashFragment fragment = new SplashFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferenceHelper helper = SharedPreferenceHelper.getInstance(requireActivity());

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            NavDirections action;
            if (!helper.getAccessToken().isEmpty()){
                action = SplashFragmentDirections.actionSplashFragmentToDifficultyFragment();
            }else{
                action = SplashFragmentDirections.actionSplashFragmentToLoginFragment();
            }
            Navigation.findNavController(view).navigate(action);
        }, splashtime);

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
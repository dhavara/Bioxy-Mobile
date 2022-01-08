package com.keld.bioxy.view.QuizView;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.keld.bioxy.R;
import com.keld.bioxy.helper.SharedPreferenceHelper;
import com.keld.bioxy.model.Difficulty;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DifficultyFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DifficultyFragment extends Fragment {
    Toolbar toolbar;
    private Button btn_easy, btn_medium, btn_hard, btn_very_hard;
    private QuizViewModel quizViewModel;
    private SharedPreferenceHelper helper;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DifficultyFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DifficultyFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DifficultyFragment newInstance(String param1, String param2) {
        DifficultyFragment fragment = new DifficultyFragment();
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
        return inflater.inflate(R.layout.fragment_difficulty, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = getActivity().findViewById(R.id.toolbar_main);
        toolbar.setTitle("Quiz");
        toolbar.setTitleTextColor(Color.WHITE);

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        quizViewModel = new ViewModelProvider(getActivity()).get(QuizViewModel.class);
        quizViewModel.init(helper.getAccessToken());
        Log.d("SUS: ", helper.getAccessToken());

        btn_easy = view.findViewById(R.id.btn_easy);
        btn_medium = view.findViewById(R.id.btn_answers2);
        btn_hard = view.findViewById(R.id.btn_hard);
        btn_very_hard = view.findViewById(R.id.btn_very_hard);

        quizViewModel = new ViewModelProvider(getActivity()).get(QuizViewModel.class);
        quizViewModel.getDifficulties();
        quizViewModel.getResultDifficulties().observe(getActivity(), showDifficulty);
    }

    private Observer<Difficulty> showDifficulty = new Observer<Difficulty>() {
        @Override
        public void onChanged(Difficulty difficulty) {

            btn_easy.setText(btn_easy.getText() + " (" + difficulty.getDifficulties().get(0).getHealth() + " nyawa)");
            btn_medium.setText(btn_medium.getText() + " (" + difficulty.getDifficulties().get(1).getHealth() + " nyawa)");
            btn_hard.setText(btn_hard.getText() + " (" + difficulty.getDifficulties().get(2).getHealth() + " nyawa)");
            btn_very_hard.setText(btn_very_hard.getText() + " (" + difficulty.getDifficulties().get(3).getHealth() + " nyawa)");

            btn_easy.setOnClickListener(v1 -> {
                Bundle bundle = new Bundle();
                bundle.putInt("difficulty_id", difficulty.getDifficulties().get(0).getId());
                bundle.putInt("health", difficulty.getDifficulties().get(0).getHealth());
                Navigation.findNavController(v1).navigate(R.id.action_difficultyFragment_to_quizFragment2, bundle);
            });

            btn_medium.setOnClickListener(v1 -> {
                Bundle bundle = new Bundle();
                bundle.putInt("difficulty_id", difficulty.getDifficulties().get(1).getId());
                bundle.putInt("health", difficulty.getDifficulties().get(1).getHealth());
                Navigation.findNavController(v1).navigate(R.id.action_difficultyFragment_to_quizFragment2, bundle);
            });

            btn_hard.setOnClickListener(v1 -> {
                Bundle bundle = new Bundle();
                bundle.putInt("difficulty_id", difficulty.getDifficulties().get(2).getId());
                bundle.putInt("health", difficulty.getDifficulties().get(2).getHealth());
                Navigation.findNavController(v1).navigate(R.id.action_difficultyFragment_to_quizFragment2, bundle);
            });

            btn_very_hard.setOnClickListener(v1 -> {
                Bundle bundle = new Bundle();
                bundle.putInt("difficulty_id", difficulty.getDifficulties().get(3).getId());
                bundle.putInt("health", difficulty.getDifficulties().get(3).getHealth());
                Navigation.findNavController(v1).navigate(R.id.action_difficultyFragment_to_quizFragment2, bundle);
            });
        }
    };
    @Override
    public void onDetach() {
        super.onDetach();
        getActivity().getViewModelStore().clear();
    }
}
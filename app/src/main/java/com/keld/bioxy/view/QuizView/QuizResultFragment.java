package com.keld.bioxy.view.QuizView;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.keld.bioxy.R;
import com.keld.bioxy.helper.SharedPreferenceHelper;
import com.keld.bioxy.model.Leaderboard;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizResultFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizResultFragment extends Fragment {
    private QuizViewModel quizViewModel;
    private SharedPreferenceHelper helper;

    Toolbar toolbar;
    private Button btn_result_leaderboard, btn_back;
    private TextView txt_accuracyPercent, txt_correct, txt_score;
    private int health, difficulty_id, soal_number, soal_correct, point;
    private String difficulty;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuizResultFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuizResultFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizResultFragment newInstance(String param1, String param2) {
        QuizResultFragment fragment = new QuizResultFragment();
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
        return inflater.inflate(R.layout.fragment_quiz_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = getActivity().findViewById(R.id.toolbar_main);
        toolbar.setTitle("Quiz Result");
        toolbar.setTitleTextColor(Color.WHITE);

        txt_accuracyPercent = view.findViewById(R.id.txt_accuracyPercent);
        txt_correct = view.findViewById(R.id.txt_correct);
        txt_score = view.findViewById(R.id.txt_score);
        btn_result_leaderboard = view.findViewById(R.id.btn_result_leaderboard);
        btn_back = view.findViewById(R.id.btn_back);

        health = getArguments().getInt("health");
        difficulty_id = getArguments().getInt("difficulty_id");
        soal_number = getArguments().getInt("soal_number");
        soal_correct = getArguments().getInt("soal_correct");
        point = getArguments().getInt("point");

        txt_accuracyPercent.setText(Math.ceil(soal_correct*100/soal_number)+"%");
        txt_correct.setText(soal_correct+"");
        txt_score.setText(point+"");

        switch (difficulty_id) {
            case 1:
                difficulty = "Mudah";
                break;
            case 2:
                difficulty = "Sedang";
                break;
            case 3:
                difficulty = "Sulit";
                break;
            case 4:
                difficulty = "Sangat Sulit";
        }

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        quizViewModel = new ViewModelProvider(getActivity()).get(QuizViewModel.class);
        quizViewModel.init(helper.getAccessToken());
        quizViewModel.result(difficulty, point, soal_correct, soal_number).observe(requireActivity(), resultResponse -> {
            if (resultResponse != null){
                Toast.makeText(requireActivity(), "Sukses! Data telah tersimpan.", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(requireActivity(), "Gagal!", Toast.LENGTH_SHORT).show();
            }
        });

        btn_result_leaderboard.setOnClickListener(v1 -> {
            Bundle bundle = new Bundle();
            Navigation.findNavController(v1).navigate(R.id.action_quizResultFragment2_to_leaderboardFragment, bundle);
        });
        btn_back.setOnClickListener(v1 -> {
            Bundle bundle = new Bundle();
            Navigation.findNavController(v1).navigate(R.id.action_quizResultFragment2_to_difficultyFragment2, bundle);
        });
    }
}
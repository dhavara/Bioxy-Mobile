package com.keld.bioxy.view.QuizView;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.keld.bioxy.R;
import com.keld.bioxy.helper.SharedPreferenceHelper;
import com.keld.bioxy.model.Soal;
import com.keld.bioxy.view.ProfileView.ProfileViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuizFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuizFragment extends Fragment {
    private QuizViewModel quizViewModel;
    private SharedPreferenceHelper helper;
    Toolbar toolbar;

    private TextView txt_no_soal, txt_soal, txt_point, txt_health;
    private ImageView img_soal;
    private Button btn_answers1, btn_answers2, btn_answers3, btn_answers4, btn_answers5;
    private int health, difficulty_id, soal_number, soal_correct, point;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public QuizFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuizFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuizFragment newInstance(String param1, String param2) {
        QuizFragment fragment = new QuizFragment();
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
        return inflater.inflate(R.layout.fragment_quiz, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = getActivity().findViewById(R.id.toolbar_main);
        toolbar.setTitle("Quiz");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.getNavigationIcon().setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);

        int health = getArguments().getInt("health");
        int difficulty_id = getArguments().getInt("difficulty_id");
        int soal_number = getArguments().getInt("soal_number");
        int soal_correct = getArguments().getInt("soal_correct");
        int point = getArguments().getInt("point");

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        quizViewModel = new ViewModelProvider(getActivity()).get(QuizViewModel.class);
        quizViewModel.init(helper.getAccessToken());
        quizViewModel.getQuiz(difficulty_id);
        quizViewModel.getResultQuiz().observe(getActivity(), showQuiz);

        txt_no_soal = view.findViewById(R.id.txt_no_soal);
        txt_soal = view.findViewById(R.id.txt_soal);
        txt_point = view.findViewById(R.id.txt_point);
        txt_health = view.findViewById(R.id.txt_health);
        img_soal = view.findViewById(R.id.img_soal);
        btn_answers1 = view.findViewById(R.id.btn_answers1);
        btn_answers2 = view.findViewById(R.id.btn_answers2);
        btn_answers3 = view.findViewById(R.id.btn_answers3);
        btn_answers4 = view.findViewById(R.id.btn_answers4);
        btn_answers5 = view.findViewById(R.id.btn_answers5);

        txt_no_soal.setText("Soal " + soal_number);
        txt_point.setText("Poin: " + point);
        txt_health.setText("Nyawa: " + health);
    }

    private Observer<Soal> showQuiz = new Observer<Soal>() {
        @Override
        public void onChanged(Soal soal) {
            Log.d("AMOGUS: ", String.valueOf(soal.getSoals()));
            Soal.Soals resultSoal = soal.getSoals().get(0);
            if (soal != null) {
                txt_soal.setText(resultSoal.getQuestion());

                Map answerMap = new HashMap();
                answerMap.put("correct", resultSoal.getAnswer_correct());
                answerMap.put("1", resultSoal.getAnswer_1());
                answerMap.put("2", resultSoal.getAnswer_2());
                answerMap.put("3", resultSoal.getAnswer_3());
                answerMap.put("4", resultSoal.getAnswer_4());

                List<String> keys = new ArrayList<String>(answerMap.keySet());
                Collections.shuffle(keys);
                for (Object o : keys) {
                    System.out.println();
                    if (btn_answers1.getText() == "ANSWER 1") btn_answers1.setText((String) answerMap.get(o));
                    else if (btn_answers2.getText() == "ANSWER 2") btn_answers2.setText((String) answerMap.get(o));
                    else if (btn_answers3.getText() == "ANSWER 3") btn_answers3.setText((String) answerMap.get(o));
                    else if (btn_answers4.getText() == "ANSWER 4") btn_answers4.setText((String) answerMap.get(o));
                    else if (btn_answers5.getText() == "ANSWER 5") btn_answers5.setText((String) answerMap.get(o));
                }
            }
        }
    };
}
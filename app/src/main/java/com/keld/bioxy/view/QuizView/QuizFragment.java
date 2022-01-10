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
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.keld.bioxy.R;
import com.keld.bioxy.helper.Const;
import com.keld.bioxy.helper.SharedPreferenceHelper;
import com.keld.bioxy.model.Soal;
import com.keld.bioxy.view.LoginView.LoginFragment;
import com.keld.bioxy.view.ProfileView.ProfileViewModel;

import java.util.Random;


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

        health = getArguments().getInt("health");
        difficulty_id = getArguments().getInt("difficulty_id");
        soal_number = getArguments().getInt("soal_number");
        soal_correct = getArguments().getInt("soal_correct");
        point = getArguments().getInt("point");

        toolbar.setNavigationOnClickListener(v -> {
            Toast.makeText(QuizFragment.this.requireActivity(), "Hayoo, mau ngecit yaaaa :V -10 poin, jangan diulangi lagi", Toast.LENGTH_SHORT).show();
            point -= 10;
            txt_point.setText("Poin: " + point);
        });

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
        img_soal = view.findViewById(R.id.img_soal);

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
                txt_soal.setText(resultSoal.getQuestion().replace("\\n", ""));
                if (resultSoal.getSoal_image() != null) {
                    Glide.with(getActivity()).load(Const.IMG_URL + "soal/" + resultSoal.getSoal_image()).into(img_soal);
                }
                btn_answers1.setText(resultSoal.getAnswer_1());
                btn_answers2.setText(resultSoal.getAnswer_2());
                btn_answers3.setText(resultSoal.getAnswer_3());
                btn_answers4.setText(resultSoal.getAnswer_4());
                btn_answers5.setText(resultSoal.getAnswer_correct());

                btn_answers1.setOnClickListener(v -> {
                    health -= 1;

                    if (health != 0) soal_number += 1;

                    Bundle bundle = new Bundle();
                    bundle.putInt("difficulty_id", difficulty_id);
                    bundle.putInt("health", health);
                    bundle.putInt("soal_number", soal_number);
                    bundle.putInt("soal_correct", soal_correct);
                    bundle.putInt("point", point);

                    if (health == 0) {
                        Navigation.findNavController(v).navigate(R.id.action_quizFragment2_to_quizResultFragment, bundle);
                    }
                    else {
                        Navigation.findNavController(v).navigate(R.id.action_quizFragment2_self, bundle);
                    }
                });
                btn_answers2.setOnClickListener(v -> {
                    health -= 1;

                    if (health != 0) soal_number += 1;

                    Bundle bundle = new Bundle();
                    bundle.putInt("difficulty_id", difficulty_id);
                    bundle.putInt("health", health);
                    bundle.putInt("soal_number", soal_number);
                    bundle.putInt("soal_correct", soal_correct);
                    bundle.putInt("point", point);

                    if (health == 0) {
                        Navigation.findNavController(v).navigate(R.id.action_quizFragment2_to_quizResultFragment, bundle);
                    }
                    else {
                        Navigation.findNavController(v).navigate(R.id.action_quizFragment2_self, bundle);
                    }
                });
                btn_answers3.setOnClickListener(v -> {
                    health -= 1;

                    if (health != 0) soal_number += 1;

                    Bundle bundle = new Bundle();
                    bundle.putInt("difficulty_id", difficulty_id);
                    bundle.putInt("health", health);
                    bundle.putInt("soal_number", soal_number);
                    bundle.putInt("soal_correct", soal_correct);
                    bundle.putInt("point", point);

                    if (health == 0) {
                        Navigation.findNavController(v).navigate(R.id.action_quizFragment2_to_quizResultFragment, bundle);
                    }
                    else {
                        Navigation.findNavController(v).navigate(R.id.action_quizFragment2_self, bundle);
                    }
                });
                btn_answers4.setOnClickListener(v -> {
                    health -= 1;

                    if (health != 0) soal_number += 1;

                    Bundle bundle = new Bundle();
                    bundle.putInt("difficulty_id", difficulty_id);
                    bundle.putInt("health", health);
                    bundle.putInt("soal_number", soal_number);
                    bundle.putInt("soal_correct", soal_correct);
                    bundle.putInt("point", point);

                    if (health == 0) {
                        Navigation.findNavController(v).navigate(R.id.action_quizFragment2_to_quizResultFragment, bundle);
                    }
                    else {
                        Navigation.findNavController(v).navigate(R.id.action_quizFragment2_self, bundle);
                    }
                });
                btn_answers5.setOnClickListener(v -> {
                    soal_correct += 1;
                    if (soal_correct % 5 == 0) {
                        health += 1;
                    }
                    point += 10;
//                    switch (difficulty_id) {
//                        case 1:
//                            point += 10;
//                            break;
//                        case 2:
//                            point += 10*2;
//                            break;
//                        case 3:
//                            point += 10*3;
//                            break;
//                        case 4:
//                            point += 10*5;
//                            break;
//                    }
                    soal_number += 1;

                    Bundle bundle = new Bundle();
                    bundle.putInt("difficulty_id", difficulty_id);
                    bundle.putInt("health", health);
                    bundle.putInt("soal_number", soal_number);
                    bundle.putInt("soal_correct", soal_correct);
                    bundle.putInt("point", point);
                    Navigation.findNavController(v).navigate(R.id.action_quizFragment2_self, bundle);
                });
            }
        }
    };
}
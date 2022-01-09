package com.keld.bioxy.view.ProfileView;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.os.Looper;
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
import com.keld.bioxy.model.Frame;
import com.keld.bioxy.model.User;
import com.keld.bioxy.view.MainView.SplashFragmentDirections;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    Toolbar toolbar;
    Button btn_profile_edit, btn_profile_logout;
    TextView tv_input_username, tv_input_name, tv_input_email, tv_input_school, tv_input_city, tv_input_birthdate;
    ImageView img_profile, img_frame;
    int frameId;

    private ProfileViewModel profileViewModel;
    private SharedPreferenceHelper helper;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolbar = getActivity().findViewById(R.id.toolbar_main);
        toolbar.setTitle("Profile");
        toolbar.setTitleTextColor(Color.WHITE);

        helper = SharedPreferenceHelper.getInstance(requireActivity());
        profileViewModel = new ViewModelProvider(getActivity()).get(ProfileViewModel.class);
        profileViewModel.init(helper.getAccessToken());
        profileViewModel.getUserDetail(helper.getUserId());
        profileViewModel.getResultUserDetail().observe(getActivity(), showUserDetail);

        tv_input_username = view.findViewById(R.id.tv_input_username);
        tv_input_name = view.findViewById(R.id.tv_input_name);
        tv_input_email = view.findViewById(R.id.tv_input_email);
        tv_input_school = view.findViewById(R.id.tv_input_school);
        tv_input_city = view.findViewById(R.id.tv_input_city);
        tv_input_birthdate = view.findViewById(R.id.tv_input_birthdate);
        img_profile = view.findViewById(R.id.img_profile);
        img_frame = view.findViewById(R.id.img_frame);
        frameId = 0;


        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            if (frameId > 0){
                profileViewModel = new ViewModelProvider(getActivity()).get(ProfileViewModel.class);
                profileViewModel.init(helper.getAccessToken());
                profileViewModel.getFrameDetail(frameId);
                profileViewModel.getResultFrameDetail().observe(getActivity(), showFrameDetail);
            }
        }, 1000);

        btn_profile_logout = view.findViewById(R.id.btn_profile_logout);
        btn_profile_logout.setOnClickListener(view1 -> {
            profileViewModel.logout().observe(requireActivity(), s -> {
                if (!s.isEmpty()) {
                    helper.clearPref();
                    NavDirections actions = ProfileFragmentDirections.actionProfileFragment2ToLoginFragment();
                    Navigation.findNavController(view1).navigate(actions);
                    Toast.makeText(requireActivity(), s, Toast.LENGTH_SHORT).show();
                }
            });
        });
    }

    private Observer<User> showUserDetail = new Observer<User>() {
        @Override
        public void onChanged(User user) {
            User.Users resultUser = user.getUsers().get(0);
            if (user == null) {
                tv_input_username.setText("Unknown");
                tv_input_name.setText("Unknown");
                tv_input_email.setText("Unknown");
                tv_input_school.setText("Unknown");
                tv_input_city.setText("Unknown");
                tv_input_birthdate.setText("Unknown");
                Glide.with(getActivity()).load(Const.IMG_URL + "null.png").into(img_profile);
            } else {
                tv_input_username.setText(resultUser.getUsername());
                tv_input_name.setText(resultUser.getName());
                tv_input_email.setText(resultUser.getEmail());
                tv_input_school.setText(resultUser.getSchool());
                tv_input_city.setText(resultUser.getCity());
                tv_input_birthdate.setText(resultUser.getBirthdate());
                if (resultUser.getDetails().getUser_image() == null) {
                    Glide.with(getActivity()).load(Const.IMG_URL + "null.png").into(img_profile);
                } else {
                    Glide.with(getActivity()).load(Const.IMG_URL + "user/" + resultUser.getDetails().getUser_image()).into(img_profile);
                }
                frameId = resultUser.getDetails().getUser_frame();
//                if (resultUser.getDetails().getUser_image() != null) {
//                    Glide.with(getActivity()).load(Const.IMG_URL + "user/" + resultUser.getDetails().getUser_image()).into(img_profile);
//                }
            }
        }
    };

    private Observer<Frame> showFrameDetail = new Observer<Frame>() {
        @Override
        public void onChanged(Frame frame) {
            Log.d("AMOGUS: ", String.valueOf(frame.getFrames()));
            Frame.Frames resultFrame = frame.getFrames().get(0);

            if (frame != null){
                Glide.with(getActivity()).load(Const.IMG_URL + "frame/" + resultFrame.getImage_path()).into(img_frame);
            }
        }
    };
}

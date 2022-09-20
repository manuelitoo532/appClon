package com.thecode.ortez.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.thecode.ortez.R;
import com.thecode.ortez.activities.EmailVerificationActivity;
import com.thecode.ortez.adapters.SliderAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {
    private Button btnLogout;
    private FirebaseAuth mAuth;


    View rootLayout;
    private SliderView sliderView;
    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootLayout = inflater.inflate(R.layout.fragment_account, container, false);
        sliderView = rootLayout.findViewById(R.id.slider_view);
        final SliderAdapter adapter = new SliderAdapter(getActivity());

        //validation email


        //logout
        mAuth = FirebaseAuth.getInstance();
        btnLogout = rootLayout.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(AccountFragment.this.getActivity(), EmailVerificationActivity.class);
                startActivity(intent);
                Toast.makeText(AccountFragment.this.getActivity(), "Cerro Sesión", Toast.LENGTH_LONG).show();
                try {
                    AccountFragment.this.finalize();
                } catch (Throwable e) {
                    e.printStackTrace();
                }

            }
        });
        //

        sliderView.setSliderAdapter(adapter);

        sliderView.setIndicatorAnimation(IndicatorAnimations.SLIDE); //set indicator animation by using SliderLayout.IndicatorAnimations. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        sliderView.startAutoCycle();

        return rootLayout;
    }


}

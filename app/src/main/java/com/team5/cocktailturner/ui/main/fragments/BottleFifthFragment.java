package com.team5.cocktailturner.ui.main.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.team5.cocktailturner.R;

import java.util.ArrayList;
import java.util.Random;

public class BottleFifthFragment extends Fragment {
    private static ArrayList<String> liquidData;

    private static ArrayList<String> randomIngredientsData;

    private static ArrayList<String> seasoningData;


    private ImageView bottle;
    private final Random random = new Random();
    private int lastDir;
    private boolean spinning;

    public BottleFifthFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Bundle bundle = this.getArguments();
        if (bundle != null) {
            liquidData = bundle.getStringArrayList("liquidData");
            randomIngredientsData = bundle.getStringArrayList("randomIngredientsData");
            seasoningData = bundle.getStringArrayList("seasoningData");
        }
        return inflater.inflate(R.layout.fragment_bottle_fifth, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button button = view.findViewById(R.id.createCocktail);

        bottle = view.findViewById(R.id.bottle);
        bottle.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                spinBottle(v);
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Fragment mixCocktailSixthFragment = new MixCocktailSixthFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Bundle arguments = new Bundle();
                arguments.putStringArrayList( "liquidData" , liquidData);
                arguments.putStringArrayList( "randomIngredientsData" , randomIngredientsData);
                arguments.putStringArrayList( "seasoningData" , seasoningData);
                mixCocktailSixthFragment.setArguments(arguments);
                transaction.replace(R.id.container, mixCocktailSixthFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

    }
    public void spinBottle(View v) {
        if (!spinning) {
            int newDir = random.nextInt(1800);
            float pivotX = bottle.getWidth() / 2;
            float pivotY = bottle.getHeight() / 2;

            Animation rotate = new RotateAnimation(lastDir, newDir, pivotX, pivotY);
            rotate.setDuration(2500);
            rotate.setFillAfter(true);
            rotate.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                    spinning = true;
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    spinning = false;
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            lastDir = newDir;
            bottle.startAnimation(rotate);
        }
    }
}
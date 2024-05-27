package com.appxemphim.activities;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.appxemphim.R;
import com.appxemphim.fragments.HomeFragment;
import com.appxemphim.fragments.ListPhimFragment;
import com.appxemphim.fragments.ProfileFragment;

public class TrangChuActivity extends AppCompatActivity {
    private View homeLayout;
    private View moviesLayout;
    private View profileLayout;

    private int flag = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);

        homeLayout = findViewById(R.id.homeLayout);
        moviesLayout = findViewById(R.id.moviesLayout);
        profileLayout = findViewById(R.id.profileLayout);

        // Set initial fragment
        replaceFragment(new HomeFragment());
        setBackgroundColor(homeLayout,moviesLayout,profileLayout);
        flag = homeLayout.getId();
        homeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag != homeLayout.getId()) {
                replaceFragment(new HomeFragment());
                setBackgroundColor(homeLayout,moviesLayout,profileLayout);
                flag = homeLayout.getId();
                }
            }
        });
        moviesLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag != moviesLayout.getId()) {
                replaceFragment(new ListPhimFragment());
                setBackgroundColor(moviesLayout,profileLayout,homeLayout);
                flag = moviesLayout.getId();
                }

            }
        });
        profileLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag != profileLayout.getId()) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("MaNguoiDung", 1);
                    Fragment fragmentProfile = new ProfileFragment();
                    fragmentProfile.setArguments(bundle);
                    replaceFragment(fragmentProfile);
                    setBackgroundColor(profileLayout,homeLayout,moviesLayout);
                    flag= profileLayout.getId();
                }
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentContainerView, fragment);
        transaction.commit();
    }
    private void setBackgroundColor(View view1,View view2,View view3) {
        Drawable drawableOther = ContextCompat.getDrawable(this, R.drawable.bg_item_appbar);
        if (drawableOther instanceof StateListDrawable) {
            StateListDrawable stateListDrawable = (StateListDrawable) drawableOther;
            Drawable.ConstantState constantState = stateListDrawable.getConstantState();
            if (constantState instanceof DrawableContainer.DrawableContainerState) {
                DrawableContainer.DrawableContainerState drawableContainerState = (DrawableContainer.DrawableContainerState) constantState;
                Drawable[] children = drawableContainerState.getChildren();
                if (children != null && children.length > 0) {
                    GradientDrawable gradientDrawable = (GradientDrawable) children[0];
                    if (gradientDrawable != null) {
                        int[] colors = gradientDrawable.getColors();
                        int color = colors != null && colors.length > 0 ? colors[0] : Color.TRANSPARENT;
                        view1.setBackgroundColor(color);
                        view2.setBackgroundColor(Color.TRANSPARENT);
                        view3.setBackgroundColor(Color.TRANSPARENT);
                    }
                }
            }
        }

    }
}
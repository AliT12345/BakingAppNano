package com.example.alit.bakingappnano.about;

import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.alit.bakingappnano.R;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by AliT on 10/18/17.
 */

public class SoftwareLicenseFragment extends Fragment {

    private static final int LICENSE_ANIMATION_DURATION = 200;
    private static final int LICENSE_MIN_HEIGHT = 1;

    @BindView(R.id.closeFragment)
    ImageView closeFragment;

    @BindView(R.id.butterKnifeWrapper)
    ConstraintLayout butterKnifeWrapper;
    @BindView(R.id.butterKnifeLicense)
    TextView butterKnifeLicense;
    @BindView(R.id.butterKnifeDownArrow)
    ImageView butterKnifeDownArrow;
    @BindView(R.id.butterKnifeUpArrow)
    ImageView butterKnifeUpArrow;

    @BindView(R.id.exoPlayerWrapper)
    ConstraintLayout exoPlayerWrapper;
    @BindView(R.id.exoPlayerLicense)
    TextView exoPlayerLicense;
    @BindView(R.id.exoPlayerDownArrow)
    ImageView exoPlayerDownArrow;
    @BindView(R.id.exoPlayerUpArrow)
    ImageView exoPlayerUpArrow;

    @BindView(R.id.jobDispatcherWrapper)
    ConstraintLayout jobDispatcherWrapper;
    @BindView(R.id.jobDispatcherLicense)
    TextView jobDispatcherLicense;
    @BindView(R.id.jobDispatcherDownArrow)
    ImageView jobDispatcherDownArrow;
    @BindView(R.id.jobDispatcherUpArrow)
    ImageView jobDispatcherUpArrow;

    @BindView(R.id.picassoWrapper)
    ConstraintLayout picassoWrapper;
    @BindView(R.id.picassoLicense)
    TextView picassoLicense;
    @BindView(R.id.picassoDownArrow)
    ImageView picassoDownArrow;
    @BindView(R.id.picassoUpArrow)
    ImageView picassoUpArrow;

    @BindView(R.id.schematicWrapper)
    ConstraintLayout schematicWrapper;
    @BindView(R.id.schematicLicense)
    TextView schematicLicense;
    @BindView(R.id.schematicDownArrow)
    ImageView schematicDownArrow;
    @BindView(R.id.schematicUpArrow)
    ImageView schematicUpArrow;

    @BindView(R.id.timberWrapper)
    ConstraintLayout timberWrapper;
    @BindView(R.id.timberLicense)
    TextView timberLicense;
    @BindView(R.id.timberDownArrow)
    ImageView timberDownArrow;
    @BindView(R.id.timberUpArrow)
    ImageView timberUpArrow;

    private Map<TextView, Integer> heightsMap;

    private Context context;

    private Unbinder unbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.software_license_fragment_layout, container, false);

        unbinder = ButterKnife.bind(this, rootView);

        ((AboutActivity) context).hideActionBar();

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        heightsMap = new HashMap<>();

        setViewTreeObserver(butterKnifeLicense);
        setViewTreeObserver(exoPlayerLicense);
        setViewTreeObserver(jobDispatcherLicense);
        setViewTreeObserver(picassoLicense);
        setViewTreeObserver(schematicLicense);
        setViewTreeObserver(timberLicense);

        setLicenseClickListener(butterKnifeWrapper, butterKnifeUpArrow, butterKnifeDownArrow, butterKnifeLicense);
        setLicenseClickListener(exoPlayerWrapper, exoPlayerUpArrow, exoPlayerDownArrow, exoPlayerLicense);
        setLicenseClickListener(jobDispatcherWrapper, jobDispatcherUpArrow, jobDispatcherDownArrow, jobDispatcherLicense);
        setLicenseClickListener(picassoWrapper, picassoUpArrow, picassoDownArrow, picassoLicense);
        setLicenseClickListener(schematicWrapper, schematicUpArrow, schematicDownArrow, schematicLicense);
        setLicenseClickListener(timberWrapper, timberUpArrow, timberDownArrow, timberLicense);

        closeFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((AboutActivity) context).removeFragment(SoftwareLicenseFragment.this);
            }
        });

    }

    public void setViewTreeObserver(final TextView license) {

        license.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {

                license.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                int height = license.getHeight();

                heightsMap.put(license, height);

                ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) license.getLayoutParams();
                params.height = height;
                license.setLayoutParams(params);
            }
        });

    }

    public void setLicenseClickListener(ConstraintLayout wrapper, final ImageView upArrow, final ImageView downArrow,
                                        final TextView license) {

        wrapper.setOnClickListener(new View.OnClickListener() {

            boolean isOpen = true;

            @Override
            public void onClick(View view) {

                final ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) license.getLayoutParams();

                if (isOpen) {
                    upArrow.setVisibility(View.INVISIBLE);
                    downArrow.setVisibility(View.VISIBLE);
                    ValueAnimator anim = ValueAnimator.ofInt(params.height, LICENSE_MIN_HEIGHT).setDuration(LICENSE_ANIMATION_DURATION);
                    anim.setInterpolator(new DecelerateInterpolator());
                    anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            params.height = (int) valueAnimator.getAnimatedValue();
                            license.setLayoutParams(params);
                        }
                    });
                    anim.start();
                } else {
                    downArrow.setVisibility(View.INVISIBLE);
                    upArrow.setVisibility(View.VISIBLE);
                    ValueAnimator anim = ValueAnimator.ofInt(params.height, heightsMap.get(license)).setDuration(LICENSE_ANIMATION_DURATION);
                    anim.setInterpolator(new DecelerateInterpolator());
                    anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                            params.height = (int) valueAnimator.getAnimatedValue();
                            license.setLayoutParams(params);
                        }
                    });
                    anim.start();
                }

                isOpen = !isOpen;
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        ((AboutActivity) context).showActionBar();
    }
}

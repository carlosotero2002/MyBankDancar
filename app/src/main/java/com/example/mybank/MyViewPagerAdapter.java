package com.example.mybank;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import com.example.mybank.fragments.CreditsFragment;
import com.example.mybank.fragments.HomeFragment;
import com.example.mybank.fragments.OfficeFragment;
import com.example.mybank.fragments.PaymentFragment;
import com.example.mybank.fragments.PurchaseFragment;
import com.example.mybank.fragments.TransferFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {
    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    /*
    @Override
    public SwipeDisabledViewPager(Context context) {
        super(context);
    }
    public SwipeDisabledViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        // returning false will not propagate the swipe event
        return false;
    }
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
    */

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new HomeFragment();
            case 1:
                return new TransferFragment();
            case 2:
                return new PaymentFragment();
            case 3:
                return new PurchaseFragment();
            case 4:
                return new CreditsFragment();
            case 5:
                return new OfficeFragment();
            default:
                return new HomeFragment();
        }
    }


    @Override
    public int getItemCount() {
        return 6; //NUMERO DE TABS A USAR
    }
}

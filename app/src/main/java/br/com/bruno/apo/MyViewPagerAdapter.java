package br.com.bruno.apo;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import br.com.bruno.apo.fragments.MainFragment;
import br.com.bruno.apo.fragments.MapFragment;

public class MyViewPagerAdapter extends FragmentStateAdapter {

    public MyViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new MainFragment();
            case 1:
                return new MapFragment();
            default:
                return new MainFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}

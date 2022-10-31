package org.daomingedu.onecpexam.base;


import android.os.Bundle;

import androidx.fragment.app.Fragment;


/**
 * Created by qin on 2017/1/7.
 */

public class BaseFragment extends Fragment {

    public Buddy bd;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = new Buddy(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (bd == null) {
            bd = new Buddy(getActivity());
        }
    }
}

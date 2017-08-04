package com.example.a42470210.donaciones;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by 42248050 on 14/7/2017.
 */

public class FragmentEventos extends Fragment {
    View myView;

    @Override
    public View onCreateView (LayoutInflater Inflador, ViewGroup Container, Bundle Bundls){
        myView=Inflador.inflate(R.layout.fragment_eventos, Container, false);
        return myView;
    }
}

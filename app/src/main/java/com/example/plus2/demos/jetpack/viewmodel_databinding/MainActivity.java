package com.example.plus2.demos.jetpack.viewmodel_databinding;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.plus2.R;
import com.example.plus2.databinding.ActivityMainDemosJetpackBinding;


public class MainActivity extends AppCompatActivity {

    private ActivityMainDemosJetpackBinding binding;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main_demos_jetpack);

        mainViewModel = new ViewModelProvider(this,new ViewModelProvider.AndroidViewModelFactory(getApplication()))
                .get(MainViewModel.class);

        //绑定
        binding.setVm(mainViewModel);

        //建立感应
        binding.setLifecycleOwner(this);
    }

}

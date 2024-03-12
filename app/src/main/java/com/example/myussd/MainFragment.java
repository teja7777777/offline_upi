/*
 * Copyright (c) 2020. BoostTag E.I.R.L. Romell D.Z.
 * All rights reserved
 * porfile.romellfudi.com
 */

package com.example.myussd;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.myussd.PermissionService;
import com.example.myussd.app;
import com.example.myussd.R;
import com.example.myussd.databinding.ContentOp1Binding;
import com.example.myussd.OverlayShowingService;
import com.example.myussd.SplashLoadingService;
import com.example.myussd.USSDApi;
import com.example.myussd.USSDController;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

import javax.inject.Inject;

import timber.log.Timber;


public class MainFragment extends Fragment {

    @Inject
    USSDApi ussdApi;

    @Inject
    HashMap<String, HashSet<String>> map;

    DaoViewModel mViewModel;
    ContentOp1Binding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        ((app) getActivity().getApplicationContext()).getAppComponent().inject(this);
        super.onCreate(savedInstanceState);
        new PermissionService(getActivity()).request(callback);
        mViewModel = ViewModelProviders.of(getActivity()).get(DaoViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.content_op1, container, false);
        requireActivity();
        binding.setViewModel(mViewModel);
        binding.setLifecycleOwner(getActivity());
        setHasOptionsMenu(false);

        binding.btn1.setOnClickListener(view -> {
            binding.result.setText("");
            ussdApi.callUSSDInvoke(getPhoneNumber(), map, new USSDController.CallbackInvoke() {

                @Override
                public void responseInvoke(String message) {
                    Timber.d(message);
                    binding.result.append("\n-\n" + message);
                    // first option list - select option 1
                    ussdApi.send("1", message12 -> {
                        Timber.i(message12);
                        binding.result.append("\n-\n" + message12);
                        // second option list - select option 2
                        ussdApi.send(String.valueOf(binding.phone.getText()), message121 -> {
                            Timber.i(message121);
                            binding.result.append("\n-\n" + message121);
                            // second option list - select option 1
                            ussdApi.send(String.valueOf(binding.card.getText()), message1211 -> {
                                Timber.i(message1211);
                                binding.result.append("\n-\n" + message1211);


                             ussdApi.send("1", message12111 -> {
                                Timber.i(message12111);
                                binding.result.append("\n-\n" + message12111);


                            ussdApi.send(String.valueOf(binding.pin.getText()), message121111 -> {
                                Timber.i(message121111);
                                binding.result.append("\n-\n" + message121111);
                                ussdApi.send("2", message1211111 -> {
                                    Timber.i(message1211111);
                                    binding.result.append("\n-\n" + message1211111);

                            });
                            });
                        });
                            });
                        });
                        Timber.i("successful"); });
            //         ussdApi.cancel();
                }

                @Override
                public void over(String message) {
                    Timber.i(message);
                    binding.result.append("\n-\n" + message);
//                        mViewModel.setResult(dao);
//                        mViewModel.update();
                }
            });
        });





        binding.btn3.setOnClickListener(view ->
                USSDController.verifyAccesibilityAccess(getActivity()));

        return binding.getRoot();
    }

   /*private void callOverlay(Intent overlayDialogService) {
        ussdApi.callUSSDOverlayInvoke(getPhoneNumber(), map, new USSDController.CallbackInvoke() {
            @Override
            public void responseInvoke(String message) {
                Timber.i(message);
                binding.result.append("\n-\n" + message);
                // first option list - select option 1
                ussdApi.send("1", message1 -> {
                    Timber.i(message1);
                    binding.result.append("\n-\n" + message1);
                    // second option list - select option 2
                    ussdApi.send("2", message2 -> {
                        Timber.i(message2);
                        binding.result.append("\n-\n" + message2);
                        // second option list - select option 1
                        ussdApi.send("1", message3 -> {
                            Timber.i(message3);
                            binding.result.append("\n-\n" + message3);
                            getActivity().stopService(overlayDialogService);
                            Timber.i("successful");
                        });
                    });
                });
//                            ussdApi.cancel();
            }

            @Override
            public void over(String message) {
                Timber.i(message);
                binding.result.append("\n-\n" + message);
                getActivity().stopService(overlayDialogService);
                Timber.i("STOP OVERLAY DIALOG");
            }
        });
    }*/

    /*private void pendingServiceIntent(Intent overlayService) {
        getActivity().startService(overlayService);
        Timber.i(getString(R.string.overlayDialog));
        new Handler().postDelayed(() -> getActivity().stopService(overlayService), 12000);
        binding.result.setText("");
    }*/

    private String getPhoneNumber() {
        String p="*99*1#";
        return p;
    }

    private PermissionService.Callback callback = new PermissionService.Callback() {
        @Override
        public void onResponse(ArrayList<String> refusePermissions) {
            if (refusePermissions != null) {
                Toast.makeText(getContext(),
                        getString(R.string.refuse_permissions), Toast.LENGTH_SHORT).show();
                getActivity().finish();
            }
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Timber.i(MessageFormat.format(getString(R.string.primissionsLogFormat), permissions, grantResults));
        callback.handler(permissions, grantResults);
    }
}

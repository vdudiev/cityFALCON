package com.example.cityfalcon.Fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cityfalcon.R;
import com.example.cityfalcon.Networking.RegistrationResponse;
import com.example.cityfalcon.Networking.RetrofitCreate;
import com.example.cityfalcon.Adapters.SectorsAdapter;
import com.example.cityfalcon.Models.SectorsArticle;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SectorsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SectorsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public SectorsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SectorsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SectorsFragment newInstance(String param1, String param2) {
        SectorsFragment fragment = new SectorsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    Context context;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_sectors, container, false);

        RegistrationResponse registrationResponse = new RegistrationResponse();
        recyclerView = root.findViewById(R.id.recyclerview_sectors);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        context = getActivity();

        RetrofitCreate.getRetrofit().GetSectors(registrationResponse.getAccept(),
                registrationResponse.getAuthorization()).enqueue(new Callback<SectorsArticle>() {
            @Override
            public void onResponse(Call<SectorsArticle> call, Response<SectorsArticle> response) {
            SectorsAdapter adapter = new SectorsAdapter(response.body().getSectors(),context);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<SectorsArticle> call, Throwable t) {

            }
        });

        return root;
    }

}

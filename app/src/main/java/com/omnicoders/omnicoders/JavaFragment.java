package com.omnicoders.omnicoders;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.omnicoders.omnicoders.Constants.FirebaseDatabaseName;
import com.omnicoders.omnicoders.Model.GetFirebaseData;
import com.omnicoders.omnicoders.Model.ViewHolder;

import static com.facebook.FacebookSdk.getApplicationContext;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link JavaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link JavaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class JavaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    //TODO Firebase Declaration
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mFirebaseDatabase;
    private Context context;

    public JavaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment JavaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static JavaFragment newInstance(String param1, String param2) {
        JavaFragment fragment = new JavaFragment();
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

    //TODO the method loadFirebaseData is to load all the  data from the firebase database
    private void loadFirebaseData() {

        FirebaseRecyclerAdapter<GetFirebaseData, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<GetFirebaseData, ViewHolder>(
                        GetFirebaseData.class,
                        R.layout.single_item_view,
                        ViewHolder.class,
                        mDatabaseReference // like : select * from notice_board where uid =
                ) {

                    @Override
                    protected void populateViewHolder(final ViewHolder viewHolder, final GetFirebaseData model, int position) {

                        viewHolder.setData(model.getTitle(), model.getContent());

                        //TODO Add OnClick Listener to show content of the the title
                        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //TODO Add detailActivity and pass data
                                String content = model.getContent();
                                String title = model.getTitle();
                                Intent i = new Intent(context, DetailActivity.class);
                                i.putExtra("content", content);
                                i.putExtra("title", title);
                                startActivity(i);
                            }
                        });
                    }

                };
        //set RecyclerView Adapter

        //rv.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_java, container, false);

        // TODO Setting up Firebase Database path in databaseReference.
        this.context = getApplicationContext();

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mDatabaseReference = mFirebaseDatabase.getReference(FirebaseDatabaseName.DATA);
        mDatabaseReference.keepSynced(true);

        recyclerView = v.findViewById(R.id.rv);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);

        //TODO Called The method loadFirebaseData
        loadFirebaseData();

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        loadFirebaseData();
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

package com.myownapps.yasser.shoping.fragments_of_baseactivity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.myownapps.yasser.shoping.R;
import com.myownapps.yasser.shoping.constants;
import com.myownapps.yasser.shoping.model.Shoppinglist;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Shopinglist_fistfragmt.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Shopinglist_fistfragmt#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Shopinglist_fistfragmt extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Shopinglist_fistfragmt() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Shopinglist_fistfragmt.
     */
    // TODO: Rename and change types and number of parameters
    public static Shopinglist_fistfragmt newInstance(String param1, String param2) {
        Shopinglist_fistfragmt fragment = new Shopinglist_fistfragmt();
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
    EditText mEDITTEXTLISTNAME;
    TextView mtextviewlistname,mtextviewownername,getMtextviewtime;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview= inflater.inflate(R.layout.fragment_shopinglist_fistfragmt, container, false);

        mtextviewlistname=(TextView)rootview.findViewById(R.id.mtextviewlistname);
        mtextviewownername=(TextView)rootview.findViewById(R.id.mtextviewownername );
        getMtextviewtime=(TextView)rootview.findViewById(R.id.mtextviewtime);

        Firebase.setAndroidContext(getActivity());


        Firebase lnr=new Firebase(constants.FIRBASE_URL).child("activeList");
        lnr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Shoppinglist shoppinglist=dataSnapshot.getValue(Shoppinglist.class);
                mtextviewlistname.setText(shoppinglist.getListname());
                mtextviewownername.setText(shoppinglist.getOwner());

                long time=shoppinglist.getDateCreatedLong();

                String t=formatDate(time);

                getMtextviewtime.setText(t);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



        return rootview;
    }

    private String formatDate(long milliseconds) /* This is your topStory.getTime()*1000 */ {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy' 'HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        TimeZone tz = TimeZone.getDefault();
        sdf.setTimeZone(tz);
        return sdf.format(calendar.getTime());
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

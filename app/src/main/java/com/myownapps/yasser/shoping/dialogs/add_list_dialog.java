package com.myownapps.yasser.shoping.dialogs;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.firebase.client.ServerValue;
import com.myownapps.yasser.shoping.R;
import com.myownapps.yasser.shoping.constants;
import com.myownapps.yasser.shoping.model.Shoppinglist;

import java.util.HashMap;

/**
 * Created by Yasser on 11/21/2016.
 */

public class add_list_dialog extends DialogFragment implements View.OnClickListener {

    EditText list_name;
    Button create;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootview=inflater.inflate(R.layout.add_list_dialog,null);
        list_name=(EditText)rootview.findViewById(R.id.list_name);
        create=(Button)rootview.findViewById(R.id.button_create);
        create.setOnClickListener(this);

        return rootview;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    @Override
    public void onClick(View v) {
        addshoppinglist();


    }


    public void addshoppinglist(){



        Firebase ref=new Firebase(constants.FIRBASE_URL);
        String usreentername =list_name.getText().toString();
        HashMap<String, Object> timestampCreated = new HashMap<>();
        timestampCreated.put("time", ServerValue.TIMESTAMP);
//Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP
        Shoppinglist shoppinglist=new Shoppinglist(usreentername,"Anonymous owner",timestampCreated);

        // ref.child("listname").setValue(usreentername);

        ref.child("activeList").setValue(shoppinglist);


    }
}

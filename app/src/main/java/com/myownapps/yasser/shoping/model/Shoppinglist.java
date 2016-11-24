package com.myownapps.yasser.shoping.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.firebase.client.ServerValue;

import java.util.HashMap;

/**
 * Created by Yasser on 11/10/2016.
 */

public class Shoppinglist {
    String listname;
    String owner;

    private HashMap<String, Object> dateCreated;

    public Shoppinglist() {
    }

    public Shoppinglist(String listname, String owner, HashMap<String, Object> dateCreated) {
        this.listname = listname;
        this.owner = owner;
        this.dateCreated = dateCreated;

    }

    public String getListname() {
        return listname;
    }

    public String getOwner() {
        return owner;
    }



    public HashMap<String, Object> getDateCreated() {
        //If there is a dateCreated object already, then return that
        if (dateCreated != null) {
            return dateCreated;
        }
        //Otherwise make a new object set to ServerValue.TIMESTAMP
        HashMap<String, Object> dateCreatedObj = new HashMap<String, Object>();
        dateCreatedObj.put("time", ServerValue.TIMESTAMP);
        return dateCreatedObj;
    }

    // Use the method described in http://stackoverflow.com/questions/25500138/android-chat-crashes-on-datasnapshot-getvalue-for-timestamp/25512747#25512747
// to get the long values from the date object.


    @JsonIgnore
    public long getDateCreatedLong() {
        return (long)dateCreated.get("time");
    }

}

package com.example.travelexpertsandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.ArrayList;

public class CustomerAdapter extends ArrayAdapter<Customer> {

    // invoke the suitable constructor of the ArrayAdapter class
    public CustomerAdapter(@NonNull Context context, ArrayList<Customer> arrayList) {

        // pass the context and arrayList for the super
        // constructor of the ArrayAdapter class
        super(context, 0, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // if the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_view, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        Customer currentCustomerPosition = getItem(position);

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView textView1 = currentItemView.findViewById(R.id.textView1);
        textView1.setText("" + currentCustomerPosition.getCustomerId());

        // then according to the position of the view assign the desired TextView 2 for the same
        TextView textView2 = currentItemView.findViewById(R.id.textView2);
        textView2.setText(currentCustomerPosition.getCustFirstName());

        TextView textView3 = currentItemView.findViewById(R.id.textView3);
        textView3.setText(currentCustomerPosition.getCustLastName());

        TextView textView4 = currentItemView.findViewById(R.id.textView4);
        textView4.setText(currentCustomerPosition.getCustAddress());

        TextView textView5 = currentItemView.findViewById(R.id.textView5);
        textView5.setText(currentCustomerPosition.getCustCity());

        TextView textView6 = currentItemView.findViewById(R.id.textView6);
        textView6.setText(currentCustomerPosition.getCustProv());

        TextView textView7 = currentItemView.findViewById(R.id.textView7);
        textView7.setText(currentCustomerPosition.getCustPostal());

        TextView textView8 = currentItemView.findViewById(R.id.textView8);
        textView8.setText(currentCustomerPosition.getCustCountry());

        TextView textView9 = currentItemView.findViewById(R.id.textView9);
        textView9.setText(currentCustomerPosition.getCustHomePhone());

        TextView textView10 = currentItemView.findViewById(R.id.textView10);
        textView10.setText(currentCustomerPosition.getCustBusPhone());

        TextView textView11 = currentItemView.findViewById(R.id.textView11);
        textView11.setText(currentCustomerPosition.getCustEmail());

        TextView textView12 = currentItemView.findViewById(R.id.textView12);
        textView12.setText("" + currentCustomerPosition.getAgentId());

        // then return the recyclable view
        return currentItemView;
    }
}


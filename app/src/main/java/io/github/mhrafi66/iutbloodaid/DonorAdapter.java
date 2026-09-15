package io.github.mhrafi66.iutbloodaid;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class DonorAdapter extends RecyclerView.Adapter<DonorAdapter.DonorHolder> {
    private Context context;
    private ArrayList<Donor> donors;

    public static class DonorHolder extends RecyclerView.ViewHolder {

        TextView name;
        Button reqbtn;

        public DonorHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.receive_single_name);
            reqbtn=itemView.findViewById(R.id.requestbtn);
        }
    }
    public DonorAdapter(Context context, ArrayList<Donor> donors) {
        this.donors = donors;
        this.context = context;
    }

    @Override
    public DonorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_donor_adapter, parent, false);

        //     view.setOnClickListener(MainActivity.myOnClickListener);

        DonorHolder myViewHolder = new DonorHolder(view);
        return myViewHolder;
    }
    @Override
    public void onBindViewHolder(final DonorHolder myViewHolder, final int listPosition) {

        TextView nameView = myViewHolder.name;
        Button reqbtn=myViewHolder.reqbtn;



        String nameofdonor = donors.get(listPosition).getName();
        final String phoneofdonor=donors.get(listPosition).getPhone();


        nameView.setText(nameofdonor);

        reqbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCall = new Intent(Intent.ACTION_DIAL);
                Log.e("Number",phoneofdonor);
                intentCall.setData(Uri.parse("tel:" + phoneofdonor));
                context.startActivity(intentCall);
            }
        });

    }
    @Override
    public int getItemCount() {
        String ds=String.valueOf(donors.size());
        Log.e("Donors:",ds);
        return donors.size();
    }
}

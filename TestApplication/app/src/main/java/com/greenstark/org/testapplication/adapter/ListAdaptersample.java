package com.greenstark.org.testapplication.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.greenstark.org.testapplication.R;
import com.greenstark.org.testapplication.modal.EntityValues;

import java.util.List;


public class ListAdaptersample extends RecyclerView.Adapter<ListAdaptersample.CustomeAdapterHolder> {

    public String TAG = "ApproveAdapter";
    List<EntityValues> entityValues;
    private int rowLayout;
    private Context context;



    public static class CustomeAdapterHolder extends RecyclerView.ViewHolder {
        TextView name;

        public CustomeAdapterHolder(View view) {
            super(view);

            name = (TextView) view.findViewById(R.id.name);

        }
    }

    public ListAdaptersample(List<EntityValues> entityValues, int rowLayout, Context context) {
        this.entityValues = entityValues;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public CustomeAdapterHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new CustomeAdapterHolder(view);
    }


    @Override
    public void onBindViewHolder(CustomeAdapterHolder holder, final int position) {


        holder.name.setText(entityValues.get(position).getUserId().getFirstName());
    }

    @Override
    public int getItemCount() {
        return entityValues.size();
    }





}


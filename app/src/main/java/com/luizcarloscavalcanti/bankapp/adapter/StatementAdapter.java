package com.luizcarloscavalcanti.bankapp.adapter;


import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.luizcarloscavalcanti.bankapp.R;
import com.luizcarloscavalcanti.bankapp.model.StatementList;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class StatementAdapter extends RecyclerView.Adapter<StatementAdapter.MyViewHolder> {

    private Context mContext;
    private List<StatementList> statementListsData;

    public StatementAdapter(Context mContext) {
        this.mContext = mContext;
        statementListsData = new ArrayList<>();

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.statement_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        SimpleDateFormat unformattedPattern = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formattedPattern = new SimpleDateFormat("dd/MM/yyyy");
        try {
            Date date = unformattedPattern.parse(statementListsData.get(position).getDate());
            holder.text_date.setText(formattedPattern.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Locale ptBr = new Locale("pt", "BR");
        String ptBrBalance = NumberFormat.getCurrencyInstance(ptBr).format(statementListsData.get(position).getValue());

        holder.text_title.setText(statementListsData.get(position).getTitle());
        holder.text_description.setText(statementListsData.get(position).getDesc());
        holder.text_value.setText(ptBrBalance);

    }

    @Override
    public int getItemCount() {
        return statementListsData.size();
    }

    public void addStatementList(List<StatementList> statementLists) {
        statementListsData.addAll(statementLists);
        notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView text_title, text_description, text_value, text_date;

        public MyViewHolder(View itemView){
            super(itemView);

            text_title = itemView.findViewById(R.id.textOperation);
            text_description = itemView.findViewById(R.id.textDescOperation);
            text_date = itemView.findViewById(R.id.textDate);
            text_value = itemView.findViewById(R.id.textValue);

        }
    }
}
package com.luizcarloscavalcanti.bankapp.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.luizcarloscavalcanti.bankapp.R;
import com.luizcarloscavalcanti.bankapp.models.StatementListModel;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class StatementListAdapter extends RecyclerView.Adapter<StatementListAdapter.MyViewHolder> {

    private final Context mContext;
    private List<StatementListModel> statementsList;

    public StatementListAdapter(Context mContext) {
        this.mContext = mContext;
        statementsList = new ArrayList<>();
    }

    public void addStatementList(List<StatementListModel> statementListModels) {
        this.statementsList = statementListModels;
        notifyDataSetChanged();
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
            Date date = unformattedPattern.parse(statementsList.get(position).getDate());
            holder.textDate.setText(formattedPattern.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Locale ptBr = new Locale("pt", "BR");
        String ptBrBalance = NumberFormat.getCurrencyInstance(ptBr)
                .format(statementsList.get(position).getValue());

        holder.textOperationTitle.setText(statementsList.get(position).getTitle());
        holder.textDescription.setText(statementsList.get(position).getDesc());
        holder.textValue.setText(ptBrBalance);
    }

    @Override
    public int getItemCount() { return statementsList.size(); }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textOperationTitle, textDescription, textValue, textDate;

        public MyViewHolder(View itemView){
            super(itemView);

            textOperationTitle = itemView.findViewById(R.id.textOperation);
            textDescription = itemView.findViewById(R.id.textDescOperation);
            textDate = itemView.findViewById(R.id.textDate);
            textValue = itemView.findViewById(R.id.textValue);
        }
    }
}
package com.example.justjava;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.justjava.model.HistoryModel;

import java.util.List;

/**
 * Created by pratama on 11/6/2016.
 */

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {

    private List<HistoryModel> historyList;

    private ClickListener clickListener;

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements RecyclerView.OnClickListener {
        public TextView name, menu, quantity, price;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name);
            menu = (TextView) view.findViewById(R.id.menu);
            price = (TextView) view.findViewById(R.id.price);
            quantity = (TextView) view.findViewById(R.id.quantity);

            if (clickListener != null) {
                view.setOnClickListener(this);
            }


        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getPosition());
        }
    }


    public HistoryAdapter(List<HistoryModel> HistoryList) {
        this.historyList = HistoryList;
    }

    public void delete(int position) {
        HistoryModel historyModels = HistoryModel.findById(HistoryModel.class, historyList.get(position).getId());
        historyModels.delete(); //menghapus data di database
        historyList.remove(position); //menghapus data di list
        this.notifyDataSetChanged(); //menotifikasi ketika ada data yg berubah
    }


    @Override
    public HistoryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_row, parent, false); //menggunakan layout history

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        HistoryModel History = historyList.get(position); //mengambi data historymodel sesuai posisi
        holder.name.setText(History.getName());
        holder.menu.setText(History.getMenu());
        holder.quantity.setText("quantity : " + History.getQuantity()); //memprint data quantitiy
        holder.price.setText("price : " + History.getPrice()); //mempriint data price

    }


    @Override
    public int getItemCount() {
        return historyList.size();
    }

    public interface ClickListener { //untuk klik
        public void onClick(View v, int position);
    }

}

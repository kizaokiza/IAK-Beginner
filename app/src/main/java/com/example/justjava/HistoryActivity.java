package com.example.justjava;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.justjava.model.HistoryModel;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends BaseActivity implements HistoryAdapter.ClickListener {

    //baseacitivy as parent
    //implement itu berarti mengimplementasikan method clicklitener yg ada pada class history adapter

    private List<HistoryModel> historyList = new ArrayList<>();
    private RecyclerView recyclerView;
    private HistoryAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        setTitle("History"); //menganti title
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        historyList = HistoryModel.find(HistoryModel.class, null, null, null, "id DESC", null); //mengambil data history model secara descending
        mAdapter = new HistoryAdapter(historyList); //mengirim data history ke history adapter
        mAdapter.setClickListener(this);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);


    }


    @Override
    public void onClick(View v, final int position) { //ketika row di klik
        final android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setMessage("apakah kamu mau menghapus data ini ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mAdapter.delete(position); //menghapus data dr adapter (ctrl + click di tulisan delete) untuk melihat info lbh jelas
                Toast.makeText(HistoryActivity.this, "Data telah terhapus", Toast.LENGTH_SHORT).show();
                builder.create().dismiss();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                builder.create().dismiss();

            }
        });
        final android.support.v7.app.AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }
}

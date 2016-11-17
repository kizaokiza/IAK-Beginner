package com.example.justjava;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.justjava.model.CalculatorModel;
import com.example.justjava.model.HistoryModel;
import com.joanzapata.iconify.IconDrawable;
import com.joanzapata.iconify.fonts.FontAwesomeIcons;
import com.joanzapata.iconify.widget.IconTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalculatorActivity extends BaseActivity {

    //mendeklarasikan
    @BindView(R.id.quantity)
    TextView quantityTextView;

    @BindView(R.id.price)
    TextView priceTextView;

    @BindView(R.id.fontawesome)
    IconTextView fontawesome;

    @BindView(R.id.editTextName)
    EditText editTextName;

    @BindView(R.id.checkboxWhipped)
    CheckBox checkboxWhipped;

    @BindView(R.id.checkboxChoco)
    CheckBox checkboxChoco;
//moe
    private int quantity = 0;
    private String price;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

//        membuat icon di kanan atas
        menu.add("history").setTitle("history")
                .setIcon(new IconDrawable(this, FontAwesomeIcons.fa_history)
                        .colorRes(android.R.color.white).actionBarSize())
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String title = item.getTitle().toString();
        if (title.equals("history")) { //aksi saat menu bertitle "history" di klik
            Intent intent = new Intent(CalculatorActivity.this, HistoryActivity.class);
            startActivity(intent);
        } else {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        ButterKnife.bind(this); //init untuk butterknife
    }

    //button
    public void increase(View v) {
        if (quantity < 5) { //membuat tombol increase (+) tidak bisa menambah data jika nilai quantity telah 5
            quantity++; //menambah nilaii +1
            display();
        }

    }

    public void decrease(View v) {
        if (quantity > 0) { //membuat tombol decrease(-) tidak bisa mengurangi data jika nilai quantity 0
            quantity--; //mengurangi nilai -1
            display();
        }

    }

    //logic
    private void display() {
        quantityTextView.setText(quantity + ""); //memprint nilai quantitiy
        price(); //menampilkan harga
    }

    public void orderonClick(View v) {
        showOrder();  //menambilkan result
    }

    private void price() {
        int p = quantity * 5;
        price = "$" + p;
        priceTextView.setText(price);
    }

    private void showOrder() {
        boolean isValid = true; //defaulnya ini valid
        if (TextUtils.isEmpty(editTextName.getText())) { //jika user tidak mengisi nama
            editTextName.setError("Mohon isi nama dahulu");
            isValid = false; // tidak valid
        }
        if (!checkboxChoco.isChecked() && !checkboxWhipped.isChecked()) { //jika user tidak memilih choco/whipped cream
            isValid = false; //tidak valid
            Toast.makeText(this, "mohon pilih whipped cream atau chocolate terlebih dahulu", Toast.LENGTH_SHORT).show();
        }


        if (isValid) {
            priceTextView.setText(price + "\nThank your for order");

            String menu = "";
            if (checkboxChoco.isChecked()) { //jika checkbox choco di check maka print choco
                menu += "choco ";
            }
            if (checkboxWhipped.isChecked()) { //jika checkbox choco di check maka print choco
                menu += "whippe cream ";
            }

            HistoryModel historyModel = new HistoryModel();
            historyModel.setName(editTextName.getText().toString());
            historyModel.setMenu(menu);
            historyModel.setQuantity(quantity);
            historyModel.setPrice(price);
            historyModel.save(); //menyimpan data ke database

            Toast.makeText(CalculatorActivity.this, "Terima kasih telah mengorder", Toast.LENGTH_SHORT).show();
            String coffee = "";
            for (int i = 0; i < quantity; i++) {
                coffee += "{fa-coffee} ";
            }
            fontawesome.setText(coffee);
        }

    }


}

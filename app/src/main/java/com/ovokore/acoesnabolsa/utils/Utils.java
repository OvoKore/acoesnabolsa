package com.ovokore.acoesnabolsa.utils;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

public class Utils {

    public static void showAlertDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message).setNegativeButton("OK", (dialog, id) -> {});
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}

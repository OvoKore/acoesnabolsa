package com.ovokore.acoesnabolsa.utils;

import static com.ovokore.acoesnabolsa.utils.GlobalValues.DELETE;
import static com.ovokore.acoesnabolsa.utils.GlobalValues.OK;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.ovokore.acoesnabolsa.R;
import com.ovokore.acoesnabolsa.persistence.AppDatabase;

public class Utils {

    public static void showAlertDialog(Context context, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message);
        builder.setPositiveButton(OK, (dialog, id) -> {
        });
        builder.show();
    }

    public static void showDeleteConfirmationDialog(Context context, Object value) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.ConfirmDelete));
        builder.setMessage(context.getString(R.string.ConfirmDeleteMessage));
        builder.setPositiveButton(context.getString(R.string.Excluir), (dialog, id) -> {
            AppDatabase.getDatabase(context).callDynamicMethod(value.getClass(), DELETE, value);
            ((Activity) context).finish();
        });
        builder.setNegativeButton(context.getString(R.string.Cancelar), null);
        builder.show();
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}

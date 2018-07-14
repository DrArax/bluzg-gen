package com.drarax.bluzggen.feature;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;

public class Inne {

    public void KopiujDoSchowka(View view, Context context, String bluzga) {
        ClipboardManager clipMan = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Bluzga", bluzga);
        if (clip != null) {
            clipMan.setPrimaryClip(clip);
        }
    }
}

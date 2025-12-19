package com.counting.kidsgame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.counting.kidsgame.activity.MainActivity;
import com.squareup.picasso.Picasso;

public class CustomArrayAdapter extends ArrayAdapter<CustomData> {
    private final int arrSize;
    private final LayoutInflater mInflater = ((LayoutInflater) getContext().getSystemService("layout_inflater"));
    private final Context mcontext;
    private SharedPreference settingSp;

    private static class Holder {
        public ImageView imageView;
        public ImageView lockView;

        private Holder() {
        }
    }

    public CustomArrayAdapter(Context context, CustomData[] customDataArr) {
        super(context, R.layout.custom_data_view, customDataArr);
        this.arrSize = customDataArr.length;
        this.mcontext = context;
        if (this.settingSp == null) {
            this.settingSp = new SharedPreference(SharedPreference.PREFS_NAME_AL, SharedPreference.PREFS_KEY_AL);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(int i, ImageView imageView, int i2) {
        boolean loveCatLocked = i2 == 16 ? this.settingSp.getLoveCatLocked(MainActivity.context) : false;
        if (i2 == 17) {
            loveCatLocked = this.settingSp.getrealCatLocked(MainActivity.context);
        }
        if (i2 == 18) {
            loveCatLocked = this.settingSp.getcuteCatLocked(MainActivity.context);
        }
        if (SharedPreference.getBuyChoise(MainActivity.context) != 0 || loveCatLocked) {
            imageView.setVisibility(View.GONE);
            return;
        }
        if (i == 0) {
            imageView.setImageResource(0);
        }


        imageView.setVisibility(View.VISIBLE);
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder holder;
        if (view == null) {
            view = this.mInflater.inflate(R.layout.custom_data_view, viewGroup, false);
            holder = new Holder();
            holder.imageView = (ImageView) view.findViewById(R.id.menubg);
            holder.lockView = (ImageView) view.findViewById(R.id.lockView);
            view.setTag(holder);
        } else {
            holder = (Holder) view.getTag();
        }
        try {
            Picasso.get().load(((CustomData) getItem(i)).getDrawableID()).into(holder.imageView);
        } catch (Exception unused) {
            holder.imageView.setImageResource(((CustomData) getItem(i)).getDrawableID());
        }
        a(((CustomData) getItem(i)).getOpenDay(), holder.lockView, i);
        return view;
    }
}

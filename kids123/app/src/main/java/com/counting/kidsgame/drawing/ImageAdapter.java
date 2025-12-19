package com.counting.kidsgame.drawing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.counting.kidsgame.MyConstant;
import com.counting.kidsgame.R;
import com.squareup.picasso.Picasso;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private View myView;

    public ImageAdapter(Context context) {
        System.err.println("image adapter");
        this.mContext = context;
    }

    public int getCount() {
        return MyConstant.selected_bitmapIds.length;
    }

    public Object getItem(int i) {
        return MyConstant.selected_bitmapIds[i];
    }

    public long getItemId(int i) {
        return 0;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        int i2 = MyConstant.widthInPixels;
        int i3 = (i2 / 2) - (i2 / 5);
        int i4 = (i3 * 2) / 2;
        if (view == null) {
            view = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(R.layout.grid_layout_view, viewGroup, false);
        }
        FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.imageView);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageViewInside);
        ImageView imageView2 = (ImageView) view.findViewById(R.id.lock);
        frameLayout.setForegroundGravity(17);
        frameLayout.setLayoutParams(new ViewGroup.LayoutParams(i3, i4));
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        Picasso.get().load(MyConstant.selected_bitmapIds[i].intValue()).into(imageView);
        return frameLayout;
    }
}

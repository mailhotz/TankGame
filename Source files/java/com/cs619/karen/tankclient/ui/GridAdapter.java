package com.cs619.karen.tankclient.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.SystemService;

import com.cs619.karen.tankclient.R;

@EBean
public class GridAdapter extends BaseAdapter {

    private final Object monitor = new Object();
    @SystemService
    protected LayoutInflater inflater;
    private int[][] mEntities = new int[16][16];
    private static final String TAG = "GridAdapter";


    //TODO
    //Default constructor
   /* public GridAdapter(Context c) {
        context = c;
    }*/
    //TODO

    public void updateList(int[][] entities) {
        System.out.println("Before structure");
        synchronized (monitor) {
            this.mEntities = entities;
            this.notifyDataSetChanged();
            System.out.println("Notified ");
        }
        System.out.println("After structure");
    }

    @Override
    public int getCount() {
        return 16 * 16;
    }

    @Override
    public Object getItem(int position) {
        return mEntities[(int) position / 16][position % 16];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        System.out.println("We in dis bitch");
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.field_item, null);
        }
        System.out.println("We made it");
        int row = position / 16;
        int col = position % 16;

        int val = mEntities[row][col];

        if (convertView instanceof TextView) {
            synchronized (monitor) {
                if (val > 0) {
                    if (val == 1000 ) {
                        ((TextView) convertView).setText("W");
                    } else if (val >= 2000000 && val <= 3000000) {
                        ((TextView) convertView).setText("B");
                    } else if (val >= 10000000 && val <= 20000000) {
                        ((TextView) convertView).setText("T");
                    }
                } else {
                    ((TextView) convertView).setText("");
                }
            }
        }

        return convertView;
    }
}



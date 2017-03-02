package com.jdkgroup.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jdkgroup.drawer.R;
import com.jdkgroup.general.General;
import com.jdkgroup.general.GeneralImplement;
import com.jdkgroup.model.DrawerItem;
import com.jdkgroup.utils.AppKeyword;
import com.jdkgroup.utils.GlobalClass;

import java.util.List;

public class AdapterDrawer extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;
    private static final int TYPE_FOOTER = 2;

    private List<DrawerItem> alDrawerItem;
    private Activity activity;

    private OnItemSelecteListener mListener;
    private SharedPreferences mPref;
    private SharedPreferences.Editor mEditor;
    private int lastPosition = -1;

    private General general;
    private GlobalClass globalClass;


    public AdapterDrawer(Activity activity, List<DrawerItem> alDrawerItem) {
        this.activity = activity;
        this.alDrawerItem = alDrawerItem;

        mPref = activity.getSharedPreferences("person", Context.MODE_PRIVATE);
        mEditor = mPref.edit();

        general = new GeneralImplement();
        globalClass = GlobalClass.getInstance();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_HEADER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_drawer_header, parent, false);
            return new HeaderViewHolder(v);
        } else if (viewType == TYPE_FOOTER) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_drawer_footer, parent, false);
            return new FooterViewHolder(v);
        } else if (viewType == TYPE_ITEM) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemview_drawer, parent, false);
            return new GenericViewHolder(v);
        }
        return null;
    }

    private DrawerItem getItem(int position) {
        return alDrawerItem.get(position);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;

        } else if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerHolder = (FooterViewHolder) holder;

        } else if (holder instanceof GenericViewHolder) {
            DrawerItem drawerItem = getItem(position - 1);
            GenericViewHolder genericViewHolder = (GenericViewHolder) holder;

            genericViewHolder.actTitle.setText(drawerItem.getTitle());
            general.ImageViewSetBG(activity, genericViewHolder.ivIcon, drawerItem.getImage(), AppKeyword.FOLDER_DRAWABLE, "", "", 1);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isPositionHeader(position)) {
            return TYPE_HEADER;
        } else if (isPositionFooter(position)) {
            return TYPE_FOOTER;
        }
        return TYPE_ITEM;
    }

    private boolean isPositionHeader(int position) {
        return position == 0;
    }

    private boolean isPositionFooter(int position) {
        return position == alDrawerItem.size() + 1;
    }

    @Override
    public int getItemCount() {
        return alDrawerItem.size() + 2;
    }

    class FooterViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;

        public FooterViewHolder(View itemView) {
            super(itemView);
            recyclerView = (RecyclerView) itemView.findViewById(R.id.recyclerView);
        }
    }

    class HeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private AppCompatTextView actLogout, actUsername, actAddress;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            this.actLogout = (AppCompatTextView) itemView.findViewById(R.id.actLogout);
            this.actUsername = (AppCompatTextView) itemView.findViewById(R.id.actUsername);
            this.actAddress = (AppCompatTextView) itemView.findViewById(R.id.actAddress);

            general.FontAppCompatTextView(actLogout, AppKeyword.sourcesanspro_regular);
            general.FontAppCompatTextView(actUsername, AppKeyword.sourcesanspro_bold);
            general.FontAppCompatTextView(actAddress, AppKeyword.sourcesanspro_regular);

            actLogout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.actLogout:
                    general.SwitchPassdataChangeFragment(activity, AppKeyword.REDIRECT_LOGOUT_6_0);
                    break;
            }
        }
    }

    class GenericViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView actTitle;
        private ImageView ivIcon;

        public GenericViewHolder(View itemView) {
            super(itemView);
            this.actTitle = (AppCompatTextView) itemView.findViewById(R.id.actTitle);
            this.ivIcon = (ImageView) itemView.findViewById(R.id.ivIcon);

            general.FontAppCompatTextView(actTitle, AppKeyword.sourcesanspro_regular);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemSelected(view, getAdapterPosition());
                }
            });
        }
    }

    public interface OnItemSelecteListener {
        public void onItemSelected(View v, int position);
    }

    public void setOnItemClickLister(OnItemSelecteListener mListener) {
        this.mListener = mListener;
    }


}
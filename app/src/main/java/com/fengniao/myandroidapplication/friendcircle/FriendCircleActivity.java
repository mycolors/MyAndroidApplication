package com.fengniao.myandroidapplication.friendcircle;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.fengniao.myandroidapplication.BaseActivity;
import com.fengniao.myandroidapplication.R;
import com.fengniao.myandroidapplication.banner.FriendCircleListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class FriendCircleActivity extends BaseActivity {
    @BindView(R.id.message_list)
    RecyclerView messageList;
    private List<String> mList;
    private FriendCircleListAdapter mAdapter;
    private Dialog mDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_circle);
        initView();
    }

    public void initView() {
        initList();
    }

    public void initList() {
        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        messageList.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new FriendCircleListAdapter(this, mList);
        messageList.setAdapter(mAdapter);
    }

    public void showDialog() {
        if (mDialog == null) {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
            mBuilder.setItems(new String[]{"更换相册封面"}, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    showToast("toast");
                }
            });
            mDialog = mBuilder.create();
        }
        mDialog.show();
    }
}


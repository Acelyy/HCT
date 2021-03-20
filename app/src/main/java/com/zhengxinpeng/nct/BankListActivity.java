package com.zhengxinpeng.nct;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.zhengxinpeng.nct.model.Bank;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class BankListActivity extends AppCompatActivity {

    private ListView list_bank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bank_list);
        list_bank = findViewById(R.id.list_bank);
        loadBank();
    }

    private void loadBank() {
        BmobQuery<Bank> query = new BmobQuery<>();
        query.findObjects(new FindListener<Bank>() {
            @Override
            public void done(List<Bank> list, BmobException e) {
                if (e == null && list != null && !list.isEmpty()) {
                    // 有题库
                    list_bank.setAdapter(new ArrayAdapter<Bank>(BankListActivity.this, android.R.layout.simple_list_item_1, list));
                    list_bank.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String objectId = list.get(position).getObjectId();
                            Intent intent = new Intent(BankListActivity.this, StudyActivity.class);
                            intent.putExtra("id", objectId);
                            startActivity(intent);
                        }
                    });
                } else {
                    Log.e("BMOB", e.toString());
                }
            }
        });
    }

}
package com.xy.wb.app;

import android.app.Activity;
import android.os.Bundle;

import com.wb.xy.lib.sqlite.SqliteHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    List<String> sqlCreate = new ArrayList<>();
    List<String> sqlUpgrade = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SqliteHelper sqliteHelper = SqliteHelper.getInstance(getApplicationContext());
        sqliteHelper.newSqlData("XYWB_001.db", 1);
        sqlCreate.add("CREATE TABLE IF NOT EXISTS USER ([id] VARCHAR(36) PRIMARY KEY, \n" +
                "[created] DATETIME NOT NULL, [modified] DATETIME NOT NULL,\n" +
                "[user_name] VARCHAR(32) NOT NULL, \n" +
                "[password] VARCHAR(32) NOT NULL,\n" +
                "[warehouse_code] VARCHAR(32) NOT NULL, \n" +
                "[warehouse_name] VARCHAR(32) NOT NULL, \n" +
                "[api_key] VARCHAR(32) NOT NULL,\n" +
                "[warehouse_id] VARCHAR(32)\n" +
                ")");
        sqliteHelper.setCreate(sqlCreate);
        sqliteHelper.setUpgrade(sqlUpgrade);
        sqliteHelper.getDataBase();
    }
}

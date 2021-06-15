package com.wb.xy.lib.sqlite.comm;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Iterator;
import java.util.List;

/**
 * Created by tanqimin on 2016/11/10.
 */
public class DatabaseOpenHelper extends SQLiteOpenHelper {
    //数据库名称
    private String name;
    //数据库版本
    private int version = 1;
    private SQLiteDatabase readableDatabase = null;
    private List<String> createSqlTalbe;    //创建语句
    private List<String> updateSqlTalbe;    //更新语句


    public DatabaseOpenHelper(Context context, String name, int version) {
        //第三个参数CursorFactory指定在执行查询时获得一个游标实例的工厂类,设置为null,代表使用系统默认的工厂类
        super(context, name, null, version);
        this.name = name;
        this.version = version;
    }

    public SQLiteDatabase getDatabase() {
        if (readableDatabase == null) {
            readableDatabase = this.getReadableDatabase();
        }
        return readableDatabase;
    }

    public void setCreate(List<String> createSqlTalbe) {
        this.createSqlTalbe = createSqlTalbe;
    }

    public void setUpgrade(List<String> updateSqlTalbe) {
        this.updateSqlTalbe = updateSqlTalbe;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (Iterator<String> iterator = createSqlTalbe.iterator(); iterator.hasNext(); ) {
            String sqlTable = iterator.next();
            db.execSQL(sqlTable);
        }
        final int FIRST_DATABASE_VERSION = 1;
        onUpgrade(db, FIRST_DATABASE_VERSION, version);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 使用for实现跨版本升级数据库
        for (int i = oldVersion; i < newVersion; i++) {
            db.execSQL(updateSqlTalbe.get(i));
        }
    }
}

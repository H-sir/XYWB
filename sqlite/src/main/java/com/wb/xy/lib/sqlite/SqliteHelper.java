package com.wb.xy.lib.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.wb.xy.lib.sqlite.comm.DatabaseOpenHelper;

import java.util.List;

/**
 * Created by HKR on 2021/6/15.
 */

public class SqliteHelper {

    private static SqliteHelper mSqliteHelper;
    private Context mContext;
    private DatabaseOpenHelper databaseOpenHelper;

    //构造函数用private修饰是为了防止在其他地方创建该实例
    private SqliteHelper(Context context) {
        this.mContext = context;
    }

    public static SqliteHelper getInstance(Context context) {
        /**
         *  此处判断singleMode == null,是为了防止singleMode已经初始化后，还会继续调用同步锁，造成不必要的损耗。
         */
        if (mSqliteHelper == null) {
            // 加锁的目的是为了防止多线程同时进入造成对象多次实例化
            synchronized (SqliteHelper.class) {
                // 此处判断singleMode == null,是为了防止对象重复实例化
                if (mSqliteHelper == null) {
                    mSqliteHelper = new SqliteHelper(context);
                }
            }
        }
        // 返回实例对象
        return mSqliteHelper;
    }

    /**
     * 实例化数据库表
     */
    public void newSqlData(String name, int version) {
        databaseOpenHelper = new DatabaseOpenHelper(mContext, name, version);
    }

    /**
     * 数据库建立时创建表
     * //创建用户表（用例）
     * db.execSQL("CREATE TABLE IF NOT EXISTS USER (\n" +
     * "  [id] VARCHAR(36) PRIMARY KEY, \n" +
     * "  [created] DATETIME NOT NULL, \n" +
     * "  [modified] DATETIME NOT NULL, \n" +
     * "  [user_name] VARCHAR(32) NOT NULL, \n" +
     * "  [password] VARCHAR(32) NOT NULL, \n" +
     * "  [warehouse_code] VARCHAR(32) NOT NULL, \n" +
     * "  [warehouse_name] VARCHAR(32) NOT NULL, \n" +
     * "  [api_key] VARCHAR(32) NOT NULL, \n" +
     * "  [warehouse_id] VARCHAR(32) \n" +
     * "  )");
     */
    public void setCreate(List<String> sqlTable) {
        databaseOpenHelper.setCreate(sqlTable);
    }

    /**
     * 更新数据库表
     */
    public void setUpgrade(List<String> sqlTable) {
        databaseOpenHelper.setUpgrade(sqlTable);
    }


    /**
     * 获取数据库
     */
    public SQLiteDatabase getDataBase() {
       return databaseOpenHelper.getDatabase();
    }
}

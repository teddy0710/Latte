package com.example.latte.ec.datebase;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * 使用green第三步
 * Created by 张枫霖 on 2017-08-30
 */

public class DatebaseManager {
    //DaoSession、UserProfileDao都是greendao 自动生成
    private DaoSession mDaoSession = null;
    private UserProfileDao mDao = null;

    private DatebaseManager() {
    }

    public DatebaseManager init(Context context) {
        initDao(context);
        return this;
    }

    private static final class Holder {
        private static final DatebaseManager INSTSNCE = new DatebaseManager();
    }

    public static DatebaseManager getInstance() {
        return Holder.INSTSNCE;
    }


    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "fast_ec.db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserProfileDao();
    }

    public final UserProfileDao getDao() {
        return mDao;
    }
}

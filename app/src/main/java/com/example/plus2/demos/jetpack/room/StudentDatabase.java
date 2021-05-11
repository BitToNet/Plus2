package com.example.plus2.demos.jetpack.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2021-01-15   16:31
 * desc   :
 */
@Database(entities = {Student.class},version = 1 ,exportSchema = false)
public abstract class StudentDatabase extends RoomDatabase {

    //单例模式
    private static StudentDatabase INSTANCE;
    public static synchronized StudentDatabase getDatabase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),StudentDatabase.class,"student").build();
        }
        return INSTANCE;
    }

    public abstract StudentDao getStudentDao();
}

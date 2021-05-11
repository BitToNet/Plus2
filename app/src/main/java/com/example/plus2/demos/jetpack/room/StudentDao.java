package com.example.plus2.demos.jetpack.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2021-01-15   16:07
 * desc   :
 */
@Dao
public interface StudentDao {

    @Insert
    void insetWords(Student... students);

    @Update
    void updateWords(Student... students);

    @Delete
    void deleteWords(Student... students);

    @Query("DELETE FROM student")
    void deleteAllWords();

    @Query("SELECT * FROM Student ORDER BY ID DESC")

    public LiveData<List<Student>>getAllStudentLive();
}

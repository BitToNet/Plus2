package com.example.plus2.demos.jetpack.room;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * author : Qiu Long
 * e-mail : 502578360@qq.com
 * date   : 2021-01-15   16:01
 * desc   :
 */
@Entity
public class Student {

    //主键唯一 自增
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")//优先使用列名注解里面的
    private int name;

    @ColumnInfo(name = "age")
    private int age;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

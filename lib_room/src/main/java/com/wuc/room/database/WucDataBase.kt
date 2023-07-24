package com.wuc.room.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.wuc.framework.helper.AppHelper
import com.wuc.room.dao.VideoListCacheDao
import com.wuc.room.entity.VideoInfo

/**
 * @author     wuchao
 * @date       2023/7/25 00:34
 * @desciption 数据库操作类
 * 指定有哪些表，version必须指定版本，exportSchema生成一个json文件，方便排查问题，还需要在build.gradle文件中配置
 */
@Database(entities = [VideoInfo::class], version = 1, exportSchema = false)
abstract class WucDataBase : RoomDatabase() {
    //抽象方法或者抽象类标记
    abstract fun videoListDao(): VideoListCacheDao

    companion object {
        private var dataBase: WucDataBase? = null

        //同步锁，可能在多个线程中同时调用
        @Synchronized
        fun getInstance(): WucDataBase {
            return dataBase ?: Room.databaseBuilder(AppHelper.getApplication(), WucDataBase::class.java, "WucTea_DB")
                //是否允许在主线程查询，默认是false
                .allowMainThreadQueries()
                //数据库被创建或者被打开时的回调
                //.addCallback(callBack)
                //指定数据查询的线程池，不指定会有个默认的
                //.setQueryExecutor {  }
                //任何数据库有变更版本都需要升级，升级的同时需要指定migration，如果不指定则会报错
                //数据库升级 1-->2， 怎么升级，以什么规则升级
                .addMigrations()
                //设置数据库工厂，用来链接room和SQLite，可以利用自行创建SupportSQLiteOpenHelper，来实现数据库加密
                //.openHelperFactory()
                .build()
        }
    }
}
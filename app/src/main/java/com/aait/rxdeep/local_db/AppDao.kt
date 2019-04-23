package com.aait.rxdeep.local_db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.aait.rxdeep.models.PostModel
import io.reactivex.Maybe
import io.reactivex.Observable

@Dao
interface AppDao {
    @Query("select * from posts ")
    fun getPosts(): List<PostModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPosts(posts:List<PostModel>)


}
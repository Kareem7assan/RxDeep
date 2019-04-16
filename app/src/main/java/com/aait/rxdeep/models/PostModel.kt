package com.aait.rxdeep.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostModel(
    var body: String?,
    var id: Int?,
    var title: String?,
    var userId: Int?
)

{
    @PrimaryKey(autoGenerate = true)
    var post_id: Int = 0
}
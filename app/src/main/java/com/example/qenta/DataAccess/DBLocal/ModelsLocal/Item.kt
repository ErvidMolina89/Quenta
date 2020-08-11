package com.example.qenta.DataAccess.DBLocal.ModelsLocal

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.qenta.Models.BaseModel


@Entity
data class Item (
    @PrimaryKey
    @ColumnInfo(name = "id")                var id: Int? = null,
    @ColumnInfo(name = "pageURL")           var pageURL: String? = null,
    @ColumnInfo(name = "type")              var type: String? = null,
    @ColumnInfo(name = "tags")              var tags: String? = null,
    @ColumnInfo(name = "previewURL")        var previewURL: String? = null,
    @ColumnInfo(name = "previewWidth")      var previewWidth: Int? = null,
    @ColumnInfo(name = "previewHeight")     var previewHeight: Int? = null,
    @ColumnInfo(name = "webformatURL")      var webformatURL: String? = null,
    @ColumnInfo(name = "webformatWidth")    var webformatWidth: Int? = null,
    @ColumnInfo(name = "webformatHeight")   var webformatHeight: Int? = null,
    @ColumnInfo(name = "largeImageURL")     var largeImageURL: String? = null,
    @ColumnInfo(name = "imageWidth")        var imageWidth: Int? = null,
    @ColumnInfo(name = "imageHeight")       var imageHeight: Int? = null,
    @ColumnInfo(name = "imageSize")         var imageSize: Int? = null,
    @ColumnInfo(name = "views")             var views: Int? = null,
    @ColumnInfo(name = "downloads")         var downloads: Int? = null,
    @ColumnInfo(name = "favorites")         var favorites: Int? = null,
    @ColumnInfo(name = "likes")             var likes: Int? = null,
    @ColumnInfo(name = "comments")          var comments: Int? = null,
    @ColumnInfo(name = "user_id")           var user_id: Int? = null,
    @ColumnInfo(name = "user")              var user: String? = null,
    @ColumnInfo(name = "userImageURL")      var userImageURL: String? = null

):BaseModel()
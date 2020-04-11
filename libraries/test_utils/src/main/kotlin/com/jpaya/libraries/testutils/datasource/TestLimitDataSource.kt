package com.jpaya.libraries.testutils.datasource

import android.annotation.SuppressLint
import android.database.Cursor
import androidx.room.RoomDatabase
import androidx.room.RoomSQLiteQuery
import androidx.room.paging.LimitOffsetDataSource

@SuppressLint("RestrictedApi")
class TestLimitDataSource<T>(
    db: RoomDatabase,
    query: RoomSQLiteQuery,
    private val itemList: List<T>
) : LimitOffsetDataSource<T>(db, query, false, null) {

    override fun convertRows(cursor: Cursor?): MutableList<T> = itemList.toMutableList()
    override fun countItems(): Int = itemList.count()
    override fun isInvalid(): Boolean = false
    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<T>) {
        /* Not implemented */
    }

    override fun loadRange(startPosition: Int, loadCount: Int) =
        itemList.subList(startPosition, startPosition + loadCount).toMutableList()

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<T>) {
        callback.onResult(itemList, 0)
    }
}

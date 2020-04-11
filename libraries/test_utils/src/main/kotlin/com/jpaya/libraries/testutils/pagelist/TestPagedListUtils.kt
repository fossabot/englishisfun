package com.jpaya.libraries.testutils.pagelist

import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.room.RoomDatabase
import androidx.room.RoomSQLiteQuery
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.jpaya.libraries.testutils.datasource.TestLimitDataSource
import com.jpaya.libraries.testutils.livedata.getOrAwaitValue

fun <T> pagedListOf(vararg elements: T, config: PagedList.Config? = null): PagedList<T>? {
    val defaultConfig = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(elements.count())
        .setMaxSize(elements.count() + 2)
        .setPrefetchDistance(1)
        .build()
    return LivePagedListBuilder<Int, T>(
        createMockDataSourceFactory(elements.toList()),
        config ?: defaultConfig
    ).build().getOrAwaitValue()
}

private fun <T> createMockDataSourceFactory(itemList: List<T>): DataSource.Factory<Int, T> =
    object : DataSource.Factory<Int, T>() {
        override fun create(): DataSource<Int, T> {
            val mockQuery = mock<RoomSQLiteQuery>()
            val mockDb = mock<RoomDatabase> {
                on { invalidationTracker }.doReturn(mock())
            }

            return TestLimitDataSource(
                db = mockDb,
                query = mockQuery,
                itemList = itemList
            )
        }
    }

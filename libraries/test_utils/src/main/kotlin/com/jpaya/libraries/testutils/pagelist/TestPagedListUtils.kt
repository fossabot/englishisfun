/*
 * Copyright 2020 Jose Maria Payá Castillo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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

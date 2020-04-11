package com.jpaya.core.database.migrations

import androidx.sqlite.db.SupportSQLiteDatabase
import com.nhaarman.mockitokotlin2.never
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MarvelDatabaseMigration1to2Test {

    @Mock
    lateinit var supportSQLiteDatabase: SupportSQLiteDatabase
    private val migration = MIGRATION_1_2

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun checkMigrationDatabaseVersions() {
        assertEquals(1, migration.startVersion)
        assertEquals(2, migration.endVersion)
    }

    @Test
    fun executeMigrationDatabase() {
        migration.migrate(supportSQLiteDatabase)

        verify(supportSQLiteDatabase, never()).beginTransaction()
    }
}

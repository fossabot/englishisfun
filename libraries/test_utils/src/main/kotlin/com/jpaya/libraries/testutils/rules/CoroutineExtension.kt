package com.jpaya.libraries.testutils.rules

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.extension.AfterEachCallback
import org.junit.jupiter.api.extension.BeforeEachCallback
import org.junit.jupiter.api.extension.ExtensionContext

/**
 * A JUnit Test Rule that swaps the background executor used by Coroutines with a
 * different one which executes each task synchronously.
 * <p>
 * You can use this rule for your host side tests that use Coroutines.
 */
@ExperimentalCoroutinesApi
class CoroutineExtension : BeforeEachCallback, AfterEachCallback {

    private val dispatcher = TestCoroutineDispatcher()

    override fun beforeEach(context: ExtensionContext?) {
        Dispatchers.setMain(dispatcher)
    }

    override fun afterEach(context: ExtensionContext?) {
        Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }
}

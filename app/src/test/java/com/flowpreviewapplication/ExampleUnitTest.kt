package com.flowpreviewapplication

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

//    val coroutineScope = TestCoroutineScope()

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun addition_isCorrect() = runTest {
        val flow = flow<String> {
            repeat(10) {
                emit(it.toString())
                delay(1000)
            }
        }

        assertEquals(10, flow.toList().size)

//        mutableMapOf("" to 3).toSortedMap { first, second -> second.compareTo(first) }
//
//        Collections.disjoint()
//        arrayOf(3).maxByOrNull {  }
    }

    @After
    fun cleanUp() {
        Dispatchers.resetMain()
    }

    @Test
    fun testMy() {
        val university = University(TestRepo())
        val result = university.getPaidCoursesWithTheNumbersOfSubscribedStudents(3)

        println(result)
    }
}

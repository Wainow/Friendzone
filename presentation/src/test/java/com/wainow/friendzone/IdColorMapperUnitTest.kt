package com.wainow.friendzone

import com.wainow.friendzone.utils.IdColorMapper
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class IdColorMapperUnitTest {
    @Test
    fun idColorMapper_isCorrect() {
        assertEquals(IdColorMapper.map(1), "#111000")
        assertEquals(IdColorMapper.map(22), "#922220")
        assertEquals(IdColorMapper.map(333333), "#333333")
    }
}
package ru.matvine.tests

import org.junit.Before
import org.junit.Test

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import org.junit.Rule
import java.lang.Exception
import java.util.*


class MainActivityTest {

    @Rule @JvmField
    var mActivityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    @Throws(Exception::class)
    fun setUp(){}

    /*
        Проверяет наличие компонентов
        после открытия приложения
     */
    @Test
    @Throws(Exception::class)
    fun checkComponents(){
        onView(withId(R.id.editText)).check(matches(isDisplayed())) // input field
        onView(withId(R.id.buttonl)).check(matches(isDisplayed())) // "set" button
        onView(withId(R.id.textView)).check(matches(isDisplayed())) // label
        onView(withId(R.id.textView)).check(matches(withText("Label"))) // initial label text
    }


    /*
        Проверяет корректность обновления лейбла
        на случайном тексте
     */
    @Test
    @Throws(Exception::class)
    fun tests() {
        val len = rand(50); // генерация случайного кол-ва символов
        val randomStr = randomString(len); // генерация случайной строки
        onView(withId(R.id.editText)).perform(replaceText(randomStr)) // замена текста на сгенерированный
        onView(withId(R.id.buttonl)).perform(click()) // кликаем по кнопке
        onView(withId(R.id.textView)).check(matches(withText(randomStr))) // проверяем лейбл
    }

    fun rand(last: Int): Int {
        return Random().nextInt(last) + 1;
    }

    fun randomString(length: Int): String? {
        val r = Random()
        val sb = StringBuffer()
        while (sb.length < length) {
            sb.append(Integer.toHexString(r.nextInt()))
        }
        return sb.toString().substring(0, length)
    }
}
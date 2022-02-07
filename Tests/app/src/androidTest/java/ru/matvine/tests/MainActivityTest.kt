package ru.matvine.tests

import android.content.pm.ActivityInfo
import androidx.appcompat.app.AppCompatDelegate
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

    private val editText = R.id.editText;
    private val button = R.id.buttonl;
    private val textView = R.id.textView;

    /*
        Проверяет наличие компонентов
        после открытия приложения
     */
    @Test
    @Throws(Exception::class)
    fun checkComponents(){
        onView(withId(editText)).check(matches(isDisplayed())) // input field
        onView(withId(button)).check(matches(isDisplayed())) // "set" button
        onView(withId(textView)).check(matches(isDisplayed())) // label
        onView(withId(textView)).check(matches(withText("Label"))) // initial label text
    }

    /*
        Проверяет наличие компонентов
        при разных ориентациях экрана
     */
    @Test
    @Throws(Exception::class)
    fun secondTest() {
        mActivityRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE; // альбомная ориентация
        checkComponents();
        mActivityRule.activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT; // портретная ориентация
        checkComponents();
    }


    /*
        Проверяет корректность обновления лейбла
        на случайном тексте
     */
    @Test
    @Throws(Exception::class)
    fun inputTest() {
        val len = rand(50); // генерация случайного кол-ва символов
        val randomStr = randomString(len); // генерация случайной строки
        onView(withId(editText)).perform(replaceText(randomStr)) // замена текста на сгенерированный
        onView(withId(button)).perform(click()) // кликаем по кнопке
        onView(withId(textView)).check(matches(withText(randomStr))) // проверяем лейбл
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
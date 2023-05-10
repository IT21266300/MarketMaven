package com.example.marketmaven.activities

import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.marketmaven.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TransportHistoryTest {

    @Rule
    @JvmField
    var mActivityRule: ActivityTestRule<TransportHistory> =
        ActivityTestRule(TransportHistory::class.java)

    @Test
    fun testRecyclerViewItemClicked() {
        onView(withId(R.id.transHisRecycle))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        // Check if the correct activity is launched
        onView(withId(R.id.transItemName)).check(matches(isDisplayed()))
        onView(withId(R.id.transTotalCost)).check(matches(isDisplayed()))
        onView(withId(R.id.transItemWeight)).check(matches(isDisplayed()))
    }

    @Test
    fun testIntent() {
        val intent = Intent()
        intent.putExtra("transId", "123")
        intent.putExtra("transItem", "Tomato")
        intent.putExtra("transItemWeight", "20")
        intent.putExtra("transWeightFactor", "10")
        intent.putExtra("transTotalWeightFactor", "200")
        intent.putExtra("transPickUp", "Welimada")
        intent.putExtra("transDelivery", "Kandy")
        intent.putExtra("transDistance", "72")
        intent.putExtra("transFuelEfficient", "20")
        intent.putExtra("transTotalFuelEfficient", "0.55")
        intent.putExtra("transTotalFuelCost", "1200")
        intent.putExtra("transFuelPrice", "320")
        intent.putExtra("transDriverWage", "1000")
        intent.putExtra("transTotalCost", "2000")

        mActivityRule.launchActivity(intent)


        onView(withId(R.id.transItemName)).check(matches(withText("Test Item name")))
        onView(withId(R.id.transTotalCost)).check(matches(withText("Test Item cost")))
        onView(withId(R.id.transItemWeight)).check(matches(withText("Test Item weight")))

    }
}

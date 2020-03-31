package com.example.triggerisechallenge

import android.view.View
import androidx.test.rule.ActivityTestRule
import com.example.triggerisechallenge.activities.SetsActivity
import org.junit.*

class MainActivityTest {

    @get:Rule
    var mActivityTestRule: ActivityTestRule<SetsActivity> =
        ActivityTestRule(SetsActivity::class.java)

    private var mActivity: SetsActivity? = null

    @Before
    fun setUp() {
        mActivity = mActivityTestRule.activity
    }

    @Test
    fun testLaunch() {

        val recyclerView: View = mActivity!!.findViewById(R.id.sets_rv)

        Assert.assertNotNull(recyclerView)

        val messageView: View = mActivity!!.findViewById(R.id.message_tv)

        Assert.assertNotNull(messageView)

        val progressBar: View = mActivity!!.findViewById(R.id.progress_bar)

        Assert.assertNotNull(progressBar)

    }

    @After
    fun tearDown() {
        mActivity = null
    }
}
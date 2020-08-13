package io.github.occultus73.poqrepositories.framework

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import io.github.occultus73.poqrepositories.R
import io.github.occultus73.poqrepositories.framework.android_test_utils.RecyclerViewTestUtils.withRecyclerView
import io.github.occultus73.poqrepositories.framework.presentation.ui.MainActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.description

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4ClassRunner::class)
class MainUITest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun a_test_isListFragmentVisible_onAppLaunch() {
        Espresso.onView(withId(R.id.activity_main))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.tool_bar))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.bottom_navigation_view))
            .check(ViewAssertions.matches(isDisplayed()))

        Espresso.onView(withId(R.id.progress_bar))
            .check(ViewAssertions.matches(not(withEffectiveVisibility(Visibility.INVISIBLE))))

        Espresso.onView(withId(R.id.rv_square_repos_item))
            .check(ViewAssertions.matches(not(withEffectiveVisibility(Visibility.INVISIBLE))))

        Thread.sleep(2000)

        Espresso.onView(withRecyclerView(R.id.rv_square_repos_item)
            .atPositionOnView(0, R.id.text_name)
        ).check(ViewAssertions.matches(withText("MimicAndRephrase")))

        Espresso.onView(withRecyclerView(R.id.rv_square_repos_item)
            .atPositionOnView(0, R.id.text_description)
        ).check(ViewAssertions.matches(withText("The code for the Mimic and Rephrase paper")))
    }


}
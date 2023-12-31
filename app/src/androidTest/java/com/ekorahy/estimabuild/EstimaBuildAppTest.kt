package com.ekorahy.estimabuild

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollTo
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.ekorahy.estimabuild.model.FakeProductDataSource
import com.ekorahy.estimabuild.ui.navigation.Screen
import com.ekorahy.estimabuild.ui.theme.EstimaBuildTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EstimaBuildAppTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        composeTestRule.setContent {
            EstimaBuildTheme {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                EstimaBuildApp(navController = navController)
            }
        }
    }

    /*
    * TESTING SCENARIO
    * HOME SCREEN
    * POSITIVE :
    * 1. Make sure the start destination displays the Home page.
    * 2. Ensure that when the search column is not empty, the application only displays data
    *    whose title contains the keywords entered.
    * 3. Ensure that when the search column is empty, the application displays all the data.
    * 4. Ensure that when one of the product items is clicked, it will go to the product details page.
    * NEGATIVE :
    * 1. Make sure that when the search column is not empty, and the keywords entered are not
    *    the same as the data title in the product list, a display will appear indicating that
    *    the data you are looking for was not found.
    *  */

    // HOME SCREEN
    // POSITIVE
    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun when_theSearchBarIsNotEmpty_displaysDataWhoseTitleContainsTheKeywords() {
        val data = arrayOf(
            R.string.title_case1,
            R.string.title_case2,
            R.string.title_case3
        )

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.search_product))
            .performTextInput(composeTestRule.activity.getString(R.string.title_case))

        for (titleResId in data) {
            val title = composeTestRule.activity.getString(titleResId)
            composeTestRule.onNodeWithText(title).assertExists()
        }
    }

    @Test
    fun when_theSearchColumnIsEmpty_displaysAllTheData() {

        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.search_product))
            .performTextInput(composeTestRule.activity.getString(R.string.empty_search))

        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.product_list))
            .performScrollToIndex(17)
        composeTestRule.onNodeWithText(FakeProductDataSource.dummyProducts[17].title).assertExists()
    }

    @Test
    fun when_oneOfTheProductIsClicked_itWillGoToTheProductDetailsPage() {
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.product_list))
            .performScrollToIndex(11)
        composeTestRule.onNodeWithText(FakeProductDataSource.dummyProducts[11].title).performClick()
        navController.assertCurrentRouteName(Screen.DetailProduct.route)
        composeTestRule.onNodeWithText(FakeProductDataSource.dummyProducts[11].title)
            .assertIsDisplayed()
    }

    // NEGATIVE
    @Test
    fun when_theKeywordsEnteredAreNotSameAsTheData_displayTheDataNotFound() {
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.search_product))
            .performTextInput(composeTestRule.activity.getString(R.string.not_found_search))
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.not_found))
            .assertExists()
    }

    /*
    * TESTING SCENARIO
    * DETAIL SCREEN
    * POSITIVE :
    * 1. Make sure that when the count value you want to add is 0, the button is disabled.
    * 2. Ensure that when the increased product button is pressed, the count value is +1.
    * 3. Ensure that when the count value is more than 0 and the declining product button
    *    is pressed, the count value is -1.
    * 4. Ensure that when the count is more than zero the add button is active.
    * 5. Ensure that when the count is more than zero and the add button is pressed it
    *    will move to the estimation page.
    * 6. Make sure that when the back arrow button is pressed, it will go to the home page.
    * NEGATIVE :
    * 1. Ensure that when the count value is zero and the decrease button is pressed, the count
    *    value remains zero.
    *  */

    // POSITIVE
    @Test
    fun when_theCountValueIsZero_theButtonAddIsDisabled() {
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.product_list))
            .performScrollToIndex(1)
        composeTestRule.onNodeWithText(FakeProductDataSource.dummyProducts[1].title).performClick()
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.product_counter))
            .performScrollTo()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.default_count))
            .assertExists()
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.button_add))
            .assertIsNotEnabled()
    }

    @Test
    fun when_buttonIncreaseIsPressedCountIncreased() {
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.product_list))
            .performScrollToIndex(1)
        composeTestRule.onNodeWithText(FakeProductDataSource.dummyProducts[1].title).performClick()
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.product_counter))
            .performScrollTo()
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.button_increase))
            .performClick()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.count_increase))
            .assertExists()
    }

    @Test
    fun when_countIsGreaterThanZero_and_theDecreaseButtonIsPressed_theCountDecreases() {
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.product_list))
            .performScrollToIndex(1)
        composeTestRule.onNodeWithText(FakeProductDataSource.dummyProducts[1].title).performClick()
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.product_counter))
            .performScrollTo()
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.button_increase))
            .performClick()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.count_increase))
            .assertExists()
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.button_decrease))
            .performClick()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.count_decrease))
            .assertExists()
    }

    @Test
    fun when_theCountIsMoreThanZero_theAddButtonIsActive() {
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.product_list))
            .performScrollToIndex(1)
        composeTestRule.onNodeWithText(FakeProductDataSource.dummyProducts[1].title).performClick()
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.product_counter))
            .performScrollTo()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.default_count))
            .assertExists()
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.button_increase))
            .performClick()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.count_increase))
            .assertExists()
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.button_add))
            .assertIsEnabled()
    }

    @Test
    fun when_theCountIsMoreThanZero_andTheAddButtonIsPressed_itWillMoveToEstimation() {
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.product_list))
            .performScrollToIndex(1)
        composeTestRule.onNodeWithText(FakeProductDataSource.dummyProducts[1].title).performClick()
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.product_counter))
            .performScrollTo()
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.button_increase))
            .performClick()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.count_increase))
            .assertExists()
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.button_add))
            .performScrollTo()
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.button_add))
            .performClick()
        navController.assertCurrentRouteName(Screen.Estimation.route)
    }

    @Test
    fun when_theBackArrowButtonIsPressed_itWillGoToTheHomePage() {
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.product_list))
            .performScrollToIndex(1)
        composeTestRule.onNodeWithText(FakeProductDataSource.dummyProducts[1].title).performClick()
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.back))
            .performClick()
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    // NEGATIVE
    @Test
    fun when_theCountValueIsZeroAndTheDecreaseButtonIsPressed_theCountRemainsZero() {
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.product_list))
            .performScrollToIndex(1)
        composeTestRule.onNodeWithText(FakeProductDataSource.dummyProducts[1].title).performClick()
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.product_counter))
            .performScrollTo()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.default_count))
            .assertExists()
        composeTestRule.onNodeWithTag(composeTestRule.activity.getString(R.string.button_decrease))
            .performClick()
        composeTestRule.onNodeWithText(composeTestRule.activity.getString(R.string.default_count))
            .assertExists()
    }
}
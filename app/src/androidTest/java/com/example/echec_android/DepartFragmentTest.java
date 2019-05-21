package com.example.echec_android;


import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.echec_android.gui.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;

/**
 * Tests instrumentés pour tester le DepartFragment qui obtient le nom des joueurs
 *
 * @author Yanick Bellavance
 * @author William Blackburn
 */
@RunWith(AndroidJUnit4.class)
public class DepartFragmentTest {
    @Rule
    public ActivityTestRule<MainActivity> m_activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testRemplirChamp() {
        onView(withId(R.id.nom_joueur_blanc)).perform(typeText("te"), closeSoftKeyboard());
        onView(withId(R.id.nom_joueur_noir)).perform(typeText("a"), closeSoftKeyboard());
        onView(withId(R.id.confirmer)).perform(click());

        onView(withText(R.string.toast_info_joueur)).inRoot(withDecorView(not(is(m_activityRule.
                getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));

        onView(withId(R.id.nom_joueur_blanc)).perform(typeText("allo"), closeSoftKeyboard());
        onView(withId(R.id.nom_joueur_noir)).perform(typeText("ennd"), closeSoftKeyboard());
        onView(withId(R.id.confirmer)).perform(click());
    }
}

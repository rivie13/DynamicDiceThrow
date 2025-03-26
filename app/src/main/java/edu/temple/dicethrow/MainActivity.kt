package edu.temple.dicethrow

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.replace


/*
Our DieThrow application has been refactored to move the dieRoll() logic
into the ViewModel instead of the Fragment.
Study the DieViewModel, ButtonFragment, and DieFragment classes to
see the changes.

Follow the requirements below to have this app function
in both portrait and landscape configurations.
The Activity layout files for both Portrait and Landscape are already provided
*/

class MainActivity : AppCompatActivity(), ButtonFragment.ButtonInterface {

    private var isLandscape = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        isLandscape = resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

        // TODO 1: Load fragment(s)
        // - Show _only_ ButtonFragment if portrait
        // - show _both_ fragments if Landscape
        if (savedInstanceState == null) {
            if (isLandscape) {
                supportFragmentManager.commit {
                    add<ButtonFragment>(R.id.container1)
                    add<DieFragment>(R.id.container2)
                }
            } else {
                supportFragmentManager.commit {
                    add<ButtonFragment>(R.id.container1)
                }
            }
        }
    }

    // TODO 2: switch fragments if die rolled and in portrait (no need to switch fragments if Landscape)
    // This callback function gets invoked when the child Fragment invokes it
    // Remember to place Fragment transactions on BackStack so then can be reversed
    override fun buttonClicked() {
        if (!isLandscape) {
            supportFragmentManager.commit {
                replace<DieFragment>(R.id.container1)
                addToBackStack(null)
            }
        }
    }
}
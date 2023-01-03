package com.raywenderlich.android.cartoonsocialclub

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.raywenderlich.android.cartoonsocialclub.databinding.ActivityAvatarSelectedBinding
class AvatarSelectedActivity : AppCompatActivity() {

    private lateinit var selectedAvatarImageView: ImageView
    private lateinit var fullNameTextView: TextView
    private lateinit var initialTextView: TextView
    private lateinit var selectedAvatarDescriptionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        // Switch to AppTheme for displaying the activity
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        val binding = ActivityAvatarSelectedBinding.inflate(layoutInflater)
        setContentView(binding.root)

        selectedAvatarImageView = binding.selectedAvatarImageView
        fullNameTextView = binding.fullNameTextView
        initialTextView = binding.initialTextView
        selectedAvatarDescriptionTextView = binding.selectedAvatarDescriptionTextView

        // TODO 10 : Add a call to the loadDataFromIntent function here.
        loadDataFromIntent()
    }

    // TODO 9 : Add the loadDataFromIntent to read Intent data to be displayed in the view.
    private fun loadDataFromIntent() {
        // 1
        val extras = intent.extras
        val fullName = extras?.getString(EXTRA_FULL_NAME)
        val cartoonEnumCaseName = extras?.getString(EXTRA_CARTOON)
        if (cartoonEnumCaseName == null) {
            // 2
            finish()
            return
        }

        // 3
        val cartoonAvatar = CartoonAvatar.valueOf(cartoonEnumCaseName)
        populateView(fullName ?: "", cartoonAvatar)
    }

    // TODO 8 : Add the populateView function to display infos about the selected avatar.
    private fun populateView(fullName: String, cartoonAvatar: CartoonAvatar) {
        // 1
        fullNameTextView.text = fullName
        selectedAvatarDescriptionTextView.text = cartoonAvatar.selectionDescription(this)

        // 2
        when (cartoonAvatar) {
            CartoonAvatar.NONE -> {
                initialTextView.text = fullName.uppercasedInitial()
                selectedAvatarImageView.visibility = View.GONE
                initialTextView.visibility = View.VISIBLE
            }
            else -> {
                selectedAvatarImageView.setImageResource(cartoonAvatar.drawableRes)
                selectedAvatarImageView.visibility = View.VISIBLE
                initialTextView.visibility = View.GONE
            }
        }
    }

    // TODO 5 : Add the companion object and newIntent function.
    companion object {
        // 1
        private const val EXTRA_FULL_NAME = "com.raywenderlich.android.cartoonsocialclub.FULL_NAME"
        private const val EXTRA_CARTOON = "com.raywenderlich.android.cartoonsocialclub.CARTOON"

        // 2
        fun newIntent(fullName: String, selectedAvatar: CartoonAvatar, context: Context): Intent {
            return Intent(context, AvatarSelectedActivity::class.java).apply {
                putExtra(EXTRA_FULL_NAME, fullName)
                // 3
                putExtra(EXTRA_CARTOON, selectedAvatar.name)
            }
        }
    }
}
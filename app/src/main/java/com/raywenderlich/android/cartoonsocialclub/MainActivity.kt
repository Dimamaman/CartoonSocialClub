package com.raywenderlich.android.cartoonsocialclub

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.raywenderlich.android.cartoonsocialclub.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var fullNameEditText: EditText
    private lateinit var avatarsRecyclerView: RecyclerView
    private lateinit var continueButton: Button

    private lateinit var cartoonAvatarAdapter: CartoonAvatarAdapter

    // TODO 3 : Add a variable to keep track of selected avatar.
    private var selectedAvatar: CartoonAvatar? = null

    // TODO 2 : Populate the list with all values of the CartoonAvatar Enum values.
    private var avatarChoices = CartoonAvatar.values().map {
        Item(avatar = it, isSelected = false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        // Switch to AppTheme for displaying the activity
        setTheme(R.style.AppTheme)

        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fullNameEditText = binding.fullNameEditText
        avatarsRecyclerView = binding.avatarsRecyclerView
        continueButton = binding.continueButton
        setupRecyclerView()

        // Hide the "Continue" button while nothing is selected
        continueButton.visibility = View.GONE

        // TODO 7 : Add a call to the setupContinueButton function here.
        setupContinueButton()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun selectAvatar(avatar: Item) {
        // TODO 4 : Implement method when a new avatar is selected.
        // 1
        selectedAvatar = avatar.avatar

        // 2
        avatarChoices.forEach { it.isSelected = it.avatar == selectedAvatar }
        cartoonAvatarAdapter.notifyDataSetChanged()

        if (fullNameEditText.text.isNotBlank()) {
            continueButton.visibility = View.VISIBLE
        }
    }

    // TODO 6 : Add the setupContinueButton function to display AvatarSelectedActivity on click.
    private fun setupContinueButton() {
        // 1
        fullNameEditText.doAfterTextChanged { fullName ->
            if (fullName.toString().isNotBlank() && selectedAvatar != null) {
                continueButton.visibility = View.VISIBLE
            } else {
                continueButton.visibility = View.GONE
            }
        }

        // 2
        continueButton.setOnClickListener {
            val selectedAvatar = selectedAvatar ?: return@setOnClickListener
            val firstName = fullNameEditText.text.toString()
            val intent = AvatarSelectedActivity.newIntent(firstName, selectedAvatar, this)
            startActivity(intent)
        }
    }

    private fun setupRecyclerView() {
        cartoonAvatarAdapter = CartoonAvatarAdapter(onItemClick = { avatar ->
            selectAvatar(avatar)
        })
        cartoonAvatarAdapter.submitList(avatarChoices)
        avatarsRecyclerView.adapter = cartoonAvatarAdapter
    }
}

package com.example.videocall

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.videocall.databinding.ActivityProfileBinding
import com.zegocloud.uikit.service.defines.ZegoUIKitUser
import java.util.Collections

class ProfileActivity : AppCompatActivity() {

    companion object {
        const val USER_ID = "userId"
    }

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.yourUser.text="Your user is ${intent.getStringExtra(USER_ID)}"
        addListner()


    }

    private fun addListner() {

        binding.targetUserId.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val targetUserID = binding.targetUserId.text.toString().trim()
                startCalling(targetUserID)
            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called after the text has been changed
            }
        })


    }

    private fun startCalling(targetUserID:String) {


        binding.callingButton.apply {
            setIsVideoCall(true)
            resourceID = "zego_uikit_call"
            setInvitees(
                Collections.singletonList(
                    ZegoUIKitUser(
                        targetUserID,
                        targetUserID
                    )
                )
            )
        }
    }
}
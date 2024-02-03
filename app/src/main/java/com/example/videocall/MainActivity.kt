package com.example.videocall

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.videocall.databinding.ActivityMainBinding
import com.example.videocall.utils.Constents
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        clickedEvent()

    }

    override fun onDestroy() {
        super.onDestroy()
        ZegoUIKitPrebuiltCallInvitationService.unInit()
    }

    private fun clickedEvent() {
        binding.loginBtn.setOnClickListener {
            val userID = binding.emailId.text.toString()
            startService(userID)
            startActivity(Intent(this, ProfileActivity::class.java).apply {
                putExtra(ProfileActivity.USER_ID, userID)
            })
        }
    }

    private fun startService(userID: String) {

        val callInvitationConfig = ZegoUIKitPrebuiltCallInvitationConfig()


        ZegoUIKitPrebuiltCallInvitationService.init(
            application,
            Constents.AppID,
            Constents.AppSign,
            userID,
            userID,
            callInvitationConfig
        )

    }

}
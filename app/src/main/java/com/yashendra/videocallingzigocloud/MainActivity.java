package com.yashendra.videocallingzigocloud;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

public class MainActivity extends AppCompatActivity {
    EditText username;
    Button startbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.user_id_edittext);
        startbtn = findViewById(R.id.start_button);

        startbtn.setOnClickListener(v -> {
            String name = username.getText().toString();
            if(name.isEmpty()){
                username.setError("Please enter your name");
            }else{
            startService(name,name);
            //in intenet to the call activity
                Intent intent = new Intent(MainActivity.this,CallActivity.class);
                intent.putExtra("username",name);
                startActivity(intent);
            }
        });
    }
    void startService(String useriD,String username){

        long appID =1642759757 ;   // yourAppID
        String appSign ="0907413dc4b14521a764b47315799741a64aadd8959e43c741b655904c4c7632";  // yourAppSign

        ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();
        callInvitationConfig.notifyWhenAppRunningInBackgroundOrQuit = true;
        ZegoNotificationConfig notificationConfig = new ZegoNotificationConfig();
        notificationConfig.sound = "zego_uikit_sound_call";
        notificationConfig.channelID = "CallInvitation";
        notificationConfig.channelName = "CallInvitation";
        ZegoUIKitPrebuiltCallInvitationService.init(getApplication(), appID, appSign, useriD, username,callInvitationConfig);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ZegoUIKitPrebuiltCallInvitationService.unInit();
    }
}
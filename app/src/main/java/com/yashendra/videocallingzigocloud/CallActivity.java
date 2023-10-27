package com.yashendra.videocallingzigocloud;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.util.Collections;

public class CallActivity extends AppCompatActivity {
    EditText userIdedittext;
    TextView hiuseredittext;
    ZegoSendCallInvitationButton videoCallButton,voiceCallButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        userIdedittext = findViewById(R.id.user_id_edittext);
        hiuseredittext = findViewById(R.id.hiusertext);
        videoCallButton = findViewById(R.id.videocallbtn);
        voiceCallButton = findViewById(R.id.voicecallbtn);

        String username= getIntent().getStringExtra("username");
        hiuseredittext.setText("Hi "+username);

        userIdedittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String targetid = userIdedittext.getText().toString();
                    setvoiceCall(targetid);
                    setVideoCall(targetid);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        } );

    }

    void setvoiceCall(String targetid){
        voiceCallButton.setIsVideoCall(false);
        voiceCallButton.setResourceID("zego_uikit_call");
        voiceCallButton.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetid,targetid)));
    }

    void setVideoCall(String targetid){;
        videoCallButton.setIsVideoCall(true);
        videoCallButton.setResourceID("zego_uikit_call");
        videoCallButton.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetid,targetid)));
    }
}
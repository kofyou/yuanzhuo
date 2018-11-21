package com.fzu.xms.rtable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.zego.zegoaudioroom.ZegoAudioRoom;
import com.zego.zegoaudioroom.ZegoAudioRoomDelegate;
import com.zego.zegoaudioroom.ZegoAudioStream;
import com.zego.zegoaudioroom.ZegoAudioStreamType;
import com.zego.zegoaudioroom.ZegoLoginAudioRoomCallback;
import com.zego.zegoliveroom.entity.ZegoBigRoomMessage;
import com.zego.zegoliveroom.entity.ZegoConversationMessage;
import com.zego.zegoliveroom.entity.ZegoRoomMessage;
import com.zego.zegoliveroom.entity.ZegoUserState;

public class wait extends AppCompatActivity {

    private ZegoAudioRoom zegoAudioRoom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wait);

        TextView inviteTxt=(TextView)findViewById(R.id.inviteTxt);
        inviteTxt.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(wait.this,invite.class);
                startActivity(intent);
            }
        });

        TextView getInTxt=(TextView)findViewById(R.id.getInTxt);
        getInTxt.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(wait.this,chatroom.class);
                startActivity(intent);
            }
        });

        TextView uploadFiles=(TextView)findViewById(R.id.uploadFiles);
        uploadFiles.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(wait.this,uploadfiles.class);
                startActivity(intent);
            }
        });

        Intent intent=getIntent();
        final String amount=intent.getStringExtra("amount");
        Log.e("upload页面接收的num",amount);

        String roomNum=intent.getStringExtra("roomNum");

        long appid = 597269392;
        byte[] sign = {0x6a,0x22,0x73,0x1e,0x7f,
                (byte) 0xfb, (byte) 0xea, (byte) 0xc2,0x2e,0x31,0x4e,
                (byte) 0xfc, (byte) 0xc9, (byte) 0xb7,0x5f, (byte) 0xb7,
                0x3d, (byte) 0xbd, (byte) 0x86,0x37,0x41, (byte) 0xdc, (byte) 0xfd,
                (byte) 0xea,0x5d, (byte) 0xb2,0x6b,0x45, (byte) 0xdb, (byte) 0xb7,0x7f, (byte) 0xb3};

        if(zegoAudioRoom!=null){
            zegoAudioRoom.unInit();
        }
        String userID = String.valueOf(System.currentTimeMillis());
        ZegoAudioRoom.setUser(userID, userID);
        ZegoAudioRoom.setUseTestEnv(true);
        ZegoAudioRoom.setBusinessType(0);

        zegoAudioRoom = new ZegoAudioRoom();
        Boolean checkSDk = zegoAudioRoom.initWithAppId(appid,sign,wait.this);

        if(checkSDk){
            Log.e("SDK初始化","成功");
            Log.e("SDK支持的最大监听数",String.valueOf(ZegoAudioRoom.getMaxPlayChannelCount()));
            zegoAudioRoom.setUserStateUpdate(true);
            Toast.makeText(wait.this,"SDK初始化成功",Toast.LENGTH_SHORT).show();
            zegoAudioRoom.setAudioRoomDelegate(new ZegoAudioRoomDelegate() {
                @Override
                public void onKickOut(int i, String s) {

                }

                @Override
                public void onDisconnect(int i, String s) {

                }

                @Override
                public void onStreamUpdate(ZegoAudioStreamType zegoAudioStreamType, ZegoAudioStream zegoAudioStream) {

                }

                @Override
                public void onUserUpdate(ZegoUserState[] zegoUserStates, int i) {
                    for(int x = 0;x <zegoUserStates.length;x++){
                        Log.e("身份"+x,String.valueOf(zegoUserStates[x].roomRole));
                        Log.e("更新"+x,String.valueOf(zegoUserStates[x].updateFlag));
                        Log.e("ID"+x,String.valueOf(zegoUserStates[x].userID));
                        Log.e("username"+x,String.valueOf(zegoUserStates[x].userName));

                        TextView waiting=findViewById(R.id.waiting);
                        waiting.setText("WAITING... "+String.valueOf(zegoUserStates.length)+"/"+amount);
                        if(String.valueOf(zegoUserStates.length).equals(amount)){//从之前的spinner获取
                            Intent intent2 = new Intent(wait.this,chatroom.class);
                            //intent2.putExtra("room", (Parcelable) zegoAudioRoom);
                            startActivity(intent2);//还要一个假装按钮
                            finish();
                        }
                    }
                }

                @Override   //用长度跳转
                public void onUpdateOnlineCount(String s, int i) {
                    Toast.makeText(wait.this,"在线人数更新",Toast.LENGTH_SHORT).show();
//                    if(String.valueOf(i).equals(amount)){//从之前的spinner获取
//                        Intent intent2 = new Intent(wait.this,chatroom.class);
//                        //intent2.putExtra("room", (Parcelable) zegoAudioRoom);
//                        startActivity(intent2);//还要一个假装按钮
//                        finish();
//                    }
                }

                @Override
                public void onRecvRoomMessage(String s, ZegoRoomMessage[] zegoRoomMessages) {

                }

                @Override
                public void onRecvConversationMessage(String s, String s1, ZegoConversationMessage zegoConversationMessage) {

                }

                @Override
                public void onRecvBigRoomMessage(String s, ZegoBigRoomMessage[] zegoBigRoomMessages) {

                }

                @Override
                public void onRecvCustomCommand(String s, String s1, String s2, String s3) {

                }

                @Override
                public void onStreamExtraInfoUpdated(ZegoAudioStream[] zegoAudioStreams, String s) {

                }
            });
        }else {
            Log.e("SDK初始化","失败");
            Toast.makeText(wait.this,"SDK初始化失败",Toast.LENGTH_SHORT).show();
        }

        Log.e("输入的房间号",roomNum);
        //加入房间
        if(roomNum.isEmpty()){
            roomNum = "500";
            Log.e("roomId","默认500");
        }else {
            Log.e("roomId",roomNum);
        }
        zegoAudioRoom.setUserStateUpdate(true);
        zegoAudioRoom.enableAEC(true);
        zegoAudioRoom.enableAux(false);
        zegoAudioRoom.enableMic(false);
        Boolean login = zegoAudioRoom.loginRoom(roomNum, new ZegoLoginAudioRoomCallback() {
            @Override
            public void onLoginCompletion(int i) {
                Log.e("房间状态码",String.valueOf(i));
            }
        });

        if(login){
            Log.e("进入房间","成功");
            Toast.makeText(wait.this,"进入房间成功",Toast.LENGTH_SHORT).show();
        }else {
            Log.e("进入房间","失败");
            Toast.makeText(wait.this,"进入房间失败",Toast.LENGTH_SHORT).show();
        }

        
    }

    public void Micopen(){

    }
}

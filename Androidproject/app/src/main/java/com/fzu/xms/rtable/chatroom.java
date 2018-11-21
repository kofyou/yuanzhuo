package com.fzu.xms.rtable;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.view.View.OnTouchListener;


import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.zego.zegoaudioroom.ZegoAudioRoom;

public class chatroom extends AppCompatActivity {

    private static final String TAG = "chatroom.class.getSimpleName()";
    public static final String SAMPLE_FILE = "TEST.pdf";
    private PDFView pdfView;
////////////////////////////////////////////////////////////////////////
    private ImageView myCanvas;
    private Bitmap baseBitmap;
    private Canvas canvas;
    private Paint paint;
    /////////////////////////////////////////////////////////
    private ZegoAudioRoom zegoAudioRoom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);

        Intent intent=getIntent();
        //zegoAudioRoom=intent.getParcelableExtra("room");
        //zegoAudioRoom.enableMic(true);
        //////////////////////////////////////////////////////////
        pdfView = (PDFView)findViewById(R.id.pdfView);
        pdfView.fromAsset("TEST.pdf")
                .defaultPage(0)
                .onPageChange(new OnPageChangeListener(){
                    @Override
                    public void onPageChanged(int page, int pageCount) {
                        setTitle(String.format("%s %s /%s",SAMPLE_FILE,page+1,pageCount));
                    }
                })
                .enableAnnotationRendering(true)
                .swipeHorizontal(true)
                .pageSnap(true)
                .autoSpacing(true)
                .pageFling(true)
                .onPageError(new OnPageErrorListener(){
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onPageError(int page, Throwable t){
                        Log.e(TAG, "onPageError: Cannot load page"+page );
                    }
                })
                .load();

        /////////////////////////////////////////////////////////////////////////////
        paint = new Paint();
        paint.setStrokeWidth(5);
        paint.setColor(Color.RED);
        myCanvas = (ImageView) findViewById(R.id.myCanvas);
        myCanvas.setOnTouchListener(touch);
    }

    private View.OnTouchListener touch = new OnTouchListener() {
        // 定义手指开始触摸的坐标
        float startX;
        float startY;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            switch (event.getAction()) {
                // 用户按下动作
                case MotionEvent.ACTION_DOWN:
                    // 第一次绘图初始化内存图片，指定背景为白色
                    if (baseBitmap == null) {
                        baseBitmap = Bitmap.createBitmap(myCanvas.getWidth(),
                                myCanvas.getHeight(), Bitmap.Config.ARGB_8888);
                        canvas = new Canvas(baseBitmap);
                        canvas.drawColor(Color.TRANSPARENT);
                    }
                    // 记录开始触摸的点的坐标
                    startX = event.getX();
                    startY = event.getY();
                    break;
                // 用户手指在屏幕上移动的动作
                case MotionEvent.ACTION_MOVE:
                    // 记录移动位置的点的坐标
                    float stopX = event.getX();
                    float stopY = event.getY();

                    Log.e("(x,y)","("+ stopX +","+stopY+")");
                    //根据两点坐标，绘制连线
                    canvas.drawLine(startX, startY, stopX, stopY, paint);

                    // 更新开始点的位置
                    startX = event.getX();
                    startY = event.getY();

                    // 把图片展示到ImageView中
                    myCanvas.setImageBitmap(baseBitmap);
                    break;
                case MotionEvent.ACTION_UP:

                    break;
                default:
                    break;
            }
            return true;
        }
    };



}

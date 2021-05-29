package com.example.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

import static java.lang.Math.abs;

public class ProtoView extends SurfaceView implements SurfaceHolder.Callback {
    public Context context;
    protected final int viewHeight = this.getResources().getDisplayMetrics().heightPixels;
    protected final int viewWidth = this.getResources().getDisplayMetrics().widthPixels;
    public int getwidth(){return viewWidth;}
    public int getheight(){return viewHeight;}
    private DrawThread drawThread;
    private int lives = 3;
    private Sprite Platform;
    private Sprite Ball;
    public ArrayList<Sprite> Brick;
    private float KoefWidth = 1;
    private float KoefHeight = 1;
    private float KoefDPIWidth = 1;
    private float KoefDPIHeight = 1;
    private boolean FirstCollision = true;
    public ProtoView(Context context){
        super(context);
        this.context = context;
        getHolder().addCallback(this);
    }
    class DrawThread extends Thread{
        private boolean runFlag = false;
        private SurfaceHolder surfaceHolder;
        private long prevTime;
        public DrawThread(SurfaceHolder surfaceHolder, Resources resources){
            this.surfaceHolder = surfaceHolder;
            float density = 240f / getResources().getDisplayMetrics().densityDpi;
            // загружаем картинку, которую будем отрисовыват
            KoefWidth = (float)((float)(viewWidth) / 1600) ;
            KoefDPIWidth = KoefWidth * density;
            KoefHeight = (float)((float)(viewHeight) / 900);
            KoefDPIHeight = KoefHeight * density;

            Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.platform);

            int w = b.getWidth();
            int h = b.getHeight();
            Log.e("U suck again", viewWidth + " " + viewHeight + "");
            Log.e("U suck again", viewWidth + " " + viewHeight + "");
            Log.e("U suck again", viewWidth + " " + viewHeight + "");
            Log.e("U suck again", viewWidth + " " + viewHeight + "");
            Log.e("U suck again", viewWidth + " " + viewHeight + "");
            Log.e("U suck again", viewWidth + " " + viewHeight + "");
            Log.e("U suck again", viewWidth + " " + viewHeight + "");
            Log.e("U suck again", viewWidth + " " + viewHeight + "");
            Log.e("U suck again", viewWidth + " " + viewHeight + "");
            Log.e("U suck again", viewWidth + " " + viewHeight + "");



            Rect firstFrame = new Rect(0, 0, (int)(w), (int)(h));

            Platform = new Sprite( 500 * KoefWidth, 600 * KoefHeight, 0, 0, firstFrame, b, KoefWidth, KoefHeight);
            //Timer t = new Timer();
            //t.start();
            b = BitmapFactory.decodeResource(getResources(), R.drawable.football_ball1);
            w = b.getWidth();
            h = b.getHeight();

            firstFrame = new Rect(0, 0, (int)(w), (int)(h));

            Ball = new Sprite(1420 * KoefWidth, 50 * KoefHeight, -300, 10, firstFrame, b, KoefWidth, KoefHeight);
            movetostartposition();

            b = BitmapFactory.decodeResource(getResources(), R.drawable.brick_beta_texture);
            w = b.getWidth();
            h = b.getHeight();
            firstFrame = new Rect(0, 0, (int)(w), (int)(h));
            Brick = new ArrayList<Sprite>(100);
            for (int i=0; i<15; i++){
                Brick.add(new Sprite((50 + 100 * i) * KoefWidth, 50 * KoefHeight, 0, 0, firstFrame, b, KoefDPIWidth, KoefDPIHeight));
            }
            for(int j=0; j<15; j++){
                Brick.add(new Sprite((25 + 100 * j) * KoefWidth, 100 * KoefHeight, 0, 0, firstFrame, b, KoefDPIWidth, KoefDPIHeight)); }
            for (int i=0; i<15; i++){
                Brick.add(new Sprite((50 + 100 * i) * KoefWidth, 150 * KoefHeight, 0, 0, firstFrame, b, KoefDPIWidth, KoefDPIHeight));
            }
            for (int i=0; i<15; i++){
                Brick.add(new Sprite((25 + 100 * i) * KoefWidth, 200 * KoefHeight, 0, 0, firstFrame, b, KoefDPIWidth, KoefDPIHeight));
            }
            for(int j=0; j<15; j++){
                Brick.add(new Sprite((50 + 100 * j) * KoefWidth, 250 * KoefHeight, 0, 0, firstFrame, b, KoefDPIWidth, KoefDPIHeight)); }
            // сохраняем текущее время
            prevTime = System.currentTimeMillis();
        }

        public void setRunning(boolean run) {
            runFlag = run;
        }
        @Override
        public void run() {
            Canvas canvas=null;
            while (runFlag) {
                //super.onDraw(canvas);
                // получаем текущее время и вычисляем разницу с предыдущим
                // сохраненным моментом времени
                Date CurrentDate = new Date();
                Date Now = new Date();
                long now = System.currentTimeMillis();
                long elapsedTime = now - prevTime;
                if (elapsedTime > 30){
                    // если прошло больше 30 миллисекунд - сохраним текущее время
                    // и повернем картинку на 2 градуса.
                    // точка вращения - центр картинк
                    // и
                    //Log.e("Elapsed time is too big", "Ты идиот. Почему проц ноута тормозит из-за двухмерной графики хуже Сеговского Соника?");
                    //matrix.preRotate(2.0f, picture.getWidth() / 2, picture.getHeight() / 2);
                }
                else{
                    //Log.e("enough time", "Обработка заняла " + elapsedTime);
                    while(Now.getTime() - CurrentDate.getTime() < 30){Now = new Date();}
                    //Thread.sleep((long)(timerInterval - prevTime - System.currentTimeMillis() - 3));
                }
                canvas = null;
                try {
                    // получаем объект Canvas и выполняем отрисовку
                    canvas = surfaceHolder.lockCanvas(null);
                    synchronized (surfaceHolder) {
                        canvas.drawARGB(250, 127, 199, 255); // заливаем цветом
                        Paint p = new Paint();
                        p.setAntiAlias(true);
                        p.setTextSize(55.0f);
                        p.setColor(Color.WHITE);
                        canvas.drawText(lives+"", viewWidth - 100, 70, p);
                        //super.onDraw(canvas);
                        //canvas.drawARGB(250, 127, 199, 255); // цвет фона
                        Platform.draw(canvas);
                        Ball.draw(canvas);
                        for (int i=0; i<Brick.size(); i++){Brick.get(i).draw(canvas);}
                        Ball.update(timerInterval);
                        prevTime = now;
                        //canvas.drawBitmap(picture, matrix, null);
                    }
                }
                finally {
                    if (canvas != null) {
                        // отрисовка выполнена. выводим результат на экран
                        surfaceHolder.unlockCanvasAndPost(canvas);
                        update();
                    }
                }
            }
        }
    }
    private final int timerInterval = 25;
    protected void update () {
        //Log.e("F0ck U","Начал обновляться кадр");
        Platform.update(timerInterval);
        // столкнвоение платформы с правой стенкой
        if (Platform.getX() + Platform.getFrameWidth() > viewWidth) {
            Platform.setX(viewWidth - Platform.getFrameWidth());
            Platform.setVx(-Platform.getVx());
        }
        // столкновение платформы с левой стенкой
        else if (Platform.getX() < 0) {
            Platform.setX(0);
            Platform.setVx(-Platform.getVx());
        }
        //попадает ли мяч в левую стенку
        if (Ball.getX() < -20) {
            Ball.setX(1);
            Ball.setVx(-Ball.getVx());
        }
        else if (Ball.getX() + Ball.getFrameWidth() > viewWidth){
            Ball.setVx(-Ball.getVx() - Math.random() * 50);
        }
        // попадает ли мяч в платформу
        // у платформы отражение мча не должно подчиняться закону равенства углов отражения
        if (Ball.intersect(Platform)) {
            double Balllen = Ball.getFrameWidth();
            double PlatformLen = Platform.getFrameWidth();
            double difference = Ball.getX() + Balllen /2 - Platform.getX() - PlatformLen / 2;
            double velocity = Math.sqrt(Ball.getVx() * Ball.getVx() + Ball.getVy() * Ball.getVy());
            if (difference >= 0){
                Ball.setVx(velocity * Math.cos(-difference / PlatformLen * Math.PI));
                Ball.setVy(-velocity * Math.sin(difference / PlatformLen * Math.PI));
            }
            else{Ball.setVx(-velocity * Math.cos(-difference / PlatformLen * Math.PI));
                Ball.setVy(velocity * Math.sin(difference / PlatformLen * Math.PI));}
        }
        for (int i=0; i<Brick.size(); i++) {
            if (Ball.intersect(Brick.get(i))) {
                if (Ball.getY() + Ball.getFrameHeight() / 2 > Brick.get(i).getFrameHeight() / 2 + Brick.get(i).getY()){
                    Ball.setVx(-Ball.getVx());
                }
                else{
                    Ball.setVy(-Ball.getVy());
                }
                //Ball.setVx(-Ball.getVx());
                //Ball.setVy(-Ball.getVy());
                Brick.remove(i);
            }
        }
        if (Ball.getY() > viewHeight + 25){
            movetostartposition();
        }
        else if (Ball.getY() < 0){
            Ball.setVy(-Ball.getVy() + Math.random() * 50 * KoefHeight);
        }
        if (Ball.getVx() > 500 * KoefWidth){
            Ball.setVx(Ball.getVx() - 35 * KoefWidth);
        }
        else if (Ball.getVx() < -500 * KoefWidth){
            Ball.setVx(Ball.getVx() + 35 * KoefWidth);
        }
        if (Ball.getVy() > 500 * KoefHeight){
            Ball.setVy(Ball.getVy() - 35 * KoefHeight);
        }
        else if (Ball.getVy() < -500 * KoefHeight){
            Ball.setVy(Ball.getVy() + 35 * KoefHeight);
        }
    }
    private void movetostartposition() {
        Ball.setY(500 * KoefHeight);
        Ball.setVy(-50 * KoefDPIHeight * 4);
        Ball.setVx((Math.random() - 50) * KoefDPIWidth * 4);
        Ball.setX((750 * KoefWidth));
        lives -= 1;
    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int eventAction = event.getAction();
        float Xsetter = event.getRawX();
        switch (eventAction) {
            case MotionEvent.ACTION_DOWN:
                Platform.setX(Xsetter - Platform.getFrameWidth() / 2);
                break;
            case MotionEvent.ACTION_MOVE:
                Xsetter = event.getRawX();
                Platform.setX(Xsetter - Platform.getFrameWidth() / 2);
                break;
            case MotionEvent.ACTION_UP:
                break;
        // движение должно быть перепилено
        // смещение должно быть плавным
    }
        return true;}
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        DrawThread drawThread = new DrawThread(getHolder(), getResources());
        drawThread.setRunning(true);
        drawThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // изменение размеров SurfaceView

        //viewWidth = width;
        //viewHeight = height;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // уничтожение SurfaceView
        boolean retry = true;
        // завершаем работу потока
        drawThread.setRunning(false);
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException e) {
                // если не получилось, то будем пытаться еще и еще
            }
        }
    }
}

package com.example.cleaner;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;


public class WelcomeView extends SurfaceView{

	int sreenw;
	int sreenh;
MainActivity context;
	Bitmap[] bmp=new Bitmap[1];
	int bmpflag;
	int alpha=255;

	
	public WelcomeView(MainActivity context) {
		super(context);
		// TODO Auto-generated constructor stub
    
	this.context=context;
	/*bmp[0]=BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);*/
	bmp[0]=BitmapFactory.decodeResource(context.getResources(), R.drawable.login);
		sreenw=context.getWindowManager().getDefaultDisplay().getWidth();
		sreenh=context.getWindowManager().getDefaultDisplay().getHeight();
	
		this.getHolder().addCallback(new Callback(){

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				
		
				new Thread(){

					@Override
					public void run() {
						// TODO Auto-generated method stub	
						SurfaceHolder myholder=WelcomeView.this.getHolder();
						for(int j=0;j<bmp.length;j++)
						{
							bmpflag=j;
						for(int i=255;i>-1;i=i-10)
						{
							alpha=i;
					
						
							Canvas cv=myholder.lockCanvas();
							try{
							synchronized(myholder)
							{
								onfDraw(cv);
							}
							}catch(Exception e)
							{e.printStackTrace();}
							finally
							{if(myholder!=null)
								{
								myholder.unlockCanvasAndPost(cv);}}
							try {
								if (i == 255)
									Thread.sleep(2000);
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
						}
						}
					WelcomeView.this.context.myhand.sendEmptyMessage(0);
					}	
					
					}.start();
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				// TODO Auto-generated method stub
				
			}});
	}


	  public void onfDraw(Canvas canvas)
	  {
	
			Paint p = new Paint(Color.BLACK);
			  canvas.drawRect(0, 0, sreenw, sreenh, p);
			  p.setAntiAlias(true);
			if (bmp == null)
				return;	
			p.setAlpha(alpha);
		
			canvas.drawBitmap(bmp[bmpflag], sreenw / 2 - bmp[bmpflag].getWidth() / 2, sreenh
					/ 2 - bmp[bmpflag].getHeight() / 2, p);
	  }


}

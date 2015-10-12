package com.example.cleaner;

import MemoryUtil.Memory;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class Myprogress extends View {

	private Paint p;//大圆
	private Paint ptext;//大圆字
	private Paint ptext_2;
	private Paint pout;//外切圆
	private RectF area;//大圆位置
	private RectF areaout;//外切位置
	private RectF areaout_s;
	private int value;//大圆百分比
	private int value_s;//小圆比分比
	private LinearGradient lgdn;
	//小圆
	private Paint p_s;//小圆
	private Paint p_sout;
	private RectF area_s;
	private int viewx;
	private int viewy;
	private int minmiss=3;
	private int maxmiss=25;
	public String availa_mom="";
	public Myprogress(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init();
	}
	public Myprogress(Context context,AttributeSet arg0,int arg1) {
		super(context,arg0,arg1);
		// TODO Auto-generated constructor stub
		init();
	}
	public Myprogress(Context context,AttributeSet arg0) {
		super(context,arg0);
		// TODO Auto-generated constructor stub
		init();
	}
	public void setProgress(int value,int value_s)
	{
	
		this.value=value;
		if(value>=100)
		{this.value=100;}
		
		this.value_s=value_s;
		if(value_s>=100)
		{this.value_s=100;}
		
		this.invalidate();
	}
	public void init()
	{
		p=new Paint();
		ptext=new Paint();
		p.setStrokeWidth(10f);
		p.setColor(Color.WHITE);
		p.setStyle(Style.STROKE);
		p_s=new Paint();
		p_s.setStrokeWidth(3.5f);
		p_s.setColor(Color.WHITE);
		p_s.setStyle(Style.STROKE);
		p_sout=new Paint();
		p_sout.setStrokeWidth(3f);
		p_sout.setColor(Color.WHITE);
		p_sout.setStyle(Style.STROKE);
		pout=new Paint();
		pout.setStrokeWidth(2f);
		pout.setColor(Color.WHITE);
		pout.setStyle(Style.STROKE);
		ptext.setTextSize(25f);
		ptext.setColor(Color.WHITE);
		ptext_2=new Paint();
		ptext_2.setTextSize(12f);
		ptext_2.setColor(Color.WHITE);
		

		

		
		
		
		
		lgdn=new LinearGradient(0, 0, 50, 0, new int[]{Color.parseColor("#FFAEB9"),Color.WHITE}, null, Shader.TileMode.CLAMP);
		p.setShader(lgdn);
		p_s.setShader(lgdn);
	}
	@Override
	protected void onDraw(Canvas canvas)
	{
		viewx=this.getWidth();
		viewy=this.getHeight();
		if(viewx>viewy)
		{
		//大圆区域
		areaout=new RectF((int)(viewx/2-viewy*0.4)-3,(int)(viewy*0.1)-3,(int)(viewx/2+viewy*0.4)+3,(int)(viewy*0.9)+3);
		area=new RectF((int)(viewx/2-viewy*0.4),(int)(viewy*0.1),(int)(viewx/2+viewy*0.4),(int)(viewy*0.9));
		//小圆区域
		area_s=new RectF((int)(viewx/2+viewx*0.08),0,(int)(viewx/2+viewx*0.08+viewy/2.3),(int)(viewy/2.3));
		areaout_s=new RectF((int)(viewx/2+viewx*0.08)-3,0-3,(int)(viewx/2+viewx*0.08+viewy/2.3)+3,(int)(viewy/2.3)+3);
		}
		else
		{
			area=new RectF((int)(viewx*0.2),(int)(viewy*0.1),(int)(viewx*0.8),(int)(viewx*0.6+viewy*0.1));
			area_s=new RectF((int)(viewx/2+viewx*0.07),0,(int)(viewx/2+viewx*0.07+viewx/2.7),(int)(viewx/2.7));
			
			areaout=new RectF((int)(viewx*0.2)-3,(int)(viewy*0.1)-3,(int)(viewx*0.8)+3,(int)(viewx*0.6+viewy*0.1)+3);
			areaout_s=new RectF((int)(viewx/2+viewx*0.07)-3,0-3,(int)(viewx/2+viewx*0.07+viewx/2.7)+3,(int)(viewx/2.7)+3);
		}
		
		
		canvas.drawColor(Color.parseColor("#99b802"));
		if(value<5)
		{
			canvas.drawArc(area, -90, value*3.6f, false, p);
		}
		
		if(value>5&&value<33)
		{
			canvas.drawArc(area, -90, 4*3.6f, false, p);
		}
		else if(value>33)
		{
			canvas.drawArc(area, -90, 4*3.6f, false, p);
	   	canvas.drawArc(area, 0, (value-33)*3.6f, false, p);
		}
		
		
		canvas.drawArc(areaout, 0, 79*3.6f, false, pout);//外圆 大圆
		
		canvas.drawText(String.valueOf(value)+"%", (int)(viewx*0.4), viewy/2, ptext);//大圆文字
		canvas.drawText("剩余空间", (int)(viewx*0.4), (int)(viewy*0.65), ptext_2);
		canvas.drawText(availa_mom, (int)(viewx*0.4),(int)(viewy*0.75), ptext_2);
		
		canvas.drawText(String.valueOf(value_s)+"%",(int)(viewx/2+viewx*0.07+viewy/2.3/3) , (int)(viewy/2.3/2), ptext_2);//小圆文字
		canvas.drawText("内存",(int)(viewx/2+viewx*0.07+viewy/2.3/3) , (int)(viewy/2.3/2*1.5), ptext_2);
		
		canvas.drawArc(area_s,-70, value_s*3.6f, false, p_s);//小圆
		canvas.drawArc(areaout_s, -70,360,false, p_sout);//外切小圆
		
	}
	
	
	

}

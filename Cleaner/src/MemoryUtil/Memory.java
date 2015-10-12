package MemoryUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.DecimalFormat;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.text.format.Formatter;
import android.util.Log;

public class Memory {
	
	
	
	
	
  public static void getTotalMem(Context context)
  {
	String state=Environment.getExternalStorageState();
	int SDcard=0;
	if(Environment.MEDIA_MOUNTED.equals(state))
	{
		File sdf=Environment.getExternalStorageDirectory();
		StatFs sfs_sd=new StatFs(sdf.getPath());
		SDcard=sfs_sd.getBlockCount()*sfs_sd.getBlockSize();
	}
    File f=Environment.getRootDirectory();
    StatFs sf=new StatFs(f.getPath());
    String result;
    result=Formatter.formatFileSize(context, sf.getBlockCount()*sf.getBlockSize()+SDcard);
       Log.d("on memory", result);
  }
  public static String getAvailableiMem(Context context)
  {
		String state=Environment.getExternalStorageState();
		int SDcard=0;
		if(Environment.MEDIA_MOUNTED.equals(state))
		{
			File sdf=Environment.getExternalStorageDirectory();
			StatFs sfs_sd=new StatFs(sdf.getPath());
			SDcard=sfs_sd.getAvailableBlocks()*sfs_sd.getBlockSize();
		}
		  File f=Environment.getRootDirectory();
		    StatFs sf=new StatFs(f.getPath());
		    String result;
		   result=Formatter.formatFileSize(context, sf.getAvailableBlocks()*sf.getBlockSize()+SDcard);
		   return result;
  }
  public static int getTotalPercent(Context context)
  {

		String state=Environment.getExternalStorageState();
		long SDcard_total=0;
		long SDcard_availa=0;
		long root_total=0;
		long root_availa=0;
		long blockcount=0;
		long blocksize=0;
		if(Environment.MEDIA_MOUNTED.equals(state))
		{
			File sdf=Environment.getExternalStorageDirectory();
			StatFs sfs_sd=new StatFs(sdf.getPath());
			blockcount=sfs_sd.getAvailableBlocks();
			blocksize=sfs_sd.getBlockSize();
			SDcard_availa=blockcount*blocksize;
			blockcount=sfs_sd.getBlockCount();
			blocksize=sfs_sd.getBlockSize();
			SDcard_total=blockcount*blocksize;
		}
		  File f=Environment.getRootDirectory();
		    StatFs sf=new StatFs(f.getPath());
		    blockcount=sf.getBlockCount();
			blocksize=sf.getBlockSize();
		    root_total=blockcount*blocksize;
		    blockcount=sf.getAvailableBlocks();
		    root_availa=blockcount*blocksize;
	        double total=root_total+SDcard_total;
	        double availa=root_availa+SDcard_availa;
	        total=total/1024/1024;
	        availa=availa/1024/1024;
		    int result=(int)Math.rint(availa/total*100);
		   
		 return result;
  }

  public static int getRunMemPercent(Context context)
  {
	  String mem_path="/proc/meminfo";
	  String result="";
	  String line;
	  int percent=-1;
	  try {
		BufferedReader br=new BufferedReader(new FileReader(mem_path),8);
		while((line=br.readLine())!=null)
		{
			result+=line;
		}
		Log.d("mem", " "+result);
		int start=result.indexOf(":");
		int end=result.indexOf("k");
		Log.d("mem", result.substring(start+1, end).trim());
		result=result.substring(start+1, end).trim();
		 ActivityManager att_man=(ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
		 ActivityManager.MemoryInfo att_mom=new ActivityManager.MemoryInfo();
		 att_man.getMemoryInfo(att_mom);
		 long a=att_mom.availMem/1024;
		 double b=Double.parseDouble(result);
		percent=(int)Math.rint((1-(a/b))*100);
		return percent;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  return percent;
	  
  }
	
}

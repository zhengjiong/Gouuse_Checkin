
package com.gouuse.checkin.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 */
public class FileUtils {

    public static File createTmpFile(Context context){

        String state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)){
            // 已挂载
            File pic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            if (!pic.exists()) {
                pic.mkdirs();
            }
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
            String fileName = "multi_image_"+timeStamp+"";
            File tmpFile = new File(pic, fileName+".jpg");
            return tmpFile;
        }else{
//            File cacheDir = context.getCacheDir();
            File cacheDir = context.getFilesDir();
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
            String fileName = "multi_image_"+timeStamp+"";
            File tmpFile = new File(cacheDir, fileName+".jpg");
            return tmpFile;
        }

    }

    public static File createPhoneFile(Context context){

        String state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)){
            // 已挂载
//            File pic = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
            File pic = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"MyCameraApp");
            if (!pic.exists()) {
                pic.mkdirs();
            }
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
            String fileName = "multi_image_"+timeStamp+"";
            File tmpFile = new File(pic, fileName+".jpg");
            try{
//            	NewSmallTaklActivity.gLogger.debug("MEDIA_MOUNTED:"+"Environment.MEDIA_MOUNTED");
            }catch(Exception e){}
            return tmpFile;
        }else{
//            File cacheDir = context.getCacheDir();
            File cacheDir = context.getFilesDir();
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.CHINA).format(new Date());
            String fileName = "multi_image_"+timeStamp+"";
            File tmpFile = new File(cacheDir, fileName+".jpg");
            try{
//            	NewSmallTaklActivity.gLogger.debug("else:"+"else");
            }catch(Exception e){}
            return tmpFile;
        }

    }

    public static String getFileSizeConversionString(long fileSize) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeConversion = "";
        if (fileSize < 1024) {
            fileSizeConversion = fileSize + "B";
        }else if (fileSize >= 1024 && fileSize < 1024 * 1024) {
            fileSizeConversion = df.format(fileSize / 1024.0) + "KB";
        }else if (fileSize >= 1024.0 * 1024) {
            fileSizeConversion = df.format(fileSize / (1024.0 * 1024.0)) + "MB";
        }
        return fileSizeConversion;
    }

    public static void deleteCache(Context context) {
        deleteDir(context.getCacheDir());

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            deleteDir(context.getExternalCacheDir());
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    public static String getFormatSizeString(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
//            return size + "Byte";
            return "0K";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                    .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }



    public static String getTotalCacheSize(Context context) throws Exception {
        long cacheSize = getFolderSize(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            cacheSize += getFolderSize(context.getExternalCacheDir());
        }
        return getFormatSizeString(cacheSize);
    }

}

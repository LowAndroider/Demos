package com.exam.basecomponent.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * @author LowAndroider
 * @date 2018/9/26
 */
public class BitmapUtil {

    /**
     * 计算图片压缩比例
     *
     * @param reqWidth  目标分辨率宽度
     * @param reqHeight 目标分辨率高度
     * @return 压缩率
     */
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
// Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            if (width > height) {
                inSampleSize = Math.round((float) height / (float) reqHeight);
            } else {
                inSampleSize = Math.round((float) width / (float) reqWidth);
            }

            return inSampleSize;
        }
        return inSampleSize;
    }

    /**
     * 图片压缩
     *
     * @param filename  文件路径
     * @param reqWidth  目标宽度
     * @param reqHeight 目标高度
     * @return bitmap
     */
    public static Bitmap decodeSampledBitmapFromFile(String filename,
                                                     int reqWidth, int reqHeight) {

// First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filename, options);

// Calculate inSampleSize
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

// Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filename, options);
    }

    /**
     * 3.质量压缩
     * 设置bitmap options属性，降低图片的质量，像素不会减少, 不会影响bitmap的内存
     * 第一个参数为需要压缩的bitmap图片对象，第二个参数为压缩后图片保存的位置
     * 设置options 属性0-100，来实现压缩
     *
     * @param bmp
     * @param file
     */
    public static void qualityCompress(Bitmap bmp, File file) {
        // 0-100 100为不压缩
        int quality = 20;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // 把压缩后的数据存放到baos中
        bmp.compress(Bitmap.CompressFormat.JPEG, quality, baos);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(baos.toByteArray());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

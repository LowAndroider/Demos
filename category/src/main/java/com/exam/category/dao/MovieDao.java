package com.exam.category.dao;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.exam.category.entity.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieDao {

    public interface PermissionCallback {
        void onPermissionGranted();

        default void onFail(Context context) {
            Toast.makeText(context, "无法获取读取文件权限，请重试", Toast.LENGTH_SHORT).show();
        }
    }

    private PermissionCallback permissionCallback;

    public void setPermissionCallback(PermissionCallback callback) {
        this.permissionCallback = callback;
    }

    public static final int PERMISSION_CODE = 0xF23;

    private Context context;


    public MovieDao(Context context) {
        this.context = context;
    }

    public boolean checkPermission() {
        int check = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE);

        return check == PackageManager.PERMISSION_GRANTED;
    }

    public final List<Movie> getList() {
        List<Movie> movieList = new ArrayList<>();

        Movie movie;

        ContentResolver contentResolver = context.getContentResolver();

        String[] projs = {
                MediaStore.Video.Thumbnails._ID,
                MediaStore.Video.Thumbnails.DATA,
                MediaStore.Video.Media._ID,
                MediaStore.Video.Media.DISPLAY_NAME,
                MediaStore.Video.Media.SIZE,
                MediaStore.Video.Media.DURATION,
                MediaStore.Video.Media.DATA
        };

        Cursor cr = contentResolver.query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                projs, null, null, MediaStore.Video.Media.DATE_MODIFIED + " desc");
        if (cr != null) {
            for (cr.moveToFirst(); !cr.isAfterLast(); cr.moveToNext()){
                movie = new Movie();
                int imgId = cr.getInt(cr.getColumnIndexOrThrow(MediaStore.Video.Thumbnails._ID));
                String imgData = cr.getString(cr.getColumnIndexOrThrow(MediaStore.Video.Thumbnails.DATA));
                int id = cr.getInt(cr.getColumnIndexOrThrow(MediaStore.Video.Media._ID));
                String displayName = cr.getString(cr.getColumnIndexOrThrow(MediaStore.Video.Media.DISPLAY_NAME));
                long size = cr.getLong(cr.getColumnIndexOrThrow(MediaStore.Video.Media.SIZE)) / 1024L;
                long duration = cr.getLong(cr.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION));
                String data = cr.getString(cr.getColumnIndexOrThrow(MediaStore.Video.Media.DATA));
                movie.setImgId(imgId)
                        .setImgData(imgData)
                        .setId(id)
                        .setDisplayName(displayName)
                        .setSize(size)
                        .setDuration(duration)
                        .setData(data);

                movieList.add(movie);
            }


            cr.close();
        }

        return movieList;
    }

    /**
     * 请求权限
     *
     * @param activity
     */
    public void reqPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_CODE);
    }

    /**
     * 权限请求反馈
     */
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == PERMISSION_CODE) {
            if (permissionCallback != null) {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    permissionCallback.onPermissionGranted();
                } else {
                    permissionCallback.onFail(context);
                }
            }
        }
    }
}

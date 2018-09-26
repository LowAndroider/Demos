package com.exam.basecomponent.util;

import android.content.ContentUris;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.util.Log;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @author LowAndroider
 * @date 2018/9/26
 */
public class FileUtil {


    /**
     * 根据uri获取真实路径
     */

    public String getRealPathFromURI(Context context, Uri uri) {
        Log.e("getRealPathFromURI", "$uri");
        boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            if ("com.exam.fileprovider".equals(uri.getAuthority())) {
                return getFPUriToPath(context, uri);
            }
        }
        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                Log.e("getRealPathFromURI", "isExternalStorageDocument");
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                String type = split[0];
                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory().toString() + "/" + split[1];
                }
            } else if (isDownloadsDocument(uri)) {
                Log.e("getRealPathFromURI", "isDownloadsDocument");
                String id = DocumentsContract.getDocumentId(uri);
                Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.parseLong(id));
                return getDataColumn(context, contentUri, null, null);
            } else if (isMediaDocument(uri)) {
                Log.e("getRealPathFromURI", "isMediaDocument");
                String docId = DocumentsContract.getDocumentId(uri);
                String[] split = docId.split(":");
                String type = split[0];
                Uri contentUri = null;
                switch (type) {
                    case "image":
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                        break;
                    case "video":
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                        break;
                    case "audio":
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                        break;
                    default:
                        break;
                }
                String selection = "_id=?";
                String[] selectionArgs = new String[] {split[1]};
                return getDataColumn(context, contentUri, selection, selectionArgs);
            }// MediaProvider
            // DownloadsProvider
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // Return the remote address
            if(isGooglePhotosUri(uri)) {
                return uri.getLastPathSegment();
            } else {
                return getDataColumn(context, uri, null, null);
            }
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }// File
        // MediaStore (and general)
        return null;
    }

    private String getDataColumn(Context context, Uri uri, String selection,
                                 String[] selectionArgs) {
        Log.e("getDataColumn", "${uri.toString()}");
        Cursor cursor = null;
        String column = MediaStore.Images.Media.DATA;
        String[] projection = new String[]{column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    private boolean isExternalStorageDocument(Uri uri){
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    private Boolean isDownloadsDocument(Uri uri){
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    private Boolean isMediaDocument(Uri uri){
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    private boolean isGooglePhotosUri(Uri uri) {
        return "com.google.android.apps.photos.content".equals(uri.getAuthority());
    }

    // 针对android N 以后的操作
    private String getFPUriToPath(Context context, Uri uri) {
        StringBuffer path = new StringBuffer();
        List<PackageInfo> packs = context.getPackageManager().getInstalledPackages(PackageManager.GET_PROVIDERS);
        if (packs != null) {
            String fileProviderClassName = FileProvider.class.getName();

            Foreach.create(packs)
                    .filter(packageInfo -> packageInfo.providers != null)
                    .map(packageInfo -> {
                        return Arrays.asList(packageInfo.providers);
                    }).filter(providerInfo -> uri.getAuthority().equals(providerInfo.authority) && providerInfo.name.equalsIgnoreCase(fileProviderClassName))
                    .map(providerInfo -> {
                        Class<FileProvider> fileProviderClass = FileProvider.class;
                        Method getPathStrategy = null;
                        try {
                            getPathStrategy = fileProviderClass.getDeclaredMethod("getPathStrategy", Context.class, String.class);
                            getPathStrategy.setAccessible(true);
                            Object invoke = getPathStrategy.invoke(null, context, uri.getAuthority());
                            if (invoke != null) {
                                String pathStrategyStringClass = FileProvider.class.getName() + "$PathStrategy";
                                Class pathStrategy = Class.forName(pathStrategyStringClass);
                                Method getFileForUri = pathStrategy.getDeclaredMethod("getFileForUri", Uri.class);
                                getFileForUri.setAccessible(true);
                                Object invoke1 = getFileForUri.invoke(invoke, uri);
                                if (invoke1 instanceof File) {
                                    path.append(((File) invoke1).getAbsolutePath());
                                }
                            }
                        } catch (Exception e) {
                        }
                    });
        }

        return path.toString();
    }
}

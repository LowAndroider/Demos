package com.exam.basecomponent.net;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author LowAndroider
 * @date 2018/9/26
 */
public abstract class CustomCallback implements Callback {

    private Context mContext;
    private Handler handler;

    public CustomCallback(Context context) {
        mContext = context;
        handler = new Handler(mContext.getMainLooper());
    }

    @Override
    public void onFailure(Call call, IOException e) {
        handler.post(() -> failure());
    }

    @Override
    public void onResponse(Call call, final Response response) throws IOException {
        final String content = response.body().string();
        Log.e("response_content",content);

        handler.post(() -> deal(content));
    }

    protected abstract void deal(String data);

    public void failure() {
        Toast.makeText(mContext, "请求失败", Toast.LENGTH_SHORT).show();
    }
}


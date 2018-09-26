package com.exam.basecomponent.net;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * @author LowAndroider
 * @date 2018/9/26
 */
public abstract class CallBackWithBean<T extends Response> implements Callback {

    private Context mContext;
    private Handler handler;
    private Type mType;

    public CallBackWithBean(Context context, TypeToken<T> type) {
        mContext = context;
        handler = new Handler(mContext.getMainLooper());
        mType = type.getType();
    }

    @Override
    public void onFailure(Call call, IOException e) {
        handler.post(() -> failure());
    }

    @Override
    public void onResponse(Call call, final okhttp3.Response response) throws IOException {
        final String content = response.body().string();
        Log.e("response_content", content);
        try {
            final T bean = new Gson().fromJson(content, mType);
            handler.post(() -> deal(bean));
        } catch (Exception e) {
            Looper.prepare();
            Toast.makeText(mContext, "数据异常", Toast.LENGTH_SHORT).show();
            unexpectedData();
            Looper.loop();
        }
    }

    /**
     * 对返回正确格式的数据进行处理
     */
    protected abstract void deal(T data);

    /**
     * 对返回数据错误的处理
     */
    protected void unexpectedData() {

    }

    private void failure() {
//        Toast.makeText(mContext, "请求失败", Toast.LENGTH_SHORT).show();
    }
}

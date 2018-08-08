package com.mredrock.cyxbs.freshman.HttpUtils.callback;


public interface NetCallback {
    void onResponse(String json);
    void onFailure(Throwable throwable);
}

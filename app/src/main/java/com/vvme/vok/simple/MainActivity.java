package com.vvme.vok.simple;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.vvme.permission.EzPermission;
import com.vvme.permission.Permission;
import com.vvme.permission.action.Action;
import com.vvme.permission.action.RationaleAction;
import com.vvme.permission.request.PermissionExecutor;
import com.vvme.vok.Vok;
import com.vvme.vok.chain.CustomResponseChain;
import com.vvme.vok.parser.CustomResponseParser;
import com.vvme.vok.callback.ProgressCallback;
import com.vvme.vok.callback.VokCallback;
import com.vvme.vok.model.FileSource;
import com.vvme.vok.model.ParamsModel;
import com.vvme.vok.response.FakeResponseBuilder;
import com.vvme.vok.response.ResponseProcessor;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.text);
        mImageView = findViewById(R.id.image);
        if (!EzPermission.hasPermission(this, Permission.Group.STORAGE)) {
            EzPermission.with(this).permission(Permission.Group.STORAGE).onGranted(new Action<List<String>>() {
                @Override
                public void onAction(List<String> data) {
//                    download();
                    upload();
//                    loadFile();
                }
            }).onDenied(new Action<List<String>>() {
                @Override
                public void onAction(List<String> data) {

                }
            }).rationale(new RationaleAction<List<String>>() {
                @Override
                public void onAction(Context context, List<String> data, final PermissionExecutor executor) {
                    new AlertDialog.Builder(context).setMessage("我们需要申请这些权限进行测试").setPositiveButton("好", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            executor.execute();
                        }
                    }).setNegativeButton("不好", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            executor.cancel();
                        }
                    }).show();
                }
            }).start();
        } else {
//            download();
            upload();
//            loadFile();
        }
        loadString();
        loadImage();
        loadJson();
        loadJsonList();
    }

    private void loadFile() {
        Vok
                .get("http://img5.duitang.com/uploads/item/201302/04/20130204103323_Fa423.thumb.700_0.jpeg")
                .asFile()
                .fileName("美女.jpg")
                .filePath(Environment.getExternalStorageDirectory() + File.separator + "aaVOK_download")
                .progressCallback(new ProgressCallback() {
                    @Override
                    public void progress(int id, long totalSize, long currentSize, float progress) {
                        Log.d("Vok", "下载文件的进度: " + progress);
                    }
                })
                .go(new VokCallback<File>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(File data) {
                        Log.d("Vok", "将图片保存到本地成功: " + data.getAbsolutePath());
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onFailed(int code, String msg) {

                    }
                });
    }

    private void upload() {
        Vok.upload("http://f4.market.xiaomi.com/download/AppStore/0f72124ac2c6549f82bc862d5e2dcc8424ec1412d/com.miui.whitenoise.apk")
                .param("11", "222")
                .progressCallback(new ProgressCallback() {
                    @Override
                    public void progress(int id, long totalSize, long currentSize, float progress) {
                        Log.d("Vok", "上传进度: " + progress);
                    }
                })
                .progressPublishTime(1000)
                .fileSource(new FileSource("file", "fileName", new File(Environment.getExternalStorageDirectory() + File.separator + "aaVOK_download", "小米白噪音.apk")))
                .go(new VokCallback<String>() {
                    @Override
                    public void onStart() {
                        Log.d("Vok", "开始上传: ");
                    }

                    @Override
                    public void onSuccess(String data) {
                        Log.d("Vok", "开始上传: ");
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.d("Vok", "上传失败: " + throwable.getMessage());
                    }

                    @Override
                    public void onFailed(int code, String msg) {

                    }
                });
    }

    private void download() {
        //白噪音app
        Vok.download("http://f4.market.xiaomi.com/download/AppStore/0f72124ac2c6549f82bc862d5e2dcc8424ec1412d/com.miui.whitenoise.apk")
                .fileName("小米白噪音")
                .filePath(Environment.getExternalStorageDirectory() + File.separator + "aaVOK_download")
                .progressCallback(new ProgressCallback() {
                    @Override
                    public void progress(int id, long totalSize, long currentSize, float progress) {
                        Log.d("Vok", "文件下载进度: " + progress);
                    }
                })
                .progressPublishTime(1000)
                .client(new OkHttpClient())
                .go(new VokCallback<File>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(File data) {
                        Log.d("Vok", "文件下载成功." + data.getAbsolutePath());
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onFailed(int code, String msg) {

                    }
                });
    }

    private void loadJsonList() {

        Vok.get("http://wanandroid.com/tools/mockapi/1564/getJson")
                .asJson()
                .openFakeData(true)
                .fakeDataBuilder(new FakeResponseBuilder() {
                    @Override
                    public String buildFakeResponse() {
                        List<TestBean> list = new ArrayList();
                        TestBean testBean = new TestBean();
                        testBean.setCode(999);
                        testBean.setName("假数据测试");
                        testBean.setMark("这真的是假数据啊.");
                        list.add(testBean);
                        Gson gson = new Gson();
                        String json = gson.toJson(list);
                        return json;
                    }
                })
                .responseChain(new CustomResponseChain() {
                    @Override
                    public boolean chain(Call call, Response response, ParamsModel paramsModel, boolean isFakeData) {
                        //如果返回false,则自己处理所有的逻辑
                        return true;
                    }
                })
                .responseProcessor(new ResponseProcessor() {
                    @Override
                    public void onResponse(int id, Response response) {
                        Log.d("hate", "自定义ResponseProcessor");
                    }
                })
                .responseParser(new CustomResponseParser<List<TestBean>>() {
                    @Override
                    public List<TestBean> parseResponse(ParamsModel paramsModel, Response response, Type type) throws Exception {
                        Log.d("hate", "自定义ResponseParser");
                        String string = response.body().string();
                        Gson gson = new Gson();
                        return gson.fromJson(string, type);
                    }
                })
                .go(new VokCallback<List<TestBean>>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(List<TestBean> data) {
                        Log.d("hate", "json 成功: " + data.get(0).getMark());
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onFailed(int code, String msg) {

                    }
                });
    }

    private void loadJson() {
        Vok.get("http://wanandroid.com/tools/mockapi/1564/myTest")
                .asJson()
                .openFakeData(true)
                .fakeDataBuilder(new FakeResponseBuilder() {
                    @Override
                    public String buildFakeResponse() {
                        return null;
                    }
                })
                .responseChain(new CustomResponseChain() {
                    @Override
                    public boolean chain(Call call, Response response, ParamsModel paramsModel, boolean isFakeData) {
                        return false;
                    }
                })
                .go(new VokCallback<TestBean>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(TestBean data) {
                        Log.d("hate", "json 成功: " + data.getMark());
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onFailed(int code, String msg) {

                    }
                });
    }

    private void loadString() {
        Vok
                .get("http://wanandroid.com/tools/mockapi/1564/myTest")
                .param("111", "2222")
                .asString()
                .openFakeData(true)
                .fakeDataBuilder(new FakeResponseBuilder() {
                    @Override
                    public String buildFakeResponse() {
                        TestBean testBean = new TestBean();
                        testBean.setCode(999);
                        testBean.setName("假数据测试");
                        testBean.setMark("这真的是假数据啊.");
                        Gson gson = new Gson();
                        String json = gson.toJson(testBean);
                        return json;
                    }
                })
                .responseChain(new CustomResponseChain() {
                    @Override
                    public boolean chain(Call call, Response response, ParamsModel paramsModel, boolean isFakeData) {
                        return true;
                    }
                })
                .responseProcessor(new ResponseProcessor() {
                    @Override
                    public void onResponse(int id, Response response) {
                        Log.d("hate", "自定义ResponseProcessor");
                    }
                })
                .responseParser(new CustomResponseParser<String>() {
                    @Override
                    public String parseResponse(ParamsModel paramsModel, Response response, Type type) throws Exception {
                        Log.d("hate", "自定义ResponseParser");
                        return response.body().string();
                    }
                })
                .go(new VokCallback<String>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(String data) {
                        Log.d("hate", "请求成功: " + data);
                        mTextView.setText(data);
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onFailed(int code, String msg) {

                    }
                });
    }

    private void loadImage() {
        Vok.get("http://img5.duitang.com/uploads/item/201302/04/20130204103323_Fa423.thumb.700_0.jpeg")
                .asBitmap()
                .go(new VokCallback<Bitmap>() {
                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onSuccess(Bitmap data) {
                        mImageView.setImageBitmap(data);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        Log.d("Vok", "onError:" + throwable.getMessage());
                    }

                    @Override
                    public void onFailed(int code, String msg) {
                        Log.d("Vok", "onFailed:" + msg);
                    }
                });
    }
}

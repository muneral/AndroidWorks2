package chapter.android.aweme.ss.com.homework;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * 作业2：一个抖音笔试题：统计页面所有view的个数
 * Tips：ViewGroup有两个API
 * {@link android.view.ViewGroup #getChildAt(int) #getChildCount()}
 * 用一个TextView展示出来
 */
public class Exercises2 extends AppCompatActivity {

    private static TextView count;
    private static View view;
    private static Button button;

    private static final String TAG = "zhuchenyi";
    private  void logAndAppend(View view) {
        Log.d(TAG,"ViewCount Event:" + view);
        count.append("\nview总个数:\n\t\t\t\t\t" + getAllChildViewCount(view));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewnum);
        count = findViewById(R.id.tv_center);
        view = findViewById(R.id.viewsn);
        button = findViewById(R.id.btn_left_top);
        logAndAppend(view);
    }


    public int getAllChildViewCount(View root) {
        // to do 补全你的代码
        int viewCount = 0;

        if (null == root) {
            return 0;
        }

        if (root instanceof ViewGroup) {

            for (int i = 0; i < ((ViewGroup)root).getChildCount(); i++){
                View view = ((ViewGroup) root).getChildAt(i);
                if(view instanceof  ViewGroup){
                    viewCount += getAllChildViewCount(view);
                }
                else{
                    viewCount++;
                }
            }
        }
        else{
            viewCount++;
        }
            return viewCount;
    }
}

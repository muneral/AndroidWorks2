package chapter.android.aweme.ss.com.homework.Exercise3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import chapter.android.aweme.ss.com.homework.R;

public class message extends Activity {

    private TextView tv_content_info;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatroom);
        tv_content_info = findViewById(R.id.tv_content_info);

        Intent dataIntent = getIntent();
        int index = dataIntent.getIntExtra("index",0);

        tv_content_info.append("点击第"+index+"条item");
        //tv_content_info.setText("点击第"+index+"条item");

    }


}

package chapter.android.aweme.ss.com.homework.Exercise3;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import chapter.android.aweme.ss.com.homework.R;
import chapter.android.aweme.ss.com.homework.model.Message;
import chapter.android.aweme.ss.com.homework.model.PullParser;
import chapter.android.aweme.ss.com.homework.widget.CircleImageView;

/**
 * 大作业:实现一个抖音消息页面,
 * 1、所需的data数据放在assets下面的data.xml这里，使用PullParser这个工具类进行xml解析即可
 * <p>如何读取assets目录下的资源，可以参考如下代码</p>
 * <pre class="prettyprint">
 *
 *         @Override
 *     protected void onCreate(@Nullable Bundle savedInstanceState) {
 *         super.onCreate(savedInstanceState);
 *         setContentView(R.layout.activity_xml);
 *         //load data from assets/data.xml
 *         try {
 *             InputStream assetInput = getAssets().open("data.xml");
 *             List<Message> messages = PullParser.pull2xml(assetInput);
 *             for (Message message : messages) {
 *
 *             }
 *         } catch (Exception exception) {
 *             exception.printStackTrace();
 *         }
 *     }
 * </pre>
 * 2、所需UI资源已放在res/drawable-xxhdpi下面
 *
 * 3、作业中的会用到圆形的ImageView,可以参考 widget/CircleImageView.java
 */
public class Exercises3 extends AppCompatActivity implements EAdapter.ListItemClickListener {

    private static final String TAG = "langyu";
    private static final int NUM_LIST_ITEMS = 100;
    private List<Item> ItemList = new ArrayList<>();

    private EAdapter mAdapter;
    private RecyclerView mNumbersListView;

    private Toast mToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips);
        mNumbersListView = findViewById(R.id.rv_list);
        //设置布局管理器
        initeCustomer();  //初始化List
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mNumbersListView.setLayoutManager(layoutManager);
        /*
         * Use this setting to improve performance if you know that changes in content do not
         * change the child layout size in the RecyclerView
         */
        mNumbersListView.setHasFixedSize(true);
        /*
         * The GreenAdapter is responsible for displaying each item in the list.
         */
        //设置数据适配器
        mAdapter = new EAdapter(ItemList,this);

        mNumbersListView.setAdapter(mAdapter);
        mNumbersListView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            // 最后一个完全可见项的位置
            private int lastCompletelyVisibleItemPosition;

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (visibleItemCount > 0 && lastCompletelyVisibleItemPosition >= totalItemCount - 1) {
                        Toast.makeText(Exercises3.this,"已滑动到底部!,触发loadMore", Toast.LENGTH_SHORT).show();;
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                if (layoutManager instanceof LinearLayoutManager) {
                    lastCompletelyVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastCompletelyVisibleItemPosition();
                }
                Log.d(TAG, "onScrolled: lastVisiblePosition=" + lastCompletelyVisibleItemPosition);
            }
        });
    }

    protected void initeCustomer( ) {
        //setContentView(R.layout.activity_xml);
        //load data from assets/data.xml
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            builder = builderFactory.newDocumentBuilder();
            Document document = builder.parse(getAssets().open("data.xml"));
            Element element = document.getDocumentElement();
            NodeList nodeList = element.getElementsByTagName("message");
            //   InputStream assetInput = getAssets().open("data.xml");
            //   List<Message> messages = PullParser.pull2xml(assetInput);
            for (int i=0 ; i<nodeList.getLength() ; i++) {
                Element message = (Element) nodeList.item(i);
                String title = message.getElementsByTagName("title").item(0).getTextContent();
                String description = message.getElementsByTagName("hashtag").item(0).getTextContent();
                String time = message.getElementsByTagName("time").item(0).getTextContent();
                String image = message.getElementsByTagName("icon").item(0).getTextContent();

                int icon;
                if(image.equals("TYPE_ROBOT") ){
                    icon=R.drawable.session_robot;
                }
                else if(image .equals("TYPE_GAME") ){

                    icon = R.drawable.icon_micro_game_comment;
                }
                else if(image.equals("TYPE_SYSTEM")){
                    icon = R.drawable.session_system_notice;
                }
                else if(image.equals("TYPE_STRANGER") ){
                    icon = R.drawable.session_stranger;
                }
                else{
                    icon = R.drawable.icon_girl;
                }
                Item Itemm = new Item(title,description,time,icon);
                ItemList.add(Itemm);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public void onListItemClick(int clickedItemIndex) {

        //if (mToast != null) {
          // mToast.cancel();
        //}
       // String toastMessage = "Item #" + clickedItemIndex + " clicked.";
       // mToast = Toast.makeText(this, toastMessage, Toast.LENGTH_LONG);

        // mToast.show();

        Intent intent = new Intent(Exercises3.this , message.class);
        intent.putExtra("index",clickedItemIndex);
        startActivity(intent);
        //startActivity(new Intent(this, message.class));



        Log.d(TAG, "onListItemClick: ");
    }


}

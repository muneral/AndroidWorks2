package chapter.android.aweme.ss.com.homework.Exercise3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import chapter.android.aweme.ss.com.homework.R;
import chapter.android.aweme.ss.com.homework.widget.CircleImageView;

public class EAdapter extends RecyclerView.Adapter<EAdapter.ViewHolder>{

    private static final String TAG = "EAdapter";
    private int mNumberItems;
    private List<Item> ItemList ;
    private final ListItemClickListener mOnClickListener;
    private static int viewHolderCount;

    public EAdapter(List<Item> ItemList,ListItemClickListener listener) {
        this.mOnClickListener =  listener;
        this.ItemList = ItemList;
        viewHolderCount = 0;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        int layoutIdForListItem = R.layout.im_list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutIdForListItem, viewGroup, shouldAttachToParentImmediately);
        ViewHolder viewHolder = new ViewHolder(view);

        Log.d(TAG, "onCreateViewHolder: number of ViewHolders created: " + viewHolderCount);
        viewHolderCount++;
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Log.d(TAG, "onBindViewHolder: #" + position);

        Item Itemm = ItemList.get(position);
        viewHolder.tv_title.setText(Itemm.getTitle());
        viewHolder.tv_description.setText(Itemm.getDescription());
        viewHolder.tv_time.setText(Itemm.getTTime());
        viewHolder.iv_avatar.setImageResource(Itemm.getImageId());
        // viewHolder.iv_avatar等与data.xml绑定
    }

    @Override
    public int getItemCount() {
        return ItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final TextView tv_title;
        private final TextView tv_description;
        private final TextView tv_time;
        private final CircleImageView iv_avatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title);
            tv_description = (TextView) itemView.findViewById(R.id.tv_description);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            iv_avatar = (CircleImageView) itemView.findViewById(R.id.iv_avatar);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int clickedPosition = getAdapterPosition();
            if (mOnClickListener != null) {
                mOnClickListener.onListItemClick(clickedPosition);
            }
        }
    }

    public interface ListItemClickListener {
        void onListItemClick(int clickedItemIndex);
    }
}

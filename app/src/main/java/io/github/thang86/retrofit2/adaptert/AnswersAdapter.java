package io.github.thang86.retrofit2.adaptert;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import io.github.thang86.retrofit2.R;
import io.github.thang86.retrofit2.model.Item;

public class AnswersAdapter extends RecyclerView.Adapter<AnswersAdapter.ViewHolder> {

    private List<Item> mItems;
    private Context mContext;
    private PostItemListener mItemListener;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView titleName;
        public TextView tvId;
        public ImageView urlAvatar;
        PostItemListener mItemListener;

        public ViewHolder(View itemView, PostItemListener postItemListener) {
            super(itemView);
            titleName = (TextView) itemView.findViewById(R.id.tv_name);
            tvId = (TextView) itemView.findViewById(R.id.tv_id);
            urlAvatar = (ImageView) itemView.findViewById(R.id.iv_avatar);
            this.mItemListener = postItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Item item = getItem(getAdapterPosition());
            this.mItemListener.onPostClick(item.getAnswerId());

            notifyDataSetChanged();
        }
    }

    public AnswersAdapter(Context context, List<Item> posts, PostItemListener itemListener) {
        mItems = posts;
        mContext = context;
        mItemListener = itemListener;
    }

    @Override
    public AnswersAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View postView = inflater.inflate(R.layout.item_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(postView, this.mItemListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final AnswersAdapter.ViewHolder holder, int position) {

        final Item item = mItems.get(position);
       /* Picasso.with(mContext)
                .load(item.getOwner().getProfileImage())
                .fit()
                .centerCrop()
                .into(holder.urlAvatar);*/
        Picasso.with(mContext)
                .load(item.getOwner().getProfileImage())
                .networkPolicy(NetworkPolicy.OFFLINE)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .fit()
                .centerCrop()
                .into(holder.urlAvatar, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        Picasso.with(mContext)
                                .load(item.getOwner().getProfileImage())
                                .fit()
                                .centerCrop()
                                .memoryPolicy(MemoryPolicy.NO_CACHE)
                                .error(android.R.drawable.dark_header)
                                .into(holder.urlAvatar, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        //
                                    }

                                    @Override
                                    public void onError() {
                                        Toast.makeText(mContext, "Could not fetch image", Toast.LENGTH_LONG).show();
                                    }
                                });
                    }
                });
        holder.titleName.setText(item.getOwner().getDisplayName());
        holder.tvId.setText(item.getOwner().getUserId().toString());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void updateAnswers(List<Item> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    private Item getItem(int adapterPosition) {
        return mItems.get(adapterPosition);
    }

    public interface PostItemListener {
        void onPostClick(long id);
    }
}
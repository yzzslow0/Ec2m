//package com.example.ec.adapter;
//
//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.ec.R;
//
///**
// * Created by yzz on 2017/2/13.
// */
//
//public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
//    @Override
//    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
////    private List<ChannelListResult.DataBean.CategoriesBean.CategoryListBean> mList;
////    private Context mContext;
////    private LayoutInflater mInflater;
////
////    public RecyclerAdapter(Context context, List<ChannelListResult.DataBean.CategoriesBean.CategoryListBean> list) {
////        this.mContext = context;
////        this.mList = list;
////        this.mInflater = LayoutInflater.from(mContext);
////    }
////
////    /**
////     * 创建条目ViewHolder
////     *
////     * @param parent   RecyclerView
////     * @param viewType view的类型可以用来显示多列表布局等等
////     * @return
////     */
////    @Override
////    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
////        // 创建条目
////        View itemView = mInflater.inflate(R.layout.channel_list_item, parent, false);
////        // 创建ViewHolder
////        ViewHolder viewHolder = new ViewHolder(itemView);
////        return viewHolder;
////    }
////
////    /**
////     * 绑定ViewHolder设置数据
////     *
////     * @param holder
////     * @param position 当前位置
////     */
////    @Override
////    public void onBindViewHolder(ViewHolder holder, int position) {
////        // 设置绑定数据
////        ChannelListResult.DataBean.CategoriesBean.CategoryListBean item = mList.get(position);
////        holder.nameTv.setText(item.getName());
////        holder.channelTopicTv.setText(item.getIntro());
////        String str = item.getSubscribe_count() + " 订阅 | " +
////                "总帖数 <font color='#FF678D'>" + item.getTotal_updates() + "</font>";
////        holder.channelUpdateInfo.setText(Html.fromHtml(str));
////        // 是否是最新
////        if (item.isIs_recommend()) {
////            holder.recommendLabel.setVisibility(View.VISIBLE);
////        } else {
////            holder.recommendLabel.setVisibility(View.GONE);
////        }
////        // 加载图片
////        Glide.with(mContext).load(item.getIcon_url()).centerCrop().into(holder.channelIconIv);
////    }
////
////    /**
////     * 总共有多少条数据
////     */
////    @Override
////    public int getItemCount() {
////        return mList.size();
////    }
////
////    /**
////     * RecyclerView的Adapter需要一个ViewHolder必须要extends RecyclerView.ViewHolder
////     */
////    public static class ViewHolder extends RecyclerView.ViewHolder {
////        public TextView nameTv;
////        public TextView channelTopicTv;
////        public TextView channelUpdateInfo;
////        public View recommendLabel;
////        public ImageView channelIconIv;
////
////        public ViewHolder(View itemView) {
////            super(itemView);
////            // 在创建的时候利用传递过来的View去findViewById
////            nameTv = (TextView) itemView.findViewById(R.id.channel_text);
////            channelTopicTv = (TextView) itemView.findViewById(R.id.channel_topic);
////            channelUpdateInfo = (TextView) itemView.findViewById(R.id.channel_update_info);
////            recommendLabel = itemView.findViewById(R.id.recommend_label);
////            channelIconIv = (ImageView) itemView.findViewById(R.id.channel_icon);
////        }
////    }
//}
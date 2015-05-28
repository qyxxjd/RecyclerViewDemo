package demo.hzdracom.com.androidldemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Random;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends AppCompatActivity {
    private static final int GRID_SPAN_COUNT       = 4;
    private static final int STAGGERED_SPAN_COUNT  = 2;

    public static final int TYPE_VERTICAL          = 0;
    public static final int TYPE_HORIZONTAL        = 1;
    public static final int TYPE_GRID              = 2;
    public static final int TYPE_STAGGERED_V       = 3;
    public static final int TYPE_STAGGERED_H       = 4;


    private int currType;
    @InjectView(R.id.main_recyclerview)
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ButterKnife.inject(this);

        recyclerView.setLayoutManager(getLayoutManager(TYPE_VERTICAL));
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
//        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(new Adapter(this));
    }


    private RecyclerView.LayoutManager getLayoutManager(int type){
        LinearLayoutManager manager = new LinearLayoutManager(this);
        if(type == TYPE_GRID){
            return new GridLayoutManager(this,GRID_SPAN_COUNT);
        }else if(type == TYPE_HORIZONTAL){
            manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        }else if(type == TYPE_VERTICAL){
            manager.setOrientation(LinearLayoutManager.VERTICAL);
        }else if(type == TYPE_STAGGERED_V){
            return new StaggeredGridLayoutManager(STAGGERED_SPAN_COUNT,StaggeredGridLayoutManager.VERTICAL);
        }else if(type == TYPE_STAGGERED_H){
            return new StaggeredGridLayoutManager(STAGGERED_SPAN_COUNT,StaggeredGridLayoutManager.HORIZONTAL);
        }
        return manager;
    }

    private static final SparseArray<Double> sPositionHeightRatios = new SparseArray<Double>();
    public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{
        private LayoutInflater mInflater;
        private final Random mRandom;
        public Adapter(Context context)
        {
            mInflater = LayoutInflater.from(context);
            mRandom = new Random();
        }

        public class ViewHolder extends RecyclerView.ViewHolder
        {
            public ViewHolder(View view)
            {
                super(view);
                ButterKnife.inject(this, view);
            }

            @InjectView(R.id.main_item_tv)
            TextView tv;
            @InjectView(R.id.main_item_iv)
            DynamicHeightImageView iv;
        }

        @Override
        public int getItemCount()
        {
            return 50;
        }

        /**
         * 创建ViewHolder
         */
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
        {
            View view = mInflater.inflate(R.layout.main_item,viewGroup, false);
            ViewHolder viewHolder = new ViewHolder(view);
            return viewHolder;
        }

        /**
         * 设置值
         */
        @Override
        public void onBindViewHolder(final ViewHolder holder, final int i)
        {
            if(currType == TYPE_GRID){
                holder.tv.setTextSize(px2dp(30f));
            }else{
                holder.tv.setTextSize(px2dp(60f));
            }
            if(currType == TYPE_STAGGERED_H || currType == TYPE_STAGGERED_V){
                holder.iv.setVisibility(View.VISIBLE);

                double positionHeight = getPositionRatio(holder.getAdapterPosition());
                holder.iv.setHeightRatio(positionHeight);
            }else{
                holder.iv.setVisibility(View.GONE);
            }
        }

        private double getPositionRatio(final int position) {
            double ratio = sPositionHeightRatios.get(position, 0.0);
            if (ratio == 0) {
                ratio = getRandomHeightRatio();
                sPositionHeightRatios.append(position, ratio);
            }
            return ratio;
        }
        private double getRandomHeightRatio() {
            return (mRandom.nextDouble() / 2.0) + 1.0; // height will be 1.0 - 1.5 the width
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.reset(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_vertical:
                currType = TYPE_VERTICAL;
                recyclerView.setLayoutManager(getLayoutManager(currType));
                break;
            case R.id.action_horizontal:
                currType = TYPE_HORIZONTAL;
                recyclerView.setLayoutManager(getLayoutManager(currType));
                break;
            case R.id.action_grid:
                currType = TYPE_GRID;
                recyclerView.setLayoutManager(getLayoutManager(currType));
                break;
            case R.id.action_staggered_v:
                currType = TYPE_STAGGERED_V;
                recyclerView.setLayoutManager(getLayoutManager(currType));
                break;
            case R.id.action_staggered_h:
                currType = TYPE_STAGGERED_H;
                recyclerView.setLayoutManager(getLayoutManager(currType));
                break;
            case R.id.action_multiple:
                startActivity(new Intent(this,MultipleItemActivity.class));
                break;
        }
        return true;
    }
    private float px2dp(float pxVal)
    {
        final float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        return (pxVal / scale + 0.5f);
    }
}

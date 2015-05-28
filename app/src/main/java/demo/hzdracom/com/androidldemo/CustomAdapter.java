/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package demo.hzdracom.com.androidldemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private Context context;
    private List<Integer> data;
    public CustomAdapter(Context context)
    {
        this.context = context;
        mInflater = LayoutInflater.from(context);
        data = new ArrayList<>();
        for(int i=0 ; i<100 ; i++){
            data.add((int)(Math.random() * 4));
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public ViewHolder(View view)
        {
            super(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Element " + getPosition() + " clicked.",Toast.LENGTH_SHORT).show();
                }
            });
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(context, "Element " + getPosition() + " long clicked.",Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position);
    }

    @Override
    public int getItemCount()
    {
        return 100;
    }

    /**
     * 创建ViewHolder
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i)
    {
        View v = null;
        switch (i){
            case 0:
                v = mInflater.inflate(R.layout.main_item,viewGroup, false);
                break;
            case 1:
                v = mInflater.inflate(R.layout.imageview,viewGroup, false);
                break;
//            case 2:
//                v = mInflater.inflate(R.layout.progress,viewGroup, false);
//                break;
            case 2:
                v = mInflater.inflate(R.layout.floating_button,viewGroup, false);
                break;
            case 3:
                v = mInflater.inflate(R.layout.slider,viewGroup, false);
                break;
        }
        return new ViewHolder(v);
    }

    /**
     * 设置值
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int i)
    {
        //TODO set view values
    }

//    private View getView(int number){
//        View v = null;
//        switch (number){
//            case 0:
//                v = mInflater.inflate(R.layout.main_item,null);
//                break;
//            case 1:
//                v = mInflater.inflate(R.layout.imageview,null);
//                break;
//            case 2:
//                v = mInflater.inflate(R.layout.progress,null);
//                final ProgressView pv_linear_buffer = (ProgressView)v.findViewById(R.id.progress_pv_linear_buffer);
//                pv_linear_buffer.setProgress(0.3f);
//                pv_linear_buffer.setSecondaryProgress(0.5f);
//                pv_linear_buffer.start();
//                pv_linear_buffer.setOnClickListener(listener);
//                final Timer timer = new Timer();
//                timer.schedule(new TimerTask() {
//                    @Override
//                    public void run() {
//                        ((Activity)context).runOnUiThread(new Runnable() {
//                            @Override
//                            public void run() {
//                                pv_linear_buffer.setProgress(pv_linear_buffer.getProgress()+0.01f);
//                                pv_linear_buffer.setSecondaryProgress(pv_linear_buffer.getSecondaryProgress() + 0.01f);
//                                if(pv_linear_buffer.getProgress() >= 1f){
//                                    pv_linear_buffer.stop();
//                                    timer.cancel();
//                                }
//                            }
//                        });
//                    }
//                }, 500, 500);
//                break;
//            case 3:
//                v = mInflater.inflate(R.layout.floating_button,null);
//                FloatingActionButton bt_float_wave = (FloatingActionButton)v.findViewById(R.id.button_bt_float_wave);
//                FloatingActionButton bt_float_wave_color = (FloatingActionButton)v.findViewById(R.id.button_bt_float_wave_color);
//                bt_float_wave.setOnClickListener(listener);
//                bt_float_wave_color.setOnClickListener(listener);
//                break;
//            case 4:
//                v = mInflater.inflate(R.layout.slider,null);
//                Slider sl_discrete = (Slider)v.findViewById(R.id.slider_sl_discrete);
//                final TextView tv_discrete = (TextView)v.findViewById(R.id.slider_tv_discrete);
//                tv_discrete.setText(String.format("pos=%.1f value=%d", sl_discrete.getPosition(), sl_discrete.getValue()));
//                sl_discrete.setOnPositionChangeListener(new Slider.OnPositionChangeListener() {
//                    @Override
//                    public void onPositionChanged(Slider view, boolean fromUser, float oldPos, float newPos, int oldValue, int newValue) {
//                        tv_discrete.setText(String.format("pos=%.1f value=%d", newPos, newValue));
//                    }
//                });
//                break;
//        }
//        return v;
//    }
//    View.OnClickListener listener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            if(v instanceof  FloatingActionButton){
//                FloatingActionButton bt = (FloatingActionButton)v;
//                bt.setLineMorphingState((bt.getLineMorphingState() + 1) % 2, true);
//            } else if (v instanceof  ProgressView){
//                ProgressView progressView = (ProgressView)v;
//                if (progressView.getProgress()<1f){
//                    progressView.setProgress(progressView.getProgress()+0.1f);
//                    progressView.setSecondaryProgress(progressView.getSecondaryProgress() + 0.1f);
//                }
//            }
//        }
//    };
}

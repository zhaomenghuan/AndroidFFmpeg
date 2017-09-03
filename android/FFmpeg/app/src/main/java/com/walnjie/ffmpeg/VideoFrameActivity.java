package com.walnjie.ffmpeg;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wlanjie.ffmpeg.FFmpeg;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wlanjie on 2017/8/31.
 */
public class VideoFrameActivity extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_video_frame);
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    VideoFrameAdapter adapter = new VideoFrameAdapter();
    recyclerView.setAdapter(adapter);
    List<Bitmap> videoFrames = FFmpeg.getInstance().getVideoFrame("/sdcard/DCIM/Camera/20170726_173615_5692.mp4");
    adapter.setBitmaps(videoFrames);
  }

  class VideoFrameAdapter extends RecyclerView.Adapter<ViewHolder> {

    private List<Bitmap> mBitmaps = new ArrayList<>();

    void setBitmaps(List<Bitmap> bitmaps) {
      if (bitmaps == null || bitmaps.isEmpty()) {
        return;
      }
      mBitmaps.clear();
      mBitmaps.addAll(bitmaps);
      notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
      LayoutInflater inflater = LayoutInflater.from(parent.getContext());
      View view = inflater.inflate(R.layout.item_video_frame, parent, false);
      return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
      Bitmap bitmap = mBitmaps.get(position);
      holder.imageView.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
      return mBitmaps.size();
    }
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    ViewHolder(View itemView) {
      super(itemView);
      imageView = (ImageView) itemView;
    }
  }
}
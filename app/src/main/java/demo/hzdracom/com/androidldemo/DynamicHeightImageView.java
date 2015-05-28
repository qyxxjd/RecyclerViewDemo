package demo.hzdracom.com.androidldemo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;


public class DynamicHeightImageView extends ImageView {

	public static final int MIN_WH = 100;
    private double mHeightRatio;

    public DynamicHeightImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DynamicHeightImageView(Context context) {
        super(context);
    }

    public void setHeightRatio(double ratio) {
        if (ratio != mHeightRatio) {
            mHeightRatio = ratio;
            requestLayout();
        }
    }
    
    private int ivWidth,ivHeight;
    public void setWH(int width,int height) {
    	ivWidth = width;
    	ivHeight = height;
    	requestLayout();
    }

    public double getHeightRatio() {
        return mHeightRatio;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (ivWidth>MIN_WH && ivHeight>MIN_WH) {
            int width = MeasureSpec.getSize(widthMeasureSpec);
			final double v = width/Double.valueOf(ivWidth);
//			System.out.println("当前缩放比例："+v);
			final int height = (int)(ivHeight * v);
//			System.err.println("当前缩放后的高度："+height);
            setMeasuredDimension(width, height);
        }
        else if (mHeightRatio > 0.0) {
        	int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = (int) (width * mHeightRatio);
        	setMeasuredDimension(width, height);
        }
        else {
        	super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}

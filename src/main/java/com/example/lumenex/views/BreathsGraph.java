package com.example.lumenex.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class BreathsGraph extends View {

    // region Consts

    public static final int  STROKE = 6;

    // endregion

    // region Members

    /**
     * Values shown on graph.
     */
    private Double[] _flow;

    /**
     * The path of the graph.
     */
    final private Path _flowPath = new Path();

    /**
     * Represing the graph view.
     */
    final private Paint _paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    /**
     * The view height and width.
     */
    private int height, width;

    /**
     * The given values max, min and their diffence.
     */
    private Double max, difference;

    // endregion

    // region C'tor


    public BreathsGraph(Context context) {
        super(context);
    }

    public BreathsGraph(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public BreathsGraph(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BreathsGraph(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    // endregion

    // region Public Methods

    /**
     * Method uses the received flow, to update the stored flow, and the
     * min and max values.
     */
    public void updateFlow(Double[] flow) {
        _flow = flow;

        max = 0.0;
        double min = 0.0;

        // Iterate over the rest of the values, to calculate the min and max
        // values.
        for (Double temp : flow) {
            if (temp > max) {
                max = temp;
            }
            else if (temp < min) {
                min = temp;
            }
        }

        difference = max - min;
    }

    // endregion

    // region View

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // Store the height and width of the view.
        height = MeasureSpec.getSize(heightMeasureSpec);
        width = MeasureSpec.getSize(widthMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        _paint.setColor(Color.RED);
        _paint.setStyle(Paint.Style.STROKE);

        // Calculate how to multiply the x values: if there are n values, they should split the
        // view's width evenly.
        int xMargin = width / _flow.length;
        _paint.setStrokeWidth(STROKE);

        // TODO handle startpoint != height!!
        _flowPath.moveTo(0, height);

        for (int breathIndex = 0; breathIndex < _flow.length; breathIndex++) {
            double yVal = (height * ((max - _flow[breathIndex] ) / difference));
            _flowPath.lineTo((breathIndex + 1) * xMargin, (float) yVal);
        }

        canvas.drawPath(_flowPath, _paint);
    }

    // endregion

}

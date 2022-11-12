package com.example.myapplication;

import static android.graphics.Paint.Cap.ROUND;
import static android.graphics.Paint.Cap.SQUARE;
import static android.graphics.Paint.Style.STROKE;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class TimeLineIndicatorItemDecoration extends RecyclerView.ItemDecoration {
    private static final float DP = Resources.getSystem().getDisplayMetrics().density;

    private final float lineStroke = 1.5f * DP;
    private final float circleRadius = 12 * DP;
    private final float circleStroke = 2f * DP;
    private final int lineColor;
    private final int circleColor;
    private final Paint lineCircleArrowPaint;
    private Path arrowPath;
    private boolean showArrow;

    public TimeLineIndicatorItemDecoration() {
        //set default line and circle color
        this(Color.LTGRAY, Color.LTGRAY, false);
    }

    public TimeLineIndicatorItemDecoration(int lineColor, int circleColor, boolean showArrow) {
        this.lineColor = lineColor;
        this.circleColor = circleColor;
        this.lineCircleArrowPaint = new Paint();
        this.showArrow = showArrow;
        if (this.showArrow) {
            arrowPath = new Path();
        }
    }

    public void showArrow(boolean showArrow) {
        this.showArrow = showArrow;
        if (!this.showArrow) {
            arrowPath = null;
        }
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
        //todo draw arrow
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int itemCount = parent.getAdapter().getItemCount();
        final int left = (int) (parent.getPaddingLeft()+(15*DP));
        final int right = parent.getWidth() - parent.getPaddingRight();
        //to draw below each child we will need the child's bottom. and draw for every child
        for (int i = 0; i < itemCount; i++) {
            final View child = parent.getChildAt(i);
            if (child == null) { //null check
                continue;
            }

            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getTop() + params.topMargin;
            final int bottom = child.getBottom();

            //draw horizontal line
//            drawHorizontalLine(c, left + circleRadius, bottom, right, bottom);
            drawCircle(c, left + child.getPaddingLeft(), bottom, circleRadius);

            //draw vertical line
            if (i == 0) {
                drawVerticalLine(c, left, top, left, bottom - circleRadius);
            } else {
                drawVerticalLine(c, left, top + circleRadius, left, bottom - circleRadius);
            }

            //draw circle
        }
        super.onDraw(c, parent, state);
    }

    private void drawHorizontalLine(Canvas c, float x1, float y1, float x2, int y2) {

        //make paint configuration here
        configPaint(lineStroke, STROKE, ROUND, lineColor);

        //finally draw line
        c.drawLine(x1, y1, x2, y2, lineCircleArrowPaint);
    }

    private void drawCircle(Canvas c, float left, float top, float circleRadius) {

        //make paint configuration here
        configPaint(circleStroke, STROKE, ROUND, circleColor);

        //finally draw circle
        c.drawCircle(left, top, circleRadius, lineCircleArrowPaint);

    }

    private void drawVerticalLine(Canvas c, float x1, float y1, float x2, float y2) {
        //make paint configuration here
        configPaint(lineStroke, STROKE, ROUND, lineColor);

        //finally draw line
        c.drawLine(x1, y1, x2, y2, lineCircleArrowPaint);

    }

    private void drawArrow(Canvas c, float centerX, float centerY, float topX, float topY, float bottomX, float bottomY) {
        if (!showArrow) {
            return;
        }
        if (arrowPath == null) {
            return;
        }

        //make paint configuration here
        //hardcoded dp and circle color for now
        configPaint(3 * DP, STROKE, SQUARE, circleColor);

        arrowPath.reset();
        arrowPath.moveTo(topX, topY);
        arrowPath.lineTo(centerX, centerY);
        arrowPath.lineTo(bottomX, bottomY);
        //close the loop if you want to change the style of arrow
        c.drawPath(arrowPath, lineCircleArrowPaint);

    }

    /**
     * config paint
     *
     * @param strokeWidth stroke width for paint
     * @param style       type of for paint
     * @param cap         for paint
     * @param color       for the paint
     */
    private void configPaint(float strokeWidth, Paint.Style style, Paint.Cap cap, int color) {
        lineCircleArrowPaint.setStrokeWidth(strokeWidth);
        lineCircleArrowPaint.setStyle(style);
        lineCircleArrowPaint.setStrokeCap(cap);
        lineCircleArrowPaint.setColor(color);
    }
}
package com.cemsofwave.gunsimulator.ui.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class GradientTextView extends AppCompatTextView {

    private int start_gradient;
    private int end_gradient;
    private int strokeColor = Color.WHITE;

    public GradientTextView(Context context) {
        super(context, null, -1);
    }

    public GradientTextView(Context context, AttributeSet attrs) {
        super(context, attrs, -1);
    }

    public GradientTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setStrokeColor(int color) {
        this.strokeColor = color;
        invalidate();
    }

    public void setGradientText(int start_gradient, int end_gradient) {
        this.start_gradient = start_gradient;
        this.end_gradient = end_gradient;
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {

        // draw the shadow
        //drawShadow();
        //super.onDraw(canvas);

        // draw the stroke
        drawStroke();
        super.onDraw(canvas);

        // draw the gradient filled text
        drawGradient();
        //getPaint().setStrokeWidth(32);
        super.onDraw(canvas);
    }

    private void drawShadow() {
        getPaint().setShadowLayer(10, 6, 6, 0xbf000000);
        getPaint().setShader(null);
    }

    private void drawStroke() {
        getPaint().clearShadowLayer();
        getPaint().setStyle(Paint.Style.STROKE);
        getPaint().setStrokeWidth(6);
        getPaint().setShader(new LinearGradient(0, 0, 0, getHeight(), strokeColor, strokeColor, Shader.TileMode.CLAMP));
    }

    private void drawGradient() {
        getPaint().setStyle(Paint.Style.FILL);
        getPaint().setShader(new LinearGradient(0, 0, 0, getHeight(), start_gradient, end_gradient, Shader.TileMode.CLAMP));
    }
}


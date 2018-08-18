package com.github.mohaka.ratingstar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import static com.github.mohaka.ratingstar.Utilities.centerPoint;
import static com.github.mohaka.ratingstar.Utilities.hasJellyBeanMR1;
import static com.github.mohaka.ratingstar.Utilities.isPointInside;

/**
 * Created by MoHaKa on 2/7/17.
 */
public class RatingStar extends FrameLayout {
    private final static boolean SHOW_GUIDELINES = false;

    private final static String KEY_SUPER_STATE = "__key_super_state";
    private final static String KEY_RATING = "__key_rating";
    private final static String KEY_SHOW_NUMBERS = "__key_show_numbers";
    private final static String KEY_STAR_COLOR = "__key_star_color";
    private final static String KEY_HIGHLIGHT_COLOR = "__key_highlight_color";

    private AppCompatImageView stars[] = new AppCompatImageView[5];
    private AppCompatImageView numbers[] = new AppCompatImageView[5];

    private Drawable[][] drawables = new Drawable[5][2];

    private Point[][] boundaries = new Point[5][4];

    private int rating;
    private boolean showNumbers;
    private int starColor;
    private int highlightColor;

    private RateValueListener listener;

    public RatingStar(Context context) {
        super(context);
        init(context);
        fetchAttributes(context, null, 0);
    }

    public RatingStar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
        fetchAttributes(context, attrs, 0);
    }

    public RatingStar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
        fetchAttributes(context, attrs, defStyleAttr);
    }

    public void fetchAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.RatingStar, defStyleAttr, 0);

        try {
            setRating(a.getInt(R.styleable.RatingStar_rating, 0));
            setShowNumbers(a.getBoolean(R.styleable.RatingStar_showNumbers, true));

            int starColor = Utilities.getThemeColor(context, R.attr.colorPrimary);
            starColor = a.getColor(R.styleable.RatingStar_starColor, starColor);

            int highlightColor = Utilities.getThemeColor(context, R.attr.colorAccent);
            highlightColor = a.getColor(R.styleable.RatingStar_highlightColor, highlightColor);

            setStarColor(starColor);
            setHighlightColor(highlightColor);

        } finally {
            a.recycle();
        }
    }

    @Override
    public Parcelable onSaveInstanceState() {
        Parcelable superState = super.onSaveInstanceState();

        Bundle bundle = new Bundle();
        bundle.putParcelable(KEY_SUPER_STATE, superState);
        bundle.putInt(KEY_RATING, rating);
        bundle.putBoolean(KEY_SHOW_NUMBERS, showNumbers);
        bundle.putInt(KEY_STAR_COLOR, starColor);
        bundle.putInt(KEY_HIGHLIGHT_COLOR, highlightColor);

        return bundle;
    }

    @Override
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            setRating(bundle.getInt(KEY_RATING));
            setShowNumbers(bundle.getBoolean(KEY_SHOW_NUMBERS));
            setStarColor(bundle.getInt(KEY_STAR_COLOR));
            setHighlightColor(bundle.getInt(KEY_HIGHLIGHT_COLOR));

            state = bundle.getParcelable(KEY_SUPER_STATE);
        }

        super.onRestoreInstanceState(state);
    }

    private void init(Context context) {
        stars[0] = new AppCompatImageView(context);
        stars[1] = new AppCompatImageView(context);
        stars[2] = new AppCompatImageView(context);
        stars[3] = new AppCompatImageView(context);
        stars[4] = new AppCompatImageView(context);

        Utilities utilities = Utilities.with(getContext());

        Drawable normal_0 = utilities.getRD(R.drawable.ic_petal_1);
        Drawable selected_0 = utilities.getRD(R.drawable.ic_petal_1);

        StateListDrawable drawable_0 = new StateListDrawable();
        drawable_0.addState(new int[]{android.R.attr.state_selected}, selected_0);
        drawable_0.addState(new int[]{}, normal_0);

        stars[0].setImageDrawable(drawable_0);
        drawables[0][0] = normal_0;
        drawables[0][1] = selected_0;


        Drawable normal_1 = utilities.getRD(R.drawable.ic_petal_2);
        Drawable selected_1 = utilities.getRD(R.drawable.ic_petal_2);

        StateListDrawable drawable_1 = new StateListDrawable();
        drawable_1.addState(new int[]{android.R.attr.state_selected}, selected_1);
        drawable_1.addState(new int[]{}, normal_1);

        stars[1].setImageDrawable(drawable_1);
        drawables[1][0] = normal_1;
        drawables[1][1] = selected_1;


        Drawable normal_2 = utilities.getRD(R.drawable.ic_petal_3);
        Drawable selected_2 = utilities.getRD(R.drawable.ic_petal_3);

        StateListDrawable drawable_2 = new StateListDrawable();
        drawable_2.addState(new int[]{android.R.attr.state_selected}, selected_2);
        drawable_2.addState(new int[]{}, normal_2);

        stars[2].setImageDrawable(drawable_2);
        drawables[2][0] = normal_2;
        drawables[2][1] = selected_2;


        Drawable normal_3 = utilities.getRD(R.drawable.ic_petal_4);
        Drawable selected_3 = utilities.getRD(R.drawable.ic_petal_4);

        StateListDrawable drawable_3 = new StateListDrawable();
        drawable_3.addState(new int[]{android.R.attr.state_selected}, selected_3);
        drawable_3.addState(new int[]{}, normal_3);

        stars[3].setImageDrawable(drawable_3);
        drawables[3][0] = normal_3;
        drawables[3][1] = selected_3;


        Drawable normal_4 = utilities.getRD(R.drawable.ic_petal_5);
        Drawable selected_4 = utilities.getRD(R.drawable.ic_petal_5);

        StateListDrawable drawable_4 = new StateListDrawable();
        drawable_4.addState(new int[]{android.R.attr.state_selected}, selected_4);
        drawable_4.addState(new int[]{}, normal_4);

        stars[4].setImageDrawable(drawable_4);
        drawables[4][0] = normal_4;
        drawables[4][1] = selected_4;


        numbers[0] = new AppCompatImageView(context);
        numbers[0].setImageResource(R.drawable.ic_num_1);

        numbers[1] = new AppCompatImageView(context);
        numbers[1].setImageResource(R.drawable.ic_num_2);

        numbers[2] = new AppCompatImageView(context);
        numbers[2].setImageResource(R.drawable.ic_num_3);

        numbers[3] = new AppCompatImageView(context);
        numbers[3].setImageResource(R.drawable.ic_num_4);

        numbers[4] = new AppCompatImageView(context);
        numbers[4].setImageResource(R.drawable.ic_num_5);

        for (View star : stars)
            addView(star, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        for (View star : numbers)
            addView(star, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        if (SHOW_GUIDELINES)
            initDebug();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                for (int i = 0; i < boundaries.length; i++) {
                    Point touchPoint = new Point((int) event.getX(), (int) event.getY());
                    Point[] points = boundaries[i];

                    if (isPointInside(touchPoint, points)) {
                        setRating(i + 1);
                        return true;
                    }
                }

                setRating(0);
                return true;
            case MotionEvent.ACTION_UP:
                if (listener != null)
                    listener.onValueChanged(getRating());
                return true;
            default:
                return super.onTouchEvent(event);
        }
    }

    @Override
    public int getPaddingEnd() {
        return hasJellyBeanMR1() ? super.getPaddingEnd() : super.getPaddingRight();
    }

    @Override
    public int getPaddingStart() {
        return hasJellyBeanMR1() ? super.getPaddingStart() : super.getPaddingLeft();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        super.onSizeChanged(w, h, oldW, oldH);

        int width = w - getPaddingEnd() - getPaddingStart();
        int height = h - getPaddingTop() - getPaddingBottom();

        boolean isWide = width > height;
        int margin = Math.abs((width - height) / 2);

        int left = getPaddingStart() + (isWide ? margin : 0);
        int top = getPaddingTop() + (isWide ? 0 : margin);
        int length = isWide ? height : width;

        Point center = new Point(length / 2 + left, (length * 13 / 24) + top);

        Point p1 = new Point(center.x, (length / 100) + top);
        Point p2 = new Point(left + length, (length * 38 / 100) + top);
        Point p3 = new Point((length * 81 / 100) + left, (length * 97 / 100) + top);
        Point p4 = new Point((length * 19 / 100) + left, (length * 97 / 100) + top);
        Point p5 = new Point(left, (length * 38 / 100) + top);

        Point m1 = centerPoint(p1, p2);
        Point m2 = centerPoint(p2, p3);
        Point m3 = centerPoint(p3, p4);
        Point m4 = centerPoint(p4, p5);
        Point m5 = centerPoint(p5, p1);

        boundaries[4][0] = p1;
        boundaries[4][1] = m1;
        boundaries[4][2] = center;
        boundaries[4][3] = m5;

        boundaries[0][0] = p2;
        boundaries[0][1] = m2;
        boundaries[0][2] = center;
        boundaries[0][3] = m1;

        boundaries[1][0] = p3;
        boundaries[1][1] = m3;
        boundaries[1][2] = center;
        boundaries[1][3] = m2;

        boundaries[2][0] = p4;
        boundaries[2][1] = m4;
        boundaries[2][2] = center;
        boundaries[2][3] = m3;

        boundaries[3][0] = p5;
        boundaries[3][1] = m5;
        boundaries[3][2] = center;
        boundaries[3][3] = m4;

        initDebugPaths();
    }

    //region Debug Mode
    private Path debugPaths[] = new Path[5];
    private Paint debugPaint = new Paint();

    private void initDebug() {
        setWillNotDraw(false);

        debugPaint.setColor(Color.RED);
        debugPaint.setStrokeWidth(3);
        debugPaint.setStyle(Paint.Style.STROKE);
    }

    private void initDebugPaths() {
        for (int x = 0; x < boundaries.length; x++) {
            Point[] points = boundaries[x];

            Path path = new Path();
            for (int i = 0; i < points.length; i++) {
                Point point = points[i];
                if (i == 0)
                    path.moveTo(point.x, point.y);
                else
                    path.lineTo(point.x, point.y);

                if (i == points.length - 1)
                    path.close();
            }
            this.debugPaths[x] = path;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (Path path : debugPaths) {
            canvas.drawPath(path, debugPaint);
        }
    }
    //endregion

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;

        for (int i = 0; i < 5; i++)
            stars[i].setSelected(i < rating);
    }

    public boolean isShowNumbers() {
        return showNumbers;
    }

    public void setShowNumbers(boolean value) {
        this.showNumbers = value;

        for (AppCompatImageView number : numbers)
            number.setVisibility(value ? VISIBLE : GONE);
    }

    public void setListener(RateValueListener listener) {
        this.listener = listener;
    }

    public int getStarColor() {
        return starColor;
    }

    public void setStarColor(int starColor) {
        this.starColor = starColor;

        for (Drawable[] drawable : drawables)
            drawable[0].setColorFilter(starColor, PorterDuff.Mode.SRC_IN);
    }

    public int getHighlightColor() {
        return highlightColor;
    }

    public void setHighlightColor(int highlightColor) {
        this.highlightColor = highlightColor;

        for (Drawable[] drawable : drawables)
            drawable[1].setColorFilter(highlightColor, PorterDuff.Mode.SRC_IN);
    }

    public interface RateValueListener {
        void onValueChanged(int value);
    }
}

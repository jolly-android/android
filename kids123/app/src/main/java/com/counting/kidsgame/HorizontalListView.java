package com.counting.kidsgame;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EdgeEffect;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Scroller;


import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.ItemTouchHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class HorizontalListView extends AdapterView<ListAdapter> {
    private static final String BUNDLE_ID_CURRENT_X = "BUNDLE_ID_CURRENT_X";
    private static final String BUNDLE_ID_PARENT_STATE = "BUNDLE_ID_PARENT_STATE";
    private static final float FLING_DEFAULT_ABSORB_VELOCITY = 30.0f;
    private static final float FLING_FRICTION = 0.009f;
    private static final int INSERT_AT_END_OF_LIST = -1;
    private static final int INSERT_AT_START_OF_LIST = 0;


    protected Scroller f4272a = new Scroller(getContext());


    protected ListAdapter f4273b;


    protected int f4274c;


    protected int f4275d;
    private DataSetObserver mAdapterDataObserver;

    public boolean mBlockTouchAction;
    private OnScrollStateChangedListener.ScrollState mCurrentScrollState;
    private int mCurrentlySelectedAdapterIndex;

    public boolean mDataChanged;
    private Runnable mDelayedLayout;
    private int mDisplayOffset;
    private Drawable mDivider;
    private int mDividerWidth;
    private EdgeEffect mEdgeGlowLeft;
    private EdgeEffect mEdgeGlowRight;

    public GestureDetector mGestureDetector;
    private final GestureListener mGestureListener;

    public boolean mHasNotifiedRunningLowOnData;
    private int mHeightMeasureSpec;
    private boolean mIsParentVerticiallyScrollableViewDisallowingInterceptTouchEvent;

    public int mLeftViewAdapterIndex;
    private int mMaxX;

    public OnClickListener mOnClickListener;
    private OnScrollStateChangedListener mOnScrollStateChangedListener;
    private Rect mRect;
    private List<Queue<View>> mRemovedViewsCache;
    private Integer mRestoreX;
    private int mRightViewAdapterIndex;
    private RunningOutOfDataListener mRunningOutOfDataListener;
    private int mRunningOutOfDataThreshold;
    private View mViewBeingTouched;

    private class GestureListener extends GestureDetector.SimpleOnGestureListener {
        private GestureListener() {
        }

        public boolean onDown(MotionEvent motionEvent) {
            return HorizontalListView.this.m(motionEvent);
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return HorizontalListView.this.n(motionEvent, motionEvent2, f, f2);
        }

        public void onLongPress(MotionEvent motionEvent) {
            HorizontalListView.this.unpressTouchedChild();
            int l = HorizontalListView.this.getChildIndex((int) motionEvent.getX(), (int) motionEvent.getY());
            if (l >= 0 && !HorizontalListView.this.mBlockTouchAction) {
                View childAt = HorizontalListView.this.getChildAt(l);
                OnItemLongClickListener onItemLongClickListener = HorizontalListView.this.getOnItemLongClickListener();
                if (onItemLongClickListener != null) {
                    int c2 = HorizontalListView.this.mLeftViewAdapterIndex + l;
                    HorizontalListView horizontalListView = HorizontalListView.this;
                    if (onItemLongClickListener.onItemLongClick(horizontalListView, childAt, c2, horizontalListView.f4273b.getItemId(c2))) {
                        HorizontalListView.this.performHapticFeedback(0);
                    }
                }
            }
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            HorizontalListView.this.requestParentListViewToNotInterceptTouchEvents(Boolean.TRUE);
            HorizontalListView.this.setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_TOUCH_SCROLL);
            HorizontalListView.this.unpressTouchedChild();
            HorizontalListView horizontalListView = HorizontalListView.this;
            horizontalListView.f4275d += (int) f;
            horizontalListView.updateOverscrollAnimation(Math.round(f));
            HorizontalListView.this.requestLayout();
            return true;
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            HorizontalListView.this.unpressTouchedChild();
            OnItemClickListener onItemClickListener = HorizontalListView.this.getOnItemClickListener();
            int l = HorizontalListView.this.getChildIndex((int) motionEvent.getX(), (int) motionEvent.getY());
            if (l >= 0 && !HorizontalListView.this.mBlockTouchAction) {
                View childAt = HorizontalListView.this.getChildAt(l);
                int c2 = HorizontalListView.this.mLeftViewAdapterIndex + l;
                if (onItemClickListener != null) {
                    HorizontalListView horizontalListView = HorizontalListView.this;
                    onItemClickListener.onItemClick(horizontalListView, childAt, c2, horizontalListView.f4273b.getItemId(c2));
                    return true;
                }
            }
            if (HorizontalListView.this.mOnClickListener == null || HorizontalListView.this.mBlockTouchAction) {
                return false;
            }
            HorizontalListView.this.mOnClickListener.onClick(HorizontalListView.this);
            return false;
        }
    }

    @TargetApi(11)
    private static final class HoneycombPlus {
        private HoneycombPlus() {
        }

        public static void setFriction(Scroller scroller, float f) {
            if (scroller != null) {
                scroller.setFriction(f);
            }
        }
    }

    @TargetApi(14)
    private static final class IceCreamSandwichPlus {
        private IceCreamSandwichPlus() {
        }

        public static float getCurrVelocity(Scroller scroller) {
            return scroller.getCurrVelocity();
        }
    }

    public interface OnScrollStateChangedListener {

        public enum ScrollState {
            SCROLL_STATE_IDLE,
            SCROLL_STATE_TOUCH_SCROLL,
            SCROLL_STATE_FLING
        }

        void onScrollStateChanged(ScrollState scrollState);
    }

    public interface RunningOutOfDataListener {
        void onRunningOutOfData();
    }

    public HorizontalListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        GestureListener gestureListener = new GestureListener();
        this.mGestureListener = gestureListener;
        this.mRemovedViewsCache = new ArrayList();
        this.mDataChanged = false;
        this.mRect = new Rect();
        this.mViewBeingTouched = null;
        this.mDividerWidth = 0;
        this.mDivider = null;
        this.mRestoreX = null;
        this.mMaxX = Integer.MAX_VALUE;
        this.mRunningOutOfDataListener = null;
        this.mRunningOutOfDataThreshold = 0;
        this.mHasNotifiedRunningLowOnData = false;
        this.mOnScrollStateChangedListener = null;
        this.mCurrentScrollState = OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE;
        this.mBlockTouchAction = false;
        this.mIsParentVerticiallyScrollableViewDisallowingInterceptTouchEvent = false;
        this.mAdapterDataObserver = new DataSetObserver() {
            public void onChanged() {
                boolean unused = HorizontalListView.this.mDataChanged = true;
                boolean unused2 = HorizontalListView.this.mHasNotifiedRunningLowOnData = false;
                HorizontalListView.this.unpressTouchedChild();
                HorizontalListView.this.invalidate();
                HorizontalListView.this.requestLayout();
            }

            public void onInvalidated() {
                boolean unused = HorizontalListView.this.mHasNotifiedRunningLowOnData = false;
                HorizontalListView.this.unpressTouchedChild();
                HorizontalListView.this.reset();
                HorizontalListView.this.invalidate();
                HorizontalListView.this.requestLayout();
            }
        };
        this.mDelayedLayout = new Runnable() {
            public void run() {
                HorizontalListView.this.requestLayout();
            }
        };
        this.mEdgeGlowLeft = new EdgeEffect(context);
        this.mEdgeGlowRight = new EdgeEffect(context);
        this.mGestureDetector = new GestureDetector(context, gestureListener);
        bindGestureDetector();
        initView();
        retrieveXmlConfiguration(context, attributeSet);
        setWillNotDraw(false);
        HoneycombPlus.setFriction(this.f4272a, FLING_FRICTION);
    }

    private void addAndMeasureChild(View view, int i) {
        addViewInLayout(view, i, getLayoutParams(view), true);
        measureChild(view);
    }

    private void bindGestureDetector() {
        setOnTouchListener(new OnTouchListener() {
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return HorizontalListView.this.mGestureDetector.onTouchEvent(motionEvent);
            }
        });
    }

    private float determineFlingAbsorbVelocity() {
        return IceCreamSandwichPlus.getCurrVelocity(this.f4272a);
    }

    private void determineIfLowOnData() {
        ListAdapter listAdapter;
        if (this.mRunningOutOfDataListener != null && (listAdapter = this.f4273b) != null && listAdapter.getCount() - (this.mRightViewAdapterIndex + 1) < this.mRunningOutOfDataThreshold && !this.mHasNotifiedRunningLowOnData) {
            this.mHasNotifiedRunningLowOnData = true;
            this.mRunningOutOfDataListener.onRunningOutOfData();
        }
    }

    private boolean determineMaxX() {
        View rightmostChild;
        if (isLastItemInAdapter(this.mRightViewAdapterIndex) && (rightmostChild = getRightmostChild()) != null) {
            int i = this.mMaxX;
            int right = (this.f4274c + (rightmostChild.getRight() - getPaddingLeft())) - getRenderWidth();
            this.mMaxX = right;
            if (right < 0) {
                this.mMaxX = 0;
            }
            if (this.mMaxX != i) {
                return true;
            }
        }
        return false;
    }

    private void drawDivider(Canvas canvas, Rect rect) {
        Drawable drawable = this.mDivider;
        if (drawable != null) {
            drawable.setBounds(rect);
            this.mDivider.draw(canvas);
        }
    }

    private void drawDividers(Canvas canvas) {
        int childCount = getChildCount();
        Rect rect = this.mRect;
        rect.top = getPaddingTop();
        Rect rect2 = this.mRect;
        rect2.bottom = rect2.top + getRenderHeight();
        for (int i = 0; i < childCount; i++) {
            if (i != childCount - 1 || !isLastItemInAdapter(this.mRightViewAdapterIndex)) {
                View childAt = getChildAt(i);
                rect.left = childAt.getRight();
                rect.right = childAt.getRight() + this.mDividerWidth;
                if (rect.left < getPaddingLeft()) {
                    rect.left = getPaddingLeft();
                }
                if (rect.right > getWidth() - getPaddingRight()) {
                    rect.right = getWidth() - getPaddingRight();
                }
                drawDivider(canvas, rect);
                if (i == 0 && childAt.getLeft() > getPaddingLeft()) {
                    rect.left = getPaddingLeft();
                    rect.right = childAt.getLeft();
                    drawDivider(canvas, rect);
                }
            }
        }
    }

    private void drawEdgeGlow(Canvas canvas) {
        EdgeEffect edgeEffect = this.mEdgeGlowLeft;
        if (edgeEffect == null || edgeEffect.isFinished() || !isEdgeGlowEnabled()) {
            EdgeEffect edgeEffect2 = this.mEdgeGlowRight;
            if (edgeEffect2 != null && !edgeEffect2.isFinished() && isEdgeGlowEnabled()) {
                int save = canvas.save();
                int width = getWidth();
                canvas.rotate(90.0f, 0.0f, 0.0f);
                canvas.translate((float) getPaddingTop(), (float) (-width));
                this.mEdgeGlowRight.setSize(getRenderHeight(), getRenderWidth());
                if (this.mEdgeGlowRight.draw(canvas)) {
                    invalidate();
                }
                canvas.restoreToCount(save);
                return;
            }
            return;
        }
        int save2 = canvas.save();
        int height = getHeight();
        canvas.rotate(-90.0f, 0.0f, 0.0f);
        canvas.translate((float) ((-height) + getPaddingBottom()), 0.0f);
        this.mEdgeGlowLeft.setSize(getRenderHeight(), getRenderWidth());
        if (this.mEdgeGlowLeft.draw(canvas)) {
            invalidate();
        }
        canvas.restoreToCount(save2);
    }

    private void fillList(int i) {
        View rightmostChild = getRightmostChild();
        int i2 = 0;
        fillListRight(rightmostChild != null ? rightmostChild.getRight() : 0, i);
        View leftmostChild = getLeftmostChild();
        if (leftmostChild != null) {
            i2 = leftmostChild.getLeft();
        }
        fillListLeft(i2, i);
    }

    private void fillListLeft(int i, int i2) {
        int i3;
        while ((i + i2) - this.mDividerWidth > 0 && (i3 = this.mLeftViewAdapterIndex) >= 1) {
            int i4 = i3 - 1;
            this.mLeftViewAdapterIndex = i4;
            View view = this.f4273b.getView(i4, getRecycledView(i4), this);
            addAndMeasureChild(view, 0);
            i -= this.mLeftViewAdapterIndex == 0 ? view.getMeasuredWidth() : this.mDividerWidth + view.getMeasuredWidth();
            this.mDisplayOffset -= i + i2 == 0 ? view.getMeasuredWidth() : view.getMeasuredWidth() + this.mDividerWidth;
        }
    }

    private void fillListRight(int i, int i2) {
        while (i + i2 + this.mDividerWidth < getWidth() && this.mRightViewAdapterIndex + 1 < this.f4273b.getCount()) {
            int i3 = this.mRightViewAdapterIndex + 1;
            this.mRightViewAdapterIndex = i3;
            if (this.mLeftViewAdapterIndex < 0) {
                this.mLeftViewAdapterIndex = i3;
            }
            View view = this.f4273b.getView(i3, getRecycledView(i3), this);
            addAndMeasureChild(view, -1);
            i += (this.mRightViewAdapterIndex == 0 ? 0 : this.mDividerWidth) + view.getMeasuredWidth();
            determineIfLowOnData();
        }
    }

    private View getChild(int i) {
        int i2 = this.mLeftViewAdapterIndex;
        if (i < i2 || i > this.mRightViewAdapterIndex) {
            return null;
        }
        return getChildAt(i - i2);
    }


    public int getChildIndex(int i, int i2) {
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            getChildAt(i3).getHitRect(this.mRect);
            if (this.mRect.contains(i, i2)) {
                return i3;
            }
        }
        return -1;
    }

    private LayoutParams getLayoutParams(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        return layoutParams == null ? new LayoutParams(-2, -1) : layoutParams;
    }

    private View getLeftmostChild() {
        return getChildAt(0);
    }

    private View getRecycledView(int i) {
        int itemViewType = this.f4273b.getItemViewType(i);
        if (isItemViewTypeValid(itemViewType)) {
            return (View) this.mRemovedViewsCache.get(itemViewType).poll();
        }
        return null;
    }

    private int getRenderHeight() {
        return (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    private int getRenderWidth() {
        return (getWidth() - getPaddingLeft()) - getPaddingRight();
    }

    private View getRightmostChild() {
        return getChildAt(getChildCount() - 1);
    }

    private void initView() {
        this.mLeftViewAdapterIndex = -1;
        this.mRightViewAdapterIndex = -1;
        this.mDisplayOffset = 0;
        this.f4274c = 0;
        this.f4275d = 0;
        this.mMaxX = Integer.MAX_VALUE;
        setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE);
    }

    private void initializeRecycledViewCache(int i) {
        this.mRemovedViewsCache.clear();
        for (int i2 = 0; i2 < i; i2++) {
            this.mRemovedViewsCache.add(new LinkedList());
        }
    }

    private boolean isEdgeGlowEnabled() {
        ListAdapter listAdapter = this.f4273b;
        if (listAdapter == null || listAdapter.isEmpty() || this.mMaxX <= 0) {
            return false;
        }
        return true;
    }

    private boolean isItemViewTypeValid(int i) {
        return i < this.mRemovedViewsCache.size();
    }

    private boolean isLastItemInAdapter(int i) {
        return i == this.f4273b.getCount() - 1;
    }

    private void measureChild(View view) {
        int i;
        LayoutParams layoutParams = getLayoutParams(view);
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(this.mHeightMeasureSpec, getPaddingTop() + getPaddingBottom(), layoutParams.height);
        int i2 = layoutParams.width;
        if (i2 > 0) {
            i = MeasureSpec.makeMeasureSpec(i2, 1073741824);
        } else {
            i = MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(i, childMeasureSpec);
    }

    private void positionChildren(int i) {
        int childCount = getChildCount();
        if (childCount > 0) {
            int i2 = this.mDisplayOffset + i;
            this.mDisplayOffset = i2;
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                int paddingLeft = getPaddingLeft() + i2;
                int paddingTop = getPaddingTop();
                childAt.layout(paddingLeft, paddingTop, childAt.getMeasuredWidth() + paddingLeft, childAt.getMeasuredHeight() + paddingTop);
                i2 += childAt.getMeasuredWidth() + this.mDividerWidth;
            }
        }
    }

    private void recycleView(int i, View view) {
        int itemViewType = this.f4273b.getItemViewType(i);
        if (isItemViewTypeValid(itemViewType)) {
            this.mRemovedViewsCache.get(itemViewType).offer(view);
        }
    }

    private void releaseEdgeGlow() {
        EdgeEffect edgeEffect = this.mEdgeGlowLeft;
        if (edgeEffect != null) {
            edgeEffect.onRelease();
        }
        EdgeEffect edgeEffect2 = this.mEdgeGlowRight;
        if (edgeEffect2 != null) {
            edgeEffect2.onRelease();
        }
    }

    private void removeNonVisibleChildren(int i) {
        View leftmostChild = getLeftmostChild();
        while (leftmostChild != null && leftmostChild.getRight() + i <= 0) {
            this.mDisplayOffset += isLastItemInAdapter(this.mLeftViewAdapterIndex) ? leftmostChild.getMeasuredWidth() : this.mDividerWidth + leftmostChild.getMeasuredWidth();
            recycleView(this.mLeftViewAdapterIndex, leftmostChild);
            removeViewInLayout(leftmostChild);
            this.mLeftViewAdapterIndex++;
            leftmostChild = getLeftmostChild();
        }
        View rightmostChild = getRightmostChild();
        while (rightmostChild != null && rightmostChild.getLeft() + i >= getWidth()) {
            recycleView(this.mRightViewAdapterIndex, rightmostChild);
            removeViewInLayout(rightmostChild);
            this.mRightViewAdapterIndex--;
            rightmostChild = getRightmostChild();
        }
    }


    public void requestParentListViewToNotInterceptTouchEvents(Boolean bool) {
        if (this.mIsParentVerticiallyScrollableViewDisallowingInterceptTouchEvent != bool.booleanValue()) {
            for (View view = this; view.getParent() instanceof View; view = (View) view.getParent()) {
                if ((view.getParent() instanceof ListView) || (view.getParent() instanceof ScrollView)) {
                    view.getParent().requestDisallowInterceptTouchEvent(bool.booleanValue());
                    this.mIsParentVerticiallyScrollableViewDisallowingInterceptTouchEvent = bool.booleanValue();
                    return;
                }
            }
        }
    }


    public void reset() {
        initView();
        removeAllViewsInLayout();
        requestLayout();
    }

    private void retrieveXmlConfiguration(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.HorizontalListView);
            Drawable drawable = obtainStyledAttributes.getDrawable(1);
            if (drawable != null) {
                setDivider(drawable);
            }
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(3, 0);
            if (dimensionPixelSize != 0) {
                setDividerWidth(dimensionPixelSize);
            }
            obtainStyledAttributes.recycle();
        }
    }


    public void setCurrentScrollState(OnScrollStateChangedListener.ScrollState scrollState) {
        OnScrollStateChangedListener onScrollStateChangedListener;
        if (!(this.mCurrentScrollState == scrollState || (onScrollStateChangedListener = this.mOnScrollStateChangedListener) == null)) {
            onScrollStateChangedListener.onScrollStateChanged(scrollState);
        }
        this.mCurrentScrollState = scrollState;
    }


    public void unpressTouchedChild() {
        View view = this.mViewBeingTouched;
        if (view != null) {
            view.setPressed(false);
            refreshDrawableState();
            this.mViewBeingTouched = null;
        }
    }


    public void updateOverscrollAnimation(int i) {
        if (this.mEdgeGlowLeft != null && this.mEdgeGlowRight != null) {
            int i2 = this.f4274c + i;
            Scroller scroller = this.f4272a;
            if (scroller != null && !scroller.isFinished()) {
                return;
            }
            if (i2 < 0) {
                this.mEdgeGlowLeft.onPull(((float) Math.abs(i)) / ((float) getRenderWidth()));
                if (!this.mEdgeGlowRight.isFinished()) {
                    this.mEdgeGlowRight.onRelease();
                }
            } else if (i2 > this.mMaxX) {
                this.mEdgeGlowRight.onPull(((float) Math.abs(i)) / ((float) getRenderWidth()));
                if (!this.mEdgeGlowLeft.isFinished()) {
                    this.mEdgeGlowLeft.onRelease();
                }
            }
        }
    }


    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        drawEdgeGlow(canvas);
    }


    public void dispatchSetPressed(boolean z) {
    }

    public int getFirstVisiblePosition() {
        return this.mLeftViewAdapterIndex;
    }

    public int getLastVisiblePosition() {
        return this.mRightViewAdapterIndex;
    }


    public float getLeftFadingEdgeStrength() {
        int horizontalFadingEdgeLength = getHorizontalFadingEdgeLength();
        int i = this.f4274c;
        if (i == 0) {
            return 0.0f;
        }
        if (i < horizontalFadingEdgeLength) {
            return ((float) i) / ((float) horizontalFadingEdgeLength);
        }
        return 1.0f;
    }


    public float getRightFadingEdgeStrength() {
        int horizontalFadingEdgeLength = getHorizontalFadingEdgeLength();
        int i = this.f4274c;
        int i2 = this.mMaxX;
        if (i == i2) {
            return 0.0f;
        }
        if (i2 - i < horizontalFadingEdgeLength) {
            return ((float) (i2 - i)) / ((float) horizontalFadingEdgeLength);
        }
        return 1.0f;
    }

    public View getSelectedView() {
        return getChild(this.mCurrentlySelectedAdapterIndex);
    }


    public boolean m(MotionEvent motionEvent) {
        int childIndex;
        this.mBlockTouchAction = !this.f4272a.isFinished();
        this.f4272a.forceFinished(true);
        setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE);
        unpressTouchedChild();
        if (!this.mBlockTouchAction && (childIndex = getChildIndex((int) motionEvent.getX(), (int) motionEvent.getY())) >= 0) {
            View childAt = getChildAt(childIndex);
            this.mViewBeingTouched = childAt;
            if (childAt != null) {
                childAt.setPressed(true);
                refreshDrawableState();
            }
        }
        return true;
    }


    public boolean n(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.f4272a.fling(this.f4275d, 0, (int) (-f), 0, 0, this.mMaxX, 0, 0);
        setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_FLING);
        requestLayout();
        return true;
    }


    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawDividers(canvas);
    }


    @SuppressLint({"WrongCall"})
    public void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.f4273b != null) {
            invalidate();
            if (this.mDataChanged) {
                int i5 = this.f4274c;
                initView();
                removeAllViewsInLayout();
                this.f4275d = i5;
                this.mDataChanged = false;
            }
            Integer num = this.mRestoreX;
            if (num != null) {
                this.f4275d = num.intValue();
                this.mRestoreX = null;
            }
            if (this.f4272a.computeScrollOffset()) {
                this.f4275d = this.f4272a.getCurrX();
            }
            int i6 = this.f4275d;
            if (i6 < 0) {
                this.f4275d = 0;
                if (this.mEdgeGlowLeft.isFinished()) {
                    this.mEdgeGlowLeft.onAbsorb((int) determineFlingAbsorbVelocity());
                }
                this.f4272a.forceFinished(true);
                setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE);
            } else {
                int i7 = this.mMaxX;
                if (i6 > i7) {
                    this.f4275d = i7;
                    if (this.mEdgeGlowRight.isFinished()) {
                        this.mEdgeGlowRight.onAbsorb((int) determineFlingAbsorbVelocity());
                    }
                    this.f4272a.forceFinished(true);
                    setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE);
                }
            }
            int i8 = this.f4274c - this.f4275d;
            removeNonVisibleChildren(i8);
            fillList(i8);
            positionChildren(i8);
            this.f4274c = this.f4275d;
            if (determineMaxX()) {
                onLayout(z, i, i2, i3, i4);
            } else if (!this.f4272a.isFinished()) {
                ViewCompat.postOnAnimation(this, this.mDelayedLayout);
            } else if (this.mCurrentScrollState == OnScrollStateChangedListener.ScrollState.SCROLL_STATE_FLING) {
                setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE);
            }
        }
    }


    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.mHeightMeasureSpec = i2;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.mRestoreX = Integer.valueOf(bundle.getInt(BUNDLE_ID_CURRENT_X));
            super.onRestoreInstanceState(bundle.getParcelable(BUNDLE_ID_PARENT_STATE));
        }
    }

    public Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable(BUNDLE_ID_PARENT_STATE, super.onSaveInstanceState());
        bundle.putInt(BUNDLE_ID_CURRENT_X, this.f4274c);
        return bundle;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            Scroller scroller = this.f4272a;
            if (scroller == null || scroller.isFinished()) {
                setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_IDLE);
            }
            requestParentListViewToNotInterceptTouchEvents(Boolean.FALSE);
            releaseEdgeGlow();
        } else if (motionEvent.getAction() == 3) {
            unpressTouchedChild();
            releaseEdgeGlow();
            requestParentListViewToNotInterceptTouchEvents(Boolean.FALSE);
        }
        return super.onTouchEvent(motionEvent);
    }

    public void scrollTo(int i) {
        Scroller scroller = this.f4272a;
        int i2 = this.f4275d;
        scroller.startScroll(i2, 0, i - i2, 0);
        setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_FLING);
        requestLayout();
    }

    public void scrollToLeft() {
        int measuredWidth = this.f4275d - getMeasuredWidth();
        this.f4275d = measuredWidth;
        this.f4272a.fling(measuredWidth, 0, ItemTouchHelper.Callback.DEFAULT_SWIPE_ANIMATION_DURATION, 0, 0, this.mMaxX, 0, 0);
        setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_FLING);
        requestLayout();
    }

    public void scrollToRight() {
        int measuredWidth = this.f4275d + getMeasuredWidth();
        this.f4275d = measuredWidth;
        this.f4272a.fling(measuredWidth, 0, -250, 0, 0, this.mMaxX, 0, 0);
        setCurrentScrollState(OnScrollStateChangedListener.ScrollState.SCROLL_STATE_FLING);
        requestLayout();
    }

    public void setDivider(Drawable drawable) {
        this.mDivider = drawable;
        if (drawable != null) {
            setDividerWidth(drawable.getIntrinsicWidth());
        } else {
            setDividerWidth(0);
        }
    }

    public void setDividerWidth(int i) {
        this.mDividerWidth = i;
        requestLayout();
        invalidate();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

    public void setOnScrollStateChangedListener(OnScrollStateChangedListener onScrollStateChangedListener) {
        this.mOnScrollStateChangedListener = onScrollStateChangedListener;
    }

    public void setRunningOutOfDataListener(RunningOutOfDataListener runningOutOfDataListener, int i) {
        this.mRunningOutOfDataListener = runningOutOfDataListener;
        this.mRunningOutOfDataThreshold = i;
    }

    public void setSelection(int i) {
        this.mCurrentlySelectedAdapterIndex = i;
    }

    public ListAdapter getAdapter() {
        return this.f4273b;
    }

    public void setAdapter(ListAdapter listAdapter) {
        ListAdapter listAdapter2 = this.f4273b;
        if (listAdapter2 != null) {
            listAdapter2.unregisterDataSetObserver(this.mAdapterDataObserver);
        }
        if (listAdapter != null) {
            this.mHasNotifiedRunningLowOnData = false;
            this.f4273b = listAdapter;
            listAdapter.registerDataSetObserver(this.mAdapterDataObserver);
        }
        initializeRecycledViewCache(this.f4273b.getViewTypeCount());
        reset();
    }
}

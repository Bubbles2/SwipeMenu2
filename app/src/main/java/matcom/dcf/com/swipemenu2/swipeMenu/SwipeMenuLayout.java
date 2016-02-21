/*
 * Copyright(c) $year PagesJaunes, SoLocal Group - All Rights Reserved.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited. Proprietary and confidential
 */
package matcom.dcf.com.swipemenu2.swipeMenu;

import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.AbsListView;
import android.widget.FrameLayout;

import matcom.dcf.com.swipemenu2.SwipeSupport.PJBloc;

public class SwipeMenuLayout extends FrameLayout
{
	private static final int STATE_CLOSE = 0;
	private static final int STATE_OPEN = 1;

	private View mContentView;

	public View getContentView()
	{
		return mContentView;
	}

	public SwipeMenuView mLeftMenuView;
	public SwipeMenuView mRightMenuView;
	private int state = STATE_CLOSE;
	private int position;
	private int lastTouchX;

	public SwipeMenuLayout(View contentView, SwipeMenuView leftMenuView, SwipeMenuView rightMenuView)
	{
		super(contentView.getContext());
		mContentView = contentView;
		mLeftMenuView = leftMenuView;
		mLeftMenuView.setLayout(this);

		mRightMenuView = rightMenuView;
		mRightMenuView.setLayout(this);

		init();
	}

	public int getPosition()
	{
		return position;
	}

	public void setPosition(int position)
	{
		this.position = position;
		mLeftMenuView.setPosition(position);
		mRightMenuView.setPosition(position);
	}

	private void init()
	{
		setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));

		LayoutParams contentParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		mContentView.setLayoutParams(contentParams);

		mLeftMenuView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		mRightMenuView.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		this.removeAllViews();

		addView(mContentView);
		//if (FeatureFlippingUtils.isLRSwipeEnabled())
		//{
			addView(mLeftMenuView);
			addView(mRightMenuView);
		//}
	}

	public boolean onSwipe(MotionEvent event)
	{
//		if (FeatureFlippingUtils.isLRSwipeEnabled())
//		{
			switch (event.getAction())
			{
				case MotionEvent.ACTION_DOWN:
					lastTouchX = (int) event.getX();
					break;

				case MotionEvent.ACTION_MOVE:
					int dis = (int) (lastTouchX - event.getX());
					if (dis != 0)
					{
						state = STATE_OPEN;
					}
					drag(dis);
					lastTouchX = (int) event.getX();
					break;

				case MotionEvent.ACTION_UP:
					if (Math.abs(mContentView.getLeft()) < 4)
					{
						return true;
					}
					else if (mContentView.getLeft() > (mLeftMenuView.getWidth() / 2) && mLeftMenuView.getWidth() > mContentView.getLeft())
					{
						smoothOpenMenu();
					}
					else if (-mContentView.getLeft() > (mRightMenuView.getWidth() / 2) && -mContentView.getLeft() < mRightMenuView.getWidth())
					{
						smoothOpenMenu();
					}
					else
					{
						smoothCloseMenu();
						return false;
					}
					break;
			}
	//	}
		return true;

	}

	public boolean isOpen()
	{
		return state == STATE_OPEN;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		return false;
	}

	private void drag(int dis)
	{
		if (dis > 0 && dis > mRightMenuView.getWidth())
		{
			dis = mRightMenuView.getWidth();
		}
		else if (dis < 0 && Math.abs(dis) > mLeftMenuView.getWidth())
		{
			dis = -mLeftMenuView.getWidth();
		}

		if ((mContentView.getLeft() - dis) < mLeftMenuView.getWidth() && mContentView.getRight() - dis > (mContentView.getWidth() - mRightMenuView.getWidth()))
		{
			mContentView.layout(mContentView.getLeft() - dis, mContentView.getTop(), mContentView.getRight() - dis, getMeasuredHeight());
			mLeftMenuView.layout(mContentView.getLeft() - mLeftMenuView.getWidth(), mContentView.getTop(), mContentView.getLeft(), mContentView.getBottom());
			mRightMenuView.layout(mContentView.getRight(), mContentView.getTop(), mContentView.getRight() + mRightMenuView.getWidth(), mContentView.getBottom());
		}

	}

	public void smoothCloseMenu()
	{
		state = STATE_CLOSE;
		if (mContentView.getLeft() > 0)
		{
			float dx_1 = -mLeftMenuView.getRight();

			TranslateAnimation animation_1 = new TranslateAnimation(0, dx_1, 0, 0);
			animation_1.setDuration(100);
			animation_1.setFillBefore(true);
			animation_1.setInterpolator(new LinearInterpolator());
			animation_1.setAnimationListener(new Animation.AnimationListener()
			{
				@Override
				public void onAnimationStart(Animation animation)
				{

				}

				@Override
				public void onAnimationEnd(Animation animation)
				{
					mLeftMenuView.clearAnimation();
					mLeftMenuView.layout(-mLeftMenuView.getWidth(), mLeftMenuView.getTop(), 0, mLeftMenuView.getBottom());
					mContentView.layout(0, mContentView.getTop(), mContentView.getWidth(), getMeasuredHeight());
					mRightMenuView.layout(mContentView.getWidth(), mContentView.getTop(), mContentView.getWidth() + mRightMenuView.getWidth(), mContentView.getBottom());
				}

				@Override
				public void onAnimationRepeat(Animation animation)
				{
				}
			});

			TranslateAnimation animation_2 = new TranslateAnimation(0, dx_1, 0, 0);
			animation_2.setDuration(100);
			animation_2.setFillBefore(true);
			animation_2.setInterpolator(new LinearInterpolator());
			animation_2.setAnimationListener(new Animation.AnimationListener()
			{
				@Override
				public void onAnimationStart(Animation animation)
				{
				}

				@Override
				public void onAnimationEnd(Animation animation)
				{
					mContentView.clearAnimation();
					//	mLeftMenuView.layout(0, mLeftMenuView.getTop(), mLeftMenuView.getWidth(), mLeftMenuView.getBottom());
					mContentView.layout(0, mContentView.getTop(), mContentView.getWidth(), getMeasuredHeight());
					mRightMenuView.layout(mContentView.getRight(), mContentView.getTop(), mContentView.getRight() + mRightMenuView.getWidth(), mContentView.getBottom());
				}

				@Override
				public void onAnimationRepeat(Animation animation)
				{
				}
			});

			mLeftMenuView.startAnimation(animation_1);
			mContentView.startAnimation(animation_2);

		}
		else if (mContentView.getLeft() < 0)
		{

			float dx_1 = -mContentView.getLeft();

			TranslateAnimation animation_1 = new TranslateAnimation(0, dx_1, 0, 0);
			animation_1.setDuration(100);
			animation_1.setFillBefore(true);
			animation_1.setFillAfter(true);
			animation_1.setInterpolator(new LinearInterpolator());
			animation_1.setAnimationListener(new Animation.AnimationListener()
			{
				@Override
				public void onAnimationStart(Animation animation)
				{

				}

				@Override
				public void onAnimationEnd(Animation animation)
				{
					mRightMenuView.clearAnimation();
					//	mLeftMenuView.layout(0, mLeftMenuView.getTop(), mLeftMenuView.getWidth(), mLeftMenuView.getBottom());
					mContentView.layout(0, mContentView.getTop(), mContentView.getWidth(), getMeasuredHeight());
					mRightMenuView.layout(mContentView.getRight(), mContentView.getTop(), mContentView.getRight() + mRightMenuView.getWidth(), mContentView.getBottom());
				}

				@Override
				public void onAnimationRepeat(Animation animation)
				{
				}
			});

			TranslateAnimation animation_2 = new TranslateAnimation(0, dx_1, 0, 0);
			animation_2.setDuration(100);
			animation_2.setFillBefore(true);
			animation_1.setFillAfter(true);
			animation_2.setInterpolator(new LinearInterpolator());
			animation_2.setAnimationListener(new Animation.AnimationListener()
			{
				@Override
				public void onAnimationStart(Animation animation)
				{
				}

				@Override
				public void onAnimationEnd(Animation animation)
				{
					mContentView.clearAnimation();
					//	mLeftMenuView.layout(0, mLeftMenuView.getTop(), mLeftMenuView.getWidth(), mLeftMenuView.getBottom());
					mContentView.layout(0, mContentView.getTop(), mContentView.getWidth(), getMeasuredHeight());
					mRightMenuView.layout(mContentView.getRight(), mContentView.getTop(), mContentView.getRight() + mRightMenuView.getWidth(), mContentView.getBottom());
				}

				@Override
				public void onAnimationRepeat(Animation animation)
				{
				}
			});

			mRightMenuView.startAnimation(animation_1);
			mContentView.startAnimation(animation_2);
		}

	}

	public void smoothOpenMenu()
	{
		state = STATE_OPEN;
		if (mContentView.getLeft() > 0)
		{
			float dx_1 = mLeftMenuView.getWidth() - mLeftMenuView.getRight();
			float dx_2 = mLeftMenuView.getWidth() - mLeftMenuView.getRight();

			TranslateAnimation animation_1 = new TranslateAnimation(0, dx_1, 0, 0);
			animation_1.setDuration(100);
			animation_1.setFillBefore(true);
			animation_1.setInterpolator(new LinearInterpolator());
			animation_1.setAnimationListener(new Animation.AnimationListener()
			{
				@Override
				public void onAnimationStart(Animation animation)
				{

				}

				@Override
				public void onAnimationEnd(Animation animation)
				{
					mLeftMenuView.clearAnimation();
					mLeftMenuView.layout(0, mLeftMenuView.getTop(), mLeftMenuView.getWidth(), mLeftMenuView.getBottom());
					mContentView.layout(mLeftMenuView.getWidth(), mContentView.getTop(), mContentView.getWidth() + mLeftMenuView.getWidth(), getMeasuredHeight());
					mRightMenuView.layout(mContentView.getWidth() + mLeftMenuView.getWidth(), mContentView.getTop(), mContentView.getWidth() + mLeftMenuView.getWidth() + mRightMenuView.getWidth(), mContentView.getBottom());
				}

				@Override
				public void onAnimationRepeat(Animation animation)
				{
				}
			});

			TranslateAnimation animation_2 = new TranslateAnimation(0, dx_2, 0, 0);
			animation_2.setDuration(100);
			animation_2.setFillBefore(true);
			animation_2.setInterpolator(new LinearInterpolator());
			animation_2.setAnimationListener(new Animation.AnimationListener()
			{
				@Override
				public void onAnimationStart(Animation animation)
				{
				}

				@Override
				public void onAnimationEnd(Animation animation)
				{
					mContentView.clearAnimation();
					//	mLeftMenuView.layout(0, mLeftMenuView.getTop(), mLeftMenuView.getWidth(), mLeftMenuView.getBottom());
					mContentView.layout(mLeftMenuView.getWidth(), mContentView.getTop(), mContentView.getWidth() + mLeftMenuView.getWidth(), getMeasuredHeight());
					//		mRightMenuView.layout(mContentView.getRight(), mContentView.getTop(), mContentView.getRight() + mRightMenuView.getWidth(), mContentView.getBottom());
				}

				@Override
				public void onAnimationRepeat(Animation animation)
				{
				}
			});

			mLeftMenuView.startAnimation(animation_1);
			mContentView.startAnimation(animation_2);

		}
		else if (mContentView.getLeft() < 0)
		{

			float dx_1 = mRightMenuView.getWidth() + mContentView.getLeft();

			TranslateAnimation animation_1 = new TranslateAnimation(0, -dx_1, 0, 0);
			animation_1.setDuration(100);
			animation_1.setFillBefore(true);
			animation_1.setFillAfter(true);
			animation_1.setInterpolator(new LinearInterpolator());
			animation_1.setAnimationListener(new Animation.AnimationListener()
			{
				@Override
				public void onAnimationStart(Animation animation)
				{

				}

				@Override
				public void onAnimationEnd(Animation animation)
				{
					mRightMenuView.clearAnimation();
					mRightMenuView.layout(mContentView.getWidth() - mRightMenuView.getWidth(), mContentView.getTop(), mContentView.getWidth(), mContentView.getBottom());

					mLeftMenuView.layout(-mLeftMenuView.getWidth() - mRightMenuView.getWidth(), mLeftMenuView.getTop(), -mRightMenuView.getWidth(), mLeftMenuView.getBottom());
					mContentView.layout(-mRightMenuView.getWidth(), mContentView.getTop(), mRightMenuView.getLeft(), getMeasuredHeight());
				}

				@Override
				public void onAnimationRepeat(Animation animation)
				{
				}
			});

			TranslateAnimation animation_2 = new TranslateAnimation(0, -dx_1, 0, 0);
			animation_2.setDuration(100);
			animation_2.setFillBefore(true);
			animation_1.setFillAfter(true);
			animation_2.setInterpolator(new LinearInterpolator());
			animation_2.setAnimationListener(new Animation.AnimationListener()
			{
				@Override
				public void onAnimationStart(Animation animation)
				{
				}

				@Override
				public void onAnimationEnd(Animation animation)
				{
					mContentView.clearAnimation();
					//	mLeftMenuView.layout(0, mLeftMenuView.getTop(), mLeftMenuView.getWidth(), mLeftMenuView.getBottom());
					mContentView.layout(-mRightMenuView.getWidth(), mContentView.getTop(), mRightMenuView.getLeft(), getMeasuredHeight());
					//mRightMenuView.layout(mContentView.getRight(), mContentView.getTop(), mContentView.getRight() + mRightMenuView.getWidth(), mContentView.getBottom());
				}

				@Override
				public void onAnimationRepeat(Animation animation)
				{
				}
			});

			mRightMenuView.startAnimation(animation_1);
			mContentView.startAnimation(animation_2);
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
	{
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		mLeftMenuView.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.EXACTLY));
		mRightMenuView.measure(MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED), MeasureSpec.makeMeasureSpec(getMeasuredHeight(), MeasureSpec.EXACTLY));
	}

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b)
	{
		mContentView.layout(0, 0, getMeasuredWidth(), mContentView.getMeasuredHeight());
		mLeftMenuView.layout(getMeasuredWidth(), 0, getMeasuredWidth() + mLeftMenuView.getMeasuredWidth(), mContentView.getMeasuredHeight());
		mRightMenuView.layout(getMeasuredWidth(), 0, getMeasuredWidth() + mRightMenuView.getMeasuredWidth(), mContentView.getMeasuredHeight());
	}

	public void setPJBloc(PJBloc pjBloc)
	{
		mLeftMenuView.setPJBloc(pjBloc);
		mRightMenuView.setPJBloc(pjBloc);
	}
}

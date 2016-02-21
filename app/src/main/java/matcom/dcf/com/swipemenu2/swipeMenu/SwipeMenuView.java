/*
 * Copyright(c) $year PagesJaunes, SoLocal Group - All Rights Reserved.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited. Proprietary and confidential
 */
package matcom.dcf.com.swipemenu2.swipeMenu;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;


import java.util.List;


import matcom.dcf.com.swipemenu2.R;
//import matcom.dcf.com.swipemenu2.SwipeSupport.FontUtils;
import matcom.dcf.com.swipemenu2.SwipeSupport.PJBloc;

public class SwipeMenuView extends LinearLayout implements OnClickListener
{
	private SwipeMenuLayout mLayout;
	private SwipeMenu mMenu;
	private Context mContext;
	private OnSwipeItemClickListener onItemClickListener;
	private int position;

	public int getPosition()
	{
		return position;
	}

	public void setPosition(int position)
	{
		this.position = position;
	}

	public SwipeMenuView(Context context, SwipeMenu menu)
	{
		super(context);
		mContext = context;
		mMenu = menu;

	}

	public void setPJBloc(PJBloc pjBloc)
	{
		mMenu.pjBloc = pjBloc;
	}

	public TextView addItem(int id, String title, int icon, boolean isLeftMenuView)
	{

		LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		LinearLayout parent = null;

		if (isLeftMenuView)
		{
			parent = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.lr_module_swipe_left_menu_element, null);
			setBackgroundColor(getResources().getColor(R.color.black_2));
		}
		else
		{
			parent = (LinearLayout) LayoutInflater.from(mContext).inflate(R.layout.lr_module_swipe_right_menu_element, null);
			setBackgroundColor(getResources().getColor(R.color.yellow));
		}

		parent.setLayoutParams(params);
		parent.setId(id);
		parent.setGravity(Gravity.CENTER);
		parent.setOrientation(LinearLayout.VERTICAL);
		parent.setLayoutParams(params);
		parent.setOnClickListener(this);

		TextView actionBtn = (TextView) parent.findViewById(R.id.lr_swipe_btn_title);
		actionBtn.setText(title);
		//actionBtn.setTypeface(FontUtils.REGULAR);
		actionBtn.setCompoundDrawablesWithIntrinsicBounds(0, icon, 0, 0);
		actionBtn.setVisibility(View.VISIBLE);

		addView(parent);

		return actionBtn;

	}

	@Override
	public void onClick(View v)
	{
		if (onItemClickListener != null && mLayout.isOpen())
		{
			onItemClickListener.onItemClick(this, mMenu, v.getId());
		}
	}

	public void setOnSwipeItemClickListener(OnSwipeItemClickListener onItemClickListener)
	{
		this.onItemClickListener = onItemClickListener;
	}

	public void setLayout(SwipeMenuLayout mLayout)
	{
		this.mLayout = mLayout;
	}

	public static interface OnSwipeItemClickListener
	{
		void onItemClick(SwipeMenuView view, SwipeMenu menu, int index);
	}

}

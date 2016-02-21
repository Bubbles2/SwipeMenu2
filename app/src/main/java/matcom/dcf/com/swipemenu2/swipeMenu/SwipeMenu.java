/*
 * Copyright(c) $year PagesJaunes, SoLocal Group - All Rights Reserved.
 *
 * Unauthorized copying of this file, via any medium is strictly prohibited. Proprietary and confidential
 */

package matcom.dcf.com.swipemenu2.swipeMenu;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import matcom.dcf.com.swipemenu2.SwipeSupport.PJBloc;

public class SwipeMenu
{

	private Context mContext;
	public PJBloc pjBloc;

	public SwipeMenu(Context context)
	{
		mContext = context;
	}

	public Context getContext()
	{
		return mContext;
	}

}

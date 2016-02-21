package matcom.dcf.com.swipemenu2.SwipeSupport;

import android.text.TextUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * PagesJaunes Bloc
 */
public class PJBloc extends HistorizablePJModel implements Cloneable, Serializable
{
	private static final long serialVersionUID = 8226219357095930422L;

	public final static String BUSINESS = "Pro";

	//	private final static String RESIDENTIAL = "Part";

	public enum ORDER_NAME
	{
		NAME_1ST,
		//
		FIRSTNAME_1ST //
	}

	public long db_id;

	public String id; // bloc[id]
	public String epjId; // bloc[bp_epj]
	public String blockId; // bloc[blockId)
}



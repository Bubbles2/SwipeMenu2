package matcom.dcf.com.swipemenu2.SwipeSupport;

import java.io.Serializable;

public class PJBlocTag implements Serializable
{
	private static final long serialVersionUID = -2932247839994108033L;

	/**
		VT : Vitrine Produits et Services
		VH : "Vitrine d'annonces immobilières Standard".
		HE : "Vitrine d'annonces immobilières Expert"
		HP : "Vitrine d'annonces immobilières Prenium"
		VO : "Carroussel Vitrine immobilières"
		VA : "Vitrine d'annonces automobile"
		VM : "Vitrine d'annonces moto"
	*/
	public static enum TYPE
	{
		NO_TYPE,
		VIDEO,
		L_SITE_WEB,
		L_COMMANDER,
		L_RESERVER,
		L_CLICK_RDV,
		E_COMMERCE,
		OM_FERME,
		OM_OUVERT,
		OM_UNKNOWN,
		VT,
		VH,
		HE,
		HP,
		VO,
		VM,
		VA,
		LLAFO
	}

	private static final String TYPE_VIDEO_KEY = "VIDEO";
	private static final String TYPE_LSITEWEB_KEY = "LSITEWEB";
	private static final String TYPE_LCOMMANDER_KEY = "LCOMMANDER";
	private static final String TYPE_LRESERVER_KEY = "LRESERVER";
	private static final String TYPE_LCLICKRDV_KEY = "LCLICKRDV";
	private static final String TYPE_E_COMMERCE_KEY = "E-COMMERCE";
	private static final String TYPE_OM_FERME_KEY = "OM_FERME";
	private static final String TYPE_OM_OUVERT_KEY = "OM_OUVERT";
	private static final String TYPE_OM_UNKNOWN_KEY = "OM_UNKNOWN";
	private static final String TYPE_VT_PRO_SV_KEY = "VT";
	private static final String TYPE_VH_PRO_KEY = "VH";
	private static final String TYPE_HE_PRO_KEY = "HE";
	private static final String TYPE_HP_PRO_KEY = "HP";
	private static final String TYPE_VO_PRO_KEY = "VO";
	private static final String TYPE_VA_KEY = "VA";
	private static final String TYPE_VM_KEY = "VM";
	private static final String TYPE_LLAFO = "LLAFO";

	protected static final String TYPE_SEPARATOR_KEY = ",";

	public long id;
	public TYPE type;

	public PJBlocTag()
	{
		super();
	}

	public PJBlocTag(String tag)
	{
		this.parse(tag);
	}

	/**
	 * @param tag
	 */
	private void parse(String tag)
	{
		this.type = PJBlocTag.getTypeFromString(tag);
	}

	public static TYPE getTypeFromString(String type)
	{
		TYPE mType = null;
		if (type.equals(PJBlocTag.TYPE_VIDEO_KEY))
		{
			mType = TYPE.VIDEO;
		}
		else if (type.equals(PJBlocTag.TYPE_LSITEWEB_KEY))
		{
			mType = TYPE.L_SITE_WEB;
		}
		else if (type.equals(PJBlocTag.TYPE_LCOMMANDER_KEY))
		{
			mType = TYPE.L_COMMANDER;
		}
		else if (type.equals(PJBlocTag.TYPE_LRESERVER_KEY))
		{
			mType = TYPE.L_RESERVER;
		}
		else if (type.equals(PJBlocTag.TYPE_LCLICKRDV_KEY))
		{
			mType = TYPE.L_CLICK_RDV;
		}
		else if (type.equals(PJBlocTag.TYPE_E_COMMERCE_KEY))
		{
			mType = TYPE.E_COMMERCE;
		}
		else if (type.equals(PJBlocTag.TYPE_OM_FERME_KEY))
		{
			mType = TYPE.OM_FERME;
		}
		else if (type.equals(PJBlocTag.TYPE_OM_OUVERT_KEY))
		{
			mType = TYPE.OM_OUVERT;
		}
		else if (type.equals(PJBlocTag.TYPE_OM_UNKNOWN_KEY))
		{
			mType = TYPE.OM_UNKNOWN;
		}
		else if (type.equals(PJBlocTag.TYPE_VT_PRO_SV_KEY))
		{
			mType = TYPE.VT;
		}
		else if (type.equals(PJBlocTag.TYPE_LLAFO))
		{
			mType = TYPE.LLAFO;
		}
		else if (type.equals(PJBlocTag.TYPE_VH_PRO_KEY))
		{
			mType = TYPE.VH;
		}
		else if (type.equals(PJBlocTag.TYPE_HE_PRO_KEY))
		{
			mType = TYPE.HE;
		}
		else if (type.equals(PJBlocTag.TYPE_HP_PRO_KEY))
		{
			mType = TYPE.HP;
		}
		else if (type.equals(PJBlocTag.TYPE_VO_PRO_KEY))
		{
			mType = TYPE.VO;
		}
		else if (type.equals(PJBlocTag.TYPE_VM_KEY))
		{
			mType = TYPE.VM;
		}
		else if (type.equals(PJBlocTag.TYPE_VA_KEY))
		{
			mType = TYPE.VA;
		}
		else
		{
			mType = TYPE.NO_TYPE;
		}

		return mType;
	}
}
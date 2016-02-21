package matcom.dcf.com.swipemenu2;

import android.location.Location;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import matcom.dcf.com.swipemenu2.SwipeSupport.PJBloc;
import matcom.dcf.com.swipemenu2.SwipeSupport.PJBlocTag;
import matcom.dcf.com.swipemenu2.swipeMenu.SwipeMenu;
import matcom.dcf.com.swipemenu2.swipeMenu.SwipeMenuLayout;
import matcom.dcf.com.swipemenu2.swipeMenu.SwipeMenuView;

public class MainActivity extends AppCompatActivity implements SwipeMenuView.OnSwipeItemClickListener {
    @Override
    public void onItemClick(SwipeMenuView view, SwipeMenu menu, int index) {

    }

    public class LeftMenuHolder
    {
        TextView review;
        TextView contact;
        TextView share;
    }

    public class RightMenuHolder
    {
        TextView call;
        TextView goTo;
        TextView reserve;
        TextView reserveLAFO;
        TextView meet;
        TextView order;
        TextView mail;
        TextView buy;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        //FrameLayout f = (FrameLayout) findViewById(R.id.ffl);
        RelativeLayout r = (RelativeLayout) findViewById(R.id.rl1);
        fillLayout(null,r);
    }

    private SwipeMenuLayout fillLayout(SwipeMenuLayout layout, View contentView)
    {
//        LRAbstractAdapter.BlocHolder holder = (LRAbstractAdapter.BlocHolder) contentView.getTag();
//        PJBloc pjBloc = holder.bloc;
//        PJPlace pjPlace = pjBloc.places.get(0);

        LeftMenuHolder leftMenuHolder = new LeftMenuHolder();
        RightMenuHolder rightMenuHolder = new RightMenuHolder();

        if (layout == null)
        {
            SwipeMenu rightMenu = new SwipeMenu(this);
            //rightMenu.pjBloc = pjBloc;
            SwipeMenu leftMenu = new SwipeMenu(this);
            //leftMenu.pjBloc = pjBloc;
            SwipeMenuView rightMenuView = new SwipeMenuView(this, rightMenu);
            SwipeMenuView leftMenuView = new SwipeMenuView(this, leftMenu);
            leftMenuView.setOnSwipeItemClickListener(this);
            rightMenuView.setOnSwipeItemClickListener(this);

            fillRightMenu(rightMenuHolder, rightMenuView);
            fillLeftMenu(leftMenuHolder, leftMenuView);
            layout = new SwipeMenuLayout(contentView, leftMenuView, rightMenuView);
        }
        else
        {
            //layout.setPJBloc(pjBloc);
            leftMenuHolder = (LeftMenuHolder) layout.mLeftMenuView.getTag();
            rightMenuHolder = (RightMenuHolder) layout.mRightMenuView.getTag();
        }


           rightMenuHolder.call.setVisibility(View.VISIBLE);



        // Itineraire

            rightMenuHolder.goTo.setVisibility(View.VISIBLE);



                    rightMenuHolder.reserve.setText("Text 1");
                    rightMenuHolder.reserve.setVisibility(View.VISIBLE);
                    rightMenuHolder.reserveLAFO.setVisibility(View.GONE);
                    rightMenuHolder.meet.setVisibility(View.GONE);




                    rightMenuHolder.order.setText("Text 2");
                    rightMenuHolder.order.setVisibility(View.VISIBLE);



            rightMenuHolder.mail.setVisibility(View.VISIBLE);


                rightMenuHolder.buy.setVisibility(View.VISIBLE);
                rightMenuHolder.buy.setText("Text ");


            leftMenuHolder.review.setVisibility(View.VISIBLE);

        leftMenuHolder.contact.setVisibility(View.VISIBLE);

        layout.setTag("Holder Tag");
        return layout;
    }
    private void fillLeftMenu(LeftMenuHolder leftMenuHolder, SwipeMenuView leftMenuView)
    {
        leftMenuHolder.review = this.setActionBarButton(leftMenuView, this.getResources().getString(R.string.fd_floating_review), R.drawable.fd_bouton_flottant_avis, R.id.lr_swipe_left_add_review, true);
        leftMenuHolder.contact = this.setActionBarButton(leftMenuView, this.getResources().getString(R.string.fd_floating_contact), R.drawable.fd_bouton_flottant_contact, R.id.lr_swipe_left_add_contact, true);
        leftMenuHolder.share = this.setActionBarButton(leftMenuView, this.getResources().getString(R.string.fd_floating_share), R.drawable.fd_bouton_flottant_partage, R.id.lr_swipe_left_share, true);
        leftMenuView.setTag(leftMenuHolder);
    }

    private void fillRightMenu(RightMenuHolder rightMenuHolder, SwipeMenuView rightMenuView)
    {
        rightMenuHolder.call = rightMenuView.addItem(R.id.lr_swipe_right_item_call, this.getResources().getString(R.string.call), R.drawable.fd_actionbar_bt_appeler, false);
        rightMenuHolder.goTo = rightMenuView.addItem(R.id.lr_swipe_right_item_goto, this.getResources().getString(R.string.go), R.drawable.fd_actionbar_bt_y_aller_panneau_enabled, false);

        rightMenuHolder.reserve = rightMenuView.addItem(R.id.lr_swipe_right_item_reserve, "", R.drawable.fd_actionbar_bt_reserver, false);
        rightMenuHolder.reserveLAFO = rightMenuView.addItem(R.id.lr_swipe_right_item_reserve_lafo, "", R.drawable.fd_actionbar_bt_reserver, false);
        rightMenuHolder.meet = rightMenuView.addItem(R.id.lr_swipe_right_item_meet, "", R.drawable.fd_actionbar_bt_reserver, false);
        rightMenuHolder.order = rightMenuView.addItem(R.id.lr_swipe_right_item_order, "", R.drawable.fd_actionbar_bt_commander, false);
        rightMenuHolder.mail = rightMenuView.addItem(R.id.lr_swipe_right_item_mail, this.getResources().getString(R.string.mail), R.drawable.fd_actionbar_bt_mail, false);
        rightMenuHolder.buy = rightMenuView.addItem(R.id.lr_swipe_right_item_buy, "", R.drawable.fd_actionbar_bt_acheter, false);

        rightMenuView.setTag(rightMenuHolder);
    }
    private TextView setActionBarButton(SwipeMenuView swipeMenuView, String title, int imageResourceId, int viewId, boolean isLeftMenu)
    {
        return swipeMenuView.addItem(viewId, title, imageResourceId, isLeftMenu);
    }



}

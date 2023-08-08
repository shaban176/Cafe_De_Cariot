package com.uzziqam.cafedecariot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.uzziqam.cafedecariot.ui.Delivery;
import com.uzziqam.cafedecariot.ui.Favorite;
import com.uzziqam.cafedecariot.ui.First;
import com.uzziqam.cafedecariot.ui.Me;
import com.uzziqam.cafedecariot.ui.Orders;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class Home extends AppCompatActivity {
private MeowBottomNavigation bottomNavigation;

private final static int MenuHome=1;
private final static int Order=2;
private final static int Delivery=3;
private final static int Favorite=4;
private final static int Me=5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigation=findViewById(R.id.bottom_navi);

        bottomNavigation.add(new MeowBottomNavigation.Model(MenuHome,R.drawable.baseline_fastfood_24_black));
        bottomNavigation.add(new MeowBottomNavigation.Model(Order,R.drawable.baseline_shopping_cart_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(Delivery,R.drawable.baseline_delivery_dining_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(Favorite,R.drawable.baseline_favorite_24));
        bottomNavigation.add(new MeowBottomNavigation.Model(Me,R.drawable.baseline_person_24));

        bottomNavigation.show(MenuHome,true);
        //add methods

        bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // YOUR CODES
                return null;
            }
        });

        bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                // show fragment CODES

                Fragment fragment=null;
                if (model.getId()==1){
                    fragment=new First();
                    
                } else if (model.getId()==2) {
                    fragment=new Orders();
                } else if (model.getId()==3) {
                    fragment=new Delivery();
                } else if (model.getId()==4) {
                    fragment=new Favorite();
                }else fragment=new Me();

                //create function

                loadFragment(fragment);
                return null;
            }
        });

    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment,null).commit();
    }
}
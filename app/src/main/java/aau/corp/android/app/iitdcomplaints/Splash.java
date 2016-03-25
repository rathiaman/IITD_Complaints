package aau.corp.android.app.iitdcomplaints;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import static aau.corp.android.app.iitdcomplaints.R.anim.abc_fade_out;

/**
 * Created by Aman Rathi on 18-02-2016.
 */

///////////////////////////////////
// Creates a splash screen on launch for some time
///////////////////////////////////

public class Splash extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);

        ///////////////////////////////////
        // calls the imgae displayed in splash screen
        ///////////////////////////////////
        final ImageView splashscreen = (ImageView)findViewById(R.id.splash_screen_image);

        ///////////////////////////////////
        // animation involved in splash screen
        // duration animation
        ///////////////////////////////////
        final Animation animation = AnimationUtils.loadAnimation(getBaseContext(), R.anim.duration);

        ///////////////////////////////////
        // fade out animation
        ///////////////////////////////////
        final Animation animation2 = AnimationUtils.loadAnimation(getBaseContext(), abc_fade_out);

        ///////////////////////////////////
        // Function call for the launch and animation of screen
        ///////////////////////////////////
        splashscreen.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            ///////////////////////////////////
            // defines the command to be executed after animation end
            ///////////////////////////////////
            @Override
            public void onAnimationEnd(Animation animation) {

                ///////////////////////////////////
                // animation starts
                ///////////////////////////////////
                splashscreen.startAnimation(animation2);
                ///////////////////////////////////
                // animation finishes
                ///////////////////////////////////
                finish();

                ///////////////////////////////////
                // Intent call to directly go to the login screen
                ///////////////////////////////////
                Intent i = new Intent(Splash.this, LoginScreen.class);
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

    }
}

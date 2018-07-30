package septiandp.kamusistilahmusik;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;

public class SplashTimer extends CountDownTimer {
    public Context cont;
    public Activity acti;

    public SplashTimer(Context cont, Activity acti, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);

        this.cont = cont;
        this.acti = acti;
    }

    @Override
    public void onTick(long l) {

    }

    @Override
    public void onFinish() {
        cont.startActivity(new Intent(cont, MainActivity.class));
        acti.finish();
    }
}

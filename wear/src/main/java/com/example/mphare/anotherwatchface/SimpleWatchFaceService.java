package com.example.mphare.anotherwatchface;

import android.os.Looper;
import android.support.wearable.watchface.CanvasWatchFaceService;
import android.support.wearable.watchface.WatchFaceStyle;
import android.view.SurfaceHolder;

import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;

/**
 * Created by mphare on 5/16/2015.
 */
public class SimpleWatchFaceService extends CanvasWatchFaceService
{

  @Override
  public Engine onCreateEngine()
  {
    return new SimpleEngine();
  }

  private class SimpleEngine extends CanvasWatchFaceService.Engine
  {
    private static final long TICK_PERIOD_MILLIS = TimeUnit.SECONDS.toMillis(1);
    private Handler timeTick;

    @Override
    public void onCreate(SurfaceHolder holder)
    {
      super.onCreate(holder);

      setWatchFaceStyle(new WatchFaceStyle.Builder(SimpleWatchFaceService.this)
                            .setCardPeekMode(WatchFaceStyle.PEEK_MODE_SHORT)
                            .setAmbientPeekMode(WatchFaceStyle.AMBIENT_PEEK_MODE_HIDDEN)
                            .setBackgroundVisibility(WatchFaceStyle.BACKGROUND_VISIBILITY_INTERRUPTIVE)
                            .setShowSystemUiTime(false)
                            .build());

      timeTick = new Handler(Looper.myLooper());
      startTimerIfNecessary();

    }

    private void startTimerIfNecessary()
    {

      timeTick.removeCallback(timeRunnable);

      if (isVisible() && !isInAmbientMode())
      {
        timeTick.post(timeRunnable);
      }

    }

  }

}

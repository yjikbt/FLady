package rasche.flady;

import android.app.Activity;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.Random;


public class LadyActivity extends Activity implements View.OnTouchListener {

    private ImageView mTits;
    private ImageView mPussy;
    private ViewGroup mRootLayout;
    private RelativeLayout.LayoutParams mlayoutParams;
    private Random mRandom;
    private int mFruitWidth = 300;
    private int mFruitHeight = 230;
    private int _xDelta;
    private int _yDelta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lady);

        mRootLayout = (ViewGroup) findViewById(R.id.root);
        mTits = (ImageView) findViewById(R.id.tits);
        mPussy = (ImageView) findViewById(R.id.pussy);
        mRandom = new Random();

        mTits.setOnTouchListener(this);
        mPussy.setOnTouchListener(this);
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        final int X = (int) event.getRawX();
        final int Y = (int) event.getRawY();
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                mlayoutParams = new RelativeLayout.LayoutParams(
                        mFruitWidth,
                        mFruitHeight);

                switch((int) view.getY()) {
                    case 246:
                        mlayoutParams.topMargin = 225;
                        mlayoutParams.leftMargin = 204;
                        generateFruitOnTits();
                        break;
                    case 521:
                        mlayoutParams.topMargin = 500;
                        mlayoutParams.leftMargin = 235;
                        generateFruitOnTits();
                        break;
                    default:
                        break;
                }

                RelativeLayout.LayoutParams mlayoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                _xDelta = X - mlayoutParams.leftMargin;
                _yDelta = Y - mlayoutParams.topMargin;
                break;
            case MotionEvent.ACTION_UP:
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                break;
            case MotionEvent.ACTION_POINTER_UP:
                break;
            case MotionEvent.ACTION_MOVE:
                mlayoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                mlayoutParams.leftMargin = X - _xDelta;
                mlayoutParams.topMargin = Y - _yDelta;
                mlayoutParams.rightMargin = -250;
                mlayoutParams.bottomMargin = -250;
                view.setLayoutParams(mlayoutParams);
                break;
        }
        mRootLayout.invalidate();
        return true;
    }

    public void generateFruitOnTits() {
        ImageView fruit = new ImageView(getApplicationContext());

        TypedArray typedArray = getResources().obtainTypedArray(R.array.fruits);
        int n = mRandom.nextInt(4) + 1;
        Drawable drawable = typedArray.getDrawable(n);
        fruit.setImageDrawable(drawable);

        fruit.setLayoutParams(mlayoutParams);
        fruit.setOnTouchListener(this);

        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.root);
        relativeLayout.addView(fruit);
    }
}

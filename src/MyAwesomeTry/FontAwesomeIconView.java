package MyAwesomeTry;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;
import com.example.PagerSlidingTabStrip.R;

/*
    1. 矢量图形不是高宽相等的。
    2. 固定高宽，在用 center 可能比较好。
    3. 还是靠微调。
 */
public class FontAwesomeIconView extends TextView {

    // =========== 静态属性和方法 ==========

    private static String TAG = "FontAwesomeIconView";

    private static Typeface FONT;

    private static void readFont(Context context) {

        if (FONT == null) {
            try {
                FONT = Typeface.createFromAsset(context.getAssets(), "fontawesome-webfont.ttf");
            } catch (Exception e) {
                Log.e(TAG, "can not find fontawesome-webfont.ttf in asset");
            }
        }
    }

    // =========== 构造函数 ==========

    public FontAwesomeIconView(Context context) {

        super(context);
        initialize(context);
    }

    public FontAwesomeIconView(Context context, AttributeSet attrs) {

        super(context, attrs);
        initialize(context, attrs);
    }

    public FontAwesomeIconView(Context context, AttributeSet attrs, int defStyle) {

        super(context, attrs, defStyle);
        initialize(context, attrs);
    }

    // =========== 私有函数 ==========

    private void initialize(Context context) {

        readFont(context);
        setTypeface(FONT);
    }

    private void initialize(Context context, AttributeSet attrs) {

        initialize(context);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FontAwesomeIconView);

        String icon = typedArray.getString(R.styleable.FontAwesomeIconView_icon);
        setIcon(icon);

        typedArray.recycle();
    }

    // =========== 共有函数 ==========

    // such as: "fa-user"
    public void setIcon(String icon) {

        String iconText = FontAwesomeManager.getIconText(icon);
        setText(iconText);
    }

}

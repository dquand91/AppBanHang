package luongduongquan.com.demoappbanhang.CustomView;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;

import luongduongquan.com.demoappbanhang.R;

public class ClearEditTextCustom extends android.support.v7.widget.AppCompatEditText {

	Drawable cross_X;
	Drawable cross_X_disable;
	boolean visible = false;

	Drawable myDrawable;

	public ClearEditTextCustom(Context context) {
		super(context);
		initialize();
	}

	public ClearEditTextCustom(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize();
	}

	public ClearEditTextCustom(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initialize();
	}

	private void initialize(){
		cross_X = ContextCompat.getDrawable(getContext(), R.drawable.ic_clear_black_24dp).mutate();

		// mình sẽ gán 1 drawable rỗng cho phần disable cross_X. (hide cross_X)
		cross_X_disable = ContextCompat.getDrawable(getContext(),android.R.drawable.screen_background_light_transparent).mutate();

		setup();
	}

	private void setup() {
		// chỗ này tương đương với dòng android:inputType="text"  ở bên file layout.
		setInputType(InputType.TYPE_CLASS_TEXT);

		Drawable[] drawables = getCompoundDrawables(); // để tạo ra 1 list Drawable ảo

		// nếu visible=true sẽ show dấu X, nếu visible=false hide dấu X đi
		myDrawable = visible ? cross_X : cross_X_disable;

		// tương tự như drawable  left, top, right, bottom bên file layout
		// Ở đây sẽ là drawableRight (dấu X sẽ hiển thị ở bên phải của edittext)
		setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],myDrawable,drawables[3]);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// Khi bấm dấu X sẽ xóa đi cái Editext
		if(event.getAction() == MotionEvent.ACTION_UP && event.getX() >= (getRight() - myDrawable.getBounds().width())){
			visible = !visible;
			setText("");
		}
		return super.onTouchEvent(event);
	}

	@Override
	protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
		super.onTextChanged(text, start, lengthBefore, lengthAfter);
		if(text.length() == 0){
			visible = false;
			setup();
		}else{
			visible = true;
			setup();
		}
	}
}

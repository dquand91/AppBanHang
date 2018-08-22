package luongduongquan.com.demoappbanhang.CustomView;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.util.AttributeSet;
import android.view.MotionEvent;

import luongduongquan.com.demoappbanhang.R;

public class PasswordEditText extends android.support.v7.widget.AppCompatEditText {

	Drawable eye;  // hình con mắt bình thường
	Drawable eyeStrike;  // hình con mắt có dấu gạch chéo
	boolean visible = false;   // check xem hiển thị con mắt nào. (false-hiển thị eye) - (true-hiển thị eyestrike)
	boolean useStrike = false; // có sử dụng hình con mắt hay không

	int ALPHA = (int) (255 * .70f); // độ mờ là 50%

	Drawable myDrawable; // để quản lý cái con mắt trên Edittext


	public PasswordEditText(Context context) {
		super(context);
		initialize(null);
	}

	public PasswordEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		initialize(attrs);
	}

	public PasswordEditText(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		initialize(attrs);
	}

	private void initialize(AttributeSet attrs){
		// attrs do bên file layout truyền vào thông qua "app:". Ví dụ: app:useStrike="true"
		// R.styleable.PasswordEditText đã thêm vào trong file "styles.xml" như bên dưới:
		//		<declare-styleable name="PasswordEditText">
		//        	<attr name="useStrike" format="boolean"></attr> // cái này là tên và kiểu của thuộc tính do mình đặt. Ở đây thuộc tính tên là "useStrike" và kiểu "boolean"
		//    	</declare-styleable>

		if(attrs != null){
			// để lấy ra được cái "useStrike" đã thêm vào bên layout
			TypedArray array = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.PasswordEditText, 0,0);

			// Xong gán cái "useStrike" vào biến để xử lý
			this.useStrike = array.getBoolean(R.styleable.PasswordEditText_useStrike, false);
		}

		// gán hình cho con mắt
		eye = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_black_24dp).mutate();

		// gán hình cho con mắt có dấu gạch
		eyeStrike = ContextCompat.getDrawable(getContext(), R.drawable.ic_visibility_off_black_24dp).mutate();

		setup();
	}


	private void setup(){
		// chỗ này tương đương với dòng android:inputType="text|textVisiblePassword"  ở bên file layout.
		// Nếu như biến visible=true => hiển thị password.
		setInputType(InputType.TYPE_CLASS_TEXT | (visible ? InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD : InputType.TYPE_TEXT_VARIATION_PASSWORD));

		Drawable[] drawables = getCompoundDrawables(); // để tạo ra 1 list Drawable ảo

		// nếu visible=true sẽ show hình còn mắt, nếu visible=false sẽ show hình con mắt có dấu gạch.
		myDrawable = visible ? eye : eyeStrike;
		myDrawable.setAlpha(ALPHA); // đặt độ mờ cho cái con mắt

		// Nếu bên layout có dùng useStrike => thì show con mắt ra, không thì ko show con mắt ra
		if(useStrike){
			// tương tự như drawable  left, top, right, bottom bên file layout
			// Ở đây sẽ là drawableRight (con mắt sẽ hiển thị ở bên phải của edittext)
			setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],myDrawable,drawables[3]);
		} else {
			setCompoundDrawablesWithIntrinsicBounds(drawables[0],drawables[1],drawables[2],drawables[3]);
		}
	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// để bắt sự kiện khi touch vào con mắt

		// Giải thích:  event.getX() >= (getRight() - myDrawable.getBounds().width())
		// Nếu tọa độ X của điểm mà user đang chạm vào  >= (tọa độ X bên phải của EditText này - tọa độ X của điểm bắt đầu của cái Drawable con mắt)
		// Tóm lại là bắt được sự kiên người dùng click vào hình con mắt.
		if(event.getAction() == MotionEvent.ACTION_UP && event.getX() >= (getRight() - myDrawable.getBounds().width()) ){
			visible = !visible;
			setup();
			invalidate(); // để vẽ lại cái Edittext
		}
		return super.onTouchEvent(event);

		// nếu ở đây return true => sẽ chỉ xử lý sự kiện click vào hình con mắt và ko xử lý sự kiện click vào Edittext cho user nhập chữ.
		// return super.onTouchEvent(event) => sẽ kế thừa lại thằng cha và cho phép xử lý việc cho user nhập chữ.
	}
}

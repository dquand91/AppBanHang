package luongduongquan.com.demoappbanhang.View.DangNhap.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

import luongduongquan.com.demoappbanhang.R;
import luongduongquan.com.demoappbanhang.View.TrangChu.MainActivity;

public class FragmentDangNhap extends Fragment implements View.OnClickListener{

	Button btnDangNhapFacebook;
	CallbackManager callbackManager;

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.layout_dangnhap_fragment, container, false);

		FacebookSdk.sdkInitialize(getContext().getApplicationContext());
		callbackManager = CallbackManager.Factory.create();
		LoginManager.getInstance().registerCallback(callbackManager,
				new FacebookCallback<LoginResult>() {
					@Override
					public void onSuccess(LoginResult loginResult) {
						Log.d("QUAN123", "FacebookCallback onSuccess: ");

						Intent intentToTrangChu = new Intent(getActivity(), MainActivity.class);
						startActivity(intentToTrangChu);

					}

					@Override
					public void onCancel() {
						Log.d("QUAN123", "FacebookCallback onCancel: ");
					}

					@Override
					public void onError(FacebookException exception) {
						Log.d("QUAN123", "FacebookCallback onError: ");
					}
				});


		btnDangNhapFacebook = view.findViewById(R.id.btnDangNhapFacebook);
		btnDangNhapFacebook.setOnClickListener(this);


		return view;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		callbackManager.onActivityResult(requestCode, resultCode, data);
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onClick(View view) {
		switch (view.getId()){
			case R.id.btnDangNhapFacebook:
				LoginManager.getInstance().logInWithReadPermissions(FragmentDangNhap.this, Arrays.asList("public_profile"));
				break;
			case R.id.btnDangNhapGoogle:
				break;
		}
	}
}

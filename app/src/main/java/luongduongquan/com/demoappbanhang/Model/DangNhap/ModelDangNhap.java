package luongduongquan.com.demoappbanhang.Model.DangNhap;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;

public class ModelDangNhap {

	AccessToken accessToken;
	AccessTokenTracker accessTokenTracker;

	public AccessToken LayTokenFacebookHienTai(){

		accessTokenTracker = new AccessTokenTracker() {
			@Override
			protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
				accessToken = currentAccessToken;
			}
		};

		accessToken = AccessToken.getCurrentAccessToken();

		return accessToken;


	}


	public void huyTokenTrackerFacebook(){
		accessTokenTracker.stopTracking();
	}

}

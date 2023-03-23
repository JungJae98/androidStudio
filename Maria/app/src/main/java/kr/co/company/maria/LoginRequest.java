package kr.co.company.maria;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest{

    //서버설정
   final static private String URL = "http://192.168.24.148/Login.php";
   private Map<String, String> map;

    // 내가 원하는대로 수정이 필요요
   public LoginRequest(String userID, String userPassword, Response.Listener<String> listener){
       super(Method.POST, URL, listener, null);
       Log.v("실행",userID);
       Log.v("실행",userPassword);
       map = new HashMap<>();
       //키값, 실제 넣을 값 나는 두개만 쓰면 된다.
       map.put("userID", userID);
       map.put("userPassword",userPassword);

   }


    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return map;
    }
}

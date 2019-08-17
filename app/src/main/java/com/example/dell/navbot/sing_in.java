package com.example.dell.navbot;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.BindView;
import butterknife.ButterKnife;
public class sing_in extends AppCompatActivity {
    private static final String TAG = "singin";
    @BindView(R.id.input_name) EditText _nameText;
    @BindView(R.id.input_email) EditText _emailText;
    @BindView(R.id.input_mobile) EditText _mobileText;
    @BindView(R.id.input_password) EditText _passwordText;
    @BindView(R.id.input_reEnterPassword) EditText _reEnterPasswordText;
    @BindView(R.id.btn_signup) Button _signupButton;
    @BindView(R.id.link_login) TextView _loginLink;
    @BindView(R.id._spinnertype)  Spinner _spinner_type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.singin);

              ButterKnife.bind(this);

            _signupButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int t = signup();
                    Intent intent = new Intent(getApplicationContext(), cv_woker.class);
                    intent.putExtra("id_email",String.valueOf(t));
                    startActivityForResult(intent, 1);
                    finish();
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
            });

            _loginLink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Finish the registration screen and return to the Login activity
                    Intent intent = new Intent(getApplicationContext(),login.class);
                    startActivity(intent);
                    finish();
                    overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
            });
        }

        public int signup() {
            Log.d(TAG, "Signup");

         /*   if (!validate()) {
                onSignupFailed();
                return 0;
            }
*/
            _signupButton.setEnabled(false);

            final ProgressDialog progressDialog = new ProgressDialog(sing_in.this,
                    R.style.AppTheme_Dark_Dialog);
            progressDialog.setIndeterminate(true);
            progressDialog.setMessage("Creating Account...");
            progressDialog.show();
            final int[] Id_result = {0};
            final String name = _nameText.getText().toString();
            final String email = _emailText.getText().toString();
            final String mobile = _mobileText.getText().toString();
            final String password = _passwordText.getText().toString();
            final String reEnterPassword = _reEnterPasswordText.getText().toString();
            final String value_spinner = _spinner_type.getSelectedItem().toString();
            final int spValue[] = {0};
            if (!password.equals(reEnterPassword)) {
                Toast.makeText(getApplicationContext(), "Password it's  not Correct", Toast.LENGTH_SHORT).show();
            }
            else{

            if (value_spinner.equals("COMPANY"))
                spValue[0] = 0;
            else
                spValue[0] = 1;
            if (spValue[0] == 1) {

                ///  insert new worker
                String Url = "http://my-app-ammar.000webhostapp.com/insertworker.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {


                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "something  is error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();

                        params.put("name", name);
                        params.put("mobile", mobile);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);


                // get id for new worker
                final int[] id_worker = {0};
                String Urlid = "http://my-app-ammar.000webhostapp.com/getcount.php";
                StringRequest stringRequestid = new StringRequest(Request.Method.POST, Urlid, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("response");
                            if (!success.equals("ER")) {
                                id_worker[0] = Integer.parseInt(success);

                            }


                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "something  is error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("mobile", mobile);
                        return params;
                    }
                };
                RequestQueue requestQueueid = Volley.newRequestQueue(getApplicationContext());
                requestQueueid.add(stringRequestid);


                /////   insert Email

                String Urlemail = "http://my-app-ammar.000webhostapp.com/insertemail.php";
                StringRequest stringRequestEmail = new StringRequest(Request.Method.POST, Urlemail, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("message");
                            if (!success.equals("Error")) {
                                Toast.makeText(getApplicationContext(), "Account Worker successfully created ", Toast.LENGTH_LONG).show();

                            }


                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "something  is error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("email", email);
                        params.put("id_worker", String.valueOf(id_worker[0]));
                        params.put("password", password);
                        params.put("phone", mobile);
                        params.put("type", String.valueOf(spValue[0]));
                        return params;
                    }
                };
                RequestQueue requestQueueemail = Volley.newRequestQueue(getApplicationContext());
                requestQueueemail.add(stringRequestEmail);

                //  get id_email and return this id
                String UrlgetId = "http://my-app-ammar.000webhostapp.com/login.php";

                StringRequest stringRequestIdGet = new StringRequest(Request.Method.POST, UrlgetId, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String  success = jsonObject.getString("response");
                            if(!success.equals("ER"))
                            {
                                       Id_result[0] = Integer.parseInt(success);


                            }else {Toast.makeText(getApplicationContext(),"The Information Not Correct",Toast.LENGTH_SHORT).show();}

                        }catch (Exception e)
                        {
                            Toast.makeText(getApplicationContext(),"something  is error",Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();

                        params.put("email",email);
                        params.put("password",password);
                        return params;
                    }
                };
                RequestQueue requestQueueget = Volley.newRequestQueue(getApplicationContext());
                requestQueueget.add(stringRequestIdGet);


            } else   /// company
            {
                String Url = "http://my-app-ammar.000webhostapp.com/insertcompany.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {


                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "something  is error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();

                        params.put("name", name);
                        params.put("mobile", mobile);
                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);


                // get id for new company
                final int[] id_company = {0};
                String Urlid = "http://my-app-ammar.000webhostapp.com/getidcompany.php";
                StringRequest stringRequestid = new StringRequest(Request.Method.POST, Urlid, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("response");
                            if (!success.equals("ER")) {
                                id_company[0] = Integer.parseInt(success);

                            }


                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "something  is error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("mobile", mobile);
                        return params;
                    }
                };
                RequestQueue requestQueueid = Volley.newRequestQueue(getApplicationContext());
                requestQueueid.add(stringRequestid);


                /////   insert Email for company

                String Urlemail = "http://my-app-ammar.000webhostapp.com/insertemailcompany.php";
                StringRequest stringRequestEmail = new StringRequest(Request.Method.POST, Urlemail, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("message");
                            if (!success.equals("Error")) {
                                Toast.makeText(getApplicationContext(), "Account Company successfully created ", Toast.LENGTH_LONG).show();

                            }


                        } catch (Exception e) {
                            Toast.makeText(getApplicationContext(), "something  is error", Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("email", email);
                        params.put("id_company", String.valueOf(id_company[0]));
                        params.put("password", password);
                        params.put("phone", mobile);
                        params.put("type", String.valueOf(spValue[0]));
                        return params;
                    }
                };
                RequestQueue requestQueueemail = Volley.newRequestQueue(getApplicationContext());
                requestQueueemail.add(stringRequestEmail);

                //  get id_email and return this id
                String UrlgetId = "http://my-app-ammar.000webhostapp.com/login.php";

                StringRequest stringRequestIdGet = new StringRequest(Request.Method.POST, UrlgetId, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String  success = jsonObject.getString("response");
                            if(!success.equals("ER"))
                            {
                                Id_result[0] = Integer.parseInt(success);


                            }else {Toast.makeText(getApplicationContext(),"The Information Not Correct",Toast.LENGTH_SHORT).show();}

                        }catch (Exception e)
                        {
                            Toast.makeText(getApplicationContext(),"something  is error",Toast.LENGTH_LONG).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_LONG).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String,String> params = new HashMap<>();

                        params.put("email",email);
                        params.put("password",password);
                        return params;
                    }
                };
                RequestQueue requestQueueget = Volley.newRequestQueue(getApplicationContext());
                requestQueueget.add(stringRequestIdGet);


            }

            // TODO: Implement your own signup logic here.

            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            // On complete call either onSignupSuccess or onSignupFailed
                            // depending on success
                            onSignupSuccess();
                            // onSignupFailed();
                            progressDialog.dismiss();
                        }
                    }, 3000);
        }

    return Id_result[0];
    }


        public void onSignupSuccess() {
            _signupButton.setEnabled(true);
            setResult(RESULT_OK, null);
            finish();
        }

        public void onSignupFailed() {
            Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

            _signupButton.setEnabled(true);
        }

        public boolean validate() {
            boolean valid = true;

            String name = _nameText.getText().toString();

            String email = _emailText.getText().toString();
            String mobile = _mobileText.getText().toString();
            String password = _passwordText.getText().toString();
            String reEnterPassword = _reEnterPasswordText.getText().toString();

            if (name.isEmpty() || name.length() < 3) {
                _nameText.setError("at least 3 characters");
                valid = false;
            } else {
                _nameText.setError(null);
            }




            if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                _emailText.setError("enter a valid email address");
                valid = false;
            } else {
                _emailText.setError(null);
            }

            if (mobile.isEmpty() || mobile.length()!=10) {
                _mobileText.setError("Enter Valid Mobile Number");
                valid = false;
            } else {
                _mobileText.setError(null);
            }

            if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
                _passwordText.setError("between 4 and 10 alphanumeric characters");
                valid = false;
            } else {
                _passwordText.setError(null);
            }

            if (reEnterPassword.isEmpty() || reEnterPassword.length() < 4 || reEnterPassword.length() > 10 || !(reEnterPassword.equals(password))) {
                _reEnterPasswordText.setError("Password Do not match");
                valid = false;
            } else {
                _reEnterPasswordText.setError(null);
            }

            return valid;
        }
}
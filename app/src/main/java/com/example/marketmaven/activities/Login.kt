package com.example.marketmaven.activities

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.marketmaven.R
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class Login : AppCompatActivity() {

    private lateinit var userName : TextInputLayout
    private lateinit var password : TextInputLayout

    private lateinit var signup :Button
    private lateinit var forgotpwd : Button
    private lateinit var logIn : Button

    private lateinit var dbRef : DatabaseReference
    private lateinit var checkUser : Query


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login)

        userName = findViewById(R.id.login_userName)
        password = findViewById(R.id.login_password)

        signup = findViewById(R.id.btn_signup)
        forgotpwd = findViewById(R.id.btn_forgot)
        logIn = findViewById(R.id.btn_login)

        signup.setOnClickListener {
            val intent = Intent(applicationContext, Register::class.java)
            startActivity(intent)

            Toast.makeText(this, "Register", Toast.LENGTH_SHORT).show()
        }

        forgotpwd.setOnClickListener {
            val intent = Intent(applicationContext, ForgotPassword::class.java)
            startActivity(intent)

            Toast.makeText(this, "Reset Password", Toast.LENGTH_SHORT).show()
        }

        logIn.setOnClickListener{
            loginUser()
        }

    }

    private fun validateUsername() : Boolean{
        var value = userName.editText?.text.toString()

        if (value.isEmpty()){
            userName.error = "Field cannot be Empty"
            return false
        }else{
            userName.error = null
            userName.isErrorEnabled = false
            return true
        }
    }
    private fun validatePassword() : Boolean{
        var value = password.editText?.text.toString()
        if(value.isEmpty()){
            password.error = "Field cannot be Empty"
            return false
        }else{
            password.error = null
            password.isErrorEnabled = false
            return true
        }
    }

    private fun loginUser(){
        if (!validateUsername() || !validatePassword()){
            return
        }else{
            isUser()
        }
    }

    private fun isUser() {

        var userEnterdUsername = userName.editText?.text.toString()
        var userEnterdPassword = password.editText?.text.toString()

        dbRef = FirebaseDatabase.getInstance().getReference("users")

        checkUser = dbRef.orderByChild("userName").equalTo(userEnterdUsername)
        //Log.d("testTag", "check user: $checkUser")

        checkUser.addListenerForSingleValueEvent(object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    userName.error = null
                    userName.isErrorEnabled = false

                    var pwdFromDB = snapshot.child(userEnterdUsername).child("password").getValue().toString()
                    //Log.d("testTag", "user name: $userEnterdUsername")
                    //Log.d("testTag1", "Password from DB: $pwdFromDB")
                    //Log.d("testTag2", "Password from user: $userEnterdPassword")


                    if (pwdFromDB.equals(userEnterdPassword)) {
                        var nameFromDB = snapshot.child(userEnterdUsername).child("name").getValue().toString()
                        var addressFromDB = snapshot.child(userEnterdUsername).child("address").getValue().toString()
                        var phoneFromDB = snapshot.child(userEnterdUsername).child("phone").getValue().toString()
                        var userNameFromDB = snapshot.child(userEnterdUsername).child("userName").getValue().toString()
                        var passwordFromDB = snapshot.child(userEnterdUsername).child("password").getValue().toString()
                        var TypeFromDB = snapshot.child(userEnterdUsername).child("userType").getValue().toString()
                        var intent = Intent(applicationContext, HomePage::class.java)

                        intent.putExtra("name",nameFromDB)
                        intent.putExtra("address", addressFromDB)
                        intent.putExtra("phone", phoneFromDB)
                        intent.putExtra("userName", userNameFromDB)
                        intent.putExtra("password", passwordFromDB)
                        intent.putExtra("userType",TypeFromDB)

                        startActivity(intent)
                    }else{
                        password.error = "Wrong Password"
                        password.requestFocus()
                    }
                }else{
                    userName.error = "No Such User Exist"
                    userName.requestFocus()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

}
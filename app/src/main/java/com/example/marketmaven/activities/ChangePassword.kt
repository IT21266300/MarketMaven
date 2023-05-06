package com.example.marketmaven.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.marketmaven.R
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class ChangePassword : AppCompatActivity() {

    private lateinit var password1 : TextInputLayout
    private lateinit var password2 : TextInputLayout
    private lateinit var btn_changePwd : Button
    private lateinit var btn_login : Button
    private lateinit var user_userName : String

    /*Database Reference*/
    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_password)

        dbRef = FirebaseDatabase.getInstance().getReference("users")

        password1 = findViewById(R.id.change_password1)
        password2 = findViewById(R.id.change_password2)
        btn_changePwd = findViewById(R.id.change_btn_submit)
        btn_login = findViewById(R.id.change_btn_login)

        btn_login.setOnClickListener {
            val intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)

            Toast.makeText(this, "LogIN", Toast.LENGTH_SHORT).show()
        }

    }

    fun setPassword(view: View) {
        if (!validatePassword1() || !validatePassword2()){
            return
        }else{
            changePassword()
        }
    }

    private fun changePassword() {
        var intent:Intent = getIntent()
        user_userName = intent.getStringExtra("userName").toString()

        val pwd1 = password1.editText?.text.toString()
        val pwd2 = password2.editText?.text.toString()

        if (pwd1!=pwd2){
            password2.error = "Password Does Not Match"
            return
        }else{
            dbRef.child(user_userName).child("password").setValue(pwd2)
            Toast.makeText(this, "Password Changed", Toast.LENGTH_SHORT).show()
        }

    }

    private fun validatePassword1(): Boolean {
        var value = password1.editText?.text.toString()

        if (value.isEmpty()){
            password1.error = "Field cannot be Empty"
            return false
        }else{
            password1.error = null
            password1.isErrorEnabled = false
            return true
        }
    }
    private fun validatePassword2(): Boolean {
        var value = password2.editText?.text.toString()

        if (value.isEmpty()){
            password2.error = "Field cannot be Empty"
            return false
        }else{
            password2.error = null
            password2.isErrorEnabled = false
            return true
        }
    }


}
package com.example.marketmaven.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.marketmaven.R
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener

class ForgotPassword : AppCompatActivity() {

    private lateinit var userName : TextInputLayout
    private lateinit var password1 : TextInputLayout
    private lateinit var password2 : TextInputLayout

    private lateinit var login : Button
    private lateinit var submit : Button

    private lateinit var dbRef : DatabaseReference
    private lateinit var checkUser : Query

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        userName = findViewById(R.id.reset_userName)
        password1 = findViewById(R.id.reset_password1)
        password2 = findViewById(R.id.reset_password2)


        login = findViewById(R.id.reset_to_home)
        submit = findViewById(R.id.reset_editPassword)

        login.setOnClickListener {
            val intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)

            Toast.makeText(this, "LogIn", Toast.LENGTH_SHORT).show()
        }
        submit.setOnClickListener{
            checkValidation()
        }

    }

    private fun checkValidation() {
        if (!validateUsername() || !validatePassword1() || !validatePassword2()){
            return
        }else{
            setPassword()
        }
    }

    private fun setPassword() {
        var userEnterdUsername = userName.editText?.text.toString()
        var userEnterdPassword1 = password1.editText?.text.toString()
        var userEnterdPassword2 = password2.editText?.text.toString()

        dbRef = FirebaseDatabase.getInstance().getReference("users")
        checkUser = dbRef.orderByChild("userName").equalTo(userEnterdUsername)

        checkUser.addListenerForSingleValueEvent(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    userName.error = null
                    userName.isErrorEnabled = false

                    if(userEnterdPassword1==userEnterdPassword2){
                        dbRef.child(userEnterdUsername).child("password").setValue(userEnterdPassword2)
                        Toast.makeText(this@ForgotPassword, "Password Changed", Toast.LENGTH_SHORT).show()
                    }else{
                        password2.error = "Password Does Not Match"
                        password2.requestFocus()
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

    private fun validateUsername(): Boolean {
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

    private fun validatePassword2(): Boolean {
        var value = password1.editText?.text.toString()
        if(value.isEmpty()){
            password1.error = "Field cannot be Empty"
            return false
        }else{
            password1.error = null
            password1.isErrorEnabled = false
            return true
        }
    }

    private fun validatePassword1(): Boolean {
        var value = password2.editText?.text.toString()
        if(value.isEmpty()){
            password2.error = "Field cannot be Empty"
            return false
        }else{
            password2.error = null
            password2.isErrorEnabled = false
            return true
        }
    }

}
package com.example.marketmaven.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.example.marketmaven.R
import com.example.marketmaven.model.userModel
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Register : AppCompatActivity() {

    private lateinit var Name : TextInputLayout
    private lateinit var address : TextInputLayout
    private lateinit var phone : TextInputLayout
    private lateinit var userName : TextInputLayout
    private lateinit var password : TextInputLayout
    private lateinit var radioGroup: RadioGroup
    private lateinit var type: RadioButton

    private lateinit var backBtn : Button
    private lateinit var regBtn : Button

    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        Name = findViewById(R.id.set_firstName)
        address = findViewById(R.id.set_address)
        phone = findViewById(R.id.set_Phone)
        userName = findViewById(R.id.set_Username)
        password = findViewById(R.id.set_password)
        radioGroup = findViewById(R.id.typeRadiogroup)

        regBtn = findViewById(R.id.btn_register)
        backBtn = findViewById(R.id.btn_login_bk)

        dbRef = FirebaseDatabase.getInstance().getReference("users")

        //save data in database
        regBtn.setOnClickListener{
            validateData()
        }

        backBtn.setOnClickListener {
            val intent = Intent(applicationContext, Login::class.java)
            startActivity(intent)
            Toast.makeText(this, "LogIn", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateData() {
        if (!validateName() || !validateAddress()|| !validatePhone() || !validateUserType()||!validateUsername()||!validatePassword()){
            return
        }else{
            saveUserData()
        }
    }


    private fun saveUserData(){

        val Name = Name.editText?.text.toString()
        val Address = address.editText?.text.toString()
        val Phone = phone.editText?.text.toString()
        val userName = userName.editText?.text.toString()
        val Password = password.editText?.text.toString()
        type = findViewById(radioGroup.checkedRadioButtonId)
        val userType = type.text.toString()

        val userId = dbRef.push().key!!

        val user = userModel(userId,Name,Address,Phone,userType,userName,Password)

        dbRef.child(userName).setValue(user).addOnCompleteListener{
            Toast.makeText(this,"Data Add Succesfully",Toast.LENGTH_LONG).show()
        }.addOnFailureListener { err->
            Toast.makeText(this,"Error ${err.message}",Toast.LENGTH_LONG).show()
        }

    }

    private fun validateName(): Boolean {
        var value = Name.editText?.text.toString()
        if(value.isEmpty()){
            Name.error = "Field cannot be Empty"
            return false
        }else{
            Name.error = null
            Name.isErrorEnabled = false
            return true
        }
    }

    private fun validatePassword(): Boolean {
        var value = password.editText?.text.toString()
        if(value.isEmpty()){
            password.error = "Field cannot be Empty"
            return false
        }else if (!value.matches(Regex("(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).+"))) {
            password.error = "Password should contain uppercase letters, lowercase letters, and numbers"
            return false
        }else{
            password.error = null
            password.isErrorEnabled = false
            return true
        }
    }

    private fun validateUsername(): Boolean {
        val value = userName.editText?.text.toString()
        if(value.isEmpty()){
            userName.error = "Field cannot be Empty"
            return false
        }else{
            userName.error = null
            userName.isErrorEnabled = false
            return true
        }
    }

    private fun validateUserType(): Boolean {
        if (radioGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Please Select Gender", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }
    }

    private fun validatePhone(): Boolean {
        var value = phone.editText?.text.toString()
        if(value.isEmpty()){
            phone.error = "Field cannot be Empty"
            return false
        }else if (value.length != 10) {
            phone.error = "Phone number should be 10 digits"
            return false
        } else if (value[0] != '0') {
            phone.error = "Phone number should start with 0"
            return false
        } else{
            phone.error = null
            phone.isErrorEnabled = false
            return true
        }
    }

    private fun validateAddress(): Boolean {
        var value = address.editText?.text.toString()
        if(value.isEmpty()){
            address.error = "Field cannot be Empty"
            return false
        }else{
            address.error = null
            address.isErrorEnabled = false
            return true
        }
    }

}
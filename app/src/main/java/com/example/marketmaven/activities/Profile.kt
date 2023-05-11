package com.example.marketmaven.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.marketmaven.R
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Profile : AppCompatActivity() {

    private lateinit var lableName : TextView
    private lateinit var lableType : TextView
    private lateinit var Name : TextInputLayout
    private lateinit var address : TextInputLayout
    private lateinit var phone : TextInputLayout
    private lateinit var userName : TextInputLayout
    //private lateinit var password : TextInputLayout

    private lateinit var user_name : String
    private lateinit var user_address : String
    private lateinit var user_phone : String
    private lateinit var user_userName : String
    private lateinit var user_type : String

    private lateinit var btn_editDetails :Button
    private lateinit var btn_changePassword :Button
    private lateinit var btn_deleteProfile :Button
    //private lateinit var btn_submit :Button

    /*Database Reference*/
    private lateinit var dbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        dbRef = FirebaseDatabase.getInstance().getReference("users")


        /*Text fields*/
        lableName = findViewById(R.id.lable_Name)
        lableType = findViewById(R.id.profile_lable_type)
        Name = findViewById(R.id.profile_Name)
        address = findViewById(R.id.profile_Address)
        phone = findViewById(R.id.profile_phone)
        userName = findViewById(R.id.profile_Username)

        /*Buttons*/
        btn_editDetails = findViewById(R.id.profile_btn_editDetails)
        btn_changePassword = findViewById(R.id.profile_btn_changePwd)
        btn_deleteProfile = findViewById(R.id.profile_btn_deleteProfile)

        /*Functions*/
        showAllData()

        btn_changePassword.setOnClickListener {

            var intent = Intent(applicationContext, ChangePassword::class.java)
            intent.putExtra("userName", user_userName)
            startActivity(intent)

            Toast.makeText(this, "Change Password", Toast.LENGTH_SHORT).show()
        }

        btn_deleteProfile.setOnClickListener {
            deleteProfile()
        }

    }

    private fun showAllData() {

        var intent:Intent = getIntent()
        user_name = intent.getStringExtra("name").toString()
        user_address = intent.getStringExtra("address").toString()
        user_phone = intent.getStringExtra("phone").toString()
        user_userName = intent.getStringExtra("userName").toString()
        user_type = intent.getStringExtra("userType").toString()

        Name.editText?.setText(user_name)
        address.editText?.setText(user_address)
        phone.editText?.setText(user_phone)
        userName.editText?.setText(user_userName)
        lableName.setText(user_name)
        lableType.setText(user_type)

    }

    fun update(view: View) {
        if(isNameChange() || isAddressChange() || isPhoneChange() || isUsernameChange()){
            Toast.makeText(this, "Data has been Updated", Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "Data is same and cannnot be updated", Toast.LENGTH_SHORT).show()

        }
    }

    /*Delete profile button*/
    private fun deleteProfile() {
        val delete = dbRef.child(user_userName).removeValue()

        delete.addOnSuccessListener {
            Toast.makeText(this, "User Deleted successfully", Toast.LENGTH_SHORT).show()

            var intent = Intent(applicationContext, Login::class.java)
            finish()
            startActivity(intent)
        }.addOnFailureListener { error->
            Toast.makeText(this, "Deleting Error ${error.message}", Toast.LENGTH_SHORT).show()
        }
    }

    /*Validate data change or not*/
    private fun isUsernameChange(): Boolean {
        if (!user_userName.equals(userName.editText?.text.toString())){
            dbRef.child(user_userName).child("userName").setValue(userName.editText?.text.toString())
            user_userName = userName.editText?.text.toString()


            return true
        }else{
            return false
        }
    }

    private fun isPhoneChange(): Boolean {
        if (!user_phone.equals(phone.editText?.text.toString())){
            dbRef.child(user_userName).child("phone").setValue(phone.editText?.text.toString())
            user_phone = phone.editText?.text.toString()
            return true
        }else{
            return false
        }
    }

    private fun isAddressChange(): Boolean {

        if (!user_address.equals(address.editText?.text.toString())){
            dbRef.child(user_userName).child("address").setValue(address.editText?.text.toString())
            user_address = address.editText?.text.toString()
            return true
        }else{
            return false
        }
    }

    private fun isNameChange(): Boolean {

        if (!user_name.equals(Name.editText?.text.toString())){
            dbRef.child(user_userName).child("name").setValue(Name.editText?.text.toString())
            user_name = Name.editText?.text.toString()
            return true
        }else{
            return false
        }
    }

    /*Validations*/
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
        }else{
            phone.error = null
            phone.isErrorEnabled = false
            return true
        }
    }
}
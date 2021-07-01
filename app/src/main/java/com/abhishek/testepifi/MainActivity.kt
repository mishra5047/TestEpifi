package com.abhishek.testepifi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.abhishek.testepifi.databinding.ActivityMainBinding
import es.dmoral.toasty.Toasty

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding;
    lateinit var model : ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityMainBinding.inflate(layoutInflater)
            model = ViewModelProvider(this).get(ViewModel::class.java)

        binding.lifecycleOwner = this
        binding.viewModel = model

        setContentView(binding.root);

        model.userMutableData.observe(this) {
            user : User? ->
            if (user != null) {

                if(user.isPanEmpty()){
                    showErrorText("Pan Number", binding.editTextPan)
                    return@observe;
                }
                if(user.isBirthDateEmpty()){
                    showErrorText("Date", binding.editTextDate)
                    return@observe;
                }
                if(user.isBirthMonthEmpty()){
                    showErrorText("Month", binding.editTextMonth)
                    return@observe;
                }
                if(user.isBirthYearEmpty()) {
                    showErrorText("Year", binding.editTextYear)
                    return@observe;
                }
                if(!user.isValidBirthDate()){
                    Toasty.error(this, "Invalid Date of Birth").show();
                    return@observe;
                }

                if(!user.isValidPanNumber()){
                    Toasty.error(this, "Invalid Pan Number").show();
                    return@observe;
                }
                // if reached here => data is valid and pan has to be validated
                // validate the pan number using the client

                Toasty.success(this, "Details submitted successfully").show();
                model.exitAppCode();
            }
        }
    }

    private fun showErrorText(message : String, editText : EditText){
        val error = "Can't be empty";
        Toasty.error(this, "$message $error", Toasty.LENGTH_SHORT).show();
        editText.error = "Empty"
        editText.requestFocus()
    }
}
package com.esprit.diceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.RadioGroup
import android.widget.SeekBar
import android.widget.Toast
import android.util.Patterns.EMAIL_ADDRESS as emailPattern

class MainActivity : AppCompatActivity() {
    private lateinit var email:EditText
    private lateinit var age:EditText
    private lateinit var skillAndroid:SeekBar
    private lateinit var gender:RadioGroup
    private lateinit var fullName:EditText
    private lateinit var skillIos:SeekBar
    private lateinit var skillFlutter:SeekBar
    private lateinit var okBtn:Button
    private lateinit var resetBtn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fullName=findViewById(R.id.nameField)
        email=findViewById(R.id.emailField)
        age=findViewById(R.id.ageField)
        gender=findViewById(R.id.radioGrp)
        skillAndroid=findViewById(R.id.seekAndroid)
        skillIos=findViewById(R.id.seekIos)
        skillFlutter=findViewById(R.id.seekFlutter)
        okBtn=findViewById(R.id.nextBtn)
        resetBtn=findViewById(R.id.resetBtn)

        okBtn.setOnClickListener {
            Log.i("Action","Clicked")
            if (fullName.text.isEmpty() || email.text.isEmpty() || age.text.isEmpty()) {
                Toast.makeText(this, "Please fill the form", Toast.LENGTH_SHORT).show()
            } else if (!email.text.matches(emailPattern.toRegex())) {
                Toast.makeText(this, "Please put a valid email", Toast.LENGTH_SHORT).show()
            } else {
                var listSeek =
                    listOf(skillAndroid.progress, skillIos.progress, skillFlutter.progress)
                Log.e("info",listSeek.toString())
                val listSkills = listOf("Android", "IOS", "Flutter")
                for ((value, skill) in listSeek.zip(listSkills)) {
                    if (value > 80) {
                        Toast.makeText(this, "Vous êtes excellent en $skill", Toast.LENGTH_LONG).show()
                        Log.e("akber","$skill-$value")
                    }
                }
                listSeek = listSeek.filter { skill -> skill > 30 }
                if (listSeek.isEmpty()) {
                    Toast.makeText(this, "Vous devez travailler vos skills", Toast.LENGTH_SHORT)
                        .show()
                } else
                    Toast.makeText(this, "Vous avez de bons skills !", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun reset(view: View) {
        val edits = listOf(fullName,email,age)
        val skills = listOf(skillAndroid,skillIos,skillFlutter)
        edits.forEach { editText -> editText.setText("") }
        skills.forEach { skill-> skill.progress = 0 }
    }

}
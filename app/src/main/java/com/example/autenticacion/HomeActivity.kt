package com.example.autenticacion

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth

enum class ProviderType {
    BASIC,
    GOOGLE,
    FACEBOOK
}

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        //esta parte presenta errores que hacen cierre de la aplicacion
        val bundle = intent.extras
        val email= bundle?.getString("email", null)
        val provider = bundle?.getString("provider", null)

        setup( email ?: "", provider ?: "")
        //guardado de datos

        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()

        }

    private fun setup(email: String, provider: String) {
        title = "inicio"

        val mimail = findViewById<TextView>(R.id.emailtextView)
        val miprovedor = findViewById<TextView>(R.id.providertextView)
        val myBtn = findViewById<Button>(R.id.singUnbutton)


        miprovedor.text = provider
       mimail.text = email

            myBtn.setOnClickListener {
                val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
                prefs.clear()
                prefs.apply()

                FirebaseAuth.getInstance().signOut()
            finish()
        }
    }
}
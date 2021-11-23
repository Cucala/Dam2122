package enriquez.lloret.damfirebase2122

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import enriquez.lloret.damfirebase2122.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        setTheme(R.style.Theme_DamFirebase2122)
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.signInBt.setOnClickListener {
            if (binding.signInEmail.text.toString().isNotEmpty() ||
                binding.signInPwd.text.toString().isNotEmpty()
            ) {
                signIn(
                    binding.signInEmail.text.toString(),
                    binding.signInPwd.text.toString()
                )
            }
        }

        binding.toRegisterBt.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }


    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if(currentUser != null){
            reload()
        }

    }

    private fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("  AccesoFirebase", "signInWithEmail:success")
                    val user = auth.currentUser
                    reload()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("AccesoFirebase", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun reload() {
        this.startActivity(
            Intent(this, MainActivity::class.java)
        )
    }
}
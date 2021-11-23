package enriquez.lloret.damfirebase2122

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import enriquez.lloret.damfirebase2122.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.registerBt.setOnClickListener {
            if (binding.registerEmail.text.toString().isNotEmpty() &&
                binding.registerPwd.text.toString().isNotEmpty() &&
                binding.registerConfirmPwd.text.toString().isNotEmpty() &&
                binding.registerPwd.text.toString()
                    .compareTo(binding.registerConfirmPwd.text.toString()) == 0
            ) {
                register(
                    binding.registerEmail.text.toString(),
                    binding.registerPwd.text.toString()
                )
            }
        }

        binding.backBt.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
    }

    private fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("Register", "createUserWithEmail:success")
                    //val user = auth.currentUser
                    //updateUI(user)
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Register", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }
}
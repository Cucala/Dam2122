package enriquez.lloret.damfirebase2122

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import enriquez.lloret.damfirebase2122.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth
        val user = auth.currentUser?.email!!.split("@")[0]

        binding.usernameTextView.text = user
        binding.signOutBt.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(this, SignInActivity::class.java))
        }

        binding.profileBt.setOnClickListener {
            startActivity(Intent(this, UserActivity::class.java))
        }

        binding.textView.setOnClickListener { startActivity(Intent(this, ListsActivity::class.java)) }
    }
}
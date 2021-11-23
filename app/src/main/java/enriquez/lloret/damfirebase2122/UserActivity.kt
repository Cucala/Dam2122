package enriquez.lloret.damfirebase2122

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import enriquez.lloret.damfirebase2122.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityUserBinding
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = FirebaseFirestore.getInstance()
        auth = Firebase.auth
        val user = auth.currentUser

        binding.emailTextView.text = user?.email.toString()
        binding.saveButton.setOnClickListener {
            db.collection("users").document(user?.email.toString()).set(
                hashMapOf(
                    "name" to binding.nameEditText.text.toString(),
                    "surname" to binding.surnameEditText.text.toString(),
                    "postalAddress" to binding.addressEditText.text.toString(),
                    "phone" to binding.phoneEditText.text.toString()
                )
            )
        }
        binding.getButton.setOnClickListener {
            getData(user?.email.toString())
        }
        binding.deleteButton.setOnClickListener {
            db.collection("users").document(user?.email.toString()).delete()
        }

    }

    override fun onStart() {
        super.onStart()
        getData(auth.currentUser?.email.toString())
    }

    private fun getData(email: String) {
        db.collection("users").document(email)
            .get().addOnSuccessListener {
                binding.nameEditText.setText(it.get("name") as String?)
                binding.surnameEditText.setText(it.get("surname") as String?)
                binding.addressEditText.setText(it.get("postalAddress") as String?)
                binding.phoneEditText.setText(it.get("phone") as String?)
            }
    }
}
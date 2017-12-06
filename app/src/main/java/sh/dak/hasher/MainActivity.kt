package sh.dak.hasher

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import se.simbio.encryption.Encryption

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        hash.setOnClickListener {
            val txtToEncrypt = password.text.toString()
            val key = key.text.toString()
            if(txtToEncrypt == "" || key == "") {
                toast("Please Enter the password and the key to hash!")
            } else {
                hashedpwd.setText(encryptText(txtToEncrypt, key))
            }
        }
        unhash.setOnClickListener {
            val txtToEncrypt = password.text.toString()
            val key = key.text.toString()
            if(txtToEncrypt == "" || key == "") {
                toast("Please Enter the hash and the key to unhash!")
            } else {
                unhashedpwd.setText(decryptText(txtToEncrypt, key))
            }
        }
    }
    fun encryptText(text: String, key: String) : String {
        val encryption = Encryption.getDefault(
                key,
                "Oh, yeah boy kotlin salts are suuuuuuusu sexyyyyyyy!",
                ByteArray(16))
        val encrypted = encryption.encryptOrNull(text)
        if(encrypted == null) {
            toast("Sorry, we were unable to hash it.")
            return "Unable to hash $text"
        }
        toast("Password has been Hashed!")
        return encrypted
    }
    fun decryptText(text: String, key: String) : String {
        val encryption = Encryption.getDefault(
                key,
                "Oh, yeah boy kotlin salts are suuuuuuusu sexyyyyyyy!",
                ByteArray(16))
        val decrypted = encryption.decryptOrNull(text)
        if(decrypted == null) {
            toast("Sorry, we were unable to unhash it.")
            return "Unable to unhash $text"
        }
        toast("Password has been UnHashed!")
        return decrypted
    }
}

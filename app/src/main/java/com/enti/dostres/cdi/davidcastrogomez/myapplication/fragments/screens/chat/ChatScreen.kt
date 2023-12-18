package com.enti.dostres.cdi.davidcastrogomez.myapplication.fragments.screens.chat

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.enti.dostres.cdi.davidcastrogomez.myapplication.MyApp
import com.enti.dostres.cdi.davidcastrogomez.myapplication.R
import com.enti.dostres.cdi.davidcastrogomez.myapplication.classes.firebase.FB
import com.enti.dostres.cdi.davidcastrogomez.myapplication.fragments.components.AppDrawer
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout

class ChatScreen : Fragment()  {

    lateinit var fragmentView: View

    val messagesTable by lazy { fragmentView.findViewById<RecyclerView>(R.id.chat_messages_recycler) }
    val messagesInput by lazy { fragmentView.findViewById<TextInputLayout>(R.id.chat_messages_input) }
    val messagesImage by lazy { fragmentView.findViewById<ImageView>(R.id.chat_messages_image_container) }

    val galleryLauncher = registerForActivityResult(ActivityResultContracts.GetContent()){galleryUri ->
        onImagePicked(galleryUri)
    }

    var currentImageUri: Uri? = null



    val messageAdapter by lazy { MessagesAdapter(messagesTable) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.screen_chat, container, false)
        return fragmentView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        messagesTable.layoutManager = LinearLayoutManager(MyApp.get())
        messagesTable.adapter = messageAdapter

        messagesInput.setStartIconOnClickListener(){
            openImagePicker()
        }

        messagesInput.setEndIconOnClickListener(){
            sendMessage()
        }
    }

    fun sendMessage(){

        if(!FB.auth.isLoginActive()){

            MyApp.get().closeKeyboard(messagesInput)
            AppDrawer.get().openLogin()

            return
        }

        val inputText = messagesInput.editText?.text.toString()
        val text = if(inputText != "") inputText else null

        val imageUri = currentImageUri ?: kotlin.run{
            messageAdapter.addMessage(text, null)
            messagesInput.editText?.text?.clear()
            return
        }

        FB.storage.saveImage(imageUri,
            onSuccess = {newImageUri ->
                messageAdapter.addMessage(text, newImageUri.toString())

                messagesInput.editText?.text?.clear()

                onImagePicked(null)

            },
            onFailure = {
                Snackbar.make(AppDrawer.get().fragmentView,
                    "ERrOR UPLOADING IMaGe",
                    Snackbar.LENGTH_LONG)
                .show()
            })


    }

    fun openImagePicker(){
        galleryLauncher.launch("image/*")
    }

    fun onImagePicked(galleryUri: Uri?){
        currentImageUri = galleryUri
        messagesImage.setImageURI(galleryUri)

        currentImageUri?.let {
            messagesImage.visibility = View.VISIBLE
        } ?: kotlin.run {
            messagesImage.visibility = View.GONE
        }
    }


}
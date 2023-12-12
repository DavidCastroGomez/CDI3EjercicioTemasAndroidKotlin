package com.enti.dostres.cdi.davidcastrogomez.myapplication.fragments.screens.chat

import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.enti.dostres.cdi.davidcastrogomez.myapplication.R
import com.enti.dostres.cdi.davidcastrogomez.myapplication.classes.firebase.FB
import com.enti.dostres.cdi.davidcastrogomez.myapplication.classes.models.DbMessage
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.textview.MaterialTextView

class MessageViewHolder(view : View) : RecyclerView.ViewHolder(view) {

    val container by lazy {view.findViewById<LinearLayout>(R.id.message_container)}
    val message by lazy {view.findViewById<MaterialTextView>(R.id.message_text)}
    val image by lazy {view.findViewById<ShapeableImageView>(R.id.message_image)}

    fun setupWithMessage(dbMessage: DbMessage){
        if(dbMessage.userId == FB.auth.getUser()?.id){
            container.gravity = Gravity.RIGHT
        }else{
            container.gravity = Gravity.LEFT
        }

        dbMessage.message?.let{text->
            message.text = text
            message.visibility = View.VISIBLE
        } ?: kotlin.run {
            message.visibility = View.GONE
        }

        dbMessage.imageURL?.let{

            image.visibility = View.VISIBLE
        } ?: kotlin.run {
            image.visibility = View.GONE
        }

    }

}
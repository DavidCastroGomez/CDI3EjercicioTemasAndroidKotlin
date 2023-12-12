package com.enti.dostres.cdi.davidcastrogomez.myapplication.fragments.screens.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.enti.dostres.cdi.davidcastrogomez.myapplication.R
import com.enti.dostres.cdi.davidcastrogomez.myapplication.classes.firebase.FB
import com.enti.dostres.cdi.davidcastrogomez.myapplication.classes.models.DbMessage
import com.google.android.material.snackbar.Snackbar

class MessagesAdapter(table: RecyclerView): RecyclerView.Adapter<MessageViewHolder>() {

    var messages = mutableListOf<DbMessage>()

    init {
        FB.db.onTableChange<DbMessage>(DbMessage().getTable()){updatedMessages ->
            updatedMessages.sortBy { message ->
                message.createDate
            }

            val count = messages.count()
            val newcount = updatedMessages.count()
            val dif = newcount - count

            messages = updatedMessages

            if(dif > 0){
                notifyItemRangeInserted(count - 1, dif)
            } else{
                notifyDataSetChanged()
            }

            table.scrollToPosition(newcount - 1)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewHolder = MessageViewHolder(layoutInflater.inflate(R.layout.screen_chat_message_cell, parent, false))
        return viewHolder
    }

    override fun getItemCount() = messages.count()

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.setupWithMessage(messages[position])
    }

    fun addMessage(text: String?, imageURL: String?){
        val dbMessage = DbMessage(null, FB.auth.getUser()?.id, text, imageURL, null)

        FB.db.save(dbMessage, onSuccess = {

        }, onFailure = {
            //TODO ADD ERROR CONTROL
        })
    }

}
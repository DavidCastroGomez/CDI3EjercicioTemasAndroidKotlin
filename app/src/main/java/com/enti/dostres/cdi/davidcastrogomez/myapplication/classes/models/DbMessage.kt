package com.enti.dostres.cdi.davidcastrogomez.myapplication.classes.models

import com.google.firebase.firestore.ServerTimestamp
import java.util.Date

class DbMessage(
    override var id :String? = null,
    var userId: String? = null,
    var message: String? = null,
    var imageURL: String? = null,
    @ServerTimestamp var createDate: Date? = null
    ) : DbBaseData {

    override fun getTable() = "Messages"
}
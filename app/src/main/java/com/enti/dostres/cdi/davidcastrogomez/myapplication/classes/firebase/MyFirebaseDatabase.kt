package com.enti.dostres.cdi.davidcastrogomez.myapplication.classes.firebase

import com.enti.dostres.cdi.davidcastrogomez.myapplication.classes.models.DbBaseData
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.firestore
import java.lang.Exception

class MyFirebaseDatabase {
    val db = Firebase.firestore

    fun <T:DbBaseData>save(data: T, onSuccess: (T) -> Unit, onFailure: (Exception) -> Unit){

        val table = db.collection(data.getTable())
        val id = data.id ?: table.document().id
        data.id = id

        table
            .document(id)
            .set(data)
            .addOnSuccessListener {
                onSuccess(data)
            }
            .addOnFailureListener { exception ->
                FB.crashlytics.logError(exception){
                    key("Object",data.toString())
                    key("Error Type", "Insert Or Update Error")
                }

            onFailure(exception) }
    }

    inline fun <reified T:DbBaseData>find(id: String, tableName:String, crossinline onSuccess: (T) -> Unit, crossinline onFailure: (Exception) -> Unit){

        val table = db.collection(tableName)

        table
            .document(id)
            .get()
            .addOnSuccessListener { documentSnapshot->
                val data: T? = documentSnapshot.toObject(T::class.java)

                data?.let {data ->

                    onSuccess(data)

                } ?: kotlin.run{

                    val exception = Exception("Error On Parse Firestone DocumentSnapshot")

                    FB.crashlytics.logError(exception){
                        key("id", id)
                        key("table", tableName)
                        key("Error Type", "Parse Error")
                        key("Snapshot", documentSnapshot.toString())

                    }

                    onFailure(exception)

                }

            }
            .addOnFailureListener {exception ->

                FB.crashlytics.logError(exception){
                    key("id", id)
                    key("table", tableName)
                    key("Error Type", "Object Not Found")
                }

                onFailure(exception)

            }

    }

    inline fun <reified T:DbBaseData>onTableChange(table: String, crossinline onChange: (MutableList<T>) -> Unit){
        db.collection(table).addSnapshotListener {snapShot, error ->

            //TODO CONTROL ERROR

            val objects = snapShot?.toObjects(T::class.java)

            objects?.let{objects ->
                onChange(objects)
            }

        }
    }
}
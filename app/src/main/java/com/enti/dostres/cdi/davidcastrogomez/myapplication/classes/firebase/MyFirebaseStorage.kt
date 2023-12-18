package com.enti.dostres.cdi.davidcastrogomez.myapplication.classes.firebase

import android.net.Uri
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import java.lang.Exception
import java.nio.file.FileSystems
import java.nio.file.Path

class MyFirebaseStorage {

    private val storage = Firebase.storage
    private val storageRootRef = storage.reference

    private val imagesPath = "Images"
    private val imagesRef = storageRootRef.child(imagesPath)    //make child with username to save icon

    fun saveImage(uri: Uri, onSuccess: (Uri) -> Unit, onFailure: (Exception) -> Unit){

        val path: Path = FileSystems.getDefault().getPath(uri.path)
        val name = path.fileName.toString()

        val imageRef = imagesRef.child(name)
        imageRef.putFile(uri)
            .addOnSuccessListener { uploadSnapshot ->

                uploadSnapshot.storage.downloadUrl
                    .addOnSuccessListener(onSuccess)
                    .addOnFailureListener(onFailure)

            }
            .addOnFailureListener(onFailure)

    }


}
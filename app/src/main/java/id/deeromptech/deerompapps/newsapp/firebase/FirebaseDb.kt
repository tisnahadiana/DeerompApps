package id.deeromptech.deerompapps.newsapp.firebase

import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import id.deeromptech.deerompapps.utils.Constant.ARTICLES_COLLECTION

class FirebaseDb {

    private val newsCollectionRef = Firebase.firestore.collection(ARTICLES_COLLECTION)

    fun getAllNews() = newsCollectionRef
    fun getLatestNews() = newsCollectionRef.orderBy("date", Query.Direction.ASCENDING)
}
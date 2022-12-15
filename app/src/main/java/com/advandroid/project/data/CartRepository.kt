package com.advandroid.project.data

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CartRepository(private val context: Context) {
    private val TAG = this.toString()
    private val db = Firebase.firestore
    private val COLLECTION_USERS = "cart"
    private val datasource = Datasource.getInstance()

    fun getCart() {
        try {
            db.collection(COLLECTION_USERS).get().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val document: QuerySnapshot = task.result
                    if (document != null) {
                        val arr = document.toObjects(SelectedProduct::class.java)
                        datasource.setCart(arr)
                    }
                } else {
                    Log.d("addCartItem", "Get failed: ${task.exception}")
                }
            }

        } catch (ex: Exception) {
            Log.e(TAG, "addCartItem: $ex")
        }
    }

    fun addCartItem(product: SelectedProduct) {
        try {
            db.collection(COLLECTION_USERS).add(product).addOnSuccessListener { docRef ->
                Log.d(TAG, "addCartItem: Document added with ID ${docRef.id}")

            }.addOnFailureListener {
                Log.e(TAG, "addCartItem: $it")
            }

        } catch (ex: Exception) {
            Log.e(TAG, "addCartItem: $ex")
        }
    }

    fun deleteCartItem(prodId: String) {
        try {
            db.collection(COLLECTION_USERS).whereEqualTo("prodId", prodId).get()
                .addOnSuccessListener { querySnapshot ->
                    querySnapshot.forEach {
                        it.reference.delete()
                    }
                }
                .addOnFailureListener {
                    Log.e(
                        TAG,
                        "deleteCartItem: Failed to delete document $prodId"
                    )
                }
        } catch (ex: Exception) {
            Log.e(TAG, "deleteCartItem: Failed $ex")
        }
    }
}
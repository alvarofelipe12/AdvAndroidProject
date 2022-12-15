package com.advandroid.project.data

import android.content.Context
import android.util.Log
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CartRepository(private val context: Context) {
    private val TAG = this.toString()
    private val db = Firebase.firestore
    private val COLLECTION_CART = "cart_all"
    private val datasource = Datasource.getInstance()

    fun createNewCart(uid:String) {
        val exist = hashMapOf("exist" to true)
        try {
            db.collection(COLLECTION_CART).document("${uid}").set(exist).addOnSuccessListener { docRef ->
                    Log.d(TAG, "addCartItem: Document added with ID ${uid}")

                }.addOnFailureListener {
                    Log.e(TAG, "addCartItem: $it")
                }
        } catch (ex: Exception) {
            Log.e(TAG, "addCartItem: $ex")
        }
    }

    fun getCart(uid:String) {
        try {
            db.collection(COLLECTION_CART).document("${uid}").collection("cart_item")
                .get().addOnCompleteListener { docRef ->
                if (docRef.isSuccessful) {
                    val document: QuerySnapshot = docRef.result
                    if (document != null) {
                        val arr = document.toObjects(SelectedProduct::class.java)
                        datasource.setCart(arr)
                    }
                } else {
                    Log.d("addCartItem", "Get failed: ${docRef.exception}")
                }
            }
        } catch (ex: Exception) {
            Log.e(TAG, "addCartItem: $ex")
        }
    }

    fun addCartItem(product: SelectedProduct,uid:String) {
        try {
            db.collection(COLLECTION_CART).document("${uid}").collection("cart_item").add(product).addOnSuccessListener { docRef ->
                Log.d(TAG, "addCartItem: Document added with ID ${docRef.id}")

            }.addOnFailureListener {
                Log.e(TAG, "addCartItem: $it")
            }

        } catch (ex: Exception) {
            Log.e(TAG, "addCartItem: $ex")
        }
    }

    fun changeItemQuantity(prodId: String,uid:String,qty:Int) {
        try {
            db.collection(COLLECTION_CART).document("${uid}").collection("cart_item").document(prodId).update("qty",qty)
                .addOnSuccessListener { docRef ->
                    Log.d(TAG, "addCartItem: Document added with ID ${docRef}")
            }.addOnFailureListener {
                Log.e(TAG, "changeItemQty: $it")
            }
        } catch (ex: Exception) {
            Log.e(TAG, "changeItemQty: $ex")
        }
    }


    fun deleteCartItem(prodId: String,uid:String) {
        try {
            db.collection(COLLECTION_CART).document("${uid}").collection("cart_item").whereEqualTo("prodId", prodId).get()
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
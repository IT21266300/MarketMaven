package com.example.marketmaven.activities

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.marketmaven.R
import com.example.marketmaven.adapters.ItemAdapter
import com.example.marketmaven.model.ItemModel
import com.google.firebase.database.*

class DisplayActivity : AppCompatActivity() {

    private lateinit var itemRecyclerView:RecyclerView
    private lateinit var tvLoadingData : TextView
    private lateinit var itemList: ArrayList<ItemModel>
    private lateinit var dbRef: DatabaseReference

    //popup Message
    private fun showPopupMessage(message: String) {
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.popup_message)

        val messageTextView = dialog.findViewById<TextView>(R.id.message)
        messageTextView.text = message

        val dismissButton = dialog.findViewById<Button>(R.id.dismiss)
        dismissButton.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

//    private lateinit var tvItemCount: TextView//Item Count

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display)

        itemRecyclerView = findViewById(R.id.rvItems)
        itemRecyclerView.layoutManager = LinearLayoutManager(this)
        itemRecyclerView.setHasFixedSize(true)
        tvLoadingData = findViewById(R.id.loadingData)

//        tvItemCount = findViewById(R.id.tvItemCount)//Item Count

        itemList = arrayListOf<ItemModel>()

        getItemData()

        //popup
        Handler().postDelayed({
            val message = "Total items: ${itemList.size}"
            showPopupMessage(message)
        }, 2000)
    }




    private fun getItemData(){
        itemRecyclerView.visibility= View.GONE
        tvLoadingData.visibility = View.VISIBLE


        dbRef = FirebaseDatabase.getInstance().getReference("Items")
        dbRef.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                itemList.clear()
                if(snapshot.exists()){
                    for(itemSnap in snapshot.children){
                        val itemData = itemSnap.getValue(ItemModel::class.java)
                        itemList.add(itemData!!)
                    }
                    val mAdapter = ItemAdapter(itemList)
                    itemRecyclerView.adapter = mAdapter

                    mAdapter.setOnItemClickListener(object :ItemAdapter.onItemClickListener{
                        override fun onItemClick(position: Int) {
                            val intent = Intent(this@DisplayActivity, ShowItemData::class.java)

                            //put extra data
                            intent.putExtra("itemId",itemList[position].itmId)
                            intent.putExtra("itemName",itemList[position].itmName)
                            intent.putExtra("itemWeight",itemList[position].itmWeight)
                            intent.putExtra("itemDistance",itemList[position].transportDistance)
                            intent.putExtra("buyingPrice",itemList[position].buyingPrice)
                            intent.putExtra("sellingPrice",itemList[position].sellingPrice)
                            startActivity(intent)
                        }

                    })

                    //Item Count
//                    tvItemCount.text = "Total Items: ${itemList.size}"

                    itemRecyclerView.visibility = View.VISIBLE
                    tvLoadingData.visibility = View.GONE
                }
            }

            //Messge

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }



        } )
    }
}
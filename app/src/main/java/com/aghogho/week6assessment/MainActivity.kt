package com.aghogho.week6assessment


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.onovughe.week6assessment.R
import com.aghogho.week6assessment.database.UserEntity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), RecyclerAdapter.RowClickListener {

    lateinit var recyclerAdapter: RecyclerAdapter
    lateinit var viewModel: ActivityModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       rvId.apply{
           layoutManager = LinearLayoutManager(this@MainActivity)
           recyclerAdapter = RecyclerAdapter(this@MainActivity)
           adapter = recyclerAdapter
           val divider = DividerItemDecoration(applicationContext, VERTICAL)
           addItemDecoration(divider)

       }
        viewModel = ViewModelProviders.of(this).get(ActivityModel::class.java)
        viewModel.getAllUserObservers().observe(this, Observer {
            recyclerAdapter.setListData(ArrayList(it))
            recyclerAdapter.notifyDataSetChanged()

        })

        btnSave.setOnClickListener{
            val name = fnId.text.toString()
            val name2 = snId.text.toString()

            if(btnSave.text.equals("Save")){

            val user = UserEntity(0, name, name2)
            viewModel.insertUserInfo(user)

        }else{
                val user = UserEntity(fnId.getTag(fnId.id).toString().toInt(), name, name2)
                viewModel.updateUserInfo(user)
                btnSave.setText("Save")
            }
            fnId.setText("")
            snId.setText("")

        }
    }

    override fun onDeleteUserClickListener(user: UserEntity) {
        viewModel.deleteUserInfo(user)
    }

    override fun onItemClickListener(user: UserEntity) {
        fnId.setText(user.name)
        snId.setText(user.surname)
        fnId.setTag(fnId.id, user.id)
        btnSave.setText("Update")
    }
}
package com.appiskey.socialpostsapp.views

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.appiskey.socialpostsapp.R
import com.appiskey.socialpostsapp.data.api.ApiHelper
import com.appiskey.socialpostsapp.data.api.RetrofitBuilder
import com.appiskey.socialpostsapp.data.model.remote.PostRemote
import com.appiskey.socialpostsapp.databinding.ActivityMainBinding
import com.appiskey.socialpostsapp.helper.BaseActivity
import com.appiskey.socialpostsapp.helper.ViewModelFactory
import com.appiskey.socialpostsapp.utils.Status
import com.appiskey.socialpostsapp.utils.gone
import com.appiskey.socialpostsapp.utils.visible
import com.appiskey.socialpostsapp.viewmodel.MainActivityViewModel
import com.appiskey.socialpostsapp.views.adapters.PostAdapter
import com.appiskey.socialpostsapp.views.dialogs.PostDetailDialog

class MainActivity : BaseActivity() {
    lateinit var mainActivityViewModel: MainActivityViewModel
    private lateinit var binding: ActivityMainBinding

    private val socialPosts: ArrayList<PostRemote> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpViewModel()

        getPosts()
    }

    private fun getPosts(){
        if(isConnectedToNetwork(this)){
            mainActivityViewModel.getPosts(
                key = "5303976-fd6581ad4ac165d1b75cc15b3",
                type = "kitten"
            ).observe(this) { resource ->
                when(resource.status){
                    Status.SUCCESS -> {
                        hideProgressBar()

                        socialPosts.clear()
                        socialPosts.addAll(resource.data?.posts?: ArrayList())
                        if(socialPosts.isEmpty()){
                            binding.tvNoPost.visible()
                        }else{
                            setupRecyclerView()
                        }
                    }
                    Status.LOADING -> {
                        showProgressBar()
                    }
                    Status.ERROR -> {
                        hideProgressBar()
                        showToastLong(resource.message?: "Unable to fetch Posts!")
                    }
                }
            }
        }else{
            showToastLong(getString(R.string.network_not_connected_msg))
        }
    }

    private var adapter: PostAdapter? = null
    private fun setupRecyclerView(){
        adapter = PostAdapter(postList = socialPosts, context = this)
        adapter?.mOnClickListener = object : PostAdapter.OnClickListener{
            override fun onImageTouch(imageURL: String) {
                showPostDetailDialog(imageURL)
            }
        }
        binding.rvPosts.setHasFixedSize(true)
        binding.rvPosts.adapter = adapter
        binding.rvPosts.layoutManager = LinearLayoutManager(this)
    }

    private fun showPostDetailDialog(imageURL: String){
        val dialog = PostDetailDialog(imageURL)
        dialog.show(supportFragmentManager, dialog.tag)
    }

    private fun showProgressBar(message: String = getString(R.string.lbl_loading)){
        binding.tvProgress.text = message
        binding.layoutProgress.visible()
    }

    private fun hideProgressBar(){
        binding.layoutProgress.gone()
    }

    private fun setUpViewModel() {
        mainActivityViewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiHelper(RetrofitBuilder.getApiService("dynamic ip here")),
                application
            )
        )[MainActivityViewModel::class.java]
    }


}
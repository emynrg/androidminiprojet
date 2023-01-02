package tn.esprit.miniprojet.Views.fragument

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import tn.esprit.miniprojet.Models.Post
import tn.esprit.miniprojet.R
import tn.esprit.miniprojet.ViewModel.Adapter.EventAdapteraffichageProfile
import tn.esprit.miniprojet.ViewModel.Adapter.PostAdapter
import tn.esprit.miniprojet.ViewModel.listPostViewModel
import tn.esprit.miniprojet.Views.joindreEvent

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeF.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeF : Fragment() {
    lateinit var rvPost: RecyclerView;
    lateinit var listpost:MutableList<Post>
    lateinit var viewModel: listPostViewModel
    lateinit var addpost : Button
    lateinit var swipeRefresh : SwipeRefreshLayout




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)


        viewModel= ViewModelProvider(this).get(listPostViewModel::class.java)
        rvPost=view.findViewById(R.id.rv_post)
        listpost= ArrayList()
        addpost = view.findViewById(R.id.addPost)
        addpost.setOnClickListener {
            val intent = Intent(context, tn.esprit.miniprojet.Views.addpost::class.java)

            context?.startActivity(intent)
        }






        getPostByIdUser()


        swipeRefresh=view.findViewById(R.id.swipeRefresh)
        swipeRefresh.setOnRefreshListener {
            getPostByIdUser()
        }



        //   val btnFr1 = view.findViewById<FloatingActionButton>(R.id.add_post)


        /* btnFr1.setOnClickListener {
             startActivity(Intent(view.context, addPost::class.java))

         }
 */
        return view
    }


    private fun getPostByIdUser(){

        viewModel.getAllPosts()
        viewModel._postLiveData.observe(viewLifecycleOwner, Observer<MutableList<Post>>{

            if (it.size>0){

                listpost=it

                swipeRefresh.isRefreshing=false


                val layoutManagerServices= LinearLayoutManager(requireContext())
                layoutManagerServices.setOrientation(LinearLayoutManager.VERTICAL);
                rvPost.layoutManager = layoutManagerServices
                val postAdapter = PostAdapter(requireContext(),listpost)
                rvPost.adapter = postAdapter
                postAdapter.notifyItemChanged(listpost.size+1)

            }
        })
    }

}
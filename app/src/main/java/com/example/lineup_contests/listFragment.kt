package com.example.lineup_contests

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class listFragment : Fragment(),RVListener {

    lateinit var recyclerView: RecyclerView
    lateinit var mvm: ContestViewModel
    lateinit var adapter : LuAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar!!.hide()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        (activity as AppCompatActivity).supportActionBar!!.hide()

        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())

        adapter = LuAdapter(this)

        recyclerView.adapter = adapter

        mvm = ViewModelProvider(this).get(ContestViewModel::class.java)

        mvm.allcontests.observe(viewLifecycleOwner,{
            if(it != null){
                adapter.updateList(it!!)
            }
        })
        mvm.getRecipes()

    }

    override fun onClicked(curr_cont: contestsItem) {
        if(curr_cont != null){
            val bundle = Bundle().apply {
                putSerializable("contest",curr_cont)
            }
            findNavController().navigate(R.id.action_listFragment_to_displayFragment,bundle)
        }
    }


}
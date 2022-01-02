package com.example.lineup_contests
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class LuAdapter (val listener : RVListener) : RecyclerView.Adapter<LuAdapter.RecipeViewHolder>() {

    var allContests : ArrayList<contestsItem> = ArrayList()


    inner class RecipeViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val site_name: TextView = view.findViewById(R.id.site_name)
        val title : TextView = view.findViewById(R.id.contest_name)
        val start_date: TextView = view.findViewById(R.id.start_date)
        val before: TextView = view.findViewById(R.id.before)
        fun  bind(curr : contestsItem){
            this.site_name.text = curr?.site
            this.title.text = curr?.name
            this.start_date.text = curr?.start_time.subSequence(0,10)
            this.before.text = curr?.in_24_hours
            itemView.setOnClickListener {
                listener.onClicked(curr)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        return RecipeViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val currContest = allContests[position]
        holder.bind(currContest)
    }

    override fun getItemCount(): Int = allContests.size

    fun updateList(arr: contests){
        allContests.clear()
        allContests = arr
        notifyDataSetChanged()
    }
}

interface RVListener {
    fun onClicked(curr_cont: contestsItem)
}
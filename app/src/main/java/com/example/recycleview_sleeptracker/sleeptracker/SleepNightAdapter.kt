//Вьюхолдер це те - які вьюшки я буду відображати у РВ
package com.example.recycleview_sleeptracker.sleeptracker
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.recycleview_sleeptracker.R
import com.example.recycleview_sleeptracker.convertDurationToFormatted
import com.example.recycleview_sleeptracker.convertNumericQualityToString
import com.example.recycleview_sleeptracker.database.SleepNight



class SleepNightAdapter : androidx.recyclerview.widget.ListAdapter<SleepNight, SleepNightAdapter.ViewHolder>(SleepNightDiffCallBack()) {




    override fun onCreateViewHolder(/*it is RecycleView */ parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    // how to change the contents of a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        val res = holder.itemView.context.resources
        holder.bind(item)

    }

    class ViewHolder private constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val sleepLength: TextView = itemView.findViewById(R.id.sleep_length)
        val quality: TextView = itemView.findViewById(R.id.quality_string)
        val qualityImage: ImageView = itemView.findViewById(R.id.quality_image)

        fun bind(item: SleepNight) {
            val res = itemView.context.resources
            sleepLength.text =
                convertDurationToFormatted(item.startTimeMilli, item.endTimeMilli, res)
            quality.text = convertNumericQualityToString(item.sleepQuality, res)

            qualityImage.setImageResource(
                when (item.sleepQuality) {
                    0 -> R.drawable.ic_sleep_0
                    1 -> R.drawable.ic_sleep_1
                    2 -> R.drawable.ic_sleep_2
                    3 -> R.drawable.ic_sleep_3
                    4 -> R.drawable.ic_sleep_4
                    5 -> R.drawable.ic_sleep_5
                    else -> R.drawable.ic_sleep_active
                }
            )
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.list_item_sleep_night, parent, false)
                return ViewHolder(view) //ViewHolder it is just like a View. In this class we say which elements he have to create(view) in RV.
            }
        }
    }

    class SleepNightDiffCallBack: DiffUtil.ItemCallback<SleepNight>(){
        //it`s will be used to discover if an item was edit, removed or moved.
        //F.e. I move last sleep night to the first position. this method will figure out that the item is the same as the item in old position.
        //So he check(for example view`s id) that item on new position and old position is the same
        override fun areItemsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
            return oldItem.nightId == newItem.nightId
        }

        //here we check that content inside the item is not changed. If anything has changed in SleepNight betw. old and new list
        //it`s tell DiffUtil the item has been updated
        override fun areContentsTheSame(oldItem: SleepNight, newItem: SleepNight): Boolean {
            return  oldItem == newItem
        }

    }

}

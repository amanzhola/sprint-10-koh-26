package ru.yandex.practicum.sprint10koh26

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.text.SimpleDateFormat
import java.util.Locale

class ChatAdapter(

): RecyclerView.Adapter<ViewHolder>() {

    companion object {
        const val VIEW_TYPE_MY = 1
        const val VIEW_TYPE_NOT_MY = 2
    }

    private var items: List<ChatMessage> = emptyList()

    fun updateItems(newItems: List<ChatMessage>) {
        val oldItems = items

        val diffResult = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun getOldListSize(): Int {
                return oldItems.size
            }

            override fun getNewListSize(): Int {
                return newItems.size
            }

            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldItems[oldItemPosition].id == newItems[newItemPosition].id
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return oldItems[oldItemPosition] == newItems[newItemPosition]
            }

        })

        items = newItems.toList()
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return when (viewType) {
            VIEW_TYPE_MY -> MyChatMessageViewHolder(parent)
            VIEW_TYPE_NOT_MY -> NotMyChatMessageViewHolder(parent)
            else -> throw IllegalStateException("there is no holder for $viewType")
        }.apply {
            Log.d("SPRINT_10", "onCreateViewHolder: $this")
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (items[position].isMine) {
            VIEW_TYPE_MY
        } else {
            VIEW_TYPE_NOT_MY
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("SPRINT_10", "onBindViewHolder: $holder")
        when (holder) {
            is MyChatMessageViewHolder -> holder.bind(items[position])
            is NotMyChatMessageViewHolder -> holder.bind(items[position])
        }

//        val chatMessage = items[position]
//        holder.itemView.setBackgroundColor(listOf(Color.RED, Color.MAGENTA, Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN).random())
//        holder.tvMessage.text = chatMessage.messageText
//        holder.tvDate.text = dateFormatter.format(chatMessage.date)
//
//        holder.ivStatus.setImageResource(
//            when(chatMessage.status) {
//                ChatMessage.Status.SENT -> R.drawable.ic_sent
//                ChatMessage.Status.DELIVERED -> R.drawable.ic_delivered
//                ChatMessage.Status.READ -> R.drawable.ic_read
//            }
//        )
    }
}


class MyChatMessageViewHolder(
    parent: ViewGroup
): RecyclerView.ViewHolder(
    LayoutInflater
        .from(parent.context)
        .inflate(R.layout.item_my_chat_message, parent, false)
) {

    private val tvMessage: TextView = itemView.findViewById(R.id.tvMessage)
    private val tvDate: TextView = itemView.findViewById(R.id.tvDate)
    private val ivStatus: ImageView = itemView.findViewById(R.id.ivStatus)

    private val dateFormatter = SimpleDateFormat("h:mm", Locale.getDefault())

    fun bind(chatMessage: ChatMessage) {
//        itemView.setBackgroundColor(listOf(Color.RED, Color.MAGENTA, Color.BLUE, Color.GREEN, Color.YELLOW, Color.CYAN).random())
        tvMessage.text = chatMessage.messageText
        tvDate.text = dateFormatter.format(chatMessage.date)


        ivStatus.setImageResource(
            when(chatMessage.status) {
                ChatMessage.Status.SENT -> R.drawable.ic_sent
                ChatMessage.Status.DELIVERED -> R.drawable.ic_delivered
                ChatMessage.Status.READ -> R.drawable.ic_read
            }
        )
    }

}

class NotMyChatMessageViewHolder(
    parent: ViewGroup
): RecyclerView.ViewHolder(
    LayoutInflater
        .from(parent.context)
        .inflate(R.layout.item_not_my_chat_message, parent, false)
) {

    private val tvMessage: TextView = itemView.findViewById(R.id.tvMessage)
    private val tvDate: TextView = itemView.findViewById(R.id.tvDate)

    private val dateFormatter = SimpleDateFormat("h:mm", Locale.getDefault())

    fun bind(chatMessage: ChatMessage) {

        tvMessage.text = chatMessage.messageText
        tvDate.text = dateFormatter.format(chatMessage.date)

    }

}
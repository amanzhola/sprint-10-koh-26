package ru.yandex.practicum.sprint10koh26

import android.content.Context
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Date

class MainActivity : AppCompatActivity() {

    private val messages: MutableList<ChatMessage> = mutableListOf()
//        (0..100).map {
//        mutableListOf(
//            ChatMessage(
//                isMine = true,
//                messageText = "Hi",
//                status = ChatMessage.Status.SENT,
//                date = Date(),
//                isChanged = false
//            ),
//            ChatMessage(
//                isMine = true,
//                messageText = "How are you?",
//                status = ChatMessage.Status.DELIVERED,
//                date = Date(),
//                isChanged = false
//            ),
//            ChatMessage(
//                isMine = true,
//                messageText = "Hope u r well?",
//                status = ChatMessage.Status.READ,
//                date = Date(),
//                isChanged = false
//            ),
//        )
//    }.flatten().toMutableList()

    private val chatAdapter = ChatAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            val ime = insets.getInsets(WindowInsetsCompat.Type.ime())
            val bottomPadding = if (ime.bottom > 0) {
                ime.bottom
            } else {
                systemBars.bottom
            }
            v.setPadding(
                systemBars.left, systemBars.top, systemBars.right,
                bottomPadding
            )
            insets
        }

//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
//        recreate()

        val rvItems: RecyclerView = findViewById(R.id.rvItems)

        rvItems.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
//        rvItems.layoutManager = GridLayoutManager(this, 3).apply {
//            spanSizeLookup = object : SpanSizeLookup() {
//                override fun getSpanSize(position: Int): Int {
//                    return if (position % 5 == 0) {
//                        3
//                    } else {
//                        1
//                    }
//                }
//
//            }
//        }
        rvItems.adapter = chatAdapter

        val ivAction: ImageView = findViewById(R.id.ivAction)
        val etMessage: EditText = findViewById(R.id.etMessage)
        etMessage.doAfterTextChanged {
            ivAction.setImageResource(
                if (etMessage.text.isNullOrEmpty()) {
                    R.drawable.ic_audio
                } else {
                    R.drawable.ic_send
                }
            )
        }
        ivAction.setOnClickListener {
            val newMessageText = etMessage.text?.toString()
            if (!newMessageText.isNullOrEmpty()) {
                messages.add( 0,
                    ChatMessage(
                        isMine = true,
                        messageText = newMessageText,
                        status = ChatMessage.Status.SENT,
                        date = Date(),
                        isChanged = false
                    )
                )
                chatAdapter.updateItems(messages)
                rvItems.scrollToPosition(0)
                etMessage.setText("")


                etMessage.postDelayed({
                    messages.add(0,
                        ChatMessage(
                            isMine = false,
                            messageText = "ok, $newMessageText",
                            status = ChatMessage.Status.SENT,
                            date = Date(),
                            isChanged = false
                        )
                    )
                    messages.add(0,
                        ChatMessage(
                            isMine = false,
                            messageText = "ok, $newMessageText",
                            status = ChatMessage.Status.SENT,
                            date = Date(),
                            isChanged = false
                        )
                    )
                    messages.add(1,
                        ChatMessage(
                            isMine = false,
                            messageText = "ok, $newMessageText",
                            status = ChatMessage.Status.SENT,
                            date = Date(),
                            isChanged = false
                        )
                    )
                    chatAdapter.updateItems(messages)
                    rvItems.scrollToPosition(0)
                }, 1000)
            }
        }
    }


    private fun viewSizes() {

//        <TextView
//        android:id="@+id/text_view_xml"
//        android:layout_width="100dp"
//        android:layout_height="wrap_content"
//        android:ellipsize="end"
//        android:maxLines="1"
//        android:layout_marginStart="20dp"
//        android:paddingEnd="30dp"/>


//        val container: FrameLayout = findViewById(R.id.container)
//
//        val text_view_xml: TextView = findViewById(R.id.text_view_xml)
//
//        Log.d("SPRINT_10", "text_view_xml 1 ${text_view_xml.width}")
//
//        text_view_xml.viewTreeObserver.addOnGlobalLayoutListener {
//            Log.d("SPRINT_10", "text_view_xml viewTreeObserver ${text_view_xml.width}")
//        }
//
//        text_view_xml.viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
//            override fun onGlobalLayout() {
//                Log.d("SPRINT_10", "text_view_xml viewTreeObserver object ${text_view_xml.width}")
//                text_view_xml.viewTreeObserver.removeOnGlobalLayoutListener(this)
//            }
//
//        })
//        text_view_xml.postDelayed({
//            Log.d("SPRINT_10", "text_view_xml 2 ${text_view_xml.width}")
//        }, 1000)
//
//
//        container.addView(
//            TextView(this).apply {
//                val width = TypedValue.applyDimension(
//                    TypedValue.COMPLEX_UNIT_DIP,
//                    100f,
//                    resources.displayMetrics
//                ).toInt()
//                val layoutParams = FrameLayout.LayoutParams(
//                    width,
//                    FrameLayout.LayoutParams.WRAP_CONTENT,
//                )
//                layoutParams.marginStart = TypedValue.applyDimension(
//                    TypedValue.COMPLEX_UNIT_DIP,
//                    20f,
//                    resources.displayMetrics
//                ).toInt()
//                this.layoutParams = layoutParams
//
//                this.setPadding(
//                    0, 0, TypedValue.applyDimension(
//                        TypedValue.COMPLEX_UNIT_DIP,
//                        30f,
//                        resources.displayMetrics
//                    ).toInt(), 0
//                )
//                this.maxLines = 1
//                this.ellipsize = TextUtils.TruncateAt.END
//            }
//        )

    }

//
//    class SimpleAdapter (private val context: Context){
//
//        fun map(message: ChatMessage): View {
//            return LayoutInflater.from(context).inflate(R.layout.item_chat_message, null, false).apply {
//                findViewById<TextView>(R.id.tvMessage).text = message.
//            }
//        }
//
//    }
}
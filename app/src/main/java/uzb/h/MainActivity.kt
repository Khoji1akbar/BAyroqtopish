package uzb.h

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.view.children
import kotlinx.android.synthetic.main.activity_main.*
import shakkallakaboom.game
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random as Random1

class MainActivity : AppCompatActivity() ,View.OnClickListener {
    lateinit var flagArreyList: ArrayList<game>
    var count = 0
    var countryName = ""
    lateinit var buttonArreyList: ArrayList<Button>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        obyektyaratish()
        btnjoylaCount()

        buttonArreyList = ArrayList()
    }

    private fun obyektyaratish() {
        flagArreyList = ArrayList()
        flagArreyList.add(game("uzbekistan", R.drawable.img))
        flagArreyList.add(game("china", R.drawable.img_1))
        flagArreyList.add(game("england", R.drawable.img_2))
        flagArreyList.add(game("russia", R.drawable.img_3))
    }

    fun btnjoylaCount() {
        image.setImageResource(flagArreyList[count].image!!)
        lin_1_matn.removeAllViews()
        lin_2_btn_1.removeAllViews()
        lin_3_btn_2.removeAllViews()
        countryName = ""
        btnjoyla(flagArreyList[count].name)
    }

    private fun btnjoyla(countryName: String?) {
        val btnArray: ArrayList<Button> = randombtn(countryName)
        for (i in 0..5) {
            lin_2_btn_1.addView((btnArray[i]))
        }
        for (i in 6..11) {
            lin_3_btn_2.addView(btnArray[i])
        }
    }

    private fun randombtn(countryName: String?): java.util.ArrayList<Button> {
        val array = ArrayList<Button>()
        val arraytext = ArrayList<String>()


        for (c in countryName!!) {
            arraytext.add(c.toString())
        }
        if (arraytext.size != 12) {
            val str = "abcdefhgijklmnopkrstyvwxyz"
            for (i in arraytext.size until 12) {
                val randommm = Random().nextInt(str.length)
                arraytext.add(str[randommm].toString())
            }
        }
        arraytext.shuffle()


        for (i in 0 until arraytext.size) {
            val button = Button(this)
            button.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
            )
            button.text = arraytext[i]
            button.setOnClickListener(this)
            array.add(button)
        }
        return array
    }

    override fun onClick(v: View?) {
        val button1 = v as Button
        if (buttonArreyList.contains(button1)) {
            lin_1_matn.removeView(button1)
            var hasC = false
            lin_2_btn_1.children.forEach { button ->
                if ((button as Button).text.toString() == button1.text.toString()) {
                    button.visibility = View.VISIBLE
                    countryName = countryName.substring(0, countryName.length - 1)
                    hasC = true
                }
            }
            lin_3_btn_2.children.forEach { button ->
                if ((button as Button).text.toString() == button1.text.toString()) {
                    button.visibility = View.VISIBLE
                    if (!hasC) {
                        countryName = countryName.substring(0, countryName.length - 1)
                    }
                }
            }
        }else{
            button1.visibility = View.INVISIBLE
            countryName += button1.text.toString().toUpperCase()
            val button2 = Button(this)
            button2.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f)
            button2.text = button1.text
            button2.setOnClickListener(this)
            buttonArreyList.add(button2)
            lin_1_matn.addView(button2)
            matnTogri()
        }
    }

    private fun matnTogri() {
        if (countryName == flagArreyList[count].name?.toUpperCase()){
            Toast.makeText(this, "Succsesfull",Toast.LENGTH_SHORT).show()
            if (count == flagArreyList.size-1){
                count = 0
            }else{
                count++
            }
            btnjoylaCount()
        }else{
            if (countryName.length == flagArreyList[count].name?.length){
                Toast.makeText(this, "error", Toast.LENGTH_SHORT).show()
                lin_1_matn.removeAllViews()
                lin_2_btn_1.removeAllViews()
                lin_3_btn_2.removeAllViews()
                btnjoyla(flagArreyList[count].name)
                countryName = ""
            }
        }
    }
}
package android.com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    private lateinit var diceImage1 : ImageView
    private lateinit var diceImage2 : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        diceImage1 = findViewById(R.id.dice_image_1)
        diceImage2 = findViewById(R.id.dice_image_2)

        val rollButton : Button = findViewById(R.id.roll_button)
        val countButton : Button = findViewById(R.id.count_button)
        val resetButton : Button = findViewById(R.id.reset_button)
        val clearButton : Button = findViewById(R.id.clear_button)

        rollButton.setOnClickListener{ rollDice() }
        countButton.setOnClickListener{ countDice() }
        resetButton.setOnClickListener{ resetDice() }
        clearButton.setOnClickListener{ clearDice() }
    }

    private fun rollDice() {
        val resourceDiceImage1 = getRandomDiceImage()
        val resourceDiceImage2 = getRandomDiceImage()

        diceImage1.apply{
            setImageResource(resourceDiceImage1)
            tag = resourceDiceImage1
        }
        diceImage2.apply{
            setImageResource(resourceDiceImage2)
            tag = resourceDiceImage2
        }
    }

    private fun countDice() {
        // If imageView don't have ImageResource, it doesn't anything
        if(diceImage1.tag == null || diceImage2.tag == null) return
        // Replace imageView with greater value
        // if value is 6 it is not changed
        val replacedDiceImage1 = getCountUpDiceImage(diceImage1.tag as Int)
        val replacedDiceImage2 = getCountUpDiceImage(diceImage2.tag as Int)

        diceImage1.apply{
            setImageResource(replacedDiceImage1)
            tag = replacedDiceImage1
        }
        diceImage2.apply{
            setImageResource(replacedDiceImage2)
            tag = replacedDiceImage2
        }
    }

    private fun resetDice() {
        diceImage1.apply {
            setImageResource(R.drawable.dice_1)
            tag = R.drawable.dice_1
        }
        diceImage2.apply {
            setImageResource(R.drawable.dice_1)
            tag = R.drawable.dice_1
        }
    }

    private fun clearDice() {
        diceImage1.apply {
            setImageResource(R.drawable.empty_dice)
            tag = null
        }
        diceImage2.apply {
            setImageResource(R.drawable.empty_dice)
            tag = null
        }
    }

    // Get a random drawable image and return an integer for the drawable resource
    private fun getRandomDiceImage() : Int {
        return when ((1..6).random()) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            else -> R.drawable.dice_6
        }
    }

    // Get a count drawable image and return an integer for the drawable resource
    private fun getCountUpDiceImage(resourceID: Int) : Int {
        return when (resourceID) {
            R.drawable.dice_1 -> R.drawable.dice_2
            R.drawable.dice_2 -> R.drawable.dice_3
            R.drawable.dice_3 -> R.drawable.dice_4
            R.drawable.dice_4 -> R.drawable.dice_5
            R.drawable.dice_5 -> R.drawable.dice_6
            else -> R.drawable.dice_6
        }
    }
}
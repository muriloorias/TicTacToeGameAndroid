package com.muriloorias.jogodavelha

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var isPlayer1 = true
    var gameEnd = false

    // Botões do topo\\
    private lateinit var top: ImageView
    private lateinit var topStart: ImageView
    private lateinit var topEnd: ImageView

    // Botões do meio\\
    private lateinit var center: ImageView
    private lateinit var centerStart: ImageView
    private lateinit var centerEnd: ImageView

    // Botões de baixo\\
    private lateinit var bottom: ImageView
    private lateinit var bottomStart: ImageView
    private lateinit var bottomEnd: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Achando os botões\\
        top = findViewById(R.id.top)
        topStart = findViewById(R.id.topStart)
        topEnd = findViewById(R.id.topEnd)

        center = findViewById(R.id.center)
        centerStart = findViewById(R.id.centerStart)
        centerEnd = findViewById(R.id.centerEnd)

        bottom = findViewById(R.id.bottom)
        bottomStart = findViewById(R.id.bottomStart)
        bottomEnd = findViewById(R.id.bottomEnd)

        val reset: Button = findViewById(R.id.buttom_reset)
        reset.setOnClickListener(){
            resetBox(top)
            resetBox(topStart)
            resetBox(topEnd)

            resetBox(center)
            resetBox(centerStart)
            resetBox(centerEnd)

            resetBox(bottom)
            resetBox(bottomStart)
            resetBox(bottomEnd)
        }

        // Configurando a "caixa" para todos os elementos
        configureBox(top)
        configureBox(topStart)
        configureBox(topEnd)

        configureBox(center)
        configureBox(centerStart)
        configureBox(centerEnd)

        configureBox(bottom)
        configureBox(bottomStart)
        configureBox(bottomEnd)
    }

    private fun resetBox(box: ImageView){
        box.setImageDrawable(null)
        box.tag = null
        isPlayer1 = true
        gameEnd = false
    }

    // Detectando evento de clique
    private fun configureBox(box: ImageView) {
        box.setOnClickListener {
            if (box.tag == null && !gameEnd) {
                if (isPlayer1) {
                    box.setImageResource(R.drawable.baseline_remove_circle_24)
                    isPlayer1 = false
                    box.tag = 1
                } else {
                    box.setImageResource(R.drawable.baseline_close_24)
                    isPlayer1 = true
                    box.tag = 2
                }
                if (playerWin(1)) {
                    gameEnd = true
                    Toast.makeText(this@MainActivity, "Player 1 venceu", Toast.LENGTH_SHORT).show()
                } else if (playerWin(2)) {
                    gameEnd = true
                    Toast.makeText(this@MainActivity, "Player 2 venceu", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun playerWin(value: Int): Boolean {
        // Lógica de verificação de vitória\\
        //logica vertical\\
        if ( (top.tag == value && center.tag == value && bottom.tag == value)||
            (topStart.tag == value && centerStart.tag == value && bottomStart.tag == value)||
            (topEnd.tag == value && centerEnd.tag == value &&  bottomEnd.tag == value)||
            // Lógica horizontal\\
            (topStart.tag == value && top.tag == value && topEnd.tag == value)||
            (centerStart.tag == value && center.tag == value && centerEnd.tag == value)||
            (bottomStart.tag == value && bottom.tag == value && bottomEnd.tag == value)||
            // Lógica diagonal\\
            (topStart.tag == value && center.tag == value && bottomEnd.tag == value)||
            (topEnd.tag == value && center.tag == value && bottomStart.tag == value)
        ) {
            return true
        }
        return false
    }
}

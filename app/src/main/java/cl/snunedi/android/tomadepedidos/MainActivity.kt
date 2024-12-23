package cl.snunedi.android.tomadepedidos

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import cl.snunedi.android.tomadepedidos.models.Pedido
import cl.snunedi.android.tomadepedidos.models.Plato

class MainActivity : AppCompatActivity() {
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Instancias de platos
        val pastelChoclo = Plato("Pastel de Choclo", 12000, R.drawable.pastel_choclo)
        val cazuela = Plato("Cazuela", 10000, R.drawable.cazuela)

        // Referencias a las vistas
        val etCantidadPastel = findViewById<EditText>(R.id.etCantidadPastel)
        val etCantidadCazuela = findViewById<EditText>(R.id.etCantidadCazuela)
        val switchPropina = findViewById<Switch>(R.id.switchPropina)
        val tvComidaTotal = findViewById<TextView>(R.id.tvComidaTotal)
        val tvPropina = findViewById<TextView>(R.id.tvPropina)
        val tvTotalFinal = findViewById<TextView>(R.id.tvTotalFinal)

        // Listener para actualizar los cálculos
        val actualizarTotales = {
            val cantidadPastel = etCantidadPastel.text.toString().toIntOrNull() ?: 0
            val cantidadCazuela = etCantidadCazuela.text.toString().toIntOrNull() ?: 0

            val pedido1 = Pedido(pastelChoclo, cantidadPastel)
            val pedido2 = Pedido(cazuela, cantidadCazuela)

            val subtotal = pedido1.calcularSubtotal() + pedido2.calcularSubtotal()
            val incluirPropina = switchPropina.isChecked
            val total = subtotal + if (incluirPropina) (subtotal * 0.1).toInt() else 0

            // Actualización de los TextViews
            tvComidaTotal.text = pedido1.formatearMoneda(subtotal)
            tvPropina.text = pedido1.formatearMoneda(if (incluirPropina) (subtotal * 0.1).toInt() else 0)
            tvTotalFinal.text = pedido1.formatearMoneda(total)
        }

        // Configurar listeners
        etCantidadPastel.setOnFocusChangeListener { _, _ -> actualizarTotales() }
        etCantidadCazuela.setOnFocusChangeListener { _, _ -> actualizarTotales() }
        switchPropina.setOnCheckedChangeListener { _, _ -> actualizarTotales() }

        // Llamar una vez para inicializar los valores
        actualizarTotales()
    }
}

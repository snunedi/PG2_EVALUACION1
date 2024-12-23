package cl.snunedi.android.tomadepedidos.models

import java.text.NumberFormat
import java.util.*

class Pedido(
    private val plato: Plato,
    private var cantidad: Int
) {
    fun calcularSubtotal(): Int {
        return plato.precio * cantidad
    }

    private fun calcularPropina(): Int {
        return (calcularSubtotal() * 0.1).toInt()
    }

    fun calcularTotal(incluirPropina: Boolean): Int {
        val subtotal = calcularSubtotal()
        return if (incluirPropina) subtotal + calcularPropina() else subtotal
    }

    fun formatearMoneda(valor: Int): String {
        val formato = NumberFormat.getCurrencyInstance(Locale("es", "CL"))
        return formato.format(valor)
    }
}



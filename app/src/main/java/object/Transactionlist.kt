package `object`

import com.example.puffandpoof.model.Transaction

object Transactionlist {
    private val transactions: MutableList<Transaction> = mutableListOf()

    fun addTransaction(id: String, dollName: String, quantity: Int, dates: String) {
        val existingTransaction = transactions.find { it.idz == id }
        if (existingTransaction != null) {
            existingTransaction.quantity += quantity
        } else {
            val transaction = Transaction(id, dollName, quantity, dates)
            transactions.add(transaction)
        }
    }

    fun updateQuantity(id: String, quantity: Int) {
        val transaction = transactions.find { it.idz == id }
        transaction?.quantity = quantity
    }

    fun removeTransaction(id: String) {
        transactions.removeAll { it.idz == id }
    }

    fun getTransactions(): List<Transaction> {
        return transactions.toList()
    }
}


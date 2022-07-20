package com.vbogd.terminals.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.vbogd.terminals.App
import com.vbogd.terminals.R
import com.vbogd.terminals.domain.model.Direction
import com.vbogd.terminals.domain.model.Order
import com.vbogd.terminals.domain.model.Terminal
import com.vbogd.terminals.domain.repository.OrdersRepository
import com.vbogd.terminals.domain.repository.TerminalsRepository
import com.vbogd.terminals.utils.Constants
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var terminalsRepository: TerminalsRepository

    @Inject
    lateinit var ordersRepository: OrdersRepository

    override fun onCreate(savedInstanceState: Bundle?) {

        (application as App).appComponent.inject(this)
        fillDatabase()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    private fun fillDatabase() {
        terminalsRepository.getTerminals()
            .subscribeOn(Schedulers.io())
            .subscribe({
                terminalsRepository.saveAll(it)
                    .subscribeOn(Schedulers.io())
                    .subscribe()
            }, {
                Log.d("TAG", "Can't get terminals from WEB")
            })
        ordersRepository.createOrder(
            Order(
                id = Constants.ORDER_SAMPLE_ID,
                terminalFrom = null,
                terminalTo = null
            )
        )
            .subscribeOn(Schedulers.io())
            .subscribe()
    }

}
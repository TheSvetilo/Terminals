package com.vbogd.terminals

import android.app.Application
import android.util.Log
import com.vbogd.terminals.di.component.AppComponent
import com.vbogd.terminals.di.component.DaggerAppComponent
import com.vbogd.terminals.domain.model.Order
import com.vbogd.terminals.domain.model.OrderStatus
import com.vbogd.terminals.domain.repository.OrdersRepository
import com.vbogd.terminals.domain.repository.TerminalsRepository
import com.vbogd.terminals.utils.Constants
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class App : Application() {

    lateinit var appComponent: AppComponent

    @Inject
    lateinit var terminalsRepository: TerminalsRepository

    @Inject
    lateinit var ordersRepository: OrdersRepository

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
        fillDatabase()
    }

    private fun initializeDagger() {
        appComponent = DaggerAppComponent.factory().create(applicationContext)
        appComponent.inject(this)
    }

    private fun fillDatabase() {
        loadAndSaveTerminals()
        initializeOrderSample()
    }

    private fun initializeOrderSample() {
        ordersRepository.getOrderById(Constants.ORDER_SAMPLE_ID)
            .subscribeOn(Schedulers.io())
            .subscribe({}, {
                ordersRepository.createOrder(
                    Order(
                        id = Constants.ORDER_SAMPLE_ID,
                        terminalFrom = null,
                        terminalTo = null,
                        status = OrderStatus.DRAFT
                    )
                )
                    .subscribeOn(Schedulers.io())
                    .subscribe()
            })
    }

    private fun loadAndSaveTerminals() {
        terminalsRepository.getTerminals()
            .subscribeOn(Schedulers.io())
            .subscribe({
                terminalsRepository.saveAll(it)
                    .subscribeOn(Schedulers.io())
                    .subscribe()
            }, {
                Log.d("TAG", "Can't get terminals from WEB")
            })
    }
}
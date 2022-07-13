package com.vbogd.terminals.data

import android.util.Log
import com.vbogd.terminals.data.mappers.convertOrderEntityToModel
import com.vbogd.terminals.data.mappers.convertOrderModelToEntity
import com.vbogd.terminals.data.orderRepository.local.OrderLocalDataSource
import com.vbogd.terminals.domain.model.Order
import com.vbogd.terminals.domain.model.Terminal
import com.vbogd.terminals.domain.repository.OrdersRepository
import com.vbogd.terminals.domain.repository.TerminalsRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OrderManager @Inject constructor(
    private val orderLocalDataSource: OrderLocalDataSource,
    private val terminalsRepository: TerminalsRepository
) : OrdersRepository {

    override fun createOrder(order: Order): Completable {
        val tempOrder = convertOrderModelToEntity(order)
        return orderLocalDataSource.createOrder(tempOrder)
    }

    override fun getOrderById(orderId: String): Single<Order?> {

        var terminalFrom: Terminal? = null
        var terminalTo: Terminal? = null

        return orderLocalDataSource.getOrderById(orderId)
            .subscribeOn(Schedulers.io())
//            .flatMap { orderEntity ->
//                orderEntity.terminalIdFrom?.let { terminalId ->
//                    terminalsRepository.getTerminalById(terminalId)
//                        .subscribeOn(Schedulers.io())
//                        .subscribe({ terminal ->
//                            terminalFrom = terminal
//                        }, {})
//                }
//                orderEntity.terminalIdTo?.let {
//                    terminalsRepository.getTerminalById(it)
//                        .subscribeOn(Schedulers.io())
//                        .subscribe({ terminal ->
//                            terminalTo = terminal
//                        }, {})
//                }
//                Single.just(convertOrderEntityToModel(orderEntity, terminalFrom, terminalTo))
            .flatMap { orderEntity ->
                Single.zip(
                    orderEntity.terminalIdFrom?.let { it1 ->
                        terminalsRepository.getTerminalById(
                            it1
                        )
                    },
                    orderEntity.terminalIdTo?.let { it1 ->
                        terminalsRepository.getTerminalById(
                            it1
                        )
                    }
                ) { terminalFrom, terminalTo ->
                    convertOrderEntityToModel(orderEntity, terminalFrom, terminalTo)
                }

            }
    }

    override fun updateOrder(order: Order): Completable {
        Log.d("TAG", "Saved order: $order")
        return orderLocalDataSource.updateOrder(convertOrderModelToEntity(order))
    }

}

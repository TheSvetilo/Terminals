package com.vbogd.terminals.data

import com.vbogd.terminals.data.mappers.convertOrderEntityToModel
import com.vbogd.terminals.data.mappers.convertOrderModelToEntity
import com.vbogd.terminals.data.orderRepository.local.OrderLocalDataSource
import com.vbogd.terminals.domain.model.Order
import com.vbogd.terminals.domain.repository.OrdersRepository
import com.vbogd.terminals.domain.repository.TerminalsRepository
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

class OrderManager @Inject constructor(
    private val orderLocalDataSource: OrderLocalDataSource,
    private val terminalsRepository: TerminalsRepository
) : OrdersRepository {

    override fun createOrder(order: Order): Completable {
        val tempOrder = convertOrderModelToEntity(order)
        return orderLocalDataSource.createOrder(tempOrder)
    }

    override fun getOrderById(orderId: String): Single<Order?> =
        orderLocalDataSource.getOrderById(orderId)
            .subscribeOn(Schedulers.io())
            .flatMap { orderEntity ->
                Single.zip(

                    orderEntity.terminalIdFrom?.let { it1 ->
                        terminalsRepository.getTerminalById(it1)
                    } ?: Single.just(Optional.empty()),

                    orderEntity.terminalIdTo?.let { it1 ->
                        terminalsRepository.getTerminalById(it1)
                    } ?: Single.just(Optional.empty())

                ) { terminalFrom, terminalTo ->
                    convertOrderEntityToModel(
                        orderEntity,
                        terminalFrom.orElse(null),
                        terminalTo.orElse(null)
                    )
                }

            }


    override fun updateOrder(order: Order): Completable {
        return orderLocalDataSource.updateOrder(convertOrderModelToEntity(order))
    }

}

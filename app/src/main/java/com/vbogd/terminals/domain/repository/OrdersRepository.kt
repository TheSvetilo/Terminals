package com.vbogd.terminals.domain.repository

import com.vbogd.terminals.domain.model.Order
import io.reactivex.Completable
import io.reactivex.Single

interface OrdersRepository {

    fun createOrder(order: Order): Completable
    fun getOrderById(orderId: String): Single<Order?>
    fun updateOrder(order: Order): Completable

}
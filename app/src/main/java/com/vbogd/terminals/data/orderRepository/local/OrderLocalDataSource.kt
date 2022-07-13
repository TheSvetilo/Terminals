package com.vbogd.terminals.data.orderRepository.local

import com.vbogd.terminals.data.orderRepository.local.dao.OrderDao
import com.vbogd.terminals.data.orderRepository.local.entity.OrderEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

interface OrderLocalDataSource {

    fun createOrder(order: OrderEntity): Completable
    fun getOrderById(orderId: String): Single<OrderEntity?>
    fun updateOrder(order: OrderEntity): Completable

    class Base @Inject constructor(
        private val orderDao: OrderDao
    ) : OrderLocalDataSource {

        override fun createOrder(order: OrderEntity): Completable {
            return orderDao.createOrder(order)
        }

        override fun getOrderById(orderId: String): Single<OrderEntity?> {
            return orderDao.getOrderById(orderId)
        }

        override fun updateOrder(order: OrderEntity): Completable {
            return orderDao.updateOrder(order)
        }

    }
}
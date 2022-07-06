package com.vbogd.terminals.data.orderRepository.local

import com.vbogd.terminals.data.orderRepository.local.dao.OrderDao
import com.vbogd.terminals.data.orderRepository.local.entity.OrderEntity
import javax.inject.Inject

interface OrderLocalDataSource {

    fun createOrder(order: OrderEntity)
    fun getOrderById(orderId: String): OrderEntity?
    fun updateOrder(order: OrderEntity)
    fun deleteOrder(order: OrderEntity)

    class Base @Inject constructor(
        private val orderDao: OrderDao
    ) : OrderLocalDataSource {

        override fun createOrder(order: OrderEntity) {
            orderDao.createOrder(order)
        }

        override fun getOrderById(orderId: String): OrderEntity? {
            return orderDao.getOrderById(orderId)
        }

        override fun updateOrder(order: OrderEntity) {
            orderDao.updateOrder(order)
        }

        override fun deleteOrder(order: OrderEntity) {
            orderDao.deleteOrder(order)
        }

    }
}
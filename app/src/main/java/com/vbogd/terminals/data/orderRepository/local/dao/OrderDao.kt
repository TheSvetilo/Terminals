package com.vbogd.terminals.data.orderRepository.local.dao

import androidx.room.*
import com.vbogd.terminals.data.orderRepository.local.entity.OrderEntity

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun createOrder(order: OrderEntity)

    @Query("SELECT * FROM orders WHERE id = :orderId")
    fun getOrderById(orderId: String): OrderEntity?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateOrder(order: OrderEntity)

    @Delete
    fun deleteOrder(order: OrderEntity)

}
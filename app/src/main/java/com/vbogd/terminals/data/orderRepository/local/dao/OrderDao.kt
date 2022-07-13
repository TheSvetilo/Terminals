package com.vbogd.terminals.data.orderRepository.local.dao

import androidx.room.*
import com.vbogd.terminals.data.orderRepository.local.entity.OrderEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface OrderDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createOrder(order: OrderEntity): Completable

    @Query("SELECT * FROM orders WHERE :orderId LIKE id")
    fun getOrderById(orderId: String): Single<OrderEntity?>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateOrder(order: OrderEntity): Completable

    @Delete
    fun deleteOrder(order: OrderEntity): Completable

}
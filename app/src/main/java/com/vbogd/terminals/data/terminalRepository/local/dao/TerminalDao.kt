package com.vbogd.terminals.data.terminalRepository.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vbogd.terminals.data.terminalRepository.local.entity.TerminalEntity
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface TerminalDao {

    @Query("SELECT * FROM terminals WHERE name LIKE '%' || :search || '%'")
    fun searchTerminal(search: String): Single<List<TerminalEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(terminals: List<TerminalEntity>): Completable

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveTerminal(terminalEntity: TerminalEntity): Completable

    @Query("SELECT * FROM terminals")
    fun getTerminals(): Single<List<TerminalEntity>>

    @Query("SELECT * FROM terminals WHERE id = :terminalId")
    fun getTerminalById(terminalId: String): Single<TerminalEntity?>
}
package com.vbogd.terminals.data.terminalRepository.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vbogd.terminals.data.terminalRepository.local.entity.TerminalEntity

@Dao
interface TerminalDao {

    @Query("SELECT * FROM terminals WHERE name LIKE '%' || :search || '%'")
    fun searchTerminal(search: String): List<TerminalEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAll(terminals: List<TerminalEntity>)

    @Query("SELECT * FROM terminals")
    fun getTerminals(): List<TerminalEntity>
}
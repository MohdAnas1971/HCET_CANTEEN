package com.example.masshcet.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface MemberDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMember(member:MemberEntity)

    @Update
    suspend fun updateMember(member:MemberEntity)

    @Delete
    suspend fun deleteMember(member: MemberEntity)

    @Query("SELECT * FROM Member WHERE name = :name")
   suspend fun getOneMember(name: String): MemberEntity

    @Query("SELECT * FROM Member ORDER BY startDate DESC")
    fun getAllMember(): Flow<List<MemberEntity>>
}
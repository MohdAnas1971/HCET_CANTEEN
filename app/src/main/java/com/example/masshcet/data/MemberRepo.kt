package com.example.masshcet.data


import kotlinx.coroutines.flow.Flow

class MemberRepo(
    private val memberDao:MemberDao
) {
    suspend fun addMember(member:MemberEntity)= memberDao.addMember(member=member)

    suspend fun updateMember(member:MemberEntity)=memberDao.updateMember(member=member)

    suspend fun deleteMember(member: MemberEntity)=memberDao.deleteMember(member)

   suspend fun getOneMember(name:String):MemberEntity = memberDao.getOneMember(name)

    fun getAllMember(): Flow<List<MemberEntity>> =memberDao.getAllMember()

}
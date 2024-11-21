package com.example.gs_mobile.repository

import com.example.prospapp.api.ProspecoApiService

class BandeiraTarifariaRepository(private val api: ProspecoApiService) {

    suspend fun getBandeiraTarifariaById(id: String) = api.getBandeiraTarifariaById(id)

    suspend fun updateBandeiraTarifaria(id: String, data: Map<String, Any>) = api.updateBandeiraTarifaria(id, data)

    suspend fun deleteBandeiraTarifaria(id: String) = api.deleteBandeiraTarifaria(id)

    suspend fun createBandeiraTarifaria(data: Map<String, Any>) = api.createBandeiraTarifaria(data)

    suspend fun getBandeirasTarifarias() = api.getBandeirasTarifarias()
}

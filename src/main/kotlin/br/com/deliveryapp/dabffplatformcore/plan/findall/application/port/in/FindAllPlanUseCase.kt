package br.com.deliveryapp.dabffplatformcore.plan.findall.application.port.`in`

import br.com.deliveryapp.dabffplatformcore.plan.findall.model.response.PlansResponse

interface FindAllPlanUseCase {

    fun findAll(page: Int, itemsPerPage: Int, cid: String): PlansResponse

}

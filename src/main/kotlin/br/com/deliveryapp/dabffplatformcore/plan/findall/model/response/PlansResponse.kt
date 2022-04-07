package br.com.deliveryapp.dabffplatformcore.plan.findall.model.response

import grpc.br.com.deliveryapp.FindAllPlanResponse
import java.math.BigDecimal

class PlansResponse(
    findAllPlanResponse: FindAllPlanResponse
) {
    val page: Int
    val itemsPerPage: Int
    val plans: Set<PlanResponse>

    init {
        this.page = findAllPlanResponse.page
        this.itemsPerPage = findAllPlanResponse.itemsPerPage
        this.plans = findAllPlanResponse.plansList.map { PlanResponse(it.id, it.name, BigDecimal(it.price)) }.toSet()
    }
}

data class PlanResponse(
    val id: Long,
    val name: String,
    val price: BigDecimal
)
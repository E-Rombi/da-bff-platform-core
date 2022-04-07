package br.com.deliveryapp.dabffplatformcore.plan.findall.application.service

import br.com.deliveryapp.dabffplatformcore.plan.findall.application.port.`in`.FindAllPlanUseCase
import br.com.deliveryapp.dabffplatformcore.plan.findall.model.response.PlansResponse
import grpc.br.com.deliveryapp.FindAllPlanRequest
import grpc.br.com.deliveryapp.PlanApiGrpc
import net.devh.boot.grpc.client.inject.GrpcClient
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import javax.validation.constraints.Positive

@Service
class FindAllPlanService : FindAllPlanUseCase {

    @GrpcClient("planService")
    private lateinit var planApiBlockingStub: PlanApiGrpc.PlanApiBlockingStub

    private val logger = LoggerFactory.getLogger(this::class.java)

    override fun findAll(page: Int, @Positive itemsPerPage: Int, cid: String): PlansResponse {


        logger.info("action=grpcCall, page=$page, itemsPerPage=$itemsPerPage, cid=$cid")
        val grpcResponse = planApiBlockingStub.findAllPlan(buildRequest(page, itemsPerPage, cid))

        return PlansResponse(grpcResponse)
    }

    private fun buildRequest(page: Int, itemsPerPage: Int, cid: String): FindAllPlanRequest? {
        return FindAllPlanRequest.newBuilder()
            .setPage(page)
            .setItemsPerPage(itemsPerPage)
            .setCid(cid)
            .build()
    }
}
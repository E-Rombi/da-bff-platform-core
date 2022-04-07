package br.com.deliveryapp.dabffplatformcore.plan.findall.adapter.`in`.web

import br.com.deliveryapp.dabffplatformcore.plan.findall.application.port.`in`.FindAllPlanUseCase
import br.com.deliveryapp.dabffplatformcore.plan.findall.model.response.PlansResponse
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/plans")
class FindAllPlanController(
    val findAllPlanUseCase: FindAllPlanUseCase
) {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping
    fun findAll(
        @RequestParam page: Int,
        @RequestParam itemsPerPage: Int,
        @RequestHeader(required = false) correlationId: String?
    ): ResponseEntity<PlansResponse> {
        val cid = correlationId ?: UUID.randomUUID().toString()

        return ResponseEntity.ok(findAllPlanUseCase.findAll(page, itemsPerPage, cid)).also {
            logger.info("action=requestEnd, cid=$cid")
        }
    }

}
package pt.isel.leic.daw.controllers.models

import pt.isel.leic.daw.repositories.GetPendingDTO
import pt.isel.leic.daw.repositories.PendingRequest

data class GetPendingOutputModel(
    val pendingGET: List<PendingRequest>,
    val pendingPOST: List<PendingRequest>,
    val pendingPUT: List<PendingRequest>,
    val pendingDELETE: List<PendingRequest>
) {
    constructor(dto: GetPendingDTO) : this(
        dto.pendingGET,
        dto.pendingPOST,
        dto.pendingPUT,
        dto.pendingDELETE
    )
}


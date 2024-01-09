package pt.isel.leic.daw.controllers.models

import pt.isel.leic.daw.repositories.ErrorRequest

data class GetErrorsOutputModel(
    val errors: List<ErrorRequest>
)

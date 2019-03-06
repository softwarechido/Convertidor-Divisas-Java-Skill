package convertidordivisas.extension

import com.amazon.ask.model.slu.entityresolution.StatusCode

val StatusCode?.isSuccessMatch: Boolean
    get() = this == StatusCode.ER_SUCCESS_MATCH
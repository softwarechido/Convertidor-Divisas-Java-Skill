package convertidordivisas.extension

import com.amazon.ask.model.slu.entityresolution.Resolution
import com.amazon.ask.model.slu.entityresolution.ValueWrapper

val Resolution?.isSuccessMatch: Boolean
    get() = this != null && status.code.isSuccessMatch

operator fun Resolution.get(index: Int): ValueWrapper = values[index]
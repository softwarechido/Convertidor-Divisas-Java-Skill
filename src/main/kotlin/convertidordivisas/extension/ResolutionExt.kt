package convertidordivisas.extension

import com.amazon.ask.model.slu.entityresolution.Resolution
import com.amazon.ask.model.slu.entityresolution.ValueWrapper

/**
 * Easy compare instead of writting the hole sentence in every if
 */
val Resolution?.isSuccessMatch: Boolean
    get() = this != null && status.code.isSuccessMatch

/**
 * Only talk to friends, remember?
 */
operator fun Resolution.get(index: Int): ValueWrapper = values[index]
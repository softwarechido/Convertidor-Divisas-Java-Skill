package convertidordivisas.extension

import com.amazon.ask.model.slu.entityresolution.ValueWrapper

val ValueWrapper.valueId: String
    get() = value.id
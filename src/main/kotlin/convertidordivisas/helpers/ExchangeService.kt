/*
     Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package convertidordivisas.helpers

import convertidordivisas.data.ExchangeRates

/**
 * Add default values just to avoid nulls and stuff on the parameters
 */
fun exchangeCall(source: String = "", dest: String = "", amount: Double = 0.0): Double? {
    return try {
        with(fetchExchangeRates(source)) {
            if (rates.isNotEmpty()) {
                val exchangeType = rates[dest] ?: return null
                amount * exchangeType
            } else {
                null
            }
        }
    } catch (e: Exception) {
        null
    }
}

/**
 * Just call and parse
 */
fun fetchExchangeRates(base: String = ""): ExchangeRates =
        parseExchangesRates(
                callURL("https://api.exchangeratesapi.io/latest?base=$base")
        )

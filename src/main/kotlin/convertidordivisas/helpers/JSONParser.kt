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
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import javax.script.ScriptException

@Throws(ScriptException::class)
fun parseExchangesRates(json: String): ExchangeRates {
    val script = "Java.asJSONCompatible($json)"
    val result = jsEngine().eval(script)

    return ExchangeRates(result as Map<String, Any>)
}

/**
 * Helper function to get easily the engine for JS and not have typos or errors
 */
fun jsEngine(): ScriptEngine =
        ScriptEngineManager()
                .getEngineByName("javascript")
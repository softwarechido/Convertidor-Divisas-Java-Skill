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

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

object URLCaller {
    @JvmStatic
    fun callURL(urlAsString: String): String {
        val content = StringBuilder()
        try {
            val url = URL(urlAsString)
            val urlConnection = url.openConnection()

            val input = BufferedReader(InputStreamReader(urlConnection.getInputStream()))

            var line: String? = input.readLine()
            while (line != null) {
                content.append(line).append("\n")
                line = input.readLine()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return content.toString()
    }
}
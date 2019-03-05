/*
     Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/
@file:JvmName("URLCaller")

package convertidordivisas.helpers

import convertidordivisas.extension.toUrl
import convertidordivisas.extension.fetchData

/**
 * Take an string and fetch the data. Return the data separated by line.
 *
 * @param urlString the string that represents a [URL]
 */
fun callURL(urlString: String): String = buildString {
    urlString.toUrl().fetchData {
        it.lineSequence().forEach { line ->
            append(line)
            append("\n")
        }
    }
}
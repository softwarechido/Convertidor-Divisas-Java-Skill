/*
     Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package convertidordivisas

import convertidordivisas.helpers.ConvertidorWebService

// @Test
fun main() {
    println("Convirtiendo EUR a USD 100")

    val resultado = ConvertidorWebService.convert("EUR", "USD", 100.0)
    val respuesta = String.format("%.2f", resultado)
    println(resultado)
    println(respuesta)

    //System.out.println("Convirtiendo Origen invalido");
    //resultado = ConvertidorWebService.convert("XXX", "USD", 100d);
    //respuesta = String.format("%.2f",resultado);
    //System.out.println(resultado);
    //System.out.println(respuesta);

    //System.out.println("Convirtiendo destino invalido");
    //resultado = ConvertidorWebService.convert("MXN", "XXX", 100d);
    //respuesta = String.format("%.2f",resultado);
    //System.out.println(resultado);
    //System.out.println(respuesta);
}

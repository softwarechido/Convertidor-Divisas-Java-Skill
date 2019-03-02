/*
     Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package convertidordivisas.driver;

import convertidordivisas.helpers.ConvertidorWebService;

public class Driver {

    // @Test
    public static void main(String[] args) {
        System.out.println("Convirtiendo EUR a USD 100");
        Double resultado = ConvertidorWebService.convertir("EUR", "USD", 100d);
        String respuesta = String.format("%.2f",resultado);
        System.out.println(resultado);
        System.out.println(respuesta);

        //System.out.println("Convirtiendo Origen invalido");
        //resultado = ConvertidorWebService.convertir("XXX", "USD", 100d);
        //respuesta = String.format("%.2f",resultado);
        //System.out.println(resultado);
        //System.out.println(respuesta);

        //System.out.println("Convirtiendo destino invalido");
        //resultado = ConvertidorWebService.convertir("MXN", "XXX", 100d);
        //respuesta = String.format("%.2f",resultado);
        //System.out.println(resultado);
        //System.out.println(respuesta);

    }º

}
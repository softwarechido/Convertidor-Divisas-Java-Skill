/*
     Copyright 2019 Amazon.com, Inc. or its affiliates. All Rights Reserved.

     Licensed under the Apache License, Version 2.0 (the "License"). You may not use this file
     except in compliance with the License. A copy of the License is located at

         http://aws.amazon.com/apache2.0/

     or in the "license" file accompanying this file. This file is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
     the specific language governing permissions and limitations under the License.
*/

package convertidordivisas.helpers;

import java.util.Map;

public class ConvertidorWebService {

    public static Double convertir(String origen, String destino, Double monto) {

        Double result;
        String contenido = URLCaller.callURL("https://api.exchangeratesapi.io/latest?base=" + origen);

        System.out.println(contenido);

        try {
            Map<String, Object> response = JSONParser.parseJson(contenido);
            if (null != response.get("error"))
                return null;
            else {
                Map<String, Object> rates = (Map<String, Object>) response.get("rates");
                Double tipoDeCambio = (Double) rates.get(destino);
                if (null == tipoDeCambio)
                    return null;

                result = monto * tipoDeCambio;
            }

        } catch (Exception e) {
            return null;
        }

        return result;

    }

}
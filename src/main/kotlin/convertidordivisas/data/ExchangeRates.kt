package convertidordivisas.data

class ExchangeRates(map: Map<String, Any>){
    val rates: Map<String, Double> by map
    val base: String by map
    val date: String by map
}
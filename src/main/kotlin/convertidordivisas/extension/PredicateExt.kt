package convertidordivisas.extension

import java.util.function.Predicate

/**
 * Infix functions allow readability. For example, this removes the small method call and adds the option
 * to do infix calling. Which makes the calls look like sentences and it's more clear in the intention.
 */
infix fun <T> Predicate<T>.orOther(other: Predicate<T>): Predicate<T> = or(other)

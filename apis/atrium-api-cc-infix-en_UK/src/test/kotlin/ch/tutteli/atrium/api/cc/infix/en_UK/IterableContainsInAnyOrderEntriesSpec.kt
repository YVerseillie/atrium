package ch.tutteli.atrium.api.cc.infix.en_UK

import ch.tutteli.atrium.AssertionVerbFactory
import ch.tutteli.atrium.creating.AssertionPlant
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.include
import kotlin.reflect.KFunction2

class IterableContainsInAnyOrderEntriesSpec : Spek({

    include(BuilderSpec)
    include(ShortcutSpec)

}) {
    object BuilderSpec : ch.tutteli.atrium.spec.assertions.IterableContainsInAnyOrderEntriesSpec(
        AssertionVerbFactory,
        getEntriesPair(),
        "[Atrium][Builder] "
    )

    object ShortcutSpec : ch.tutteli.atrium.spec.assertions.IterableContainsInAnyOrderEntriesSpec(
        AssertionVerbFactory,
        getEntriesShortcutPair(),
        "[Atrium][Shortcut] "
    )

    companion object : IterableContainsSpecBase() {
        fun getEntriesPair()
            = "$the ${Entries::class.simpleName}" to Companion::entries

        private fun entries(plant: AssertionPlant<Iterable<Double>>, a: AssertionPlant<Double>.() -> Unit, aX: Array<out AssertionPlant<Double>.() -> Unit>): AssertionPlant<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant to contain inAny order atLeast 1 entry a
            } else {
                plant to contain inAny order atLeast 1 the Entries(a, *aX)
            }
        }

        private fun getContainsShortcutName(): String {
            val f: KFunction2<AssertionPlant<Iterable<Double>>, AssertionPlant<Double>.() -> Unit, AssertionPlant<Iterable<Double>>> = AssertionPlant<Iterable<Double>>::contains
            return f.name
        }

        fun getEntriesShortcutPair()
            = getContainsShortcutName() to Companion::entriesShortcut

        private fun entriesShortcut(plant: AssertionPlant<Iterable<Double>>, a: AssertionPlant<Double>.() -> Unit, aX: Array<out AssertionPlant<Double>.() -> Unit>): AssertionPlant<Iterable<Double>> {
            return if (aX.isEmpty()) {
                plant contains a
            } else {
                plant contains Entries(a, *aX)
            }
        }
    }
}

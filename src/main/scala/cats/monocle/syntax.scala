package cats.monocle

import cats.data.State
import cats.{Monoid, Semigroup}
import monocle.Lens

object syntax {

  implicit class MonocleLensOps[A, B](val l: Lens[A, B]) extends AnyVal {

    def :=(a: B): State[A, B] = MonocleLens.set(l)(a)

    def +=(a: B)(implicit S: Semigroup[B]): State[A, B] = MonocleLens.plus(l)(a)(S)

    def zero(implicit M: Monoid[B]): State[A, B] = MonocleLens.zero(l)(M)

    def %%=[C](st: State[B, C]): State[A, C] = MonocleLens.transform(l)(st)

  }

}

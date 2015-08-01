package cats.monocle

import cats.{Monoid, Semigroup}
import cats.state.State
import cats.std.function._
import monocle.Lens


trait MonocleLensFunctions {

  def set[A, B](l: Lens[A, B])(a: B): State[A, B] = State(s => (l.set(a)(s), a))

  def transform[A, B, C](l: Lens[A, B])(st: State[B, C]): State[A, C] = State(
    s =>
      st.run(l.get(s)).map {
        case (s1, a) => (l.set(s1)(s), a)
      }.run
  )

  def plus[A, B](l: Lens[A, B])(a: B)(implicit S: Semigroup[B]): State[A, B] = State {
    s =>
      val a1 = S.combine(l.get(s), a)
      (l.set(a1)(s), a1)
  }

  def zero[A, B](l: Lens[A, B])(implicit M: Monoid[B]): State[A, B] = set(l)(M.empty)

}

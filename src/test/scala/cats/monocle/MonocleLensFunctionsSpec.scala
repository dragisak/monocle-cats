package cats.monocle

import monocle.macros.Lenses
import org.scalatest._
import Matchers._

class MonocleLensFunctionsSpec extends WordSpec {

  import MonocleLens._
  import cats.instances.all._

  @Lenses case class Address(street: String, city: String, zip: Int)

  @Lenses case class Person(name: String, address: Address)

  "MonocleLens" should {

    "set" in {

      val address = Address("1 Market St.", "Foo", 12345)

      val s1    = "2 Central St."
      val state = set(Address.street)(s1)

      val (a2, s) = state.run(address).value

      a2 shouldBe Address(s1, "Foo", 12345)
      s shouldBe s1
    }

    "transform" in {

      val address = Address("1 Market St.", "Foo", 12345)
      val person  = Person("R2D2", address)
      val s1      = "2 Central St."

      val state1 = set(Address.street)(s1)

      val state2 = transform(Person.address)(state1)

      val (p2, s) = state2.run(person).value

      p2 shouldBe Person("R2D2", Address(s1, "Foo", 12345))
      s shouldBe s1
    }

    "plus" in {

      val address = Address("1 Market St.", "Foo", 12345)

      val st = plus(Address.zip)(1)

      val (s1, a) = st.run(address).value

      s1 shouldBe Address("1 Market St.", "Foo", 12346)
      a shouldBe 12346
    }

    "zero" in {

      val address = Address("1 Market St.", "Foo", 12345)

      val st = zero(Address.zip)

      val (s1, a) = st.run(address).value

      s1 shouldBe Address("1 Market St.", "Foo", 0)
      a shouldBe 0
    }
  }

}

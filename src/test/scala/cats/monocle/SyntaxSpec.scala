package cats.monocle

import monocle.macros.Lenses
import org.scalatest.{Matchers, WordSpec}
import scala.language.postfixOps

class SyntaxSpec extends WordSpec with Matchers{

  import syntax._
  import cats.std.all._

  @Lenses case class Address(street: String, city: String, zip: Int)

  @Lenses case class Person(name: String, address: Address)

  "MonocleLens syntax" should {
    ":=" in {
      val st = Address.street := "Foo"

      val (a1, s) = st.run(Address("Yar", "Far", 123)).run
      a1 shouldBe Address("Foo", "Far", 123)
      s shouldBe "Foo"
    }

    "%%=" in {
      val st = Address.street := "Foo"
      val st2 = Person.address %%= st

      val (p1, s) = st2.run(Person("Will", Address("Yar", "Far", 123))).run
      p1 shouldBe Person("Will", Address("Foo", "Far", 123))
      s shouldBe "Foo"
    }

    "+=" in {
      val st = Address.zip += 1000

      val (a1, s) = st.run(Address("Yar", "Far", 123)).run
      a1 shouldBe Address("Yar", "Far", 1123)
      s shouldBe 1123
    }

    "zero" in {
      val st = Address.zip zero

      val (a1, s) = st.run(Address("Yar", "Far", 123)).run
      a1 shouldBe Address("Yar", "Far", 0)
      s shouldBe 0
    }

  }

}

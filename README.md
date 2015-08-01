# Monocle Lenses and Cats State

This library brings together Monocle Lenses and Cats project.

Example:

```scala
@Lenses case class Address(street: String, city: String, zip: Int)
@Lenses case class Person(name: String, address: Address)

val setStreet(s: String) = Address.street := s

val setPersonStreet(s: String) = Person.address %%= setStreet(s)

```

## Copyright and License

All code is available to you under the MIT license, available at [http://opensource.org/licenses/MIT](http://opensource.org/licenses/MIT) and also
in the LICENSE file.

Copyright Dragisa Krsmanovic, 2015.

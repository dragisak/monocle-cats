# Monocle Lenses and Cats State

[![Build Status](https://travis-ci.org/dragisak/monocle-cats.svg)](https://travis-ci.org/dragisak/monocle-cats) [![Coverage Status](https://coveralls.io/repos/dragisak/monocle-cats/badge.svg?branch=master&service=github)](https://coveralls.io/github/dragisak/monocle-cats?branch=master)

This library brings together [Monocle Lenses](https://github.com/julien-truffaut/Monocle)  and [Cats](https://github.com/non/cats) project.

## Use

```scala
libraryDependencies += "com.dragishak" %% "monocle-lens % "1.0"
```

## Example

```scala
import cats.monocle.syntax._
import cats.instances.all._
import monocle.macros.Lenses

@Lenses case class Address(street: String, city: String, zip: Int)
@Lenses case class Person(name: String, age:Int, address: Address)

val setStreet = Address.street := "13 Main St."
val incrementAge = Person.age += 1

val state = for {
  newStreetName <- Person.address %%= setStreet
  newAge        <- incrementAge
} yield(newStreetName, newAge)

val person = Person("Alice", 30, Address("1 Main St", "San Francisco", 94123))

val (changedPerson, (newStreet, newAge)) = state.run(person).value

```

## Copyright and License

All code is available to you under the MIT license, available at [http://opensource.org/licenses/MIT](http://opensource.org/licenses/MIT) and also
in the LICENSE file.

Copyright Dragisa Krsmanovic, 2016.

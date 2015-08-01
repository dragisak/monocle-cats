# Monocle Lenses and Cats State

[![Build Status](https://travis-ci.org/dragisak/monocle-cats.svg)](https://travis-ci.org/dragisak/monocle-cats) [![Coverage Status](https://coveralls.io/repos/dragisak/monocle-cats/badge.svg?branch=master&service=github)](https://coveralls.io/github/dragisak/monocle-cats?branch=master)

This library brings together [Monocle Lenses](https://github.com/julien-truffaut/Monocle)  and [Cats](https://github.com/non/cats) project.

Example:

```scala
@Lenses case class Address(street: String, city: String, zip: Int)
@Lenses case class Person(name: String, address: Address)

def setStreet(s: String) = Address.street := s

def setPersonStreet(s: String) = Person.address %%= setStreet(s)

```

## Copyright and License

All code is available to you under the MIT license, available at [http://opensource.org/licenses/MIT](http://opensource.org/licenses/MIT) and also
in the LICENSE file.

Copyright Dragisa Krsmanovic, 2015.

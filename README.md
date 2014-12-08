#scalajs-rx-idb


Indexed Database reactive (Rx) wrapper written in [scala.js](1) using [monifu](2), [uPickle](3) and [uTest](4).

Primarily it is trying to be :

##type safe

* thanks to [uPickle](3) and its Reader/Writer type classes user just declares input/return types and uPickle does the rest. It even allows you to deserialize objects in a type safe manner without knowing what type of object you're gonna get from database (See uPickle's tagged values in sealed hierarchies)
* a key validation type class doesn't let you store keys of unsupported types

##user friendly because

* there is too much mutability and confusion regarding request result value, versioning, transactions and error handling in IndexedDb API
* no living soul wants to spend 3 hours trying to reliably check whether a database exists
* it should prevent lock starvation that I spent literally days to put up with already
* it should supervise transaction boundaries. There are a few edge cases though I haven't covered yet, [I asked a question on SO](http://stackoverflow.com/questions/27326698/indexeddb-transaction-auto-commit-behavior-in-edge-cases)  
* Rx based API has a clean contract by definition

##handling asynchrony, resilience and back-pressure the Rx way because 

* IndexedDb API imho leads to inevitable callback hell and I couldn't really say when it crashes and why
* it makes it easier to implement new features like profiling
* you get a full control over returned data streams in form of higher-order functions
* thanks to Monifu's back pressure implementation you get a way to asynchronously processing results requested lazily with the possibility to cancel. 

In other words, doing complicated stuff with IndexedDb directly is not that easy as one might expect.

NOTE: 

* It currently depends on scala-js 0.6.0-SNAPSHOT and unaccepted [utest PR](https://github.com/lihaoyi/utest/pull/40)
* Just the basic operations are implemented and tested so far, it's a work in progress
* Before trying, please wait until it depends on scala-js 0.6.0 milestone version otherwise you're gonna spend some time with it !


  [1]: http://www.scala-js.org
  [2]: http://www.monifu.org
  [3]: https://github.com/lihaoyi/upickle
  [4]: https://github.com/lihaoyi/utest
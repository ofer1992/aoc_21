# Problem LOG
Here I will log my thoughts about the problems and my solutions

## Day 4 - 10/03/24
I was quite stumped. I'm used to array manipulations in python with numpy and that paradigm doesn't translate directly to clojure (though to be fair, using lists of lists in python would also not transfer from numpy). I ended up spending a lot of time puzzled by enumerating values and then getting rid of enumeration or the values. Made me think that it's not the clojure way.

Also some questions about efficiency, but since the problem is small I take the approach of "better a clean solution than a scalable one".

For future reference, [here](https://github.com/wagdav/advent-of-code-2021/blob/main/src/aoc2021/day04.clj) is a solution that uses the same problem representation (2d arrays for boards) and is much cleaner.

## Day 1-3
Pretty straightforward. In day 3 I didn't use some existing functions. After day 3 I started using `clojure.test`.
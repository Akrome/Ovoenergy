Hi Ovo!

Here is the source code for my attempt at the Programming challenge found at
https://github.com/oblackwood/coding-exercise

Looking at the questions in the challenge, I felt that they were kind of a data-exploration nature,
which is normally something you'd code by scripting and trial-and-error (read: hacking) while you look for pattern.

At the same time, this is in theory supposed to be showing off production-style code.

So I solved it twice: one is in the "simple" package, and it's a straight Scala script that attacks the problem:
it's aimed at simplicity, rather than supporting changes or maintainability

Then there is the "shapeless" package: there you'll find a more "professional" solution. I implemented it using
shapeless, because browsing your tech blog that felt like something you use a lot. I don't have much shapeless
experience, and there are not many comments in my code, so for your reference and transparency I'll let you know
that I roughly followed the approach outlined here: https://www.becompany.ch/en/blog/2016/07/13/csv-parsing, with
some improvement, mostly syntax overhead, remove the Akka dependency and supporting multiple errors at the parser level
(I made some assumption about data constraints). The "shapeless" package, being more professional, comes with tests
(TDD style).

One key assumption I made is that it's OK to load all data into memory. This is probably not true in most real-world
scenarios.

I have wrapped the entries fields in value class types for type safety. In the "simple" case I probably wouldn't have
done that, but I already had the bean defined and did not want to duplicate it just for that.

I also ditched the dependency on external CSV libraries due to the relatively simple formatting of the data. In a
real real-world scenario, I probably would use an external dependency to parse the CSV instead of implementing my own
splitter.

There is a single git commit because I was not on git initially: my laptop power adapter died and I had to use a spare
for the test, one on which git wouldn't install right .In the end I had to give up and install the github for windows
app, which I completely distrust and use the least possible, hence the single commit.
I'll be happy to talk you through the development steps if you wish.

If you have any questions, I'm available to answer them :)

Hope to see you soon,

D.
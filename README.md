# Graph Colouring {

The graph colouring problem has many applications, including event scheduling, assigning
radio frequencies to stations, and task/processor allocation. In this lab you will explore two
aspects of this problem.

You have been asked to handle the seating arrangements for the next dinner party of the
QUEEN'S Ultimately Exhausting Endlessly Nested Society (in which “QUEEN'S” stands for
“QUEEN'S Ultimately Exhausting Endlessly Nested Society” - it's the club for people who
love infinite recursion). The club has been wracked by political wrangling lately and a
number of deep personal conflicts have developed. You have a graph in which each person is
represented by a vertex, and edges represent personal conflicts. Vertices are connected by an
edge if the people they represent dislike each other so much they cannot possibly sit at the
same table. There are a lot of edges in the graph.

## Part 1: 2-Colouring
 
The treasurer of your club is a real cheapskate. He wants to rent as few tables as possible (all
tables cost the same to rent, regardless of size) so he wants you to find a way to seat all the
people using just two tables if possible.

Create a polynomial-time algorithm that will determine if the graph can be 2-coloured, and
find a valid 2-colouring if one exists. (Hint: pick a vertex and colour it red. All of its
neighbours must be blue. All of their neighbours must be red, etc. ... Does any vertex end up
needing to be both red and blue? If so, there is no valid 2-colouring.)

Demonstrate the correctness of your algorithm by testing it on Graph1 and Graph2, which are
posted on the course website. One of these can be 2-coloured, and one cannot. See below for
information regarding the format of these files..

## Part 2: Pseudo-Minimal Colouring

Even if two tables will not suffice, the treasurer still wants you to find a seating arrangement
that uses the minimal number of tables. Luckily you are able to convince him that finding the
minimal number of tables needed is a very difficult problem – its decision version is NPComplete,
so we don't believe there is any polynomial-time algorithm for it.

However, we can certainly design algorithms that try to find a “good” seating arrangement –
that is , a colouring of the graph that hopefully uses a small number of colours. Here is one
such algorithm:

```
def easy_colour(G):
  n = # of vertices in G
  Colours = {1,2,3,...,n}
  Sort the vertices in order of decreasing degree
    # i.e. the one with the most neighbours is first
  Let L be the sorted list of vertices
  for v in L:
    Col(v) = smallest value in Colours that has not been used so
              far on any of v's neighbours
  return Col()
```

The rationale behind this algorithm is that if we leave a high-degree vertex until the end, we
may be forced to use a new colour for it because its neighbours have already been coloured
with all different colours. By colouring the high-degree vertices first, we can try to avoid this
problem.

Determine the computational complexity of your algorithm, and state it in your program’s
documentation.

Part of the problem with algorithms like this is that it is difficult to determine how good they
are. It is easy to show that the algorithm sometimes uses more colours than necessary. (For
example we can create a small tree that needs only 2 colours, but for which easy_colour uses 3
colours.)

Test the algorithm by applying it to Graph3, Graph4 and Graph5, and output the number of
colours used in each case. Each of these graphs can be coloured with no more than 25 colours
(and possibly fewer). 

# }

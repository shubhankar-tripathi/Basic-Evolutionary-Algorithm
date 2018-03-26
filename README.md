# Basic Evolutionary Algorithm
## Goals of this Project 
- Implement a basic evolutionary algorithm.
- Make sure that the implementation leverages the OOP techniques we studied in order to enable future extensions (even unforeseen ones).
- Experiment without restriction to determine which OOP techniques are suitable or not. The goal is to gain hands-on experience with as many OOP techniques as possible during the course of this practice project. 
## What is an Evolutionary Algorithm? 
Let us assume we have a fitness function F which takes a candidate solution and returns a value representing how good the candidate solution is at solving the underlying optimization problem F encodes. As we get started, we will define a candidate solution as a binary vector of n bits. If n = 10, an example of a random candidate solution would be 0101110010. The problem we will try to solve is called ONE-MAX and it is very simple, thus allowing us to more easily debug our first implementations. Given a binary string of length n, its value as a solution to ONE-MAX is simply the sum of its binary digits divided by n. As the name of the problem implies, we are therefore optimizing by searching the space of all possible n-bits long binary vector for the one containing all ones. 

Let’s take a few examples of computing the fitness value of different random candidate solutions; 
- F(0101110010) = 5 / 10 = 0.5 
- F(1111100000) = 5 / 10 = 0.5 
- F(0000000000) = 0 / 10 = 0 
- F(1111111111) = 10 / 10 = 1

Of course, we know the definition of F so we could just infer what the best candidate solution is. In real applications of Evolutionary Algorithms, the fitness function is provided as a “black box”; it returns the fitness of any given candidate solution it is passed but we can’t analyze its implementation or mathematical properties.  

Similarly, even if F is a black box, with sufficiently small values of n the problem would still be trivial; just start with 000…000 then enumerate all possible binary vectors. For each of them, compute the fitness value and keep track of the best one you found so far. However, as n increases, the size of the underlying search space also increases but much faster: 2n. As such, for large values of n we quickly end up with an enumeration that would take centuries to be performed on modern hardware. This is something those of you who ever heard of algorithms complexity analysis already know.  So we are going to use instead an Evolutionary Algorithm to find the best candidate solution. 

One of the simplest form of Evolutionary Algorithm may be defined as follows. For those of you who are curious, this variant is known as a steady state evolutionary algorithm; 
- Initialize popsize random candidate solution, each a binary vector of n bits. Store them together as the current population P 
- Iterate over all candidate solutions in P and compute their fitness, using the given function F. Store the fitness value along with the candidate solution in P. 
- For g = 1 to maxgenerations 
  - *Select for breeding* two distinct candidate solutions from P we will refer to them as Parents p1 and p2 
  - *Apply Crossover* to p1 and p2, thus generating Offspring  o1 and o2 
  - *Apply mutation* to o1 
  - *Apply mutation* to o2 
  - *Select for replacement* a candidate solution from P, replace it by o1 
  - *Select for replacement* a candidate solution from P, replace it by o2
  
We have marked in italics various procedures that are needed by the algorithm:
- *Select for breeding* will work by picking two random candidate solutions from P then comparing their fitness. The winner is the candidate solution that is selected for breeding. This variant is called the K-Tournament. The value of K is 2 in this example but can be parameterized to be anything up to popsize. We will use as parameters TournamentSize as the value of K and ensure it is > 0 and < popsize. We will also assume that there is another parameter BreedingMethod set to value KTournament. As we keep working on these practice projects we will implement different breeding methods and tell the algorithm which one to use by this parameter. 
- *Apply Crossover* takes as parameters two candidate solutions. With a probability CrossoverRate, it will creates two offspring by exchanging genes between them. Otherwise it will simply have the offspring be the parents, untouched. For instance, given 0000 and 1111, the crossover may decide to split each in the middle and exchange the resulting segments thus leading to offspring 0011 and 1100. This method of crossover is called one-point-crossover since it works by picking an index randomly then create offspring by exchanging the segments before this index in both parents. We will, once again, eventually implement different methods so we will need a parameter CrossoverMethod which, for this example, will have an integer value 1 representing the fact that we are using one-point crossover. 
- *Apply Mutation* takes as parameter one candidate solution at a time. The MutationMethod we will implement first simply iterates over all bits and, with a probability MutationRate, flips each. 
- *Select for Replacement* will pick one candidate solution in P that will be replaced by the candidate solution it is given. The criterion with which to perform this selection will be specified by parameter ReplacementMethod. In our first implementation that method will be WorstFitness meaning that we select always the candidate solution with worst fitness. 

The other parameters of the algorithms, and their default values, are:
- n = 500 
- popsize = 100 
- maxgenerations = 5000 
- Fitness = OneMax 

The following resources can provide further background and introduction to evolutionary algorithms: 
-  http://www.perlmonks.org/?node_id=298877 
- http://cims.nyu.edu/~gn387/glp/lec3.pdf 
- http://www.cs.vu.nl/~gusz/ecbook/Eiben-Smith-Intro2EC-Ch2.

Monon is an order pair that represent the coeffcient and the power in the phrase ax^b
this class can also build Monom from a given string such as ax^b without spaces when "a" is a double and "b" is a positive int
this class have a regular constructor(given a and b), a string constructor and a copy constructor. it can get and set the power and the coeffcient.
in addition it can do:
add and multiply the monon with another Monom
derivative the Monom
get the value in the given x
check if this is the Zero Monom
return a string that represent the Monom
check equality with another Monom
and get a new zero Monom
initfromstring that gets a string and return the monom
 

Polynom is an ArrayList of Monom (every _cell_ represent a diffrent power)
this class can also build Polynom from a given string such as ax^b+ax^b+... without spaces when every Monom should be in accordence with the rules of the Monom.
this class have an empty constructor(create an empty Polynom) and a string constructor..
in addition it can do:
add and multiply the Polynom with Monom
add, substract and multiply the Polynom with another Polynom
derivative the Polynom
get the value in the given x
check if this is the Zero Polynom
return a string that represent the Polynom
check equality with another Polynom
check if the Polynom contains a given power
get the root and the area of the Polynom between a given two numbers
return a copy
make an iterator
initfromstring that gets a string and return the polynom


ComplexFunction has 3 variables, function left, function right,and operation.the functions can be Monom, Polynom or ComplexFunction. the operation is an enom that contains {Plus, Times, Divid, Max, Min, Comp , None, Error}.
 this class can also build ComplexFunction from a given String such as div(mul(x,2x),x), or x^2+5(the bulider applys the polynom in function left , right is null, and the operation is none)
 this class has two more constuctors, a regular constructor that get two functions and a string of the operation.and a constructor that gets only one function.
the class also has:
a get funcion for every variable.
a function for every opration(except none and error) that recieves another function and adds the complex function with the function according the operation.
toString function
f- that returns the value in the given x
copy
initfromstring that gets a string and returns the complexfunction


Function_GUI is an ArrayList of functions(complexFunction,Polynom, or Monom).it has all the functions that a collaction in java has.
in addition it can do:
init from file
save to file
draw functions given:int width, int height, Range rx, Range ry, int resolution.
and draw function givin a json file

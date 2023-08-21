package com.example.kotlinbasics



    fun main() {
        println("Hello, world!!!")
        // This is a comment. Comments start with //
        // val is used for variables which are immutable (not changable)
        //val myName = "Denis"


        var myName = "Frank"
        print("Hello " + myName)


        // myName = "Michael" // ERROR: Val cannot be reassigned

        /* This is a multi line comment starts with /* and ends with *'/ without the '
         var is used for variables which are mutable/changeable.
         kotlin is a strongly typed language that supports Type Inference.
         The compiler will directly assume the size and type to a variable by that.
         For example, if you assign any whole number to a variable,
         the compiler will directly assume that the variable is of type Int
        */ */


        var myAge = 31

        // Integer TYPES: Byte (8 bit), Short (16 bit), Int (32 bit), Long (64 bit)
        val myByte: Byte = 13
        val myShort: Short = 125
        val myInt: Int = 123123123
        val myLong: Long = 12_039_812_309_487_120

        // Floating Point number Types: Float (32 bit), Double (64 bit)
        val myFloat: Float = 13.37F
        val myDouble: Double = 3.14159265358979323846

        // Booleans the type Boolean is used to represent logical values.
        // It can have two possible values true and false.
        var isSunny: Boolean = true
        // not sunny anymore...
        isSunny = false

        // Characters
        val letterChar = 'A'
        val digitChar = '1'

        // Strings
        val myStr = "Hello World"
        var firCharInStr = myStr[0]
        print("First Character")
        var myLength = myStr.length
        var lastCharInStr = myStr[myStr.length - 1]
        print("last character" + lastCharInStr)
        print ("FIrst character $firCharInStr and the length of mystr is ${myStr.length}")

        //Arithmetic Operators (+,-,*,/,%)
        var result = 5 + 3
        val a = 5.0
        var b = 3
        var resultDouble :Double
        resultDouble = a / b

        print(resultDouble)

        val isEqual = 5 == 3
        val isNotequal = 5!=5
        println("isNotEqual isNotEqual")
        println("isEqual is " + isEqual)





    }





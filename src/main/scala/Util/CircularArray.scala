package Util

class CircularArray[T: Manifest](size: Int) {
    private val array: Array[T] = new Array[T](size)
    private var currentIndex: Int = 0

    // Ubacivanje elementa u array
    def insert(element: T): Unit = {
        array(currentIndex) = element
        currentIndex = (currentIndex + 1) % size
    }

    // Funkcija za dohvacanje elementa na odredenom indexu
    def get(index: Int): T = {
        if (size > 0) {
            val circularIndex = (currentIndex + index) % size
            array(circularIndex)
        } else {
            throw new IndexOutOfBoundsException("Circular array is empty")
        }
    }

    // Funkcija za postavljanje elementa na odredenom indexu
    def set(index: Int, element: T): Unit = {
        if (size > 0) {
            val circularIndex = (currentIndex + index) % size
            array(circularIndex) = element
        } else {
            throw new IndexOutOfBoundsException("Circular array is empty")
        }
    }

    // Funkcija za printanje arraya
    def printArray(): Unit = {
        for (i <- 0 until size) {
            print(get(i) + "\t")
        }
        println()
    }
}

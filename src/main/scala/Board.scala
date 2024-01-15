import Util.CircularArray

class Board {
    private val boardSpaces: CircularArray[Piece] = new CircularArray[Piece](40)

    private val safeHouseEntrances: Map[String, Int] = Map(
        "green" -> 39,
        "red" -> 9,
        "blue" -> 19,
        "yellow" -> 29
    )

    private val startingPositions: Map[String, Int] = Map(
        "green" -> 0,
        "red" -> 10,
        "blue" -> 20,
        "yellow" -> 30
    )

    def initializeBoard(): Unit = {

        for (index <- 0 until 40) {
            boardSpaces.insert(new Piece(index, "Empty", "Empty"))
        }

    }

    // Funkcija za ubacivanje figure na pocetak
    def insertPieceAtStart(piece: Piece, color: String): Unit = {
        startingPositions.get(color).foreach { startPosition =>
            if (boardSpaces.get(startPosition).getColor() == "Empty") {
                boardSpaces.set(startPosition, piece)
                println(s"Piece ${piece.getPieceName()} inserted at the starting position for $color.")
            } else {
                println(s"Starting position for $color is occupied. Cannot insert piece.")
            }
        }
    }

   

    //Pomjeranje
    def movePiece(piece: Piece, steps: Int): Unit = {
        val currentPosition = findPiecePosition(piece)

        // Jel na ploci
        if (currentPosition.isDefined) {
            val newPosition = (currentPosition.get + steps) % 40

            // Jel u kucici
            if (safeHouseEntrances.values.toList.contains(newPosition)) {
                val color = piece.getColor()
                val safeHouseColor = safeHouseEntrances.find { case (_, position) => position == newPosition }.map(_._1)

                if (safeHouseColor.contains(color)) {
                    println(s"Piece ${piece.getPieceName()} reached its own safe house.")
                } else {
                    boardSpaces.set(currentPosition.get, new Piece(currentPosition.get, "Empty", "Empty"))
                    boardSpaces.set(newPosition, piece)
                    println(s"Piece ${piece.getPieceName()} moved to position $newPosition.")
                    return 
                }
            } else {
                boardSpaces.set(currentPosition.get, new Piece(currentPosition.get, "Empty", "Empty"))
                boardSpaces.set(newPosition, piece)
                println(s"Piece ${piece.getPieceName()} moved to position $newPosition.")
            }
        } else {
            println(s"Piece ${piece.getPieceName()} is not on the board.")
        }
    }





    def findPiecePosition(piece: Piece): Option[Int] = {
        (0 until 40).find(index => {
            val boardPiece = boardSpaces.get(index)
            boardPiece != null && boardPiece.pieceId == piece.pieceId && boardPiece.color == piece.color
        })
    }

    // Funkcija za printanje situacije na ploci
    def printBoard(): Unit = {
        for (i <- 0 until 40) {
            print(s"${boardSpaces.get(i).getPieceName()}\t")
            if ((i + 1) % 10 == 0) {
                println()
            }
        }
    }

    def printBoard2(): Unit = {
        for (i <- 0 until 10) {
            for (j <- 0 until 4) {
                val index = i + j * 10
                val piece = boardSpaces.get(index)
                print(getSymbol(piece) + "\t")
            }
            println()
        }
    }

    def printBoard3(): Unit = {
        val path: String = "  \u001b[37mO\u001b[0m  "
        val greenO: String = "  \u001b[32mO\u001b[0m  "
        val redO: String = "  \u001b[31mO\u001b[0m  "
        val yellowO: String = "  \u001b[33mO\u001b[0m  "
        val blueO: String = "  \u001b[34mO\u001b[0m  "
        val spacing: String = "     "
        val startingPos: String = "  \u001b[90mO\u001b[0m  "

        val RP1: String = " \u001b[31mR\u001b[0m\u001b[31mP\u001b[0m\u001b[31m1\u001b[0m "
        val RP2: String = " \u001b[31mR\u001b[0m\u001b[31mP\u001b[0m\u001b[31m2\u001b[0m "
        val RP3: String = " \u001b[31mR\u001b[0m\u001b[31mP\u001b[0m\u001b[31m3\u001b[0m "
        val RP4: String = " \u001b[31mR\u001b[0m\u001b[31mP\u001b[0m\u001b[31m4\u001b[0m "
        val BP1: String = " \u001b[34mB\u001b[0m\u001b[34mP\u001b[0m\u001b[34m1\u001b[0m "
        val BP2: String = " \u001b[34mB\u001b[0m\u001b[34mP\u001b[0m\u001b[34m2\u001b[0m "
        val BP3: String = " \u001b[34mB\u001b[0m\u001b[34mP\u001b[0m\u001b[34m3\u001b[0m "
        val BP4: String = " \u001b[34mB\u001b[0m\u001b[34mP\u001b[0m\u001b[34m4\u001b[0m "
        val YP1: String = " \u001b[33mY\u001b[0m\u001b[33mP\u001b[0m\u001b[33m1\u001b[0m "
        val YP2: String = " \u001b[33mY\u001b[0m\u001b[33mP\u001b[0m\u001b[33m2\u001b[0m "
        val YP3: String = " \u001b[33mY\u001b[0m\u001b[33mP\u001b[0m\u001b[33m3\u001b[0m "
        val YP4: String = " \u001b[33mY\u001b[0m\u001b[33mP\u001b[0m\u001b[33m4\u001b[0m "
        val GP1: String = " \u001b[32mG\u001b[0m\u001b[32mP\u001b[0m\u001b[32m1\u001b[0m "
        val GP2: String = " \u001b[32mG\u001b[0m\u001b[32mP\u001b[0m\u001b[32m2\u001b[0m "
        val GP3: String = " \u001b[32mG\u001b[0m\u001b[32mP\u001b[0m\u001b[32m3\u001b[0m "
        val GP4: String = " \u001b[32mG\u001b[0m\u001b[32mP\u001b[0m\u001b[32m4\u001b[0m "


        val paths: Seq[String] = (0 until 40).map(index => getColoredPieceName(boardSpaces.get(index)))


        println(YP1 + YP2 + spacing + spacing + paths(38) + paths(39) + paths(0) + spacing + spacing + GP1 + GP2)
        println()
        println(YP3 + YP4 + spacing + spacing + paths(37) + greenO + paths(1) + spacing + spacing + GP3 + GP4)
        println()
        println(spacing + spacing + spacing + spacing + paths(36) + greenO + paths(2) + spacing + spacing + spacing + spacing)
        println()
        println(spacing + spacing + spacing + spacing + paths(35) + greenO + paths(3) + spacing + spacing + spacing + spacing)
        println()
        println(paths(30) + paths(31) + paths(32) + paths(33) + paths(34) + greenO + paths(4) + paths(5) + paths(6) + paths(7) + paths(8))
        println()
        println(paths(29) + yellowO + yellowO + yellowO + yellowO + spacing + redO + redO + redO + redO + paths(9))
        println()
        println(paths(28) + paths(27) + paths(26) + paths(25) + paths(24) + blueO + paths(14) + paths(13) + paths(12) + paths(11) + paths(10))
        println()
        println(spacing + spacing + spacing + spacing + paths(23) + blueO + paths(15) + spacing + spacing + spacing + spacing)
        println()
        println(spacing + spacing + spacing + spacing + paths(22) + blueO + paths(16) + spacing + spacing + spacing + spacing)
        println()
        println(BP1 + BP2 + spacing + spacing + paths(21) + blueO + paths(17) + spacing + spacing + RP1 + RP2)
        println()
        println(BP3 + BP3 + spacing + spacing + paths(20) + paths(19) + paths(18) + spacing + spacing + RP3 + RP4)
    }

    // Simbol za printanje
    private def getSymbol(piece: Piece): String = {
        if (piece != null) {
            piece.getPieceName()
        } else {
            "Empty"
        }
    }



    // Simbol za figuru sa bojom
    private def getColoredPieceName(piece: Piece): String = {
        if (piece != null) {
            if (piece.getPieceName() == "Empty") {
                "  \u001b[37mO\u001b[0m  " // BIJELO "O" za prazno
                        } else {
                val colorCode = getColorCode(piece.getColor())
                s" $colorCode${piece.getPieceName()}${Console.RESET} "
            }
        } else {
            "  \u001b[37mO\u001b[0m  " // BIJELO "O" za prazno
        }
    }

    //FUNCKIJA ZA BOJU
    private def getColorCode(color: String): String = color.toLowerCase match {
        case "red"    => Console.RED
        case "green"  => Console.GREEN
        case "yellow" => Console.YELLOW
        case "blue"   => Console.BLUE
        case "Empty"  => Console.WHITE
        case _        => Console.RESET
    }


}

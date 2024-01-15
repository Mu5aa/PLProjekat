import scala.Array

class Player(val id: Int, val color: String) {
    private var pieces: Array[Piece] = new Array[Piece](4)
    private var safeHouse: Array[Piece] = new Array[Piece](4)

    def initializePieces(): Unit = {
        for (i <- pieces.indices) {
            pieces(i) = new Piece(i + 1, color, s"${color.charAt(0).toUpper}P${i + 1}")
        }
    }

    // printanje figura
    def printPieces(): Unit = {
        if (pieces != null) {
            for (i <- 0 until 4) {
                println(pieces(i).getPieceName())
            }
        }
    }

    // printanje figura u kucici
    def printSafeHouse(): Unit = {
        if (safeHouse != null) {
            for (i <- 0 until 4) {
                if (safeHouse(i) != null) {
                    println(safeHouse(i).getPieceName())
                } else {
                    println("Empty slot in safeHouse")
                }
            }
        }
    }

    // micanje figura po id
    def getPieceById(pieceId: Int): Piece = {
        pieces.find(_.getPieceId() == pieceId).orNull
    }

    // ucerat u kucicu
    def enterPiece(pieceId: Int): Unit = {
        val piece = getPieceById(pieceId)

        if (piece != null) {
            if (safeHouse.contains(null)) {
                val emptyIndex = safeHouse.indexOf(null)
                safeHouse(emptyIndex) = piece
                println(s"Piece ${piece.getPieceName()} entered the safeHouse.")
            } else {
                println("SafeHouse is full. Cannot enter more pieces.")
            }
        } else {
            println("Invalid piece ID. Cannot enter piece into the safeHouse.")
        }
    }

    //  provjera jel kucica puna
    def isSafeHouseFull: Boolean = {
        !safeHouse.contains(null)
    }

    // da li igrac ima ista pomjerit
    def noPiecesOnBoard(board: Board): Boolean = {
        // da li su figure prikazan 
        pieces.forall(piece => board.findPiecePosition(piece).isEmpty)
    }

    // biranje pomjeranja figure
    def selectPieceToMove(board: Board): Int = {
        // Figgure koje su na ploci
        val piecesOnBoard = pieces.filter(piece => board.findPiecePosition(piece).isDefined)

        println(s"Player $id (${color}), select a piece to move:")
        for (i <- piecesOnBoard.indices) {
            println(s"${i + 1}: Piece ${piecesOnBoard(i).getPieceName()}")
        }

        var selectedPieceIndex = -1
        do {
            print("Enter the number of the piece you want to move: ")
            selectedPieceIndex = scala.io.StdIn.readInt() - 1
        } while (selectedPieceIndex < 0 || selectedPieceIndex >= piecesOnBoard.length)

        // vrati id figure koju zelimo pomjerit
        piecesOnBoard(selectedPieceIndex).getPieceId()
    }

    def askIfStartNewPiece(): Boolean = {
        print(s"Player $id, do you want to start a new piece? (y/N): ")
        val response = scala.io.StdIn.readLine().trim.toLowerCase
        response == "y" || response == "yes"
    }


}

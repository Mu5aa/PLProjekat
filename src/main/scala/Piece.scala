class Piece (val pieceId: Int, val color: String, val pieceName: String) {
    def getPieceId(): Int = {
        pieceId
    }

    // BOJA FIGURE
    def getColor(): String = {
        color
    }

    // IME FIGURE
    def getPieceName(): String = {
        pieceName
    }
}

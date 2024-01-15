object Main extends App {
  println("Hello, World!")



  
  val board = new Board()

  board.initializeBoard()


  val players = Array(
    new Player(1, "red"),
    new Player(2, "green"),
    new Player(3, "blue"),
    new Player(4, "yellow")
  )

  players.foreach(_.initializePieces())

  val game = new Game(board, players)
  game.startGame()


  board.printBoard3();





}

package Kattis

/**
 * Created by Rahul on 12/27/16.
 */
class State(val potNumber : Int) {

}

class StateMachine {
  def commandA(currentState: State) : State = {
    if (currentState.potNumber == 1 || currentState.potNumber == 2) {
      if (currentState.potNumber == 2) {
        new State(1)
      }else {
        new State(2)
      }
    }else {
      currentState
    }
  }

  def commandB(currentState: State) : State = {
    if (currentState.potNumber == 2 || currentState.potNumber == 3) {
      //new State(3)
      if (currentState.potNumber == 2) {
        new State(3)
      }else {
        new State(2)
      }
    }else {
      currentState
    }
  }

  def commandC(currentState: State) : State = {
    if (currentState.potNumber == 3 || currentState.potNumber == 1) {
      if (currentState.potNumber == 3) {
        new State(1)
      }else {
        new State(3)
      }
    }else {
      currentState
    }
  }


}
object Trik {
  def main(str : Array[String]) : Unit = {
    val commands = scala.io.StdIn.readLine()
    var state = new State(1)
    val stateMachine = new StateMachine
    commands.foreach(cmd => {
      cmd match {
        case 'A' => {
          state = stateMachine.commandA(state)
        }
        case 'B' => {
          state = stateMachine.commandB(state)
        }
        case 'C' => {
          state = stateMachine.commandC(state)
        }

      }
    })

    println(state.potNumber)
  }
}



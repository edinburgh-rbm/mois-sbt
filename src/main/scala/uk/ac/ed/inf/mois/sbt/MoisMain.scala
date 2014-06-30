package uk.ac.ed.inf.mois.sbt

import sbt._
import java.io.{File, PrintWriter}
import uk.ac.ed.inf.mois

abstract class MoisMain extends xsbti.AppMain
{
  val process: mois.Process
  val state: mois.State

  def run(configuration: xsbti.AppConfiguration): xsbti.MainResult =
    MainLoop.runLogged(initialState(configuration))

  def initialState(configuration: xsbti.AppConfiguration): State = {
    val commandDefinitions = evolve +: BasicCommands.allBasicCommands
    val commandsToRun = Evolve +: "iflast shell" +: configuration.arguments.map(_.trim)

    State(configuration, commandDefinitions, Set.empty, None, commandsToRun, State.newHistory,
          AttributeMap.empty, initialGlobalLogging, State.Continue)
  }

  val Evolve = "evolve"
  val evolve = Command.command(Evolve) { s =>
    process.state <<< state
    s.log.info(s"initial state ${process}")
    s.log.info(s"delta ${process(0, 50)}")
    s.log.info(s"final state ${process}")
    s
  }

  val console = ConsoleOut.systemOutOverwrite(ConsoleOut.overwriteContaining("Resolving "))
  def initialGlobalLogging: GlobalLogging = GlobalLogging.initial(MainLogging.globalDefault(console), File.createTempFile("mois", ".log"), console)

  //def initialGlobalLogging: GlobalLogging =
    //GlobalLogging.initial(MainLogging.globalDefault _, File.createTempFile("hello", "log"))
}

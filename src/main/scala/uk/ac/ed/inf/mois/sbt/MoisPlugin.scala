package uk.ac.ed.inf.mois.sbt

import sbt._
import Keys._

object MoisPlugin extends AutoPlugin {
  override lazy val projectSettings = Seq(commands += mois)

  // n.b we should do something better here, like check if
  // mois is in libraryDependencies
  override def trigger = allRequirements

  lazy val mois =
    Command.command("mois") { (state: State) =>
      println("hello world")
      state
  }
}

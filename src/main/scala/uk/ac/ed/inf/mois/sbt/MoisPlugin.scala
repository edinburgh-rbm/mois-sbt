package uk.ac.ed.inf.mois.sbt

import sbt._
import Keys._

object MoisPlugin extends AutoPlugin {
  object autoImport {
    lazy val mois = inputKey[Unit]("foo bar baz")
    lazy val moisSettings: Seq[Def.Setting[_]] = Seq(
      mois := {
	val log = streams.value.log
	log.info("hello world")
      }
    )
  }
  import autoImport._
  override def trigger = allRequirements
  override val projectSettings = moisSettings
}

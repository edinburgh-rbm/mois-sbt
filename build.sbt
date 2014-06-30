name := "mois-sbt"

organization := "uk.ac.ed.inf"

version := "1.99.2-SNAPSHOT"

scalaVersion := "2.11.1"

crossScalaVersions := Seq("2.11.1", "2.10.4")

// For mois
resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

// For SBT
resolvers += "Typesafe Simple Repository" at "http://repo.typesafe.com/typesafe/simple/maven-releases/"

libraryDependencies += "uk.ac.ed.inf" %% "mois" % "1.99.2-SNAPSHOT"

libraryDependencies += "org.scala-sbt" % "command" % "0.13.5"

libraryDependencies := {
  CrossVersion.partialVersion(scalaVersion.value) match {
    case Some((2, scalaMajor)) if scalaMajor >= 11 =>
      libraryDependencies.value :+ "org.scala-lang.modules" %% "scala-xml" % "1.0.2"
    case _ =>
      libraryDependencies.value
  }
}

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (version.value.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

publishMavenStyle := true

publishArtifact in Test := false

pomIncludeRepository := { x => false }

pomExtra := (
  <url>https://gallows.inf.ed.ac.uk/rbm/mois</url>
  <licenses>
    <license>
      <name>GPLv3 or Later</name>
      <url>https://www.gnu.org/licenses/gpl-3.0.html</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:edinburgh-rbm/mois.git</url>
    <connection>scm:git:git@github.com:edinburgh-rbm/mois.git</connection>
  </scm>
  <developers>
    <developer>
      <id>dominik</id>
      <name>Dominik Bucher</name>
    </developer>
    <developer>
      <id>rhz</id>
      <name>Ricardo Honorato Z</name>
    </developer>
    <developer>
      <id>ww</id>
      <name>William Waites</name>
    </developer>
  </developers>
)

useGpg := true

usePgpKeyHex("84225CBC")

credentials += Credentials(Path.userHome / ".ivy2" / ".credentials")

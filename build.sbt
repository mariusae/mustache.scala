name := "mustache.scala"

organization := "me.gladwell"

version := "1.0.4-SNAPSHOT"

scalaVersion := "2.9.3"

libraryDependencies += "commons-lang" % "commons-lang" % "2.6"

libraryDependencies += "org.scala-tools.testing" %% "specs" % "1.6.9" % "test"

sbtantlr.SbtAntlrPlugin.antlrSettings

publishMavenStyle := true

publishTo <<= version { (v: String) =>
  val nexus = "https://oss.sonatype.org/"
  if (v.trim.endsWith("SNAPSHOT"))
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := (
  <url>https://github.com/mariusaeriksen/mustache.scala</url>
  <licenses>
    <license>
      <name>MIT</name>
      <url>http://opensource.org/licenses/MIT</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com:mariusaeriksen/mustache.scala.git</url>
    <connection>scm:git:git@github.com:mariusaeriksen/mustache.scala.git</connection>
  </scm>
  <developers>
    <developer>
      <id>mariusaeriksen</id>
      <name>Marius A. Eriksen</name>
      <url>http://monkey.org/~marius/</url>
    </developer>
  </developers>
)

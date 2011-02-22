import sbt._
import com.twitter.sbt._

class MustacheScalaProject(info: ProjectInfo)
  extends StandardProject(info)
  with DefaultRepos
{
  override def compileOrder = CompileOrder.JavaThenScala

  val antlr = "org.antlr" % "antlr" % "3.3"

  // For testing:
  val specs = "org.scala-tools.testing" % "specs_2.8.0" % "1.6.5" % "test"
}

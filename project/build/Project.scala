import sbt._
import com.twitter.sbt._

class DeerstacheProject(info: ProjectInfo)
  extends StandardServiceProject(info)
  with DefaultRepos
{
  override def compileOrder = CompileOrder.JavaThenScala

  val antlr = "org.antlr" % "antlr" % "3.3"
  // val specs = "org.scala-tools.testing" % "specs" % "1.6.2.1"
}

import java.io.File
import sbt._
import com.twitter.sbt._

class MustacheScalaProject(info: ProjectInfo)
  extends StandardProject(info)
  with DefaultRepos
{
  lazy val publishTo = Resolver.file("local-repo", new File("publish"))

  override def compileOrder = CompileOrder.JavaThenScala

  val antlr       = "org.antlr"    % "antlr"        % "3.3"
  val commonsLang = "commons-lang" % "commons-lang" % "2.6"

  // Test-only:
  val specs = "org.scala-tools.testing" % "specs_2.8.0" % "1.6.5" % "test"

  /**
   * ANTLR compilation.
   */
  def antlrSources = (mainSourcePath / "antlr" ##) ** "*.g"
  def antlrPath = "lib_build" / "antlr-3.3-complete.jar"
  def antlrOutputPath = outputPath / "gen-antlr"

  def compileAntlrAction = task {
    import Process._
    antlrOutputPath.asFile.mkdirs()
    val tasks = antlrSources.getPaths.map { path =>
      execTask { "java -jar %s -o %s %s".format(
        antlrPath, antlrOutputPath.absolutePath, path)
      }
    }

    if (tasks.isEmpty) None else tasks.reduceLeft { _ && _ }.run
  }

  lazy val compileAntlr =
    compileAntlrAction describedAs("Compile ANTLR into java")

  lazy val cleanAntlr = cleanTask(antlrOutputPath) describedAs(
    "Clean generated ANTLR sources")

  override def cleanAction =
    super.cleanAction dependsOn(cleanAntlr)

  override def mainSourceRoots =
    super.mainSourceRoots +++ (antlrOutputPath ##)

  // uncomment for auto compiles.
  // override def compileAction = super.compileAction dependsOn(compileAntlr)
}

//name := "cwf"


//libraryDependencies ++=
lazy val dep = Seq(
  "org.scalaz" % "scalaz-core_2.12" % "7.2.15",
  "org.scalaz" % "scalaz-effect_2.12" % "7.2.15",
//  "org.scala-lang.modules" %% "scala-swing" % "2.0.0-M2",
  "org.scalatest" %% "scalatest" % "3.0.1" % "test",
  "org.scalacheck" %% "scalacheck" % "1.13.4" % "test",
  "de.tuda.stg" %% "rescala" % "0.19.0",
  "io.monadless" %% "monadless-core" % "0.0.13"
)

testOptions in Test += Tests.Argument(TestFrameworks.ScalaCheck,
  "-minSize","10","-maxSize", "20", "-minSuccessfulTests", "200", "-maxDiscardRatio", "30", "-workers", "1", "-verbosity", "2")

lazy val commonSettings = Seq(
  version := "1.0",
  scalaVersion := "2.12.3",
  resolvers += Resolver.bintrayRepo("rmgk", "maven"),
  resolvers += Resolver.bintrayRepo("pweisenburger", "maven"),
  resolvers += Resolver.sonatypeRepo("releases"),
  libraryDependencies ++= dep,
  libraryDependencies ++= (scalaBinaryVersion.value match {
    case "2.10" =>
      compilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full) :: Nil
    case _ =>
      Nil
  }),
  scalacOptions ++= Seq("-unchecked","-deprecation","-feature","-J-Xss256m","-J-Xmx4096m"
    //, "-Xprint:tailcall"
    //  , "-Ymacro-debug-lite", "-Yshow-trees-compact"
  ),
  testOptions in Test += Tests.Argument(TestFrameworks.ScalaCheck,
    "-minSize","10","-maxSize", "20", "-minSuccessfulTests", "200", "-workers", "1", "-verbosity", "2"),
  addCompilerPlugin("org.spire-math" %% "kind-projector" % "0.9.4")
  // if your project uses multiple Scala versions, use this for cross building
  //addCompilerPlugin("org.spire-math" % "kind-projector" % "0.9.4" cross CrossVersion.binary)
)

lazy val root = (project in file(".")).settings(
  name := "context-workflow"//,
  //commonSettings
).aggregate(cw)

//lazy val macros = (project in file("macros")).settings(
//  name := "macro",
//  commonSettings
//)

lazy val cw = (project in file("cworkflow")).settings(
  name := "cworkflow",
  commonSettings
)

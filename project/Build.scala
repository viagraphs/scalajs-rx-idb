import sbt.Keys._
import sbt._
import utest.jsrunner.Plugin.internal._

import scala.scalajs.sbtplugin.ScalaJSPlugin.ScalaJSKeys._
import scala.scalajs.sbtplugin.ScalaJSPlugin._
import scala.scalajs.sbtplugin.env.phantomjs.PhantomJSEnv

object Build extends sbt.Build {

  lazy val `scalajs-rx-idb` =
    project.in(file("."))
      .settings(scalaJSSettings:_*)
      .settings(utestJsSettings:_*)
      .settings(
        organization := "com.viagraphs",
        name := "scalajs-rx-idb",
        version := "0.0.1",
        scalaVersion := "2.11.0",
        scalacOptions ++= Seq("-unchecked", "-feature"),
        libraryDependencies ++= Seq(
          "org.scala-lang.modules.scalajs" %%% "scalajs-dom" % "0.7-SNAPSHOT",
          "org.monifu" %%% "monifu-rx-js" % "0.14.1",
          "com.lihaoyi" %%% "utest" % "0.2.5-SNAPSHOT" % "test"
        ),
        requiresDOM := true,
        test in Test := (test in (Test, fastOptStage)).value,
        testOnly  in Test := (testOnly  in(Test, fastOptStage)).evaluated,
        testQuick in Test := (testQuick in(Test, fastOptStage)).evaluated,
        persistLauncher in Test := false,
        postLinkJSEnv in Test := new PhantomJSEnv(autoExit = false)
      )
}
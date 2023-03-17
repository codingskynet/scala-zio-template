name := "scala-zio-presentation"

libraryDependencies ++= Dependencies.presentation

Compile / PB.protoSources += baseDirectory.value / "interface/grpc"
Compile / PB.targets := Seq(
  scalapb.gen() -> (Compile / sourceManaged).value / "scalapb",
  scalapb.zio_grpc.ZioCodeGenerator -> (Compile / sourceManaged).value / "scalapb"
)
